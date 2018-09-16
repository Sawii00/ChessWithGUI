package application.subsystem.Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;

import application.Controller;
import application.subsystem.Utils.Utils;
import javafx.application.Platform;

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
			Controller.pm.player2.virtual = true;

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
			dout.writeUTF("Closing");
			dout.flush();
			socket.close();
			din.close();
			dout.close();
			t.interrupt();
			if(Controller.isConnected) {Controller.isConnected=false;}
			if(Controller.isHosting) {Controller.isHosting=false;}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {

			setupConnection();

			while (true) {
				String response = din.readUTF();
				if (Utils.decodeString(response)) {
					Platform.runLater(() -> {
						Controller.syncArrayGrid();
					});
				}else if(response.equals("Client")) {
					Controller.pm.player1.myTurn = true;
				}else if (response.equals("Server")) {
					Controller.pm.player1.myTurn = false;				
				}
				
				System.out.println(response);
			}

		} catch (IOException e) {
		}

	}

	private void setupConnection() {

		try {
			socket = new Socket(this.ip, this.port);
			System.out.println("Socket creato");
			System.out.println("Client connected");
			dout = new DataOutputStream(socket.getOutputStream());
			din = new DataInputStream(socket.getInputStream());
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
