package com.igor.guitarproject.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;

public class MainGUI extends JFrame implements ActionListener, ChangeListener {

	private JPanel contentPane;

	private Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
	private int frame_location_width;
	private int frame_location_height;
	private int frame_width = 600;
	private int frame_height = 800;
	
	ImageIcon on_led = new ImageIcon(this.getClass().getResource("/ON.png"));
	ImageIcon off_led = new ImageIcon(this.getClass().getResource("/OFF.png"));
	ImageIcon off_inactive_led = new ImageIcon(this.getClass().getResource("/OFF_inactive.png"));
	ImageIcon on_inactive_led = new ImageIcon(this.getClass().getResource("/ON_inactive.png"));
	ImageIcon background = new ImageIcon(this.getClass().getResource("/background.png"));
	ImageIcon background2 = new ImageIcon(this.getClass().getResource("/background2.png"));
	ImageIcon bg_panel = new ImageIcon(this.getClass().getResource("/panel_bg.png"));
	ImageIcon bg_panel2 = new ImageIcon(this.getClass().getResource("/panel_bg2.png"));
	ImageIcon bg_panel3 = new ImageIcon(this.getClass().getResource("/panel_bg3.png"));
	ImageIcon button_on = new ImageIcon(this.getClass().getResource("/button_on.png"));
	ImageIcon button_off = new ImageIcon(this.getClass().getResource("/button_off.png"));
	ImageIcon button_off2 = new ImageIcon(this.getClass().getResource("/switch2_off.png"));
	ImageIcon button_on2 = new ImageIcon(this.getClass().getResource("/switch2_on.png"));
	ImageIcon overdrive_icon = new ImageIcon(this.getClass().getResource("/overdrive.png"));
	ImageIcon delay_icon = new ImageIcon(this.getClass().getResource("/delay.png"));

	JPanel main_panel = new JPanel();
	JPanel filter_panel = new JPanel();
	JLabel background_label = new JLabel();
	JLabel top_panel_label = new JLabel("");
	JLabel title_label = new JLabel("Java Guitar Amp");
	JLabel filter_box_label = new JLabel("New label");
	JLabel controls_text_label = new JLabel("CONTROLS");
	JLabel controls_box_label = new JLabel("");
	JLabel status_text_label = new JLabel("STATUS");
	JLabel status_box_label = new JLabel("");
	JButton switch_button = new JButton("");
	JLabel off_led_label = new JLabel("");
	JLabel on_led_label = new JLabel("");
	JLabel filter_background_label = new JLabel("");
	
	JSlider cutoff_slider = new JSlider();
	
	boolean isAmpOn = false;
	boolean isFilterOn = false;
	String current_effect = "none";
	boolean isEffectOn = false;
	
	double cutoff_frequency = 0;
	double frequency = 0;
	double drive = 0;
	double delay_length = 0;
	double delay_feedback = 0;
	
	private final JLabel filter_text_label = new JLabel("FILTER SOUND");
	private final JLabel cutoff_text_label = new JLabel("CUTOFF:");
	private final JLabel cutoff_value_label = new JLabel("");
	private final JLabel frequency_text_label = new JLabel("FREQ:");
	private final JLabel freq_value_label = new JLabel("");
	private final JButton filter_switch = new JButton("");
	private final JPanel effects_panel = new JPanel();
	private final JLabel effect_background_label = new JLabel("");
	private final JLabel effect_text_label = new JLabel("");
	private final JLabel drive_delayF_text_label = new JLabel("");
	private final JLabel drive_delayF_value_label = new JLabel("");
	private final JSlider drive_delayF_slider = new JSlider();
	private final JSlider freq_slider = new JSlider();
	private final JLabel delayL_text_label = new JLabel("");
	private final JSlider delayL_slider = new JSlider();
	private final JLabel delayL_value_label = new JLabel("");
	private final JButton overdrive_switch = new JButton("");
	private final JButton delay_switch = new JButton("");

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		frame_location_width = (int)(screen_size.getWidth() / 2) - (frame_width / 2);
		frame_location_height = (int)(screen_size.getHeight() / 2 - (frame_height / 2));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(frame_location_width, frame_location_height, frame_width, frame_height);
//		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		main_panel.setBounds(0, 0, 582, 753);
		contentPane.add(main_panel);
		main_panel.setLayout(null);
		switch_button.setToolTipText("On / Off");
		
		switch_button.setMnemonic('o');
		switch_button.setIcon(button_off);
		switch_button.addActionListener(this);
		delay_switch.setToolTipText("Delay Effect");
		delay_switch.setMnemonic('2');
		delay_switch.setBounds(300, 399, 180, 340);
		delay_switch.setIcon(delay_icon);
		delay_switch.addActionListener(this);
		main_panel.add(delay_switch);
		overdrive_switch.setToolTipText("Overdrive Effect");
		overdrive_switch.setMnemonic('1');
		overdrive_switch.setBounds(93, 399, 180, 340);
		overdrive_switch.setIcon(overdrive_icon);
		overdrive_switch.addActionListener(this);
		main_panel.add(overdrive_switch);
		effects_panel.setBounds(12, 286, 555, 100);
		
		main_panel.add(effects_panel);
		effects_panel.setVisible(true);
		effects_panel.setLayout(null);
		drive_delayF_slider.setBackground(new Color(192, 192, 192));
		drive_delayF_slider.setValue(8);
		drive_delayF_slider.setMaximum(10);
		drive_delayF_slider.setBounds(93, 26, 290, 26);
		drive_delayF_slider.addChangeListener(this);
		delayL_value_label.setHorizontalAlignment(SwingConstants.CENTER);
		delayL_value_label.setForeground(Color.ORANGE);
		delayL_value_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		delayL_value_label.setBounds(394, 65, 49, 25);
		effects_panel.add(delayL_value_label);
		delayL_slider.setValue(5);
		delayL_slider.setMaximum(10);
		delayL_slider.setBackground(new Color(192, 192, 192));
		delayL_slider.setBounds(93, 65, 290, 26);
		delayL_slider.addChangeListener(this);
		delayL_value_label.setText("" + delayL_slider.getValue());
		effects_panel.add(delayL_slider);
		delayL_text_label.setHorizontalAlignment(SwingConstants.CENTER);
		delayL_text_label.setForeground(Color.ORANGE);
		delayL_text_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		delayL_text_label.setBounds(12, 71, 69, 16);
		
		effects_panel.add(delayL_text_label);
		drive_delayF_value_label.setHorizontalAlignment(SwingConstants.CENTER);
		drive_delayF_value_label.setForeground(Color.ORANGE);
		drive_delayF_value_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		drive_delayF_value_label.setBounds(394, 27, 49, 25);
		effects_panel.add(drive_delayF_value_label);
		effects_panel.add(drive_delayF_slider);
		drive_delayF_text_label.setHorizontalAlignment(SwingConstants.CENTER);
		drive_delayF_text_label.setForeground(Color.ORANGE);
		drive_delayF_text_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		drive_delayF_text_label.setBounds(12, 30, 69, 16);
		
		effects_panel.add(drive_delayF_text_label);
		effect_text_label.setHorizontalAlignment(SwingConstants.CENTER);
		effect_text_label.setForeground(Color.ORANGE);
		effect_text_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		effect_text_label.setBounds(156, 0, 215, 16);
		
		effects_panel.add(effect_text_label);
		effect_background_label.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE));
		effect_background_label.setBounds(0, 0, 555, 100);
		effect_background_label.setIcon(background);
		effects_panel.add(effect_background_label);
		filter_panel.setBounds(12, 176, 555, 100);
		
		main_panel.add(filter_panel);
		filter_panel.setLayout(null);
		filter_switch.setBounds(456, 12, 85, 80);
		filter_switch.setIcon(button_off2);
		filter_switch.addActionListener(this);
		freq_slider.setBackground(new Color(192, 192, 192));
		freq_slider.setMaximum(10000);
		freq_slider.setValue(5000);
		freq_slider.setBounds(93, 66, 290, 26);
		freq_slider.addChangeListener(this);
		
		filter_panel.add(freq_slider);
		filter_panel.add(filter_switch);
		freq_value_label.setHorizontalAlignment(SwingConstants.CENTER);
		freq_value_label.setForeground(Color.ORANGE);
		freq_value_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		freq_value_label.setBounds(395, 67, 49, 25);
		freq_value_label.setText("" + freq_slider.getValue());
		
		filter_panel.add(freq_value_label);
		frequency_text_label.setHorizontalAlignment(SwingConstants.CENTER);
		frequency_text_label.setForeground(Color.ORANGE);
		frequency_text_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		frequency_text_label.setBounds(12, 71, 69, 16);
		
		filter_panel.add(frequency_text_label);
		cutoff_value_label.setHorizontalAlignment(SwingConstants.CENTER);
		cutoff_value_label.setForeground(Color.ORANGE);
		cutoff_value_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		cutoff_value_label.setBounds(395, 29, 49, 25);
		cutoff_value_label.setText("" + cutoff_slider.getValue());
		
		filter_panel.add(cutoff_value_label);
		cutoff_slider.setForeground(Color.ORANGE);
		cutoff_slider.setBackground(new Color(192, 192, 192));
		cutoff_slider.addChangeListener(this);
		cutoff_slider.setMinimum(10);
		cutoff_slider.setMaximum(10000);
		cutoff_slider.setValue(500);
		cutoff_slider.setBounds(93, 29, 290, 25);
		filter_panel.add(cutoff_slider);
		cutoff_text_label.setForeground(Color.ORANGE);
		cutoff_text_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		cutoff_text_label.setHorizontalAlignment(SwingConstants.CENTER);
		cutoff_text_label.setBounds(12, 33, 69, 16);
		
		filter_panel.add(cutoff_text_label);
		filter_text_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		filter_text_label.setForeground(Color.ORANGE);
		filter_text_label.setHorizontalAlignment(SwingConstants.CENTER);
		filter_text_label.setBounds(205, 0, 132, 16);
		
		filter_panel.add(filter_text_label);
		filter_background_label.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE));
		filter_background_label.setIcon(background2);
		filter_background_label.setBounds(0, 0, 555, 100);
		
		filter_panel.add(filter_background_label);
		switch_button.setBounds(12, 83, 73, 75);
		main_panel.add(switch_button);
		
		controls_text_label.setForeground(Color.ORANGE);
		controls_text_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		controls_text_label.setHorizontalAlignment(SwingConstants.CENTER);
		controls_text_label.setBounds(169, 70, 124, 16);
		main_panel.add(controls_text_label);
		
		controls_box_label.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
		controls_box_label.setIcon(bg_panel2);
		controls_box_label.setBounds(3, 70, 415, 96);
		main_panel.add(controls_box_label);
		
		status_text_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		status_text_label.setHorizontalAlignment(SwingConstants.CENTER);
		status_text_label.setForeground(Color.ORANGE);
		status_text_label.setBounds(438, 70, 124, 16);
		main_panel.add(status_text_label);
		
		off_led_label.setIcon(off_led);
		off_led_label.setBounds(514, 99, 56, 56);
		main_panel.add(off_led_label);
		
		on_led_label.setIcon(on_inactive_led);
		on_led_label.setBounds(438, 99, 56, 56);
		main_panel.add(on_led_label);
		
		status_box_label.setBackground(Color.BLACK);
		status_box_label.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
		status_box_label.setIcon(bg_panel2);
		status_box_label.setBounds(425, 70, 155, 96);
		main_panel.add(status_box_label);
		
		background_label.setIcon(bg_panel3);
		background_label.setForeground(Color.WHITE);
		background_label.setBounds(0, 70, 582, 685);
		main_panel.add(background_label);
		
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setFont(new Font("Ravie", Font.BOLD, 34));
		title_label.setForeground(new Color(255, 204, 0));
		title_label.setBounds(71, 13, 440, 45);
		main_panel.add(title_label);
		top_panel_label.setIcon(bg_panel);
		top_panel_label.setForeground(Color.WHITE);
		top_panel_label.setBounds(0, 0, 582, 70);
		main_panel.add(top_panel_label);
		
		filter_box_label.setBounds(13, 181, 260, 360);
		main_panel.add(filter_box_label);
		
		
		cutoff_slider.setEnabled(false);
		freq_slider.setEnabled(false);
		cutoff_value_label.setText("");
		freq_value_label.setText("");
		overdrive_switch.setEnabled(false);
		delay_switch.setEnabled(false);
		refreshEffectsPanel();
	}

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
				isAmpOn = false;
			} else {
				switch_button.setIcon(button_on);
				on_led_label.setIcon(on_led);
				off_led_label.setIcon(off_inactive_led);
				overdrive_switch.setEnabled(true);
				delay_switch.setEnabled(true);
				filter_switch.setIcon(button_off2);
				isAmpOn = true;
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
			} else {
				filter_switch.setIcon(button_on2);
				cutoff_slider.setEnabled(true);
				freq_slider.setEnabled(true);
				cutoff_value_label.setText("" + cutoff_slider.getValue());
				freq_value_label.setText("" + freq_slider.getValue());
				isFilterOn = true;
			}
		}
		
		if(e.getSource().equals(overdrive_switch)) {
			if(current_effect.equals("none")) {
				current_effect = "overdrive";
				delay_switch.setEnabled(false);
				refreshEffectsPanel();
			} else {
				delay_switch.setEnabled(true);
				current_effect = "none";
				refreshEffectsPanel();
			}
		}
		
		if(e.getSource().equals(delay_switch)) {
			if(current_effect.equals("none")) {
				current_effect = "delay";
				overdrive_switch.setEnabled(false);
				refreshEffectsPanel();
			} else {
				overdrive_switch.setEnabled(true);
				current_effect = "none";
				refreshEffectsPanel();
			}
		}
	}
	
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

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		
		if(source.equals(cutoff_slider)) {
			if(!source.getValueIsAdjusting()) {
				cutoff_value_label.setText("" + (int)source.getValue());
			}
		}
		
		if(source.equals(freq_slider)) {
			if(!source.getValueIsAdjusting()) {
				freq_value_label.setText("" + (int)source.getValue());
			}
		}
		
		if(source.equals(drive_delayF_slider)) {
			if(!source.getValueIsAdjusting()) {
				drive_delayF_value_label.setText("" + (int)source.getValue());
			}
		}
		
		if(source.equals(delayL_slider)) {
			if(!source.getValueIsAdjusting()) {
				delayL_value_label.setText("" + (int)source.getValue());
			}
		}
	}
}
