package com.isoft.customersupport.team;

import com.isoft.customersupport.config.Util;
import com.isoft.customersupport.exception.ValidationException;
import com.isoft.customersupport.usermngt.ActiveDirectory;
import com.isoft.customersupport.usermngt.ActiveDirectoryRepo;
import com.isoft.customersupport.usermngt.User;
import com.isoft.customersupport.usermngt.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service @Slf4j
public class TeamServiceImpl implements TeamService {
	
	private final TeamRepository teamRepository;
	private final UserRepository userRepository;
	private final ActiveDirectoryRepo activeDirectoryRepo;
	
	@Autowired
	public TeamServiceImpl ( TeamRepository teamRepository , UserRepository userRepository, ActiveDirectoryRepo activeDirectoryRepo ) {
		this.teamRepository = teamRepository;
		this.userRepository = userRepository;
		this.activeDirectoryRepo = activeDirectoryRepo;
	}
	
	@Override
	public Team createTeam ( Team team ) {
		if(team == null) throw new ValidationException ();
		Util.validateUserRoleAction (userRepository);
		Team newTeam = new Team ();
		newTeam.setName ( team.getName () );
		User existingUser = userRepository.findByEmail(team.getSupervisor().getEmail ());
		if(existingUser != null)
			newTeam.setSupervisor ( existingUser );
		else
			newTeam.setSupervisor ( team.getSupervisor () );
		
		for( ActiveDirectory members : team.getMembers ()){
			members = activeDirectoryRepo.findByEmail ( members.getEmail () );
			newTeam.setMembers( Collections.singleton ( members ) );
		}
		return teamRepository.save ( newTeam );
	}
	
	@Override
	public List< Team > findAllTeam () {
		return teamRepository.findAll ();
	}
}
