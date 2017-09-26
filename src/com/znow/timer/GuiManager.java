package com.znow.timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
		
		JTextArea hoursArea = new JTextArea(1, 2);
		timeSectionsPane.add(hoursArea);
		
		JTextArea minutesArea = new JTextArea(1, 2);
		timeSectionsPane.add(minutesArea);
		
		JTextArea secondsArea = new JTextArea(1, 2);
		timeSectionsPane.add(secondsArea);
		
		root.add(timeSectionsPane);
		
		JButton setTimerButton = new JButton("Set timer");
		setTimerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int seconds = Integer.parseInt(hoursArea.getText()) * 3600 
						+ Integer.parseInt(minutesArea.getText()) * 60
						+ Integer.parseInt(secondsArea.getText());
				
				timer = new Timer(seconds);
				drawTimerProcessWindow();
			}
		});
		root.add(setTimerButton);
		
		setContentPane(root);
	}
	
	public void drawTimerProcessWindow() {
		
	}
	
}
