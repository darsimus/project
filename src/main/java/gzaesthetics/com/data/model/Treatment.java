package gzaesthetics.com.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// @Entity is picked up by Hibernate, which will automatically map objects to and from
// json data for us
@Entity // Specifies that this is a persistable type, i.e. in a database
public class Treatment {
	
	

	@Id // required by any class marked with @Entity
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id; // this is the primary key in a MySql DB
	private String name;
	private String description;
	private double price;
	private int duration;
	
	
	public Treatment() {
		
	}


	public Treatment(Integer id, String name, String description, double price, int duration) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.duration = duration;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	

	
	
}

