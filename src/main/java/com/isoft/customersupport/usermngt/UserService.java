package com.isoft.customersupport.usermngt;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserService {
	User createUser(@NotNull User userCmd);
	User updateUser(@NotNull User userCmd);
	User findUserByEmail (@NotNull String email);
	User findUserById (@NotNull Integer id);
	void deleteUser(@NotNull String email);
	List<User> findAllUsers ();
}
