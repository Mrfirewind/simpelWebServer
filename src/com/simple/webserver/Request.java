package com.simple.webserver;

import java.io.InputStream;

public class Request {
	private InputStream inputStream;
	private String uri;

	public Request(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void parse() {
		try {
			StringBuffer request = new StringBuffer(2048);
			int i;
			byte[] buffer = new byte[2048];
			i = inputStream.read(buffer);

			for (int j = 0; j < i; j++) {
				request.append((char) buffer[j]);
			}
			System.out.println(request.toString());
			uri = parseUri(request.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String parseUri(String requestString) {
		int index1, index2;
		index1 = requestString.indexOf(" ");
		if (index1 != -1) {
			index2 = requestString.indexOf(" ", index1 + 1);
			if (index2 > index1) {
				return requestString.substring(index1 + 1, index2);
			}
		}

		return null;
	}

	public String getUri() {
		return uri;
	}
}
