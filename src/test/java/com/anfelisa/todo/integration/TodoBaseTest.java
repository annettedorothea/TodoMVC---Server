package com.anfelisa.todo.integration;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClientBuilder;

import com.anfelisa.ace.ITimelineItem;
import com.anfelisa.ace.JodaObjectMapper;
import com.anfelisa.todo.BaseTest;

public class TodoBaseTest extends BaseTest {

	protected final JodaObjectMapper mapper = new JodaObjectMapper();

	protected void prepare(List<ITimelineItem> timeline) {
		Client client = new JerseyClientBuilder().build();
		Response response = client.target(String.format("http://localhost:%d/api/test/prepare", SUPPORT.getLocalPort()))
				.request().put(Entity.json(timeline));
		int status = response.getStatus();
		System.out.println(status);
	}

	protected void prepare() {
		List<ITimelineItem> timeline = new ArrayList<>();
		Client client = new JerseyClientBuilder().build();
		Response response = client.target(String.format("http://localhost:%d/api/test/prepare", SUPPORT.getLocalPort()))
				.request().put(Entity.json(timeline));
		int status = response.getStatus();
		System.out.println(status);
	}

}
