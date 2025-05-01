package com.example.FleetGPS.service.gpslog;

import com.example.FleetGPS.payload.response.Response;

import java.time.LocalDateTime;

import com.example.FleetGPS.payload.request.GPSLogRequest;

public interface GPSLogService {
    Response addGPSLog(GPSLogRequest gpsLogRequest);

    Response getLastLocation(Long vehicleId);

    Response getHistory(Long vehicleId, LocalDateTime from, LocalDateTime to);

    // Response getGPSLogs();

    // Response getGPSLogsByVehicleId(Long vehicleId);

    // Response updateGPSLogById(Long id, GPSLogRequest gpsLogRequest);

    // Response deleteGPSLogById(Long id);
}
