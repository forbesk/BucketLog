package test;

import org.frc4183.bucketlog.BucketLog;
import org.frc4183.bucketlog.CSVRecorder;
import org.frc4183.bucketlog.Recordable;

import junit.framework.TestCase;

public class RecordableTest extends TestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
		BucketLog bl = BucketLog.getInstance();
		bl.addRecorder(new CSVRecorder("test.csv"));
		bl.log(System.currentTimeMillis(), "Test", "Some data");
	}
	
	public void testSomething() throws InterruptedException {
		Recordable<Integer> n = new Recordable<>("n");
		Recordable<Boolean> b = new Recordable<>("b");
		Recordable<Double> d = new Recordable<>("d");
		
		for(int i = 0; i < 100; i++) {
			n.update(i);
			b.update(i % 2 == 0);
			d.update(i/Math.PI);
			Thread.sleep(10);
		}
	}

}
