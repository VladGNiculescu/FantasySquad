package alexvlad;

import alexvlad.controller.Controller;
import alexvlad.model.Squad;
import alexvlad.view.Fantasy;

public class Main {

    public static void main(String[] args) {
        Fantasy mainView = new Fantasy();
        Squad squad = new Squad();
        Controller controller = new Controller(mainView, squad);

    }
}
