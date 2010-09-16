package model.dao;

import java.util.List;
import model.entities.Product;
import model.entities.User;

public interface ProductDAO extends GenericDAO<Product> {
	List<Product> findByUser(User user, int firstResult, int maxResults);
}
