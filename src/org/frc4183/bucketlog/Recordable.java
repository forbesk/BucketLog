package org.frc4183.bucketlog;

public class Recordable<E> {
	private String name;
	
	public Recordable(String name) {
		this.name = name;
	}
	
	public void update(E var) {
		update(System.currentTimeMillis(), var);
	}
	
	public void update(long time, E var) {
		BucketLog.getInstance().log(time, name, var.toString());
	}
}
