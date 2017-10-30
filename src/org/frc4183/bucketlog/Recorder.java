package org.frc4183.bucketlog;

public abstract class Recorder implements Runnable {
	private boolean stopped = false;
	
	public void run() {
		setup();
		
		stopped = false;
		
		while(!stopped) {
			execute();
		}
		
		cleanup();
	}
	
	public void stop() {
		stopped = true;
	}
	
	public abstract void setup();
	
	public abstract void execute();
	
	public abstract void cleanup();
	
	public abstract void publish(long time, String name, String value);
}
