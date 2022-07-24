package com.example;

import java.io.IOException;

import com.example.Network.NetworkController;

public class Main {
	public static void main(String[] args) throws IOException {
		new NetworkController(8080).start();
	}
}
