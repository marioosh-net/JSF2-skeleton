package model.dao;

import model.entities.User;

public interface UserDAO extends GenericDAO<User> {
	public User getUserByLogin(String login);
}
