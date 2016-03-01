package alexvlad.view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public MainView() {
        super();

        createWidgets();
    }

    private void createWidgets() {
        this.setMinimumSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] formations = {"Select a formation...", "4-4-2", "4-3-3", "3-5-2", "5-3-2", "3-4-3", "4-5-1"};

        JComboBox formationList = new JComboBox(formations);

        JPanel borderPanel = new JPanel();
        borderPanel.setLayout(new BorderLayout());

        borderPanel.add(formationList, BorderLayout.NORTH);
        borderPanel.add(new FormationPanel(4,4,2),BorderLayout.CENTER);
        add(borderPanel);

        pack();
    }
}
