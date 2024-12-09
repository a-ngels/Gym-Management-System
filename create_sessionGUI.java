import java.awt.*;
import javax.swing.*;

public class create_sessionGUI extends JFrame {

   // variables
   private JTextField name_field, trainer_field, location_field, date_field, time_field, price_field;
   private JLabel id_label, revenue_label, class_size_label;
   private JButton add_session_btn, back_btn;
   private JComboBox<String> type_field;

   private static int id_count = 1;
   private int class_size = 0;

   // constructor
   public create_sessionGUI(Gym gym) {

      // create buttons for gui
      set_buttons();

      // set up general information
      setTitle("Add New Session");
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setMinimumSize(new Dimension(500, 600));
      this.setLayout(new GridLayout(10, 2, 0, 10));

      // add components and listeners
      add_components();
      add_listeners(gym);

      this.setVisible(true);
   }

   // methods
   private void set_buttons() {
      id_label = new JLabel("Session ID: " + Session.getNumSessions());
      name_field = new JTextField();
      trainer_field = new JTextField();
      type_field = new JComboBox<String>(new String[] { "Yoga", "Pilates", "Zumba", "CrossFit",
            "Spinning", "Kickboxing", "Circuit Training", "Weight Training", "Boxing",
            "HIIT (High-Intensity Interval Training)", "Cycling", "1-on-1", "Other" });
      location_field = new JTextField();
      date_field = new JTextField();
      time_field = new JTextField();
      price_field = new JTextField();
      revenue_label = new JLabel("Revenue: 0.0");
      class_size_label = new JLabel("Class Size: " + class_size);

      add_session_btn = new JButton("Add Session");
      back_btn = new JButton("Back");

      // init values for testing
      name_field.setText("cycling with bob");
      trainer_field.setText("bob");
      type_field.setSelectedIndex(10);
      location_field.setText("crunch astor");
      date_field.setText("11/24/2024");
      time_field.setText("11:00am");
      price_field.setText("20");
      // delete for release
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

   private void add_listeners(Gym gym) {
      back_btn.addActionListener(l -> dispose());
      
      add_session_btn.addActionListener(l -> {
         addSession(gym, name_field.getText(), trainer_field.getText(), (String) type_field.getSelectedItem(),
               location_field.getText(), date_field.getText(),
               time_field.getText(), Double.parseDouble(price_field.getText()));
      });

   }

   //private int new_session_id() {
      //return id_count++;
   //}

   //private void reset_fields() {
      //name_field.setText("");
      //trainer_field.setText("");
      //location_field.setText("");
      //date_field.setText("");
      //time_field.setText("");
      //price_field.setText("");
   //}

   private void addSession(Gym gym, String name, String trainer, String type, String location, String date, String time,
         double price) {
      Session temp = gym.createSession(name, trainer, type, location, date, time, price);
      System.out.println(temp.toString());
      dispose();
   }

}
