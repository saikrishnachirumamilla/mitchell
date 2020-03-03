package com.vehicle.management.system.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vehicle.management.system.dao.VehicleDaoImpl;
import com.vehicle.management.system.dto.Vehicle;
import com.vehicle.management.system.service.VehicleServiceImpl;

public class TestVehicleManagement {

	@InjectMocks
	VehicleServiceImpl vehicleService;
	
	@Mock
	VehicleDaoImpl vehicleDao;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void fetchVehiclesTest() {
		String expectedResult = "[{\"id\":7,\"make\":\"BMW\",\"model\":\"Car\",\"year\":2001},{\"id\":8,\"make\":\"BMW\",\"model\":\"van\",\"year\":2001}]";
		try {
			when(vehicleDao.fetchVehicle()).thenReturn(expectedResult);
			String actualResult = vehicleService.fetchVehicle();
			assertTrue(expectedResult.equals(actualResult));
			verify(vehicleDao,times(1)).fetchVehicle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void fetchVehiclesByIdTest() {
		String expectedResult = "[{\"id\":7,\"make\":\"BMW\",\"model\":\"Car\",\"year\":2001}]";
		try {
			Vehicle vehicle = new Vehicle();
			vehicle.setId(7);
			when(vehicleDao.fetchVehicleById(vehicle.getId())).thenReturn(expectedResult);
			String actualResult = vehicleService.fetchVehicleById(vehicle.getId());
			assertTrue(expectedResult.equals(actualResult));
			verify(vehicleDao,times(1)).fetchVehicleById(vehicle.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void insertVehiclesTest() {
		Vehicle vehicle = new Vehicle();
		vehicle.setMake("BMW");
		vehicle.setModel("7-Series");
		vehicle.setYear(2020);
		try {
			vehicleService.insertVehicle(vehicle.getMake(), vehicle.getModel(), vehicle.getYear());
			verify(vehicleDao,times(1)).insertVehicle(vehicle.getMake(), vehicle.getModel(), vehicle.getYear());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void deleteVehiclesTest() {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(1);
		try {
			vehicleService.deleteVehicle(vehicle.getId());
			verify(vehicleDao, times(1)).deleteVehicle(vehicle.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateVehiclesTest() {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(1);
		vehicle.setModel("3-Series");
		try {
			vehicleService.updateVehicle(vehicle.getId(), vehicle.getModel());
			verify(vehicleDao, times(1)).updateVehicle(vehicle.getId(), vehicle.getModel());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void validateYearTest() {
		try {
			assertTrue(vehicleService.validateYear(2001));
			assertFalse(vehicleService.validateYear(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void validateMakeTest() {
		try {
			assertTrue(vehicleService.validateMake("make"));
			assertFalse(vehicleService.validateMake(""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void validateModelTest() {
		try {
			assertTrue(vehicleService.validateModel("model"));
			assertFalse(vehicleService.validateModel(""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
