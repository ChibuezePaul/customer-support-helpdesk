package com.isoft.customersupport.ticket.category;

import com.isoft.customersupport.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryServiceImpl ( CategoryRepository categoryRepository ) {this.categoryRepository = categoryRepository;}
	
	@Override
	public Category createCategory ( Category category ) {
		if(category == null) throw new ArithmeticException ();
		
		Category newCategory = new Category ();
		newCategory.setTicketClass ( category.getTicketClass () );
		newCategory.setAssignee ( category.getAssignee () );
		return categoryRepository.save ( newCategory );
	}
	
	@Override
	public List< Category > findAllCategory () {
		return categoryRepository.findAll ( Sort.by ( "ticketClass" ));
	}
}
