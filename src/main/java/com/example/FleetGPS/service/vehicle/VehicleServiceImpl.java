package com.example.FleetGPS.service.vehicle;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.FleetGPS.model.Vehicle;
import com.example.FleetGPS.payload.request.VehicleRequest;
import com.example.FleetGPS.payload.response.Response;
import com.example.FleetGPS.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Response addVehicle(VehicleRequest vehicleRequest) {
        Vehicle vehicle = new Vehicle();

        vehicle.setPlateNumber(vehicleRequest.getPlateNumber());
        vehicle.setName(vehicleRequest.getName());
        vehicle.setType(vehicleRequest.getType());

        vehicle = vehicleRepository.save(vehicle);

        Response response = new Response();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Vehicle added successfully!");
        response.setData(vehicle);

        return response;
    }

    @Override
    public Response getVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();

        Response response = new Response(HttpStatus.OK.value(), "Vehicles fetched successfully!", vehicles);

        return response;
    }

    @Override
    public Response getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("Vehicle is not found!");
        });
        
        return new Response(HttpStatus.OK.value(), "Vehicle fetched successfully!", vehicle);
    }

    @Override
    public Response updateVehicleById(Long id, VehicleRequest vehicleRequest) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("Vehicle is not found!");
        });
        
        vehicle.setPlateNumber(vehicleRequest.getPlateNumber());
        vehicle.setName(vehicleRequest.getName());
        vehicle.setType(vehicleRequest.getType());

        vehicle = vehicleRepository.save(vehicle);

        return new Response(HttpStatus.OK.value(), "Vehicle updated successfully!", vehicle);
    }

    @Override
    public Response deleteVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("Vehicle is not found!");
        });

        vehicleRepository.delete(vehicle);

        return new Response(HttpStatus.OK.value(), "Vehicle deleted successfully!", null);
    }
}
