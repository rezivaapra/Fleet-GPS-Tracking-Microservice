CREATE INDEX idx_plate_number ON vehicles(plate_number);
CREATE INDEX idx_vehicle_id ON gps_logs(vehicle_id);
CREATE INDEX idx_timestamp ON gps_logs(timestamp);