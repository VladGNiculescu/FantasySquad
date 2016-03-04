package alexvlad.view;

import alexvlad.model.Player;

import javax.swing.*;
import java.awt.*;
/**
 * Created by Vlad-minihp on 02/03/2016.
 */
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

        add(playerButton, BorderLayout.CENTER);
        add(playerNameLabel, BorderLayout.SOUTH);

        setMaximumSize(new Dimension(10, 10));
    }

    public JButton getButton() {
        return playerButton;
    }
}
