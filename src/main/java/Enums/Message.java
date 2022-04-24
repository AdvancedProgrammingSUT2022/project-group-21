package Enums;

public enum Message {
    LOGIN_FIRST("please login first"),
    NAVIGATION_NOT_POSSIBLE("menu navigation is not possible"),
    SIGNUP_SUCCESS("user created successfully!"),
    LOGIN_SUCCESS("user logged in successfully!"),
    INVALID_COMMAND("invalid command"),
    NICKNAME_EXISTS(""),
    USERNAME_EXISTS(""),
    LOGIN_MATCHED(""),
    SIGNUP_MATCHED("");


    private String message;

    Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
