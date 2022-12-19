package soccer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
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

import com.mysql.cj.protocol.ValueDecoder;
import com.mysql.cj.xdevapi.Statement;

import DB_conn.db_conn;

public class worldcup extends JFrame implements ActionListener, MouseListener,KeyListener{
	
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
	
	JLabel label_title;
	JLabel label_input;
	JLabel label_name;
	JLabel label_age;
	JLabel label_weight;
	JLabel label_position;
	JLabel label_backnumber;
	JLabel label_hometown;
	
	JTextField text_name;
	JTextField text_age;
	JTextField text_weight;
	JTextField text_position;
	JTextField text_backnumber;
	JTextField text_hometown;
	
	JButton btn_main;
	JButton btn_add;
	
	
	
	public worldcup() {   //=====생성자=====
		this.setTitle("world cup player");
		this.setBounds(200,200,700,750);
		this.setLayout(null);
		
		panel_top=new JPanel(); //=====top 패널 =====
		panel_top.setLayout(null);
		panel_top.setBounds(0,0,700,65);
		panel_top.setBackground(Color.BLACK);
		
		label_title=new JLabel("WORLD CUP 선수 추가");
		label_title.setForeground(Color.WHITE);
		label_title.setFont(new Font("돋움", Font.BOLD, 23));
		label_title.setBounds(100,10,300,50);
		panel_top.add(label_title);
		
		panel_content=new JPanel(); //=====content 패널=====
		panel_content.setLayout(null);
		panel_content.setBounds(0,75,700,400);
		
		border_content = new TitledBorder("입력");
		panel_content.setBorder(border_content);
		
		label_name=new JLabel("이름");
		label_age=new JLabel("나이");
		label_weight=new JLabel("몸무게");
		label_position=new JLabel("포지션");
		label_backnumber=new JLabel("등번호");
		label_hometown=new JLabel("연고지");
		
		text_name=new JTextField();
		text_age=new JTextField();
		text_weight=new JTextField();
		text_position=new JTextField();
		text_backnumber=new JTextField();
		text_hometown=new JTextField();
		
		label_name.setBounds(50,50,40,40);
		label_age.setBounds(50,100,40,40);
		label_weight.setBounds(50,150,40,40);
		label_position.setBounds(50,200,40,40);
		label_backnumber.setBounds(50,250,40,40);
		label_hometown.setBounds(50,300,40,40);
		
		text_name.setBounds(170,60,200,25);
		text_age.setBounds(170,110,200,25);
		text_weight.setBounds(170,160,200,25);
		text_position.setBounds(170,210,200,25);
		text_backnumber.setBounds(170,260,200,25);
		text_hometown.setBounds(170,310,200,25);
		
		panel_content.add(label_name);
		panel_content.add(label_age);
		panel_content.add(label_weight);
		panel_content.add(label_position);
		panel_content.add(label_backnumber);
		panel_content.add(label_hometown);
		
		panel_content.add(text_name);
		panel_content.add(text_age);
		panel_content.add(text_weight);
		panel_content.add(text_position);
		panel_content.add(text_backnumber);
		panel_content.add(text_hometown);
		
		//=====테이블 패널=====
		String titleName[]= {"index","이름","나이","몸무게","포지션","등번호","연고지"}; 
		Object[][] rowData= {};
		
		
		
		dtm=new DefaultTableModel(rowData,titleName);
		table=new JTable(dtm);
		scroll=new JScrollPane(table);
		scroll.setBounds(0, 420, 700,200);
		
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
		
		btn_add=new JButton("선수 추가");
		btn_add.setFont(new Font("돋움", Font.BOLD, 23));
		btn_add.setBounds(350,20,150,50);
		btn_add.setBackground(Color.CYAN);
		btn_add.setForeground(Color.white);
		btn_add.addActionListener(this);
		
		panel_bot.add(btn_main);
		panel_bot.add(btn_add);
		
		add(panel_bot);
		this.add("center",scroll);
		add(panel_top);
		add(panel_content);
		
		dbconn = new DB_connect();   
		Player_display();
		
		setVisible(true);
	}
	
	private void Player_display() {
		
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

	public static void main(String[] args) { //=====main=====
		new worldcup();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob=e.getSource();
		
		if(ob==btn_main) { 
			this.dispose();
			mainGui mainGui = new mainGui(null);
			mainGui.setVisible(true);
		}
		
		if(ob==btn_add) {
			
			if(text_name.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "선수이름을 입력하세요");
				clear();
				text_name.requestFocus();
				return;
			}
			else {
				int i1 = JOptionPane.showConfirmDialog(this,text_name.getText()+"선수를 추가할?",
					 getTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
				
				if(i1==JOptionPane.YES_OPTION) {
					int result=0;
					
					String query="Select * from player where name = '" + text_name.getText()+"'";
					try {
						pstmt = dbconn.conn.prepareStatement(query);
						rs=pstmt.executeQuery();
						
						if(rs.next()) {
							JOptionPane.showMessageDialog(this,text_name.getText()+ "선수는 이미 등록되있음");
							return;
						}
						
						query="Insert into player(idx,name,age,weight,position,backNumber,Hometown)"+"values(?,?,?,?,?,?,?)";
						pstmt = dbconn.conn.prepareStatement(query);
						pstmt.setInt(1, 0);
						pstmt.setString(2,text_name.getText());
						pstmt.setString(3,text_age.getText());
						pstmt.setString(4,text_weight.getText());
						pstmt.setString(5,text_position.getText());
						pstmt.setString(6,text_backnumber.getText());
						pstmt.setString(7,text_hometown.getText());
						
						result=pstmt.executeUpdate();
						clear();
						Player_display();
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		}
		
	}

	private void clear() {
		text_name.setText(null);
		text_age.setText(null);
		text_weight.setText(null);
		text_position.setText(null);
		text_backnumber.setText(null);
		text_hometown.setText(null);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int ch= e.getKeyCode();
		
		if(ch==KeyEvent.VK_UP) {
			if(text_name.isFocusOwner()) {
				text_name.requestFocus();
			}
			else if(text_age.isFocusOwner()) {
				text_name.requestFocus();
			}
			else if(text_weight.isFocusOwner()) {
				text_age.requestFocus();
			}
			else if(text_position.isFocusOwner()) {
				text_weight.requestFocus();
			}
			else if(text_backnumber.isFocusOwner()) {
				text_position.requestFocus();
			}
			else if(text_hometown.isFocusOwner()) {
				text_backnumber.requestFocus();
			}
		}
		if(ch==KeyEvent.VK_DOWN) {
			if(text_name.isFocusOwner()) {
				text_age.requestFocus();
			}
			else if(text_age.isFocusOwner()) {
				text_weight.requestFocus();
			}
			else if(text_weight.isFocusOwner()) {
				text_position.requestFocus();
			}
			else if(text_position.isFocusOwner()) {
				text_backnumber.requestFocus();
			}
			else if(text_backnumber.isFocusOwner()) {
				text_hometown.requestFocus();
			}
			else if(text_hometown.isFocusOwner()) {
				text_name.requestFocus();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
