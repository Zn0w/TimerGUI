package com.znow.timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GuiManager extends JFrame {
	
	Timer timer = new Timer();
	
	public GuiManager() {
		init();
	}
	
	private void init() {
		setLocationRelativeTo(null);
		setTitle("Timer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	public void drawSetTimerWindow() {
		JPanel root = new JPanel();
		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
		
		JPanel timeSectionsPane = new JPanel();
		
		JTextField hoursArea = new JTextField("0");
		hoursArea.setColumns(2);
		timeSectionsPane.add(hoursArea);
		
		JTextField minutesArea = new JTextField("0");
		minutesArea.setColumns(2);
		timeSectionsPane.add(minutesArea);
		
		JTextField secondsArea = new JTextField("0");
		secondsArea.setColumns(2);
		timeSectionsPane.add(secondsArea);
		
		root.add(timeSectionsPane);
		
		JButton setTimerButton = new JButton("Set timer");
		setTimerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (isTimeFormatLegal(hoursArea.getText(), hoursArea.getText(), secondsArea.getText())) {
					int seconds = toInteger(hoursArea.getText()) * 3600 
						+ toInteger(minutesArea.getText()) * 60
						+ toInteger(secondsArea.getText());
				
					timer.setSecondSettings(seconds);
					drawTimerProcessWindow();
				}
				else {
					JOptionPane.showMessageDialog(GuiManager.this, "Use 0 - 24 for hours, 0 - 59"
							+ " for minutes and seconds", "Illegal time format.", 
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		root.add(setTimerButton);
		
		setContentPane(root);
		
		pack();
	}
	
	public void drawTimerProcessWindow() {
		JPanel root = new JPanel();
		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
		
		JLabel currentTimeLabel = new JLabel(timer.displayTimeLeft());
		root.add(currentTimeLabel);
		
		TimerCounter timerCounter = new TimerCounter(currentTimeLabel);
		
		Thread timeCounterThread = new Thread(timerCounter);
		timeCounterThread.start();
		
		JPanel buttonPane = new JPanel();
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				timer.startTimer();
			}
		});
		buttonPane.add(startButton);
		
		JButton pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				timer.pauseTimer();
			}
		});
		buttonPane.add(pauseButton);
		
		JButton unpauseButton = new JButton("Unpause");
		unpauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				timer.unpauseTimer();
			}
		});
		buttonPane.add(unpauseButton);
		
		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				timer.stopTimer();
			}
		});
		buttonPane.add(stopButton);
		
		root.add(buttonPane);
		
		JButton changeButton = new JButton("Change time settings");
		changeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stopTimer();
				timerCounter.isRunning = false;
				drawSetTimerWindow();
			}
		});
		root.add(changeButton);
		
		setContentPane(root);
		
		pack();
	}
	
	public int toInteger(String strFormat) {
		if (strFormat.equals(""))
			return 0;
		else
			return Integer.parseInt(strFormat);
	}
	
	public boolean isTimeFormatLegal(String hours, String minutes, String seconds) {
		try {
			int h = Integer.parseInt(hours);
			int m = Integer.parseInt(minutes);
			int s = Integer.parseInt(seconds);
			
			return (h >= 0 && h <= 24) && (m >= 0 && m <= 59) && (s >=0 && s <=59);
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private class TimerCounter implements Runnable {

		private JLabel currentTimeLabel;
		private boolean isRunning;
		
		private TimerCounter(JLabel currentTimeLabel) {
			this.currentTimeLabel = currentTimeLabel;
			isRunning = true;
		}
		
		@Override
		public void run() {
			while (isRunning) {
				currentTimeLabel.setText(timer.displayTimeLeft());
			}
		}
		
	}
	
}
