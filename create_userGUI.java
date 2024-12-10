import java.awt.*;
import javax.swing.*;

public class create_userGUI extends JFrame {

   // variables
   private JTextField fname_field, lname_field, phone_field, email_field;
   private JLabel id_label;
   private JButton create_btn, back_btn;

   private static int id_count = 1;

   // constructor 
   public create_userGUI(Gym gym) {

      // create buttons for gui
      set_buttons();

      // set up general information
      setTitle("Add New User");
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setMinimumSize(new Dimension(400, 500));
      setLayout(new GridLayout(6, 2, 10, 10));

      // add components and listeners
      add_components();
      add_listeners(gym);


      this.setVisible(true);
   }

   // methods
   private void set_buttons () {
      id_label = new JLabel("User ID: " + new_session_id());
      fname_field = new JTextField();
      lname_field = new JTextField();
      phone_field = new JTextField();
      email_field = new JTextField();

      create_btn = new JButton("Create");
      back_btn = new JButton("Back");

      // values for testing
      fname_field.setText("bob");
      lname_field.setText("Smith");
      phone_field.setText("333");
      email_field.setText("gmail");
      // delete for release
   }

   private void add_components() {
      this.add(new JLabel("User ID:"));
      this.add(id_label);

      this.add(new JLabel("First Name:"));
      this.add(fname_field);

      this.add(new JLabel("Last Name:"));
      this.add(lname_field);

      this.add(new JLabel("Phone Number:"));
      this.add(phone_field);

      this.add(new JLabel("Email:"));
      this.add(email_field);

      this.add(create_btn);
      this.add(back_btn);
   }

   private void add_listeners(Gym gym) {
      back_btn.addActionListener(l -> dispose());
      create_btn.addActionListener(l -> {
         addUser(gym, fname_field.getText(), lname_field.getText(), phone_field.getText(), email_field.getText());
      });
   }

   private void resetFields() {
      fname_field.setText("");
      lname_field.setText("");
      phone_field.setText("");
      email_field.setText("");
   }
   
   private int new_session_id () {
      return id_count++;
   }

   private void addUser(Gym gym, String fname, String lname, String num, String email) {
      User temp = gym.createUser(fname, lname, num, email);
      System.out.println(temp.toString());
      dispose();
   }
}
