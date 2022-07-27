package com.example.Model.user;


import com.example.Model.Civilization;
import com.google.gson.annotations.Expose;

public class User {	
	@Expose private String username;
	@Expose private String password;
	@Expose private String nickname;
	private Civilization civilization;

	public User(){}

	public User(String username, String password, String nickname) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
	}

	public boolean isPasswordEqualTo(String password) {
		return this.password.equals(password);
	}
	public void setPassword(String newPassword, String currentPassword) {
		if (password.equals(currentPassword))
			password = newPassword;
	}
		
	public void setNickname(String newNickname) { this.nickname = newNickname;}
	
	public String getUsername(){ return username;}
	public String getNickname() { return nickname; }

	public void setCivilization(Civilization civilization){ this.civilization=civilization;}
	public Civilization getCivilization(){ return this.civilization;}
	
}
