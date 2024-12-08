import java.awt.*;
import javax.swing.*;

public class mainGUI extends JFrame {

   // variables
   private JButton b1;
   private JButton b2;

   public mainGUI() {

      // declare variables
      b1 = new JButton("Gym Trainer");
      b2 = new JButton("J Gym User");

      // set up general information
      setTitle("Gym Management System");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      setSize(500, 300);

      b1.setPreferredSize(new Dimension(175, 75));
      b2.setPreferredSize(new Dimension(175, 75));
      this.setLayout(new FlowLayout());
      this.add(b1);
      this.add(b2);
      



      this.setVisible(true);
   }
}
