import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class viewScheduleGUI extends JFrame {

      // declare variables
      private JButton delete_btn, back_btn;
      private DefaultTableModel sessionsTableModel;
      private JTable sessionsTable;
      private JScrollPane sessionsScrollPane;
      private JPanel bottom;
      private JSplitPane main;

   public viewScheduleGUI(User user) {

      // create components and adjust sizes for gui
      set_components(user);
      
      // set up frame
      setTitle("Schedule for " + user.getFirstName() + " " + user.getLastName());
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setMinimumSize(new Dimension(650, 650));
      setLayout(new FlowLayout());

      // add components and listeners
      add_components();
      add_listeners(user);
      
      
      this.setVisible(true);
   }

   private void set_components(User user) {

      delete_btn = new JButton("Delete");
      back_btn = new JButton("Back");
      delete_btn.setPreferredSize(new Dimension(100, 75));
      back_btn.setPreferredSize(new Dimension(100, 75));

      // initialize table and scrollpane
      sessionsTableModel = new DefaultTableModel(sessionsToJTable(user),
            new String[] { "ID", "Date", "Time", "Name", "Trainer", "Cost"});
      sessionsTable = new JTable(sessionsTableModel);
      sessionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      // column widths
      sessionsTable.getColumnModel().getColumn(0).setPreferredWidth(25);
      sessionsTable.getColumnModel().getColumn(1).setPreferredWidth(100);
      sessionsTable.getColumnModel().getColumn(2).setPreferredWidth(75);
      sessionsTable.getColumnModel().getColumn(3).setPreferredWidth(200);
      sessionsTable.getColumnModel().getColumn(4).setPreferredWidth(125);
      sessionsTable.getColumnModel().getColumn(5).setPreferredWidth(20);

      sessionsScrollPane = new JScrollPane(sessionsTable);
      sessionsTable.setRowSelectionAllowed(true);
      bottom = new JPanel();
      main = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
      sessionsScrollPane.setPreferredSize(new Dimension(500, 500));
   }

   private void add_components() {
      bottom.add(delete_btn);
      bottom.add(back_btn);
      main.add(sessionsScrollPane);
      main.add(bottom);
      this.add(main);
   }
   
   private void add_listeners(User user) {
      back_btn.addActionListener(e -> dispose());
      delete_btn.addActionListener(l -> {

         int selectedRow = sessionsTable.getSelectedRow();
         if (selectedRow != -1) {
            int sessionId = Integer.parseInt((String) sessionsTable.getValueAt(selectedRow, 0));
            user.getClassList().removeIf(session -> session.getId() == sessionId);
            sessionsTableModel.removeRow(selectedRow);
            refresh(user);
         } else {
            JOptionPane.showMessageDialog(this, "Please select a session to delete.");
         }
      });
   }

   private String[][] sessionsToJTable(User user) {
      ArrayList<String[]> temp = new ArrayList<>();
      for (Session s : user.getClassList()) {
         temp.add(new String[] {String.format("%d", s.getId()), s.getDate(), s.getTime(), s.getName(), s.getTrainer(), String.format("%.2f", s.getPrice())});
      }
      return temp.toArray(new String[0][0]);
   }

   private void refresh(User user) {
      sessionsTableModel.setRowCount(0);
      for (Session s : user.getClassList()) {
         sessionsTableModel.addRow(new String[] {
            String.format("%d", s.getId()), s.getDate(), s.getTime(), s.getName(), s.getTrainer(), String.format("%.2f", s.getPrice())});
      }
  }
  

}
