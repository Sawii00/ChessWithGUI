package application.subsystem.Networking;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import application.subsystem.Utils.BasicAlertBox;

public class Server implements Runnable {

	int port;
	ServerSocket server;
	Socket socket;
	Thread t;

	public Server(int port) {
		this.port = port;
		if (t == null) {
			t = new Thread(this, "Server");
			t.start();
		}

	}

	public void socketClose() {

		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		System.out.println("Pippo");
		try {
			server = new ServerSocket(port);
			socket = server.accept();
			DataInputStream din = new DataInputStream(socket.getInputStream());
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			String message = (String) din.readUTF();
			String to_send = "Server chiama Client";
			Thread.sleep(1000);

			if (message != null) {
				System.out.println(message);
				dout.writeUTF(to_send);
				dout.flush();
				//dout.close();
			}

		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
