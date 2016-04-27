package com.Brendon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


/*
This class is for entering information on the new solver.
 */

public class add_GUI extends JFrame implements WindowListener{

    private JPanel addPanel;
    private JTextField solverEntry;
    private JTextField timeEntry;
    private JButton doneButton;


    add_GUI(final rubik parent ) {


        setContentPane(addPanel);
        pack();
        addWindowListener(this);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String solver = solverEntry.getText();
                double time = Double.parseDouble(timeEntry.getText());

                parent.insertRow(solver,time);
                add_GUI.this.dispose();

            }
        });
    }





    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
