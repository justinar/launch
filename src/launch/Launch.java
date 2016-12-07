package launch;

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

    Launch() {
        super(new BorderLayout());

        String[] missileStrings = {"Bird", "Cat", "Dog", "Rabbit", "Pig"};

        //Create the combo box
        JComboBox missileList = new JComboBox(missileStrings);
        missileList.setSelectedIndex(0);
        missileList.addActionListener(this);
        
        JButton fire = new JButton();

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

        add(missileList, BorderLayout.PAGE_START);
        add(picture, BorderLayout.CENTER);
        add(fire,BorderLayout.PAGE_END);
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
