package com.learning.helloworld.controller;

import java.io.*;
import java.net.Socket;

/**
 * @Package: com.learning.helloworld.controller
 * @Description: ServerThread
 * @Author: Sammy
 * @Date: 2022/6/25 12:37
 */

public class ServerThread extends Thread {
	Socket socket = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		InputStream is=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		OutputStream os=null;
		PrintWriter pw=null;

		try {
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String info = null;
			while ((info = br.readLine()) != null) {
				System.out.println("我是服务端，客户端说："+info);
			}
			socket.shutdownInput();
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.write("welcome~!");
			pw.flush();
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pw!=null)
					pw.close();
				if(os!=null)
					os.close();
				if(br!=null)
					br.close();
				if(isr!=null)
					isr.close();
				if(is!=null)
					is.close();
				if(socket!=null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
