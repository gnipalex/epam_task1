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

	public static final String COMMAND_COUNT = "get count";
	public static final String COMMAND_FIND = "get item = ";

	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		String command = null;
		do {
			command = prepareCommand();
			if (command == null || command.isEmpty()) {
				continue;
			}
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
				writer.write(command);
				writer.newLine();
				writer.flush();
				String response = reader.readLine();
				System.out.println("response --> " + response);
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
		} while (command != null);
	}

	/**
	 * 
	 * @return null - if was quit command, empty string - if need to retry input
	 */
	private static String prepareCommand() {
		System.out.println("Choose command : ");
		System.out.println("1 - get count");
		System.out.println("2 - get item = ?");
		System.out.println("q - quit");
		System.out.print("Command -> ");
		String line = sc.nextLine();
		if (!line.isEmpty()) {
			switch (line.charAt(0)) {
			case '1':
				return COMMAND_COUNT;
			case '2':
				System.out.print("Enter id of item -> ");
				line = sc.nextLine();
				long id = 0;
				try {
					id = Long.parseLong(line);
					return COMMAND_FIND + id;
				} catch (NumberFormatException e) {
					System.out.println("#number illegal format#");
				}
				break;
			case 'q':
				return null;
			default:
				System.out.println("#command not supported#");
			}
		}
		return line;
	}
}
