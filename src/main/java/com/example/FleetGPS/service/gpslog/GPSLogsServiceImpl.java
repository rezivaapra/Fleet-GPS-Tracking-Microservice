package com.example.FleetGPS.service.gpslog;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.FleetGPS.payload.request.GPSLogRequest;
import com.example.FleetGPS.payload.response.Response;
import com.example.FleetGPS.repository.GPSLogRepository;
import com.example.FleetGPS.repository.VehicleRepository;
import com.example.FleetGPS.model.GPSLog;
import com.example.FleetGPS.model.Vehicle;

@Service
public class GPSLogsServiceImpl implements GPSLogService {
    @Autowired
    private GPSLogRepository gpsLogRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Value("${gpslog.cleanup.days}")
    private int cleanupDays;

    @Scheduled(cron = "0 * * * * ?") 
    public void cleanUpOldLogs() {
        LocalDateTime cutoffDate = LocalDateTime.now().minus(cleanupDays, ChronoUnit.DAYS);
        List<GPSLog> oldLogs = gpsLogRepository.findByTimestampBefore(cutoffDate);
        gpsLogRepository.deleteAll(oldLogs);
        System.out.println("Cleaned up GPS logs older than " + cleanupDays + " days.");
    }

    @Override
    public Response addGPSLog(GPSLogRequest gpsLogRequest) {
        GPSLog gpsLog = new GPSLog();

        gpsLog.setLatitude(gpsLogRequest.getLatitude());
        gpsLog.setLongitude(gpsLogRequest.getLongitude());
        gpsLog.setSpeed(gpsLogRequest.getSpeed());
        gpsLog.setTimestamp(gpsLogRequest.getTimestamp());
        if (gpsLogRequest.getSpeed() > 100) {
            gpsLog.setSpeedViolation(true);
        }

        Vehicle vehicle = vehicleRepository.findById(gpsLogRequest.getVehicleId())
            .orElseThrow(() -> new NoSuchElementException("Vehicle not found!"));
        gpsLog.setVehicle(vehicle);

        gpsLog = gpsLogRepository.save(gpsLog);

        Response response = new Response();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("GPSLog added successfully!");
        response.setData(gpsLog);

        return response;
    }

    @Override
    public Response getLastLocation(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
            .orElseThrow(() -> new NoSuchElementException("Vehicle not found!"));
        
        GPSLog lastLog = gpsLogRepository.findFirstByVehicleOrderByTimestampDesc(vehicle);
        
        Response response = new Response();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Last location fetched successfully!");
        response.setData(lastLog);
        
        return response;
    }

    @Override
    public Response getHistory(Long vehicleId, LocalDateTime from, LocalDateTime to) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
            .orElseThrow(() -> new NoSuchElementException("Vehicle not found!"));
        
        List<GPSLog> logs = gpsLogRepository.findByVehicleAndTimestampBetweenOrderByTimestampDesc(vehicle, from, to);
        
        Response response = new Response();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("History logs fetched successfully!");
        response.setData(logs);
        
        return response;
    }

    // @Override
    // public Response getGPSLogs() {
    //     List<GPSLog> gpsLogs = gpsLogRepository.findAll();

    //     Response response = new Response(HttpStatus.OK.value(), "GPSLogs fetched successfully!", gpsLogs);

    //     return response;
    // }

    // @Override
    // public Response getGPSLogsByVehicleId(Long vehicleId) {
    //     Vehicle vehicle = vehicleRepository.findById(vehicleId)
    //         .orElseThrow(() -> new NoSuchElementException("Vehicle not found!"));

    //     // Mengambil log GPS berdasarkan kendaraan
    //     List<GPSLog> gpsLogs = gpsLogRepository.findByVehicle(vehicle);

    //     return new Response(HttpStatus.OK.value(), "GPSLogs fetched successfully!", gpsLogs);
    // }

    // @Override
    // public Response updateGPSLogById(Long id, GPSLogRequest gpsLogRequest) {
    //     GPSLog gpsLog = gpsLogRepository.findById(id).orElseThrow(() -> new NoSuchElementException("GPSLog not found!"));

    //     gpsLog.setLatitude(gpsLogRequest.getLatitude());
    //     gpsLog.setLongitude(gpsLogRequest.getLongitude());
    //     gpsLog.setSpeed(gpsLogRequest.getSpeed());
    //     // gpsLog.setTimestamp(gpsLogRequest.getTimestamp());

    //     gpsLog = gpsLogRepository.save(gpsLog);

    //     return new Response(HttpStatus.OK.value(), "GPSLog updated successfully!", gpsLog);
    // }

    // @Override
    // public Response deleteGPSLogById(Long id) {
    //     GPSLog gpsLog = gpsLogRepository.findById(id).orElseThrow(() -> new NoSuchElementException("GPSLog not found!"));

    //     gpsLogRepository.delete(gpsLog);

    //     return new Response(HttpStatus.OK.value(), "GPSLog deleted successfully!", null);   
    // }
}