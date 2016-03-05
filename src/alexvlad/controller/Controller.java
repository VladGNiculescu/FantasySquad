package alexvlad.controller;

import alexvlad.model.*;
import alexvlad.view.Fantasy;
import alexvlad.view.FormationPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
public class Controller implements ActionListener {

    Fantasy view;
    Squad squad;
    public Controller(Fantasy view, Squad squad) {
        this.view = view;
        this.squad = squad;
        this.view.setVisible(true);
        setupFormations();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().getClass() == JTextField.class) {
            JTextField source = (JTextField) e.getSource();
            String text = source.getText();
            int index = view.getPlayerIDForTextField(source);
            userChangedPlayerName(text, index);
        }
    }
    private void setupFormations() {
        view.getFormationList().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFormation = String.valueOf(view.getFormationList().getSelectedItem());
                if (view.getFormationList().getSelectedIndex() > 0) {
                    formationChanged(selectedFormation);
                    view.resize();
                }
                addActionListenersToPlayers();
            }
        });
    }
    private void formationChanged(String formation) {
        int[] decodedFormation = decodeFormationString(formation);
        squad.updateFieldPlayers(decodedFormation[0], decodedFormation[1], decodedFormation[2]);
        if (view.getFormationPanel() == null) {
            view.setFormationPanel(new FormationPanel(view));
        } else if (view.getFormationPanel() != null) {
            view.clearPanel();
            view.setFormationPanel(new FormationPanel(view));
        }
        sendPlayersToPitch();
        view.updateFormationPanel();
    }
    private void sendPlayersToPitch() {
        ArrayList<HashMap<String, String>> playersToSend = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < squad.getGoalkeepers().size(); ++i) {
            Player current = squad.getGoalkeepers().get(i);
            HashMap<String, String> infoMap = new HashMap<String, String>();
            infoMap.put("name", current.getName());
            infoMap.put("imagePath", current.getImgPath());
            playersToSend.add(infoMap);
        }
        view.getFormationPanel().add(playersToSend);
        playersToSend.clear();
        for (int i = 0; i < squad.getDefenders().size(); ++i) {
            Player current = squad.getDefenders().get(i);
            HashMap<String, String> infoMap = new HashMap<String, String>();
            infoMap.put("name", current.getName());
            infoMap.put("imagePath", current.getImgPath());
            playersToSend.add(infoMap);
        }
        view.getFormationPanel().add(playersToSend);
        playersToSend.clear();
        for (int i = 0; i < squad.getMidfielders().size(); ++i) {
            Player current = squad.getMidfielders().get(i);
            HashMap<String, String> infoMap = new HashMap<String, String>();
            infoMap.put("name", current.getName());
            infoMap.put("imagePath", current.getImgPath());
            playersToSend.add(infoMap);
        }
        view.getFormationPanel().add(playersToSend);
        playersToSend.clear();
        for (int i = 0; i < squad.getStrikers().size(); ++i) {
            Player current = squad.getStrikers().get(i);
            HashMap<String, String> infoMap = new HashMap<String, String>();
            infoMap.put("name", current.getName());
            infoMap.put("imagePath", current.getImgPath());
            playersToSend.add(infoMap);
        }
        view.getFormationPanel().add(playersToSend);
        playersToSend.clear();
        for (int i = 0; i < squad.getSubs().size(); ++i) {
            Player current = squad.getSubs().get(i);
            HashMap<String, String> infoMap = new HashMap<String, String>();
            infoMap.put("name", current.getName());
            infoMap.put("imagePath", current.getImgPath());
            playersToSend.add(infoMap);
        }
        view.getFormationPanel().add(playersToSend);
        playersToSend.clear();
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
            JTextField currentTextField = view.getPlayerTextFieldById(id);
            currentTextField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    actionPerformed(new ActionEvent(currentTextField, 1001, null));
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    actionPerformed(new ActionEvent(currentTextField, 1001, null));
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    actionPerformed(new ActionEvent(currentTextField, 1001, null));
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
        view.getPlayer(index).put("imagePath", file);
        squad.getPlayerList().get(index).setImgPath(file);
        view.getPanel(index).setImage();
        view.resize();
    }
    private void setPlayerName(String imageFile, int index) {
        int count = 0;
        String finalString = "";
        while (imageFile.charAt(count) != '.') {
            finalString += String.valueOf(imageFile.charAt(count));
            count++;
        }
        String capitalised = String.valueOf(finalString.charAt(0)).toUpperCase() + finalString.substring(1, finalString.length());
        view.getPlayer(index).put("name", capitalised);
        squad.getPlayerList().get(index).setName(capitalised);
        view.getPanel(index).setNameLabel();
    }
    private void userChangedPlayerName(String name, int index) {
        view.getPlayer(index).put("name", name);
        squad.getPlayerList().get(index).setName(name);
    }
}
