package com.anfelisa.ace;

import org.skife.jdbi.v2.DBI;

public class Resource {

	private DBI jdbi;

	public Resource( DBI jdbi) {
		super();
		this.jdbi = jdbi;
	}

	public DatabaseHandle createDatabaseHandle() {
		return new DatabaseHandle(jdbi.open(), jdbi.open(), jdbi.open());
	}

	public DBI getJdbi() {
		return jdbi;
	}
	
}

