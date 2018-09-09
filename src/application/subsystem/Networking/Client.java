package application.subsystem.Networking;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;

public class Client {

	Socket socket;
	Inet4Address ip;
	int port;

	public Client(String ip, String port) {
		try {
			this.port = Integer.parseInt(port);
			this.ip = (Inet4Address) Inet4Address.getByName(ip);
			
			socket = new Socket(this.ip, this.port);
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			
			dout.writeUTF("ClientTest");
			dout.flush();
			
			dout.close();
			
			socket.close();
			
			
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

	}

}
