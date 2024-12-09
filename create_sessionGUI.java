import java.awt.*;
import javax.swing.*;

public class create_sessionGUI extends JFrame {

   // variables
   private JTextField name_field, type_field, trainer_field, location_field, date_field, time_field, price_field;
   private JLabel id_label, revenue_label, class_size_label;
   private JButton add_session_btn, back_btn;

   private static int id_count = 1;
   private int class_size = 0;

   // constructor
   public create_sessionGUI() {

      // create buttons for gui
      set_buttons();

      // set up general information 
      setTitle("Add New Session");
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setMinimumSize(new Dimension(500, 600));
      this.setLayout(new GridLayout(10, 2, 0, 10));

      // add components and listeners
      add_components();
      add_listeners();


      this.setVisible(true);
   }

   // methods
   private void set_buttons () {
      id_label = new JLabel("Session ID: " + new_session_id());
      name_field = new JTextField();
      trainer_field = new JTextField();
      type_field = new JTextField();
      location_field = new JTextField();
      date_field = new JTextField();
      time_field = new JTextField();
      price_field = new JTextField();
      revenue_label = new JLabel("Revenue: 0.0");
      class_size_label = new JLabel("Class Size: " + class_size);

      add_session_btn = new JButton("Add Session");
      back_btn = new JButton("Back");
   }

   private void add_components() {
      this.add(new JLabel("Session ID:"));
      this.add(id_label);

      this.add(new JLabel("Name:"));
      this.add(name_field);

      this.add(new JLabel("Trainer:"));
      this.add(trainer_field);

      this.add(new JLabel("Type:"));
      this.add(type_field);

      this.add(new JLabel("Location:"));
      this.add(location_field);

      this.add(new JLabel("Date:"));
      this.add(date_field);
      
      this.add(new JLabel("Time:"));
      this.add(time_field);

      this.add(new JLabel("Price:"));
      this.add(price_field);

      this.add(class_size_label);
      this.add(revenue_label);
      this.add(add_session_btn);
      this.add(back_btn);
   }

   private void add_listeners() {
      back_btn.addActionListener(l -> dispose());
      
   }

   private int new_session_id () {
      return id_count++;
   }

   private void reset_fields() {
      name_field.setText("");
      trainer_field.setText("");
      type_field.setText("");
      location_field.setText("");
      date_field.setText("");
      time_field.setText("");
      price_field.setText("");
   }

   
   
}
