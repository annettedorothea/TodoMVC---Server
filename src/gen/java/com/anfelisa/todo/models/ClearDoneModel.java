/********************************************************************************
 * generated by de.acegen
 ********************************************************************************/




package com.anfelisa.todo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;
import java.util.ArrayList;

import de.acegen.DateTimeToStringConverter;
import de.acegen.StringToDateTimeConverter;

@SuppressWarnings("all")
public class ClearDoneModel implements IClearDoneModel {

	private String categoryId;


	public ClearDoneModel() {
	}

	public ClearDoneModel(
		@JsonProperty("categoryId") String categoryId
	) {
		this.categoryId = categoryId;
	}

	@JsonProperty
	public String getCategoryId() {
		return this.categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	

	public IClearDoneModel deepCopy() {
		IClearDoneModel copy = new ClearDoneModel();
		copy.setCategoryId(this.getCategoryId());
		return copy;
	}

}



/******* S.D.G. *******/



