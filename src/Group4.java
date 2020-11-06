import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Group4 extends JFrame {
	private Container ct;
	private BackgroundPanel bgp;
	private JLabel title;
	private JLabel subTitle;
	private JLabel pic1;
	private Icon pi1;
	private Icon pb1;
	private JLabel picbar1;
	private JTextArea p1;

	public Group4() {
		createIntro();
		createFont();
		createTitle();
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
	
	public void createFont() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,this.getClass().getResourceAsStream("/fonts/FUTRFW.ttf")));
        } catch (FontFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,this.getClass().getResourceAsStream("/fonts/Zector.otf")));
        } catch (FontFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,this.getClass().getResourceAsStream("/fonts/secrcode.ttf")));
        } catch (FontFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
	}
	
	public void createTitle() {
		title = new JLabel("FOUR");
		title.setFont(new Font("Futurist Fixed-width", Font.PLAIN, 80));
		title.setBounds(70, 100, 340, 70);
		add(title);
		subTitle = new JLabel("Full of joy");
		subTitle.setFont(new Font("Futurist Fixed-width", Font.PLAIN, 25));
		subTitle.setBounds(90, 200, 300, 70);
		add(subTitle);
	}
	
	public void createIntro() {
		pi1 = new ImageIcon(MainPage.class.getResource("/resources/pic1.png"));
		pic1 = new JLabel(pi1);
		pic1.setBounds(450, 30, 300, 300);
		add(pic1);
		pb1 = new ImageIcon(MainPage.class.getResource("/resources/picbar1.png"));
		picbar1 = new JLabel(pb1);
		picbar1.setBounds(450, 340, 300, 25);
		add(picbar1);
	}
	
}
