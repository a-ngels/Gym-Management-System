

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class userGUI extends JFrame{

   // variables 
   private JButton create_btn, modify_btn, delete_btn, search_btn, view_schedule_btn, back_btn;
   private DefaultTableModel usersTableModel;
   private JTable usersTable;
   private JScrollPane usersScrollPane;
   private JPanel bottom;
   private JSplitPane main;

   // constructor
   public userGUI(Gym gym) {

      // create buttons and adjust sizes for gui
      set_components(gym);

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
   private void set_components(Gym gym) {
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

      // initialize table and scrollpane
      usersTableModel = new DefaultTableModel()
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

   private String[][] usersToJTable(Gym gym) {
      ArrayList<String[]> temp = new ArrayList<>();
      for (User u : gym.get_users()) {
         temp.add(
               new String[] { String.format("%d", s.getId()), s.getDate(), s.getTime(), s.getName(), s.getTrainer() });
      }
      return temp.toArray(new String[0][0]);
   }

   
}
