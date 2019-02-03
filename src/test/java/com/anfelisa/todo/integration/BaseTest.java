package com.anfelisa.todo.integration;

import org.jdbi.v3.core.Jdbi;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.AbstractBaseTest;
import com.anfelisa.ace.App;
import com.anfelisa.ace.CustomAppConfiguration;
import com.anfelisa.ace.DaoProvider;

import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.testing.DropwizardTestSupport;

public class BaseTest extends AbstractBaseTest {

	static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);

	public static final DropwizardTestSupport<CustomAppConfiguration> DROPWIZARD = new DropwizardTestSupport<CustomAppConfiguration>(
			App.class, "test.yml");
	
	private static Jdbi jdbi;

	@BeforeClass
	public static void beforeClass() {
		DROPWIZARD.before();
		final JdbiFactory factory = new JdbiFactory();
		jdbi = factory.build(DROPWIZARD.getEnvironment(), DROPWIZARD.getConfiguration().getDataSourceFactory(), "testdb");
	}

	@AfterClass
	public static void afterClass() {
		try {
			DROPWIZARD.after();
		} catch (Exception x) {
			LOG.error("exception when cleaning up dropwizard", x);
		}
	}

	@Before
	public void before() {
		daoProvider = new DaoProvider();
		handle = jdbi.open();
		MockitoAnnotations.initMocks(this);
	}
	
	@After
	public void after() {
		handle.close();
	}
	
}
