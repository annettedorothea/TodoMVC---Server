/********************************************************************************
 * generated by de.acegen 1.5.5
 ********************************************************************************/




package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import com.anfelisa.todo.models.IClearDoneModel;

import de.acegen.AbstractData;
import de.acegen.IDataContainer;
import de.acegen.DateTimeToStringConverter;
import de.acegen.StringToDateTimeConverter;

@SuppressWarnings("unused")
public abstract class AbstractClearDoneData extends AbstractData implements IClearDoneData {
	
	static final Logger LOG = LoggerFactory.getLogger(AbstractClearDoneData.class);
	
	private String categoryId;
	

	public AbstractClearDoneData(
		@JsonProperty("categoryId") String categoryId
,		@JsonProperty("uuid") String uuid
	) {
		super(uuid);
		this.categoryId = categoryId;
	}

	public AbstractClearDoneData( String uuid ) {
		super(uuid);
	}

	@JsonProperty
	public String getCategoryId() {
		return this.categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public IClearDoneData withCategoryId(String categoryId) {
		this.categoryId = categoryId;
		return this;
	}
	
	
	
	public IClearDoneData deepCopy() {
		IClearDoneData copy = new ClearDoneData(this.getUuid());
		copy.setCategoryId(this.getCategoryId());
		copy.setSystemTime(this.getSystemTime());
		return copy;
	}

}



/******* S.D.G. *******/



