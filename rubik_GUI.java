package com.Brendon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class rubik_GUI extends JFrame implements WindowListener {

    private JTable table1;
    private JPanel rubiks;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton quitButton;



    rubik_GUI(final rubik rubikModel) {

        setContentPane(rubiks);
        pack();
        setTitle("Rubik's Cube Solvers");
        addWindowListener(this);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        table1.setGridColor(Color.black);
        table1.setModel(rubikModel);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                add_GUI add = new add_GUI(rubikModel);


            }
        });
    }

    /*
    I'm not entirely sure why these need to be called, i think it's because the class implements WindowListener
     */
    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("closing");
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}
