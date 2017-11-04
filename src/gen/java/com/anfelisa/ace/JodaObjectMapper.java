package com.anfelisa.ace;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class JodaObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 3204337293769989499L;

	public JodaObjectMapper() {
        super();
        registerModule(new JodaModule());
    }
}
