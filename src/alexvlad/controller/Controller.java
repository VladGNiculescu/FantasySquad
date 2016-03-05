package alexvlad.controller;

import alexvlad.model.*;
import alexvlad.view.Fantasy;
import alexvlad.view.FormationPanel;

import javax.swing.*;
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

    /**
     * Constructor for Controller
     * Sets the view and the model
     *
     * @param view
     * @param squad
     */
    public Controller(Fantasy view, Squad squad) {
        this.view = view;
        this.squad = squad;
        this.view.setVisible(true);
        setupFormations();
    }

    /**
     * Overriding actionPerformed method for JTextField updates
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().getClass() == JTextField.class) {
            JTextField source = (JTextField) e.getSource();
            String text = source.getText();
            int index = view.getPlayerIDForTextField(source);
            userChangedPlayerName(text, index);
        }
    }

    /**
     * Method to add action listeners for the JComboBox and for Player Buttons
     */
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

    /**
     * Method to respond when the formation was changed.
     * Will decode the formation string and will send requests to view for it to update.
     *
     * @param formation
     */

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

    /**
     * Method to arrange players based on their role.
     * Will send requests to the view to update every line according to the player role.
     */

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

    /**
     * Method to decode the formation String
     *
     * @param formation String representation of the formation (i.e. 3-4-3)
     * @return an array of ints representing the number of players on each line (defense, midfield and attack)
     */

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

    /**
     * Method that iterates over all Player Panels and adds action listeners for Buttons and TextFields
     */

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

    /**
     * Method to show the file chooser when selecting an image file
     * @param index int of the ID of the player that is changing its image
     */

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

    /**
     * Method to check if the file selected by the user is an image file
     * @param file the selected file
     * @return boolean value for the validity of file
     */

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

    /**
     * Method to update the player's image file
     * Will update the image in view and model as well
     * @param file Path of the image file
     * @param index ID of the player
     */

    private void setImgToPlayer(String file, int index) {
        view.getPlayer(index).put("imagePath", file);
        squad.getPlayerList().get(index).setImgPath(file);
        view.getPanel(index).setImage();
        view.resize();
    }

    /**
     * Method to update the player's name from the Image File
     * The method looks into the file name and selects the player's name from it
     * Will update the name in view and in model
     * @param imageFile Image file name (as in 'image.png')
     * @param index  ID of the player
     */

    private void setPlayerName(String imageFile, int index) {
        int count = imageFile.length() - 1;

        while (imageFile.charAt(count) != '.') {
            count--;
        }

        String capitalised = String.valueOf(imageFile.charAt(0)).toUpperCase() + imageFile.substring(1, count);

        for (int i = 1; i < capitalised.length(); ++i) {
            if (capitalised.charAt(i) == '.') {
                capitalised = capitalised.substring(0, i + 1) + String.valueOf(capitalised.charAt(i + 1)).toUpperCase() + capitalised.substring(i + 2, capitalised.length());
            }
        }

        view.getPlayer(index).put("name", capitalised);
        squad.getPlayerList().get(index).setName(capitalised);
        view.getPanel(index).setNameLabel();
    }

    /**
     * Method to update the name of the player based on calls received on the JTextField Action Listener
     * @param name The name of the player
     * @param index ID of the player
     */

    private void userChangedPlayerName(String name, int index) {
        view.getPlayer(index).put("name", name);
        squad.getPlayerList().get(index).setName(name);
    }
}
