package model.impl;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.dao.support.DataAccessUtils;
import model.dao.ProductDAO;
import model.entities.Product;
import model.entities.User;

public class ProductDAOImpl extends GenericDAOImpl<Product> implements ProductDAO {
	public ProductDAOImpl() {
		super(Product.class);
	}

	@Override
	public List<Product> findByUser(User user, int firstResult, int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class).add(Property.forName("owner").eq(user));
		return getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
	}

}
