package alexvlad.view;

import alexvlad.model.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {

    private JButton playerButton;
    private JLabel playerNameLabel;
    private Player player;

    public PlayerPanel(Player player) {
        super();

        this.player = player;
        createWidget();
    }

    public void createWidget() {

        setLayout(new BorderLayout());

        playerButton = new JButton("+");


        playerNameLabel = new JLabel(player.getName());
        playerNameLabel.setHorizontalAlignment(JLabel.CENTER);
        JPanel buttonPanel = new JPanel();


        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));


        playerButton.setPreferredSize(new Dimension(50, 50));

        buttonPanel.add(playerButton);

        add(buttonPanel, BorderLayout.CENTER);


        add(playerNameLabel, BorderLayout.SOUTH);


        if (player.getImgPath() != null) {
            setImage();
        }
    }

    public JButton getButton() {
        return playerButton;
    }

    public Player getPlayer() {
        return player;
    }

    public void setImage() {
        try {
            System.out.println(player.getImgPath());
            ImageIcon ico = new ImageIcon(player.getImgPath());
            playerButton.setText("");
            playerButton.setIcon(ico);
            playerButton.setPreferredSize(new Dimension(ico.getIconWidth(), ico.getIconHeight()));

        } catch (Exception e) {
        }
    }

    public void setNameLabel() {
        playerNameLabel.setText(player.getName());
    }

}
