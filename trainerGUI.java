
import java.awt.*;
import javax.swing.*;

public class trainerGUI extends JFrame {

   // variables
   private JButton create_btn, modify_btn, delete_btn, classlist_btn, back_btn;
   private JScrollPane sessions;
   private JPanel bottom;
   private JSplitPane main;

   // constructor
   public trainerGUI(Gym gym) {

      // initialize internal components
      setInternalComponents();

      // create buttons and adjust sizes for gui
      set_buttons();

      // set up general inforamtion
      setTitle("Gym Trainer");
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setMinimumSize(new Dimension(550, 650));

      // format frame
      this.setLayout(new FlowLayout());

      // add buttons and event listeners to frame
      add_components();
      add_listeners(gym);

      this.setVisible(true);
   }

   // methods
   private void set_buttons() {
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
   }

   private void setInternalComponents() {
      sessions = new JScrollPane();
      bottom = new JPanel();
      main = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

      sessions.setPreferredSize(new Dimension(500, 500));
   }

   private void add_components() {

      bottom.add(create_btn);
      bottom.add(modify_btn);
      bottom.add(delete_btn);
      bottom.add(classlist_btn);
      bottom.add(back_btn);
      main.add(sessions);
      main.add(bottom);
      this.add(main);
   }

   private void add_listeners(Gym gym) {
      back_btn.addActionListener(l -> dispose());
      create_btn.addActionListener(l -> new create_sessionGUI(gym));
   }
}
