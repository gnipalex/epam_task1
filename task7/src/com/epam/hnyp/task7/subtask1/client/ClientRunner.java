package com.epam.hnyp.task7.subtask1.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
			System.out.print("Command -> ");
			line = sc.nextLine();
			if (!line.isEmpty()) {
				Socket socket = null;
				try {
					socket = new Socket(HOST, PORT);
				} catch (UnknownHostException e) {
					System.out.println("#socket create error : " + e.getMessage());
					return;
				} catch (IOException e) {
					System.out.println("#socket create error : " + e.getMessage());
					return;
				}
				try {
					BufferedWriter writer = new BufferedWriter(
							new OutputStreamWriter(socket.getOutputStream()));
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(socket.getInputStream()));
					writer.write(line);
					writer.newLine();
					writer.flush();
					String response = reader.readLine();
					System.out.println(response);
				} catch (IOException e) {
					System.out.println("#error while i/o via socket");
				} finally {
					if (socket != null && !socket.isClosed()) {
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
