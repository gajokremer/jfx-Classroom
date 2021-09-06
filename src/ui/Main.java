package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	private ClassroomGUI classroomGUI;

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Main-pane.fxml"));
		classroomGUI = new ClassroomGUI();
		fxmlloader.setController(classroomGUI);
		Parent root = fxmlloader.load();
		Scene scene =  new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Classroom");
		primaryStage.show();
		
		classroomGUI.startClassroom();
	}
}