package launch;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.border.*;

/**
 *
 * @author jariv
 */
public class Launch extends JPanel implements ActionListener {

    JLabel picture;
    JPanel rea;
    JPanel rand;
    JPanel cust;
    JTabbedPane missileSet;
    JPanel custIn;
    JLabel rangeLabel = new JLabel("Range (km)");
    JTextField range = new JTextField("0");
    JLabel blastLabel = new JLabel("Warhead Size (kt)");
    JTextField blast = new JTextField("0");

    Launch() {
        super(new BorderLayout());

        String[] missileStrings = {"Big", "Small", "Tiny", "Giant", "You"};
        String[] countryStrings = {"USA", "Russia", "France", "Cuba", "Italy"};

        //Create the combo box for countries
        JComboBox countryList = new JComboBox(countryStrings);
        countryList.setSelectedIndex(0);
        countryList.addActionListener(this);

        //Create the combo box for missiles
        JComboBox missileList = new JComboBox(missileStrings);
        missileList.setSelectedIndex(0);
        missileList.addActionListener(this);

        //Fire button
        JButton fire = new JButton();
        fire.setText("FIRE");
        fire.setBackground(Color.red);
        fire.setForeground(Color.white);
        fire.setFont(new Font("Serif", Font.BOLD, 24));
        //fire.setBorder(new LineBoarder());

        //Set random tab
        rand = new JPanel();
        JButton rdm = new JButton();
        rdm.setText("Random Missile");
        rand.add(rdm, BorderLayout.CENTER);

        //Set combo box tab
        rea = new JPanel();
        rea.add(countryList, BorderLayout.WEST);
        rea.add(missileList, BorderLayout.EAST);
        JButton setA = new JButton();
        setA.setText("Set");
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

        //Fill page
        add(missileSet, BorderLayout.PAGE_START);
        add(picture, BorderLayout.CENTER);
        add(fire, BorderLayout.PAGE_END);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    /**
     * Listens to the combo box.
     */
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        String missile = (String) cb.getSelectedItem();
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

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
