package com.example.FleetGPS.payload.request;

public class VehicleRequest {
    private String plateNumber;
    private String name;
    private String type;

    public VehicleRequest() {

    }

    public VehicleRequest(String plateNumber, String name, String type) {
        this.plateNumber = plateNumber;
        this.name = name;
        this.type = type;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getName() {
        return name;
    }    

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
