package com.epam.hnyp.task7.subtask1.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientRunner {

	public static final int PORT = 3000;
	public static final String HOST = "127.0.0.1";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = null;
		do {
			Socket socket = null;
			try {
				socket = new Socket(HOST, PORT);
			} catch (UnknownHostException e) {
				System.out.println("#error host : " + e.getMessage());
				return;
			} catch (IOException e) {
				System.out.println("#error io : " + e.getMessage());
				return;
			}
			System.out.print("Command -> ");
			line = sc.nextLine();
			if (!line.isEmpty()) {
				try {
//					BufferedWriter writer = new BufferedWriter(
//							new OutputStreamWriter(socket.getOutputStream()));
//					BufferedReader reader = new BufferedReader(
//							new InputStreamReader(socket.getInputStream()));
					DataOutputStream output = new DataOutputStream(socket.getOutputStream());
					DataInputStream input = new DataInputStream(socket.getInputStream());
//					writer.write(line);
//					writer.flush();
//					String response = reader.readLine();
//					reader.
//					System.out.println(response);
					
				} catch (IOException e) {
					System.out.println("#error while i/o via socket");
				} finally {
					if (socket != null) {
						try {
							socket.close();
						} catch (IOException e) {
						}
					}
				}
			}
		} while (!line.isEmpty());
	}
}
