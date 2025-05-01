package com.example.FleetGPS.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plateNumber")
    private String plateNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GPSLog> gpsLogs = new ArrayList<>();

    public Vehicle() {

    }

    public Vehicle(String plateNumber, String name, String type) {
        super();
        this.plateNumber = plateNumber;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<GPSLog> getGpsLogs() {
        return gpsLogs;
    }

    public void setGpsLogs(List<GPSLog> gpsLogs) {
        this.gpsLogs = gpsLogs;
    }

}
