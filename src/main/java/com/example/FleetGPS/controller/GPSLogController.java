package com.example.FleetGPS.controller;

import com.example.FleetGPS.service.gpslog.GPSLogService;
import com.example.FleetGPS.payload.request.GPSLogRequest;
import com.example.FleetGPS.payload.response.Response;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gps")
public class GPSLogController {
    @Autowired
    private GPSLogService gpsLogService;

    @PostMapping
    public ResponseEntity<?> addGPSLog(@Valid @RequestBody GPSLogRequest gpsLogRequest) {
        try{
            Response response = gpsLogService.addGPSLog(gpsLogRequest);

            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}