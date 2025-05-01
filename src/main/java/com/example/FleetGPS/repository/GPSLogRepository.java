package com.example.FleetGPS.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FleetGPS.model.GPSLog;
import com.example.FleetGPS.model.Vehicle;

public interface GPSLogRepository extends JpaRepository<GPSLog, Long> {
    GPSLog findFirstByVehicleOrderByTimestampDesc(Vehicle vehicle);
    
    List<GPSLog> findByVehicleAndTimestampBetweenOrderByTimestampDesc(Vehicle vehicle, LocalDateTime from, LocalDateTime to);

    List<GPSLog> findByVehicle(Vehicle vehicle);

    List<GPSLog> findByTimestampBefore(LocalDateTime cutoffDate);
}
