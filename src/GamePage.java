import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

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
import javax.swing.JTextArea;
import javax.swing.Timer;

public class GamePage extends JFrame {
	private JLabel l1;
	private JLabel tl;
	private JLabel s;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton rButton;
	private JButton sb;
	private JTextArea q;
	private JTextArea cd;
	private JTextArea sc;
	private Container ct;
	private BackgroundPanel bgp;
	private Timer t;
	private Timer t2;
	private TimerListener timerlistener = new TimerListener();
	private StartListener startListener = new StartListener();
	private int questionNum = 0;
	private int timerCount = 0;
	private int timeCount2 = 0;
	private ArrayList<Question> questionList;
	private int i;
	private JLabel b1check;
	private JLabel b2check;
	private JLabel b3check;
	private JLabel b4check;
	private String rightAnswer;
	private int a = 5;
	private ArrayList<Integer> list;
	
	Connection conn = null;
	
	
	public GamePage() throws SQLException {
		
		setTitle("Financial Quiz");
		createQnum();
		createQuestionList();
		createButton();
		createLabel();
		createReturn();
		createTextArea();
		createCountDown();
		createScore();
		createT2();

		t = new Timer(1000, timerlistener);
		
		ct = this.getContentPane();
		bgp = new BackgroundPanel(new ImageIcon(this.getClass().getResource("/resources/bbgg3.png")).getImage());
		bgp.setBounds(0,0,1300,950);
		ct.add(bgp);
		ct.setVisible(true);
		setSize(1300,950);	
		setResizable(false);
		
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void createQnum() {
		list = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
	    for (int i=1; i<21; i++) {
	        list2.add(new Integer(i));
	    }
	    Collections.shuffle(list2);
	    for (int i=0; i<5; i++) {
	        list.add(list2.get(i));
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
			if(timeCount2>0&&timeCount2<275) {
				rButton.setBounds(-timeCount2+175, 70, 100, 100);
			}
			if(timeCount2>275) {
				t2.stop();
				getMainPage().setVisible(true);
				dispose();
				timeCount2 = 0;
			}
		}
		
	}
	
	/////////////////////TextArea
	
	public void createTextArea() {
		
		q = new JTextArea(20,10);
		q.setBounds(275,200,750,200);
		q.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 35));
		q.setForeground(Color.black);
		q.setEditable(false);
		q.setOpaque(false);
		q.setText("Press the Button to Start !");
		q.setLineWrap(true);
//		q.setBackground(new Color(1,1,1,20));
		getContentPane().add(q);
		
	}
	
	public JTextArea getScore() {
		return sc;
	}
	
/////////////////////Button
	
	public void createButton() {
		
		b1 = new JButton("Answer 1");
		b2 = new JButton("Answer 2");
		b3 = new JButton("Answer 3");
		b4 = new JButton("Answer 4");
		
		b1check = new JLabel();
		b2check = new JLabel();
		b3check = new JLabel();
		b4check = new JLabel();
		
		b1.setEnabled(false);
		b2.setEnabled(false);
		b3.setEnabled(false);
		b4.setEnabled(false);
		
		b1.setBounds(210, 450, 400, 150);
		b1.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 28));
//		b1.setBackground(new Color(1,1,1,0));
//		b1.setOpaque(false);
		Button1Listener l1 = new Button1Listener();
		b1.addActionListener(l1);
		
		b1check.setVisible(false);
		b1.add(b1check);
		getContentPane().add(b1);
		
		b2.setBounds(690, 450, 400, 150);
		b2.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 28));
		Button2Listener l2 = new Button2Listener();
		b2.addActionListener(l2);
		
		b2check.setVisible(false);
		b2.add(b2check);
		
		getContentPane().add(b2);
		
		b3.setBounds(210, 650, 400, 150);
		b3.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 28));
		Button3Listener l3 = new Button3Listener();
		b3.addActionListener(l3);
		
		b3check.setVisible(false);
		b3.add(b3check);
		
		getContentPane().add(b3);
		
		b4.setBounds(690, 650, 400, 150);
		b4.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 28));
		Button4Listener l4 = new Button4Listener();
		b4.addActionListener(l4);
		
		b4check.setVisible(false);
		b4.add(b4check);
		
		getContentPane().add(b4);
		
		sb = new JButton("Start!");
		sb.setBounds(575, 140, 150, 50);
		sb.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 40));
//		sb.setBorderPainted(false);
		sb.setBackground(new Color(87,181,243));
		sb.setOpaque(false);
		sb.setForeground(Color.black);
		sb.addActionListener(startListener);
		getContentPane().add(sb);
	}
	
/////////////////////Game Title Label
	
	public void createLabel() {
		
		l1 = new JLabel("Fintest");
		l1.setFont(new Font("Bowhouse Regular", Font.PLAIN, 70));
		l1.setForeground(Color.black);
		l1.setBounds(573, 15, 300, 150 );
		TitleMouseListener t = new TitleMouseListener();
		l1.addMouseListener(t);
		getContentPane().add(l1);
	}
	
	class TitleMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
//			l1.setForeground(new Color(247,177,137));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
//			l1.setForeground(Color.black);
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
 
/////////////////////Return Button
	
	public void createReturn() {
		
		rButton = new JButton();
		rButton.setIcon(new ImageIcon(this.getClass().getResource("/resources/return_arrow.png")));
		rButton.setBackground(new Color(1,1,1,0));
		rButton.setOpaque(false);
		rButton.setBounds(175, 70, 100, 100);
		rButton.setBorderPainted(false);
		ReturnListener r = new ReturnListener();
		rButton.addActionListener(r);
		getContentPane().add(rButton);
		
	}

/////////////////////Count Down!
	
	public void createCountDown() {
		
		tl = new JLabel("Time: ");
		tl.setFont(new Font("Agency FB", Font.BOLD, 45));
		tl.setForeground(new Color(45,101,138));
		tl.setBounds(900, 70, 90, 45);
		getContentPane().add(tl);
		
		cd = new JTextArea(10,10);
		cd.setText("5");
		cd.setEditable(false);
		cd.setFont(new Font("Agency FB", Font.BOLD, 50));
		cd.setOpaque(false);
//		cd.setBackground(new Color(0,0,0,0));
		cd.setForeground(new Color(45,101,138));
		cd.setBounds(1020, 65, 25, 60);
		getContentPane().add(cd);
	}

/////////////////////User Score
	
	public void createScore() {
		
		s = new JLabel("Score: ");
		s.setFont(new Font("Agency FB", Font.BOLD, 45));
		s.setForeground(new Color(45,101,138));
		s.setBounds(880, 130, 110, 45);
		getContentPane().add(s);
		
		sc = new JTextArea(10,10);
		sc.setEditable(true);
		sc.setText("0");
		sc.setFont(new Font("Agency FB", Font.BOLD, 50));
		sc.setOpaque(false);
		sc.setForeground(new Color(45,101,138));
		sc.setBounds(1020, 125, 125, 60);
		getContentPane().add(sc);
	}
	
	public void setScore() {
		
		int time = Integer.parseInt(cd.getText());
		int score = Integer.parseInt(sc.getText());
		if(time==5) {
			score = score + 20;
		}
		else if(time==4) {
			score = score + 18;
		}
		else if(time==3) {
			score = score + 16;
		}
		else if(time==2) {
			score = score + 14;
		}
		else if(time==1) {
			score = score + 12;
		}
		else {
			score = score + 0;
		}
		sc.setText("" + score);
	}
	
///////////////////////////next question
	
	public void nextQuestion() {
		
		if(Integer.parseInt(sc.getText()) >= 100) {
			getMainPage().getClip().stop();
			if(getMainPage().getClip2()==null) {
				
			}
			else {
				getMainPage().getClip2().stop();
			}
			
			getMainPage().dispose();
			l1.setVisible(false);
			tl.setVisible(false);
			s.setVisible(false);
			b1.setVisible(false);
			b2.setVisible(false);
			b3.setVisible(false);
			b4.setVisible(false);
			rButton.setVisible(false);
			sb.setVisible(false);
			q.setVisible(false);
			cd.setVisible(false);
			sc.setVisible(false);
//			cool.setVisible(false);
			Egg egg = new Egg();
			sc.setText("0");
			dispose();
		}
		
		if(questionNum >4 ) {
			
			q.setText("Quiz over!"+"\n"+"Score: "+sc.getText());
			cd.setText("5");
			t.stop();
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			questionNum = 0;
		}
		else {
			questionNum++;
			cd.setText("5");
			b1.setEnabled(true);
			b2.setEnabled(true);
			b3.setEnabled(true);
			b4.setEnabled(true);
			q.setText("Question "+questionNum+"\n");
			i = (int)(Math.random()*a);
			q.append(questionList.get(i).getQuestion());
			int b = (int)(Math.random()*4);
			if(b==0) {
				b1.setText(questionList.get(i).getAnswerList().get(0).getName());
				b2.setText(questionList.get(i).getAnswerList().get(1).getName());
				b3.setText(questionList.get(i).getAnswerList().get(2).getName());
				b4.setText(questionList.get(i).getAnswerList().get(3).getName());
			}
			else if(b==1) {
				b1.setText(questionList.get(i).getAnswerList().get(1).getName());
				b2.setText(questionList.get(i).getAnswerList().get(0).getName());
				b3.setText(questionList.get(i).getAnswerList().get(3).getName());
				b4.setText(questionList.get(i).getAnswerList().get(2).getName());
			}
			else if(b==2) {
				b1.setText(questionList.get(i).getAnswerList().get(2).getName());
				b2.setText(questionList.get(i).getAnswerList().get(3).getName());
				b3.setText(questionList.get(i).getAnswerList().get(0).getName());
				b4.setText(questionList.get(i).getAnswerList().get(1).getName());
			}else if(b==3) {
				b1.setText(questionList.get(i).getAnswerList().get(1).getName());
				b2.setText(questionList.get(i).getAnswerList().get(2).getName());
				b3.setText(questionList.get(i).getAnswerList().get(3).getName());
				b4.setText(questionList.get(i).getAnswerList().get(0).getName());
			}
			
			a = a - 1;
			
			for(Answer rightAns:questionList.get(i).getAnswerList()) {
				if(rightAns.checkAnswer()==true) {
					rightAnswer = rightAns.getName();
				}
			}
			
			b1.setBackground(null);
			b2.setBackground(null);
			b3.setBackground(null);
			b4.setBackground(null);
			b1check.setVisible(false);
			b2check.setVisible(false);
			b3check.setVisible(false);
			b4check.setVisible(false);
		}
		
		
		
	}
		
	class ReturnListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			t.stop();
			t2.start();
		}
	}
	
	class Button1Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			q.setText("Your answer: "+b1.getText()+"\n");
			if(b1checkAnswer() == true) {
				q.append("Great!");
				if(questionNum==1) {
					getRecord().getQ1c().setVisible(true);
					getRecord().getQ1c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
				if(questionNum==2) {
					getRecord().getQ2c().setVisible(true);
					getRecord().getQ2c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
				if(questionNum==3) {
					getRecord().getQ3c().setVisible(true);
					getRecord().getQ3c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
				if(questionNum==4) {
					getRecord().getQ4c().setVisible(true);
					getRecord().getQ4c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
				if(questionNum==5) {
					getRecord().getQ5c().setVisible(true);
					getRecord().getQ5c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
			
			}
			else {
				q.append("Oops!");
				if(questionNum==1) {
					getRecord().getQ1c().setVisible(true);
					getRecord().getQ1c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
				if(questionNum==2) {
					getRecord().getQ2c().setVisible(true);
					getRecord().getQ2c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
				if(questionNum==3) {
					getRecord().getQ3c().setVisible(true);
					getRecord().getQ3c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
				if(questionNum==4) {
					getRecord().getQ4c().setVisible(true);
					getRecord().getQ4c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
				if(questionNum==5) {
					getRecord().getQ5c().setVisible(true);
					getRecord().getQ5c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
			}
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			if(b1checkAnswer() == true) {
				setScore();
			}
			getRecord().getQuestionArrayList().set(questionNum-1,("Question: "+"\n"+questionList.get(i).getQuestion()));
			getRecord().getYourAnswerArrayList().set(questionNum-1,"Your Answer: "+"\n"+b1.getText());
			getRecord().getRightAnswerArrayList().set(questionNum-1,"Right Answer: "+"\n"+rightAnswer);
			
			cd.setText("3");
			setColor();
			questionList.remove(i);			
		}
	}
	
	class Button2Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			q.setText("Your answer: "+b2.getText()+"\n");
			if(b2checkAnswer() == true) {
				q.append("Great!");
				if(questionNum==1) {
					getRecord().getQ1c().setVisible(true);
					getRecord().getQ1c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
				if(questionNum==2) {
					getRecord().getQ2c().setVisible(true);
					getRecord().getQ2c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
				if(questionNum==3) {
					getRecord().getQ3c().setVisible(true);
					getRecord().getQ3c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
				if(questionNum==4) {
					getRecord().getQ4c().setVisible(true);
					getRecord().getQ4c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
				if(questionNum==5) {
					getRecord().getQ5c().setVisible(true);
					getRecord().getQ5c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
			}
			else {
				q.append("Oops!");
				if(questionNum==1) {
					getRecord().getQ1c().setVisible(true);
					getRecord().getQ1c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
				if(questionNum==2) {
					getRecord().getQ2c().setVisible(true);
					getRecord().getQ2c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
				if(questionNum==3) {
					getRecord().getQ3c().setVisible(true);
					getRecord().getQ3c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
				if(questionNum==4) {
					getRecord().getQ4c().setVisible(true);
					getRecord().getQ4c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
				if(questionNum==5) {
					getRecord().getQ5c().setVisible(true);
					getRecord().getQ5c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
			}
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			if(b2checkAnswer() == true) {
				setScore();
			}
			getRecord().getQuestionArrayList().set(questionNum-1,("Question: "+"\n"+questionList.get(i).getQuestion()));
			getRecord().getYourAnswerArrayList().set(questionNum-1,"Your Answer: "+"\n"+b2.getText());
			getRecord().getRightAnswerArrayList().set(questionNum-1,"Right Answer: "+"\n"+rightAnswer);
			cd.setText("3");
			setColor();
			questionList.remove(i);
		
		}
	}
	
	class Button3Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			q.setText("Your answer: "+b3.getText()+"\n");
			if(b3checkAnswer() == true) {
				q.append("Great!");
				if(questionNum==1) {
					getRecord().getQ1c().setVisible(true);
					getRecord().getQ1c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
				if(questionNum==2) {
					getRecord().getQ2c().setVisible(true);
					getRecord().getQ2c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
				if(questionNum==3) {
					getRecord().getQ3c().setVisible(true);
					getRecord().getQ3c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
				if(questionNum==4) {
					getRecord().getQ4c().setVisible(true);
					getRecord().getQ4c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
				if(questionNum==5) {
					getRecord().getQ5c().setVisible(true);
					getRecord().getQ5c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
			}
			else {
				q.append("Oops!");
				if(questionNum==1) {
					getRecord().getQ1c().setVisible(true);
					getRecord().getQ1c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
				if(questionNum==2) {
					getRecord().getQ2c().setVisible(true);
					getRecord().getQ2c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
				if(questionNum==3) {
					getRecord().getQ3c().setVisible(true);
					getRecord().getQ3c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
				if(questionNum==4) {
					getRecord().getQ4c().setVisible(true);
					getRecord().getQ4c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
				if(questionNum==5) {
					getRecord().getQ5c().setVisible(true);
					getRecord().getQ5c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
			}
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			if(b3checkAnswer() == true) {
				setScore();
			}
			getRecord().getQuestionArrayList().set(questionNum-1,("Question: "+"\n"+questionList.get(i).getQuestion()));
			getRecord().getYourAnswerArrayList().set(questionNum-1,"Your Answer: "+"\n"+b3.getText());
			getRecord().getRightAnswerArrayList().set(questionNum-1,"Right Answer: "+"\n"+rightAnswer);
			cd.setText("3");
			setColor();
			questionList.remove(i);

		}
	}
	
	class Button4Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			q.setText("Your answer: "+b4.getText()+"\n");
			if(b4checkAnswer() == true) {
				q.append("Great!");
				if(questionNum==1) {
					getRecord().getQ1c().setVisible(true);
					getRecord().getQ1c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
				if(questionNum==2) {
					getRecord().getQ2c().setVisible(true);
					getRecord().getQ2c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
				if(questionNum==3) {
					getRecord().getQ3c().setVisible(true);
					getRecord().getQ3c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
				if(questionNum==4) {
					getRecord().getQ4c().setVisible(true);
					getRecord().getQ4c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
				if(questionNum==5) {
					getRecord().getQ5c().setVisible(true);
					getRecord().getQ5c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
				}
			}
			else {
				q.append("Oops!");
				if(questionNum==1) {
					getRecord().getQ1c().setVisible(true);
					getRecord().getQ1c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
				if(questionNum==2) {
					getRecord().getQ2c().setVisible(true);
					getRecord().getQ2c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
				if(questionNum==3) {
					getRecord().getQ3c().setVisible(true);
					getRecord().getQ3c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
				if(questionNum==4) {
					getRecord().getQ4c().setVisible(true);
					getRecord().getQ4c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
				if(questionNum==5) {
					getRecord().getQ5c().setVisible(true);
					getRecord().getQ5c().setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross3.png")));
				}
			}
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			
			if(b4checkAnswer() == true) {
				setScore();
			}
			getRecord().getQuestionArrayList().set(questionNum-1,("Question: "+"\n"+questionList.get(i).getQuestion()));
			getRecord().getYourAnswerArrayList().set(questionNum-1,"Your Answer: "+"\n"+b4.getText());
			getRecord().getRightAnswerArrayList().set(questionNum-1,"Right Answer: "+"\n"+rightAnswer);
			cd.setText("3");
			setColor();
			questionList.remove(i);

		}
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
	
	class StartListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			playMouse();
			sb.setEnabled(false);
			sb.setOpaque(false);
			sb.setBackground(new Color(0,0,0,0));
			t.start();
			nextQuestion();
//			q.setText("First Question Coming..."+"\n"+"Get READY!");
		}
	}
	
	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
	
			cd.setText(""+(Integer.parseInt(cd.getText())-1));
			
			timerCount++;
			
			if(cd.getText().contentEquals("-1")) {

				nextQuestion();	
			}
			
		}
	}
	
	//////////////////////////////Question and Answer
	
	public Question createQuestion(int a) throws SQLException {
		
	try {	
		conn = SimpleDataSource.getConnection();
		Statement stat = conn.createStatement();
		String query = "SELECT FQ_question.Question,FQ_question.True_answer,FQ_answer.Wrong_answer1,FQ_answer.Wrong_answer2,FQ_answer.Wrong_answer3 FROM FQ_answer,FQ_question WHERE FQ_question.True_answer=FQ_answer.True_answer AND FQ_question.Qnum="+a;
		stat.execute(query);
		ResultSet result = stat.getResultSet();
//		result.next();
		while(result.next()) {
		Answer a1 = new Answer(result.getString(2),true);
		Answer a2 = new Answer(result.getString(3),false);
		Answer a3 = new Answer(result.getString(4),false);
		Answer a4 = new Answer(result.getString(5),false);
		Question q1 = new Question(result.getString(1),a1,a2,a3,a4);
		return q1;
		}
		result.close();
		
	} catch (Exception e) {
		// TODO: handle exception
		e.getMessage();
	} finally {
		conn.close();
	}	
	return null;
	}
	
	public void createQuestionList() throws SQLException {		
		questionList = new ArrayList<Question>();
		questionList.add(createQuestion(list.get(0)));
		questionList.add(createQuestion(list.get(1)));
		questionList.add(createQuestion(list.get(2)));
		questionList.add(createQuestion(list.get(3)));
		questionList.add(createQuestion(list.get(4)));

	}
	
	///////////////////////////////check answer
	
	public boolean b1checkAnswer() {
//		questionList.get(i).getAnswerList().get(2).getName()
		for(Answer right:questionList.get(i).getAnswerList()) {
			if(right.getName().contentEquals(b1.getText())) {
				
				return right.checkAnswer();
			}
		}
		return false;
		
	}
	
	public boolean b2checkAnswer() {
//		questionList.get(i).getAnswerList().get(2).getName()
		for(Answer right:questionList.get(i).getAnswerList()) {
			if(right.getName().contentEquals(b2.getText())) {
				return right.checkAnswer();
			}
		}
		return false;
		
	}
	
	public boolean b3checkAnswer() {
//		questionList.get(i).getAnswerList().get(2).getName()
		for(Answer right:questionList.get(i).getAnswerList()) {
			if(right.getName().contentEquals(b3.getText())) {
				return right.checkAnswer();
			}
		}
		return false;
		
	}
	
	public boolean b4checkAnswer() {
//		questionList.get(i).getAnswerList().get(2).getName()
		for(Answer right:questionList.get(i).getAnswerList()) {
			if(right.getName().contentEquals(b4.getText())) {
				return right.checkAnswer();
			}
		}
		return false;
		
	}
	
	public void setColor() {
		
		if(b1checkAnswer()==true) {
			b1.setBackground(new Color(51,204,255));
			b1check.setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
			b1check.setVisible(true);
		}
		else {
			b1.setBackground(new Color(230,255,255));
			b1check.setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross2.png")));
			b1check.setVisible(true);
		}
		
		if(b2checkAnswer()==true) {
			b2.setBackground(new Color(51,204,255));
			b2check.setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
			b2check.setVisible(true);
		}
		else {
			b2.setBackground(new Color(230,255,255));
			b2check.setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross2.png")));
			b2check.setVisible(true);
		}
		
		if(b3checkAnswer()==true) {
			b3.setBackground(new Color(51,204,255));
			b3check.setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
			b3check.setVisible(true);
		}
		else {
			b3.setBackground(new Color(230,255,255));
			b3check.setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross2.png")));
			b3check.setVisible(true);
		}
		
		if(b4checkAnswer()==true) {
			b4.setBackground(new Color(51,204,255));
			b4check.setIcon(new ImageIcon(MainPage.class.getResource("/resources/tick.png")));
			b4check.setVisible(true);
		}
		else {
			b4.setBackground(new Color(230,255,255));
			b4check.setIcon(new ImageIcon(MainPage.class.getResource("/resources/cross2.png")));
			b4check.setVisible(true);
		}
		
	}
	
	
	
	///////////////////////////////////////
	
	public Record getRecord() {
		for(Frame frame: JFrame.getFrames()) {
		if(frame.getTitle().equals("Record")) {
			Record r = (Record) frame;
//			return ((Ranking)frame);
			return r;
			
		}
		}	
		return null;
		}
	
	public MainPage getMainPage() {
		for(Frame frame: JFrame.getFrames()) {
		if(frame.getTitle().equals("FinTest")) {
//			MainPage mp = (MainPage) frame;
//			return mp.getMainPage();
			return (MainPage)frame;
		}
		}
		return null;
		}
	
	
}

