package com.anfelisa.ace;

import org.skife.jdbi.v2.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseHandle {

	static final Logger LOG = LoggerFactory.getLogger(DatabaseHandle.class);

	private Handle handle;
	private Handle errorHandle;
	private Handle timelineHandle;

	public DatabaseHandle(Handle handle, Handle errorHandle,  Handle timelineHandle) {
		super();
		try {
			if (handle != null) {
				this.handle = handle;
				this.handle.getConnection().setAutoCommit(false);
			}
			if (errorHandle != null) {
				this.errorHandle = errorHandle;
				this.errorHandle.getConnection().setAutoCommit(false);
			}
			if (timelineHandle != null) {
				this.timelineHandle = timelineHandle;
				this.timelineHandle.getConnection().setAutoCommit(false);
			}
		} catch (Exception e) {
			LOG.error("failed to set auto commit off", e);
		}
	}

	public void beginTransaction() {
		if (handle != null) {
			handle.begin();
		}
		if (errorHandle != null) {
			errorHandle.begin();
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
		if (errorHandle != null) {
			errorHandle.rollback();
		}
	}

	public void rollbackTransaction() {
		if (handle != null) {
			handle.rollback();
		}
		if (errorHandle != null) {
			errorHandle.commit();
		}
		if (timelineHandle != null) {
			timelineHandle.commit();
		}
	}

	public void close() {
		if (handle != null) {
			handle.close();
		}
		if (errorHandle != null) {
			errorHandle.close();
		}
		if (timelineHandle != null) {
			timelineHandle.close();
		}
	}

	public Handle getHandle() {
		return handle;
	}

	public Handle getErrorHandle() {
		return errorHandle;
	}

	public Handle getTimelineHandle() {
		return timelineHandle;
	}

}

