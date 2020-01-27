package com.isoft.customersupport.ticket.category;

import com.isoft.customersupport.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category createCategory ( Category category ) {
		if(category == null) throw new ArithmeticException ();
		
		Category newCategory = new Category ();
		newCategory.setTicketType ( category.getTicketType () );
		newCategory.setTicketClass ( category.getTicketClass () );
		newCategory.setAssignee ( assignTeamByTicketClass ( newCategory.getTicketClass () ) );
		return categoryRepository.save ( newCategory );
	}
	
	@Override
	public List< Category > findAllCategory () {
		return categoryRepository.findAll ();
	}
	
	private Team assignTeamByTicketClass(String ticketClass){
		Map<String, Team> teamMap = new HashMap<> (  );
		findAllCategory ().forEach ( c -> teamMap.put ( c.getTicketClass (), c.getAssignee () ));
		return teamMap.get ( ticketClass );
	}
}
