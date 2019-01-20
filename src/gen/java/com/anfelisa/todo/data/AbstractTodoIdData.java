package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.AbstractData;
import com.anfelisa.ace.IDataContainer;

@SuppressWarnings("unused")
public abstract class AbstractTodoIdData extends AbstractData implements ITodoIdData {
	
	static final Logger LOG = LoggerFactory.getLogger(AbstractTodoIdData.class);
	
	@NotNull
	private String id;
	

	public AbstractTodoIdData(
		@JsonProperty("id") String id
,		@JsonProperty("uuid") String uuid
	) {
		super(uuid);
		this.id = id;
	}

	public AbstractTodoIdData( String uuid ) {
		super(uuid);
	}

	@JsonProperty
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}

/*       S.D.G.       */
