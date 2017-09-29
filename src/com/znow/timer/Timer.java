package com.znow.timer;

public class Timer implements Runnable {
	
	private int secondSettings = 0;
	private int secondsLeft = 0;
	
	private boolean isRunning = false;
	private boolean isPaused = false;
	
	public void startTimer() {
		if (isRunning) {
			return;
		}
		else {
			isRunning = true;
			
			secondsLeft = secondSettings;
			
			Thread timerThread = new Thread(this);
			timerThread.start();
		}
	}
	
	public void pauseTimer() {
		isPaused = true;
	}
	
	public void unpauseTimer() {
		if (isRunning)
			isPaused = false;
		else return;
	}
	
	public void stopTimer() {
		isRunning = false;
		isPaused = false;
	}
	
	public String displayTimeLeft() {
		return String.valueOf(secondsLeft);
	}
	
	public void setSecondSettings(int secondSettings) {
		this.secondSettings = secondSettings;
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
				System.out.println(secondsLeft);
			}
		}
	}
	
}
