package DB_conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db_conn {
	String dbURL= "jdbc:mysql://localhost/member?serverTimezone=Asia/Seoul";
	String id= "root";
	String pass= "1111";
	
	Connection conn;
	
	public db_conn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("데이터베이스 연결중");
			conn = DriverManager.getConnection(dbURL,id,pass);
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e) {
			System.out.println("연결 실패" + e);
		}
		 catch (ClassNotFoundException e) {
			System.out.println("jdbc 드라이버를 찾을 수 없습니다");
		 }
	}
	
	public static void main(String[] args) {
		new db_conn();
	}
	
}
