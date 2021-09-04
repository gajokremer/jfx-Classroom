package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ClassroomManager {
	
	private String FILE_SAVE_PATH = "data/students.bin";
	
	private List<UserAccount> students;
	
	public List<UserAccount> getStudents() {
		return students; 
	}
	
	public void setStudents(List<UserAccount> students) {
		this.students = students;
	}
	
	public ClassroomManager() {
		
		students = new ArrayList<UserAccount>();
		students.add(createBaseAccount());
	}
	
	public UserAccount createBaseAccount() {
		
		UserAccount baseAccount = new UserAccount("admin", "123456", "url", "2/8/2002", "Male", "Software Engineering", "Safari");
		
		return baseAccount;
	}
	
	public boolean accountExists(String username, String password) {
		
		boolean exists = false;
		
		for(int i = 0; i < students.size(); i++) {
			
			if(students.get(i).getUsername().equalsIgnoreCase(username) && 
					students.get(i).getPassword().equals(password)) {
				
				exists = true;
			}
		}
		
		return exists;
	}
	
	public boolean addAccount(UserAccount account) {
		
		if(students.add(account)) {
			
			return true;
			
		} else {
			
			return false;
		}
	}
	
	public boolean saveData() throws FileNotFoundException, IOException {
		
//		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_SAVE_PATH));
//		oos.writeObject(students);
//		oos.close();
		
		File f = new File(FILE_SAVE_PATH);
		
		boolean wasSaved = false;
		
		if(f.exists()) {
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_SAVE_PATH));
			oos.writeObject(students);
			oos.close();
			wasSaved = true;
		}
		
		return wasSaved;
	}
	
	@SuppressWarnings("unchecked")
	public boolean loadData() throws FileNotFoundException, IOException, ClassNotFoundException {
		
		File f = new File(FILE_SAVE_PATH);
		
		boolean isLoaded = false;
		
		if(f.exists()) {
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			students = (List<UserAccount>) ois.readObject();
			ois.close();
			isLoaded = true;
		}
		
		return isLoaded;
	}
}
