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
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

public class MainPage extends JFrame {
	
	private Container ct;
	private BackgroundPanel bgp;
	private JLabel title;
	private JButton start;
	private JButton record;
	private JButton fb;
	private JButton ig;
	private JButton mail;
	private JLabel nm;
	private JLabel gp;
	private Record r;
	private GamePage g;
	private JLabel cool;
	private Icon coolI;
	private JLabel cool2;
	private Icon coolI2;
	private JLabel cool3;
	private Icon coolI3;
	private JLabel cool4;
	private Icon coolI4;
	private JLabel cool5;
	private Icon coolI5;
	private int timeCount;
	private int timeCount2;
	private int timeCount3;
	private Timer t;
	private Timer t2;
	private Timer t3;
	private JButton sun;
	private JButton moon;
	private JButton logo;
	Clip clip;
	Clip clip2;
	private JLabel k;
	private JLabel h;
	
	public MainPage() {
//		playSound();
		createButton();
		createTitle();
		createGp();
		createCool();
		createTimer();
		createT2();
		createT3();
//		createkn();
		r = new Record();
		try {
			play();
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
		
		setTitle("FinTest");
		ct = this.getContentPane();
		bgp = new BackgroundPanel((new ImageIcon(MainPage.class.getResource("resources/bg.jpg")).getImage()));
		bgp.setBounds(0,0,1300,950);
		ct.add(bgp);
		ct.setVisible(true);
		setSize(1300,950);	
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	public void createkn() {
		k = new JLabel("KNOWLEDGE");
		h = new JLabel("HOME");
		k.setFont(new Font("Beon", Font.PLAIN, 25));
		h.setFont(new Font("Beon", Font.PLAIN, 25));
		k.setBounds(992, 30, 200, 30);
		h.setBounds(180, 35, 200, 30);
		k.setVisible(false);
		h.setVisible(false);
		add(k);
		add(h);
	}
	
	public static void playMouse() {
		
		new Thread(new Runnable() {
			  // The wrapper thread is unnecessary, unless it blocks on the
			  // Clip finishing; see comments.
			    public void run() {
			      try(InputStream in = getClass().getResourceAsStream("/music/mouse.wav")) {	
			    	  InputStream bufferedIn = new BufferedInputStream(in);
			          try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
			          Clip clip = AudioSystem.getClip();
			          clip.open(audioIn);
			          clip.start();
				      Thread.sleep(clip.getMicrosecondLength()/1000);
			      }
			      }
			       catch (Exception e) {
			        System.err.println(e.getMessage());
			      }
			    }
			  }).start();
	}
	
	public Clip getClip() {
		return clip;
	}
	
	public Clip getClip2() {
		return clip2;
	}
	
	public void play() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		InputStream in = getClass().getResourceAsStream("/music/13.wav");
		InputStream bufferedIn = new BufferedInputStream(in);
        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
        clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);}
	}
	
	public void play1() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		InputStream in = getClass().getResourceAsStream("/music/04.wav");
		InputStream bufferedIn = new BufferedInputStream(in);
        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
        clip2 = AudioSystem.getClip();
        clip2.open(audioIn);
        clip2.start();
        clip2.loop(Clip.LOOP_CONTINUOUSLY);}
	}
	
	public void createTitle() {
		title = new JLabel("");
		title.setFont(new Font("Bowhouse Regular", Font.PLAIN, 160));
//		title.setForeground(new Color(255,204,102));
//		title.setBorder(new EtchedBorder());
		title.setForeground(Color.black);
		title.setBounds(470, 173, 470,180 );
		TitleMouseListener t = new TitleMouseListener();
		title.addMouseListener(t);
		getContentPane().add(title);
	}
	
	public void createTimer() {
		TimerListener a = new TimerListener();
		t = new Timer(1,a);
		t.start();
		timeCount = -300;
	}
	
	class TimerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			timeCount++;
			if(timeCount>-220) {
				logo.setVisible(true);
			}
			if(timeCount<=70&&timeCount>0) {
				title.setText("F");
			}
			else if(timeCount<=140&&timeCount>70) {
				title.setText("Fi");
			}
			else if(timeCount<=210&&timeCount>140) {
				title.setText("Fin");
			}
			else if(timeCount<=280&&timeCount>210) {
				title.setText("Fint");
			}
			else if(timeCount<=350&&timeCount>280) {
				title.setText("Finte");
			}
			else if(timeCount<=420&&timeCount>350) {
				title.setText("Fintes");
			}
			else if(timeCount<=490&&timeCount>420) {
				title.setText("Fintest");
			}
			
//			else if(timeCount<=560&&timeCount>490) {
//				title.setText("Financia");
//			}
//			else if(timeCount<=630&&timeCount>560) {
//				title.setText("Financial");
//			}
//			else if(timeCount<=700&&timeCount>630) {
//				title.setText("Financial Q");
//			}
//			else if(timeCount<=770&&timeCount>700) {
//				title.setText("Financial Qu");
//			}
//			else if(timeCount<=840&&timeCount>770) {
//				title.setText("Financial Qui");
//			}
//			else if(timeCount<=910&&timeCount>840) {
//				title.setText("Financial Quiz");
//			}
//			
			if(timeCount>=490) {
//				start.setBounds(timeCount-1145, 360, 405,150 );
				start.setVisible(true);
			}
			if(timeCount>=590) {
//				ranking.setBounds(-timeCount+1815, 550, 405,150 );
				record.setVisible(true);
			}
			if(timeCount>590&&timeCount<708) {
				moon.setBounds(150, -timeCount/5+1098/5, 128,timeCount-580 );
				sun.setBounds(1000, -timeCount/5+1098/5, 128,timeCount-580 );
//				k.setVisible(true);
//				h.setVisible(true);
			}
			if(timeCount>708&&timeCount<1658) {
				cool.setBounds(0,-timeCount+1668,1300,950);
			}
			
			if(timeCount>=1658) {
				
				start.setEnabled(true);
				record.setEnabled(true);
				t.stop();
			}
		}
		
	}
	
	public void createCool() {
		coolI = new ImageIcon(MainPage.class.getResource("/resources/designbig2.png"));
		cool = new JLabel(coolI);
		cool.setBounds(0,950,1300,950);
		getContentPane().add(cool);
		coolI2 = new ImageIcon(MainPage.class.getResource("/resources/ice.png"));
		cool2 = new JLabel(coolI2);
		cool2.setBounds(0,950,1300,950);
		getContentPane().add(cool2);
		coolI3 = new ImageIcon(MainPage.class.getResource("/resources/blue.png"));
		cool3 = new JLabel(coolI3);
		cool3.setBounds(0,950,1300,950);
		getContentPane().add(cool3);
		coolI4 = new ImageIcon(MainPage.class.getResource("/resources/gold1.png"));
		cool4 = new JLabel(coolI4);
		cool4.setBounds(0,950,1300,476);
		getContentPane().add(cool4);
		coolI5 = new ImageIcon(MainPage.class.getResource("/resources/gold2.png"));
		cool5 = new JLabel(coolI5);
		cool5.setBounds(0,950,1300,474);
		getContentPane().add(cool5);
		
		
//		earthI = new ImageIcon("earth.png");
//		earth = new JLabel(earthI);
//		earth.setBounds(150,100,800,800);
//		add(earth);
	}
	
	public void createButton() {
		start = new JButton("Start");
		start.setEnabled(false);
		start.setFont(new Font("Beon", Font.PLAIN, 53));
		start.setBounds(447, 360, 405,150 );
		start.setBackground(new Color(184,207,229));
//		start.setOpaque(false);
		start.setForeground(Color.white);
//		start.setBorderPainted(false);
		StartListener listener = new StartListener();
		start.addActionListener(listener);
		StartMouseListener mouse = new StartMouseListener();
		start.addMouseListener(mouse);
		start.setVisible(false);
		getContentPane().add(start);
		
		record = new JButton("Record");
		record.setEnabled(false);
		record.setFont(new Font("Beon", Font.PLAIN, 53));
		record.setBounds(447, 550, 405,150 );
		record.setBackground(new Color(184,207,229));
//		ranking.setOpaque(false);
		record.setForeground(Color.white);
//		ranking.setBorderPainted(false);
		RankingListener r = new RankingListener();
		record.addActionListener(r);
		RankingMouseListener mouse2 = new RankingMouseListener();
		record.addMouseListener(mouse2);
		record.setVisible(false);
		getContentPane().add(record);
		
	}
		
	public void createGp() {

		moon = new JButton();
		moon.setIcon(new ImageIcon(MainPage.class.getResource("/resources/moon.png")));
		moon.setBackground(new Color(1,1,1,0));
		moon.setOpaque(false);
		moon.setBorderPainted(false);
		moon.setBounds(150, 400, 128,0 );
		MoonListener m = new MoonListener();
		moon.addActionListener(m);
		getContentPane().add(moon);
		
		sun = new JButton();
		sun.setIcon(new ImageIcon(MainPage.class.getResource("/resources/sun.png")));
		sun.setBackground(new Color(1,1,1,0));
		sun.setOpaque(false);
		sun.setBorderPainted(false);
		sun.setBounds(1000, 400, 128,0 );
		SunListener s = new SunListener();
		sun.addActionListener(m);
		getContentPane().add(sun);
		
		logo = new JButton();
		
		logo.setIcon(new ImageIcon(MainPage.class.getResource("/resources/logo2.1.png")));
		logo.setBackground(new Color(1,1,1,0));
		logo.setOpaque(false);
		logo.setBorderPainted(false);
		logo.setBounds(550, 60, 200,78 );
		logo.setVisible(false);
		getContentPane().add(logo);
	}
	
//////////Action Listener
	
	class StartListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			t2.start();
			r.getRecordArea().setText("");
			playMouse();	
		}
	}
	
	public void createT3() {
		T3Listener a = new T3Listener();
		t3 = new Timer(1,a);
		timeCount3 = 0;
	}
	
	public void createT2() {
		T2Listener a = new T2Listener();
		t2 = new Timer(1,a);
		timeCount2 = 0;
	}
	
	class T3Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			timeCount3++;


		if(timeCount3>1) {

		}
		if(timeCount3>150) {
			logo.setVisible(false);
		}
		if(timeCount3>300) {
			record.setVisible(false);
		}
		if(timeCount3>450) {
			start.setVisible(false);
		}
		if(timeCount3>620&&timeCount3<950) {
			title.setBounds(470, -timeCount3+777, 470,180 );
		}
		if(timeCount3>620&&timeCount3<950) {
			sun.setBounds(1000, timeCount3*3-1700, 128,128 );
			moon.setBounds(150, timeCount3*3-1700, 128,128 );
			cool.setBounds(0,timeCount3*3-1860,1300,950);
//			k.setVisible(false);
//			h.setVisible(false);
		}
		if(timeCount3>970&&timeCount3<1396) {
			cool4.setBounds(0,timeCount3-1424,1300,476);
			cool5.setBounds(0,-timeCount3+1842,1300,476);
		}
		if(timeCount3>1500) {
			r.setVisible(true);
			setVisible(false);
		}
		if(timeCount3>1600) {
			logo.setVisible(true);
			moon.setBounds(150, 78, 128,128 );
			sun.setBounds(1000, 78, 128,128 );
			record.setVisible(true);
			start.setVisible(true);
			cool.setBounds(0,10,1300,950);
			cool2.setBounds(0,950,1300,950);
			cool5.setBounds(0,950,1300,476);
			cool4.setBounds(0,950,1300,476);
			title.setBounds(470, 173, 470,180 );
			setVisible(false);
			cool3.setBounds(0,950,1300,950);
//			k.setVisible(true);
//			h.setVisible(true);
			t3.stop();
			timeCount3=0;
			
		}
		}
		
	}
	
	
	
	class T2Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			timeCount2++;
//			if(timeCount2==1) {
//				try {
//					g = new GamePage();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			if(timeCount2>1) {
//				gp.setVisible(false);
//				nm.setVisible(false);
			}
			if(timeCount2>150) {
				logo.setVisible(false);
			}
			if(timeCount2>300) {
				record.setVisible(false);
			}
			if(timeCount2>450) {
				start.setVisible(false);
			}
			if(timeCount2>620&&timeCount2<950) {
				title.setBounds(470, -timeCount2+777, 470,180 );
			}
			if(timeCount2>620&&timeCount2<950) {
				sun.setBounds(1000, timeCount2*3-1700, 128,128 );
				moon.setBounds(150, timeCount2*3-1700, 128,128 );
//				k.setVisible(false);
//				h.setVisible(false);
				cool.setBounds(0,timeCount2*3-1860,1300,950);
			}
			if(timeCount2>970&&timeCount2<1920) {
				cool3.setBounds(0,0,1300,950);
				cool2.setBounds(0,-timeCount2+1950,1300,950);
			}
			if(timeCount2>1920&&timeCount2<1922) {
				try {
					g = new GamePage();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.setVisible(true);
//				t2.stop();
//				timeCount2=0;
			}
			if(timeCount2>2000) {
//				k.setVisible(true);
//				h.setVisible(true);
				logo.setVisible(true);
				moon.setBounds(150, 78, 128,128 );
				sun.setBounds(1000, 78, 128,128 );
				record.setVisible(true);
				start.setVisible(true);
				cool.setBounds(0,10,1300,950);
				cool2.setBounds(0,950,1300,950);
				title.setBounds(470, 173, 470,180 );
				setVisible(false);
				cool3.setBounds(0,950,1300,950);
				t2.stop();
				timeCount2=0;
				
			}
			
		}
	}

	
	class RankingListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			playMouse();
			t3.start();
//			start.setEnabled(false);
//			ranking.setEnabled(false);
		}
		
	}
	
	class SunListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			playMouse();
			clip.stop();
			if(getClip2()==null) {
				
				r.dispose();
				dispose();
			}
			else {
				getClip2().stop();
				Knowledge k = new Knowledge();
				r.dispose();
				dispose();
			}
			
		}
		
	}
	
	class MoonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			playMouse();
			clip.stop();
			if(getClip2()==null) {
				clip.stop();
				dispose();
				r.dispose();
				getCover().setVisible(true);
			}
			else {
				getClip2().stop();
				dispose();
				r.dispose();
				getCover().setVisible(true);
			}
		}
		
	}
	
	public Cover getCover() {
		for(Frame frame: JFrame.getFrames()) {
		if(frame.getTitle().equals("Financial Box")) {
			Cover mp = (Cover) frame;
			return mp;
		}
		}
		return null;
		}
	
///////////Mouse Listener
	
	class StartMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			start.setBackground(new Color(113,177,237));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			start.setBackground(new Color(184,207,229));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			start.setBackground(new Color(184,207,229));
		}
		
	}
	
	class RankingMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			record.setBackground(new Color(113,177,237));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			record.setBackground(new Color(184,207,229));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			record.setBackground(new Color(184,207,229));
		}
		
	}

	
	////////////////////////////////////
	
	
	
	class TitleMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			title.setForeground(new Color(247,177,137));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			title.setForeground(Color.black);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	//////////////////////////////////////renew

	
	public MainPage getMainPage() {
		return this;
	}
	
}

//start.addMouseListener(new java.awt.event.MouseAdapter() {
//
//	public void mouseClicked(java.awt.event.MouseEvent e) {
//
//	Runtime runtime = Runtime.getRuntime();
//
//	try {
//		Process process = runtime.exec("C:\\Program Files\\Internet Explorer\\IEXPLORE.EXE https://www.facebook.com/profile.php?id=100006726992151");
//	} catch (IOException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
//	}
//
//	});
