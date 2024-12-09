import java.awt.*;
import javax.swing.*;

public class mainGUI extends JFrame {

   //gym
   private Gym gym;

   // variables
   private JButton trainer_btn, user_btn;

   // constructor
   public mainGUI() {

      gym = new Gym();

      // set buttons for gui
      set_buttons();

      // set up general information
      setTitle("Gym Management System");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setMinimumSize(new Dimension(500, 300));

      // format frame 
      this.setLayout(new FlowLayout());     

      // set buttons and listeners
      add_components();
      add_listeners();



      this.setVisible(true);
   }


   // methods
   private void set_buttons () {
      trainer_btn = new JButton("Gym Trainer");
      user_btn = new JButton("Gym User");

      trainer_btn.setPreferredSize(new Dimension(175, 75));
      user_btn.setPreferredSize(new Dimension(175, 75));
   }

   private void add_components() {
      this.add(trainer_btn);
      this.add(user_btn);
   }

   private void add_listeners () {
      trainer_btn.addActionListener(l -> new trainerGUI(gym));
      user_btn.addActionListener(l -> new userGUI(gym));
   }

}
