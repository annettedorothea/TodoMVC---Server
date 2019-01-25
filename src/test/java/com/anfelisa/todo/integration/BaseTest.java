package com.anfelisa.todo.integration;

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
import com.anfelisa.ace.CustomAppConfiguration;
import com.anfelisa.ace.DaoProvider;
import com.anfelisa.ace.ITimelineItem;
import com.anfelisa.ace.JodaObjectMapper;
import com.anfelisa.ace.TimelineItem;
import com.anfelisa.todo.data.ITodoData;
import com.anfelisa.todo.data.TodoData;
import com.anfelisa.todo.events.CreateTodoSuccessEvent;
import com.anfelisa.todo.models.ITodoModel;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.DropwizardTestSupport;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

public class BaseTest {

	protected final JodaObjectMapper mapper = new JodaObjectMapper();

	protected DaoProvider daoProvider;

	protected Handle handle;

	public static final DropwizardTestSupport<CustomAppConfiguration> SUPPORT = new DropwizardTestSupport<CustomAppConfiguration>(
			App.class, "test.yml", ConfigOverride.config("server.applicationConnectors[0].port", "0"));

	@BeforeClass
	public static void beforeClass() throws SQLException, LiquibaseException {
		SUPPORT.before();
		ManagedDataSource ds = SUPPORT.getConfiguration().getDataSourceFactory().build(SUPPORT.getEnvironment().metrics(), "migrations");
		try (Connection connection = ds.getConnection()) {
			Liquibase migrator = new Liquibase("migrations.xml", new ClassLoaderResourceAccessor(),
					new JdbcConnection(connection));
			migrator.update("");
		}
	}

	@AfterClass
	public static void afterClass() {
		SUPPORT.after();
	}

	@Before
	public void before() {
		final JdbiFactory factory = new JdbiFactory();
		Jdbi jdbi = factory.build(SUPPORT.getEnvironment(), SUPPORT.getConfiguration().getDataSourceFactory(), "todo");
		daoProvider = new DaoProvider();
		handle = jdbi.open();
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void after() {
		handle.close();
	}

	protected ITimelineItem createCreateTodoSuccessEvent(TodoData todoData) throws JsonProcessingException {
		CreateTodoSuccessEvent event = new CreateTodoSuccessEvent(todoData, null, null, null);
		String json = mapper.writeValueAsString(event.getEventData());
		System.out.println(json);
		return new TimelineItem("prepare", null, event.getEventName(), null, json, UUID.randomUUID().toString());
	}

	protected TodoData createCreateTodoData(String description, DateTime dateTime) throws JsonProcessingException {
		String uuid = UUID.randomUUID().toString();
		return new TodoData(uuid, description, false, dateTime, null, uuid);
	}

	protected Response callGetAll() {
		Client client = new JerseyClientBuilder().build();
		return client.target(
				String.format("http://localhost:%d/api/todos/all?uuid=%s", SUPPORT.getLocalPort(), UUID.randomUUID()))
				.request().get();
	}

	protected Response callCreateTodo(ITodoData todoData) {
		Client client = new JerseyClientBuilder().build();
		return client.target(String.format("http://localhost:%d/api/todos/create", SUPPORT.getLocalPort())).request()
				.post(Entity.json(todoData));
	}

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

	protected void assertEquals(ITodoModel actual, ITodoModel expected) {
		assertThat(actual.getDescription(), is(expected.getDescription()));
		// assertThat(actual.getCreatedDateTime(), is(expected.getCreatedDateTime()));
		assertThat(actual.getDone(), is(expected.getDone()));
		assertThat(actual.getId(), is(expected.getId()));
		assertThat(actual.getUpdatedDateTime(), is(expected.getUpdatedDateTime()));
	}

}

