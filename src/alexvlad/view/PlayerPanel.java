package alexvlad.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PlayerPanel extends JPanel {

    private JButton playerButton;
    private JTextField playerNameTextField;
    private HashMap<String, String> player;

    private Fantasy f;

    /**
     * Constructor for PlayerPanel
     *
     * @param player HashMap with Player info
     * @param f      the parent view
     */

    public PlayerPanel(HashMap<String, String> player, Fantasy f) {
        super();

        this.player = player;
        this.f = f;
        createWidget();
    }

    /**
     * Method to set widgets on the panel
     */

    public void createWidget() {

        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBorder(BorderFactory.createEmptyBorder());

        playerButton = new JButton("+");

        playerNameTextField = new JTextField(player.get("name"));
        playerNameTextField.setHorizontalAlignment(JTextField.CENTER);
        JPanel p = new JPanel();
        BorderLayout bl = new BorderLayout();
        bl.setVgap(5);
        p.setLayout(bl);

        p.add(playerButton, BorderLayout.CENTER);

        p.add(playerNameTextField, BorderLayout.SOUTH);

        playerButton.setPreferredSize(new Dimension(70, 30));


        add(p);

        if (player.get("imagePath") != null) {
            setImage();
        }
    }

    /**
     * Getter for the JButton on the Panel
     *
     * @return
     */

    public JButton getPlayerButton() {
        return playerButton;
    }

    /**
     * Getter for the JTextField on the Panel
     *
     * @return
     */

    public JTextField getPlayerNameTextField() {
        return playerNameTextField;
    }

    /**
     * Getter for the HashMap that holds player info
     *
     * @return
     */

    public HashMap<String, String> getPlayer() {
        return player;
    }

    /**
     * Method to set the player's image on the panel
     */

    public void setImage() {
        try {
            ImageIcon ico = new ImageIcon(player.get("imagePath"));
            playerButton.setText("");
            playerButton.setIcon(ico);
            playerButton.setPreferredSize(new Dimension(ico.getIconWidth(), ico.getIconHeight()));
            f.resize();
        } catch (Exception e) {
        }
    }

    /**
     * Method to set the player's name on the JTextField
     */

    public void setNameLabel() {
        playerNameTextField.setText(player.get("name"));
    }
}
