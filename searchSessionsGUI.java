
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class searchSessionsGUI extends JFrame {

   // declare variables
   private JButton search_btn, addSession_btn, back_btn;
   private JTextField searchField;
   private DefaultTableModel sessionsTableModel;
   private JTable sessionsTable;
   private JScrollPane sessionsScrollPane;
   private JPanel bottom, searchPanel;
   private JSplitPane main;

   public searchSessionsGUI(User user, Gym gym) {

      // create buttons and adjust sizes for gui
      set_components(gym);

      // set up general inforamtion
      setTitle("Search and Add Sessions for " + user.getFirstName() + " " + user.getLastName());
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setMinimumSize(new Dimension(650, 700));

      // format frame 
      this.setLayout(new FlowLayout());

       // Add components and listeners
       add_components();
       add_listeners(user, gym);

       this.setVisible(true);
   }

   private void set_components(Gym gym) {

      // Initialize buttons and search field
      search_btn = new JButton("Search");
      addSession_btn = new JButton("Add Session");
      back_btn = new JButton("Back");
      searchField = new JTextField(20);

      search_btn.setPreferredSize(new Dimension(120, 50));
      addSession_btn.setPreferredSize(new Dimension(120, 50));
      back_btn.setPreferredSize(new Dimension(120, 50));
      searchField.setPreferredSize(new Dimension(250, 50));

      // Initialize table and scroll pane
      sessionsTableModel = new DefaultTableModel(sessionsToJTable(gym),
            new String[] { "ID", "Date", "Time", "Name", "Trainer", "Cost" });
      sessionsTable = new JTable(sessionsTableModel);
      sessionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      // Column widths
      sessionsTable.getColumnModel().getColumn(0).setPreferredWidth(25);
      sessionsTable.getColumnModel().getColumn(1).setPreferredWidth(100);
      sessionsTable.getColumnModel().getColumn(2).setPreferredWidth(75);
      sessionsTable.getColumnModel().getColumn(3).setPreferredWidth(120);
      sessionsTable.getColumnModel().getColumn(4).setPreferredWidth(120);
      sessionsTable.getColumnModel().getColumn(5).setPreferredWidth(25);

      sessionsScrollPane = new JScrollPane(sessionsTable);
      bottom = new JPanel();
      searchPanel = new JPanel();
      main = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
      sessionsScrollPane.setPreferredSize(new Dimension(600, 500));
   }

   private void add_components() {

      searchPanel.add(new JLabel("Search by Name:"));
      searchPanel.add(searchField);
      searchPanel.add(search_btn);
      bottom.add(addSession_btn);
      bottom.add(back_btn);

      JPanel tableAndButtonsPanel = new JPanel(new BorderLayout());
      tableAndButtonsPanel.add(sessionsScrollPane, BorderLayout.CENTER);
      tableAndButtonsPanel.add(bottom, BorderLayout.SOUTH); 
      main.setEnabled(false);
      
      main.setTopComponent(searchPanel);
      main.setBottomComponent(tableAndButtonsPanel);
  
      this.add(main);
  }

   private void add_listeners(User user, Gym gym) {

      back_btn.addActionListener(e -> dispose());
      search_btn.addActionListener(e -> {
         String search = searchField.getText().trim();
         executeSearch(gym, search);
      });

      addSession_btn.addActionListener(e -> {

         int selectedRow = sessionsTable.getSelectedRow();
         if (selectedRow != -1) {
            int sessionId = Integer.parseInt((String) sessionsTable.getValueAt(selectedRow, 0));
            for (Session s : gym.get_sessions()) {
               if (s.getId() == sessionId) {

                  if (user.alreadyScheduled(s)) {
                     JOptionPane.showMessageDialog(this, "Session already added to schedule!");
                  } else {
                     user.addToList(s);
                     JOptionPane.showMessageDialog(this, "Session added successfully!");
                  }
                  return;
               }
            }
         } else {
            JOptionPane.showMessageDialog(this, "Please select a session to add.");
         }
      });
   }

   private String[][] sessionsToJTable(Gym gym) {
      ArrayList<String[]> temp = new ArrayList<>();
      for (Session s : gym.get_sessions()) {
         temp.add(new String[] { String.format("%d", s.getId()), s.getDate(), s.getTime(), s.getName(), s.getTrainer(), String.format("%.2f", s.getPrice())});
      }
      return temp.toArray(new String[0][0]);
   }

   private void executeSearch(Gym gym, String search) {
      sessionsTableModel.setRowCount(0);
      for (Session s : gym.get_sessions()) {
         if (search.isEmpty()) {
            sessionsTableModel.addRow(new String[] {String.format("%d", s.getId()), s.getDate(), s.getTime(), s.getName(), s.getTrainer(), String.format("%.2f", s.getPrice())});
         }
         if (s.getName().toLowerCase().contains(search.toLowerCase())) {
            sessionsTableModel.addRow(new String[] {String.format("%d", s.getId()), s.getDate(), s.getTime(), s.getName(), s.getTrainer(), String.format("%.2f", s.getPrice())});
         }
      }
   }
 
}
