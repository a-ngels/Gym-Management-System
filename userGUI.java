

import java.awt.*;
import javax.swing.*;

public class userGUI extends JFrame{

   // variables 
   private JButton create_btn, modify_btn, delete_btn, search_btn, view_schedule_btn, back_btn;
   

   // constructor
   public userGUI(Gym gym) {

      // create buttons and adjust sizes for gui
      set_buttons();

      // set up general inforamtion
      setTitle("Gym User");
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setMinimumSize(new Dimension(500, 300));

      // format frame 
      this.setLayout(new FlowLayout());

      // add buttons and listerners
      add_components();
      add_listeners();


      this.setVisible(true);
   }

   // methods
   private void set_buttons () {
      create_btn = new JButton("Create");
      modify_btn = new JButton("Modify");
      delete_btn = new JButton("Delete");
      search_btn = new JButton("Search");
      view_schedule_btn = new JButton("Schedule");
      back_btn = new JButton("Back");

      create_btn.setPreferredSize(new Dimension(100, 75));
      modify_btn.setPreferredSize(new Dimension(100, 75));
      delete_btn.setPreferredSize(new Dimension(100, 75));
      search_btn.setPreferredSize(new Dimension(100, 75));
      view_schedule_btn.setPreferredSize(new Dimension(100, 75));
      back_btn.setPreferredSize(new Dimension(100, 75));
   }

   private void add_components() {
      this.add(create_btn);
      this.add(modify_btn);
      this.add(delete_btn);
      this.add(search_btn);
      this.add(view_schedule_btn);
      this.add(back_btn);
   }

   private void add_listeners() {
      back_btn.addActionListener(l -> dispose());
      create_btn.addActionListener(l -> new create_userGUI());
   }

   
}
