package soccer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

public class delete extends JFrame implements ActionListener{
	
	int idx;
	int selRow=-1;
	

	DB_connect dbconn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs= null;
	
	TitledBorder border_content;
	TitledBorder border_table;
	
	JTable table;
	DefaultTableModel dtm;
	JScrollPane scroll;
	
	JPanel panel_top;
	JPanel panel_content;
	JPanel panel_bot;
	
	JLabel label_name;
	JLabel label_title;
	
	JTextField text_name;
	
	JButton btn_main;
	JButton btn_delete;

	
	
	
	public delete() {   //=====생성자=====
		this.setTitle("world cup player");
		this.setBounds(200,200,700,750);
		this.setLayout(null);
		
		panel_top=new JPanel(); //=====top 패널 =====
		panel_top.setLayout(null);
		panel_top.setBounds(0,0,700,65);
		panel_top.setBackground(Color.BLACK);
		
		label_title=new JLabel("WORLD CUP 선수 삭제");
		label_title.setForeground(Color.WHITE);
		label_title.setFont(new Font("돋움", Font.BOLD, 23));
		label_title.setBounds(100,10,300,50);
		panel_top.add(label_title);
		
		panel_content=new JPanel(); //=====content 패널=====
		panel_content.setLayout(null);
		panel_content.setBounds(0,75,700,400);
		
	
		
		label_name=new JLabel("이름");
		
		btn_delete=new JButton("삭제");
		btn_delete.setBounds(450,45,80,30);
		btn_delete.setBackground(Color.pink);
		btn_delete.addActionListener(this);
		

		
		text_name=new JTextField();
		
		
		label_name.setBounds(50,50,40,40);
		
		
		text_name.setBounds(110,60,200,25);
		
		
		panel_content.add(label_name);
	
		panel_content.add(btn_delete);
		
		panel_content.add(text_name);
		
		
		//=====테이블 패널=====
		String titleName[]= {"index","이름","나이","몸무게","포지션","등번호","연고지"}; 
		Object[][] rowData= {};
		
		
		
		dtm=new DefaultTableModel(rowData,titleName);
		table=new JTable(dtm);
		scroll=new JScrollPane(table);
		scroll.setBounds(0, 250, 700,200);
		
		border_table = new TitledBorder("테이블");
		scroll.setBorder(border_table);

		
		//=====bot 패널=====
		
		panel_bot=new JPanel();
		panel_bot.setLayout(null);
		panel_bot.setBounds(0,620,700,100);
		
		btn_main=new JButton("메인 메뉴");
		btn_main.setFont(new Font("돋움", Font.BOLD, 23));
		btn_main.setBounds(150,20,150,50);
		btn_main.setBackground(Color.pink);
		btn_main.setForeground(Color.white);
		btn_main.addActionListener(this);
		
	
		
		panel_bot.add(btn_main);
		
		add(panel_bot);
		this.add("center",scroll);
		add(panel_top);
		add(panel_content);
		
		dbconn = new DB_connect();   
		Player_display();
		
		setVisible(true);
	}

	private void Player_display() {
		dtm.setRowCount(0);
	try {
		String query="select * from player";
		pstmt = dbconn.conn.prepareStatement(query); //dbconn(DB_connect 클래스의 conn 오브젝트의 쿼리를 사용한다);
		rs = pstmt.executeQuery();//executeQuery 는 int 값으로 반환되기 때문에 String 타입으로 변경
		
		while(rs.next()) {
			String idx=rs.getString(1);
			String name=rs.getString(2);
			String age=rs.getString(3);
			String weight=rs.getString(4);
			String position=rs.getString(5);
			String backnum=rs.getString(6);
			String hometown=rs.getString(7);
			
			
			Object[] rowData= {idx,name,age,weight,position,backnum,hometown};
			dtm.addRow(rowData);
			
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}

public static void main(String[] args) {
	new delete();
}
@Override
public void actionPerformed(ActionEvent e) {
	Object o=e.getSource();
	int result=0;
	
	if(o==btn_delete) {
//		dtm.setRowCount(0);
		if(text_name.getText().length()==0) {
			JOptionPane.showMessageDialog(this,"삭제할 선수을 입력하시오",
					 getTitle(), JOptionPane.WARNING_MESSAGE);
			text_name.requestFocus();
			Player_display();
			return;
		}
		else {
			
		
		String query="Delete from player where name=?";
		
		try {
			System.out.println(idx);
			pstmt = dbconn.conn.prepareStatement(query);
			
			pstmt.setString(1, text_name.getText());
			
			result= pstmt.executeUpdate();
			System.out.println(result);
			
			clear();
			Player_display();
		} catch (Exception e2) {
			
		}
		
	}
	}
	if(o==btn_main) {
		this.dispose();
		mainGui mainGui = new mainGui(null);
		mainGui.setVisible(true);
	}
				
	}

private void clear() {
	text_name.setText(null);

}
}
