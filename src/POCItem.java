import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.JLabel;

public class POCItem extends JLabel{
	
	
	private boolean isCorrect;
	private boolean isUsed;
	private int pos;
	private String qid;
	
	public POCItem() {
		setFont(new Font("Times New Roman", Font.BOLD, 40));
	}
	
	public POCItem(String str) {
		setText(str);
		setFont(new Font("Times New Roman", Font.BOLD, 40));
	}
	
	
	public POCItem(String str, boolean isCorrect) {
		setText(str);
		this.isCorrect = isCorrect;
		setFont(new Font("Times New Roman", Font.BOLD, 40));
	}
	
	public POCItem(String qid, String str, boolean isCorrect) {
		setText(str);
		this.qid = qid;
		this.isCorrect = isCorrect;
		setFont(new Font("Times New Roman", Font.BOLD, 40));
	}
	
	public boolean checkAnswer() {
		return isCorrect;
	}
	
	public boolean checkStatus() { //True: is used False: is unused
		return isUsed;
	}
	
	public int getPos() {
		return pos;
	}
	
	public String getQID() {
		return qid;
	}
	
	public void setPos(int p) {
		pos = p;
	}
	
	public void setAns(boolean a) {
		isCorrect = a;
	}
	
	public void use() {
		isUsed = true;
	}
	
	
}
