package com.assignment.string_manipulation.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StringManipulationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String jsonModel;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJsonModel() {
		return jsonModel;
	}
	public void setJsonModel(String jsonModel) {
		this.jsonModel = jsonModel;
	}
	
}
