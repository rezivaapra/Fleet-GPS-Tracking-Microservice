package com.example.FleetGPS.service.vehicle;

import com.example.FleetGPS.payload.request.VehicleRequest;
import com.example.FleetGPS.payload.response.Response;

public interface VehicleService {
    Response addVehicle(VehicleRequest vehicleRequest);

    Response getVehicles();

    Response getVehicleById(Long id);

    Response updateVehicleById(Long id, VehicleRequest vehicleRequest);

    Response deleteVehicleById(Long id);
}