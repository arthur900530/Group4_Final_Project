import java.util.ArrayList;

public class Question {
	
	private String name;
	private Answer answer1;
	private Answer answer2;
	private Answer answer3;
	private Answer answer4;
	private ArrayList<Answer> answerList;
	
	public Question(String name, Answer answer1, Answer answer2, Answer answer3, Answer answer4) {
		this.name = name;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		answerList = new ArrayList<Answer>();
		answerList.add(answer1);
		answerList.add(answer2);
		answerList.add(answer3);
		answerList.add(answer4);
	}

	public String getQuestion() {
		return name;
	}
	
	public ArrayList<Answer> getAnswerList(){
		return this.answerList;
	}
	
	
	
	
	
	
	
}
