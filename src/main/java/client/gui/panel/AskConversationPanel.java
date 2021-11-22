package client.gui.panel;

import client.gui.Gui;
import client.gui.viewstate.ConversationOpenedViewState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AskConversationPanel extends GuiPanel implements ActionListener {

    JButton enterConversationButton;
    JTextField name;

    public AskConversationPanel(Gui gui) {
        super(gui);
        setOpaque(true);
        setPreferredSize(new Dimension(350, 200));
        setBackground(new Color(0xCCE8E8));
        setLayout(new FlowLayout());

        JLabel welcomeLabel = new JLabel(
                "Who do you want to talk with? "
        );
        this.add(welcomeLabel);
        name = new JTextField();
        name.setPreferredSize(new Dimension(200, 30));
        this.add(name);
        enterConversationButton = new JButton("Let's talk");
        enterConversationButton.addActionListener(this);
        this.add(enterConversationButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterConversationButton) {
            this.gui.getController().creatingConversationButtonClick(this.gui, name.getText());
            this.gui.getUserConnectedViewState().stopTimer();
        }
    }
}
