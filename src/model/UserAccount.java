package model;

public class UserAccount {
	
	private String username;
	private String password;
	private String profilePic;
	private String birthday;
	private String gender;
	private String career;
	private String favoriteBrowser;
	
	public UserAccount(String username, String password, String profilePic, String birthday, String gender, String career,
			String favoriteBrowser) {
		super();
		this.username = username;
		this.password = password;
		this.profilePic = profilePic;
		this.birthday = birthday;
		this.gender = gender;
		this.career = career;
		this.favoriteBrowser = favoriteBrowser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getFavoriteBrowser() {
		return favoriteBrowser;
	}

	public void setFavoriteBrowser(String favoriteBrowser) {
		this.favoriteBrowser = favoriteBrowser;
	}
}
