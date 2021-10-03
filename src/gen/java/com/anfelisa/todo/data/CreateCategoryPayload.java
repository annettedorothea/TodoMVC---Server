/********************************************************************************
 * generated by de.acegen 1.5.5
 ********************************************************************************/




package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.List;

import de.acegen.IDataContainer;
import de.acegen.DateTimeToStringConverter;
import de.acegen.StringToDateTimeConverter;

@SuppressWarnings("all")
public class CreateCategoryPayload implements ICreateCategoryPayload {
	
	private String categoryId;
	
	public CreateCategoryPayload() {
	}
	
	public CreateCategoryPayload(com.anfelisa.todo.models.ICategoryModel data) {
		categoryId = data.getCategoryId();
	}
	
	@JsonProperty
	public String getCategoryId() {
		return this.categoryId;
	}
	
}



/******* S.D.G. *******/



