package alexvlad.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    JComboBox formationList;
    JPanel borderPanel;
    FormationPanel formationPanel;

    public MainView() {
        super();

        createWidgets();
    }

    private void createWidgets() {
        setMinimumSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] formations = {"Select a formation...", "4-4-2", "4-3-3", "3-5-2", "5-3-2", "3-4-3", "4-5-1"};

        formationList = new JComboBox(formations);

        formationList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFormation = String.valueOf(formationList.getSelectedItem());
                if (formationList.getSelectedIndex() > 0) {
                    System.out.println("Changed");
                    changeFormationLayout(selectedFormation);
                }
            }
        });

        borderPanel = new JPanel();
        borderPanel.setLayout(new BorderLayout());

        borderPanel.add(formationList, BorderLayout.NORTH);
        add(borderPanel);

        pack();
    }

    private void changeFormationLayout(String formation) {

        int[] players = new int[3];
        int count = 0;

        for (int i = 0; i < formation.length(); ++i) {
            if (formation.charAt(i) != '-') {
                players[count++] = Character.getNumericValue(formation.charAt(i));
            }
        }

        if (formationPanel != null) {
            borderPanel.remove(formationPanel);
        }

        formationPanel = new FormationPanel(players[0], players[1], players[2]);
        borderPanel.add(formationPanel, BorderLayout.CENTER);
        borderPanel.validate();
    }
}
