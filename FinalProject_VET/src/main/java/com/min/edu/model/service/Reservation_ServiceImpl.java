package com.min.edu.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.model.mapper.IReservation_Dao;

@Service
public class Reservation_ServiceImpl implements IReservation_Service {

	@Autowired
	private IReservation_Dao dao;
	
}
