package com.coderising.download.impl;

import static util.Print.println;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

import sun.net.www.protocol.http.HttpURLConnection;

public class ConnectionImpl implements Connection{

	private ConnectionManager cm;
	private static int buffer_size = 1024;
	private HttpURLConnection httpConn;
	private URL url;
	private boolean finished = false;
	
	public ConnectionImpl(ConnectionManager cm, String _url) {
		this.cm = cm;
		try {
			url = new URL(_url);
			httpConn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			in  = httpConn.getInputStream();
			out = new ByteArrayOutputStream();
			in = httpConn.getInputStream();
			in.skip(startPos);
			byte[] buffer = new byte[endPos-startPos + 1];
			int len = 0;
			//byte[] b = new byte[1024];
			while((len = in.read(buffer)) != -1) {
				out.write(buffer);
			}
			System.out.println(out.toByteArray().length);
			return out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public int getContentLength() {
		int len = httpConn.getContentLength();

		return len;
		
	}

	@Override
	public void close() {
		httpConn.disconnect();	
	}

	@Override
	public String getFileName() {
		String fileName = httpConn.getURL().getFile();
		fileName = fileName.substring(fileName.lastIndexOf('/')+1);
		return fileName;
	}

	@Override
	public void setFinished() {
		finished = true;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}


}
