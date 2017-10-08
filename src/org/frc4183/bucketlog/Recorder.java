package org.frc4183.bucketlog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Recorder implements Runnable {
	private String filename;
	private PrintWriter pw;
	private boolean stopped = true;
	private ConcurrentLinkedQueue<String> queue;
	
	public Recorder(String filename) {
		this.filename = filename;
		queue = new ConcurrentLinkedQueue<String>();
	}
	
	@Override
	public void run() {
		try {
			pw = new PrintWriter(new File(filename));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return;
		}
		
		stopped = false;
		
		while(!stopped) {
			while(true) {
				try {
					pw.println(queue.remove());
				} catch (NoSuchElementException e) {
					break;
				}
			}
			
			pw.flush();
			
			try {
				Thread.sleep(0, 100000);
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	public void stop() {
		stopped = true;
	}

	public void publish(long time, String name, String value) {
		queue.add(String.format("%d, %s, %s", time, name, value));
	}

}
