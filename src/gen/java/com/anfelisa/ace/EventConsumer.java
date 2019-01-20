package com.anfelisa.ace;

import org.jdbi.v3.core.Handle;

@FunctionalInterface
public interface EventConsumer {
	public void consumeEvent(IDataContainer data, Handle handle);
}
