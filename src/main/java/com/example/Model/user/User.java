package com.example.Model.user;


import com.example.Model.Civilization;
import com.google.gson.annotations.Expose;

public class User {	
	@Expose private String username;
	@Expose private String nickname;
	private Civilization civilization;

	public User(String username, String nickname) {
		this.username = username;
		this.nickname = nickname;
	}
	
	public void setNickname(String newNickname) { this.nickname = newNickname;}
	
	public String getUsername(){ return username;}
	public String getNickname() { return nickname; }

	public void setCivilization(Civilization civilization){ this.civilization=civilization;}
	public Civilization getCivilization(){ return this.civilization;}

	public String getPassword() {
		return password;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj==null) return this==null;
		if (!(obj instanceof User)) return false;
		return ((User) obj).username.equals(username);
	}
}
