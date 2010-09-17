package beans;

import java.util.List;
import model.entities.Product;

public class ProductsListBean extends AbstractListBean<Product> {

	private static final long serialVersionUID = 1L;

	@Override
	public int getCountAll() {
		return getProductDAO().countAll();
	}

	@Override
	public List<Product> getElems() {
		return getProductDAO().findAll(page*getElemsPerPage(), getElemsPerPage());
	}

	@Override
	public int getElemsPerPage() {
		return 5;
	}

	@Override
	public int getStartPageNumber() {
		return 1;
	}

}
