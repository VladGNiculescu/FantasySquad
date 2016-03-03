package alexvlad.controller;

import alexvlad.model.Squad;
import alexvlad.view.Fantasy;
import alexvlad.view.PlayerPanel;

import javax.swing.*;
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
            JButton currentButton = view.getPlayerButtonById(id);

            currentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object source = e.getSource();

                    JButton buttonSource = (JButton) source;
                    Integer index = view.getPlayerIDForButton(buttonSource);

                    System.out.println("ID: " + index);

                    showFileChooserDialog();
                }
            });
        }
    }

    private void showFileChooserDialog() {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(view);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            boolean isImageFile = checkFileValidity(selectedFile);

            if (!isImageFile) {
                JOptionPane.showMessageDialog(null, "Please select an image file!");
            }
        }
    }

    private boolean checkFileValidity(File file) {

        String name = file.getName().toLowerCase();
        if (name.endsWith(".jpeg") ||
                name.endsWith(".jpg") ||
                name.endsWith(".png") ||
                name.endsWith(".tiff")||
                name.endsWith(".gif")) {
            return true;
        }

        return false;
    }
}
