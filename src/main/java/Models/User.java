package Models;

import javax.swing.table.TableRowSorter;
import java.util.ArrayList;

public class User {
    private static ArrayList<User> allUsers;
    private String username;
    private String nickname;
    private String password;
    private Civilization civilization;

    public User(String username, String nickname, String password) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        allUsers.add(this);
    }
    public static User getUserByUsername(String username) {
        for (User user : allUsers)
            if (user.getUsername().equals(username))    return user;
        return null;
    }
    public static boolean doesNicknameExist(String nickname) {
        for (User user : allUsers)
            if (user.getNickname().equals(nickname))     return true;
        return false;
    }
    public boolean isPasswordEqualTo(String password) {
        return this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }
    public String getNickname() {
        return nickname;
    }
}
