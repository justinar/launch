package launch;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

/**
 *
 * @author jariv
 */
public class Launch extends JPanel implements ActionListener {

    JLabel picture;
    JPanel rea;
    JPanel rand;
    JPanel cust;
    JPanel rest;
    JTabbedPane missileSet;
    JPanel custIn;
    JLabel rangeLabel = new JLabel("Range (km)");
    JTextField range = new JTextField("0");
    JLabel blastLabel = new JLabel("Warhead Size (kt)");
    JTextField blast = new JTextField("0");
    JComboBox countryList;
    JComboBox missileList;
    DefaultComboBoxModel model;
    String[] countryStrings;
    //String[] missileStrings = new String;
    double activeRange;
    double activeBlast;

    Launch() {
        super(new BorderLayout());

        //Initialize class variables
        activeRange = 0.0;
        activeBlast = 0.0;
        //Create lists
        countryStrings = arrayFromFile("countries");
        //missileStrings = {"No","Yes"};

        //Create the combo box for countries
        countryList = new JComboBox(countryStrings);
        countryList.setSelectedIndex(0);
        countryList.setActionCommand("comboBoxChanged");
        countryList.addActionListener(this);

        //Create the combo box for missiles
        missileList = new JComboBox(new String[1]);
        model = new DefaultComboBoxModel(new String[1]);
        missileList.setModel(model);
        missileList.setSelectedIndex(0);

        //Fire button
        JButton fire = new JButton();
        fire.setText("FIRE");
        fire.setBackground(Color.red);
        fire.setForeground(Color.white);
        fire.setFont(new Font("Serif", Font.BOLD, 24));
        fire.setActionCommand("fire");
        fire.addActionListener(this);
        //fire.setBorder(new LineBoarder());

        //Set random tab
        rand = new JPanel();
        JButton rdm = new JButton();
        rdm.setText("Random Missile");
        rdm.setActionCommand("random");
        rdm.addActionListener(this);
        rand.add(rdm, BorderLayout.CENTER);

        //Set reset tab
        rest = new JPanel();
        JButton rst = new JButton();
        rst.setText("Random Missile");
        rst.setBackground(Color.green);
        rst.setActionCommand("reset");
        rst.addActionListener(this);
        rest.add(rst, BorderLayout.CENTER);

        //Set combo box tab
        rea = new JPanel();
        rea.add(countryList, BorderLayout.WEST);
        rea.add(missileList, BorderLayout.EAST);
        JButton setA = new JButton();
        setA.setText("Set");
        setA.setActionCommand("set");
        setA.addActionListener(this);
        rea.add(setA, BorderLayout.LINE_END);

        //Set input for custom missile
        custIn = new JPanel();
        custIn.add(rangeLabel);
        custIn.add(range);
        custIn.add(blastLabel);
        custIn.add(blast);

        //Set custom tab
        cust = new JPanel();
        JLabel test = new JLabel();
        test.setText("Please enter the following values:");
        cust.add(test, BorderLayout.LINE_START);
        cust.add(custIn, BorderLayout.CENTER);
        JButton setC = new JButton();
        setC.setText("Set");
        setC.setActionCommand("set");
        setC.addActionListener(this);
        cust.add(setC, BorderLayout.LINE_END);

        //Set up the picture.
        picture = new JLabel();
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.CENTER);
        //updateLabel(missileStrings[missileList.getSelectedIndex()]);
        picture.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        picture.setToolTipText("World map");
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("./img/worldMap.jpg"));
        } catch (Exception e) {
        }
        picture.setIcon(new ImageIcon(image));

        picture.setPreferredSize(new Dimension(1544, 777 + 10));

        //Add tabs
        missileSet = new JTabbedPane();
        missileSet.addTab("Random Missile", rand);
        missileSet.addTab("Real Missile", rea);
        missileSet.addTab("Custom Missile", cust);
        missileSet.addTab("Reset Sim", rest);

        //Fill page
        add(missileSet, BorderLayout.PAGE_START);
        add(picture, BorderLayout.CENTER);
        add(fire, BorderLayout.PAGE_END);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    //Listener
    public void actionPerformed(ActionEvent e) {
        String act = (String) e.getActionCommand();

        if (act.equals("fire")) {
        } else if (act.equals("set")) {
        } else if (act.equals("random")) {
        } else if (act.equals("reset")) {
        } else {
            JOptionPane.showMessageDialog(null, countryList.getSelectedIndex(), "Check", 2);
            String country = countryStrings[countryList.getSelectedIndex()];
            //String[] missileStrings = arrayFromFile("United States");
            model = new DefaultComboBoxModel(arrayFromFile(countryList.getSelectedItem().toString().trim()));
            missileList.setModel(model);
        }
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Doomsday Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new Launch();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        try {
            ImageIcon img = new ImageIcon("./img/nuke.gif");
            frame.setIconImage(img.getImage());
        } catch (Exception e) {
        }
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    static String[] arrayFromFile(String who) {
        ArrayList<String> pull = new ArrayList<>();
        Scanner in = null;
        try {
            in = new Scanner(new File("./data/" + who + ".csv"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error: File not found", 0);
            System.out.println("bad file name, check structure");
            System.exit(1);
        }
        in.useDelimiter(",");
        while (in.hasNext()) {
            pull.add(in.next());
        }
        return pull.toArray(new String[pull.size()]);
    }

    public static void main(String[] args) {
        /*
        **As per suggestion from Oracle's tutorial, GUI is initialized in 
        **constructor allowing dynamic alterations without crashing/hanging
        **
        **Main is used to start the constructor and get the ball rolling
        **The entire program is run by calling various functions as needed
         */
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
