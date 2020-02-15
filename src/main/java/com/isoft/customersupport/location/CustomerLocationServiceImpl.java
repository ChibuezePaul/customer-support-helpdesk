package com.isoft.customersupport.location;

import com.isoft.customersupport.config.Util;
import com.isoft.customersupport.exception.ValidationException;
import com.isoft.customersupport.usermngt.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Slf4j
public class CustomerLocationServiceImpl implements CustomerLocationService {
	
	private final CustomerLocationRepository locationRepository;
	private UserRepository userRepository;
	
	@Autowired
	public CustomerLocationServiceImpl ( CustomerLocationRepository locationRepository, UserRepository userRepository ) {
		this.locationRepository = locationRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public CustomerLocation createCustomerLocation ( CustomerLocation location ) {
		if(location == null) throw new ValidationException ();
		Util.validateIsSuperUser (userRepository);
		CustomerLocation newLocation = new CustomerLocation ();
		newLocation.setCustomerBranch ( location.getCustomerBranch () );
		newLocation.setCustomerCity ( location.getCustomerCity () );
		newLocation.setCustomerCountry ( location.getCustomerCountry () );
		return locationRepository.save ( newLocation );
	}
	
	@Override
	public List< CustomerLocation > findAllCustomerLocation () {
		return locationRepository.findAll ( Sort.by ( "id" ));
	}
}
