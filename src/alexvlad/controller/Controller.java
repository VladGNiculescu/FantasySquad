package alexvlad.controller;
import alexvlad.model.Squad;
import alexvlad.view.Fantasy;
import alexvlad.view.PlayerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Controller {
    Fantasy view;
    Squad squad;
    public Controller(Fantasy view, Squad squad) {
        this.view = view;
        this.squad = squad;
        this.view.setVisible(true);
        setupFormations();
    }
    private void setupFormations() {
        view.getFormationList().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFormation = String.valueOf(view.getFormationList().getSelectedItem());
                if (view.getFormationList().getSelectedIndex() > 0) {
                    view.changeFormationLayout(selectedFormation);
                    playerButtonHandler();
                }
            }
        });
    }
    private void playerButtonHandler() {
        for (int i = 0; i < view.getPlayerPanel().size(); i++) {
            final int finalI = i;
            view.getPlayerPanel().get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new JOptionPane().showMessageDialog(view, "Button id " + finalI, "TEST", JOptionPane.CANCEL_OPTION);
                }
            });
        }
    }
}
