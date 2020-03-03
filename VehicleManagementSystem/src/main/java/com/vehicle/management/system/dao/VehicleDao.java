package com.vehicle.management.system.dao;

public interface VehicleDao {
	
	public String fetchVehicle() throws Exception;
	public String fetchVehicleById(Integer id) throws Exception;
	public String insertVehicle(String make, String model, Integer year) throws Exception;
	public String deleteVehicle(Integer id) throws Exception;
	public String updateVehicle(Integer id,String model) throws Exception;
	public String filterVehicle(String query) throws Exception;
}
