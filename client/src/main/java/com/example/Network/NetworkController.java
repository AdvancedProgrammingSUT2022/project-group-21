package com.example.Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkController {
	private static Socket socket;
	private static DataInputStream dataInputStream;
	private static DataOutputStream dataOutputStream;
	
	private static String uuid;

	public static synchronized void initializeNetwork(int port) throws IOException{
		socket = new Socket("localhost", port);
		dataInputStream = new DataInputStream(socket.getInputStream());
		dataOutputStream = new DataOutputStream(socket.getOutputStream());
		uuid = dataInputStream.readUTF();
	}
	
	public static synchronized void close(){
		try {
			socket.close();
			dataInputStream.close();
			dataOutputStream.close();
		} catch (IOException e) {
			System.err.println("error: cant close socket");
			e.printStackTrace();
		}
	}

	public static synchronized Response makeQuery(Request request) throws IOException{
		request.setUUID(uuid);
		dataOutputStream.writeUTF(request.toJson());
		dataOutputStream.flush();
		String result = dataInputStream.readUTF();
		return Response.fromJson(result);
	}

}
