import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class Knowledge extends JFrame {
	private Container ct;
	private BackgroundPanel bgp;
	private JTextArea k;
	private Connection conn = null;
	private JLabel title,kbg;
	private JButton k1,k2,k3,k4,k5,k6,rButton;
	private int timeCount2 = 0;
	private Timer t2;
	private JLabel kbg1,kbg2,kbg3;
	private int timeCount = 0;
	private Timer t1;
	private JLabel kl1,kl2,kl3,kl4,kl5,kl6,book;
	private JLabel g1,g2,g3,g4,g5,g6;
	
	public Knowledge() {
		setTitle("Knowledge");
		createGlass();
		createK();
		createTimer();
//		try {
//			getK();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		createBook();
		createT2();
		createReturn();
		createButton();
		createTitle();
		createPaper();
		createKbg();
		ct = this.getContentPane();
		bgp = new BackgroundPanel(new ImageIcon(MainPage.class.getResource("/resources/0002.png")).getImage());
		bgp.setBounds(0,0,1300,950);
		ct.add(bgp);
		ct.setVisible(true);
		setSize(1300,950);	
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	private void createGlass() {
		Icon g = new ImageIcon(MainPage.class.getResource("/resources/glass.png"));
		g1 = new JLabel(g);
		g1.setBounds(247, 175, 60, 60);
		g1.setVisible(false);
		add(g1);
		g2 = new JLabel(g);
		g2.setBounds(423, 175, 60, 60);
		g2.setVisible(false);
		add(g2);
		g3 = new JLabel(g);
		g3.setBounds(599, 175, 60, 60);
		g3.setVisible(false);
		add(g3);
		g4 = new JLabel(g);
		g4.setBounds(775, 175, 60, 60);
		g4.setVisible(false);
		add(g4);
		g5 = new JLabel(g);
		g5.setBounds(960, 175, 60, 60);
		g5.setVisible(false);
		add(g5);
		g6 = new JLabel(g);
		g6.setBounds(1135, 175, 60, 60);
		g6.setVisible(false);
		add(g6);
	}
	
	public void createBook() {
		Icon b = new ImageIcon(MainPage.class.getResource("/resources/knowledge.png"));
		book = new JLabel(b);
		book.setBounds(550, 420, 256, 256);
		book.setVisible(false);
		add(book);
	}
	
	public void createKbg() {
		Icon k1 = new ImageIcon(this.getClass().getResource("/resources/kbg1.png"));
		kbg1 = new JLabel(k1);
		kbg1.setBounds(0, 950, 1300, 210);
		add(kbg1);
		Icon k2 = new ImageIcon(this.getClass().getResource("/resources/kbg2.png"));
		kbg2 = new JLabel(k2);
		kbg2.setBounds(0, 950, 1300, 210);
		add(kbg2);
		Icon k3 = new ImageIcon(this.getClass().getResource("/resources/kbg3.png"));
		kbg3 = new JLabel(k3);
		kbg3.setBounds(0, 950, 1300, 210);
		add(kbg3);
		
	}
	
	public void createPaper() {
		Icon k5 = new ImageIcon(this.getClass().getResource("/resources/kbg.png"));
		kbg = new JLabel(k5);
		kbg.setVisible(false);
		kbg.setBounds(103, 200, 1100, 700);
		add(kbg);
	}
	
	public void createK() {
		k = new JTextArea();
		k.setBounds(150, 250, 1000, 600);
		k.setLineWrap(true);
		k.setEditable(false);
		k.setOpaque(false);
		k.setFont(new Font("微軟正黑體 Light",Font.BOLD,24));
		add(k);
	}
	
	public void getK() throws SQLException {
		conn = SimpleDataSource.getConnection();
		Statement stat = conn.createStatement();
		String query = "SELECT content FROM knowledge WHERE topic='股票買賣觀念'";
		stat.execute(query);
		boolean hasResultSet = stat.execute(query);
		if(hasResultSet) {
			ResultSet result = stat.getResultSet();
			result.next();
//			System.out.print(result.getString(1));
			k.append(result.getString(1));
			result.close();
		}
	}
	
	public void createTitle() {
		title = new JLabel("Knowledge");
		title.setForeground(Color.black);
		title.setBounds(400, 30, 500, 80);
		title.setFont(new Font("Futurist Fixed-width",Font.PLAIN,55));
		title.setVisible(false);
		add(title);
	}
	
	public void createReturn() {
		rButton = new JButton();
		rButton.setIcon(new ImageIcon(getClass().getResource("/resources/return_arrow.png")));
		rButton.setBackground(new Color(1,1,1,0));
		rButton.setOpaque(false);
		rButton.setBounds(93, 23, 100, 100);
		rButton.setBorderPainted(false);
		rButton.setVisible(false);
		ReturnListener r = new ReturnListener();
		rButton.addActionListener(r);
		getContentPane().add(rButton);
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
//				rButton.setBounds(-timeCount2+175, 70, 100, 100);
				rButton.setBounds(-timeCount2+93, 23, 100, 100);
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
			Cover c = (Cover) frame;
			return c;
		}
		}
		return null;
		}
	//////////////////////
	public void createButton() {
		kl1 = new JLabel("股票買賣觀念");
		kl1.setFont(new Font("SetoFont",Font.PLAIN,20));
		kl1.setForeground(Color.white);
		kl1.setBounds(132,137,130,65);
		kl1.setVisible(false);
		add(kl1);
		kl2 = new JLabel("股票交易時間");
		kl2.setFont(new Font("SetoFont",Font.PLAIN,20));
		kl2.setBounds(313, 137, 170, 65);
		kl2.setForeground(Color.white);
		kl2.setVisible(false);
		add(kl2);
		kl3 = new JLabel("開戶");
		kl3.setFont(new Font("SetoFont",Font.PLAIN,20));
		kl3.setBounds(535, 137, 170, 65);
		kl3.setForeground(Color.white);
		kl3.setVisible(false);
		add(kl3);
		kl4 = new JLabel("證券買賣");
		kl4.setFont(new Font("思源宋體",Font.PLAIN,20));
		kl4.setBounds(695, 137, 170, 65);
		kl4.setForeground(Color.white);
		kl4.setVisible(false);
		add(kl4);
		kl5 = new JLabel("股票交易成本");
		kl5.setFont(new Font("SetoFont",Font.PLAIN,20));
		kl5.setBounds(855, 137, 170, 65);
		kl5.setForeground(Color.white);
		kl5.setVisible(false);
		add(kl5);
		kl6 = new JLabel("投資策略");
		kl6.setFont(new Font("SetoFont",Font.PLAIN,20));
		kl6.setBounds(1055, 137, 170, 65);
		kl6.setForeground(Color.white);
		kl6.setVisible(false);
		add(kl6);
		
		k1 = new JButton("");
		k1.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb3.png")));
		k1.setBorderPainted(false);
		k1.setBackground(new Color(184,207,229));
		k1.setOpaque(false);
		k1.setBounds(110, 140, 170, 59);
		k1.setVisible(false);
		K1Listener a = new K1Listener();
		k1.addActionListener(a);
		add(k1);
		k2 = new JButton("");
		k2.setBackground(new Color(184,207,229));
		k2.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb3.png")));
		k2.setOpaque(false);
		k2.setBorderPainted(false);
//		k2.setBounds( 395, 325, 500, 50);
		k2.setBounds(290, 140, 170, 59);
//		k2.setBorderPainted(false);
		k2.setVisible(false);
		K2Listener b = new K2Listener();
		k2.addActionListener(b);
		add(k2);
		k3 = new JButton("");
		k3.setBackground(new Color(184,207,229));
		k3.setBorderPainted(false);
		k3.setBounds(470, 140, 170, 59);
		k3.setVisible(false);
		k3.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb3.png")));
		k3.setOpaque(false);
		K3Listener c = new K3Listener();
		k3.addActionListener(c);
		add(k3);
		k4 = new JButton("");
		k4.setBackground(new Color(184,207,229));
		k4.setBorderPainted(false);
		k4.setBounds(650, 140, 170, 59);
		k4.setVisible(false);
		k4.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb3.png")));
		k4.setOpaque(false);
		K4Listener d = new K4Listener();
		k4.addActionListener(d);
		add(k4);
		k5 = new JButton("");
		k5.setBackground(new Color(184,207,229));
//		k5.setBounds( 395, 550, 500, 50);
		k5.setBounds(830, 140, 170, 59);
		k5.setBorderPainted(false);
		k5.setVisible(false);
		k5.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb3.png")));
		k5.setOpaque(false);
		K5Listener e = new K5Listener();
		k5.addActionListener(e);
		add(k5);
		k6 = new JButton("");
		k6.setBackground(new Color(184,207,229));
		k6.setBounds(1010, 140, 170, 59);
		k6.setBorderPainted(false);
		k6.setVisible(false);
		k6.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb3.png")));
		k6.setOpaque(false);
		K6Listener f = new K6Listener();
		k6.addActionListener(f);
		add(k6);
	}
	
	/////////////////////AL
	class K1Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			playMouse();
			g1.setVisible(true);
			g2.setVisible(false);
			g3.setVisible(false);
			g4.setVisible(false);
			g5.setVisible(false);
			g6.setVisible(false);
			book.setVisible(false);
//			k1.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb1.png")));
//			k2.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k3.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k4.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k5.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k6.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
			k.setText("股票買賣觀念\r\n" + 
					"1.股票交易時，每次買賣最小單位是「1股」，但因為1股金額實在太小了，所以在台灣一般買賣股票的單位都是「1張」，1張股票=1000股，如大立光成交價3860，代表的是一股\r\n" + 
					"3860，實際上買賣一張是386萬元!!\r\n" + 
					"\r\n" + 
					"2.漲停跌停，代表股價在單日的最高漲幅跌幅，是以前一交易日收盤價格做為基準價格，股價一天的漲幅和跌幅最多都是10%。\r\n" + 
					"\r\n" + 
					"3.多頭指的是股票價格大漲，投資者看好股市，市場熱絡；空頭指的是股票價格大跌，投資者不看好股市，市場冷清。股市被大家看好時，稱作牛市；股市不被大家看好時，稱作熊市。\r\n" + 
					"4.股票賺錢的方式有兩種1.資本利得(價差) 2.股利(股票股利、現金股利)  \r\n" + 
					"(1)看準買賣時機，賺取價差\r\n" + 
					"(2)持有股票，只要公司有賺錢，通常會發放股利(台股大部分一年發放一次)。在除息日前一天持有股票，就能領到股利。正常情況下，公司獲利的高低與現金股利配發的多寡是呈正相關。根據上市櫃公司的資料統計，台股整體殖利率近十年的平均為4.16%，遠比銀行定存1%來的高!");
		}
		
	}
	class K2Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			playMouse();
			g1.setVisible(false);
			g2.setVisible(true);
			g3.setVisible(false);
			g4.setVisible(false);
			g5.setVisible(false);
			g6.setVisible(false);
			book.setVisible(false);
//			k1.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k2.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb1.png")));
//			k3.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k4.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k5.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k6.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
			k.setText("台灣股市證券股票交易時間\r\n" + 
					"\r\n" + 
					"除表內所示交易時間之外，另需注意\r\n" + 
					"開市時採30分鐘 ( 8:30 -  9:00) 集合競價決定開盤價\r\n" + 
					"—投資人可以了解當日市場的心態，以及看多看空的趨勢\r\n" + 
					"收市時採 5 分鐘 (13:25 - 13:30) 集合競價決定收盤價\r\n" + 
					"\r\n" + 
					"(集合競價—就是將已輸入電腦系統的買賣單，買方依檔位價格將所有買單由高至低累計，賣方也依檔位價格將所有賣出張數由低至高累計，再找出能滿足最大成交量的檔位價作為成交價。)\r\n" + 
					"\r\n" + 
					" 	交易單位	 交易時間	成交時間\r\n" + 
					"普通交易	1000股(=1張)	 09:00-13:30	09:00-13:30\r\n" + 
					"盤後交易	1000股(=1張)	 14:00-14:30	14:30\r\n" + 
					"零股交易	1 股	 13:40-14:30	14:30\r\n" + 
					"普通預約	1000股(=1張)	 14:40-下個交易日08:30	 下個交易日09:00-13:30\r\n" + 
					"");
		}
		
	}
	class K3Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			playMouse();
			g1.setVisible(false);
			g2.setVisible(false);
			g3.setVisible(true);
			g4.setVisible(false);
			g5.setVisible(false);
			g6.setVisible(false);
			book.setVisible(false);
//			k1.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k2.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k3.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb1.png")));
//			k4.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k5.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k6.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
			k.setText("開戶\r\n" + 
					"\r\n" + 
					"投資人要買股票，要透過一個中間人—也就是券商。他們負責保證買賣能順利進行，\r\n" + 
					"以及從中收取手續費。因此要正式踏入股票市場，請先找一個信任的券商開戶，他們會提供免費的看盤軟體，大型券商有：元大、凱基、永豐、富邦、群益。\r\n" + 
					"\r\n" + 
					"開戶只需要年滿20歲並攜帶雙證件及印章到券商辦理，至於銀行交割帳號到時候可以一起辦，無須擔心。如果想要投資國外股票可以透過國內券商複委託，或是透過海外券商開戶。");
		}
		
	}
	class K4Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			playMouse();
			g1.setVisible(false);
			g2.setVisible(false);
			g3.setVisible(false);
			g4.setVisible(true);
			g5.setVisible(false);
			g6.setVisible(false);
			book.setVisible(false);
//			k1.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k2.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k3.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k4.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb1.png")));
//			k5.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k6.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
			k.setText("證券買賣交割銀行帳戶 --- 資金進出用\r\n" + 
					"\r\n" + 
					"這是你買賣股票撥款的專款專用的銀行帳戶，買賣股票的資金只能從這裡出入。\r\n" + 
					"\r\n" + 
					"如果小美在T日購買一張股票，則小美在T+1日時就能拿到這張股票，要在T+2日10點前付錢，因為台灣是採「T+2交割」制度。\r\n" + 
					"\r\n" + 
					"小美在「T+1」日才能拿到這張股票，因此他不能買的當天，賺了錢就將他賣掉，(除非向券商申請當沖—可即時買賣股票)。\r\n" + 
					"\r\n" + 
					"「T+2」就是當你買進股票成功後，在第2天營業日的上午10點前，才會從你開戶的交割銀行扣款，譬如我禮拜五買進股票金額是10萬塊，那就是下禮拜二營業日他才會從我的交割銀行扣款，但若在T+2日十點後戶頭裡沒有錢，就會產生違約交割。");
		}
		
	}
	class K5Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			playMouse();
			g1.setVisible(false);
			g2.setVisible(false);
			g3.setVisible(false);
			g4.setVisible(false);
			g5.setVisible(true);
			g6.setVisible(false);
			book.setVisible(false);
//			k1.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k2.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k3.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k4.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k5.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb1.png")));
//			k6.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
			k.setText("股票交易成本\r\n" + 
					"\r\n" + 
					"每次買賣股票都必須負擔手續費及交易稅，股票手續費為0.1425%(網路下單有折扣)，買進賣出皆需要支付。因此買賣一張股票，假設總價10萬元，一買一賣之間已經付出了 285元手續費，除了手續費外，交易稅為0.3%，是在“賣出”股票要付給政府的成本，所以買賣一張總價10萬的股票，隱藏的交易成本為585元。因此在投資股票時獲利超過 0.6% 時就賺錢，反之則賠錢。\r\n" + 
					"\r\n" + 
					"若你長期頻繁的買賣股票，你的報酬都被手續費和交易稅吃掉了！因此在巴菲特的投資心法裡有提到，選擇一檔有潛力的股票並做長期投資，才是創造最佳收益的秘訣。\r\n" + 
					"ETF的交易稅為0.1%，股價類期貨為契約金額之0.002%，如果覺得股票的交易成本過高，也可以轉戰這兩種投資方法。");
		}
		
	}
	class K6Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			playMouse();
			g1.setVisible(false);
			g2.setVisible(false);
			g3.setVisible(false);
			g4.setVisible(false);
			g5.setVisible(false);
			g6.setVisible(true);
			book.setVisible(false);
//			k1.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k2.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k3.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k4.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k5.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb.png")));
//			k6.setIcon(new ImageIcon(this.getClass().getResource("/resources/kb1.png")));
			k.setText("股票投資的兩種策略\r\n" + 
					"\r\n" + 
					"1.長線投資—長期持有一家公司的股票，領取股利，不在乎股票短時間的波動。尋找有未來性、穩定的潛力公司進行持有是首要目標，適合時間不多、無法每天盯盤的投資者。適合用基本面分析挑股票。\r\n" + 
					"\r\n" + 
					"2.短線投資—短線賺價差的目標：隨時找到報酬率最高的機會。短線賺價差因為持有股票的時間短，因此股票真實價值高低或股票是否安全在短期內都沒有影響。適合能每天盯盤、能夠承擔高風險、追求高投資報酬率的投資者。適合用技術分析挑股票。");
		}
		
	}
	
	public void createTimer() {
		Timer1Listener a = new Timer1Listener();
		t1 = new Timer(1,a);
		t1.start();
	}
	class Timer1Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			timeCount++;
			if(timeCount>100&&timeCount<415) {
				kbg1.setBounds(0, -(timeCount)+1155, 1300, 210);
			}
			if(timeCount<730&&timeCount>415) {
				kbg2.setBounds(0, -(timeCount)+1260, 1300, 210);
			}
			if(timeCount>730&&timeCount<1045) {
				kbg3.setBounds(0, -(timeCount)+1365, 1300, 210);
			}
			if(timeCount>1200) {
				title.setVisible(true);
				rButton.setVisible(true);
			}
			if(timeCount>1350) {
				k1.setVisible(true);
				k2.setVisible(true);
				k3.setVisible(true);
				k4.setVisible(true);
				k5.setVisible(true);
				k6.setVisible(true);
				kl1.setVisible(true);
				kl2.setVisible(true);
				kl3.setVisible(true);
				kl4.setVisible(true);
				kl5.setVisible(true);
				kl6.setVisible(true);
			}
			if(timeCount>1450) {
				kbg.setVisible(true);
				book.setVisible(true);
			}
			if(timeCount>1451) {
				t1.stop();
			}
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
}
