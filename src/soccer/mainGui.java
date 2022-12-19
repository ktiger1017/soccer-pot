package soccer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class mainGui extends JFrame implements ActionListener {      
   JPanel nnorthp;
   JPanel northp;

   JLabel title;
   
   JButton input;
   JButton edit;
   JButton delete;
   JButton exit;
   JButton search;
   
   Font f1, f2;   

   public mainGui(String s) {
      this.setTitle("사용자: " + s);
      
      this.setLayout(null);
      this.setBounds(50, 50, 600, 600);      

      nnorthp = new JPanel();
      nnorthp.setLayout(null);
      nnorthp.setBounds(0, 0, 600, 50);

      title = new JLabel("Worldcup Player", JLabel.CENTER);
      title.setBounds(0, 20, 600, 20);

      f1 = new Font("돋움", Font.BOLD, 23);
      f2 = new Font("serif", Font.BOLD, 20);

      title.setFont(f1);
      title.setForeground(Color.WHITE);
      nnorthp.setBackground(Color.BLACK);
      nnorthp.add(title);
      this.add(nnorthp);

      northp = new JPanel();
      northp.setLayout(null);
      northp.setBounds(30, 60, 530, 480);

      northp.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" 메뉴선택 "),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));

      input = new JButton("선수 추가");
      edit = new JButton("선수 수정");
      delete = new JButton("선수 삭제");
      search = new JButton("선수 검색");
      exit = new JButton("종     료");      

      input.setBounds(130, 50, 142, 40);
      edit.setBounds(130, 130, 142, 40);
      delete.setBounds(130, 210, 142, 40);
      search.setBounds(130, 290, 142, 40);
      exit.setBounds(130, 370, 142, 40);

      input.setFont(f1);
      input.setBackground(Color.BLUE);
      input.setForeground(Color.YELLOW);
      edit.setFont(f1);
      edit.setBackground(Color.YELLOW);
      edit.setForeground(Color.BLACK);
      search.setFont(f1);
      search.setBackground(Color.GREEN);
      search.setForeground(Color.RED);
      delete.setFont(f1);
      delete.setBackground(Color.CYAN);
      delete.setForeground(Color.RED);
      exit.setFont(f1);
      exit.setBackground(Color.MAGENTA);
      exit.setForeground(Color.WHITE);

      northp.add(input);
      northp.add(edit);
      northp.add(delete);
      northp.add(search);
      northp.add(exit);
      this.add(northp);      

      input.addActionListener(this);
      edit.addActionListener(this);
      delete.addActionListener(this);
      search.addActionListener(this);
      exit.addActionListener(this);

      this.setVisible(true);      
   }

   
   public void actionPerformed(ActionEvent e) {
      Object o = e.getSource();
      
      if (o == input) {         
         new worldcup();         
         this.dispose();
      }
      if(o == edit) {
    	  new edit();
    	  this.dispose();
      }
      if(o==search) {
    	  new search();
    	  this.dispose();
      }
      if(o==delete) {      
    	  new delete();
    	  this.dispose();
      }
      if(o==exit) {
    	 System.exit(0);
      }
   }
   
   public static void main(String[] args) {
      new mainGui("ria kim");
   }   

}