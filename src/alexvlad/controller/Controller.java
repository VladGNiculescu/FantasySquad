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

        ArrayList<Player> players = squad.getPlayerList();

        ArrayList<Player> goalkeepers = new ArrayList<Player>();
        ArrayList<Player> defenders = new ArrayList<Player>();
        ArrayList<Player> midfielders = new ArrayList<Player>();
        ArrayList<Player> strikers = new ArrayList<Player>();

        ArrayList<Player> subs = new ArrayList<Player>();

        goalkeepers.add(players.get(0));
        subs.add(players.get(1));


        for (int i = 0; i < players.size(); ++i) {
            if (players.get(i) instanceof Defender && !players.get(i).isSub()) {
                defenders.add(players.get(i));
            } else if (players.get(i) instanceof Defender && players.get(i).isSub()) {
                subs.add(players.get(i));
            }

            if (players.get(i) instanceof Midfielder && !players.get(i).isSub()) {
                midfielders.add(players.get(i));
            } else if (players.get(i) instanceof Midfielder && players.get(i).isSub()) {
                subs.add(players.get(i));
            }

            if (players.get(i) instanceof Striker && !players.get(i).isSub()) {
                strikers.add(players.get(i));
            } else if (players.get(i) instanceof Striker && players.get(i).isSub()) {
                subs.add(players.get(i));
            }
        }

        if (view.getFormationPanel() == null) {
            view.setFormationPanel(new FormationPanel());
        } else if (view.getFormationPanel() != null) {
            view.clearPanel();
            view.setFormationPanel(new FormationPanel());
           /* view.getFormationPanel().remove(goalkeepers);
            view.getFormationPanel().remove(defenders);
            view.getFormationPanel().remove(midfielders);
            view.getFormationPanel().remove(strikers);
            view.getFormationPanel().remove(subs);
            */
        }

        System.out.println(goalkeepers);
        view.getFormationPanel().add(goalkeepers);
        view.getFormationPanel().add(defenders);
        view.getFormationPanel().add(midfielders);
        view.getFormationPanel().add(strikers);
        view.getFormationPanel().add(subs);

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

                    System.out.println("ID: " + index);

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
            }

            setImgToPlayer(selectedFile.getPath(), index);
//            setPlayerName(selectedFile.getPath(), index);
            setPlayerName(selectedFile.getName().toLowerCase(), index);
            System.out.println(view.getPlayer(index).getImgPath());


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
