package application.subsystem.Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	int port;
	ServerSocket server;
	Socket socket;
	
	public Server(int port) {
		this.port = port;
		
		try {
			server = new ServerSocket(port);
			socket = server.accept();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}
