package application.subsystem.Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import application.Controller;
import application.subsystem.Utils.Utils;
import javafx.application.Platform;

public class Server implements Runnable {

	int port;
	ServerSocket server;
	Socket socket;
	Thread t;
	DataInputStream din;
	DataOutputStream dout;

	public Server(int port) {
		this.port = port;
		Controller.pm.player1.virtual = true;
		Controller.pm.player2.virtual = false;
		Controller.pm.player1.myTurn = false;
		Controller.pm.player2.myTurn = true;
		if (t == null) {
			t = new Thread(this, "Server");
			t.start();
		}

	}

	public void socketClose() {

		try {
			server.close();
			din.close();
			dout.close();
			t.interrupt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		System.out.println("Server initialized");
		try {
			server = new ServerSocket(port);
			socket = server.accept();
			din = new DataInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());
			//
			while (true) {

				String response = din.readUTF();
				if (Utils.decodeString(response)) {
					Platform.runLater( ()-> {
			            	Controller.syncArrayGrid();
			        });
					
					
				}
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
			e.printStackTrace();
		}

	}

}
