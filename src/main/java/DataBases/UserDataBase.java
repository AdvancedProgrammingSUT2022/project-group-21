package DataBases;

import Models.User;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class UserDataBase {
	private static final String PATH = "src\\main\\resources\\User.json";

	public static void addToDataBase(HashMap<String , User> users) {
		try {
			FileWriter myWriter = new FileWriter(PATH);
			myWriter.write(new Gson().toJson(users));
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void clearData() {
		try {
			FileWriter myWriter = new FileWriter(PATH);
			myWriter.write("");
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
