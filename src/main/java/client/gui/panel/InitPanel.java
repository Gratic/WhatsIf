package client.gui.panel;

import client.gui.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitPanel extends GuiPanel implements ActionListener {

    private JButton beginButton;
    private JTextField usernameEntry;


    public InitPanel(Gui gui) {
        super(gui);
        setOpaque(true);
        setPreferredSize(new Dimension(200,200));
        setBackground(new Color(0x9EE1DE));
       // setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel(
                "Welcome !\n Enter your username\n"
        );
        this.add(welcomeLabel);

        usernameEntry = new JTextField("username");
        usernameEntry.setPreferredSize(new Dimension(200,30));
        this.add(usernameEntry);

        beginButton = new JButton("Begin");
        beginButton.setBackground(new Color(0xBFC5D9));
        beginButton.addActionListener(this);
        this.add(beginButton);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // load action
        if (e.getSource() == beginButton) {
            System.out.println("Bouton clické");
            System.out.println(usernameEntry.getText());
            this.gui.getController().connectingButtonClick(this.gui);


        }

    }

}
