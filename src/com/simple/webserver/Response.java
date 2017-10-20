package com.simple.webserver;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

public class Response implements ServletResponse{
	private static final int  BUFFER_SIZE= 1024;
	Request request;
	OutputStream outputStream;
	PrintWriter printWriter;

	public Response(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public void setRequest(Request request) {
		this.request = request;
	}
	//静态资源
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
			getWriter().println(errorMessage);
		}
		if(fileInputStream !=null){
			fileInputStream.close();
			fileInputStream = null;
		}
		
	}

	@Override
	public String getCharacterEncoding() {
		return null;
	}

	@Override
	public String getContentType() {
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		//println的任何调用都会刷新输出，但print方法调用不会
		printWriter = new PrintWriter(outputStream,true);
		return printWriter;
	}

	@Override
	public void setCharacterEncoding(String s) {

	}

	@Override
	public void setContentLength(int i) {

	}

	@Override
	public void setContentLengthLong(long l) {

	}

	@Override
	public void setContentType(String s) {

	}

	@Override
	public void setBufferSize(int i) {

	}

	@Override
	public int getBufferSize() {
		return 0;
	}

	@Override
	public void flushBuffer() throws IOException {

	}

	@Override
	public void resetBuffer() {

	}

	@Override
	public boolean isCommitted() {
		return false;
	}

	@Override
	public void reset() {

	}

	@Override
	public void setLocale(Locale locale) {

	}

	@Override
	public Locale getLocale() {
		return null;
	}
}
