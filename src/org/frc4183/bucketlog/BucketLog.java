package org.frc4183.bucketlog;

public class BucketLog {
	private static BucketLog instance = null;
	private Recorder recorder;
	private Thread thread;
	private boolean paused = false;
	
	public static BucketLog getInstance() {
		if(instance == null) {
			instance = new BucketLog();
		}
		
		return instance;
	}
	
	private BucketLog() {
		recorder = new Recorder("test.csv");
		thread = new Thread(recorder);
		thread.start();
	}
	
	public void log(long time, String name, String value) {
		if(!paused) {
			recorder.publish(time, name, value);
		}
	}
	
	public void pause() {
		paused = true;
	}
	
	public void resume() {
		paused = false;
	}
}
