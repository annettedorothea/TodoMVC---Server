package com.anfelisa.ace;

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
public abstract class AbstractBaseTest {

	protected final JodaObjectMapper mapper = new JodaObjectMapper();

	protected DaoProvider daoProvider;

	protected Handle handle;

	public static final DropwizardTestSupport<CustomAppConfiguration> SUPPORT = new DropwizardTestSupport<CustomAppConfiguration>(
			App.class, "test.yml", ConfigOverride.config("server.applicationConnectors[0].port", "0"));

	@BeforeClass
	public static void beforeClass() throws SQLException, LiquibaseException {
		SUPPORT.before();
	}

	@AfterClass
	public static void afterClass() {
		SUPPORT.after();
	}

	@Before
	public void before() {
		final JdbiFactory factory = new JdbiFactory();
		Jdbi jdbi = factory.build(SUPPORT.getEnvironment(), SUPPORT.getConfiguration().getDataSourceFactory(), "testdb");
		daoProvider = new DaoProvider();
		handle = jdbi.open();
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void after() {
		handle.close();
	}

	protected void prepare(List<ITimelineItem> timeline) {
		Client client = new JerseyClientBuilder().build();
		client.target(String.format("http://localhost:%d/api/test/replay-events", SUPPORT.getLocalPort()))
				.request().put(Entity.json(timeline));
	}

	protected void prepare() {
		List<ITimelineItem> timeline = new ArrayList<>();
		Client client = new JerseyClientBuilder().build();
		client.target(String.format("http://localhost:%d/api/test/replay-events", SUPPORT.getLocalPort()))
				.request().put(Entity.json(timeline));
	}

	protected void setSystemTime(DateTime systemTime) {
		Client client = new JerseyClientBuilder().build();
		client.target(String.format("http://localhost:%d/api/test/system-time", SUPPORT.getLocalPort()))
				.request().put(Entity.json(systemTime.toString()));
	}

	protected String getAuthenticationHeader() {
		return "";
	}
	
}

