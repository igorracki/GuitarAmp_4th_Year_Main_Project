package com.igor.guitarproject.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.igor.guitarproject.controller.GUIController;

import javax.swing.JSlider;

public class MainGUI extends JFrame implements ActionListener, ChangeListener {
	
	private static final long serialVersionUID = 9137898017288735704L;
	
	/******************************* 	Icons. 	************************************/
	private ImageIcon on_led = new ImageIcon(this.getClass().getResource("/ON.png"));
	private ImageIcon off_led = new ImageIcon(this.getClass().getResource("/OFF.png"));
	private ImageIcon off_inactive_led = new ImageIcon(this.getClass().getResource("/OFF_inactive.png"));
	private ImageIcon on_inactive_led = new ImageIcon(this.getClass().getResource("/ON_inactive.png"));
	private ImageIcon background = new ImageIcon(this.getClass().getResource("/background.png"));
	private ImageIcon background2 = new ImageIcon(this.getClass().getResource("/background2.png"));
	private ImageIcon bg_panel = new ImageIcon(this.getClass().getResource("/panel_bg.png"));
	private ImageIcon bg_panel2 = new ImageIcon(this.getClass().getResource("/panel_bg2.png"));
	private ImageIcon bg_panel3 = new ImageIcon(this.getClass().getResource("/panel_bg3.png"));
	private ImageIcon button_on = new ImageIcon(this.getClass().getResource("/button_on.png"));
	private ImageIcon button_off = new ImageIcon(this.getClass().getResource("/button_off.png"));
	private ImageIcon button_off2 = new ImageIcon(this.getClass().getResource("/switch2_off.png"));
	private ImageIcon button_on2 = new ImageIcon(this.getClass().getResource("/switch2_on.png"));
	private ImageIcon overdrive_icon = new ImageIcon(this.getClass().getResource("/overdrive.png"));
	private ImageIcon delay_icon = new ImageIcon(this.getClass().getResource("/delay.png"));

	/******************************* 	Panels. 	************************************/
	private JPanel contentPane;
	private JPanel main_panel = new JPanel();
	private JPanel filter_panel = new JPanel();
	private JPanel effects_panel = new JPanel();
	
	/******************************* 	Labels. 	************************************/
	private JLabel background_label = new JLabel();
	private JLabel top_panel_label = new JLabel("");
	private JLabel title_label = new JLabel("Java Guitar Amp");
	private JLabel filter_box_label = new JLabel("New label");
	private JLabel controls_text_label = new JLabel("CONTROLS");
	private JLabel controls_box_label = new JLabel("");
	private JLabel status_text_label = new JLabel("STATUS");
	private JLabel status_box_label = new JLabel("");
	private JLabel off_led_label = new JLabel("");
	private JLabel on_led_label = new JLabel("");
	private JLabel filter_background_label = new JLabel("");
	private JLabel filter_text_label = new JLabel("FILTER SOUND");
	private JLabel cutoff_text_label = new JLabel("CUTOFF:");
	private JLabel cutoff_value_label = new JLabel("");
	private JLabel frequency_text_label = new JLabel("FREQ:");
	private JLabel freq_value_label = new JLabel("");
	private JLabel effect_background_label = new JLabel("");
	private JLabel effect_text_label = new JLabel("");
	private JLabel drive_delayF_text_label = new JLabel("");
	private JLabel drive_delayF_value_label = new JLabel("");
	private JLabel delayL_text_label = new JLabel("");
	private JLabel delayL_value_label = new JLabel("");
	private JLabel footer_text_label = new JLabel("Igor Racki - B00068103 @ Institute of Technology Blanchardstown");
	private JLabel footer_backgroud_label = new JLabel("");
	
	/******************************* 	Buttons. 	************************************/
	private JButton switch_button = new JButton("");
	private JButton filter_switch = new JButton("");
	private JButton overdrive_switch = new JButton("");
	private JButton delay_switch = new JButton("");
	
	/******************************* 	Sliders. 	************************************/
	private JSlider cutoff_slider = new JSlider();
	private JSlider drive_delayF_slider = new JSlider();
	private JSlider freq_slider = new JSlider();
	private JSlider delayL_slider = new JSlider();
	
	/******************************* 	Frame Settings. 	************************************/
	private Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
	private int frame_location_width;
	private int frame_location_height;
	private int frame_width = 588;
	private int frame_height = 835;
	
	/******************************* 	Variables. 	************************************/
	private GUIController gui_c;
	
	private boolean isAmpOn = false;
	private boolean isFilterOn = false;
	private String current_effect = "none";
	private boolean isEffectOn = false;
	
	private double cutoff_frequency;
	private double frequency;
	private double drive;
	private int delay_length;
	private double delay_feedback;

	public MainGUI(GUIController gui_c) {
		this.gui_c = gui_c;
		/******************************* 	Top. 	************************************/
		background_label.setIcon(bg_panel3);
		background_label.setForeground(Color.WHITE);
		background_label.setBounds(0, 70, 582, 678);
		
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setFont(new Font("Ravie", Font.BOLD, 34));
		title_label.setForeground(new Color(255, 204, 0));
		title_label.setBounds(71, 13, 440, 45);
		
		top_panel_label.setIcon(bg_panel);
		top_panel_label.setForeground(Color.WHITE);
		top_panel_label.setBounds(0, 0, 582, 70);
		
		/******************************* 	Controls. 	************************************/
		controls_box_label.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
		controls_box_label.setIcon(bg_panel2);
		controls_box_label.setBounds(3, 70, 415, 96);
		
		controls_text_label.setForeground(Color.ORANGE);
		controls_text_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		controls_text_label.setHorizontalAlignment(SwingConstants.CENTER);
		controls_text_label.setBounds(169, 70, 124, 16);
		
		switch_button.setToolTipText("On / Off");
		switch_button.setMnemonic('o');
		switch_button.setIcon(button_off);
		switch_button.addActionListener(this);
		switch_button.setBounds(12, 83, 73, 75);
		
		/******************************* 	Status. 	************************************/
		status_box_label.setBackground(Color.BLACK);
		status_box_label.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
		status_box_label.setIcon(bg_panel2);
		status_box_label.setBounds(425, 70, 155, 96);
		
		status_text_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		status_text_label.setHorizontalAlignment(SwingConstants.CENTER);
		status_text_label.setForeground(Color.ORANGE);
		status_text_label.setBounds(438, 70, 124, 16);
		
		on_led_label.setIcon(on_inactive_led);
		on_led_label.setBounds(438, 99, 56, 56);
		
		off_led_label.setIcon(off_led);
		off_led_label.setBounds(514, 99, 56, 56);
		
		/******************************* 	Filter. 	************************************/
		filter_box_label.setBounds(13, 181, 260, 360);
		
		filter_background_label.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE));
		filter_background_label.setIcon(background2);
		filter_background_label.setBounds(0, 0, 555, 100);
		
		filter_text_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		filter_text_label.setForeground(Color.ORANGE);
		filter_text_label.setHorizontalAlignment(SwingConstants.CENTER);
		filter_text_label.setBounds(205, 0, 132, 16);
		
		cutoff_text_label.setForeground(Color.ORANGE);
		cutoff_text_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		cutoff_text_label.setHorizontalAlignment(SwingConstants.CENTER);
		cutoff_text_label.setBounds(12, 33, 69, 16);
		
		cutoff_slider.setForeground(Color.ORANGE);
		cutoff_slider.setBackground(new Color(192, 192, 192));
		cutoff_slider.addChangeListener(this);
		cutoff_slider.setMinimum(10);
		cutoff_slider.setMaximum(10000);
		cutoff_slider.setValue(500);
		cutoff_slider.setBounds(93, 29, 290, 25);
		
		cutoff_value_label.setHorizontalAlignment(SwingConstants.CENTER);
		cutoff_value_label.setForeground(Color.ORANGE);
		cutoff_value_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		cutoff_value_label.setBounds(395, 29, 49, 25);
		
		frequency_text_label.setHorizontalAlignment(SwingConstants.CENTER);
		frequency_text_label.setForeground(Color.ORANGE);
		frequency_text_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		frequency_text_label.setBounds(12, 71, 69, 16);
		
		freq_slider.setBackground(new Color(192, 192, 192));
		freq_slider.setMaximum(10000);
		freq_slider.setValue(5000);
		freq_slider.setBounds(93, 66, 290, 26);
		freq_slider.addChangeListener(this);
		
		freq_value_label.setHorizontalAlignment(SwingConstants.CENTER);
		freq_value_label.setForeground(Color.ORANGE);
		freq_value_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		freq_value_label.setBounds(395, 67, 49, 25);
		
		filter_switch.setBounds(456, 12, 85, 80);
		filter_switch.setIcon(button_off2);
		filter_switch.addActionListener(this);
		
		filter_panel.setBounds(12, 176, 555, 100);
		filter_panel.setLayout(null);
		filter_panel.add(freq_slider);
		filter_panel.add(filter_switch);
		filter_panel.add(freq_value_label);
		filter_panel.add(frequency_text_label);
		filter_panel.add(cutoff_value_label);
		filter_panel.add(cutoff_slider);
		filter_panel.add(cutoff_text_label);
		filter_panel.add(filter_text_label);
		filter_panel.add(filter_background_label);
		
		cutoff_slider.setEnabled(false);
		freq_slider.setEnabled(false);
		cutoff_value_label.setText("");
		freq_value_label.setText("");

		/******************************* 	Effects. 	************************************/
		effect_background_label.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE));
		effect_background_label.setBounds(0, 0, 555, 100);
		effect_background_label.setIcon(background);
		
		effect_text_label.setHorizontalAlignment(SwingConstants.CENTER);
		effect_text_label.setForeground(Color.ORANGE);
		effect_text_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		effect_text_label.setBounds(156, 0, 215, 16);
		
		drive_delayF_text_label.setHorizontalAlignment(SwingConstants.CENTER);
		drive_delayF_text_label.setForeground(Color.ORANGE);
		drive_delayF_text_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		drive_delayF_text_label.setBounds(12, 30, 69, 16);
		
		drive_delayF_slider.setBackground(new Color(192, 192, 192));
		drive_delayF_slider.setValue(8);
		drive_delayF_slider.setMaximum(10);
		drive_delayF_slider.setBounds(93, 26, 290, 26);
		drive_delayF_slider.addChangeListener(this);
		
		drive_delayF_value_label.setHorizontalAlignment(SwingConstants.CENTER);
		drive_delayF_value_label.setForeground(Color.ORANGE);
		drive_delayF_value_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		drive_delayF_value_label.setBounds(394, 27, 49, 25);
		
		delayL_text_label.setHorizontalAlignment(SwingConstants.CENTER);
		delayL_text_label.setForeground(Color.ORANGE);
		delayL_text_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		delayL_text_label.setBounds(12, 71, 69, 16);
		
		delayL_slider.setValue(5);
		delayL_slider.setMaximum(10);
		delayL_slider.setBackground(new Color(192, 192, 192));
		delayL_slider.setBounds(93, 65, 290, 26);
		delayL_slider.addChangeListener(this);
		
		delayL_value_label.setHorizontalAlignment(SwingConstants.CENTER);
		delayL_value_label.setForeground(Color.ORANGE);
		delayL_value_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		delayL_value_label.setBounds(394, 65, 49, 25);
		delayL_value_label.setText("" + delayL_slider.getValue());
		
		effects_panel.setBounds(12, 286, 555, 100);
		effects_panel.setVisible(true);
		effects_panel.setLayout(null);
		effects_panel.add(delayL_value_label);
		effects_panel.add(delayL_slider);
		effects_panel.add(delayL_text_label);
		effects_panel.add(drive_delayF_value_label);
		effects_panel.add(drive_delayF_slider);
		effects_panel.add(drive_delayF_text_label);
		effects_panel.add(effect_text_label);
		effects_panel.add(effect_background_label);
		
		/******************************* 	Effect Switches. 	************************************/
		overdrive_switch.setToolTipText("Overdrive Effect");
		overdrive_switch.setMnemonic('1');
		overdrive_switch.setBounds(93, 399, 180, 340);
		overdrive_switch.setIcon(overdrive_icon);
		overdrive_switch.addActionListener(this);
		
		delay_switch.setToolTipText("Delay Effect");
		delay_switch.setMnemonic('2');
		delay_switch.setBounds(300, 399, 180, 340);
		delay_switch.setIcon(delay_icon);
		delay_switch.addActionListener(this);
		
		overdrive_switch.setEnabled(false);
		delay_switch.setEnabled(false);
		
		/******************************* 	Footer. 	************************************/
		footer_backgroud_label.setBounds(0, 747, 582, 56);
		footer_backgroud_label.setIcon(background2);
		
		footer_text_label.setHorizontalAlignment(SwingConstants.CENTER);
		footer_text_label.setForeground(Color.WHITE);
		footer_text_label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		footer_text_label.setBounds(12, 761, 555, 29);
		
		/******************************* 	Main Panel. 	************************************/
		main_panel.add(footer_text_label);
		main_panel.add(delay_switch);
		main_panel.add(overdrive_switch);
		main_panel.add(effects_panel);
		main_panel.add(filter_panel);
		main_panel.add(switch_button);
		main_panel.add(controls_text_label);
		main_panel.add(controls_box_label);
		main_panel.add(status_text_label);
		main_panel.add(off_led_label);
		main_panel.add(on_led_label);
		main_panel.add(status_box_label);
		main_panel.add(background_label);
		main_panel.add(title_label);
		main_panel.add(top_panel_label);
		main_panel.add(filter_box_label);
		main_panel.add(footer_backgroud_label);
		main_panel.setBounds(0, 0, 582, 803);
		main_panel.setLayout(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(main_panel);
		
		/******************************* 	Frame Setup. 	************************************/
		frame_location_width = (int)(screen_size.getWidth() / 2) - (frame_width / 2);
		frame_location_height = (int)(screen_size.getHeight() / 2 - (frame_height / 2));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(frame_location_width, frame_location_height, frame_width, frame_height);
		setResizable(false);

		refreshEffectsPanel();
		
		cutoff_frequency = (double)cutoff_slider.getValue();
		frequency = (double)freq_slider.getValue();
		drive = (double)drive_delayF_slider.getValue();
		drive = drive / 10;
		delay_length = (int)delayL_slider.getValue();
		delay_feedback = (double)drive_delayF_slider.getValue();
		delay_feedback = delay_feedback / 10;
	}

	/**
	 * Refreshes the effects panel based on current effect.
	 */
	public void refreshEffectsPanel() {
		if(current_effect.equals("none")) {
			effect_text_label.setText("NO EFFECT");
			drive_delayF_text_label.setText("");
			drive_delayF_slider.setEnabled(false);
			drive_delayF_value_label.setText("");
			delayL_text_label.setText("");
			delayL_value_label.setText("");
			delayL_slider.setEnabled(false);
		} else if(current_effect.equals("overdrive")) {
			effect_text_label.setText("OVERDRIVE");
			drive_delayF_text_label.setText("Drive:");
			drive_delayF_slider.setEnabled(true);
			drive_delayF_value_label.setText("" + drive_delayF_slider.getValue());
		} else if(current_effect.equals("delay")) {
			effect_text_label.setText("DELAY");
			drive_delayF_text_label.setText("Feedback:");
			drive_delayF_slider.setEnabled(true);
			drive_delayF_value_label.setText("" + drive_delayF_slider.getValue());
			delayL_text_label.setText("Length:");
			delayL_slider.setEnabled(true);
			delayL_value_label.setText("" + delayL_slider.getValue());
		}
	}
	
	/**
	 * ActionListener for buttons.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(switch_button)) {
			if(isAmpOn) {
				switch_button.setIcon(button_off);
				on_led_label.setIcon(on_inactive_led);
				off_led_label.setIcon(off_led);
				overdrive_switch.setEnabled(false);
				delay_switch.setEnabled(false);
				filter_switch.setIcon(button_off2);
				current_effect = "none";
				refreshEffectsPanel();
				filter_switch.setEnabled(false);
				cutoff_slider.setEnabled(false);
				freq_slider.setEnabled(false);
				isFilterOn = false;
				cutoff_value_label.setText("");
				freq_value_label.setText("");
				isAmpOn = false;
				gui_c.buttonOff();
			} else {
				switch_button.setIcon(button_on);
				on_led_label.setIcon(on_led);
				off_led_label.setIcon(off_inactive_led);
				overdrive_switch.setEnabled(true);
				delay_switch.setEnabled(true);
				filter_switch.setIcon(button_off2);
				filter_switch.setEnabled(true);
				isAmpOn = true;
				gui_c.buttonOn();
			}
		}
		
		if(e.getSource().equals(filter_switch)) {
			if(isFilterOn) {
				filter_switch.setIcon(button_off2);
				cutoff_slider.setEnabled(false);
				freq_slider.setEnabled(false);
				cutoff_value_label.setText("");
				freq_value_label.setText("");
				isFilterOn = false;
				gui_c.stopFilter();
			} else {
				filter_switch.setIcon(button_on2);
				cutoff_slider.setEnabled(true);
				freq_slider.setEnabled(true);
				cutoff_value_label.setText("" + cutoff_slider.getValue());
				freq_value_label.setText("" + freq_slider.getValue());
				isFilterOn = true;
				gui_c.startFilter(cutoff_frequency, frequency);
			}
		}
		
		if(e.getSource().equals(overdrive_switch)) {
			if(current_effect.equals("none")) {
				current_effect = "overdrive";
				delay_switch.setEnabled(false);
				refreshEffectsPanel();
				gui_c.startOverdrive(drive);
			} else {
				delay_switch.setEnabled(true);
				current_effect = "none";
				refreshEffectsPanel();
				gui_c.stopEffect();
			}
		}
		
		if(e.getSource().equals(delay_switch)) {
			if(current_effect.equals("none")) {
				current_effect = "delay";
				overdrive_switch.setEnabled(false);
				refreshEffectsPanel();
				gui_c.startDelay(delay_length, delay_feedback);
			} else {
				overdrive_switch.setEnabled(true);
				current_effect = "none";
				refreshEffectsPanel();
				gui_c.stopEffect();
			}
		}
	}
	
	/**
	 * StateListener for sliders.
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		
		if(source.equals(cutoff_slider)) {
			if(!source.getValueIsAdjusting()) {
				cutoff_frequency = (double)source.getValue();
				cutoff_value_label.setText("" + (int)cutoff_frequency);
				gui_c.updateFilter(cutoff_frequency, frequency);
			}
		}
		
		if(source.equals(freq_slider)) {
			if(!source.getValueIsAdjusting()) {
				frequency = (double)source.getValue();
				freq_value_label.setText("" + (int)frequency);
				gui_c.updateFilter(cutoff_frequency, frequency);
			}
		}
		
		if(source.equals(drive_delayF_slider)) {
			if(!source.getValueIsAdjusting()) {
				if(current_effect.equals("delay")) {
					delay_feedback = (double)(source.getValue());
					delay_feedback = delay_feedback / 10;
					drive_delayF_value_label.setText("" + (int)(delay_feedback * 10));
					gui_c.updateDelay(delay_length, delay_feedback);
				} else if(current_effect.equals("overdrive")) {
					drive = (double)(source.getValue());
					drive = drive / 10;
					drive_delayF_value_label.setText("" + (int)(drive * 10));
					gui_c.updateDrive(drive);
				}
			}
		}
		
		if(source.equals(delayL_slider)) {
			if(!source.getValueIsAdjusting()) {
				delay_length = (int)source.getValue();
				delayL_value_label.setText("" + delay_length);
				gui_c.updateDelay(delay_length, delay_feedback);
			}
		}
	}
}
