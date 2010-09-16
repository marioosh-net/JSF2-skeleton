package model.impl;

import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import model.dao.GenericDAO;
import org.apache.log4j.Logger;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class GenericDAOImpl<T> extends HibernateDaoSupport implements GenericDAO<T> {

	protected final Class<T> persistentClass;
	protected Logger log = Logger.getLogger(getClass());

	GenericDAOImpl(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	@Override
	public void add(T obj) {
		validate(obj);
		getHibernateTemplate().save(obj);		
	}

	@Override
	public void delete(T obj) {
		if (getHibernateTemplate().contains(obj)){
			getHibernateTemplate().delete(obj);
		}else{
			getHibernateTemplate().delete(getHibernateTemplate().merge(obj));
		}
	}

	@Override
	public T get(Integer id) {
		return (T) getHibernateTemplate().get(persistentClass, id);
	}

	@Override
	public T update(T obj) {
		validate(obj);
		Object attachedObject = null;
		if (getHibernateTemplate().contains(obj)){
			getHibernateTemplate().update(obj);
			attachedObject = obj;
		}else{
			attachedObject = getHibernateTemplate().merge(obj);
		}
		return (T)attachedObject;
	}

	@Override
	public List<T> findAll() {
		return getHibernateTemplate().loadAll(persistentClass);
	}
	
	protected void validate(T obj){
		// sprawdza czy wszystkie reguly rzadzace naszym obiektem sa spelnione
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> violations = validator.validate(obj);
		for (ConstraintViolation<T> violation : violations) {
			log.warn("Pole " + violation.getPropertyPath() + ": " + violation.getMessage());				
		}
		if (violations.size() > 0){
			throw new ValidationException("Błąd walidacji!");
		}
	}

	@Override
	public List<T> find(DetachedCriteria criteria, int firstResult, int maxResults) {
		return getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
	}
	 
}
