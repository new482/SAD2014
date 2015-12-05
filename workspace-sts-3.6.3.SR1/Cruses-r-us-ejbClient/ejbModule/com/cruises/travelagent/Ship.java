package com.cruises.travelagent;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity @Table(name="Ship")
public class Ship implements java.io.Serializable {
	
	@Id @GeneratedValue
    private int id;
    private String name;
    @OneToMany(cascade={CascadeType.ALL})
    private Collection<Cabin> cabins = new ArrayList<Cabin>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collection<Cabin> getCabins() {
		return cabins;
	}
	public void setCabins(Collection<Cabin> cabins) {
		this.cabins = cabins;
	}
    
}
