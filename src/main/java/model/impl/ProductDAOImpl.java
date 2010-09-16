package model.impl;

import model.dao.ProductDAO;
import model.entities.Product;

public class ProductDAOImpl extends GenericDAOImpl<Product> implements ProductDAO {
	public ProductDAOImpl() {
		super(Product.class);
	}
}
