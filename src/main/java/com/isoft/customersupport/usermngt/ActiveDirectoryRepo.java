package com.isoft.customersupport.usermngt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveDirectoryRepo extends JpaRepository<ActiveDirectory,Integer> {
	ActiveDirectory findByEmail (String email);
}
