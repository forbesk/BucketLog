package org.frc4183.bucketlog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CSVRecorder extends Recorder {
	private String filename;
	private PrintWriter pw;
	private ConcurrentLinkedQueue<String> queue;
	
	public CSVRecorder(String filename) {
		this.filename = filename;
		queue = new ConcurrentLinkedQueue<String>();
	}
	
	public void setup() {
		try {
			pw = new PrintWriter(new File(filename));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return;
		}
	}
	
	public void execute() {
		while(!queue.isEmpty()) {
			pw.println(queue.remove());
		}
		
		pw.flush();
		
		try {
			Thread.sleep(0, 100000);
		} catch (InterruptedException e) {
			// :{O Oh no!
		}
	}
	
	public void cleanup() {
		pw.close();
	}
	
	public void publish(long time, String name, String value) {
		queue.add(String.format("%d, %s, %s", time, name, value));
	}

}
