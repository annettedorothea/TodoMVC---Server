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

import java.io.File;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.CreateTodoResponse;
import com.anfelisa.todo.data.GetAllTodosResponse;
import com.anfelisa.todo.models.ITodoModel;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

@RunWith(JUnitPlatform.class)
public abstract class BaseScenario extends AbstractBaseScenario {

	static final Logger LOG = LoggerFactory.getLogger(BaseScenario.class);

	private static Jdbi jdbi;

	private static int port;

	private static String host = "localhost";

	private static String protocol;

	private static String rootPath;

	private String testId;

	public Client client;

	protected static Map<String, DescriptiveStatistics> metrics;

	@BeforeAll
	public static void beforeClass() throws Exception {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory())
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		YamlConfiguration config = mapper.readValue(new File("test.yml"), YamlConfiguration.class);
		port = Integer.parseInt(config.getServer().getApplicationConnectors()[0].getPort());
		protocol = config.getServer().getApplicationConnectors()[0].getType();
		rootPath = config.getServer().getRootPath();
		jdbi = Jdbi.create(config.getDatabase().getUrl());
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
					+ padLeft(format(values.getPercentile(50)), 9)
					+ padLeft(format(values.getPercentile(10)), 9)
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
		LOG.info("testId {}", testId);
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

	private String buidlUrl(String path) {
		return String.format("%s://%s:%d%s%s", protocol, host, port, rootPath, path);
	}

	protected Response httpGet(String path, String authorization) {
		Builder builder = client.target(buidlUrl(path)).request();
		if (authorization != null) {
			builder.header("Authorization", authorization);
		}
		return builder.get();
	}

	protected Response httpPost(String path, Object data, String authorization) {
		Builder builder = client.target(buidlUrl(path)).request();
		if (authorization != null) {
			builder.header("Authorization", authorization);
		}
		return builder.post(Entity.json(data));
	}

	protected Response httpPut(String path, Object data, String authorization) {
		Builder builder = client.target(buidlUrl(path)).request();
		if (authorization != null) {
			builder.header("Authorization", authorization);
		}
		return builder.put(Entity.json(data));
	}

	protected Response httpDelete(String path, String authorization) {
		Builder builder = client.target(buidlUrl(path)).request();
		if (authorization != null) {
			builder.header("Authorization", authorization);
		}
		return builder.delete();
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
			ITodoModel actualTodo = actual.getTodoList().get(i);
			ITodoModel expectedTodo = expected.getTodoList().get(i);
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
	protected Response callNotReplayableDataProviderPutValue(String uuid, String key, Object data) {
		Client client = new JerseyClientBuilder().build();
		Builder builder = client
				.target(String.format("%s://%s:%d%s/test/not-replayable/value?uuid=" + uuid + "&key=" + key, protocol,
						host, port, rootPath))
				.request();
		return builder.put(Entity.json(data));
	}

	@Override
	protected Response callNotReplayableDataProviderPutSystemTime(String uuid, LocalDateTime dateTime) {
		Client client = new JerseyClientBuilder().build();
		Builder builder = client.target(
				String.format("%s://%s:%d%s/test/not-replayable/system-time?uuid=" + uuid + "&system-time=" + dateTime,
						protocol, host, port, rootPath))
				.request();
		return builder.put(Entity.json(dateTime));
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

	protected Object extractDescription(CreateTodoResponse data) {
		return data.getDescription();
	}

	protected Object extractCreatedDateTime(CreateTodoResponse data) {
		return data.getCreatedDateTime();
	}

	protected Object extractTodoId(CreateTodoResponse data) {
		return data.getId();
	}

}

/******* S.D.G. *******/
