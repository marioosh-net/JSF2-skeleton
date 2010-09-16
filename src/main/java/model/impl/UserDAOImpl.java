package model.impl;

import model.dao.UserDAO;
import model.entities.User;

public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

	public UserDAOImpl() {
		super(User.class);
	}
	
	@Override
	public User getUserByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

}
