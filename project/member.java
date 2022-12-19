package DB_conn;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;

public class member extends JFrame implements ActionListener, MouseListener{
	JTable table;
	DefaultTableModel dtm;
	JScrollPane scroll;
		
	JPanel top_panel;
	JPanel mid_Panel;
	JPanel cont_Panel;
	JPanel table_Panel;
	JPanel bot_Panel;
	
	JTextField name_text;
	JTextField age_text;
	
	JRadioButton gen_man_radio;
	JRadioButton gen_woman_radio;
	
	JTextField adress_text;
	JTextField pnmber1_text;
	JTextField pnmber2_text;
	JTextField pnmber3_text;
	
	JTextField mail1_text;
	JTextField mail2_text;
	JComboBox mail_box;
	
	JComboBox search_box;
	JTextField search_text;
	JButton search_btn;
	JButton search_all_btn;
	
	JButton add_btn;
	JButton edit_btn;
	JButton delete_btn;
	JButton exit_btn;
	
	String driver = "com.mysql.jdbc.Driver";
	String user="root";
	String pass="1111";
	String dbURL="jdbc:mysql://localhost/member";
	
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	Resultset rs= null;
	
	Font f1,f2;
	int selRow=0;
	// 전체 구조
	
	public member() { //생성자
	 this.setTitle("자바 주소록 관리 프로그램");
	 this.setBounds(100,100,600,650);
	 
	 Toolkit tk= Toolkit.getDefaultToolkit();
	 Image logo= tk.getImage("C:\\Users\\admin\\workspace-jsp\\javadb\\image");
	 this.setIconImage(logo);
	}
	
	
	
public static void main(String[] args) { //main
	new member();
}

}

