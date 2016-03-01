package alexvlad;

import alexvlad.controller.Controller;
import alexvlad.model.Squad;
import alexvlad.view.MainView;

public class Main {

    public static void main(String[] args) {
        MainView mainView = new MainView();
        Squad squad = new Squad();
        Controller controller = new Controller(mainView, squad);

    }
}
