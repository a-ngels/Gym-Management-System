import java.awt.*;
import javax.swing.*;

public class detailsGUI extends JFrame {
    private JLabel sessionTitle, id, type, trainer, location, date, time, price, revenue, classSize;
    private JButton back_btn;
    private JList<String> classList;
    private DefaultListModel<String> classListModel;
    private JScrollPane classScrollPane;
    private JPanel top;
    private JSplitPane main;

    public detailsGUI(Session s) {
        initFields(s);

        setTitle("Session Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(550, 650));

        this.setLayout(new FlowLayout());
        setAttributes();
        addComponents();

        this.setVisible(true);
    }

    private void initFields(Session s) {
        sessionTitle = new JLabel(s.getName());// make bold?
        id = new JLabel(String.format("ID: %d", s.getId()));
        type = new JLabel("Type: " + s.getType());
        trainer = new JLabel("Trainer: " + s.getTrainer());
        location = new JLabel("Location: " + s.getLocation());
        date = new JLabel("Date: " + s.getDate());
        time = new JLabel("Time: " + s.getTime());
        price = new JLabel(String.format("Price: %f", s.getPrice()));
        revenue = new JLabel(String.format("Revenue: %f", s.calculateRevenue()));
        classSize = new JLabel(String.format("Class Size: %d", s.getClassSize()));
        back_btn = new JButton("Back");

        // initialize table and scrollpane
        classListModel = new DefaultListModel<String>();
        classListModel.addAll(s.getClassList());
        classList = new JList<String>(classListModel);
        classScrollPane = new JScrollPane(classList);

        top = new JPanel(new GridLayout(4, 3, 10, 10));
        main = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        // create sessions box
        classScrollPane.setPreferredSize(new Dimension(500, 400));
    }

    private void setAttributes() {
        classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        classList.addListSelectionListener(l -> classList.setSelectedIndex(-1));

        back_btn.addActionListener(l -> dispose());
    }

    private void addComponents() {
        top.add(sessionTitle);
        top.add(new JLabel());
        top.add(back_btn);
        top.add(id);
        top.add(location);
        top.add(price);
        top.add(date);
        top.add(type);
        top.add(revenue);
        top.add(time);
        top.add(trainer);
        top.add(classSize);
        main.add(top);
        main.add(classScrollPane);
        this.add(main);
    }
}
