package application.subsystem.Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {

	Socket socket;
	Inet4Address ip;
	int port;
	Thread t;

	public Client(String ip, String port) {
		try {
			this.port = Integer.parseInt(port);
			this.ip = (Inet4Address) Inet4Address.getByName(ip);
			if (t == null) {
				t = new Thread(this, "Server");
				t.start();
			}
		} catch (UnknownHostException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {

			socket = new Socket(this.ip, this.port);
			System.out.println("Socket creato");
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			DataInputStream din = new DataInputStream(socket.getInputStream());

			dout.writeUTF("ClientTest");
			dout.flush();
			//dout.close();

			Thread.sleep(1000);
			
			String message = (String) din.readUTF();

			if (message != null) {
				System.out.println(message);
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
