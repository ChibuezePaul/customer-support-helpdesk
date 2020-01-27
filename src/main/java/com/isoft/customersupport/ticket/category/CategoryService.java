package com.isoft.customersupport.ticket.category;

import java.util.List;

public interface CategoryService {
	Category createCategory(Category category);
	List<Category>findAllCategory();
}
