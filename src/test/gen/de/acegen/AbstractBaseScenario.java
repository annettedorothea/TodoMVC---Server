/* 
 * Copyright (c) 2020, Annette Pohl, Koblenz, Germany
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
 *
 * generated with de.acegen 0.9.6
 *
 */




package de.acegen;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

public abstract class AbstractBaseScenario {

	protected final ObjectMapper mapper = new ObjectMapper();

	protected DaoProvider daoProvider;

	protected PersistenceHandle handle;
	
	protected ObjectMapper objectMapper;
	
	public AbstractBaseScenario() {
		objectMapper = new ObjectMapper();
	}

	protected abstract void runTest() throws Exception;

	protected abstract Response httpGet(String path, String authorization);
	
	protected abstract Response httpPost(String path, Object data, String authorization);
	
	protected abstract Response httpPut(String path, Object data, String authorization);
	
	protected abstract Response httpDelete(String path, String authorization);
	
	protected abstract String randomString();
	
	protected abstract String randomUUID();

	protected abstract String authorization(String username, String password);

	protected abstract void assertThat(int actual, int expected);

	protected abstract void assertThat(Object actual, Object expected);

	protected abstract void assertIsNull(Object actual);

	protected abstract void assertIsNotNull(Object actual);

	protected abstract void assertFail(String message);
	
	protected abstract void assertTrue(boolean b);

	protected abstract void assertFalse(boolean b);

	protected abstract String scenarioName();
	
	protected abstract String getTestId();

	protected abstract boolean prerequisite(String scenarioName);
	
	protected abstract Response callNotReplayableDataProviderPutValue(
				String uuid, String key, Object data);
				
	protected abstract Response callNotReplayableDataProviderPutSystemTime(
				String uuid, LocalDateTime dateTime);
				
	protected abstract void addToMetrics(String action, Long duration);

}




/******* S.D.G. *******/



