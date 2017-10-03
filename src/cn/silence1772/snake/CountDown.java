package cn.silence1772.snake;

public class CountDown implements Runnable{
	
	private Thread t;
	private int cnt;
	
	public CountDown(int times) {
		
		cnt = times;
	}
	
	@Override
	public void run() {
		
		try {
			while (cnt > 0) {
				cnt--;
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	
	public void start() {
		if (t == null) {
	         t = new Thread (this);
	         t.start ();
	      }
	}
	
	public int getCnt() {
		return cnt;
	}
}
