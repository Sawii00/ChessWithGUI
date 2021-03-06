package application.subsystem.Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import application.Controller;
import application.subsystem.Game.Board;
import application.subsystem.Utils.Utils;
import javafx.application.Platform;

public class Server implements Runnable {

	int port;
	ServerSocket server;
	Socket socket;
	Thread t;
	DataInputStream din;
	DataOutputStream dout;
	boolean hasClientConnected = false;

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
			if (server != null && din != null && dout != null) {
				server.close();
				din.close();
				dout.close();
			}

			t.interrupt();
			if (Controller.isConnected) {
				Controller.isConnected = false;
			}
			
			if (Controller.isHosting) {
				Controller.isHosting = false;
			}
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

			while (true) {

				System.out.println("While restart");

				if (hasClientConnected == false) {
					socket = server.accept();
					hasClientConnected = true;
					// initialize method (sync and modify turns)
					initialize();
				}
				String response = din.readUTF();

				if (Utils.decodeString(response, false)) {
					Platform.runLater(() -> {
						Controller.syncArrayGrid();
					});

				}
				if (response.equals("Closing")) {
					hasClientConnected = false;

				}
				System.out.println(response);

			}

		} catch (IOException e) {
		}

	}

	private void initialize() {
		System.out.println("Initializing");
		try {
			din = new DataInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());

			dout.writeUTF(Board.boardToString());

			//Thread.sleep(500);

			// send the turn

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
