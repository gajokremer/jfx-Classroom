package ui;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ClassroomManager;
import model.UserAccount;

public class ClassroomGUI {

	@FXML
    private Pane mainPane;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;
    
    @FXML
    private TextField picUrl;    
    
    @FXML
    private HBox favoriteBrowser;
    
    
    private String[] browsers = {"Chrome", "Safari", "Firefox", "Edge", "Opera"};
    
    private ObservableList<String> browsersList = FXCollections.observableArrayList(browsers);
    
    private ChoiceBox<String> cb = new ChoiceBox<String>(browsersList);
    
    
    @FXML
    private DatePicker birthDate;
    
    @FXML
    private ToggleGroup tgGender;

    @FXML
    private RadioButton rbMale;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private RadioButton rbOther;
    
    @FXML
    private CheckBox careerS;

    @FXML
    private CheckBox careerT;

    @FXML
    private CheckBox careerI;
    
    
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
		
    	createChoiceBox();
    }
    
    @FXML
    void createAccount(ActionEvent event) {

    	String username = txtUsername.getText();
    	System.out.println("Username: " + username);
    	
    	String password = txtPassword.getText();
    	System.out.println("Password: " + password);
    	
    	String url = picUrl.getText();
    	System.out.println("Profile pic: " + url);
    	
    	String gender = getGenderValue();
    	String birthday = getBirthdayValue();
    	String career =	getCareer();
    	String favoriteBrowser = getFavoriteBrowser();
    	
    	UserAccount account = new UserAccount(username, password, url, gender, birthday, career, favoriteBrowser);
    	
    	if(classroomManager.addAccount(account) == true) {
    		
    		String message = "Account created successfully";
    		
    		showSuccessDialogue(message);
    	}
    }

    @FXML
    void signIn(ActionEvent event) throws IOException {

    	FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Login.fxml"));
    	fxmlloader.setController(this);
    	Parent logIn = fxmlloader.load();
    	mainPane.getChildren().setAll(logIn);
    }

    @FXML
    void uploadPic(ActionEvent event) {

    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Search image");
    	File img = fileChooser.showOpenDialog(null);
    	
    	if(img != null) {
    		
    		String url = img.getAbsolutePath();
    		picUrl.setText(url);
    	}
    }
    
    public String getGenderValue() {
    	
    	String gender = "";
    	
    	if(rbMale.isSelected()) {
    		
    		gender = "Male";
    		
    	} else if(rbFemale.isSelected()) {
    		
    		gender = "Female";
    		
    	} else if(rbOther.isSelected()) {
    		
    		gender = "Other";
    	}
    	
    	System.out.println("Gender: " + gender);
    	
    	return gender;
    }
    
    public String getBirthdayValue() {
    	
    	if(birthDate.getValue() != null) {
    		
    		LocalDate aDate = birthDate.getValue();
//    		String formattedDate  = aDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    		
    		System.out.println("Birth date: " + aDate.toString());
    		
    		return aDate.toString();
    	}
    	
		return null;
    }

    public String getFavoriteBrowser() {
    	
    	String browser = cb.getSelectionModel().getSelectedItem();
    	
    	System.out.println("Favorite browser: " + browser);
    	
    	return browser;
    }
    
    public void createChoiceBox() {
    	
    	favoriteBrowser.getChildren().add(cb);
    }

    public String getCareer() {
    	
    	String career = "";
    	
    	if(careerS.isSelected()) {
    		
    		career += "Software Engineer";
    		
    	}
    	
    	if(careerT.isSelected()) {
    		
    		if(careerS.isSelected()) {
    			
    			career += ", ";
    		}
    		
    		career += "Telematic Engineer";
    		
    	} 
    	
    	if(careerI.isSelected()) {
    		
    		if(careerS.isSelected() || careerT.isSelected()) {
    			
    			career += ", ";
    		}
    		
    		career += "Industrial Engineer";
    	}
    	
    	System.out.println("Career: " + career);
    	
    	return career;
    }
    
    public void showSuccessDialogue(String message) {
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Contact Manager");
		alert.setHeaderText("Admin");
		alert.setContentText(message);
		alert.showAndWait();
    }
}