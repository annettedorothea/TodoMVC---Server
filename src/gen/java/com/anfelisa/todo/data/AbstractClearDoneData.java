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
public abstract class AbstractClearDoneData extends AbstractData implements IClearDoneData {
	
	static final Logger LOG = LoggerFactory.getLogger(AbstractClearDoneData.class);
	

	public AbstractClearDoneData(
		@JsonProperty("uuid") String uuid
	) {
		super(uuid);
	}


	
}

/*       S.D.G.       */
