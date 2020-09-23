import Controller.MainController;
import Model.Model;
import View.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainProgram extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage praimaryStage){
		Model m = new Model();
		View v = new View(praimaryStage);
		MainController c = new MainController(m ,v);
	}


}
