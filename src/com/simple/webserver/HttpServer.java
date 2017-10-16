package com.simple.webserver;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
	 public static final String WEB_ROOT = System.getProperty("user.dir")+File.separator + "webroot";
	  private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	  private boolean shutdown = false;
	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		server.awiat();
	}
 
  public void awiat(){
	  try {
		ServerSocket serverSocket = new ServerSocket(8080,1,InetAddress.getByName("127.0.0.1"));
		while(!shutdown){
			Socket socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			Request request = new Request(inputStream);
			request.parse();
			Response response = new Response(outputStream);
			response.setRequest(request);
			response.sendStaticResource();
			socket.close();
			shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
}
