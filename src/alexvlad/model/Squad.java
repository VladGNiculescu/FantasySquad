package alexvlad.model;

import java.util.ArrayList;

public class Squad {

    private final int totalGoalkeepers = 2;
    private final int totalDef = 5;
    private final int totalMidfielders = 5;
    private final int totalStrikers = 3;

    private ArrayList<Goalkeeper> goalkeepers;
    private ArrayList<Defender> defenders;
    private ArrayList<Midfielder> midfielders;
    private ArrayList<Striker> strikers;
    private ArrayList<Player> subs;

    private int id;

    public Squad() {
        goalkeepers = new ArrayList<Goalkeeper>();
        defenders = new ArrayList<Defender>();
        midfielders = new ArrayList<Midfielder>();
        strikers = new ArrayList<Striker>();
        subs = new ArrayList<Player>();

        id = 0;
    }

    public void generate(int gk, int def, int mid, int at) {
        if (goalkeepers.isEmpty() || defenders.isEmpty() || midfielders.isEmpty() || strikers.isEmpty() || subs.isEmpty()) {

            generateFieldPlayers(gk, def, mid, at);
            generateSubs(totalGoalkeepers - gk, totalDef - def, totalMidfielders - mid, totalStrikers - at);

        } else {


        }
    }

    private void generateFieldPlayers(int gk, int def, int mid, int at) {
        Goalkeeper goalkeeper = new Goalkeeper(id);
        goalkeepers.add(goalkeeper);
        id++;

        for (int i = 0; i < def; ++i) {
            Defender defender = new Defender(id);
            defenders.add(defender);
            id++;
        }

        for (int i = 0; i < mid; ++i) {
            Midfielder midfielder = new Midfielder(id);
            midfielders.add(midfielder);
            id++;
        }

        for (int i = 0; i < at; ++i) {
            Striker striker = new Striker(id);
            strikers.add(striker);
            id++;
        }
    }

    private void generateSubs(int gk, int def, int mid, int at) {
        for (int i = 0; i < gk; ++i) {
            Goalkeeper goalkeeper = new Goalkeeper(id);
            subs.add(goalkeeper);
            id++;
        }

        for (int i = 0; i < def; ++i) {
            Defender defender = new Defender(id);
            subs.add(defender);
            id++;
        }

        for (int i = 0; i < mid; ++i) {
            Midfielder midfielder = new Midfielder(id);
            subs.add(midfielder);
            id++;
        }

        for (int i = 0; i < at; ++i) {
            Striker striker = new Striker(id);
            subs.add(striker);
            id++;
        }
    }
}
