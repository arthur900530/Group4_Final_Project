
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.MouseInfo;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class POCFrame extends JFrame{
	private int position;
	private ArrayList<POCQuestion> questionList;
	private ArrayList<POCItem> itemList;
	private ArrayList<POCItem> movelblGroup;
	ArrayList<POCItem> rdnList;
	private boolean isGameRunning;
	private int score;
	private int highestScore;
	private int cout;
	private int answerAmount;
	private JLabel transparentLabel;
	private Timer popTimer;
	private int popCout;
	
	
//	Foreground
	private JLabel tipsLabel1;
	private JLabel tipsLabel2;
	private JLabel foregroundLabel;
	private JButton gameStartButton;
	
//	Background
	
	private POCItem moveLabel1;
	private POCItem moveLabel2;
	private POCItem moveLabel3;
	private POCItem moveLabel4;
	private POCItem moveLabel5;
	private POCItem moveLabel6;
	private JLabel questionLabel;
	private JLabel playerLabel;
	private JLabel bgLabel;
	private JLabel scoreLabel;
	private JLabel popLabel;
	private JButton backButton,rButton;
	private Timer bgTimer;
	private Timer t2;
	private int timeCount2;
// Loading
	private JLabel loadScene;
	private Timer loadTimer;
	
//	Music
	private Clip clip;
	
	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public POCFrame() throws IOException {
		position = 0;
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					if(rdnList.get(position - 1).checkAnswer()) {
						if(!rdnList.get(position - 1).checkStatus()) {
							score += 100;
							scoreLabel.setText("Score: " + score);
							rdnList.get(position - 1).use();
							rdnList.get(position - 1).setText("");

							playCorrectFx();
							
							int corX = 0;
							int corY = 0;
							
							corX = MouseInfo.getPointerInfo().getLocation().x - 100;
							corY = MouseInfo.getPointerInfo().getLocation().y - 100;
						
							popLabel.setBounds(corX, corY, 213, 116);
							popLabel.setIcon(new ImageIcon(POCFrame.class.getResource("/resources/100.png")));
							popCout = 0;
							
							cout++;
							checkFinished();
						}
						
					}
					else {
						if(!rdnList.get(position - 1).checkStatus()) {
							score -= 100;
							scoreLabel.setText("Score: " + score);
							rdnList.get(position - 1).use();

							playWrongFx();
							
							int corX = 0;
							int corY = 0;
							
							corX = MouseInfo.getPointerInfo().getLocation().x - 100;
							corY = MouseInfo.getPointerInfo().getLocation().y - 150;
						
							popLabel.setBounds(corX, corY, 213, 116);
							popLabel.setIcon(new ImageIcon(POCFrame.class.getResource("/resources/-100.png")));
							popCout = 0;
							
							checkFinished();
						}
					}
				}
			}
		});

		getContentPane().setBackground(UIManager.getColor("Button.focus"));
		setSize(1300,950);
		getContentPane().setLocation(getContentPane().getX() + 200 , getContentPane().getY() + 100);
		initialize();
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		showLoadingAnimation();
		setVisible(true);
		setFocusable(true);
	}
	public void createReturn() {
		
		rButton = new JButton();
		rButton.setIcon(new ImageIcon(getClass().getResource("/resources/return_arrow.png")));
		rButton.setBackground(new Color(1,1,1,0));
		rButton.setOpaque(false);
		rButton.setBounds(75, 50, 100, 100);
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
				rButton.setBounds(-timeCount2+75, 50, 100, 100);
			}
			if(timeCount2>175) {
				t2.stop();
				clip.stop();
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
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		setBackground(UIManager.getColor("Button.light"));
		getContentPane().setLayout(null);
		requestFocus();
		questionList = new ArrayList<>();
		itemList = new ArrayList<>();
		movelblGroup = new ArrayList<>();
		highestScore = 0;
		createForeground();
		createBackground();
		createT2();
		createReturn();
		activateForeground(false);
		activateBackground(false);
		try {
			playOPMusic();
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
	
	public void setPosition(int n) {
		position = n;
	}
	
	public void gameStart() throws SQLException {
		questionList = new ArrayList<>();
		itemList = new ArrayList<>();
		loadQuestions();
		updateItems();
		cout = 0;
		score = 0;
		scoreLabel.setText("Score: 0");
	}
	
	public void nextQuestion() {
		questionList.remove(0);
		itemList.remove(0);
		itemList.remove(0);
		itemList.remove(0);
		itemList.remove(0);
		itemList.remove(0);
		itemList.remove(0);
	}
	
	public void checkFinished() {
		if(cout == answerAmount) {
			cout = 0;
			nextQuestion();
			if(itemList.size() >= 6 && questionList.size() >= 1) {
				JOptionPane.showMessageDialog(null, "Next");
				updateItems();
			}
			else {
				if(score > highestScore) {
					highestScore = score;
					JOptionPane.showMessageDialog(null, "Game End\nNew Record!!\nYour score is: " + score);
				}
				else {
					JOptionPane.showMessageDialog(null, "Game End\nYour score is: " + score +"\nYour highest score is: " + highestScore);
				}
				backButton.doClick();
			}
		}
	}
	
	public void loadQuestions() throws SQLException {
		String server = "jdbc:mysql://140.119.19.73:9306/";
		String database = "TG04";
		String config= "?useUnicode=true&characterEncoding=utf8";
		String url = server + database + config;
		String username = "TG04"; 
		String password = "98ffe7";
		Connection conn = null;
		answerAmount = 0;
		try {
			conn =DriverManager.getConnection(url, username, password);
			PreparedStatement stat = conn.prepareStatement("SELECT Qid, Content, Amount FROM POC_question");
			boolean hasResult = stat.execute();
			if(hasResult) {
				ResultSet rs = stat.executeQuery(); 
				while(rs.next()) {
					questionList.add(new POCQuestion(rs.getString("Qid") , rs.getString("Content"), rs.getInt("Amount")));
				}
			}

			stat = conn.prepareStatement("SELECT Qid, Content, Answer FROM POC_answer");
			hasResult = stat.execute();
			if(hasResult) {
				ResultSet rs = stat.executeQuery(); 
				while(rs.next()) {
					itemList.add(new POCItem(rs.getString("Qid") , rs.getString("Content"), rs.getBoolean("Answer")));
				}
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
	}
	public boolean checkIsCorrect(POCItem item) {
		return item.checkAnswer();
	}
	
	public void setMovelblFonts() {
		moveLabel1.setFont(new Font("微软雅黑 Light", Font.BOLD, 24));
		moveLabel2.setFont(new Font("微软雅黑 Light", Font.BOLD, 24));
		moveLabel3.setFont(new Font("微软雅黑 Light", Font.BOLD, 24));
		moveLabel4.setFont(new Font("微软雅黑 Light", Font.BOLD, 24));
		moveLabel5.setFont(new Font("微软雅黑 Light", Font.BOLD, 24));
		moveLabel6.setFont(new Font("微软雅黑 Light", Font.BOLD, 24));
	}
	
	public void updateItems() {
		
		String question = "Q" + questionList.get(0).getQID() + ": " + questionList.get(0).getQuestion();
		questionLabel.setText("<html>" + question + "</html>");
		answerAmount = questionList.get(0).getAmount();
		ArrayList<POCItem> copyList = new ArrayList<>();
		rdnList = new ArrayList<>();
				
		for(int i = 0 ; i < 6 ; i++) { 
			copyList.add(itemList.get(i));
		}
		
		Random rdn = new Random();
		
		for(int i = 0 ; i < 6 ; i++) { 
			int r = rdn.nextInt( copyList.size());
			rdnList.add(copyList.get(r));
			rdnList.get(i).setPos(i+1);
			
			copyList.remove(r);
		}
		
		for(int i = 0 ; i < 6 ; i++) {
			movelblGroup.get(i).setOpaque(false);
			movelblGroup.get(i).setForeground(Color.white);
			movelblGroup.get(i).setBackground(transparentLabel.getBackground());
			movelblGroup.get(i).setText("<html>" + rdnList.get(i).getText() + "</html>");
			movelblGroup.get(i).setAns(rdnList.get(i).checkAnswer());
			movelblGroup.get(i).setIcon(null);
		}
		

	}

	public void createForeground() {
		
		transparentLabel = new JLabel();
		
					moveLabel2 = new POCItem("");
					
							moveLabel2.setBounds(358, 387, 199, 185);
							moveLabel2.addMouseListener(new MouseAdapter() {
								public void mouseEntered(MouseEvent e) {
									playerLabel.setLocation(358, 387);
									setPosition(2);
									if(isGameRunning) {
										if(!rdnList.get(1).checkStatus()){
											moveLabel2.setOpaque(true);
											moveLabel2.setBackground(Color.cyan);
											}
										}
									}
									@Override
									public void mouseExited(MouseEvent e) {
										if(isGameRunning) {
											if(!rdnList.get(1).checkStatus())	{
												moveLabel2.setOpaque(false);
												moveLabel2.setBackground(transparentLabel.getBackground());
											}
											else {
												if(rdnList.get(1).checkAnswer() == false) {
													moveLabel2.setOpaque(false);
													moveLabel2.setText("");
													moveLabel2.setIcon(new ImageIcon(POCFrame.class.getResource("/resources/wrong.png")));
													}
												else {	
													moveLabel2.setOpaque(false);
													moveLabel2.setText("");
													moveLabel2.setIcon(new ImageIcon(POCFrame.class.getResource("/resources/correct.png")));
													}
											}
										}
									}
								
							});
							moveLabel3 = new POCItem("");
							
									moveLabel3.setBounds(621, 387, 199, 185);
									moveLabel3.addMouseListener(new MouseAdapter() {
										public void mouseEntered(MouseEvent e) {
											playerLabel.setLocation(621, 387);
											setPosition(3);
											if(isGameRunning) {
												if(!rdnList.get(2).checkStatus()){
													moveLabel3.setOpaque(true);
													moveLabel3.setBackground(Color.cyan);
													}
												}
											}
											@Override
											public void mouseExited(MouseEvent e) {
												if(isGameRunning) {
													if(!rdnList.get(2).checkStatus())	{
														moveLabel3.setOpaque(false);
														moveLabel3.setBackground(transparentLabel.getBackground());
													}
													else {
														if(rdnList.get(2).checkAnswer() == false) {
															moveLabel3.setOpaque(false);
															moveLabel3.setText("");
															moveLabel3.setIcon(new ImageIcon(POCFrame.class.getResource("/resources/wrong.png")));
															}
														else {	
															moveLabel3.setOpaque(false);
															moveLabel3.setText("");
															moveLabel3.setIcon(new ImageIcon(POCFrame.class.getResource("/resources/correct.png")));
															}
													}
												}
											}
										
									});
									moveLabel4 = new POCItem("");
									
									moveLabel4.setBounds(102, 608, 199, 185);
									moveLabel4.addMouseListener(new MouseAdapter() {
										public void mouseEntered(MouseEvent e) {
											playerLabel.setLocation(102, 608);
											setPosition(4);
											if(isGameRunning) {
												moveLabel4.setOpaque(true);
												if(!rdnList.get(3).checkStatus()){
													moveLabel4.setBackground(Color.cyan);
													}
												}
											}
											@Override
											public void mouseExited(MouseEvent e) {
												if(isGameRunning) {
													if(!rdnList.get(3).checkStatus())	{
														moveLabel4.setOpaque(false);
														moveLabel4.setBackground(transparentLabel.getBackground());
													}
													else {
														if(rdnList.get(3).checkAnswer() == false) {
															moveLabel4.setOpaque(false);
															moveLabel4.setText("");
															moveLabel4.setIcon(new ImageIcon(POCFrame.class.getResource("/resources/wrong.png")));
															}
														else {	
															moveLabel4.setOpaque(false);
															moveLabel4.setText("");
															moveLabel4.setIcon(new ImageIcon(POCFrame.class.getResource("/resources/correct.png")));
															}
													}
												}
											}
										
									});
									moveLabel5 = new POCItem("");
									
									moveLabel5.setBounds(358, 608, 199, 185);
									moveLabel5.addMouseListener(new MouseAdapter() {
										public void mouseEntered(MouseEvent e) {
											playerLabel.setLocation(358, 608);
											setPosition(5);
											if(isGameRunning) {
												moveLabel5.setOpaque(true);
												if(!rdnList.get(4).checkStatus()){
													moveLabel5.setBackground(Color.cyan);
													}
												}
											}
											@Override
											public void mouseExited(MouseEvent e) {
												if(isGameRunning) {
													if(!rdnList.get(4).checkStatus())	{
														moveLabel5.setOpaque(false);
														moveLabel5.setBackground(transparentLabel.getBackground());
													}
													else {
														if(rdnList.get(4).checkAnswer() == false) {
															moveLabel5.setOpaque(false);
															moveLabel5.setText("");
															moveLabel5.setIcon(new ImageIcon(POCFrame.class.getResource("/resources/wrong.png")));
															}
														else {	
															moveLabel5.setOpaque(false);
															moveLabel5.setText("");
															moveLabel5.setIcon(new ImageIcon(POCFrame.class.getResource("/resources/correct.png")));
															}
													}
												}
											}
									});
									moveLabel6 = new POCItem("");
									
									moveLabel6.setBounds(621, 608, 199, 185);
									moveLabel6.addMouseListener(new MouseAdapter() {
										public void mouseEntered(MouseEvent e) {
											playerLabel.setLocation(621, 608);
											setPosition(6);
											if(isGameRunning) {
												if(!rdnList.get(5).checkStatus()){
													moveLabel6.setOpaque(true);
													moveLabel6.setBackground(Color.cyan);
													}
												}
											}
											@Override
											public void mouseExited(MouseEvent e) {
												if(isGameRunning) {
													if(!rdnList.get(5).checkStatus())	{
														moveLabel6.setOpaque(false);
														moveLabel6.setBackground(transparentLabel.getBackground());
													}
													else {
														if(rdnList.get(5).checkAnswer() == false) {
															moveLabel6.setOpaque(false);
															moveLabel6.setText("");
															moveLabel6.setIcon(new ImageIcon(POCFrame.class.getResource("/resources/wrong.png")));
															}
														else {	
															moveLabel6.setOpaque(false);
															moveLabel6.setText("");
															moveLabel6.setIcon(new ImageIcon(POCFrame.class.getResource("/resources/correct.png")));
															}
													}
												}
											}
										
										
									});
					loadScene = new JLabel("");
					loadScene.setIcon(new ImageIcon(POCFrame.class.getResource("/loading/loading0.png")));
					loadScene.setBounds(48, 60, 853, 793);
					getContentPane().add(loadScene);
					loadScene.setVisible(false);
					
					popLabel = new JLabel("");
					popLabel.setIcon(new ImageIcon(POCFrame.class.getResource("/resources/100.png")));
					popLabel.setBounds(650, 298, 213, 116);
					getContentPane().add(popLabel);
					popLabel.setVisible(false);
									
					playerLabel = new JLabel("");
					playerLabel.setIcon(new ImageIcon(POCFrame.class.getResource("/resources/player.png")));
					playerLabel.setBounds(48, 60, 853, 793);
					getContentPane().add(playerLabel);
					
					
					playerLabel.setBounds(140, 659, 161, 155);  //first block
					getContentPane().add(moveLabel6);
					getContentPane().add(moveLabel5);
					getContentPane().add(moveLabel4);
					getContentPane().add(moveLabel3);
					getContentPane().add(moveLabel2);
			
							
							
							moveLabel1 = new POCItem("");
							moveLabel1.setBounds(102, 387, 199, 185);
							
							moveLabel1.addMouseListener(new MouseAdapter() {
								
								@Override
								public void mouseEntered(MouseEvent e) {
									playerLabel.setLocation(102, 387);
									setPosition(1);
									if(isGameRunning) {
										if(!rdnList.get(0).checkStatus()){
											moveLabel1.setOpaque(true);
											moveLabel1.setBackground(Color.cyan);
											}
										}
								}
								@Override
								public void mouseExited(MouseEvent e) {
									if(isGameRunning) {
										if(!rdnList.get(0).checkStatus())	{
											moveLabel1.setOpaque(false);
											moveLabel1.setBackground(transparentLabel.getBackground());
										}
										else {
											if(rdnList.get(0).checkAnswer() == false) {
												moveLabel1.setOpaque(false);
												moveLabel1.setText("");
												moveLabel1.setIcon(new ImageIcon(POCFrame.class.getResource("/resources/wrong.png")));
												}
											else {	
												moveLabel1.setOpaque(false);
												moveLabel1.setText("");
												moveLabel1.setIcon(new ImageIcon(POCFrame.class.getResource("/resources/correct.png")));
												}
										}
									}
								}
							});
							getContentPane().add(moveLabel1);
		
							movelblGroup.add(moveLabel1);
							movelblGroup.add(moveLabel2);
							movelblGroup.add(moveLabel3);
							movelblGroup.add(moveLabel4);
							movelblGroup.add(moveLabel5);
							movelblGroup.add(moveLabel6);
							
		tipsLabel2 = new JLabel("* If the answer you choose is correct , you will Get 100 points, otherwise you will Lose 100 points :))");
		tipsLabel2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		tipsLabel2.setBounds(78, 703, 706, 59);
		getContentPane().add(tipsLabel2);
		
		tipsLabel1 = new JLabel("Tutorial:  Move the mouse to the answers and press space to choose them !!");
		tipsLabel1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tipsLabel1.setBounds(81, 645, 546, 80);
		getContentPane().add(tipsLabel1);
		
		gameStartButton = new JButton("Start");
		
		gameStartButton.setFont(new Font("Times New Roman", Font.BOLD, 36));
		gameStartButton.setBackground(new Color(70, 130, 180));
		gameStartButton.setBounds(607, 750, 268, 80);
		gameStartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activateBackground(true);
				activateForeground(false);
				try {
					setMovelblFonts();
					gameStart();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				clip.stop();
				try {
					playGameMusic();
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
		});
		getContentPane().add(gameStartButton);
		
	}
	
	public void createBackground() {
	
		
		foregroundLabel = new JLabel("");
		foregroundLabel.setIcon(new ImageIcon(POCFrame.class.getResource("/resources/foreground.png")));
		foregroundLabel.setBounds(48, 0, 853, 793);
		getContentPane().add(foregroundLabel);
		
		questionLabel = new JLabel("Q :");
		questionLabel.setFont(new Font("標楷體", Font.BOLD, 25));
		questionLabel.setForeground(Color.white);
		questionLabel.setBounds(181, 140, 571, 144);
		getContentPane().add(questionLabel);
		

		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("POC");
		setBounds(100, 100, 943, 882);
		
		
		score = 0;
		bgLabel = new JLabel("");
		bgLabel.setIcon(new ImageIcon(POCFrame.class.getResource("/background/bg0.png")));
		bgLabel.setBounds(41, 39, 860, 814);
		getContentPane().add(bgLabel);
		scoreLabel = new JLabel("Score: " + score);
		scoreLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		scoreLabel.setForeground(Color.white);
		scoreLabel.setBounds(637, 10, 147, 34);
		getContentPane().add(scoreLabel);
		
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activateForeground(true);
				activateBackground(false);
				clip.stop();
				try {
					playOPMusic();
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
		});
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
		backButton.setBounds(830, 0, 97, 30);
		getContentPane().add(backButton);
		
		
		class PopTimerListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(popCout < 2) {
					popLabel.setVisible(true);
					popCout++;
				}
				else {
					popLabel.setVisible(false);
				}
			}
		}
		
		int interval = 500;
		popTimer = new Timer(interval, new PopTimerListener());
		
		class ChangeBackgroundTimer implements ActionListener{
			int loadTime = 1;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(loadTime<13) {
					bgLabel.setIcon(new ImageIcon(POCFrame.class.getResource("/background/bg" + loadTime +".png")));	
					loadTime++;
				}
				else {
					loadTime = 1;
				}
			}
		}
		bgTimer = new Timer(300, new ChangeBackgroundTimer());
	}
	
	public void showLoadingAnimation() {
		
		class ChangeLoadSceneListener implements ActionListener{
			int loadTime = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(loadTime <= 53) {
				loadScene.setIcon(new ImageIcon(POCFrame.class.getResource("/loading/loading" + loadTime +".png")));
				if(loadTime == 28 || loadTime == 35 || loadTime == 44) playCrashFx();
				if(loadTime == 52) playDestroyFx();
				loadTime++;
				}
				else {
					loadScene.setVisible(false);
					activateForeground(true);
				}
			}
			
		}
		loadScene.setVisible(true);
		loadTimer = new Timer(100, new ChangeLoadSceneListener());
		loadTimer.start();
		
		
	}

	public void activateBackground(Boolean set) {
		if(set == true) {
			bgLabel.setVisible(true);
			playerLabel.setVisible(true);
			questionLabel.setVisible(true);
			scoreLabel.setVisible(true);
			backButton.setVisible(true);
			moveLabel1.setVisible(true);
			moveLabel2.setVisible(true);
			moveLabel3.setVisible(true);
			moveLabel4.setVisible(true);
			moveLabel5.setVisible(true);
			moveLabel6.setVisible(true);
			
			bgTimer.start();
			popCout = 2;
			popTimer.start();
			
			isGameRunning = true;
		}
		else {
			bgLabel.setVisible(false);
			playerLabel.setVisible(false);
			questionLabel.setVisible(false);
			scoreLabel.setVisible(false);
			backButton.setVisible(false);
			moveLabel1.setVisible(false);
			moveLabel2.setVisible(false);
			moveLabel3.setVisible(false);
			moveLabel4.setVisible(false);
			moveLabel5.setVisible(false);
			moveLabel6.setVisible(false);
			
			bgTimer.stop();
			popTimer.stop();
			popLabel.setVisible(false);
			
			isGameRunning = false;
			}
	}
	
	public void activateForeground(Boolean set) {
		if(set == true) {
			loadTimer.stop();
			foregroundLabel.setVisible(true);
			gameStartButton.setVisible(true);
			tipsLabel1.setVisible(true);
			tipsLabel2.setVisible(true);

		}
		else {
			foregroundLabel.setVisible(false);
			gameStartButton.setVisible(false);
			tipsLabel1.setVisible(false);
			tipsLabel2.setVisible(false);

		}
	}
	
	public void playOPMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		  InputStream in = getClass().getResourceAsStream("/fx/opening.wav");
		  InputStream bufferedIn = new BufferedInputStream(in);
		        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
		        clip = AudioSystem.getClip();
		        clip.open(audioIn);
		        clip.start();
		        clip.loop(Clip.LOOP_CONTINUOUSLY);}
		 }
	
	public void playGameMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		  InputStream in = getClass().getResourceAsStream("/fx/background.wav");
		  InputStream bufferedIn = new BufferedInputStream(in);
		        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
		        clip = AudioSystem.getClip();
		        clip.open(audioIn);
		        clip.start();
		        clip.loop(Clip.LOOP_CONTINUOUSLY);}
		 }
	public static void playCrashFx() {
		  
		  new Thread(new Runnable() {
		     // The wrapper thread is unnecessary, unless it blocks on the
		     // Clip finishing; see comments.
		       public void run() {
		         try(InputStream in = getClass().getResourceAsStream("/fx/crash.wav")) { 
		          InputStream bufferedIn = new BufferedInputStream(in);
		             try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
		             Clip c = AudioSystem.getClip();
		             c.open(audioIn);
		             c.start();
		          Thread.sleep(c.getMicrosecondLength()/1000);
		         }
		         } 
		          catch (Exception e) {
		           System.err.println(e.getMessage());
		         }
		       }
		     }).start();
		 }
	
	public static void playDestroyFx() {
		  
		  new Thread(new Runnable() {
		     // The wrapper thread is unnecessary, unless it blocks on the
		     // Clip finishing; see comments.
		       public void run() {
		         try(InputStream in = getClass().getResourceAsStream("/fx/destroy.wav")) { 
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
	
	public static void playCorrectFx() {
		  
		  new Thread(new Runnable() {
		     // The wrapper thread is unnecessary, unless it blocks on the
		     // Clip finishing; see comments.
		       public void run() {
		         try(InputStream in = getClass().getResourceAsStream("/fx/correct.wav")) { 
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
	
	public static void playWrongFx() {
		  
		  new Thread(new Runnable() {
		     // The wrapper thread is unnecessary, unless it blocks on the
		     // Clip finishing; see comments.
		       public void run() {
		         try(InputStream in = getClass().getResourceAsStream("/fx/wrong.wav")) { 
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
}
