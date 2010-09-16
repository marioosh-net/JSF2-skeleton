package model.entities;

import java.io.Serializable;
import java.util.logging.Logger;

/*
 * equals i hashCode() dla Hibernate
 */
public abstract class AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	transient protected final Logger log = Logger.getLogger(getClass().getName());

	public abstract Integer getId();
	
	@Override
	public boolean equals(Object obj) {
		if (this==obj) return true;
		if (obj == null) return false;
		if (obj.getClass() != getClass()) return false;
		Object objId = ((AbstractEntity)obj).getId();
		if ((getId() == null) || (objId == null)) return false;
		return getId().equals(objId);
	}

	@Override
	public int hashCode() {
		return (getId() == null) ? System.identityHashCode(this) : getId().hashCode();
	}
}
