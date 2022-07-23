package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.example.Contoller.UserController;
import com.example.Model.user.User;
import com.example.Model.user.UserDatabase;

public class UserDatabaseTest{
	@Test
	public void mainTest() {
		User user = new User("ali", "pass", "Ali");
		try {
			UserController.getInstance().registerUser("ali", "pass", "Ali");
			fail("duplicate username");
		} catch (Exception e){}

		UserDatabase.getInstance().addUser(user);
		assertEquals(1, UserDatabase.getInstance().getUserList().size());
		
		User user2 = UserDatabase.getInstance().getUserByUsername("reza");
		assertNull(user2);

		User user3 = UserDatabase.getInstance().getUserByUsername("ali");
		assertEquals(user, user3);

		user.setPassword(":)", "wrong");
		assertEquals(true, user.isPasswordEqualTo("pass"));

		try {
			UserController.getInstance().loginUser("ali", "pass");
		} catch (Exception e) {
			fail("unsuccessful login");
		}
		

	}

}

