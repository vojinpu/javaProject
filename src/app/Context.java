package app;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import enums.UserType;
import model.User;

//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Context {

	public static final String META_META_SCHEME_PATH = "/res/meta/meta_meta_scheme.json";
	public static final String CONTEXT_DIRECTORY = System.getProperty("user.home");
	public static final String CONTEXT_FILE_NAME = "context.json";
	public static final String CONTEXT_PATH = CONTEXT_DIRECTORY + "\\" + CONTEXT_FILE_NAME;

	@JsonProperty("metaPath")
	private String mMetaPath;
	@JsonProperty("mIsDefaultSet")
	private boolean mIsDefaultSet;
	@JsonProperty("users")
	private ArrayList<User> mUsers;

	public Context() {

		mIsDefaultSet = true; // Za sad rekao zeka da je zakucano
		mUsers = createDummyUsers();
	}

	public String getMetaPath() {
		return mMetaPath;
	}

	public void setMetaPath(String mMetaPath) {
		this.mMetaPath = mMetaPath;
	}

	public boolean isDeafultSet() {
		return mIsDefaultSet;
	}

	public void setDeafult(boolean value) {
		mIsDefaultSet = value;
	}

	public String toString() {
		return mIsDefaultSet + " " + mMetaPath + " " + META_META_SCHEME_PATH + " " + CONTEXT_PATH;
	}

	public ArrayList<User> getUsers() {
		return mUsers;
	}

	public void setUsers(ArrayList<User> mUsers) {
		this.mUsers = mUsers;
	}
	
	private ArrayList<User> createDummyUsers() {
		ArrayList<User> users = new ArrayList<>();
		users.add(new User("admin", "admin", UserType.ADMIN));
		users.add(new User("user", "user", UserType.USER));
		users.add(new User("a", "a", UserType.ADMIN));
		users.add(new User("u", "u", UserType.USER));
		
		return users;
	}
}
