
public class Answer {
	String name;
	Boolean rightOrWrong;
	
	public Answer(String name, Boolean rightOrWrong) {
		this.name = name;
		this.rightOrWrong = rightOrWrong;
	}
	
	public boolean checkAnswer() {
		return this.rightOrWrong;
	}
	
	public String getName() {
		return this.name;
	}
	
}
