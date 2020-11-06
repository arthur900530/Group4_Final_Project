import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException; 
import java.sql.Statement;
import java.util.ArrayList;

public class News {	 
	private Connection con = null;
	private Statement stat = null; 
	private ResultSet rs1 = null;
	private PreparedStatement pst = null;
	private String selectSQL1 = "select * from news ";
	private News news;
	private ArrayList<String> newsArrayList;
	
	
		public News() 
		{ 
			   try { 
			     Class.forName("com.mysql.cj.jdbc.Driver"); 
			      //註冊driver 
			     con = DriverManager.getConnection("jdbc:mysql://140.119.19.73:9306/TG04?serverTimezone=UTC"
			    	,"TG04","98ffe7"); 
			      //取得connection
	
			      
			    } 
			    catch(ClassNotFoundException e) 
			    { 
			      System.out.println("DriverClassNotFound :"+e.toString()); 
			    }//有可能會產生sqlexception 
			    catch(SQLException x) { 
			      System.out.println("Exception :"+x.toString()); 
			    }
		}
		public void setNews(News news1) {
			news=new News();
			this.news=news1;
		}
		public ArrayList<String> getNews() {
			try {
				newsArrayList=new ArrayList<String>();
				stat = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			    rs1 = stat.executeQuery(selectSQL1);
			    ResultSetMetaData rm = rs1.getMetaData();
			    int cnum = rm.getColumnCount();
			    rs1.last();                              //move the cursor to the last row
			    int rnum = rs1.getRow();         //get the number of rows
			    rs1.beforeFirst();
			   
			    while(rs1.next())
			    {
			    	String s="";
			    	for(int i=1; i<=rnum; i++) {
			    		
			    
			    			rs1.absolute(i);
			    			s=rs1.getString(3);
			    		
			    		newsArrayList.add(s.substring(3));
			    	}
			    } 
			}
			catch(SQLException e) 
		    { 
		      System.out.println("DropDB Exception :" + e.toString()); 
		    } 
		    finally 
		    {
		      Close(); 
		    }
			return newsArrayList; 
		}
	
		  
		 public void SelectTable() 
		  { 
		    try 
		    { 
		      stat = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
		      rs1 = stat.executeQuery(selectSQL1);
		      ResultSetMetaData rm = rs1.getMetaData();
		      int cnum = rm.getColumnCount();
		      rs1.last();                              //move the cursor to the last row
		      int rnum = rs1.getRow();         //get the number of rows
		      rs1.beforeFirst();
		      String topic=String.format("%1s%22s%9s","Date","Source","Content");
		      System.out.println(topic);
		      while(rs1.next())
		      {
			      for(int i=1; i<=rnum; i++) {
			    	  for(int j=1;j<=cnum;j++){
			    		  rs1.absolute(i);
			    		  System.out.print(rs1.getString(j)+" ");
				     }
			    	  System.out.println("");  
			      }
		      }
		    }  
		    catch(SQLException e) 
		    { 
		      System.out.println("DropDB Exception :" + e.toString()); 
		    } 
		  }
		 private void Close() {
			 try 
			 { 
			      if(rs1!=null) 
			      { 
			        rs1.close(); 
			        rs1 = null; 
			      } 
			      if(stat!=null) 
			      { 
			        stat.close(); 
			        stat = null; 
			      } 
			      if(pst!=null) 
			      { 
			        pst.close(); 
			        pst = null; 
			      } 
			 } 
			 catch(SQLException e) 
			    { 
			      System.out.println("DropDB Exception :" + e.toString()); 
			    }  
			     
		  }
	public static void main(String[] args) {
		 News test = new News(); 
		 test.SelectTable();
	}

}

