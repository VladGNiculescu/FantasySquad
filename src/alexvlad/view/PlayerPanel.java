package alexvlad.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PlayerPanel extends JPanel {

    private JButton playerButton;
    private JTextField playerNameTextField;
    private HashMap<String, String> player;

    public PlayerPanel(HashMap<String, String> player) {
        super();

        this.player = player;
        createWidget();
    }

    public void createWidget() {

        setLayout(new BorderLayout());

        playerButton = new JButton("+");

        playerNameTextField = new JTextField(player.get("name"));
        playerNameTextField.setHorizontalAlignment(JTextField.CENTER);
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        playerButton.setPreferredSize(new Dimension(50, 50));

        buttonPanel.add(playerButton);

        add(buttonPanel, BorderLayout.CENTER);

        add(playerNameTextField, BorderLayout.SOUTH);

        if (player.get("imagePath") != null) {
            setImage();
        }
    }

    public JButton getPlayerButton() {
        return playerButton;
    }

    public JTextField getPlayerNameTextField() {
        return playerNameTextField;
    }

    public HashMap<String, String> getPlayer() {
        return player;
    }

    public void setImage() {
        try {
            ImageIcon ico = new ImageIcon(player.get("imagePath"));
            playerButton.setText("");
            playerButton.setIcon(ico);
            playerButton.setPreferredSize(new Dimension(ico.getIconWidth(), ico.getIconHeight()));

        } catch (Exception e) {
        }
    }

    public void setNameLabel() {
        playerNameTextField.setText(player.get("name"));
    }

}
