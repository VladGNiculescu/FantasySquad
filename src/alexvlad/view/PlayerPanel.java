package alexvlad.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PlayerPanel extends JPanel {

    private JButton playerButton;
    private JLabel playerNameLabel;
    private HashMap<String, String> player;

    public PlayerPanel(HashMap<String, String> player) {
        super();

        this.player = player;
        createWidget();
    }

    public void createWidget() {

        setLayout(new BorderLayout());

        playerButton = new JButton("+");

        playerNameLabel = new JLabel(player.get("name"));
        playerNameLabel.setHorizontalAlignment(JLabel.CENTER);
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        playerButton.setPreferredSize(new Dimension(50, 50));

        buttonPanel.add(playerButton);

        add(buttonPanel, BorderLayout.CENTER);


        add(playerNameLabel, BorderLayout.SOUTH);

        if (player.get("imagePath") != null) {
            setImage();
        }
    }

    public JButton getButton() {
        return playerButton;
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
        playerNameLabel.setText(player.get("name"));
    }

}
