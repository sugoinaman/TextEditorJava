package Myapp;

import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;

public class Layout extends JFrame implements ActionListener {

    private JTextArea jTextArea;

    private JFrame jFrame;


    public Layout() {

        jFrame = new JFrame("editor");

        try {
            // Set metal look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            // Set theme to ocean
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {
        }

        // Text component
        jTextArea = new JTextArea();

        // Create a menubar
        JMenuBar mb = new JMenuBar();

        // A menu inside menu
        JMenu m1 = new JMenu("File");

        // Create menu items
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi2 = new JMenuItem("Open");
        JMenuItem mi3 = new JMenuItem("Save");
        JMenuItem mi9 = new JMenuItem("Print");

        // Add action listener
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);

        // Create a menu for menu
        JMenu m2 = new JMenu("Edit");

        // Create menu items
        JMenuItem mi4 = new JMenuItem("cut");
        JMenuItem mi5 = new JMenuItem("copy");
        JMenuItem mi6 = new JMenuItem("paste");

        // Add action listener
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);

        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);

        JMenuItem mc = new JMenuItem("close");

        mc.addActionListener(this);

        mb.add(m1);
        mb.add(m2);
        mb.add(mc);

        jFrame.setJMenuBar(mb);
        jFrame.add(jTextArea);
        jFrame.setSize(700, 700);
        jFrame.show();
        jFrame.setLocationRelativeTo(null);
    }

    // If a button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("cut")) {
            jTextArea.cut();
        } else if (s.equals("copy")) {
            jTextArea.copy();
        } else if (s.equals("paste")) {
            jTextArea.paste();
        } else if (s.equals("Save")) {
            // Create an object of JFileChooser class
            JFileChooser j = new JFileChooser("f:");

            // Invoke the showsSaveDialog function to show the save dialog
            int r = j.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {

                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    FileWriter wr = new FileWriter(fi, false);
                    BufferedWriter w = new BufferedWriter(wr);
                    w.write(jTextArea.getText());
                    w.flush();
                    w.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(jFrame, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(jFrame, "Operation Cancelled!");
        } else if (s.equals("Print")) {
            try {
                jTextArea.print();
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(jFrame, evt.getMessage());
            }
        } else if (s.equals("Open")) {
            JFileChooser j = new JFileChooser("f:");
            int r = j.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                try {
                    // String
                    String s1 = "", sl = "";

                    // File reader
                    FileReader fr = new FileReader(fi);

                    // Buffered reader
                    BufferedReader br = new BufferedReader(fr);

                    // Initialize sl
                    sl = br.readLine();

                    // Take the input from the file
                    while ((s1 = br.readLine()) != null) {
                        sl = sl + "\n" + s1;
                    }
                    // Set the text
                    jTextArea.setText(sl);
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(jFrame, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(jFrame, "Operation Cancelled!");
        } else if (s.equals("New")) {
            jTextArea.setText("");
        } else if (s.equals("Close")) {
            jFrame.setVisible(false);
        }
    }
}
