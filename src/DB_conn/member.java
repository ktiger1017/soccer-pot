package DB_conn;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.ClientImpl.PooledXProtocol;
import com.mysql.cj.xdevapi.Statement;

public class member extends JFrame implements ActionListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable table;
	DefaultTableModel dtm;
	JScrollPane scroll;
	
	JPanel top_panel;
	JPanel cont_Panel;
	JPanel mid_Panel;
	JPanel bot_Panel;
	
	JTextField name_text;
	JTextField age_text;
	
	CheckboxGroup gen_group;
	Checkbox gen_man_radio;
	Checkbox gen_woman_radio;
	
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
	
	JLabel name_Label;
	JLabel age_Label;
	JLabel gen_Label;
	JLabel gen_man_Label;
	JLabel gen_woman_Label;
	JLabel adress_Label;
	JLabel pnumber_Label;
	JLabel point1;
	JLabel point2;
	JLabel point3;
	JLabel mail_Label;
	
	String driver = "com.mysql.jdbc.Driver";
	String user="root";
	String pass="1111";
	String dbURL="jdbc:mysql://localhost/member";
	String gender; //성별 값 
	String category;
	
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs= null;
	
	Font f1,f2;
	int selRow=0;
	

	
	// 전체 구조
	
	public member() { //생성자
		
	
		
	 this.setTitle("자바 주소록 관리 프로그램");
	 this.setBounds(100,100,600,650);
	 
	 Toolkit tk= Toolkit.getDefaultToolkit();
	 Image logo= tk.getImage("C:\\Users\\admin\\workspace-jsp\\javadb\\image");
	 this.setIconImage(logo);
	 
	 this.setLayout(null);
	 
	 top_panel = new JPanel();
	 top_panel.setLayout(null);
	 top_panel.setBounds(0,0,600,50);
	 top_panel.setBackground(Color.black);
	 
	 JLabel title = new JLabel("주소록 관리 프로그램");
	 title.setBounds(180,15,600,25);
	 
	 f1=new Font("돋움", Font.BOLD, 23);
	 f2=new Font("serif", Font.BOLD, 20);
	 
	 title.setFont(f1);
	 title.setForeground(Color.white);
	
	 
	 top_panel.add(title);
	 add(top_panel);
	 
	// ===== cont 패널 시작=====
	 name_Label=new JLabel("이름");
	 name_text=new JTextField(20);
	 age_Label=new JLabel("나이");
	 age_text=new JTextField(10);
	 
	 gen_Label=new JLabel("성별");
	 gen_group = new CheckboxGroup();
	 gen_man_radio=new Checkbox("남",true);
	 gen_woman_radio=new Checkbox("여",false);
	 
	 adress_Label=new JLabel("주소");
	 adress_text= new JTextField(45);
	 
	 pnumber_Label= new JLabel("연락처");
	 pnmber1_text=new JTextField();
	 point1= new JLabel("-");
	 pnmber2_text=new JTextField();
	 point2= new JLabel("-");
	 pnmber3_text=new JTextField();
	 
	 mail_Label=new JLabel("이메일");
	 mail1_text=new JTextField();
	 point3=new JLabel("@");
	 mail2_text=new JTextField();
	 
	 mail_box=new JComboBox();
	 mail_box.addItem("직접입력");
	 mail_box.addItem("naver.com");
	 mail_box.addItem("daum.com");
	 mail_box.addItem("google.com");
	 mail_box.addItem("nate.com");
	 
	 cont_Panel=new JPanel();
	 cont_Panel.setLayout(null);
	 cont_Panel.setBounds(0,50,600,170);
	 
	 name_Label.setBounds(40,10,30,30);
	 name_text.setBounds(70,15,170,25);
	 
	 age_Label.setBounds(260,10,30,30);
	 age_text.setBounds(295,15,120,25);
	 
	 gen_man_Label=new JLabel("남");
	 gen_woman_Label=new JLabel("여");
	 
	 gen_Label.setBounds(430,10,30,30);
	 gen_man_radio.setBounds(470,15,20,20);
	 gen_man_Label.setBounds(490,15,20,20);
	 gen_woman_radio.setBounds(510,15,20,20);
	 gen_woman_Label.setBounds(530,15,20,20);
	 
	 adress_Label.setBounds(40,50,30,30);
	 adress_text.setBounds(70,55,440,20);
	 
	 pnumber_Label.setBounds(30,70,60,60);
	 pnmber1_text.setBounds(72,90,150,25);
	 point1.setBounds(230,90,20,20);
	 pnmber2_text.setBounds(240,90,150,25);
	 point2.setBounds(400,90,20,20);
	 pnmber3_text.setBounds(410,90,150,25);
	 
	 mail_Label.setBounds(30, 110, 60, 60);
	 mail1_text.setBounds(72,130,150,25);
	 point3.setBounds(230,130,20,20);
	 mail2_text.setBounds(250,130,157,25);
	 mail_box.setBounds(420,130,130,25); 
	 
	 cont_Panel.add(name_Label);
	 cont_Panel.add(name_text);
	 cont_Panel.add(age_Label);
	 cont_Panel.add(age_text);
	 cont_Panel.add(gen_Label);
	 cont_Panel.add(gen_man_radio);
	 cont_Panel.add(gen_woman_radio);
	 cont_Panel.add(gen_man_Label);
	 cont_Panel.add(gen_woman_Label);
	 cont_Panel.add(adress_Label);
	 cont_Panel.add(adress_text);
	 cont_Panel.add(pnumber_Label);
	 cont_Panel.add(pnmber1_text);
	 cont_Panel.add(point1);
	 cont_Panel.add(pnmber2_text);
	 cont_Panel.add(point2);
	 cont_Panel.add(pnmber3_text);
	 cont_Panel.add(mail_Label);
	 cont_Panel.add(mail1_text);
	 cont_Panel.add(point3);
	 cont_Panel.add(mail2_text);
	 cont_Panel.add(mail_box);
	 
	 add(cont_Panel);
	 
	 // ===== cont 패널 끝=====

	//===== mid 패널 시작=====
		 mid_Panel=new JPanel();
		 mid_Panel.setLayout(null);
		 mid_Panel.setBackground(Color.blue);
		 mid_Panel.setBounds(0,210,600,70);
		 
		 search_box=new JComboBox();
		 search_box.addItem("검색할 카테고리를 선택하시오...");
		 search_box.addItem("name");
		 search_box.addItem("age");
		 search_box.addItem("gender");
		 search_box.addItem("adress");
		 search_box.addItem("pnumber");
		 search_box.addItem("mail");
		 search_box.addItem("index");
		
		 search_box.setBounds(30,30,150,20);
		 
		 search_text=new JTextField();
		 search_text.setBounds(200,30,140,20);
		 
		 search_btn=new JButton("조회");
		 search_btn.setBounds(350,25,75,30);
		 search_btn.setForeground(Color.pink);
		 search_btn.setBackground(Color.black);
		 
		 search_all_btn=new JButton("전체조회");
		 search_all_btn.setBounds(440,25,85,30);
		 search_all_btn.setForeground(Color.pink);
		 search_all_btn.setBackground(Color.black);
		 
		 mid_Panel.add(search_box);
		 mid_Panel.add(search_text);
		 mid_Panel.add(search_btn);
		 mid_Panel.add(search_all_btn);
		 
		 search_btn.addActionListener(this);
		search_all_btn.addActionListener(this);
		 
		 add(mid_Panel);
	//===== mid 패널 끝=====
	
	//=====table 패널 시작=====
		 String titleName[]= {"이름","나이","성별","주소","연락처","이메일","INDEX"};
		 Object[][] rowData= {};
		 
		 dtm=new DefaultTableModel(rowData,titleName);
		 table=new JTable(dtm);
		 scroll=new JScrollPane(table);
		 
		 
		 scroll.setBounds(0,280,600,250);
		 this.add("Center",scroll);
		  
	//=====table 패널 끝=====
		 
	//===== bot 패널 시작=====
		 bot_Panel= new JPanel();
		 bot_Panel.setLayout(null);
		 bot_Panel.setBounds(0,530,600,75);
		 
		add_btn=new JButton("입력");
		add_btn.setBounds(5,30,145,40);
		add_btn.setBackground(Color.pink);
		add_btn.setForeground(Color.white);
		add_btn.setFont(f1);
		
		edit_btn=new JButton("수정");
		edit_btn.setBounds(153,30,145,40);
		edit_btn.setBackground(Color.green);
		edit_btn.setForeground(Color.blue);
		edit_btn.setFont(f1);
		
		delete_btn=new JButton("삭제");
		delete_btn.setBounds(301,30,145,40);
		delete_btn.setBackground(Color.white);
		delete_btn.setForeground(Color.pink);
		delete_btn.setFont(f1);
		
		exit_btn=new JButton("종료");
		exit_btn.setBounds(449,30,145,40);
		exit_btn.setBackground(Color.gray);
		exit_btn.setForeground(Color.yellow);
		exit_btn.setFont(f1);
		 
		add_btn.addActionListener(this);
		delete_btn.addActionListener(this);
		edit_btn.addActionListener(this);
		exit_btn.addActionListener(this);
		
		bot_Panel.add(add_btn);
		bot_Panel.add(edit_btn);
		bot_Panel.add(delete_btn);
		bot_Panel.add(exit_btn);
		add(bot_Panel);
	//===== bot 패널 끝=====	 
	 this.setVisible(true);
	 
	 connect();
	 getListAll();
	 
	 
	 mail_box.addItemListener(new ItemListener() {
		
		public void itemStateChanged(ItemEvent e) {
			Object o = e.getSource();
			
			if(o.equals(mail_box)) {
				if(mail_box.getSelectedIndex()==0) {   //콤보박스 첫번째 "직접입력"을 선택시 메일부분을 초기화
					mail2_text.setText("");
				}
				else {
					mail2_text.setText((String)e.getItem());
					System.out.println(e.getItem());
				}
			}
			
			
		}
	});
	 
	 gen_man_radio.addItemListener(new ItemListener() {
		
		public void itemStateChanged(ItemEvent e) {
			gender=(String)e.getItem();
			System.out.println(gender);
		}
	});
	 
	 gen_woman_radio.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
			gender=(String)e.getItem();
			System.out.println(gender);
			
		}
	});
	 
	 search_box.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				category=(String)e.getItem();
				System.out.println(category);
				search_text.requestFocus();
			}
		});
	 
	
	}
	
	
	
private void connect() {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("MYSQL 드라이버 검색 성공");
	} catch (ClassNotFoundException e) {
		System.out.println("MYSQL 드라이버 검색 오류");
	}
	try {
		conn= DriverManager.getConnection(dbURL,user,pass);
		System.out.println("MYSQL 연결 성공");
	} catch (SQLException e) {
		System.out.println("MYSQL 연결 객체 생성 실패" + e);
		System.exit(0);
	}
	}

private void getListAll() {
	try {
		dtm.setRowCount(0);
		
		String query="Select * from member";
		pstmt = conn.prepareStatement(query);
		rs= pstmt.executeQuery();
		
		while(rs.next()) {
			String name = rs.getString(1);
			String age = rs.getString(2);
			String gender= rs.getString(3);
			String adress = rs.getString(4);
			String pnumber= rs.getString(5);
			String mail= rs.getString(6);
			int index= rs.getInt(7);
			
			Object[] rowData= {name,age,gender,adress,pnumber,mail,index};
			dtm.addRow(rowData);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}


public static void main(String[] args) { //main
	new member();
}



@Override
public void mouseClicked(MouseEvent e) {
	selRow= table.getSelectedRow();
	name_text.setText((String)table.getValueAt(selRow, 0));
	age_text.setText((String)table.getValueAt(selRow, 1));
	String g= (String)table.getValueAt(selRow, 2);
	adress_text.setText((String)table.getValueAt(selRow, 3));
	
	String phoneNum = (String)table.getValueAt(selRow, 4);
	String email = (String)table.getValueAt(selRow, 5);
	
	pnmber1_text.setText(phoneNum.substring(0,phoneNum.indexOf("-")));
	pnmber2_text.setText(phoneNum.substring(phoneNum.indexOf("-")+1,phoneNum.lastIndexOf("-")));
	pnmber3_text.setText(phoneNum.substring(phoneNum.lastIndexOf("-")+1));
	
	mail1_text.setText(email.substring(0,email.indexOf("@")));
	mail2_text.setText(email.substring(email.indexOf("@")+1));
	
	if(g==null) {
		gen_man_radio.setState(true);
		gen_woman_radio.setState(false);
	}
	else {
		if(g.equals("남")) {
			gen_man_radio.setState(true);
		}
		else if(g.equals("여")){
			gen_woman_radio.setState(true);
		}
	}
}



private Object indexof(String string) {
	// TODO Auto-generated method stub
	return null;
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
	
	Object o = e.getSource();
	if(o== add_btn) {          // ===========입력==========
		if(name_text.getText().length()==0) {
			JOptionPane.showMessageDialog(this, "내용을 입력하세요");
			name_text.requestFocus();
			return;
		}
		int result= regist();
		if(result !=0) {
			JOptionPane.showMessageDialog(this, "등록");
			getListAll();
			clear();
		}
		else {
			JOptionPane.showMessageDialog(this, "실패");
		}
	}
	
	else if(o==edit_btn) {   //===========수정==========
		if((Integer)dtm.getValueAt(selRow, 6)==0) {
			JOptionPane.showMessageDialog(this,"수정할 행을 선택하세요");
			return;
		}
		selRow=table.getSelectedRow();
		int idx=(Integer) dtm.getValueAt(selRow, 6);
		if(JOptionPane.showConfirmDialog(getParent(), (String)table.getValueAt(selRow, 0)+"님의 정보를 수정하시겠?")==0) {
			int result= edit(idx);
			
			if(result!=0) {
				getListAll();
				clear();
			}
		}
	}
	
	else if(o==delete_btn) {  // ===========삭제==========
		if((Integer)dtm.getValueAt(selRow, 6)==0) {
			JOptionPane.showMessageDialog(this,"삭제할 행을 선택하세요");
			return;
		}
	}
	selRow= table.getSelectedRow();
	  int idx = (Integer) dtm.getValueAt(selRow, 6);
	
	 if(JOptionPane.showConfirmDialog(getParent(),(String) table.getValueAt(selRow, 0)+ " 님의 정보를 삭제 하시겠습니까? ")==0){
         int result = delete(idx);
         
         if(result!=0) {
        	 getListAll();
        	 clear();
         }
	 }
	else if(o==exit_btn) {   //=========종료==========
		System.exit(0);
	}
	
	if(o==search_btn) {
		search(category);
	}
	else if(o==search_all_btn) {
		getListAll();
	}
	
}



private int edit(int idx_a) {
	int idx=idx_a;
	int result=0;
	String sql="UPDATE member SET" + "name=?,age=?,gender=?,adress=?,pnumber=?,mail=? where idx=?";
	  try {
	         pstmt = conn.prepareStatement(sql);         
	         pstmt.setString(1, name_text.getText());
	         pstmt.setString(2, age_text.getText());
	         pstmt.setString(3, gender);
	         pstmt.setString(4, adress_text.getText());
	         pstmt.setString(5, pnmber1_text.getText() + "-" + pnmber2_text.getText() +"-"+ pnmber3_text.getText());
	         pstmt.setString(6, mail1_text.getText() + "@" + mail2_text.getText());
	         pstmt.setInt(7, idx);
	         System.out.print(idx);
	         result = pstmt.executeUpdate();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return result;
	
}



private int delete(int idx) {
	int result=0;
	String query ="Delete from member where idx=?";
	
	try {
		pstmt=conn.prepareStatement(query);
		pstmt.setInt(1, idx);
		result=pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;
}



private void search(String s) {
	try {
		dtm.setRowCount(0);
		if(search_text.getText().length()==0) {
			JOptionPane.showMessageDialog(this,"조회할 내용을 입력하시요",
					s, JOptionPane.WARNING_MESSAGE);
			search_text.requestFocus();
			return;
		}
		String query= "Select * from member where " + s + " = '" + search_text.getText() 
        + "' ";

		
		pstmt = conn.prepareStatement(query);
		System.out.println(query);
		rs= pstmt.executeQuery();
		
		while(rs.next()) {
			String name = rs.getString(1);
			String age = rs.getString(2);
			String gender= rs.getString(3);
			String adress = rs.getString(4);
			String pnumber= rs.getString(5);
			String mail= rs.getString(6);
			int index= rs.getInt(7);
			
			Object[] rowData= {name,age,gender,adress,pnumber,mail,index};
			dtm.addRow(rowData);
	} 
	}catch (Exception e) {
		System.out.println(e);
	}

}


private void clear() {
	name_text.setText("");
	age_text.setText("");
	gen_man_radio.setState(false);
	gen_woman_radio.setState(false);
	adress_text.setText("");
	pnmber1_text.setText("");
	pnmber2_text.setText("");
	pnmber3_text.setText("");
	mail1_text.setText("");
	mail2_text.setText("");
	name_text.requestFocus();
	
}



private int regist() {
	int result=0;
	String query= "Insert into member(name,age,gender,adress,pnumber,mail)"+"values(?,?,?,?,?,?)";
	
	try {
		pstmt= conn.prepareStatement(query);
		pstmt.setString(1, name_text.getText());
		pstmt.setString(2, age_text.getText());
		pstmt.setString(3, gender);
		pstmt.setString(4, adress_text.getText());
		pstmt.setString(5, pnmber1_text.getText()+"-"+pnmber2_text.getText()+"-"+pnmber3_text.getText());
		pstmt.setString(6, mail1_text.getText()+"@"+mail2_text.getText());
		
		result= pstmt.executeUpdate();
		
	} catch (Exception e) {
			e.printStackTrace();
	}
	return result;
}
}