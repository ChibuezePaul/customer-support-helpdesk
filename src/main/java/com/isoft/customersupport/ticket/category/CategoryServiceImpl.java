package com.isoft.customersupport.ticket.category;

import com.isoft.customersupport.config.Util;
import com.isoft.customersupport.exception.ValidationException;
import com.isoft.customersupport.usermngt.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Slf4j
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;
	private UserRepository userRepository;
	
	@Autowired
	public CategoryServiceImpl ( CategoryRepository categoryRepository, UserRepository userRepository ) {this.categoryRepository = categoryRepository;
	this.userRepository = userRepository;}
	
	@Override
	public Category createCategory ( Category category ) {
		if(category == null) throw new ValidationException ();
		Util.validateUserRoleAction (userRepository);
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
