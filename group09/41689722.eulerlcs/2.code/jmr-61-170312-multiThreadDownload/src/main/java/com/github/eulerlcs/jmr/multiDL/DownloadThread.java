package com.github.eulerlcs.jmr.multiDL;

import com.github.eulerlcs.jmr.multiDL.api.Connection;

public class DownloadThread extends Thread {
	Connection conn;
	int startPos;
	int endPos;

	public DownloadThread(Connection conn, int startPos, int endPos) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
	}

	@Override
	public void run() {

	}
}
