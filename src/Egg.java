import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

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
import javax.swing.JTextArea;
import javax.swing.Timer;

public class Egg extends JFrame {
	private Container ct;
	private BackgroundPanel bgp;
	private JButton eggReturn;
	private JTextArea egg;
	private JButton mail;
	private Timer t2;
	private int timeCount2;
	private int timeCount3;
	private Timer t3;
	private JLabel cool;
	private JLabel click;
	private Clip clip;
	
	public Egg() {
		setTitle("Congrats!");
//		playSound();
		createEggR();
		createMail();
		createEgg();
		createCool();
		createFont();
		createT2();
		createT3();
		
		ct = this.getContentPane();
		bgp = new BackgroundPanel(new ImageIcon(this.getClass().getResource("/resources/egg.png")).getImage());
		bgp.setBounds(0,0,1300,950);
		ct.add(bgp);
		ct.setVisible(true);
		setSize(1300,950);	
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
//	public static void playSound() {
//		
//		new Thread(new Runnable() {
//			  // The wrapper thread is unnecessary, unless it blocks on the
//			  // Clip finishing; see comments.
//			    public void run() {
//			      try(InputStream in = getClass().getResourceAsStream("/music/33.wav")) {	
//			    	  InputStream bufferedIn = new BufferedInputStream(in);
//			          try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
//			          Clip clip = AudioSystem.getClip();
//			          clip.open(audioIn);
//			          clip.start();
//			          clip.loop(Clip.LOOP_CONTINUOUSLY);
//				      Thread.sleep(1000000000);
//			      }
//			      } 
//			       catch (Exception e) {
//			        System.err.println(e.getMessage());
//			      }
//			    }
//			    
//			  }).start();
//	 }
	public void play() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		InputStream in = getClass().getResourceAsStream("/music/33.wav");
		InputStream bufferedIn = new BufferedInputStream(in);
        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
        clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);}
	}
	
	public void createMail() {
		click = new JLabel("You got a new MESSAGE!");
		click.setBounds(250, 200, 800, 90);
		click.setVisible(false);
		click.setFont(new Font("Bowhouse Regular", Font.PLAIN, 90));
		add(click);
		mail = new JButton();
		mail.setIcon(new ImageIcon(this.getClass().getResource("/resources/mail.png")));
		mail.setBackground(new Color(1,1,1,0));
		mail.setOpaque(false);
		mail.setBounds(394, 219, 512, 512);
		mail.setBorderPainted(false);
		mail.setVisible(false);
		Ml m = new Ml();
		mail.addActionListener(m);
		add(mail);
	}
	class Ml implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			click.setVisible(false);
			mail.setIcon(new ImageIcon(this.getClass().getResource("/resources/mail2.png")));
			t3.start();
			try {
				play();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	public void createCool() {
		Icon coolI = new ImageIcon(this.getClass().getResource("/resources/rainbow2.jpg"));
		cool = new JLabel(coolI);
		cool.setBounds(1300,0,1300,950);
		add(cool);
	}
	
	public void createEgg() {
		egg = new JTextArea();
		egg.setOpaque(false);
		egg.setBackground(new Color(1,1,1,1));
//		egg.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		egg.setBounds(300, 200, 800, 600);
		egg.setLineWrap(true);
		egg.setEditable(false);
		egg.setVisible(true);
		egg.setFont(new Font("High Summit",Font.PLAIN,50));
		add(egg);
	}
	
	public void createFont() {
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,this.getClass().getResourceAsStream("/fonts/Bowhouse-Regular.otf")));
        } catch (FontFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
		try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,this.getClass().getResourceAsStream("/fonts/Phonics-Bats-by-Phontz.ttf")));
        } catch (FontFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
	}
	
	public void createT2() {
		T2Listener a = new T2Listener();
		t2 = new Timer(1,a);
		timeCount2= 0;
		t2.start();
	}
	
	class T2Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			timeCount2++;
			if(timeCount2>100) {
				mail.setVisible(true);
			}
			if(timeCount2>350) {
				click.setVisible(true);
				t2.stop();
				timeCount2= 0;
			}
		}
		
	}
	
	public void createT3() {
		T3Listener a = new T3Listener();
		t3 = new Timer(1,a);
		timeCount3=-2181;
		
	}
	
	public void createEggR() {
		eggReturn = new JButton("h");
		eggReturn.setFont(new Font("Phonics-Bats-by-Phontz",Font.PLAIN,60));
		eggReturn.setBounds(550,660,200,80);
		eggReturn.setOpaque(false);
		eggReturn.setBorderPainted(false);
		eggReturn.setBackground(new Color(1,1,1,1));
		eggReturn.setVisible(false);
		El e = new El();
		eggReturn.addActionListener(e);
		add(eggReturn);
	}
	
	class El implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			dispose();
			MainPage m = new MainPage();
			clip.stop();
		}
		
	}
	
	public MainPage getMainPage() {
		for(Frame frame: JFrame.getFrames()) {
		if(frame.getTitle().equals("Main Page")) {
			return (MainPage)frame;
		}
		}
		return null;
		}
	
	class T3Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			timeCount3++;
			
			if(timeCount3>-2031&&timeCount3<-1290) {
				mail.setBounds(394, -timeCount3-1812, 512, 512);
			}
			
			if(timeCount3<0&&timeCount3>-1118) {
				cool.setBounds(timeCount3+182,125,936,700);
			}
			if(timeCount3<72&&timeCount3>70) {
				egg.append("  T");
			}
			if(timeCount3<142&&timeCount3>140) {
				egg.append("h");
			}
			if(timeCount3<212&&timeCount3>210) {
				egg.append("a");
			}
			if(timeCount3<282&&timeCount3>280) {
				egg.append("n");
			}
			if(timeCount3<352&&timeCount3>350) {
				egg.append("k");
			}
			if(timeCount3<422&&timeCount3>420) {
				egg.append(" ");
			}
			
			if(timeCount3<492&&timeCount3>490) {
				egg.append("y");
			}
			if(timeCount3<562&&timeCount3>560) {
				egg.append("o");
			}
			
			if(timeCount3<632&&timeCount3>630) {
				egg.append("u");
			}
			if(timeCount3<702&&timeCount3>700) {
				egg.append(" f");
			}
			
			if(timeCount3<772&&timeCount3>770) {
				egg.append("o");
			}
			if(timeCount3<852&&timeCount3>850) {
				egg.append("r");
			}
			if(timeCount3<922&&timeCount3>920) {
				egg.append(" p");
			}
			if(timeCount3<992&&timeCount3>990) {
				egg.append("l");
			}
			if(timeCount3<1062&&timeCount3>1060) {
				egg.append("a");
			}
			if(timeCount3<1132&&timeCount3>1130) {
				egg.append("y");
			}
			if(timeCount3<1202&&timeCount3>1200) {
				egg.append("i");
			}
			if(timeCount3<1272&&timeCount3>1270) {
				egg.append("n");
			}
			if(timeCount3<1342&&timeCount3>1340) {
				egg.append("g");
			}
			if(timeCount3<1412&&timeCount3>1410) {
				egg.append(" t");
			}
			if(timeCount3<1482&&timeCount3>1480) {
				egg.append("h");
			}
			if(timeCount3<1522&&timeCount3>1520) {
				egg.append("e");
			}
			if(timeCount3<1592&&timeCount3>1590) {
				egg.append(" g");
			}
			if(timeCount3<1662&&timeCount3>1660) {
				egg.append("a");
			}
			if(timeCount3<1732&&timeCount3>1730) {
				egg.append("m");
			}
			if(timeCount3<1802&&timeCount3>1800) {
				egg.append("e!"+"\n");
			}
			if(timeCount3<1872&&timeCount3>1870) {
				egg.append("H");
			}
			if(timeCount3<1942&&timeCount3>1940) {
				egg.append("o");
			}
			if(timeCount3<2012&&timeCount3>2010) {
				egg.append("n");
			}
			if(timeCount3<2082&&timeCount3>2080) {
				egg.append("e");
			}
			if(timeCount3<2152&&timeCount3>2150) {
				egg.append("s");
			}
			if(timeCount3<2222&&timeCount3>2220) {
				egg.append("t");
			}
			if(timeCount3<2292&&timeCount3>2290) {
				egg.append("l");
			}
			if(timeCount3<2362&&timeCount3>2360) {
				egg.append("y");
			}
			if(timeCount3<2432&&timeCount3>2430) {
				egg.append(" I");
			}
			if(timeCount3<2502&&timeCount3>2500) {
				egg.append(" D");
			}
			if(timeCount3<2572&&timeCount3>2570) {
				egg.append("o");
			}
			if(timeCount3<2642&&timeCount3>2640) {
				egg.append("n'");
			}
			if(timeCount3<2712&&timeCount3>2710) {
				egg.append("t");
			}
			if(timeCount3<2782&&timeCount3>2780) {
				egg.append("  e");
			}
			if(timeCount3<2852&&timeCount3>2850) {
				egg.append("x");
			}
			if(timeCount3<2922&&timeCount3>2920) {
				egg.append("p");
			}
			if(timeCount3<2992&&timeCount3>2990) {
				egg.append("e");
			}
			if(timeCount3<3062&&timeCount3>3060) {
				egg.append("c");
			}
			if(timeCount3<3132&&timeCount3>3130) {
				egg.append("t");
			}
			if(timeCount3<3202&&timeCount3>3200) {
				egg.append("  a");
			}
			if(timeCount3<3272&&timeCount3>3270) {
				egg.append("n");
			}
			if(timeCount3<3342&&timeCount3>3340) {
				egg.append("y");
			}
			if(timeCount3<3412&&timeCount3>3410) {
				egg.append("o");
			}
			if(timeCount3<3482&&timeCount3>3480) {
				egg.append("n");
			}
			if(timeCount3<3552&&timeCount3>3550) {
				egg.append("e");
			}
			if(timeCount3<3622&&timeCount3>3620) {
				egg.append("  c");
			}
			if(timeCount3<3692&&timeCount3>3690) {
				egg.append("a");
			}
			if(timeCount3<3762&&timeCount3>3760) {
				egg.append("n"+"\n");
			}
			if(timeCount3<3832&&timeCount3>3830) {
				egg.append("  g");
			}
			if(timeCount3<3902&&timeCount3>3900) {
				egg.append("e");
			}
			if(timeCount3<3972&&timeCount3>3970) {
				egg.append("t");
			}
			if(timeCount3<4042&&timeCount3>4040) {
				egg.append(" 100");
			}
			if(timeCount3<4112&&timeCount3>4110) {
				egg.append(" i");
			}
			if(timeCount3<4182&&timeCount3>4180) {
				egg.append("n");
			}
			if(timeCount3<4252&&timeCount3>4250) {
				egg.append("t");
			}
			if(timeCount3<4322&&timeCount3>4320) {
				egg.append("h");
			}
			if(timeCount3<4392&&timeCount3>4390) {
				egg.append("e");
			}
			if(timeCount3<4462&&timeCount3>4460) {
				egg.append(" g");
			}
			if(timeCount3<4532&&timeCount3>4530) {
				egg.append("a");
			}
			if(timeCount3<4602&&timeCount3>4600) {
				egg.append("m");
			}
			if(timeCount3<4672&&timeCount3>4670) {
				egg.append("e");
			}
			if(timeCount3<4742&&timeCount3>4740) {
				egg.append(" lol.");
			}
			if(timeCount3<4812&&timeCount3>4810) {
				egg.append(" y");
			}
			if(timeCount3<4882&&timeCount3>4880) {
				egg.append("o");
			}
			if(timeCount3<4952&&timeCount3>4950) {
				egg.append("u");
			}
			if(timeCount3<5022&&timeCount3>5020) {
				egg.append(" r");
			}
			if(timeCount3<5092&&timeCount3>5090) {
				egg.append("e");
			}
			if(timeCount3<5162&&timeCount3>5160) {
				egg.append("a");
			}
			if(timeCount3<5232&&timeCount3>5230) {
				egg.append("l");
			}
			if(timeCount3<5302&&timeCount3>5300) {
				egg.append("l");
			}
			if(timeCount3<5372&&timeCount3>5370) {
				egg.append("y"+"\n");
			}
			if(timeCount3<5442&&timeCount3>5440) {
				egg.append("       d");
			}
			if(timeCount3<5512&&timeCount3>5510) {
				egg.append("i");
			}
			if(timeCount3<5582&&timeCount3>5580) {
				egg.append("d");
			}
			if(timeCount3<5652&&timeCount3>5650) {
				egg.append(" a");
			}
			if(timeCount3<5722&&timeCount3>5720) {
				egg.append(" g");
			}
			if(timeCount3<5792&&timeCount3>5790) {
				egg.append("r");
			}
			if(timeCount3<5862&&timeCount3>5860) {
				egg.append("e");
			}
			if(timeCount3<5932&&timeCount3>5930) {
				egg.append("a");
			}
			if(timeCount3<6002&&timeCount3>6000) {
				egg.append("t");
			}
			if(timeCount3<6072&&timeCount3>6070) {
				egg.append("  j");
			}
			if(timeCount3<6142&&timeCount3>6140) {
				egg.append("o");
			}
			if(timeCount3<6212&&timeCount3>6210) {
				egg.append("b.");
			}
			if(timeCount3<6282&&timeCount3>6280) {
				egg.append(" W");
			}
			if(timeCount3<6352&&timeCount3>6350) {
				egg.append("e");
			}
			if(timeCount3<6422&&timeCount3>6420) {
				egg.append("l");
			}
			if(timeCount3<6512&&timeCount3>6510) {
				egg.append("l");
			}
			if(timeCount3<6582&&timeCount3>6580) {
				egg.append(" d");
			}
			if(timeCount3<6652&&timeCount3>6650) {
				egg.append("o");
			}
			if(timeCount3<6722&&timeCount3>6720) {
				egg.append("n");
			}
			if(timeCount3<6792&&timeCount3>6790) {
				egg.append("e!"+"\n");
			}
			if(timeCount3<6862&&timeCount3>6860) {
				egg.append("        H");
			}
			if(timeCount3<6932&&timeCount3>6930) {
				egg.append("o");
			}	
			if(timeCount3<7002&&timeCount3>7000) {
				egg.append("p");
			}	
			if(timeCount3<7072&&timeCount3>7070) {
				egg.append("e");
			}	
			if(timeCount3<7142&&timeCount3>7140) {
				egg.append(" y");
			}	
			if(timeCount3<7212&&timeCount3>7210) {
				egg.append("o");
			}	
			if(timeCount3<7282&&timeCount3>7280) {
				egg.append("u");
			}	
			if(timeCount3<7352&&timeCount3>7350) {
				egg.append(" e");
			}	
			if(timeCount3<7422&&timeCount3>7420) {
				egg.append("n");
			}	
			if(timeCount3<7492&&timeCount3>7490) {
				egg.append("j");
			}	
			if(timeCount3<7562&&timeCount3>7560) {
				egg.append("o");
			}	
			if(timeCount3<7632&&timeCount3>7630) {
				egg.append("y");
			}
			if(timeCount3<7702&&timeCount3>7700) {
				egg.append(" o");
			}
			if(timeCount3<7772&&timeCount3>7770) {
				egg.append("u");
			}
			if(timeCount3<7842&&timeCount3>7840) {
				egg.append("r");
			}
			if(timeCount3<7912&&timeCount3>7910) {
				egg.append(" p");
			}
			if(timeCount3<7982&&timeCount3>7980) {
				egg.append("r");
			}
			if(timeCount3<8052&&timeCount3>8050) {
				egg.append("o");
			}
			if(timeCount3<8122&&timeCount3>8120) {
				egg.append("j");
			}
			if(timeCount3<8192&&timeCount3>8190) {
				egg.append("e");
			}
			if(timeCount3<8262&&timeCount3>8260) {
				egg.append("c");
			}
			if(timeCount3<8332&&timeCount3>8330) {
				egg.append("t."+"\n");
			}
			if(timeCount3<8402&&timeCount3>8400) {
				egg.append("   G");
			}
			if(timeCount3<8472&&timeCount3>8470) {
				egg.append("a");
			}
			if(timeCount3<8542&&timeCount3>8540) {
				egg.append("m");
			}
			if(timeCount3<8612&&timeCount3>8610) {
				egg.append("e");
			}
			if(timeCount3<8682&&timeCount3>8680) {
				egg.append(" P");
			}
			if(timeCount3<8752&&timeCount3>8750) {
				egg.append("r");
			}
			if(timeCount3<8822&&timeCount3>8820) {
				egg.append("o");
			}
			if(timeCount3<8892&&timeCount3>8890) {
				egg.append("d");
			}
			if(timeCount3<8692&&timeCount3>8960) {
				egg.append("u");
			}
			if(timeCount3<9032&&timeCount3>9030) {
				egg.append("c");
			}
			if(timeCount3<9102&&timeCount3>9100) {
				egg.append("e");
			}
			if(timeCount3<9172&&timeCount3>9170) {
				egg.append("d");
			}
			if(timeCount3<9242&&timeCount3>9240) {
				egg.append(" b");
			}
			if(timeCount3<9312&&timeCount3>9310) {
				egg.append("y");
			}
			if(timeCount3<9382&&timeCount3>9380) {
				egg.append(" A");
			}
			if(timeCount3<9452&&timeCount3>9450) {
				egg.append("r");
			}
			if(timeCount3<9522&&timeCount3>9520) {
				egg.append("t");
			}
			if(timeCount3<9592&&timeCount3>9590) {
				egg.append("h");
			}
			if(timeCount3<9662&&timeCount3>9660) {
				egg.append("u");
			}
			if(timeCount3<9732&&timeCount3>9730) {
				egg.append("r");
			}
			if(timeCount3<9802&&timeCount3>9800) {
				egg.append(" C");
			}
			if(timeCount3<9872&&timeCount3>9870) {
				egg.append("h");
			}
			if(timeCount3<9942&&timeCount3>9940) {
				egg.append("i");
			}
			if(timeCount3<10012&&timeCount3>10010) {
				egg.append("e");
			}
			if(timeCount3<10082&&timeCount3>10080) {
				egg.append("n");
			}
			
			if(timeCount3>10150) {
				eggReturn.setVisible(true);
			}
			if(timeCount3>10200) {
				t3.stop();
			}
		}
		
	}
}
