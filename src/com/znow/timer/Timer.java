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
		while (isRunning) {
			if (isPaused)
				continue;
			
			try {
				Thread.sleep(998);
				secondsLeft--;
				System.out.println("Seconds left: " + secondsLeft);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
