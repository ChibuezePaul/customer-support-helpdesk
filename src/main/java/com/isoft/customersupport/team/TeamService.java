package com.isoft.customersupport.team;

import java.util.List;

public interface TeamService {
	Team createTeam (Team team);
	List<Team> findAllTeam ();
}
