package com.znow.timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
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
		
		JTextField hoursArea = new JTextField();
		hoursArea.setColumns(2);
		timeSectionsPane.add(hoursArea);
		
		JTextField minutesArea = new JTextField();
		minutesArea.setColumns(2);
		timeSectionsPane.add(minutesArea);
		
		JTextField secondsArea = new JTextField();
		secondsArea.setColumns(2);
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
		
		pack();
	}
	
	public void drawTimerProcessWindow() {
		
	}
	
}
