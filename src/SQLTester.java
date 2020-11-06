import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLTester {
	
	public static void main(String[] args) throws SQLException {
		
		ArrayList<POCQuestion> questionList = new ArrayList<>();;
		ArrayList<POCItem> itemList = new ArrayList<>();;
		
		String server = "jdbc:mysql://140.119.19.73:9306/";
		String database = "TG04";
		String config= "?useUnicode=true&characterEncoding=utf8";
		String url = server + database + config;
		String username = "TG04"; 
		String password = "98ffe7";
		Connection conn = null;
		
		try {
			conn =DriverManager.getConnection(url, username, password);
			PreparedStatement stat = conn.prepareStatement("SELECT Qid, Content, Amount FROM POC_question_test");
			boolean hasResult = stat.execute();
			if(hasResult) {
				ResultSet rs = stat.executeQuery(); 
				while(rs.next()) {
					questionList.add(new POCQuestion(rs.getString("Qid") , rs.getString("Content"), rs.getInt("Amount")));
				}
			}
			for(POCQuestion q : questionList) {
				System.out.println("ID: " + q.getQID() + "\nQuestion: " + q.getQuestion() +"\nAmount: " + q.getAmount());
			}
			
			stat = conn.prepareStatement("SELECT Qid, Content, Answer FROM POC_answer_test");
			hasResult = stat.execute();
			if(hasResult) {
				ResultSet rs = stat.executeQuery(); 
				while(rs.next()) {
					itemList.add(new POCItem(rs.getString("Qid") , rs.getString("Content"), rs.getBoolean("Answer")));
				}
			}
			for(POCItem i : itemList) {
				System.out.println("ID: " + i.getQID() + "\nQuestion: " + i.getText() +"\nAmount: " + i.checkAnswer());
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
	}
}
