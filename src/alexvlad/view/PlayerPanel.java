package alexvlad.view;

import javax.swing.*;
import java.awt.*;
/**
 * Created by Vlad-minihp on 02/03/2016.
 */
public class PlayerPanel extends JPanel {

    private JButton playerButton;
    private String name;

    public PlayerPanel(String name) {
        super();

        this.name = name;
        createWidget();
    }

    public void createWidget() {

        setLayout(new BorderLayout());

        playerButton = new JButton("+");

        JLabel playerName = new JLabel(name);
        playerName.setHorizontalAlignment(JLabel.CENTER);

        add(playerButton, BorderLayout.CENTER);
        add(playerName, BorderLayout.SOUTH);

        setMaximumSize(new Dimension(10, 10));
    }

    public JButton getButton() {
        return playerButton;
    }
}
