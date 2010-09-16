package model.dao;

import java.util.List;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;

public interface GenericDAO<T> {
	public void add(T obj);
	public T update(T obj);
	public void delete(T obj);
	public T get(Integer id);
	public List<T> findAll();
	public List<T> findAll(int firstResult, int maxResults);
	public List<T> find(DetachedCriteria criteria, int firstResult, int maxResults);
}
