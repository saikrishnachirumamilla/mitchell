package com.vehicle.management.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicle.management.system.dao.VehicleDao;
import com.vehicle.management.system.dto.VehicleConstants;

@Service
public class VehicleServiceImpl implements VehicleService{
	
	@Autowired
	private VehicleDao vehicleDao;
	
	public boolean validateMake(String make) throws Exception{
		try {
			if(make.isEmpty() || make.equals(null)) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public boolean validateModel(String model)throws Exception{
		try {
			if(model.isEmpty() || model.equals(null)) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public boolean validateYear(Integer year)throws Exception{
		try {
			if(!(year>=1950 && year<=2050)) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String fetchVehicle() throws Exception {
		try {
			return vehicleDao.fetchVehicle();			
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String fetchVehicleById(Integer id) throws Exception {
		try {
			return vehicleDao.fetchVehicleById(id);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String insertVehicle(String make, String model, Integer year) throws Exception {
		try {
			if(validateMake(make) && validateModel(model) && validateYear(year)) {
				return vehicleDao.insertVehicle(make, model, year);
			}
			System.out.println("Please enter valid values.");
			return VehicleConstants.VEHICLE_FAILURE;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String deleteVehicle(Integer id) throws Exception {
		try {
			return vehicleDao.deleteVehicle(id);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String updateVehicle(Integer id, String model) throws Exception {
		try {
			if(validateModel(model)) {
				return vehicleDao.updateVehicle(id, model);
			}
			System.out.println("Please enter valid values.");
			return VehicleConstants.VEHICLE_FAILURE;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String filterVehicle(String make, String model) throws Exception {
		try {
			String query = "";
			if(validateMake(make) && validateModel(model)) {
				query = query + " where make = '"+make+"' and model = '"+model+"'";
			}else if(validateMake(make)) {
				query = query + " where make = '"+make+"'";
			}else if(validateMake(model)) {
				query = query + " where model = '"+model+"'";
			}
			return vehicleDao.filterVehicle(query);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
}
