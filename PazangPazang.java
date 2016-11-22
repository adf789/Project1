package softwareEngineering;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class PazangPazang {

	public static void main(String[] args) {
		FirstGUI start_Gui = FirstGUI.getGUI();
		start_Gui.setVisible(true);
	}

}

class FirstGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static FirstGUI gui = new FirstGUI();
	private static File file;
	private final static int WIDTH = 600, HEIGHT = 200;
	private MainGUI main_Gui;
	private JPanel pane_Center, pane_East;
	private JButton btn_Start, btn_Find, btn_Easy, btn_Normal, btn_Hard;
	private JLabel lb_Title, lb_Level;
	private JTextField text_File;
	private MyActionListener listener;
	
	private FirstGUI(){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		setResizable(false);
		setBounds((screen.width - WIDTH)/2, (screen.height - HEIGHT)/2, WIDTH, HEIGHT);
		setTitle("PazangPazang");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		file = new File(Toolkit.getDefaultToolkit().getClass().getResource("/res/sound/sample.mp3").getFile());
		file = new File("res/sample.mp3");
		pane_Center = new JPanel();
		pane_East = new JPanel();
		btn_Start = new JButton("Start Game");
		btn_Find = new JButton("Find Music");
		lb_Title = new JLabel("PazangPazang");
		lb_Level = new JLabel("Easy");
		text_File = new JTextField();
		btn_Easy = new JButton("Easy");
		btn_Normal = new JButton("Normal");
		btn_Hard = new JButton("Hard");
		listener = new MyActionListener();
		//객체 생성
		
		
		pane_Center.setBackground(Color.BLACK);
		pane_Center.setOpaque(true);
		pane_East.setBackground(Color.BLACK);
		pane_East.setOpaque(true);
		pane_East.setLayout(new BoxLayout(pane_East, BoxLayout.Y_AXIS));
		//panel 설정
		
		Font temp_Font = new Font("돋음", Font.BOLD, 15);
		btn_Start.setPreferredSize(new Dimension(100, 40));
		btn_Start.addActionListener(listener);
		btn_Find.setPreferredSize(new Dimension(100, 40));
		btn_Find.addActionListener(listener);
		btn_Easy.setPreferredSize(new Dimension(100, 40));
		btn_Easy.addActionListener(listener);
		btn_Easy.setBackground(Color.LIGHT_GRAY);
		btn_Easy.setFont(temp_Font);
		btn_Normal.setPreferredSize(new Dimension(100, 40));
		btn_Normal.addActionListener(listener);
		btn_Normal.setBackground(Color.LIGHT_GRAY);
		btn_Normal.setFont(temp_Font);
		btn_Hard.setPreferredSize(new Dimension(100, 40));
		btn_Hard.addActionListener(listener);
		btn_Hard.setBackground(Color.LIGHT_GRAY);
		btn_Hard.setFont(temp_Font);
		//button 설정
		
		lb_Title.setPreferredSize(new Dimension(500, 70));
		lb_Title.setHorizontalAlignment(JLabel.CENTER);
		lb_Title.setFont(new Font("궁서", Font.ITALIC, 30));
		lb_Title.setForeground(Color.BLUE);
		lb_Level.setFont(new Font("바탕", Font.BOLD, 20));
		lb_Level.setBackground(Color.BLACK);
		lb_Level.setForeground(Color.BLUE);
		lb_Level.setHorizontalAlignment(JLabel.CENTER);
		//label 설정
		
		text_File.setEditable(false);
		text_File.setText(file.toString());
		
		add(pane_Center, BorderLayout.CENTER);
		add(text_File, BorderLayout.SOUTH);
		add(pane_East, BorderLayout.EAST);
		pane_Center.add(lb_Title);
		pane_Center.add(btn_Find);
		pane_Center.add(btn_Start);
		pane_East.add(btn_Easy);
		pane_East.add(btn_Normal);
		pane_East.add(btn_Hard);
		pane_East.add(lb_Level);
	}
	public static FirstGUI getGUI(){
		return gui;
	}
	
	private class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btn_Find)){
				JFileChooser fc = new JFileChooser();
				if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					file = fc.getSelectedFile();
					text_File.setText(file.toString());
				}else{
					JOptionPane.showMessageDialog(null, "취소되었습니다.");
				}
				
			}else if(e.getSource().equals(btn_Start)){
				if(file == null){
					JOptionPane.showMessageDialog(null, "음악 파일이 선택되지 않았습니다.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				main_Gui = MainGUI.getGUI();
				main_Gui.setFile(file, lb_Level.getText());
				main_Gui.setVisible(true);
				gui.setVisible(false);
				
			}else if(e.getSource().equals(btn_Easy)){
				lb_Level.setText("Easy");
			}else if(e.getSource().equals(btn_Normal)){
				lb_Level.setText("Normal");
			}else if(e.getSource().equals(btn_Hard)){
				lb_Level.setText("Hard");
			}
		}
		
	}
}
