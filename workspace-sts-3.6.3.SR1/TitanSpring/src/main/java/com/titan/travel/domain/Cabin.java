package com.titan.travel.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cabin implements Serializable
{
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue
   private long id;
   private String name;
   private int deckLevel;
   private int shipId;
   private int bedCount;

   public boolean equals(Object object)
   {
      if (object instanceof Cabin)
      {
         Cabin other = (Cabin) object;
         return this.shipId == other.shipId
               && this.deckLevel == other.deckLevel
               && this.name.equals(other.name);
      }
      return false;
   }


public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getDeckLevel() {
	return deckLevel;
}

public void setDeckLevel(int deckLevel) {
	this.deckLevel = deckLevel;
}

public int getShipId() {
	return shipId;
}

public void setShipId(int shipId) {
	this.shipId = shipId;
}

public int getBedCount() {
	return bedCount;
}

public void setBedCount(int bedCount) {
	this.bedCount = bedCount;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

public void setId(long id) {
	this.id = id;
}

public long getId() {
	return id;
}
}