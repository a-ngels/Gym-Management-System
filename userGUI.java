
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class userGUI extends JFrame {

   // variables
   private JButton create_btn, modify_btn, delete_btn, search_btn, view_schedule_btn, back_btn;
   private DefaultTableModel usersTableModel;
   private JTable usersTable;
   private JScrollPane usersScrollPane;
   private JPanel bottom;
   private JSplitPane main;

   // constructor
   public userGUI(Gym gym) {

      // create buttons and adjust sizes for gui
      set_components(gym);

      // set up general inforamtion
      setTitle("Gym User");
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setMinimumSize(new Dimension(650, 650));

      // format frame
      this.setLayout(new FlowLayout());

      // add buttons and listerners
      add_components();
      add_listeners(gym);

      // window focus listener to update after creating new session
      this.addWindowFocusListener(new WindowFocusListener() {
         @Override
         public void windowGainedFocus(WindowEvent e) {
            // System.out.println("gained focus");
            refresh(gym);
         }

         @Override
         public void windowLostFocus(WindowEvent e) {
            // System.out.println("lost focus");
         }
      });

      this.setVisible(true);
   }

   // methods
   private void set_components(Gym gym) {
      create_btn = new JButton("Create");
      modify_btn = new JButton("Modify");
      delete_btn = new JButton("Delete");
      search_btn = new JButton("Search");
      view_schedule_btn = new JButton("Schedule");
      back_btn = new JButton("Back");

      create_btn.setPreferredSize(new Dimension(100, 75));
      modify_btn.setPreferredSize(new Dimension(100, 75));
      delete_btn.setPreferredSize(new Dimension(100, 75));
      search_btn.setPreferredSize(new Dimension(100, 75));
      view_schedule_btn.setPreferredSize(new Dimension(100, 75));
      back_btn.setPreferredSize(new Dimension(100, 75));

      // initialize table and scrollpane
      usersTableModel = new DefaultTableModel(usersToJTable(gym),
            new String[] { "Id", "First Name", "Last Name", "Phone Number", "Email" }) {
         @Override
         public boolean isCellEditable(int row, int column) {
            // all cells false
            return false;
         }
      };
      usersTable = new JTable(usersTableModel);
      usersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      // column widths
      usersTable.getColumnModel().getColumn(0).setPreferredWidth(10);
      usersTable.getColumnModel().getColumn(1).setPreferredWidth(25);
      usersTable.getColumnModel().getColumn(2).setPreferredWidth(25);
      usersTable.getColumnModel().getColumn(3).setPreferredWidth(50);
      usersTable.getColumnModel().getColumn(4).setPreferredWidth(140);
      usersScrollPane = new JScrollPane(usersTable);
      usersTable.setRowSelectionAllowed(true);
      usersTable.getTableHeader().setReorderingAllowed(false);
      bottom = new JPanel();
      main = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

      // create users box
      usersScrollPane.setPreferredSize(new Dimension(500, 500));
   }

   private void refresh(Gym gym) {
      usersTableModel.setDataVector(usersToJTable(gym),
            new String[] { "Id", "First Name", "Last Name", "Phone Number", "Email" });
      usersScrollPane.repaint();
   }

   private void add_components() {
      bottom.add(create_btn);
      bottom.add(modify_btn);
      bottom.add(delete_btn);
      bottom.add(search_btn);
      bottom.add(view_schedule_btn);
      bottom.add(back_btn);
      main.add(usersScrollPane);
      main.add(bottom);
      this.add(main);
      main.setEnabled(false);
   }

   private void add_listeners(Gym gym) {
      back_btn.addActionListener(l -> dispose());
      create_btn.addActionListener(l -> new create_userGUI(gym, null));
      delete_btn.addActionListener(l -> {
         if (usersTable.getSelectedRow() != -1) {
            gym.deleteUser(usersTable.getSelectedRow());
            usersTableModel.removeRow(usersTable.getSelectedRow());
            refresh(gym);
         } else {
            JOptionPane.showMessageDialog(this, "Please select a user to view their sessions.");
         }
      });

      view_schedule_btn.addActionListener(l -> {
         int selectedRow = usersTable.getSelectedRow();
         if (selectedRow != -1) {
            User selectedUser;
            int userId = Integer.parseInt((String) usersTable.getValueAt(selectedRow, 0));
            for (User u : gym.get_users()) {
               if (u.getID() == userId) {
                  selectedUser = u;
                  new viewScheduleGUI(selectedUser, gym);
                  break;
               }
            }

         } else {
            JOptionPane.showMessageDialog(this, "Please select a user to view their schedule.");
         }
      });

      search_btn.addActionListener(l -> {
         int selectedRow = usersTable.getSelectedRow();
         if (selectedRow != -1) {
            User selectedUser;
            int userId = Integer.parseInt((String) usersTable.getValueAt(selectedRow, 0));
            for (User u : gym.get_users()) {
               if (u.getID() == userId) {
                  selectedUser = u;
                  new searchSessionsGUI(selectedUser, gym);
                  break;
               }
            }
         } else {
            JOptionPane.showMessageDialog(this, "Please select a user to search sessions.");
         }
      });

      modify_btn.addActionListener(l -> {
         if (usersTable.getSelectedRow() != -1) {
            new create_userGUI(gym, gym.getUser(
                  Integer.parseInt((String) usersTableModel.getValueAt(usersTable.getSelectedRow(), 0))));
         } else {
            JOptionPane.showMessageDialog(this, "Please select a user to modify");
         }
      });
   }

   private String[][] usersToJTable(Gym gym) {
      ArrayList<String[]> temp = new ArrayList<>();
      for (User u : gym.get_users()) {
         temp.add(new String[] { String.format("%d", u.getID()), u.getFirstName(), u.getLastName(), u.getPhoneNumber(),
               u.getEmail() });
      }
      return temp.toArray(new String[0][0]);
   }

}
