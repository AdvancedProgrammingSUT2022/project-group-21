package com.example;

import java.io.IOException;

import com.example.Network.NetworkController;
import com.example.Network.Request;

public class Main {
	public static void main(String[] args) throws IOException {
		// test();
		new NetworkController(8080).start();
	}

	// public static void test(){
	// 	String input = "{\"requestType\":\"LOGIN\",\"uuid\":\"d2e0c1f1-2226-4328-ba31-226966611854\",\"params\":{\"password\":\"ali\",\"username\":\"ali\"}}";
	// 	Request request = Request.fromJson(input);

	// }
}
