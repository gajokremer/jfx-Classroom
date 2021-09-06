package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    
    @FXML
    private Label currentUser;
    
    @FXML
    private TableView<UserAccount> tvStudentList;
    
    @FXML
    private TableColumn<UserAccount, String> tcUsername;
    
    @FXML
    private TableColumn<UserAccount, String> tcGender;
    
    @FXML
    private TableColumn<UserAccount, String> tcCareer;
    
    @FXML
    private TableColumn<UserAccount, String> tcBirthday;
    
    @FXML
    private TableColumn<UserAccount, String> tcBrowser;
    
    private String usernameToDisplay = "";
    
    public String getUsernameToDisplay() {
		return usernameToDisplay;
	}

	public void setUsernameToDisplay(String usernameToDisplay) {
		this.usernameToDisplay = usernameToDisplay;
	}

	
	private ObservableList<UserAccount> observableList;
    
    private String[] browsers = {"Chrome", "Safari", "Firefox", "Edge", "Opera"};
    private ObservableList<String> browsersList = FXCollections.observableArrayList(browsers);
    private ChoiceBox<String> cb = new ChoiceBox<String>(browsersList);
    
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
    
	private void initializeTableView() {

    	observableList = FXCollections.observableArrayList(classroomManager.getStudents());

    	tvStudentList.setItems(observableList);
    	tcUsername.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("username"));
    	tcGender.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("gender"));
    	tcCareer.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("career"));
    	tcBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("birthday"));
    	tcBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("favoriteBrowser"));
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
    	
    	setUsernameToDisplay(txtUsername.getText());
    	
    	if(classroomManager.accountExists(txtUsername.getText(), txtPassword.getText()) == true) {
    		
    		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Account-list.fxml"));
    		fxmlloader.setController(this);
    		Parent accountList = fxmlloader.load();
    		mainPane.getChildren().setAll(accountList);
    		
    		currentUser.setText("Current user: " + getUsernameToDisplay());
    		
    		initializeTableView();
    		
    	} else {
    		
    		String header = "Log in error";
    		String message = "Username or password is incorrect";
    		
    		showWarningDialogue(header, message);
    		txtUsername.setText(null);
    		txtPassword.setText(null);
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
    void createAccount(ActionEvent event) throws IOException {

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
    	
    	if(username != null && password != null && url != null && gender != null && 
    			birthday != null && career != null && favoriteBrowser != null) {
    		
    		UserAccount account = new UserAccount(username, password, url, birthday, gender, career, favoriteBrowser);
    		
    		if(classroomManager.addAccount(account) == true) {

    			String header = "Sign up successful";
    			String message = "Account created successfully";

    			showSuccessDialogue(header, message);
    		}
    		
    	} else {
    		
			String header = "Sign up error";
    		String message = "All parameters are mandatory";
    		
    		showWarningDialogue(header, message);
    	}
    	
    	FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Login.fxml"));
    	fxmlloader.setController(this);
    	Parent logIn = fxmlloader.load();
    	mainPane.getChildren().setAll(logIn);
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
    
    @FXML
    void logOut(ActionEvent event) throws IOException {

    	FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Login.fxml"));
    	fxmlloader.setController(this);
    	Parent logIn = fxmlloader.load();
    	mainPane.getChildren().setAll(logIn);
    }
    
    @FXML
    void saveData(ActionEvent event) throws FileNotFoundException, IOException {
    	
    	if(classroomManager.saveData()) {
    		
    		String header = "Data processing";
    		String message = "Data saved successfully";
    		showSuccessDialogue(header, message);
    	}
    }
    
    @FXML
    void loadData(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, IOException {

    	if(classroomManager.loadData()) {

    		String header = "Data processing";
    		String message = "Data loaded successfully";
    		showSuccessDialogue(header, message);

    		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Account-list.fxml"));
    		fxmlloader.setController(this);
    		Parent accountList = fxmlloader.load();
    		mainPane.getChildren().setAll(accountList);
    		
    		currentUser.setText("Current user: " + getUsernameToDisplay());
    		
    		initializeTableView();
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
    	
    	if(itemIsFilled(gender)) {
    		
    		return gender;
    		
    	} else {
    		
    		return null;
    	}
    }
    
    public String getBirthdayValue() {
    	
    	if(birthDate.getValue() != null) {
    		
    		LocalDate aDate = birthDate.getValue();
//    		String formattedDate  = aDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    		
    		System.out.println("Birth date: " + aDate.toString());
    		
    		return aDate.toString();
    		
    	} else {
    		
    		return null;
    	}
    }

    public String getFavoriteBrowser() {
    	
    	String browser = cb.getSelectionModel().getSelectedItem();
    	
    	System.out.println("Favorite browser: " + browser);
    	
    	if(itemIsFilled(browser) == true) {
    		
    		return browser;
    		
    	} else {
    		
    		return null;
    	}
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
    
    public boolean itemIsFilled(String item) {
    	
    	if(item != null) {
    		
    		return true;	
    		
    	} else {
    		
    		return false;
    	}
    }
    
    public void showSuccessDialogue(String header, String message) {
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Contact Manager");
		alert.setHeaderText(header);
		alert.setContentText(message);
		alert.showAndWait();
    }
    
    public void showWarningDialogue(String header, String message) {
    	
    	Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Contact Manager");
		alert.setHeaderText(header);
		alert.setContentText(message);
		alert.showAndWait();
    }
}