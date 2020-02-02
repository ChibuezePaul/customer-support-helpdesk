package com.isoft.customersupport.location;

import com.isoft.customersupport.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerLocationServiceImpl implements CustomerLocationService {
	
	private final CustomerLocationRepository locationRepository;
	
	@Autowired
	public CustomerLocationServiceImpl ( CustomerLocationRepository locationRepository ) {this.locationRepository =
		  locationRepository;}
	
	@Override
	public CustomerLocation createCustomerLocation ( CustomerLocation location ) {
		if(location == null) throw new ValidationException ();
		
		CustomerLocation newLocation = new CustomerLocation ();
		newLocation.setCustomerBranch ( location.getCustomerBranch () );
		newLocation.setCustomerCity ( location.getCustomerCity () );
		newLocation.setCustomerCountry ( location.getCustomerCountry () );
		return locationRepository.save ( newLocation );
	}
	
	@Override
	public List< CustomerLocation > findAllCustomerLocation () {
//		locationRepository.findAll ().forEach ( a -> System.out.println (a.getCustomerCountry ().replaceAll ( "","" )) );
		return locationRepository.findAll ();
	}
}
