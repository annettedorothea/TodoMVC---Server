package com.anfelisa.ace;

import org.skife.jdbi.v2.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseHandle {

	static final Logger LOG = LoggerFactory.getLogger(DatabaseHandle.class);

	private Handle handle;
	private Handle errorHandle;

	public DatabaseHandle(Handle handle, Handle errorHandle) {
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
	}

	public void commitTransaction() {
		if (handle != null) {
			handle.commit();
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
	}

	public void close() {
		if (handle != null) {
			handle.close();
		}
		if (errorHandle != null) {
			errorHandle.close();
		}
	}

	public Handle getHandle() {
		return handle;
	}

	public Handle getErrorHandle() {
		return errorHandle;
	}

}

