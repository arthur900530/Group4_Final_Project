import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;


	public class MainFrame extends JFrame{
	 
	 private News news;
	 private JLabel title,monitor,r;
	 private JTextArea newsArea0,newsArea1,newsArea2,newsArea3,newsArea4,newsArea5,newsArea6,newsArea7,newsArea8,newsArea9,newsArea10,newsArea11;
	 private JButton rButton,b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11;
	 private JLabel c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,paper,breaking ;
	 private JTextArea moreNews;
	 private JScrollPane scroll;
	 private Container ct;
	 private BackgroundPanel bgp;
	 private JPanel p;
	 private Timer t2;
	 private int timeCount2;
	 
	 public MainFrame() {
		  
		
		  setTitle("News");
		  setSize(1300,950);
		  moreNews=new JTextArea();
		  
		  moreNews.setBounds(0,0,850,500);
		  moreNews.setLineWrap(true);
		  moreNews.setEditable(false);
		  moreNews.setFont(new Font("微軟正黑體 Light",Font.PLAIN,25));
		  moreNews.setVisible(false);
		  scroll=new JScrollPane(moreNews);
		  scroll.setBounds(223,140,850,500);
		  scroll.setVisible(false);
		  scroll.setViewportView(moreNews);
		  scroll.setBorder(null);
		  add(scroll);
		  
		  createR();
		  createReturn();
		  createTitle();
		  createNewsArea();
		  createSeemoreBtn();
		  createT2();
		  createMonitor();
		  createLens();
		  createPaper();
		  ct = this.getContentPane();
		  bgp = new BackgroundPanel((new ImageIcon(this.getClass().getResource("resources/newsBG.png")).getImage()));
		  bgp.setBounds(0,0,1300,950);
		  ct.add(bgp);
		  ct.setVisible(true);	
		  setResizable(false);
		  setVisible(true);
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
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
	 public void createMonitor() {
		 Icon i = new ImageIcon(getClass().getResource("/resources/monitor2.png"));
		 monitor = new JLabel(i);
		 monitor.setBounds(138, 70, 1023, 772);
		 monitor.setVisible(false);
		 add(monitor);
	 }
	 
	 public void createR() {
		 r = new JLabel("       ");
		 RMouse m = new RMouse();
//		 r.setIcon(new ImageIcon(getClass().getResource("/resources/back.png")));
		 r.addMouseListener(m);
		 r.setBounds(586,700,128,128);
//		 r.setOpaque(false);
		 r.setBackground(Color.white);
		 r.setVisible(false);
		 add(r);
	 }
	 
	 class RMouse implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			playMouse();
			rButton.setVisible(true);
			moreNews.setVisible(false);
			scroll.setVisible(false);
			monitor.setVisible(false);
			newsArea0.setVisible(true);
			newsArea1.setVisible(true);
			newsArea2.setVisible(true);
			newsArea3.setVisible(true);
			newsArea4.setVisible(true);
			newsArea5.setVisible(true);
			newsArea6.setVisible(true);
			newsArea7.setVisible(true);
			newsArea8.setVisible(true);
			newsArea9.setVisible(true);
			newsArea10.setVisible(true);
			newsArea11.setVisible(true);
			b0.setVisible(true);
			b1.setVisible(true);
			b2.setVisible(true);
			b3.setVisible(true);
			b4.setVisible(true);
			b5.setVisible(true);
			b6.setVisible(true);
			b7.setVisible(true);
			b8.setVisible(true);
			b9.setVisible(true);
			b10.setVisible(true);
			b11.setVisible(true);
			r.setVisible(false);
			breaking.setVisible(true);
			paper.setVisible(true);
			title.setVisible(true);
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
	 class RListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			playMouse();
			moreNews.setVisible(false);
			scroll.setVisible(false);
			
			monitor.setVisible(false);
			newsArea0.setVisible(true);
			newsArea1.setVisible(true);
			newsArea2.setVisible(true);
			newsArea3.setVisible(true);
			newsArea4.setVisible(true);
			newsArea5.setVisible(true);
			newsArea6.setVisible(true);
			newsArea7.setVisible(true);
			newsArea8.setVisible(true);
			newsArea9.setVisible(true);
			newsArea10.setVisible(true);
			newsArea11.setVisible(true);
			b0.setVisible(true);
			b1.setVisible(true);
			b2.setVisible(true);
			b3.setVisible(true);
			b4.setVisible(true);
			b5.setVisible(true);
			b6.setVisible(true);
			b7.setVisible(true);
			b8.setVisible(true);
			b9.setVisible(true);
			b10.setVisible(true);
			b11.setVisible(true);
			r.setVisible(false);
			title.setVisible(true);
		}
		 
	 }
	 public void createReturn() {
			
			rButton = new JButton();
			rButton.setIcon(new ImageIcon(getClass().getResource("/resources/return_arrow.png")));
			rButton.setBackground(new Color(1,1,1,0));
			rButton.setOpaque(false);
			rButton.setBounds(75, 22, 100, 100);
			rButton.setBorderPainted(false);
			ReturnListener r = new ReturnListener();
			rButton.addActionListener(r);
			add(rButton);
			
		}
		
		class ReturnListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				t2.start();
				playMouse();
			}
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
//					rButton.setBounds(-timeCount2+175, 70, 100, 100);
					rButton.setBounds(-timeCount2+75, 22, 100, 100);
				}
				if(timeCount2>175) {
					t2.stop();
					getCover().setVisible(true);
					dispose();
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
	 
		 public void createTitle() {
			  title=new JLabel("News",SwingConstants.CENTER);
			  title.setFont(new Font("Futurist Fixed-width",Font.PLAIN,100));
			  title.setBounds(450,0,400,120);
			  add(title);
		 }
		 public void createNewsArea() {
			   news=new News();
		
			   int x = 212;
			   newsArea0=new JTextArea();
			   newsArea0.setFont(new Font("微軟正黑體 Light",Font.PLAIN,25));
			   newsArea0.append(news.getNews().get(0));
			   newsArea0.setLineWrap(false);
			   newsArea0.setEditable(false);
			   newsArea0.setBounds(x,200,852,35);
			   newsArea0.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			   newsArea0.setOpaque(false);
			   add(newsArea0);
			   newsArea1=new JTextArea();
			   newsArea1.setFont(new Font("微軟正黑體 Light",Font.PLAIN,25));
			   newsArea1.append(news.getNews().get(1));
			   newsArea1.setLineWrap(false);
			   newsArea1.setEditable(false);
			   newsArea1.setBounds(x,250,852,35);
			   newsArea1.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			   newsArea1.setOpaque(false);
			   add(newsArea1);
			   newsArea2=new JTextArea();
			   newsArea2.setFont(new Font("微軟正黑體 Light",Font.PLAIN,25));
			   newsArea2.append(news.getNews().get(2));
			   newsArea2.setLineWrap(false);
			   newsArea2.setEditable(false);
			   newsArea2.setBounds(x,300,852,35);
			   newsArea2.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			   newsArea2.setOpaque(false);
			   add(newsArea2);
			   newsArea3=new JTextArea();
			   newsArea3.setFont(new Font("微軟正黑體 Light",Font.PLAIN,25));
			   newsArea3.append(news.getNews().get(3));
			   newsArea3.setLineWrap(false);
			   newsArea3.setEditable(false);
			   newsArea3.setBounds(x,350,852,35);
			   newsArea3.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			   newsArea3.setOpaque(false);
			   add(newsArea3);
			   newsArea4=new JTextArea();
			   newsArea4.setFont(new Font("微軟正黑體 Light",Font.PLAIN,25));
			   newsArea4.append(news.getNews().get(4));
			   newsArea4.setLineWrap(false);
			   newsArea4.setEditable(false);
			   newsArea4.setBounds(x,400,852,35);
			   newsArea4.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			   newsArea4.setOpaque(false);
			   add(newsArea4);
			   newsArea5=new JTextArea();
			   newsArea5.setFont(new Font("微軟正黑體 Light",Font.PLAIN,25));
			   newsArea5.append(news.getNews().get(5));
			   newsArea5.setLineWrap(false);
			   newsArea5.setEditable(false);
			   newsArea5.setBounds(x,450,852,35);
			   newsArea5.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			   newsArea5.setOpaque(false);
			   add(newsArea5);
			   newsArea6=new JTextArea();
			   newsArea6.setFont(new Font("微軟正黑體 Light",Font.PLAIN,25));
			   newsArea6.append(news.getNews().get(6));
			   newsArea6.setLineWrap(false);
			   newsArea6.setEditable(false);
			   newsArea6.setBounds(x,500,852,35);
			   newsArea6.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			   newsArea6.setOpaque(false);
			   add(newsArea6);
			   newsArea7=new JTextArea();
			   newsArea7.setFont(new Font("微軟正黑體 Light",Font.PLAIN,25));
			   newsArea7.append(news.getNews().get(7));
			   newsArea7.setLineWrap(false);
			   newsArea7.setEditable(false);
			   newsArea7.setBounds(x,550,852,35);
			   newsArea7.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			   newsArea7.setOpaque(false);
			   add(newsArea7);
			   newsArea8=new JTextArea();
			   newsArea8.setFont(new Font("微軟正黑體 Light",Font.PLAIN,25));
			   newsArea8.append(news.getNews().get(8));
			   newsArea8.setLineWrap(false);
			   newsArea8.setEditable(false);
			   newsArea8.setBounds(x,600,852,35);
			   newsArea8.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			   newsArea8.setOpaque(false);
			   add(newsArea8);
			   newsArea9=new JTextArea();
			   newsArea9.setFont(new Font("微軟正黑體 Light",Font.PLAIN,25));
			   newsArea9.append(news.getNews().get(9));
			   newsArea9.setLineWrap(false);
			   newsArea9.setEditable(false);
			   newsArea9.setBounds(x,650,852,35);
			   newsArea9.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			   newsArea9.setOpaque(false);
			   add(newsArea9);
			   newsArea10=new JTextArea();
			   newsArea10.setFont(new Font("微軟正黑體 Light",Font.PLAIN,25));
			   newsArea10.append(news.getNews().get(10));
			   newsArea10.setLineWrap(false);
			   newsArea10.setEditable(false);
			   newsArea10.setBounds(x,700,852,35);
			   newsArea10.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			   newsArea10.setOpaque(false);
			   add(newsArea10);
			   newsArea11=new JTextArea();
			   newsArea11.setFont(new Font("微軟正黑體 Light",Font.PLAIN,25));
			   newsArea11.append(news.getNews().get(11));
			   newsArea11.setLineWrap(false);
			   newsArea11.setEditable(false);
			   newsArea11.setBounds(x,750,852,35);
			   newsArea11.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			   newsArea11.setOpaque(false);
			   add(newsArea11);
			   //return area;
		 }
		 public void createPaper() {
			 Icon p = new ImageIcon(getClass().getResource("/resources/paper.png"));
			 paper = new JLabel(p);
			 paper.setBounds(140, 120, 1000, 754);
			 add(paper);
		 }
		 public void createLens() {
			 breaking = new JLabel("Breaking News");
			 breaking.setForeground(Color.white);
			 breaking.setFont(new Font("Futurist Fixed-width",Font.PLAIN,30));
			 breaking.setBounds(240, 115, 500, 80);
			 add(breaking);
		 }
		 public void createSeemoreBtn() {
		
			 class SeemoreListener implements ActionListener{
		
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						title.setVisible(false);
						breaking.setVisible(false);
						paper.setVisible(false);
						rButton.setVisible(false);
						newsArea0.setVisible(false);
						newsArea1.setVisible(false);
						newsArea2.setVisible(false);
						newsArea3.setVisible(false);
						newsArea4.setVisible(false);
						newsArea5.setVisible(false);
						newsArea6.setVisible(false);
						newsArea7.setVisible(false);
						newsArea8.setVisible(false);
						newsArea9.setVisible(false);
						newsArea10.setVisible(false);
						newsArea11.setVisible(false);
						b0.setVisible(false);
						b1.setVisible(false);
						b2.setVisible(false);
						b3.setVisible(false);
						b4.setVisible(false);
						b5.setVisible(false);
						b6.setVisible(false);
						b7.setVisible(false);
						b8.setVisible(false);
						b9.setVisible(false);
						b10.setVisible(false);
						b11.setVisible(false);
						r.setVisible(true);
						monitor.setVisible(true);
						playMouse();
					}
				  }
			 class ShowNewsListener0 implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					scroll.setVisible(true);
					moreNews.setVisible(true);
					moreNews.setText(news.getNews().get(0));
					moreNews.setCaretPosition(0);
				}
				 
			 }
			 ActionListener seemoreListener=new SeemoreListener();
			  b0=new JButton("");
			  b0.setIcon(new ImageIcon(getClass().getResource("/resources/stop.png")));
			  b0.setBounds(1088,200,32,32);
			  b0.setOpaque(false);
			  b0.setBackground(Color.white);
			  b0.setBorderPainted(false);
			  ActionListener show0=new ShowNewsListener0();
			  b0.addActionListener(seemoreListener);
			  b0.addActionListener(show0);
			  add(b0);
			  class ShowNewsListener1 implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						scroll.setVisible(true);
						moreNews.setVisible(true);
						moreNews.setText(news.getNews().get(1));
						moreNews.setCaretPosition(0);
					}
					 
				 }
			  b1=new JButton();
			  b1.setIcon(new ImageIcon(getClass().getResource("/resources/stop.png")));
			  b1.setBounds(1088,250,32,32);
			  b1.setOpaque(false);
			  b1.setBackground(Color.white);
			  b1.setBorderPainted(false);
			  ActionListener show1=new ShowNewsListener1();
			  b1.addActionListener(seemoreListener);
			  b1.addActionListener(show1);
			  add(b1);
			  class ShowNewsListener2 implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						scroll.setVisible(true);
						moreNews.setVisible(true);
						moreNews.setText(news.getNews().get(2));
						moreNews.setCaretPosition(0);
					}
					 
				 }
			  b2=new JButton("");
			  b2.setIcon(new ImageIcon(getClass().getResource("/resources/stop.png")));
			  b2.setBounds(1088,300,32,32);
			  b2.setOpaque(false);
			  b2.setBackground(Color.white);
			  b2.setBorderPainted(false);
			  ActionListener show2=new ShowNewsListener2();
			  b2.addActionListener(seemoreListener);
			  b2.addActionListener(show2);
			  add(b2);
			  class ShowNewsListener3 implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						scroll.setVisible(true);
						moreNews.setVisible(true);
						moreNews.setText(news.getNews().get(3));
						moreNews.setCaretPosition(0);
					}
					 
				 }
			  b3=new JButton("");
			  b3.setIcon(new ImageIcon(getClass().getResource("/resources/stop.png")));
			  b3.setBounds(1088,350,32,32);
			  b3.setOpaque(false);
			  b3.setBackground(Color.white);
			  b3.setBorderPainted(false);
			  ActionListener show3=new ShowNewsListener3();
			  b3.addActionListener(seemoreListener);
			  b3.addActionListener(show3);
			  add(b3);
			  class ShowNewsListener4 implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						scroll.setVisible(true);
						moreNews.setVisible(true);
						moreNews.setText(news.getNews().get(4));
						moreNews.setCaretPosition(0);
					}
					 
				 }
			  b4=new JButton("");
			  b4.setIcon(new ImageIcon(getClass().getResource("/resources/stop.png")));
			  b4.setBounds(1088,400,32,32);
			  b4.setOpaque(false);
			  b4.setBackground(Color.white);
			  b4.setBorderPainted(false);
			  ActionListener show4=new ShowNewsListener4();
			  b4.addActionListener(seemoreListener);
			  b4.addActionListener(show4);
			  add(b4);
			  class ShowNewsListener5 implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						scroll.setVisible(true);
						moreNews.setVisible(true);
						moreNews.setText(news.getNews().get(5));
						moreNews.setCaretPosition(0);
					}
					 
				 }
			  b5=new JButton("");
			  b5.setIcon(new ImageIcon(getClass().getResource("/resources/stop.png")));
			  b5.setBounds(1088,450,32,32);
			  b5.setOpaque(false);
			  b5.setBackground(Color.white);
			  b5.setBorderPainted(false);
			  ActionListener show5=new ShowNewsListener5();
			  b5.addActionListener(seemoreListener);
			  b5.addActionListener(show5);
			  add(b5);
			  class ShowNewsListener6 implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						scroll.setVisible(true);
						moreNews.setVisible(true);
						moreNews.setText(news.getNews().get(6));
						moreNews.setCaretPosition(0);
					}
					 
				 }
			  b6=new JButton("");
			  b6.setIcon(new ImageIcon(getClass().getResource("/resources/stop.png")));
			  b6.setBounds(1088,500,32,32);
			  b6.setOpaque(false);
			  b6.setBackground(Color.white);
			  b6.setBorderPainted(false);
			  ActionListener show6=new ShowNewsListener6();
			  b6.addActionListener(seemoreListener);
			  b6.addActionListener(show6);
			  add(b6);
			  class ShowNewsListener7 implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						scroll.setVisible(true);
						moreNews.setVisible(true);
						moreNews.setText(news.getNews().get(7));
						moreNews.setCaretPosition(0);
					}
					 
				 }
			  b7=new JButton("");
			  b7.setIcon(new ImageIcon(getClass().getResource("/resources/stop.png")));
			  b7.setBounds(1088,550,32,32);
			  b7.setOpaque(false);
			  b7.setBackground(Color.white);
			  b7.setBorderPainted(false);
			  ActionListener show7=new ShowNewsListener7();
			  b7.addActionListener(seemoreListener);
			  b7.addActionListener(show7);
			  add(b7);
			  class ShowNewsListener8 implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						scroll.setVisible(true);
						moreNews.setVisible(true);
						moreNews.setText(news.getNews().get(8));
						moreNews.setCaretPosition(0);
					}
					 
				 }
			  b8=new JButton("");
			  b8.setIcon(new ImageIcon(getClass().getResource("/resources/stop.png")));
			  b8.setBounds(1088,600,32,32);
			  b8.setOpaque(false);
			  b8.setBackground(Color.white);
			  b8.setBorderPainted(false);
			  ActionListener show8=new ShowNewsListener8();
			  b8.addActionListener(seemoreListener);
			  b8.addActionListener(show8);
			  add(b8);
			  class ShowNewsListener9 implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						scroll.setVisible(true);
						moreNews.setVisible(true);
						moreNews.setText(news.getNews().get(9));
						moreNews.setCaretPosition(0);
					}
					 
				 }
			  b9=new JButton("");
			  b9.setIcon(new ImageIcon(getClass().getResource("/resources/stop.png")));
			  b9.setBounds(1088,650,32,32);
			  b9.setOpaque(false);
			  b9.setBackground(Color.white);
			  b9.setBorderPainted(false);
			  ActionListener show9=new ShowNewsListener9();
			  b9.addActionListener(seemoreListener);
			  b9.addActionListener(show9);
			  add(b9);
			  class ShowNewsListener10 implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						scroll.setVisible(true);
						moreNews.setVisible(true);
						moreNews.setText(news.getNews().get(10));
						moreNews.setCaretPosition(0);
					}
					 
				 }
			  b10=new JButton("");
			  b10.setIcon(new ImageIcon(getClass().getResource("/resources/stop.png")));
			  b10.setBounds(1088,700,32,32);
			  b10.setOpaque(false);
			  b10.setBackground(Color.white);
			  b10.setBorderPainted(false);
			  ActionListener show10=new ShowNewsListener10();
			  b10.addActionListener(seemoreListener);
			  b10.addActionListener(show10);
			  add(b10);
			  class ShowNewsListener11 implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						scroll.setVisible(true);
						moreNews.setVisible(true);
						moreNews.setText(news.getNews().get(11));
						moreNews.setCaretPosition(0);
					}
					 
				 }
			  b11=new JButton("");
			  b11.setIcon(new ImageIcon(getClass().getResource("/resources/stop.png")));
			  b11.setBounds(1088,750,32,32);
			  b11.setOpaque(false);
			  b11.setBackground(Color.white);
			  b11.setBorderPainted(false);
			  ActionListener show11=new ShowNewsListener11();
			  b11.addActionListener(seemoreListener);
			  b11.addActionListener(show11);
			  add(b11);
			 
		  }
	 }