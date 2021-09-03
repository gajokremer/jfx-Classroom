package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClassroomManager {
	
	public ClassroomManager() {
		
		students = new ArrayList<UserAccount>();
		students.add(createBaseAccount());
	}

	private List<UserAccount> students;

	public List<UserAccount> getStudents() {
		return students; 
	}

	public void setStudents(List<UserAccount> students) {
		this.students = students;
	}
	
	public UserAccount createBaseAccount() {
		
//		Date date = new Date();
//		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy");
//		String birthday = DATE_FORMAT.format("2/9/2002");
		
		UserAccount baseAccount = new UserAccount("Admin", "123456", "url", "2/8/2002", "Male", "Software Engineering", "Safari");
		
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
}
