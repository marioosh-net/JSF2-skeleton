package model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
@SequenceGenerator(name = "seq_product", sequenceName = "seq_product")
public class Product extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_product")
	private Integer id;

	@ManyToOne
	@NotNull
	private User owner;

	private String name;

	public Product() {
		super();
	}

	public Product(String name, User owner) {
		this.name = name;
		this.owner = owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getOwner() {
		return owner;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
