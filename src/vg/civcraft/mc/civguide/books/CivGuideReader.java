package vg.civcraft.mc.civguide.books;

import java.util.concurrent.TimeUnit;

public class CivGuideReader {
	private long readtime;
	private long owntime;
	private boolean reading;
	
	public CivGuideReader(){
		reading = false;
		readtime = 0;
		owntime = System.nanoTime();
	}
	
	public boolean isReading(){
		return reading;
	}
	
	public void setReading(boolean read){
		reading = read;
		if (read== true){
			if (readtime == 0)
				readtime = System.nanoTime();
		} else {
			readtime = 0;
		}
	}
	
	public long getOwnTime(){
		return TimeUnit.SECONDS.convert(System.nanoTime() - owntime, TimeUnit.NANOSECONDS);
	}
	
	public long getReadTimeSecs(){
		if (isReading()){
			return TimeUnit.SECONDS.convert(System.nanoTime() - readtime, TimeUnit.NANOSECONDS);
		} else {
			return 0;
		}
	}
	
}
