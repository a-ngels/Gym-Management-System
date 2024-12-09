
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;

import javax.swing.*;

public class trainerGUI extends JFrame {

   // variables
   private JButton create_btn, modify_btn, delete_btn, details_btn, back_btn;
   private DefaultListModel<String> sessionsListModel;
   private JList<String> sessionsList;
   private JScrollPane sessionsScrollPane;
   private JPanel bottom;
   private JSplitPane main;

   // constructor
   public trainerGUI(Gym gym) {

      // initialize internal components
      setComponents(gym);

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
            refresh(gym);
         }

         @Override
         public void windowLostFocus(WindowEvent e) {
            System.out.println("lost focus");
         }
      });

      this.setVisible(true);
   }

   // methods

   //intialize components
   private void setComponents(Gym gym) {
      create_btn = new JButton("Create");
      modify_btn = new JButton("Modify");
      delete_btn = new JButton("Delete");
      details_btn = new JButton("Details");
      back_btn = new JButton("Back");

      create_btn.setPreferredSize(new Dimension(100, 75));
      modify_btn.setPreferredSize(new Dimension(100, 75));
      delete_btn.setPreferredSize(new Dimension(100, 75));
      details_btn.setPreferredSize(new Dimension(100, 75));
      back_btn.setPreferredSize(new Dimension(100, 75));

      // Create the JList
      sessionsListModel = new DefaultListModel<String>();
      sessionsToSessionEntries(gym);
      sessionsList = new JList<>(sessionsListModel);
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

   //update each time a session is added or deleted
   private void refresh(Gym gym) {
      System.out.println("updating");
      if(sessionsListModel.getSize()<gym.get_sessions().size())
         sessionsListModel.addElement(sessionToSessionEntry(gym.getLastSession()));
      sessionsScrollPane.repaint();
   }

   private void add_components() {

      bottom.add(create_btn);
      bottom.add(modify_btn);
      bottom.add(delete_btn);
      bottom.add(details_btn);
      bottom.add(back_btn);
      main.add(sessionsScrollPane);
      main.add(bottom);
      this.add(main);
   }

   private void add_listeners(Gym gym) {
      back_btn.addActionListener(l -> dispose());
      create_btn.addActionListener(l -> new create_sessionGUI(gym));
      delete_btn.addActionListener(l -> {
         if(!sessionsList.isSelectionEmpty()){
            gym.deleteSession(sessionsList.getSelectedIndex());
            sessionsListModel.remove(sessionsList.getSelectedIndex());
         }
      });
   }

   private void sessionsToSessionEntries(Gym gym) {
      for (Session s : gym.get_sessions())
         sessionsListModel.addElement(sessionToSessionEntry(s));
   }

   private String sessionToSessionEntry(Session s) {
      return String.format("%3d%12s%9s%20s%10s", s.getId(), s.getDate(), s.getTime(), s.getName(), s.getTrainer());
   }

}
