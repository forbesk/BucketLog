package org.frc4183.bucketlog;

import java.util.ArrayList;
import java.util.List;

public class BucketLog {
	private static BucketLog instance = null;
	private ArrayList<Recorder> recorders = new ArrayList<Recorder>();
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	private boolean paused = false;
	
	public static BucketLog getInstance() {
		if(instance == null) {
			instance = new BucketLog();
		}
		
		return instance;
	}
	
	private BucketLog() {
		
	}
	
	public void addRecorder(Recorder recorder) {
		Thread t = new Thread(recorder);
		recorders.add(recorder);
		threads.add(t);
	}
	
	public void log(long time, String name, String value) {
		if(!paused) {
			for(Recorder r : recorders) {
				r.publish(time, name, value);
			}
		}
	}
	
	public void pause() {
		paused = true;
	}
	
	public void resume() {
		paused = false;
	}
	
	public void stop() {
		for(Recorder r : recorders) {
			r.stop();
		}
	}
}
