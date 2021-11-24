package client.gui.panel;

import client.gui.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitPanel extends GuiPanel implements ActionListener {

    private final JButton beginButton;
    private final JTextField usernameEntry;
    private final JTextField ipEntry;
    private final JTextField portEntry;


    public InitPanel(Gui gui) {
        super(gui);
        setOpaque(true);
        setPreferredSize(new Dimension(200, 200));
        setBackground(new Color(0x9EE1DE));
        // setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel(
                "Welcome !\n Enter your username\n"
        );
        this.add(welcomeLabel);

        usernameEntry = new JTextField("username");
        usernameEntry.setPreferredSize(new Dimension(200, 30));
        this.add(usernameEntry);

        ipEntry = new JTextField("localhost");
        ipEntry.setPreferredSize(new Dimension(200, 30));
        this.add(ipEntry);

        portEntry = new JTextField("8123");
        portEntry.setPreferredSize(new Dimension(200, 30));
        this.add(portEntry);

        beginButton = new JButton("Begin");
        beginButton.setBackground(new Color(0xBFC5D9));
        beginButton.addActionListener(this);
        this.add(beginButton);
        setVisible(true);

    }

    /**
     * Send a connecion request when the button is clicked
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // load action
        if (e.getSource() == beginButton) {
            System.out.println(usernameEntry.getText());
            String username = usernameEntry.getText();
            String ip = ipEntry.getText();
            int port = Integer.parseInt(portEntry.getText());
            System.out.println(username);
            System.out.println(ip);
            System.out.println(port);
            this.gui.getController().connectingButtonClick(this.gui, username, ip, port);
        }

    }

}
