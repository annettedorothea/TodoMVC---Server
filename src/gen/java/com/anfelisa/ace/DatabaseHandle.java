package com.anfelisa.ace;

import org.jdbi.v3.core.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseHandle {

	static final Logger LOG = LoggerFactory.getLogger(DatabaseHandle.class);

	private Handle handle;
	private Handle timelineHandle;

	public DatabaseHandle(Handle handle, Handle timelineHandle) {
		super();
		if (handle != null) {
			this.handle = handle;
		}
		if (timelineHandle != null) {
			this.timelineHandle = timelineHandle;
		}
	}

	public void beginTransaction() {
		if (handle != null) {
			handle.begin();
		}
		if (timelineHandle != null) {
			timelineHandle.begin();
		}
	}

	public void commitTransaction() {
		if (handle != null) {
			handle.commit();
		}
		if (timelineHandle != null) {
			timelineHandle.commit();
		}
	}

	public void rollbackTransaction() {
		if (handle != null) {
			handle.rollback();
		}
		if (timelineHandle != null) {
			timelineHandle.commit();
		}
	}

	public void close() {
		if (handle != null) {
			handle.close();
		}
		if (timelineHandle != null) {
			timelineHandle.close();
		}
	}

	public Handle getHandle() {
		return handle;
	}

	public Handle getTimelineHandle() {
		return timelineHandle;
	}

}

