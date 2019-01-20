package com.anfelisa.todo.integration;

import java.util.UUID;

import org.jdbi.v3.core.Jdbi;
import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anfelisa.ace.AppRegistration;
import com.anfelisa.ace.CustomAppConfiguration;
import com.anfelisa.ace.DaoProvider;
import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.EventFactory;
import com.anfelisa.ace.IEvent;
import com.anfelisa.ace.ServerConfiguration;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.todo.actions.GetAllTodosAction;
import com.anfelisa.todo.data.TodoData;
import com.codahale.metrics.MetricRegistry;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;

public class DatabaseTest {

	private static Environment env;
	private static Jdbi jdbi;

	@BeforeClass
	public static void setup() {
		env = new Environment("test-env", Jackson.newObjectMapper(), null, new MetricRegistry(), null);
		final JdbiFactory factory = new JdbiFactory();

		jdbi = factory.build(env, getDataSourceFactory(), "test");

	}

	static DataSourceFactory getDataSourceFactory() {
		DataSourceFactory dataSourceFactory = new DataSourceFactory();
		dataSourceFactory.setDriverClass("org.postgresql.Driver");
		dataSourceFactory.setUrl("jdbc:postgresql://localhost/todo_test");
		dataSourceFactory.setUser("annette");
		dataSourceFactory.setPassword("");
		return dataSourceFactory;
	}

	public static Environment getEnvironment() {
		return env;
	}
	
	@Test
	public void test() throws Exception {
		DatabaseHandle databaseHandle = new DatabaseHandle(jdbi.open(), null);
		DaoProvider daoProvider = new DaoProvider();
		
		ViewProvider viewProvider = new ViewProvider(daoProvider);

		AppRegistration.registerConsumers(viewProvider, ServerConfiguration.REPLAY);
		
		daoProvider.truncateAllViews(databaseHandle.getHandle());
		
		UUID uuid = UUID.randomUUID();
		TodoData todoData = new TodoData("id", "description", false, new DateTime(), null, uuid.toString());
		IEvent event = EventFactory.createEvent("com.anfelisa.todo.events.CreateTodoSuccessEvent", todoData, databaseHandle,
				daoProvider, viewProvider);
		event.notifyListeners();
		
		CustomAppConfiguration appConfiguration = new CustomAppConfiguration();
		ServerConfiguration serverConfiguration = new ServerConfiguration();
		serverConfiguration.setMode(ServerConfiguration.LIVE);
		appConfiguration.setServerConfiguration(serverConfiguration);
		GetAllTodosAction action = new GetAllTodosAction(jdbi, appConfiguration, daoProvider, viewProvider);


		UUID uuid2 = UUID.randomUUID();
		action.getAllTodosResource(uuid2.toString());
	}


}
