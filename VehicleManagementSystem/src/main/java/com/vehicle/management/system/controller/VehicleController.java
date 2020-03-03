package com.vehicle.management.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vehicle.management.system.dto.Vehicle;
import com.vehicle.management.system.dto.VehicleConstants;
import com.vehicle.management.system.service.VehicleService;


@Controller
@RequestMapping("/vehicle")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	
	@RequestMapping(value = "/get",method=RequestMethod.GET)
	public @ResponseBody String getVehicle(HttpServletRequest request) {
		try {
			return vehicleService.fetchVehicle();
		} catch (Exception e) {
			System.out.println("Controller: "+e.getMessage());
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "/get/{id}",method=RequestMethod.GET)
	public @ResponseBody String getVehicleById(@PathVariable(value=VehicleConstants.VEHICLE_ID) Integer id) {
		try {
			Vehicle vehicle = new Vehicle();
			vehicle.setId(id);
			return vehicleService.fetchVehicleById(id);
		} catch (Exception e) {
			System.out.println("Controller: "+e.getMessage());
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "/insert",method=RequestMethod.POST)
	public @ResponseBody String insertVehicle(HttpServletRequest request) {
		try {
			Vehicle vehicle = new Vehicle();
			vehicle.setMake(request.getParameter(VehicleConstants.VEHICLE_MAKE));
			vehicle.setModel(request.getParameter(VehicleConstants.VEHICLE_MODEL));
			vehicle.setYear(Integer.valueOf(request.getParameter(VehicleConstants.VEHICLE_YEAR)));
			return vehicleService.insertVehicle(vehicle.getMake(), vehicle.getModel(), vehicle.getYear());
		} catch (Exception e) {
			System.out.println("Controller: "+e.getMessage());
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "/delete/{id}",method=RequestMethod.DELETE)
	public @ResponseBody String deleteVehicle(@PathVariable(value = VehicleConstants.VEHICLE_ID) Integer id) {
		try {
			Vehicle vehicle = new Vehicle();
			vehicle.setId(id);
			return vehicleService.deleteVehicle(vehicle.getId());
		} catch (Exception e) {
			System.out.println("Controller: "+e.getMessage());
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "/update",method=RequestMethod.POST)
	public @ResponseBody String updateVehicle(HttpServletRequest request) {
		try {
			Vehicle vehicle = new Vehicle();
			vehicle.setModel(request.getParameter(VehicleConstants.VEHICLE_MODEL));
			vehicle.setId(Integer.valueOf(request.getParameter(VehicleConstants.VEHICLE_ID)));
			return vehicleService.updateVehicle(vehicle.getId(), vehicle.getModel());
		} catch (Exception e) {
			System.out.println("Controller: "+e.getMessage());
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "/filter",method=RequestMethod.GET)
	public @ResponseBody String filterVehicle(HttpServletRequest request) {
		try {
			Vehicle vehicle = new Vehicle();
			vehicle.setMake(request.getParameter(VehicleConstants.VEHICLE_MAKE));
			vehicle.setModel(request.getParameter(VehicleConstants.VEHICLE_MODEL));
			return vehicleService.filterVehicle(vehicle.getMake(), vehicle.getModel());
		} catch (Exception e) {
			System.out.println("Controller: "+e.getMessage());
			return e.getMessage();
		}
	}
}
