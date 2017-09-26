package com.znow.timer;

public class Timer implements Runnable {
	
	private int secondsLeft;
	
	private boolean isRunning, isPaused;
	
	public Timer(int secondsLeft) {
		this.secondsLeft = secondsLeft;
	}
	
	public void startTimer() {
		// Create new thread and start the timer
	}
	
	public void pauseTimer() {
		// Freeze timer counting
	}
	
	public void unpauseTimer() {
		// Unfreeze timer counting
	}
	
	public void stopTimer() {
		// Terminate the thread
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
		}
	}
	
}
