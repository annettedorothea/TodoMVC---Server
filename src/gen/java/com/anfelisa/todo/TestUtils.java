package com.anfelisa.todo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Invocation.Builder;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.MockitoAnnotations;

import com.anfelisa.ace.App;
import com.anfelisa.ace.AbstractBaseTest;
import com.anfelisa.ace.CustomAppConfiguration;
import com.anfelisa.ace.DaoProvider;
import com.anfelisa.ace.ITimelineItem;
import com.anfelisa.ace.JodaObjectMapper;
import com.anfelisa.ace.TimelineItem;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.DropwizardTestSupport;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

@SuppressWarnings("unused")
public class TestUtils {

	private static final JodaObjectMapper mapper = new JodaObjectMapper();

	public static String randomUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static Response callGetAllTodos(
			String uuid
	) {
		Client client = new JerseyClientBuilder().build();
		Builder builder = client.target(String.format("http://localhost:%d/api/todos/all?uuid=" + uuid, AbstractBaseTest.SUPPORT.getLocalPort())).request(); 
		return builder.get();
	}
	
	public static Response callCreateTodo(com.anfelisa.todo.data.ITodoData data) {
		Client client = new JerseyClientBuilder().build();
		Builder builder = client.target(String.format("http://localhost:%d/api/todos/create", AbstractBaseTest.SUPPORT.getLocalPort())).request(); 
		return builder.post(Entity.json(data));
	}
	
	public static TimelineItem createCreateTodoSuccessEventTimelineItem(com.anfelisa.todo.data.ITodoData data) throws JsonProcessingException {
		com.anfelisa.todo.events.CreateTodoSuccessEvent event =  new com.anfelisa.todo.events.CreateTodoSuccessEvent(data, null, null, null);
		String json = mapper.writeValueAsString(event.getEventData());
		return new TimelineItem("prepare", null, event.getEventName(), null, json, randomUUID());
	}
	public static Response callToggleTodo(
			String uuid, 
			String id
		) {
		Client client = new JerseyClientBuilder().build();
		Builder builder = client.target(String.format("http://localhost:%d/api/todos/toggle?uuid=" + uuid + "&id=" + id, AbstractBaseTest.SUPPORT.getLocalPort())).request();
		return builder.put(Entity.json(null));
	}
	
	public static TimelineItem createToggleTodoSuccessEventTimelineItem(com.anfelisa.todo.data.ITodoToggleData data) throws JsonProcessingException {
		com.anfelisa.todo.events.ToggleTodoSuccessEvent event =  new com.anfelisa.todo.events.ToggleTodoSuccessEvent(data, null, null, null);
		String json = mapper.writeValueAsString(event.getEventData());
		return new TimelineItem("prepare", null, event.getEventName(), null, json, randomUUID());
	}
	public static Response callToggleAll(
			String uuid
		) {
		Client client = new JerseyClientBuilder().build();
		Builder builder = client.target(String.format("http://localhost:%d/api/todos/toggle-all?uuid=" + uuid, AbstractBaseTest.SUPPORT.getLocalPort())).request();
		return builder.put(Entity.json(null));
	}
	
	public static TimelineItem createToggleAllSuccessEventTimelineItem(com.anfelisa.todo.data.IToggleAllData data) throws JsonProcessingException {
		com.anfelisa.todo.events.ToggleAllSuccessEvent event =  new com.anfelisa.todo.events.ToggleAllSuccessEvent(data, null, null, null);
		String json = mapper.writeValueAsString(event.getEventData());
		return new TimelineItem("prepare", null, event.getEventName(), null, json, randomUUID());
	}
	public static Response callUpdateTodo(
			com.anfelisa.todo.data.ITodoData data, 
			String uuid
		) {
		Client client = new JerseyClientBuilder().build();
		Builder builder = client.target(String.format("http://localhost:%d/api/todos/update?uuid=" + uuid, AbstractBaseTest.SUPPORT.getLocalPort())).request();
		return builder.put(Entity.json(data));
	}
	
	public static TimelineItem createUpdateTodoSuccessEventTimelineItem(com.anfelisa.todo.data.ITodoData data) throws JsonProcessingException {
		com.anfelisa.todo.events.UpdateTodoSuccessEvent event =  new com.anfelisa.todo.events.UpdateTodoSuccessEvent(data, null, null, null);
		String json = mapper.writeValueAsString(event.getEventData());
		return new TimelineItem("prepare", null, event.getEventName(), null, json, randomUUID());
	}
	public static Response callDeleteTodo(
			String uuid, 
			String id
	) {
		Client client = new JerseyClientBuilder().build();
		Builder builder = client.target(String.format("http://localhost:%d/api/todos/delete?uuid=" + uuid + "&id=" + id, AbstractBaseTest.SUPPORT.getLocalPort())).request();
		return builder.delete();
	}
	
	public static TimelineItem createDeleteTodoSuccessEventTimelineItem(com.anfelisa.todo.data.ITodoIdData data) throws JsonProcessingException {
		com.anfelisa.todo.events.DeleteTodoSuccessEvent event =  new com.anfelisa.todo.events.DeleteTodoSuccessEvent(data, null, null, null);
		String json = mapper.writeValueAsString(event.getEventData());
		return new TimelineItem("prepare", null, event.getEventName(), null, json, randomUUID());
	}
	public static Response callClearDone(
			String uuid
	) {
		Client client = new JerseyClientBuilder().build();
		Builder builder = client.target(String.format("http://localhost:%d/api/todos/clear-done?uuid=" + uuid, AbstractBaseTest.SUPPORT.getLocalPort())).request();
		return builder.delete();
	}
	
	public static TimelineItem createClearDoneSuccessEventTimelineItem(com.anfelisa.todo.data.IClearDoneData data) throws JsonProcessingException {
		com.anfelisa.todo.events.ClearDoneSuccessEvent event =  new com.anfelisa.todo.events.ClearDoneSuccessEvent(data, null, null, null);
		String json = mapper.writeValueAsString(event.getEventData());
		return new TimelineItem("prepare", null, event.getEventName(), null, json, randomUUID());
	}
	
	public static void assertEquals(com.anfelisa.todo.models.ITodoDoneHistoryModel actual, com.anfelisa.todo.models.ITodoDoneHistoryModel expected) {
		assertThat(actual.getId(), is(expected.getId()));
		assertThat(actual.getDescription(), is(expected.getDescription()));
		assertThat(actual.getDoneDateTime(), is(expected.getDoneDateTime()));
	}
	public static void assertEquals(com.anfelisa.todo.models.ITodoModel actual, com.anfelisa.todo.models.ITodoModel expected) {
		assertThat(actual.getId(), is(expected.getId()));
		assertThat(actual.getDescription(), is(expected.getDescription()));
		assertThat(actual.getDone(), is(expected.getDone()));
		assertThat(actual.getCreatedDateTime(), is(expected.getCreatedDateTime()));
		assertThat(actual.getUpdatedDateTime(), is(expected.getUpdatedDateTime()));
	}
	public static void assertEquals(com.anfelisa.todo.models.ITodoIdModel actual, com.anfelisa.todo.models.ITodoIdModel expected) {
		assertThat(actual.getId(), is(expected.getId()));
	}
	public static void assertEquals(com.anfelisa.todo.models.ITodoToggleModel actual, com.anfelisa.todo.models.ITodoToggleModel expected) {
		assertThat(actual.getId(), is(expected.getId()));
		assertThat(actual.getDone(), is(expected.getDone()));
		assertThat(actual.getUpdatedDateTime(), is(expected.getUpdatedDateTime()));
	}
	public static void assertEquals(com.anfelisa.todo.models.IToggleAllModel actual, com.anfelisa.todo.models.IToggleAllModel expected) {
		assertThat(actual.getDone(), is(expected.getDone()));
		assertThat(actual.getUpdatedDateTime(), is(expected.getUpdatedDateTime()));
	}

}

