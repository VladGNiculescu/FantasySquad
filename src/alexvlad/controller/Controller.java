package alexvlad.controller;

import alexvlad.model.*;
import alexvlad.view.Fantasy;
import alexvlad.view.FormationPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

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
//                    view.changeFormationLayout(selectedFormation);
                    formationChanged(selectedFormation);
                }


                addActionListenersToPlayers();
            }
        });
    }

    private void formationChanged(String formation) {


        int[] decodedFormation = decodeFormationString(formation);

        squad.updateFieldPlayers(decodedFormation[0], decodedFormation[1], decodedFormation[2]);

        if (view.getFormationPanel() == null) {
            view.setFormationPanel(new FormationPanel());
        } else if (view.getFormationPanel() != null) {
            view.clearPanel();
            view.setFormationPanel(new FormationPanel());
        }

        view.getFormationPanel().add(squad.getGoalkeepers());
        view.getFormationPanel().add(squad.getDefenders());
        view.getFormationPanel().add(squad.getMidfielders());
        view.getFormationPanel().add(squad.getStrikers());
        view.getFormationPanel().add(squad.getSubs());

        view.updateFormationPanel();
    }

    private int[] decodeFormationString(String formation) {
        int[] players = new int[3];
        int count = 0;

        for (int i = 0; i < formation.length(); ++i) {
            if (formation.charAt(i) != '-') {
                players[count++] = Character.getNumericValue(formation.charAt(i));
            }
        }

        return players;
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

                    showFileChooserDialog(index);
                }
            });
        }
    }

    private void showFileChooserDialog(int index) {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(view);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            boolean isImageFile = checkFileValidity(selectedFile);

            if (!isImageFile) {
                JOptionPane.showMessageDialog(null, "Please select an image file!");
            } else {
                setImgToPlayer(selectedFile.getPath(), index);
                setPlayerName(selectedFile.getName().toLowerCase(), index);
            }
        }
    }

    private boolean checkFileValidity(File file) {

        String name = file.getName().toLowerCase();
        if (name.endsWith(".jpeg") ||
                name.endsWith(".jpg") ||
                name.endsWith(".png") ||
                name.endsWith(".tiff") ||
                name.endsWith(".gif")) {
            return true;
        }

        return false;
    }

    private void setImgToPlayer(String file, int index) {

        view.getPlayer(index).setImgPath(file);
        view.getPanel(index).setImage();
    }

    private void setPlayerName(String imageFile, int index) {

        int count = 0;
        String finalString = "";

        while(imageFile.charAt(count) != '.') {
            finalString += String.valueOf(imageFile.charAt(count));
            count++;
        }

        String capitalised = String.valueOf(finalString.charAt(0)).toUpperCase() + finalString.substring(1, finalString.length());

        view.getPlayer(index).setName(capitalised);
        view.getPanel(index).setNameLabel();
    }

}
