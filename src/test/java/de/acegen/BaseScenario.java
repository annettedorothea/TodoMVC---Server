/* 
 * Copyright (c) 2019, Annette Pohl, Koblenz, Germany
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.

 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package de.acegen;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;

import java.io.IOException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.client.Client;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.GetAllTodosResponse;
import com.anfelisa.todo.models.ITodoItemModel;
import com.fasterxml.jackson.core.JsonProcessingException;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

@ExtendWith(DropwizardExtensionsSupport.class)
public abstract class BaseScenario extends AbstractBaseScenario {

	static Logger LOG;

	private static Jdbi jdbi;

	private static int port;

	private static String host = "localhost";

	private static String protocol;

	private static String rootPath;

	private String testId;

	public Client client;

	protected static Map<String, DescriptiveStatistics> metrics;

	@SuppressWarnings("unused")
	private static DropwizardAppExtension<CustomAppConfiguration> EXT = new DropwizardAppExtension<>(
			App.class,
			"dev.yml");

	@BeforeAll
	public static void beforeClass() throws Exception {
		ManagedDataSource ds = EXT.getConfiguration().getDataSourceFactory().build(
				EXT.getEnvironment().metrics(), "migrations");
		try (Connection connection = ds.getConnection()) {
			Liquibase migrator = new Liquibase("migrations.xml", new ClassLoaderResourceAccessor(),
					new JdbcConnection(connection));
			migrator.update("");
			migrator.close();
		}

		LOG = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		LOG.setLevel(Level.INFO);

		port = EXT.getLocalPort();
		protocol = "http";
		rootPath = "/api";
		final JdbiFactory factory = new JdbiFactory();
		jdbi = factory.build(EXT.getEnvironment(), EXT.getConfiguration().getDataSourceFactory(), "anfelisa test database");

		if (metrics == null) {
			metrics = new HashMap<>();
		}

	}

	@AfterAll
	public static void afterClass() {
		Object[] actions = metrics.keySet().toArray();
		Arrays.sort(actions);
		LOG.info(padRight("action", 25) + padLeft("times", 9) + padLeft("mean", 9) + padLeft("std dev", 9)
				+ padLeft("median", 9) + padLeft("pctl(10)", 9) + padLeft("pctl(90)", 9) + padLeft("min", 9)
				+ padLeft("max", 9));
		for (Object action : actions) {
			DescriptiveStatistics values = metrics.get(action);
			LOG.info(padRight(action.toString(), 25) + padLeft(values.getN() + "", 9)
					+ padLeft(format(values.getMean()), 9) + padLeft(format(values.getStandardDeviation()), 9)
					+ padLeft(format(values.getPercentile(50)), 9) + padLeft(format(values.getPercentile(10)), 9)
					+ padLeft(format(values.getPercentile(90)), 9) + padLeft(values.getMin(), 9)
					+ padLeft(values.getMax(), 9));
		}
	}

	private static String padLeft(double d, int n) {
		return String.format("%" + n + "s", new DecimalFormat("#").format(d));
	}

	public static String padLeft(String s, int n) {
		return String.format("%" + n + "s", s);
	}

	public static String padRight(String s, int n) {
		return String.format("%-" + n + "s", s);
	}

	private static String format(double d) {
		return new DecimalFormat("0.00").format(d);
	}

	@BeforeEach
	public void before() {
		daoProvider = new DaoProvider();
		handle = new PersistenceHandle(jdbi.open());
		testId = randomString();
		client = new JerseyClientBuilder().build();
		LOG.info("*********************************************************************************");
		LOG.info("********   {} test id {}", this.scenarioName(), testId);
		LOG.info("*********************************************************************************");
	}

	@AfterEach
	public void after() {
		handle.getHandle().close();
	}

	@Test
	public void test() throws Exception {
		this.runTest();
	}

	protected <T> HttpResponse<T> httpGet(String path, String authorization, String uuid, Class<T> entityType) {
		final HttpGet httpGet = new HttpGet(buildUrl(path, uuid));
		addHeaders(httpGet, authorization);
		return execute(httpGet, entityType);
	}

	protected <T> HttpResponse<T> httpPost(String path, Object payload, String authorization, String uuid,
			Class<T> entityType) {
		final HttpPost httpPost = new HttpPost(buildUrl(path, uuid));
		addHeaders(httpPost, authorization);
		addEntity(httpPost, payload);
		return execute(httpPost, entityType);
	}

	protected <T> HttpResponse<T> httpPut(String path, Object payload, String authorization, String uuid,
			Class<T> entityType) {
		final HttpPut httpPut = new HttpPut(buildUrl(path, uuid));
		addHeaders(httpPut, authorization);
		addEntity(httpPut, payload);
		return execute(httpPut, entityType);
	}

	protected <T> HttpResponse<T> httpDelete(String path, String authorization, String uuid, Class<T> entityType) {
		final HttpDelete httpDelete = new HttpDelete(buildUrl(path, uuid));
		addHeaders(httpDelete, authorization);
		return execute(httpDelete, entityType);
	}

	private String buildUrl(String path, String uuid) {
		if (StringUtils.isNotBlank(uuid)) {
			if (path.contains("?")) {
				path += "&uuid=" + uuid;
			} else {
				path += "?uuid=" + uuid;
			}
		}
		return String.format("%s://%s:%d%s%s", protocol, host, port, rootPath, path);
	}

	private void addHeaders(HttpUriRequest request, String authorization) {
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");
		if (authorization != null) {
			request.addHeader("Authorization", authorization);
		}
	}

	private void addEntity(HttpUriRequest request, Object payload) {
		try {
			String json = "";
			if (payload instanceof String) {
				json = payload.toString();
			} else {
				json = objectMapper.writeValueAsString(payload);
			}
			StringEntity httpEntity = new StringEntity(json);
			request.setEntity(httpEntity);
		} catch (JsonProcessingException e) {
			LOG.error("failed to write entity", e);
		}
	}

	private <T> HttpResponse<T> execute(HttpUriRequest request, Class<T> entityType) {
		try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
			long timeBeforeRequest = System.currentTimeMillis();
			final HttpClientResponseHandler<HttpResponse<T>> responseHandler = new HttpClientResponseHandler<HttpResponse<T>>() {
				@Override
				public HttpResponse<T> handleResponse(final ClassicHttpResponse response) throws IOException {
					long timeAfterRequest = System.currentTimeMillis();
					return createHttpResponse(response, entityType, timeAfterRequest - timeBeforeRequest);
				}
			};
			return httpclient.execute(request, responseHandler);
		} catch (IOException e) {
			return new HttpResponse<T>(null, e.getMessage(), -1, 0L);
		}
	}

	private <T> HttpResponse<T> createHttpResponse(ClassicHttpResponse response, Class<T> entityType, long duration) {
		int statusCode = response.getCode();
		String statusMessage = null;
		T entity = null;
		final int status = response.getCode();
		final HttpEntity httpEntity = response.getEntity();
		try {
			if (httpEntity != null) {
				if (status >= 400) {
					statusMessage = httpEntity != null ? EntityUtils.toString(httpEntity) : null;
				} else {
					String json = httpEntity != null ? EntityUtils.toString(httpEntity) : null;
					entity = objectMapper.readValue(json, entityType);
				}
			}
			response.close();
		} catch (final Exception x) {
			statusMessage = x.getMessage();
		}
		return new HttpResponse<T>(entity, statusMessage, statusCode, duration);
	}

	protected String randomString() {
		return randomUUID().replace("-", "").substring(0, 8);
	}

	@Override
	protected String randomUUID() {
		return UUID.randomUUID().toString();
	}

	@Override
	protected String authorization(String username, String password) {
		String string = username.replace("${testId}", testId) + ":" + password;
		String hash = Base64.getEncoder().encodeToString(string.getBytes());
		return "basic " + hash;
	}

	@Override
	protected void assertThat(int actual, int expected) {
		org.hamcrest.MatcherAssert.assertThat(actual, is(expected));
	}

	@Override
	protected void assertFail(String message) {
		org.junit.jupiter.api.Assertions.fail(message);
	}

	@Override
	protected void assertThat(Object actual, Object expected) {
		if (actual == null) {
			assertIsNull(expected);
		}
		if (actual instanceof GetAllTodosResponse && expected instanceof GetAllTodosResponse) {
			assertThat((GetAllTodosResponse) actual, (GetAllTodosResponse) expected);
		} else {
			org.hamcrest.MatcherAssert.assertThat(actual, is(samePropertyValuesAs(expected)));
		}
	}

	private void assertThat(GetAllTodosResponse actual, GetAllTodosResponse expected) {
		assertThat(actual.getTodoList().size(), expected.getTodoList().size());
		for (int i = 0; i < actual.getTodoList().size(); i++) {
			ITodoItemModel actualTodo = actual.getTodoList().get(i);
			ITodoItemModel expectedTodo = expected.getTodoList().get(i);
			assertThat(actualTodo, expectedTodo);
		}
	}

	@Override
	protected void assertIsNull(Object actual) {
		org.junit.jupiter.api.Assertions.assertNull(actual);
	}

	@Override
	protected void assertIsNotNull(Object actual) {
		org.junit.jupiter.api.Assertions.assertNotNull(actual);
	}

	@Override
	protected void assertTrue(boolean value) {
		org.junit.jupiter.api.Assertions.assertTrue(value);
	}

	@Override
	protected void assertFalse(boolean b) {
		org.junit.jupiter.api.Assertions.assertFalse(b);
	}

	@Override
	protected boolean prerequisite(String scenarioName) {
		if ("GetAllTodos".equals(scenarioName) && daoProvider.getTodoDao().selectAll(handle).size() != 1) {
			return false;
		}
		return true;
	}

	@Override
	protected String getTestId() {
		return testId;
	}

	protected String replaceTestId(String string) {
		return string.replace("${testId}", testId);
	}

	@Override
	protected HttpResponse<Object> callSquishyDataProviderPutValue(String uuid, String key, String value) {
		return this.httpPut("/test/squishy/value?uuid=" + uuid + "&key=" + key + "&value=" + value, null, null, null,
				Object.class);
	}

	@Override
	protected HttpResponse<Object> callSquishyDataProviderPutSystemTime(String uuid, LocalDateTime dateTime) {
		return this.httpPut("/test/squishy/system-time?uuid=" + uuid + "&system-time=" + dateTime, null, null,
				null, Object.class);
	}

	@Override
	protected void addToMetrics(String action, Long duration) {
		DescriptiveStatistics values = metrics.get(action);
		if (values == null) {
			values = new DescriptiveStatistics();
			metrics.put(action, values);
		}
		values.addValue(duration);
	}

}

/******* S.D.G. *******/
