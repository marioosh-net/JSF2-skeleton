package model.dao;

import java.util.List;

public interface GenericDAO<T> {
	public void add(T obj);
	public T update(T obj);
	public void delete(T obj);
	public T get(Integer id);
	public List<T> findAll();
}
