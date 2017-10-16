package com.simple.webserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 8080);
			OutputStream os = socket.getOutputStream();
			boolean autoFlush = true;
			PrintWriter printWriter = new PrintWriter(os, autoFlush);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			printWriter.println("GET /index.jsp HTTP1.1");
			printWriter.println("HOST: localhost:8080");
			printWriter.println("CONNECTION: Close");
			printWriter.println();

			boolean loop = true;
			StringBuffer sb = new StringBuffer(8096);
			while (loop) {
				if (br.ready()) {
					int i = 0;
					while (i != -1) {
						i = br.read();
						sb.append((char) i);
					}
					loop = false;
				}
				Thread.currentThread().sleep(50);
			}
			System.out.println(sb.toString());
			socket.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
