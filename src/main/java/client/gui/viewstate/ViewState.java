package client.gui.viewstate;

import client.gui.Gui;

import javax.swing.*;
import java.awt.*;

public abstract class ViewState {
    protected Gui gui;

    public ViewState(Gui gui) {
        this.gui = gui;
    }

    protected void createGuiComponents() {

    }
}
