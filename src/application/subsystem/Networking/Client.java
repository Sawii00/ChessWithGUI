package application.subsystem.Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;

import application.subsystem.Game.PlayerManager;
import application.subsystem.Utils.BasicAlertBox;
import application.subsystem.Utils.Utils;

public class Client implements Runnable {

	public Socket socket;
	Inet4Address ip;
	int port;
	Thread t;
	DataInputStream din;
	DataOutputStream dout;

	public Client(String ip, String port) {
		try {
			this.port = Integer.parseInt(port);
			this.ip = (Inet4Address) Inet4Address.getByName(ip);
			PlayerManager.player2.virtual = true;
			if (t == null) {
				t = new Thread(this, "Server");
				t.start();
			}
		} catch (UnknownHostException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void socketClose() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {

			socket = new Socket(this.ip, this.port);
			System.out.println("Socket creato");
			dout = new DataOutputStream(socket.getOutputStream());
			din = new DataInputStream(socket.getInputStream());

			while (true) {

				String response = din.readUTF();
				System.out.println(response);

			}

		} catch (IOException e) {
		}

	}
	
	

	
	public void pieceMoved(String message) {

		System.out.println(message);

		try {
			dout.writeUTF(message);
			dout.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
