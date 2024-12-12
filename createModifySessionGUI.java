import java.awt.*;
import javax.swing.*;

public class createModifySessionGUI extends JFrame {

   // variables
   private boolean modify = false;

   private JTextField name_field, trainer_field, location_field, date_field, time_field, price_field;
   private JLabel id_label, revenue_label, class_size_label;
   private JButton add_session_btn, back_btn;
   private JComboBox<String> type_field;

   private static int id_count = 1;
   private int class_size = 0;

   // constructor
   public createModifySessionGUI(Gym gym, Session s) {
      if (s != null)
         modify = true;

      // create buttons for gui
      set_buttons();
      if (s != null)
         modifySettings(s);

      // set up general information
      setTitle("Add New Session");
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setMinimumSize(new Dimension(500, 600));
      this.setLayout(new GridLayout(10, 2, 0, 10));

      // add components and listeners
      add_components();
      add_listeners(gym, s);
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

      add_session_btn = new JButton("Save Session");
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

   private void modifySettings(Session s) {
      id_label = new JLabel("Session ID: " + s.getId());
      name_field = new JTextField(s.getName());
      trainer_field = new JTextField(s.getTrainer());
      type_field = new JComboBox<String>(new String[] { "Yoga", "Pilates", "Zumba", "CrossFit",
            "Spinning", "Kickboxing", "Circuit Training", "Weight Training", "Boxing",
            "HIIT (High-Intensity Interval Training)", "Cycling", "1-on-1", "Other" });
      type_field.setSelectedItem(s.getType());
      location_field = new JTextField(s.getLocation());
      date_field = new JTextField(s.getDate());
      time_field = new JTextField(s.getTime());
      price_field = new JTextField(String.format("%f", s.getPrice()));
      revenue_label = new JLabel(String.format("Revenue: %f", s.calculateRevenue()));
      class_size_label = new JLabel("Class Size: " + s.getClassSize());
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

   private void add_listeners(Gym gym, Session s) {
      back_btn.addActionListener(l -> dispose());
      if (modify) {
         add_session_btn.addActionListener(l -> {
            addSession(gym, name_field.getText(), trainer_field.getText(), (String) type_field.getSelectedItem(),
                  location_field.getText(), date_field.getText(),
                  time_field.getText(), Double.parseDouble(price_field.getText()), s.getId());
         });
      } else {
         add_session_btn.addActionListener(l -> {
            addSession(gym, name_field.getText(), trainer_field.getText(), (String) type_field.getSelectedItem(),
                  location_field.getText(), date_field.getText(),
                  time_field.getText(), Double.parseDouble(price_field.getText()), -1);
         });
      }
   }

   private void addSession(Gym gym, String name, String trainer, String type, String location, String date, String time,
         double price, int id) {
      if (modify) {
         int success = gym.setSession(id, name, trainer, type, location, date, time, price);
         if (success == -1)
            throw new IndexOutOfBoundsException("session not found");
      } else {
         Session temp = gym.createSession(name, trainer, type, location, date, time, price);
      }
      dispose();
   }

}
