package com.anfelisa.ace;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.jdbi.v3.core.Handle;
import org.joda.time.DateTime;

public abstract class AbstractBaseTest {

	protected final JodaObjectMapper mapper = new JodaObjectMapper();

	protected DaoProvider daoProvider;

	protected Handle handle;

	public static String randomUUID() {
		return UUID.randomUUID().toString();
	}

	protected void prepare(List<ITimelineItem> timeline, int port) {
		Client client = new JerseyClientBuilder().build();
		client.target(String.format("http://localhost:%d/api/test/replay-events", port))
				.request().put(Entity.json(timeline));
	}

	protected void prepare(int port) {
		List<ITimelineItem> timeline = new ArrayList<>();
		Client client = new JerseyClientBuilder().build();
		client.target(String.format("http://localhost:%d/api/test/replay-events", port))
				.request().put(Entity.json(timeline));
	}

	protected void setSystemTime(DateTime systemTime, int port) {
		Client client = new JerseyClientBuilder().build();
		client.target(String.format("http://localhost:%d/api/test/system-time", port))
				.request().put(Entity.json(systemTime.toString()));
	}

}

