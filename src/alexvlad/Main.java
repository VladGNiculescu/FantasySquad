package alexvlad;
import alexvlad.controller.Controller;
import alexvlad.model.Squad;
import alexvlad.view.Fantasy;

public class Main {
    public static void main(String[] args) {

        Fantasy view = new Fantasy();
        Squad squad = new Squad();
        Controller controller = new Controller(view, squad);
        view.setController(controller);
        squad.generate(2, 5, 5, 3);
    }
}
