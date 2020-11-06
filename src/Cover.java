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
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

public class Cover extends JFrame {
	private Container ct;
	private BackgroundPanel bgp;
	private JLabel title;
	private JLabel title2;
	private JLabel l;
	private JLabel sm;
	private JButton g1;
	private JButton g2;
	private JButton k;
	private JButton n;
	private JLabel nl;
	private JLabel kl;
	private JLabel g1l;
	private JLabel g2l;
	private JLabel big;
	private Icon i0;
	private Icon i1;
	private Icon i2;
	private Icon i3;
	private Icon i4;
	private Clip clip;
	
	public Cover() {
//		try {
//			play();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedAudioFileException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (LineUnavailableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		createFont();
		createBig();
		createLabel();
		createButton();
		createTitle();
		setTitle("Financial Box");
		ct = this.getContentPane();
		bgp = new BackgroundPanel((new ImageIcon(MainPage.class.getResource("resources/0001.png")).getImage()));
		bgp.setBounds(0,0,1300,950);
		ct.add(bgp);
		ct.setVisible(true);
		
		setSize(1300,950);	
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
//	public void play() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
//		InputStream in = getClass().getResourceAsStream("/music/j1.wav");
//		InputStream bufferedIn = new BufferedInputStream(in);
//        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
//        clip = AudioSystem.getClip();
//        clip.open(audioIn);
//        clip.start();
//        clip.loop(Clip.LOOP_CONTINUOUSLY);}
//	}
	
	public void createTitle() {
		Icon line = new ImageIcon(getClass().getResource("/resources/l.png"));
		l = new JLabel(line);
		l.setBounds(100, 140, 763, 113);
		add(l);
		title = new JLabel("FINANCIAL BOX");
		title.setForeground(Color.black);
		title.setFont(new Font("Futurist Fixed-width", Font.BOLD, 50));
		title.setBounds(150, 60, 700, 80);
		TitleListener t = new TitleListener();
		title.addMouseListener(t);
		getContentPane().add(title);
		title2 = new JLabel("GROUP 4");
		title2.setForeground(Color.LIGHT_GRAY);
		title2.setFont(new Font("Futurist Fixed-width", Font.BOLD, 35));
		title2.setBounds(850, 160, 700, 80);
		getContentPane().add(title2);
	}
	
	public void createFont() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,this.getClass().getResourceAsStream("/fonts/FUTRFW.otf")));
        } catch (FontFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,this.getClass().getResourceAsStream("/fonts/Beon-Regular.otf")));
        } catch (FontFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
  
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,this.getClass().getResourceAsStream("/fonts/AGENCYB.ttf")));
        } catch (FontFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,this.getClass().getResourceAsStream("/fonts/MSJHL.ttc")));
        } catch (FontFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,this.getClass().getResourceAsStream("/fonts/SetoFont Handwriting Chinese Font -Simplified Chinese Fonts.ttf")));
        } catch (FontFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
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
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,this.getClass().getResourceAsStream("/fonts/High Summit.otf")));
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
	
	public void createButton() {
		g1 = new JButton("FinTest");
		g1.setFont(new Font("Futurist Fixed-width", Font.PLAIN, 30));
		g1.setBackground(new Color(100,196,195));
		g1.setBounds(370, 650, 280, 180);
		G1Listener c = new G1Listener();
		g1.addMouseListener(c);
		FintestListener f = new FintestListener();
		g1.addActionListener(f);
		getContentPane().add(g1);
		
		g2 = new JButton("P.O.C");
		g2.setFont(new Font("Futurist Fixed-width", Font.PLAIN, 30));
		g2.setBackground(new Color(81,147,179));
		g2.setBounds(90, 650, 280, 180);
		G2Listener d = new G2Listener();
		g2.addMouseListener(d);
		POCListener pl = new POCListener();
		g2.addActionListener(pl);
		getContentPane().add(g2);
		
		k = new JButton("KNOWLEDGE");
		k.setFont(new Font("Futurist Fixed-width", Font.PLAIN, 26));
//		k.setIcon(new ImageIcon(MainPage.class.getResource("/resources/k.png")));
		k.setBackground(new Color(248,212,154));
//		k.setOpaque(false);
//		k.setBorderPainted(false);
		k.setBounds(650, 650, 280, 180);
		KListener b = new KListener();
		k.addMouseListener(b);
		KnowledgeListener kn = new KnowledgeListener();
		k.addActionListener(kn);
		getContentPane().add(k);
		
		n = new JButton("News");
		n.setFont(new Font("Futurist Fixed-width", Font.PLAIN, 30));
		n.setBackground(new Color(247,230,202));
		n.setBounds(930, 650, 280, 180);
		NListener a = new NListener();
		n.addMouseListener(a);
		NewsListener nl = new NewsListener();
		n.addActionListener(nl);
		getContentPane().add(n);
	}
	
	public void createLabel() {
		Icon i1 = new ImageIcon(this.getClass().getResource("/resources/news2.png"));
		nl = new JLabel(i1);
		nl.setBounds(1035, 550, 80, 80);
		add(nl);
		Icon i2 = new ImageIcon(this.getClass().getResource("/resources/k2.png"));
		kl = new JLabel(i2);
		kl.setBounds(755, 550, 80, 80);
		add(kl);
		Icon i3 = new ImageIcon(this.getClass().getResource("/resources/fin2.png"));
		g1l = new JLabel(i3);
		g1l.setBounds(475, 550, 80, 80);
		add(g1l);
		Icon i4 = new ImageIcon(this.getClass().getResource("/resources/poc2.png"));
		g2l = new JLabel(i4);
		g2l.setBounds(195, 550, 80, 80);
		add(g2l);
	}
	public void createBig() {
		i1 = new ImageIcon(this.getClass().getResource("/resources/b1.png"));
		i2 = new ImageIcon(this.getClass().getResource("/resources/b2.png"));
		i3 = new ImageIcon(this.getClass().getResource("/resources/b3.png"));
		i4 = new ImageIcon(this.getClass().getResource("/resources/b4.png"));
		i0 = new ImageIcon(this.getClass().getResource("/resources/market.png"));
		big = new JLabel(i0);
		big.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
		big.setBounds(522, 220, 256, 256);
		add(big);
	}
	/////////////////////ML
	class NListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			playBubble();
			nl.setVisible(false);
			big.setIcon(i2);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			nl.setVisible(true);
			big.setIcon(i0);
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
	
	class KListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			playBubble();
			kl.setVisible(false);
			big.setIcon(i3);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			kl.setVisible(true);
			big.setIcon(i0);
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
	
	class G1Listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			playBubble();
			g1l.setVisible(false);
			big.setIcon(i4);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			g1l.setVisible(true);
			big.setIcon(i0);
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
	
	class G2Listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			playBubble();
			g2l.setVisible(false);
			big.setIcon(i1);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			g2l.setVisible(true);
			big.setIcon(i0);
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

	class TitleListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			title.setForeground(new Color(0, 204, 153));
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

	/////////////////////AL
	class NewsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			playMouse();
			MainFrame m = new MainFrame();
			setVisible(false);
		}
		
	}
	class POCListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			playMouse();
			try {
				POCFrame p = new POCFrame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setVisible(false);
		}
		
	}
	class FintestListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			playMouse();
			MainPage m = new MainPage();
			setVisible(false);
		}
		
	}
	
	class KnowledgeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			playMouse();
			Knowledge k = new Knowledge();
			setVisible(false);
		}
		
	}
	/////////////////////play
	public static void playBubble() {
			
			new Thread(new Runnable() {
				  // The wrapper thread is unnecessary, unless it blocks on the
				  // Clip finishing; see comments.
				    public void run() {
				      try(InputStream in = getClass().getResourceAsStream("/music/bubble.wav")) {	
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
	public Cover getCover() {
		for(Frame frame: JFrame.getFrames()) {
		if(frame.getTitle().equals("Financial Box")) {
			Cover mp = (Cover) frame;
			return mp;
		}
		}
		return null;
		}
}