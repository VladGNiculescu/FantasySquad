package alexvlad.view;

import alexvlad.model.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vlad-minihp on 02/03/2016.
 */
public class PlayerPanel extends JPanel {


    private String name;

    public PlayerPanel(String name)
    {
       super();
        this.name = name;
        createWidget();

    }

    public void createWidget(){


        setLayout(new BorderLayout());

        JButton playerButton = new JButton("+");

        JLabel playerName = new JLabel(name);

        playerName.setHorizontalAlignment(JLabel.CENTER);

        add(playerButton,BorderLayout.CENTER);
        add(playerName,BorderLayout.SOUTH);

        setMaximumSize(new Dimension(10,10));
    }
}
