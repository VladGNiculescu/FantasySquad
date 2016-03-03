package alexvlad.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vlad-minihp on 02/03/2016.
 */
public class PlayerPanel extends JPanel {

    private String name;
    private JButton playerButton;
    private JLabel playerNameLabel;

    public PlayerPanel(String name)
    {
       super();
        this.name = name;
        createWidget();
    }

    public void createWidget(){


        setLayout(new BorderLayout());

        playerButton = new JButton("+");

        playerNameLabel = new JLabel(name);

        playerNameLabel.setHorizontalAlignment(JLabel.CENTER);

        add(playerButton,BorderLayout.CENTER);
        add(playerNameLabel,BorderLayout.SOUTH);

        setMaximumSize(new Dimension(10,10));
    }

    @Override
    public String getName() {
        return name;
    }

    public JButton getPlayerButton() {
        return playerButton;
    }

    public JLabel getPlayerNameLabel() {
        return playerNameLabel;
    }
}
