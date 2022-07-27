package com.example.Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkController {
	private ServerSocket serverSocket;
	
	public NetworkController(int port) throws IOException{
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("listening on port "+port);
		} catch (IOException e) {
			System.out.println("error: cant open server socket on port "+port);
			throw e;
		}
	}

	public void start(){
		try {
			while (true){
				Socket socket = serverSocket.accept();
				new SocketControllerThread(socket).start();
			}
		} catch (IOException e) {
			System.out.println("closing server");
			e.printStackTrace();
		}
	}

}
