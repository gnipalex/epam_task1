package com.epam.hnyp.task7.subtask1.server;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.epam.hnyp.task7.subtask1.facade.ProductsFacade;
import com.epam.hnyp.task7.subtask1.factory.RequestHandlerFactory;
import com.epam.hnyp.task7.subtask1.factory.SimpleRequestHandlerFactory;

public class ServerTest {

	private static final int prodCount = 2;
	private static final ProductsFacade.ProductInfo prodInfo = new ProductsFacade.ProductInfo(
			"AAA", 111);
	private static final String host = "127.0.0.1";
	private static final int port = 3333;
	
	private static final String countRequest = "get count";
	private static final String itemRequest = "get item = 1";

	@BeforeClass 
	public static void initServer() {
		ProductsFacade mockFacade = Mockito.mock(ProductsFacade.class);
		Mockito.when(mockFacade.getCount()).thenReturn(prodCount);
		Mockito.when(mockFacade.getProductInfo(Mockito.anyLong())).thenReturn(
				prodInfo);

		RequestHandlerFactory reqFactory = new SimpleRequestHandlerFactory(
				mockFacade);
		Thread serverThread = new Thread(new Server(port, reqFactory));
		serverThread.setDaemon(true);
		serverThread.start();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			fail("Test was interrupted");
		}
	}
	
	@Test
	public void testGetCount() {
		try {
			assertEquals("get count failed","" + prodCount, sendToServer(countRequest));
		} catch (IOException e) {
			fail("error while i/o via socket");
		}
	}
	
	@Test
	public void testGetItem() {
		try {
			String expectedResult = prodInfo.getName() + "|" + prodInfo.getPrice();
			assertEquals("get item failed", expectedResult, sendToServer(itemRequest));
		} catch (IOException e) {
			fail("error while i/o via socket");
		}
	}

	private String sendToServer(String message) throws IOException {
		Socket clientSocket = null;
		String response = null;

		clientSocket = new Socket(host, port);

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					clientSocket.getOutputStream()));

			bw.write(message);
			bw.newLine();
			bw.flush();

			response = br.readLine();
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
			}
		}
		return response;
	}

}
