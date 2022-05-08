package Enums;

public enum Message {
	SUCCESS("OK"),
	FAIL("Fail"),
	LOGIN_FIRST("please login first"),
	NAVIGATION_NOT_POSSIBLE("menu navigation is not possible"),
	SIGNUP_SUCCESS("user created successfully!"),
	LOGIN_SUCCESS("user logged in successfully!"),
	LOGIN_FAIL("Username and password didn’t match!"),
	LOGOUT_SUCCESS("user logged out successfully!"),
	INVALID_COMMAND("invalid command"),
	NICKNAME_EXISTS(""),
	USERNAME_EXISTS(""),
	LOGIN_MATCHED(""),
	SIGNUP_MATCHED(""),
	
	UNIT_NOT_WORKER(""),
	UNIT_NOT_SETTLER(""),
	UNIT_NOT_MILITARY(""),
	NO_CITY_ON_TILE(""),
	NOT_ENOUGH_GOLD("insufficient gold"),
	ALREADY_ASSIGNED("already assigned"),
	UNSUITABLE_TERRAIN(""),
	TILE_NOT_OWNED(""),
	;


	private final String message;

	Message(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return this.message;
	}
}
