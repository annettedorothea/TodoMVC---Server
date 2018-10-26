package com.anfelisa.ace;

import org.jdbi.v3.core.Jdbi;


public class Resource {

	private Jdbi jdbi;

	public Resource( Jdbi jdbi) {
		super();
		this.jdbi = jdbi;
	}

	public DatabaseHandle createDatabaseHandle() {
		return new DatabaseHandle(jdbi.open(), jdbi.open());
	}

	public Jdbi getJdbi() {
		return jdbi;
	}
	
}

