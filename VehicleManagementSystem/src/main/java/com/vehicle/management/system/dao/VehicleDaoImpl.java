package com.vehicle.management.system.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.Gson;
import com.vehicle.management.system.dto.VehicleConstants;

public class VehicleDaoImpl implements VehicleDao{
	
	@SuppressWarnings("unused")
	@Autowired
	private DataSource datasource;
	  
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String fetchVehicle;
	private String fetchVehicleById;
	private String deleteVehicle;
	private String insertVehicle;
	private String updateVehicle;
	
	public String getFetchVehicle() {
		return fetchVehicle;
	}
	public void setFetchVehicle(String fetchVehicle) {
		this.fetchVehicle = fetchVehicle;
	}
	public String getFetchVehicleById() {
		return fetchVehicleById;
	}
	public void setFetchVehicleById(String fetchVehicleById) {
		this.fetchVehicleById = fetchVehicleById;
	}
	public String getDeleteVehicle() {
		return deleteVehicle;
	}
	public void setDeleteVehicle(String deleteVehicle) {
		this.deleteVehicle = deleteVehicle;
	}
	public String getInsertVehicle() {
		return insertVehicle;
	}
	public void setInsertVehicle(String insertVehicle) {
		this.insertVehicle = insertVehicle;
	}
	public String getUpdateVehicle() {
		return updateVehicle;
	}
	public void setUpdateVehicle(String updateVehicle) {
		this.updateVehicle = updateVehicle;
	}
	
	@Override
	public String fetchVehicle() throws Exception {
		try {
			Gson gson = new Gson();
			return gson.toJson(this.jdbcTemplate.queryForList(fetchVehicle));
		} catch (Exception e) {
			System.out.println("Unable to fetch Vehicles.");
			return VehicleConstants.VEHICLE_FAILURE;
		}
	}
	
	@Override
	public String fetchVehicleById(Integer id) throws Exception {
		try {
			Gson gson = new Gson();
			return gson.toJson(this.jdbcTemplate.queryForList(fetchVehicleById,id));
		} catch (Exception e) {
			System.out.println("Unable to fetch Vehicle.");
			return VehicleConstants.VEHICLE_FAILURE;
		}
	}
	
	@Override
	public String insertVehicle(String make, String model, Integer year) throws Exception {
		try {
			this.jdbcTemplate.update(insertVehicle, make,model,year);
		} catch (Exception e) {
			System.out.println("Unable to insert vehicle.");
			return VehicleConstants.VEHICLE_FAILURE;
		}
		System.out.println("Successfully inserted vehicle.");
		return VehicleConstants.VEHICLE_SUCCESS;
	}
	
	@Override
	public String deleteVehicle(Integer id) throws Exception {
		try {
			this.jdbcTemplate.update(deleteVehicle,id);
		} catch (Exception e) {
			System.out.println("Unable to delete vehicle.");
			return VehicleConstants.VEHICLE_FAILURE;
		}
		System.out.println("Successfully deleted vehicle.");
		return VehicleConstants.VEHICLE_SUCCESS;
	}
	
	@Override
	public String updateVehicle(Integer id, String model) throws Exception {
		try {
			this.jdbcTemplate.update(updateVehicle,model,id);
		} catch (Exception e) {
			System.out.println("Unable to update vehicle.");
			return VehicleConstants.VEHICLE_FAILURE;
		}
		System.out.println("Successfully updated vehicle.");
		return VehicleConstants.VEHICLE_SUCCESS;
	}
	
	@Override
	public String filterVehicle(String query) throws Exception {
		try {
			Gson gson = new Gson();
			return gson.toJson(this.jdbcTemplate.queryForList(fetchVehicle+query));
		} catch (Exception e) {
			 System.out.println("Unable to filter Vehicles.");
			 return VehicleConstants.VEHICLE_SUCCESS;
		}
	}
	

}
