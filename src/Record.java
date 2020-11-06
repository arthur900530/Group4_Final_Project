import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;


public class Record extends JFrame{
	
	private Container ct;
	private BackgroundPanel bgp;
	private JButton rButton;
	private JLabel title;
	private JTextArea rArea;
	private JTextArea yArea;
	private JTextArea raArea;
	private ArrayList<String> scoreList;
	private JLabel intro;
//	private JLabel gif;
	private Icon shark1;
	private Icon shark2;
	private JLabel shark,qbg,ql1,ql2,ql3,ql4,ql5,q1c,q2c,q3c,q4c,q5c;
	private Timer s;
	private int timeCount = 0;
//	private JScrollPane j = new JScrollPane();
//	private JLabel qa;
//	private JLabel ya;
//	private JLabel ra;
	private int count=3;
	int i = 0;
	boolean turn = false;
	int a = 0;
	private int timeCount2 = 0;
	private Timer t2;
	private ArrayList<String> yans,rans,q;
	private JButton rturn,q1,q2,q3,q4,q5;
	
	public Record() {
		
//		Icon icon = new ImageIcon("6mb.gif");
//        gif = new JLabel(icon);
//        gif.setBackground(new Color(1,1,1,0));
//        gif.setOpaque(false);
//        gif.setBounds(300, 300, 450, 500);
//		add(gif);
		/////
		setTitle("Record");
		createLabel();
		createArrayList();
		createReturn();
		createButton();
		createTitle();
		createRankArea();
		createScoreList();
		createIntro();
		createShark();
		createT2();
		createQBG();
		createCheck();
		ct = this.getContentPane();
		bgp = new BackgroundPanel((new ImageIcon(MainPage.class.getResource("/resources/fire.jpg"))).getImage());
		bgp.setBounds(0,0,1300,950);
		ct.add(bgp);
		ct.setVisible(true);
		setSize(1300,950);	
		setResizable(false);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void createCheck() {
		q1c = new JLabel();
		q1c.setBounds(255, 670, 64, 64);
		add(q1c);
		q2c = new JLabel();
		q2c.setBounds(442, 670, 64, 64);
		add(q2c);
		q3c = new JLabel();
		q3c.setBounds(623, 670, 64, 64);
		add(q3c);
		q4c = new JLabel();
		q4c.setBounds(798, 670, 64, 64);
		add(q4c);
		q5c = new JLabel();
		q5c.setBounds(991, 670, 64, 64);
		add(q5c);
	}
	public JLabel getQ1c() {
		return q1c;
	}
	public JLabel getQ2c() {
		return q2c;
	}
	public JLabel getQ3c() {
		return q3c;
	}
	public JLabel getQ4c() {
		return q4c;
	}
	public JLabel getQ5c() {
		return q5c;
	}
	public void createQBG() {
		Icon i = new ImageIcon(getClass().getResource("/resources/qbg2.png"));
		qbg = new JLabel(i);
		qbg.setBounds(100, 230, 1096, 544);
		qbg.setVisible(false);
		add(qbg);
	}
	
	public void createT2() {
		T2Listener a = new T2Listener();
		t2 = new Timer(1,a);
	}
	
	class T2Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			timeCount2++;
			if(timeCount2>0&&timeCount2<175) {
//				rButton.setBounds(-timeCount2+175, 70, 100, 100);
				rButton.setBounds(-timeCount2+75, 35, 100, 100);
			}
			if(timeCount2>175) {
				t2.stop();
				getMainPage().setVisible(true);
				setVisible(false);
				timeCount2 = 0;
				rButton.setBounds(75, 35, 100, 100);
				yArea.setVisible(false);
				raArea.setVisible(false);
				rArea.setVisible(false);
				qbg.setVisible(false);
				q1.setVisible(true);
				q2.setVisible(true);
				q3.setVisible(true);
				q4.setVisible(true);
				q5.setVisible(true);
				rturn.setVisible(false);
				ql1.setVisible(true);
				ql2.setVisible(true);
				ql3.setVisible(true);
				ql4.setVisible(true);
				ql5.setVisible(true);
			}
		}
		
	}
	
/////////////////////////Return
	
	public void createReturn() {
			
			rButton = new JButton();
			rButton.setIcon(new ImageIcon(getClass().getResource("/resources/return_arrow.png")));
			rButton.setBackground(new Color(1,1,1,0));
			rButton.setOpaque(false);
			rButton.setBounds(75, 35, 100, 100);
			rButton.setBorderPainted(false);
			ReturnListener r = new ReturnListener();
			rButton.addActionListener(r);
			getContentPane().add(rButton);
			
		}
		
		class ReturnListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				t2.start();
			}
		}
		
///////////////////////////Introduction
		
		public void createIntro() {
			intro = new JLabel("Produced by Arthur Chien .");
			intro.setFont(new Font("Beon", Font.PLAIN, 20));
			intro.setForeground(Color.WHITE);
			intro.setBounds(500, 800, 300,30 );
			getContentPane().add(intro);
		}
		
		
///////////////////////////Title
		
		public void createTitle() {
			title = new JLabel("Your Record");
			title.setFont(new Font("Beon", Font.PLAIN, 50));
			title.setForeground(Color.WHITE);
			title.setBounds(470, 10, 418,150 );
//			TitleMouseListener t = new TitleMouseListener();
//			title.addMouseListener(t);
			getContentPane().add(title);
		}
		
//		class TitleMouseListener implements MouseListener{
//
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override 
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//				title.setFont(new Font("Beon", Font.PLAIN, 60));
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//				title.setFont(new Font("Beon", Font.PLAIN, 50));
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		}
//		
//		class LabelMouseListener implements MouseListener{
//
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override 
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//				qa.setFont(new Font("Yu Gothic Light", Font.BOLD, 40));
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//				qa.setFont(new Font("Yu Gothic Light", Font.PLAIN, 40));
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		}
//		
//		class yAMouseListener implements MouseListener{
//
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override 
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//				ya.setFont(new Font("Yu Gothic Light", Font.BOLD, 30));
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//				ya.setFont(new Font("Yu Gothic Light", Font.PLAIN, 30));
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		}
//		
//		class rAMouseListener implements MouseListener{
//
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override 
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//				ra.setFont(new Font("Yu Gothic Light", Font.BOLD, 30));
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//				ra.setFont(new Font("Yu Gothic Light", Font.PLAIN, 30));
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		}
///////////////////////////////Rank Area
	class Q1Listener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		yArea.setVisible(true);
		raArea.setVisible(true);
		rArea.setVisible(true);
		qbg.setVisible(true);
		q1.setVisible(false);
		q2.setVisible(false);
		q3.setVisible(false);
		q4.setVisible(false);
		q5.setVisible(false);
		rturn.setVisible(true);
		rArea.setText(q.get(0));
		raArea.setText(rans.get(0));
		yArea.setText(yans.get(0));
		ql1.setVisible(false);
		ql2.setVisible(false);
		ql3.setVisible(false);
		ql4.setVisible(false);
		ql5.setVisible(false);
	}
		
	}
	class Q2Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			yArea.setVisible(true);
			raArea.setVisible(true);
			rArea.setVisible(true);
			qbg.setVisible(true);
			q1.setVisible(false);
			q2.setVisible(false);
			q3.setVisible(false);
			q4.setVisible(false);
			q5.setVisible(false);
			rturn.setVisible(true);
			rArea.setText(q.get(1));
			raArea.setText(rans.get(1));
			yArea.setText(yans.get(1));
			ql1.setVisible(false);
			ql2.setVisible(false);
			ql3.setVisible(false);
			ql4.setVisible(false);
			ql5.setVisible(false);
		}
			
		}
	class Q3Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			yArea.setVisible(true);
			raArea.setVisible(true);
			rArea.setVisible(true);
			qbg.setVisible(true);
			q1.setVisible(false);
			q2.setVisible(false);
			q3.setVisible(false);
			q4.setVisible(false);
			q5.setVisible(false);
			rturn.setVisible(true);
			rArea.setText(q.get(2));
			raArea.setText(rans.get(2));
			yArea.setText(yans.get(2));
			ql1.setVisible(false);
			ql2.setVisible(false);
			ql3.setVisible(false);
			ql4.setVisible(false);
			ql5.setVisible(false);
		}
			
		}
	class Q4Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			yArea.setVisible(true);
			raArea.setVisible(true);
			rArea.setVisible(true);
			qbg.setVisible(true);
			q1.setVisible(false);
			q2.setVisible(false);
			q3.setVisible(false);
			q4.setVisible(false);
			q5.setVisible(false);
			rturn.setVisible(true);
			rArea.setText(q.get(3));
			raArea.setText(rans.get(3));
			yArea.setText(yans.get(3));
			ql1.setVisible(false);
			ql2.setVisible(false);
			ql3.setVisible(false);
			ql4.setVisible(false);
			ql5.setVisible(false);
		}
			
		}
	class Q5Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			yArea.setVisible(true);
			raArea.setVisible(true);
			rArea.setVisible(true);
			qbg.setVisible(true);
			q1.setVisible(false);
			q2.setVisible(false);
			q3.setVisible(false);
			q4.setVisible(false);
			q5.setVisible(false);
			rturn.setVisible(true);
			rArea.setText(q.get(4));
			raArea.setText(rans.get(4));
			yArea.setText(yans.get(4));
			ql1.setVisible(false);
			ql2.setVisible(false);
			ql3.setVisible(false);
			ql4.setVisible(false);
			ql5.setVisible(false);
		}
			
		}
	class RturnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			yArea.setVisible(false);
			raArea.setVisible(false);
			rArea.setVisible(false);
			qbg.setVisible(false);
			q1.setVisible(true);
			q2.setVisible(true);
			q3.setVisible(true);
			q4.setVisible(true);
			q5.setVisible(true);
			rturn.setVisible(false);
			ql1.setVisible(true);
			ql2.setVisible(true);
			ql3.setVisible(true);
			ql4.setVisible(true);
			ql5.setVisible(true);
		}
			
		}
		
	public void createButton() {
		rturn = new JButton("Return");
		rturn.setBounds(560, 140, 180, 50);
		rturn.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 40));
//		rturn.setBorderPainted(false);
		rturn.setBackground(new Color(255,255,255,70));
		rturn.setOpaque(false);
		rturn.setForeground(Color.white);
		RturnListener r = new RturnListener();
		rturn.setVisible(false);
		rturn.addActionListener(r);
		add(rturn);
		
		q1 = new JButton("");
		q1.setIcon(new ImageIcon(getClass().getResource("/resources/gem1.png")));
		q1.setBorderPainted(false);
		q1.setOpaque(false);
		q1.setBackground(Color.white);
		q1.setBounds(200, 385, 180, 180);
		Q1Listener a = new Q1Listener();
		q1.addActionListener(a);
		add(q1);
		q2 = new JButton("");
		q2.setIcon(new ImageIcon(getClass().getResource("/resources/gem2.png")));
		q2.setBorderPainted(false);
		q2.setOpaque(false);
		q2.setBackground(Color.white);
		q2.setBounds(380, 385, 180, 180);
		Q2Listener a2 = new Q2Listener();
		q2.addActionListener(a2);
		add(q2);
		q3 = new JButton("");
		q3.setIcon(new ImageIcon(getClass().getResource("/resources/gem3.png")));
		q3.setBorderPainted(false);
		q3.setOpaque(false);
		q3.setBackground(Color.white);
		q3.setBounds(560, 385, 180, 180);
		Q3Listener a3 = new Q3Listener();
		q3.addActionListener(a3);
		add(q3);
		q4 = new JButton("");
		q4.setIcon(new ImageIcon(getClass().getResource("/resources/gem4.png")));
		q4.setBorderPainted(false);
		q4.setOpaque(false);
		q4.setBackground(Color.white);
		q4.setBounds(740, 385, 180, 180);
		Q4Listener a4 = new Q4Listener();
		q4.addActionListener(a4);
		add(q4);
		q5 = new JButton("");
		q5.setIcon(new ImageIcon(getClass().getResource("/resources/gem5.png")));
		q5.setBorderPainted(false);
		q5.setOpaque(false);
		q5.setBackground(Color.white);
		q5.setBounds(918, 385, 180, 180);
		Q5Listener a5 = new Q5Listener();
		q5.addActionListener(a5);
		add(q5);
	}
	
	public void createLabel() {
		ql1 = new JLabel("1");
		ql1.setFont(new Font("Bowhouse Regular",Font.PLAIN,100));
		ql1.setBounds(250, 220, 100, 100);
		add(ql1);
		ql2 = new JLabel("2");
		ql2.setFont(new Font("Bowhouse Regular",Font.PLAIN,100));
		ql2.setBounds(445, 220, 100, 100);
		add(ql2);
		ql3 = new JLabel("3");
		ql3.setFont(new Font("Bowhouse Regular",Font.PLAIN,100));
		ql3.setBounds(620, 220, 100, 100);
		add(ql3);
		ql4 = new JLabel("4");
		ql4.setFont(new Font("Bowhouse Regular",Font.PLAIN,100));
		ql4.setBounds(795, 220, 100, 100);
		add(ql4);
		ql5 = new JLabel("5");
		ql5.setFont(new Font("Bowhouse Regular",Font.PLAIN,100));
		ql5.setBounds(982, 220, 100, 100);
		add(ql5);
	}
		
	public void createArrayList() {
		rans = new ArrayList<String>();
		rans.add(" Play!");
		rans.add(" Play!");
		rans.add(" Play!");
		rans.add(" Play!");
		rans.add(" Play!");
		yans = new ArrayList<String>();
		yans.add("                                       Go");
		yans.add("                                       Go");
		yans.add("                                       Go");
		yans.add("                                       Go");
		yans.add("                                       Go");
		q = new ArrayList<String>();
		q.add("\n"+"\n"+"                     You haven't Answer this Question!");
		q.add("\n"+"\n"+"                     You haven't Answer this Question!");
		q.add("\n"+"\n"+"                     You haven't Answer this Question!");
		q.add("\n"+"\n"+"                     You haven't Answer this Question!");
		q.add("\n"+"\n"+"                     You haven't Answer this Question!");
	}
		
	public ArrayList getRightAnswerArrayList() {
		return rans;
	}
	public ArrayList getYourAnswerArrayList() {
		return yans;
	}
	public ArrayList getQuestionArrayList() {
		return q;
	}
	public void createRankArea() {
//		qa = new JLabel("Questions");
//		qa.setForeground(Color.white);
//		qa.setFont(new Font("Yu Gothic Light", Font.PLAIN, 40));
//		qa.setBounds(100, 180, 200, 50);
//		
//		ya = new JLabel("Your Ans");
//		ya.setForeground(Color.white);
//		ya.setFont(new Font("Yu Gothic Light", Font.PLAIN, 30));
//		ya.setBounds(800, 180, 150, 50);
//		
//		ra = new JLabel("Right Ans");
//		ra.setForeground(Color.white);
//		ra.setFont(new Font("Yu Gothic Light", Font.PLAIN, 30));
//		ra.setBounds(1000, 180, 150, 50);
//		
//		LabelMouseListener t = new LabelMouseListener();
//		qa.addMouseListener(t);
//		
//		yAMouseListener y = new yAMouseListener();
//		ya.addMouseListener(y);
//		
//		rAMouseListener r = new rAMouseListener();
//		ra.addMouseListener(r);
//		
//		add(ya);
//		add(ra);
//		add(qa);
		yArea = new JTextArea();
		yArea.setVisible(false);
		yArea.setOpaque(false);
		yArea.setLineWrap(true);
		yArea.setBackground(new Color(255,255,255,90));
		yArea.setEditable(false);
		

		raArea = new JTextArea();
		raArea.setVisible(false);
		raArea.setLineWrap(true);
		raArea.setOpaque(false);
		raArea.setBackground(new Color(255,255,255,90));
		raArea.setEditable(false);
		
		rArea = new JTextArea();
		rArea.setVisible(false);
		rArea.setLineWrap(true);
		rArea.setOpaque(false);
		rArea.setBackground(new Color(255,255,255,90));
		rArea.setEditable(true);

		yArea.setBounds( 100, 575, 550, 200);
		yArea.setBorder(new EtchedBorder());
		yArea.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 40));
		yArea.setForeground(Color.white);
		yArea.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 0, Color.white));
		
		raArea.setBounds( 650, 575, 550, 200);
		raArea.setBorder(new EtchedBorder());
		raArea.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 40));
		raArea.setForeground(Color.white);
		raArea.setBorder(BorderFactory.createMatteBorder(4, 0, 4, 4, Color.white));
		
		rArea.setBounds( 100, 230, 1100, 345);
		rArea.setBorder(new EtchedBorder());
		rArea.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 40));
		rArea.setForeground(Color.white);
		rArea.setBorder(BorderFactory.createMatteBorder(4, 4, 0, 4, Color.white));
		getContentPane().add(yArea);
		getContentPane().add(raArea);
		getContentPane().add(rArea);
	}
	public JTextArea getRecordArea() {
		return this.rArea;
	}
	
	public JTextArea getYArea() {
		return yArea;
	}
	
	public JTextArea getRaArea() {
		return raArea;
	}
	
	public void createScoreList() {
		scoreList = new ArrayList<String>();
	}
	
	public ArrayList<String> getScoreList() {
		return scoreList;
	}
	
	public String updateScore() {
		String s = "";
		for(int i = 0;i < scoreList.size();i++) {
			s = s +(i+1)+".          "+scoreList.get(i)+"\n";
		}

		return s;
	}
	
	public MainPage getMainPage() {
		for(Frame frame: JFrame.getFrames()) {
		if(frame.getTitle().equals("FinTest")) {
			MainPage mp = (MainPage) frame;
			return mp.getMainPage();
		}
		}
		return null;
		}
	
	public Record getRanking() {
		return this;
	}
	 
	public void renew() {
		rArea.setText("11111");
	}
	
	/////////////////////shark
	
	public void createShark() {
		shark1 = new ImageIcon(MainPage.class.getResource("/resources/snail2.png"));
		shark2 = new ImageIcon(MainPage.class.getResource("/resources/snail.png"));
		shark = new JLabel(shark1);
		Snail s1 = new Snail();
		shark.addMouseListener(s1);
		shark.setBounds(0, 800, 100, 100);
		SharkTimeListener stl1 = new SharkTimeListener();
		s = new Timer(100,stl1);
		s.start();
		getContentPane().add(shark);
	}
	
	class Snail implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
			count++;
			if(count%2==0) {

			getMainPage().getClip().stop();
			try {
				getMainPage().play1();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			else {
				getMainPage().getClip2().stop();
				try {
					getMainPage().play();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class SharkTimeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				timeCount++;
				
				if(timeCount<=500) {
					shark.setIcon(shark1);
					a = (100+timeCount*2);
					shark.setBounds( a, 800, 100, 100);
				}
				if(timeCount>500) {
					i++;
					shark.setIcon(shark2);
					shark.setBounds( a-i*2, 800, 100, 100);
				}
				if(timeCount>1000) {
					timeCount = 0;
					i=0;
				}
			
			
//			if(turn = true) {
//				shark.setBounds( a-timeCount*8, 800, 100, 100);
//				
//			}
			
	
		}
		
	}
}

