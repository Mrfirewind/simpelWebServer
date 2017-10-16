package com.simple.webserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

public class Response {
	private static final int  BUFFER_SIZE= 1024;
	Request request;
	OutputStream outputStream;

	public Response(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public void setRequest(Request request) {
		this.request = request;
	}
	
	public void sendStaticResource() throws Exception{
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fileInputStream = null;
		File file = new File(HttpServer.WEB_ROOT,request.getUri());
		if(file != null && file.exists()){
			fileInputStream = new FileInputStream(file);
			int ch = fileInputStream.read(bytes, 0, BUFFER_SIZE);
			while(ch != -1){
				outputStream.write(bytes, 0, ch);
				ch = fileInputStream.read(bytes, 0, BUFFER_SIZE);
			}
		}else{
			String errorMessage = "HTTP/1.1 404 FILE NOT FOUND \r\n"+
		   "Content-Type: text/html\r\n"+
		   "Content-Length: 23\r\n"+"\r\n"+"<h1>File Not Found</h1>";
			outputStream.write(errorMessage.getBytes());
		}
		if(fileInputStream !=null){
			fileInputStream.close();
			fileInputStream = null;
		}
		
	}

}
