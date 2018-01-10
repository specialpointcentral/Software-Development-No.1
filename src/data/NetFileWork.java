package data;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JOptionPane;

import server.DataPack;

public class NetFileWork {
	/**
	 * 
	 * @param name
	 *            file name
	 * @param type
	 *            1-server to client 2-client to server
	 */
	public NetFileWork(String name, int type) throws Exception {
		int port = 9999;
		InetAddress address=InetAddress.getLocalHost();
		Socket socket = new Socket(address, port);
		ObjectOutputStream obout = new ObjectOutputStream(socket.getOutputStream());
		// first write info
		DataPack pack = new DataPack();
		pack.fileName = name;
		pack.type = type;
		obout.writeObject(pack);
		obout.flush();

		// then read or write
		if (type == 1) {
			// server to client
			// maybe server has not this file
			InputStream netIn = socket.getInputStream();
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(netIn));

			if (!in.readBoolean()) {
				System.err.println("Server has not the file");
			} else {
				File file = new File(name);
				if (!file.exists())// file not exit create it
					file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(fos));
				out.writeObject(in.readObject());
				out.flush();

				// close all stream
				out.close();
				fos.close();
			}
			// close all stream
			in.close();
			netIn.close();
		} else {
			// client to server
			OutputStream netOut = socket.getOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(netOut));

			FileInputStream fos = new FileInputStream(name);
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(fos));

			out.writeObject(in.readObject());
			out.flush();
			
			//close all stream
			fos.close();
			in.close();
			out.close();
			netOut.close();
		}
		//close all stream
		obout.close();
		socket.close();
	}

}
