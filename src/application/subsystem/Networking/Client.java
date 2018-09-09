package application.subsystem.Networking;

import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;

import application.Utils;

public class Client {
	
	Socket socket;
	Inet4Address ip;
	int port;
	
	public Client(String ip, String port) {
		this.port = Integer.parseInt(port);
		try {
			this.ip = (Inet4Address) Inet4Address.getByName(ip);
			System.out.println(this.ip.toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
