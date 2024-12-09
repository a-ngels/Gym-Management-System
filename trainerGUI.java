
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.*;

public class trainerGUI extends JFrame {

   // variables
   private JButton create_btn, modify_btn, delete_btn, classlist_btn, back_btn;
   private JList<String> sessionsList;
   private JScrollPane sessionsScrollPane;
   private JPanel bottom;
   private JSplitPane main;

   private String[] fitnessClasses = {
         "Yoga",
         "Pilates",
         "Zumba",
         "CrossFit",
         "Spinning",
         "Kickboxing",
         "HIIT (High-Intensity Interval Training)",
         "Barre",
         "Aqua Aerobics",
         "Bootcamp"
   };

   // constructor
   public trainerGUI(Gym gym) {

      // initialize internal components
      setComponents();

      // set up general inforamtion
      setTitle("Gym Trainer");
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setMinimumSize(new Dimension(550, 650));

      // format frame
      this.setLayout(new FlowLayout());

      // add buttons and event listeners to frame
      add_components();
      add_listeners(gym);
      this.addWindowFocusListener(new WindowFocusListener() {
         @Override
         public void windowGainedFocus(WindowEvent e) {
            System.out.println("gained focus");
            refresh();
         }

         @Override
         public void windowLostFocus(WindowEvent e) {
             System.out.println("lost focus");
         }
     });

      this.setVisible(true);
   }

   // methods

   private void setComponents() {
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

      // Create the JList
      sessionsList = new JList<>(fitnessClasses);
      sessionsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      // Add a scroll pane to make the list scrollable
      sessionsScrollPane = new JScrollPane(sessionsList);

      // Add a label to display the selected item
      JLabel selectedLabel = new JLabel("Selected: None");

      // Add a listener to capture selected items
      sessionsList.addListSelectionListener(e -> {
         if (!e.getValueIsAdjusting()) { // Prevent multiple events during selection
            String selectedValue = sessionsList.getSelectedValue();
            selectedLabel.setText("Selected: " + (selectedValue != null ? selectedValue : "None"));
         }
      });
      bottom = new JPanel();
      main = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

      // create sessions box
      sessionsScrollPane.setPreferredSize(new Dimension(500, 500));
   }

   private void refresh() {
      System.out.println("updating");
      sessionsScrollPane.repaint();
   }

   private void add_components() {

      bottom.add(create_btn);
      bottom.add(modify_btn);
      bottom.add(delete_btn);
      bottom.add(classlist_btn);
      bottom.add(back_btn);
      main.add(sessionsScrollPane);
      main.add(bottom);
      this.add(main);
   }

   private void add_listeners(Gym gym) {
      back_btn.addActionListener(l -> dispose());
      create_btn.addActionListener(l -> new create_sessionGUI(gym));
   }

   private class SessionPanel extends JPanel {
      SessionPanel() {

      }
   }

}
