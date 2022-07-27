package com.example.Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

public class SocketControllerThread extends Thread{
	private final Socket socket;
	private final DataInputStream dataInputStream;
	private final DataOutputStream dataOutputStream;
	private final String uuid;

	public SocketControllerThread(Socket socket) throws IOException{
		this.socket = socket;
		dataInputStream = new DataInputStream(socket.getInputStream());
		dataOutputStream = new DataOutputStream(socket.getOutputStream());
		uuid = UUID.randomUUID().toString();
		dataOutputStream.writeUTF(uuid);
		dataOutputStream.flush();
	}

	private void close() throws IOException{
		socket.close();
		dataInputStream.close();
		dataOutputStream.close();
	}

	@Override
	public void run() {
		try {
			while (true){
				String input=dataInputStream.readUTF();
				System.out.println(input);
				Request request = Request.fromJson(input);
				Response response = request.handle();
				
				dataOutputStream.writeUTF(response.toJson());
				dataOutputStream.flush();
			}
		} catch (IOException e) {
			System.out.println("lost connection to a client");
		}
		
		try {
			close();
		} catch (IOException e) {
			System.out.println("error while closing socket");
			e.printStackTrace();
		}
	}

}
