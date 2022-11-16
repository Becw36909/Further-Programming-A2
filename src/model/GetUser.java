package model;

public interface GetUser extends DatabaseGetConnection{
	

	void getUser(String username, String password);

}
