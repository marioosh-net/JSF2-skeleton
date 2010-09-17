package beans;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractListBean<Elem> extends BaseBean {

	private static final long serialVersionUID = 1L;
	protected int page;
	
	/** lista elementow **/
	abstract public List<Elem> getElems();
	
	/** wszystkich elementow **/
	abstract public int getCountAll();
	
	/** elementow na strone **/
	abstract public int getElemsPerPage();
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPagesCount() {
		int c = getCountAll();
		return (c > 0 ? (c % getElemsPerPage() == 0 ? c / getElemsPerPage() : (c / getElemsPerPage()) + 1) : 0);
	}
	
	public void nextPage() {
		setPage(getPage() + 1 < getPagesCount() ? getPage() + 1 : getPage());
	}

	public void prevPage() {
		setPage(getPage() > 0 ? getPage() - 1 : 0);
	}
	
	public List<Integer> getNaviPages() {
		List<Integer> l = new ArrayList<Integer>();
		getLog().debug("getNaviPages()");

		// numerowanie od 1
		int offset = 1;
		int pageCount = getPagesCount();
		for(int i = offset, j = 0; i < pageCount + offset; i++) {
			// pierwsza ostatnia, 3 w przod, 3 w tyl
			if(/*i != getPage() &&*/ (i == offset || i == pageCount + offset - 1 || (i - getPage() <= 3 && i - getPage() >= 0) || (getPage() - i <= 3 && getPage() - i >= 0))) {
				if(!(j - i == 1 || i - j == 1)) {
					// -1 to bedzie 3 kropki
					l.add(new Integer(-1));
					
				}				
				l.add(new Integer(i));
				j = i;
			}
		}
		getLog().debug("NAVI: "+l);
		return l;		
	}

}
