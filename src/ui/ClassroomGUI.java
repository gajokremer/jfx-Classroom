package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ClassroomManager;

public class ClassroomGUI {

    @FXML
    private Pane mainPane;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;
    
    @FXML
    private TextField profilePic;    
    
    @FXML
    private ChoiceBox<String> favoriteBrowser;
    
    @FXML
    private DatePicker birthDate;
    
    private ClassroomManager classroomManager;
    
    private Stage mainStage;
    
    public Stage getMainStage() {
		return mainStage;
	}


	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}


	public ClassroomGUI() {
    	
		classroomManager = new ClassroomManager();
    }
    
    @FXML
    void startClassroom() throws IOException {

    	FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Login.fxml"));
    	fxmlloader.setController(this);
    	Parent logIn = fxmlloader.load();
    	mainPane.getChildren().setAll(logIn);
    }
    
    @FXML
    void logIn(ActionEvent event) throws IOException {
    	
    	if(classroomManager.accountExists(txtUsername.getText(), txtPassword.getText()) == true) {
    		
    		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Account-list.fxml"));
    		fxmlloader.setController(this);
    		Parent accountList = fxmlloader.load();
    		mainPane.getChildren().setAll(accountList);
    	}
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
    	
    	FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Register.fxml"));
		fxmlloader.setController(this);
		Parent signUp = fxmlloader.load();
		mainPane.getChildren().setAll(signUp);
    }
    
    @FXML
    void createAccount(ActionEvent event) {

    }

    @FXML
    void signIn(ActionEvent event) throws IOException {

    }

    @FXML
    void uploadPic(ActionEvent event) {

    }
}
