package alexvlad.view;

import alexvlad.controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
public class Fantasy extends JFrame {

    private JComboBox formationList;
    private JPanel borderPanel;
    private FormationPanel formationPanel;
    private Controller controller;
    private JPanel  bottomPanel;
    public Fantasy() {
        super("Fantasy Football");

        createWidgets();
    }
    private void createWidgets() {

        setMinimumSize(new Dimension(450, 600));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] formations = {"Select a formation...", "4-4-2", "4-3-3", "3-5-2", "5-3-2", "3-4-3", "4-5-1"};
        formationList = new JComboBox(formations);
        borderPanel = new JPanel();
        bottomPanel = new JPanel();
        borderPanel.setLayout(new BorderLayout());
        borderPanel.add(formationList, BorderLayout.NORTH);
        formationList.setBorder(new EmptyBorder(20,20,0,20));
        borderPanel.add(bottomPanel,BorderLayout.SOUTH);

        bottomPanel.setBorder(BorderFactory.createMatteBorder(1,0,0,0,Color.black));

        bottomPanel.setPreferredSize(new Dimension(borderPanel.getWidth(),25));
        add(borderPanel);


        pack();
    }
    public void resize() {

        if (this.getHeight() <= this.getMinimumSize().getHeight() && this.getWidth() <= this.getMinimumSize().getWidth()) {
            pack();
            System.out.println(this.getWidth() + "" + this.getHeight());
            setMinimumSize(new Dimension(this.getWidth(), this.getHeight()));
        }
    }
    public void clearPanel() {
        borderPanel.remove(formationPanel);
        borderPanel.validate();
    }
    public void updateFormationPanel() {
        borderPanel.add(formationPanel, BorderLayout.CENTER);

        borderPanel.validate();
    }
    public HashMap<Integer, PlayerPanel> getPlayerPanel() {
        return formationPanel.getPlayerButtonsMap();
    }
    public JButton getPlayerButtonById(int id) {
        return formationPanel.getPlayerButtonsMap().get(id).getPlayerButton();
    }
    public JTextField getPlayerTextFieldById(int id) {
        return formationPanel.getPlayerButtonsMap().get(id).getPlayerNameTextField();
    }
    public HashMap<String, String> getPlayer(int id) {
        return formationPanel.getPlayerButtonsMap().get(id).getPlayer();
    }
    public PlayerPanel getPanel(int id) {
        return formationPanel.getPlayerButtonsMap().get(id);
    }
    public Integer getPlayerIDForButton(JButton button) {
        return formationPanel.getPlayerIDForButton(button);
    }
    public Integer getPlayerIDForTextField(JTextField textField) {
        return formationPanel.getPlayerIDForTextField(textField);
    }
    public JComboBox getFormationList() {
        return formationList;
    }
    public JPanel getBorderPanel() {
        return borderPanel;
    }
    public FormationPanel getFormationPanel() {
        return formationPanel;
    }
    public void setFormationPanel(FormationPanel formationPanel) {
        this.formationPanel = formationPanel;
    }
    public Controller getController() {
        return controller;
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
