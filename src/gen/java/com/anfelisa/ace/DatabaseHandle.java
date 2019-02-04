package com.anfelisa.ace;

import org.jdbi.v3.core.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jdbi.v3.core.Jdbi;

public class DatabaseHandle {

	static final Logger LOG = LoggerFactory.getLogger(DatabaseHandle.class);

	private Handle handle;
	private Handle readonlyHandle;
	private Handle timelineHandle;

	public DatabaseHandle(Jdbi jdbi) {
		super();
		this.handle = jdbi.open();
		this.readonlyHandle = jdbi.open();
		this.timelineHandle = jdbi.open();
	}

	synchronized public void beginTransaction() {
		handle.begin();
		readonlyHandle.begin();
		timelineHandle.begin();
	}

	synchronized public void commitTransaction() {
		handle.commit();
		readonlyHandle.rollback();
		timelineHandle.commit();
	}

	synchronized public void rollbackTransaction() {
		handle.rollback();
		readonlyHandle.rollback();
		timelineHandle.commit();
	}

	synchronized public void close() {
		handle.close();
		readonlyHandle.close();
		timelineHandle.close();
	}

	public Handle getHandle() {
		return handle;
	}

	public Handle getReadonlyHandle() {
		return readonlyHandle;
	}

	public Handle getTimelineHandle() {
		return timelineHandle;
	}

}

