package alexvlad.controller;

import alexvlad.model.Squad;
import alexvlad.view.Fantasy;
import alexvlad.view.PlayerPanel;

import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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

                addActionListenersToPlayers();
            }
        });
    }

    private void addActionListenersToPlayers() {

        for (int id = 0; id < 15; ++id) {
            PlayerPanel playerPanel = view.getPlayerPanel(id);
            JButton currentButton = playerPanel.getPlayerButton();

            currentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object source = e.getSource();

                    JButton buttonSource = (JButton) source;
                    Integer index = view.getPlayerIDForButton(buttonSource);

                    openFileChooser();
                }
            });
        }
    }

    private void openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(view);



        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getName().toLowerCase();

            if (!(fileName.endsWith(".jpeg") ||
                    fileName.endsWith(".png") ||
                    fileName.endsWith(".jpg") || fileName.endsWith(".tiff"))) {
                JOptionPane.showMessageDialog(null, "Please select an image file!");
            }
        }
    }
}
