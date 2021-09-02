package ui;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
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
    private TextField picUrl;    
    
    @FXML
    private ComboBox<String> favoriteBrowser;
    
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

    	String username = txtUsername.getText();
    	System.out.println("Username: " + username);
    	
    	String password = txtPassword.getText();
    	System.out.println("Password: " + password);
    	
    	String url = picUrl.getText();
    	System.out.println("Profile pic: " + url);
    	
    	getGenderValue();
    	getBirthdayValue();
    }

    @FXML
    void signIn(ActionEvent event) throws IOException {

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
    
//    @FXML
//    public void fileSelection(ActionEvent event) {
//    	FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Buscar Imagen");
//		File imgFile = fileChooser.showOpenDialog(null);
//		if (imgFile != null) {
//			//Image image = new Image("file:" + imgFile.getAbsolutePath());// obtiene una imagen y podría ser setiada en un imageView
//			//ivImagen.setImage(image); // así podría ser puesta la imagen unido a la linea anterior
//			String url = imgFile.getAbsolutePath();
//			txtPhoto.setText(url);
//			//TODO hace falta guardarlo en el atributo del objeto de tipo UserAccount 
//			
//		}
//    }
    
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
    		String formattedDate  = aDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    		
    		System.out.println("Birth date: " + aDate.toString());
    		
    		return aDate.toString();
    	}
    	
		return null;
    }
}