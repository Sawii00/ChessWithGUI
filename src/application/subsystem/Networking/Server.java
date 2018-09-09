package application.subsystem.Networking;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import application.subsystem.Utils.BasicAlertBox;

public class Server {
	
	int port;
	ServerSocket server;
	Socket socket;
	
	public Server(int port) {
		this.port = port;
		
		try {
			server = new ServerSocket(port);
			socket = server.accept();
			DataInputStream din = new DataInputStream(socket.getInputStream());
			
			String message = (String)din.readUTF();
			
			new BasicAlertBox("Server",message, 200, 200);
			socket.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}
