package com.znow.timer;

public class Timer implements Runnable {
	
	private int secondsLeft = 0;
	
	private boolean isRunning = false;
	private boolean isPaused = false;
	
	public void startTimer() {
		if (isRunning) {
			return;
		}
		else {
			isRunning = true;
			
			Thread timerThread = new Thread(this);
			timerThread.start();
		}
	}
	
	public void pauseTimer() {
		isPaused = true;
	}
	
	public void unpauseTimer() {
		isPaused = true;
	}
	
	public void stopTimer() {
		isRunning = false;
	}
	
	public String displayTimeLeft() {
		// Return string of time left in format: hh:mm:ss
		return null;
	}
	
	public void setSecondsLeft(int secondsLeft) {
		this.secondsLeft = secondsLeft;
	}

	@Override
	public void run() {
		long previousTime = System.currentTimeMillis();
		long currentTime = 0;
		
		while (isRunning) {
			if (isPaused)
				continue;
			
			if (((currentTime = System.currentTimeMillis()) - previousTime) >= 1000) {
				secondsLeft--;
				previousTime = currentTime;
				System.out.println("Seconds left: " + secondsLeft);
			}
		}
	}
	
}
