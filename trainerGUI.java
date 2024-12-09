

import java.awt.*;
import javax.swing.*;

public class trainerGUI extends JFrame{

   // variables
   private JButton create_btn;
   private JButton modify_btn;
   private JButton delete_btn;
   private JButton classlist_btn;
   private JButton back_btn;

   // constructor
   public trainerGUI() {

      // create buttons and adjust sizes
      set_buttons();

      // set up general inforamtion
      setTitle("Gym Trainer");
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setSize(500, 300);

      // format frame 
      this.setLayout(new FlowLayout());
      
      // add buttons and event listeners to frame
      add_components();
      add_listeners();

      this.setVisible(true);
   }

   // methods
   private void set_buttons () {
      create_btn = new JButton("Create");
      modify_btn = new JButton("Modify");
      delete_btn = new JButton("Delete");
      classlist_btn = new JButton("Class List");
      back_btn = new JButton("Back");

      create_btn.setPreferredSize(new Dimension(100, 75));
      modify_btn.setPreferredSize(new Dimension(100, 75));
      delete_btn.setPreferredSize(new Dimension(100, 75));
      classlist_btn.setPreferredSize(new Dimension(100, 75));
      back_btn.setPreferredSize(new Dimension(100, 75));
   }


   private void add_components() {
      this.add(create_btn);
      this.add(modify_btn);
      this.add(delete_btn);
      this.add(classlist_btn);
      this.add(back_btn);
   }
 
   private void add_listeners() {
      back_btn.addActionListener(l -> dispose());
   }

}
