package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.joda.time.DateTime;
import java.util.List;

import com.anfelisa.ace.AbstractData;
import com.anfelisa.ace.IDataContainer;

public class PublicData extends AbstractPublicData implements IPublicData {
	
	public PublicData(
		@JsonProperty("uuid") String uuid
	) {
		super(
			uuid
		);
	}



	public void migrateLegacyData(String json) {
	}

	public void overwriteNotReplayableData(IDataContainer dataContainer) {
		/*if (dataContainer != null) {
			try {
				IPublicData original = (IPublicData)dataContainer;
				//overwrite values
			} catch (ClassCastException x) {
				LOG.error("cannot cast data to IPublicData for overwriting not replayable attributes", x);
			}
		}*/
	}

}

/*       S.D.G.       */
