
public class POCQuestion {

	private String qid;
	private String content;
	private int amount;
	
	public POCQuestion(String qid, String cont, int amount) {
		this.qid = qid;
		this.content = cont;
		this.amount = amount;
	}
	
	public String getQID(){
		return qid;
	}
	public String getQuestion() {
		return content;
	}
	
	public int getAmount() {
		return amount;
	}
}
