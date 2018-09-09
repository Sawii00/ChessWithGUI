package application;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class Utils {

	public static Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
		for (Node node : gridPane.getChildren()) {
			try {
				if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
					return node;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		return null;
	}

	public static boolean validIP(String ip) {
		try {
			if (ip == null || ip.isEmpty()) {
				return false;
			}

			String[] parts = ip.split("\\.");
			if (parts.length != 4) {
				return false;
			}

			for (String s : parts) {
				int i = Integer.parseInt(s);
				if ((i < 0) || (i > 255)) {
					return false;
				}
			}
			if (ip.endsWith(".")) {
				return false;
			}

			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static boolean validPORT(String port) {

		try {
			int p = Integer.parseInt(port);
			if (p < 65535 && p > 1000) {
				return true;
			} else {
				return false;
			}

		} catch (NumberFormatException e) {
			return false;
		}

	}

	public static byte[] stringToByteArray(String ip) {
		String[] string_array = ip.split("\\.");
		byte[] byte_array = new byte[string_array.length];

		for (int i = 0; i < string_array.length; i++) {
			byte_array[i] = Byte.valueOf(string_array[i]);
		}
		return byte_array;

	}

}
