
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

public class trainerGUI extends JFrame {

   // variables
   private JButton create_btn, modify_btn, delete_btn, details_btn, back_btn;
   private DefaultTableModel sessionsTableModel;
   private JTable sessionsTable;
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
      // window focus listener to update after creating new session
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

   // intialize components
   private void setComponents(Gym gym) {
      // initalize buttons
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

      // initialize table and scrollpane
      sessionsTableModel = new DefaultTableModel(sessionsToJTable(gym),
            new String[] { "ID", "Date", "Time", "Name", "Trainer" }) {
         @Override
         public boolean isCellEditable(int row, int column) {
            // all cells false
            return false;
         }
      };
      sessionsTable = new JTable(sessionsTableModel);
      sessionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      // column widths
      sessionsTable.getColumnModel().getColumn(0).setPreferredWidth(25);
      sessionsTable.getColumnModel().getColumn(1).setPreferredWidth(100);
      sessionsTable.getColumnModel().getColumn(2).setPreferredWidth(75);
      sessionsTable.getColumnModel().getColumn(3).setPreferredWidth(200);
      sessionsTable.getColumnModel().getColumn(4).setPreferredWidth(125);
      sessionsScrollPane = new JScrollPane(sessionsTable);
      sessionsTable.setRowSelectionAllowed(true);
      sessionsTable.getTableHeader().setReorderingAllowed(false);

      bottom = new JPanel();
      main = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

      // create sessions box
      sessionsScrollPane.setPreferredSize(new Dimension(500, 500));
   }

   // update each time a session is added or deleted
   private void refresh(Gym gym) {
      try {
         Session s = gym.getLastSession();
         System.out.println("updating");
         if (sessionsTable.getRowCount() < gym.get_sessions().size())
            sessionsTableModel.addRow(
                  new String[] { String.format("%d", s.getId()), s.getDate(), s.getTime(), s.getName(),
                        s.getTrainer() });
      } catch (Exception e) {
      }
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
      main.setEnabled(false);
   }

   private void add_listeners(Gym gym) {
      back_btn.addActionListener(l -> dispose());
      create_btn.addActionListener(l -> new createModifySessionGUI(gym, null));
      delete_btn.addActionListener(l -> {
         if (sessionsTable.getSelectedRow() != -1) {
            gym.deleteSession(sessionsTable.getSelectedRow());
            sessionsTableModel.removeRow(sessionsTable.getSelectedRow());
         } else {
            JOptionPane.showMessageDialog(this, "Please select a session to delete");
         }

      });
      details_btn.addActionListener(l -> {
         if (sessionsTable.getSelectedRow() != -1) {
            new detailsGUI(
                  gym.getSession(
                        Integer.parseInt((String) sessionsTableModel.getValueAt(sessionsTable.getSelectedRow(), 0))));
         } else {
            JOptionPane.showMessageDialog(this, "Please select a session to view its details");
         }
      });
      modify_btn.addActionListener(l -> {
         if (sessionsTable.getSelectedRow() != -1) {
            System.out.println(sessionsTableModel.getValueAt(sessionsTable.getSelectedRow(), 0).getClass());
            new createModifySessionGUI(gym, gym.getSession(
                  Integer.parseInt((String) sessionsTableModel.getValueAt(sessionsTable.getSelectedRow(), 0))));
         } else {
            JOptionPane.showMessageDialog(this, "Please select a session to modify");
         }
      });
   }

   private String[][] sessionsToJTable(Gym gym) {
      ArrayList<String[]> temp = new ArrayList<>();
      for (Session s : gym.get_sessions()) {
         temp.add(
               new String[] { String.format("%d", s.getId()), s.getDate(), s.getTime(), s.getName(), s.getTrainer() });
      }
      return temp.toArray(new String[0][0]);
   }

}
