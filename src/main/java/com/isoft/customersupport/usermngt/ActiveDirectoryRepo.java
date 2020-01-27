package com.isoft.customersupport.usermngt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActiveDirectoryRepo extends JpaRepository<ActiveDirectory,Integer> {
	Optional<ActiveDirectory> findByEmail (String email);
}
