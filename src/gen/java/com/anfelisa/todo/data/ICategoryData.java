/********************************************************************************
 * generated by de.acegen
 ********************************************************************************/




package com.anfelisa.todo.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.acegen.IDataContainer;

import com.anfelisa.todo.models.ICategoryModel;

@JsonDeserialize(as=CategoryData.class)
public interface ICategoryData extends ICategoryModel, IDataContainer {
	
	ICategoryData withCategoryId(String categoryId);
	
	
	ICategoryData deepCopy();
}



/******* S.D.G. *******/



