package alexvlad.controller;

import alexvlad.model.Squad;
import alexvlad.view.Fantasy;

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
                }
            }
        });
    }



}
