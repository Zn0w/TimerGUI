package com.znow.timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GuiManager extends JFrame {
	
	private Timer timer;
	
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
						+ toInteger(hoursArea.getText()) * 60
						+ toInteger(secondsArea.getText());
				
					timer = new Timer(seconds);
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
	
}
