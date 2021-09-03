package model;

import java.util.ArrayList;
import java.util.List;

public class ClassroomManager {
	
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
}
