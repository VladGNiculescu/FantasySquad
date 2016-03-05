package alexvlad.view;

import alexvlad.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Fantasy extends JFrame {

    private JComboBox formationList;
    private JPanel borderPanel;
    private FormationPanel formationPanel;
    private Controller controller;

    /**
     * Constructor for the Fantasy class (Main View)
     */

    public Fantasy() {
        super("Fantasy Football");

        createWidgets();
    }

    /**
     * Method to create the widgets
     * Will create a JComboBox to select formations and sets the main Panel
     */

    private void createWidgets() {

        setMinimumSize(new Dimension(450, 600));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] formations = {"Select a formation...", "4-4-2", "4-3-3", "3-5-2", "5-3-2", "3-4-3", "4-5-1"};
        formationList = new JComboBox(formations);
        borderPanel = new JPanel();
        borderPanel.setLayout(new BorderLayout());
        borderPanel.add(formationList, BorderLayout.NORTH);
        add(borderPanel);


        pack();
    }

    /**
     * Method to resize the view based on the elements that it holds
     */

    public void resize() {

        if (this.getHeight() <= this.getMinimumSize().getHeight() && this.getWidth() <= this.getMinimumSize().getWidth()) {
            pack();
            System.out.println(this.getWidth() + "" + this.getHeight());
            setMinimumSize(new Dimension(this.getWidth(), this.getHeight()));
        }
    }

    /**
     * Method to clear all elements from the panel
     */

    public void clearPanel() {
        borderPanel.remove(formationPanel);
        borderPanel.validate();
    }

    /**
     * Method to update the formation panel
     * Will add the new formed FormationPanel instance
     */

    public void updateFormationPanel() {
        borderPanel.add(formationPanel, BorderLayout.CENTER);

        borderPanel.validate();
    }

    /**
     * Method to return the JButton of a certain Player
     *
     * @param id the ID of the Player
     * @return JButton of the Player
     */

    public JButton getPlayerButtonById(int id) {
        return formationPanel.getPlayerButtonsMap().get(id).getPlayerButton();
    }

    /**
     * Method to return the JTextField of a certain Player
     *
     * @param id the ID of the Player
     * @return JTextField of the Player
     */

    public JTextField getPlayerTextFieldById(int id) {
        return formationPanel.getPlayerButtonsMap().get(id).getPlayerNameTextField();
    }

    /**
     * Method to return the data of a Player from a Panel
     *
     * @param id ID of the Player
     * @return HashMap with data (keys name and imagePath)
     */

    public HashMap<String, String> getPlayer(int id) {
        return formationPanel.getPlayerButtonsMap().get(id).getPlayer();
    }

    /**
     * Method to return the PlayerPanel based on an ID
     *
     * @param id Player ID
     * @return PlayerPanel
     */

    public PlayerPanel getPanel(int id) {
        return formationPanel.getPlayerButtonsMap().get(id);
    }

    /**
     * Method to return the ID of a certain JButton
     *
     * @param button the JButton to look for
     * @return Integer of the JButton
     */

    public Integer getPlayerIDForButton(JButton button) {
        return formationPanel.getPlayerIDForButton(button);
    }

    /**
     * Method to return the ID of a certain JTextField
     *
     * @param textField the JTextField to look for
     * @return Integer of the JTextField
     */

    public Integer getPlayerIDForTextField(JTextField textField) {
        return formationPanel.getPlayerIDForTextField(textField);
    }

    /**
     * Getter of the formation list JComboBox
     *
     * @return the JComboBox
     */

    public JComboBox getFormationList() {
        return formationList;
    }

    /**
     * Getter for the FormationPanel
     *
     * @return FormationPanel
     */

    public FormationPanel getFormationPanel() {
        return formationPanel;
    }

    /**
     * Setter for the FormationPanel
     *
     * @param formationPanel the new FormationPanel
     */

    public void setFormationPanel(FormationPanel formationPanel) {
        this.formationPanel = formationPanel;
    }

    /**
     * Method to set the view's Controller
     *
     * @param controller the Controller you're going to set
     */

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
