/********************************************************************************
 * generated by de.acegen 1.3.0
 ********************************************************************************/




package de.acegen;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBaseScenario {

	protected final ObjectMapper mapper = new ObjectMapper();

	protected DaoProvider daoProvider;

	protected PersistenceHandle handle;
	
	protected ObjectMapper objectMapper;
	
	protected Map<String, Object> extractedValues = new HashMap<String, Object>();
	
	public AbstractBaseScenario() {
		objectMapper = new ObjectMapper();
	}

	protected abstract void runTest() throws Exception;

	protected abstract <T> HttpResponse<T> httpGet(String path, String authorization, String uuid, Class<T> entityType);
	
	protected abstract <T> HttpResponse<T> httpPost(String path, Object payload, String authorization, String uuid, Class<T> entityType);
	
	protected abstract <T> HttpResponse<T> httpPut(String path, Object payload, String authorization, String uuid, Class<T> entityType);
	
	protected abstract <T> HttpResponse<T> httpDelete(String path, String authorization, String uuid, Class<T> entityType);
	
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
	
	protected abstract HttpResponse<Object> callNonDeterministicDataProviderPutValue(
				String uuid, String key, String data);
				
	protected abstract HttpResponse<Object> callNonDeterministicDataProviderPutSystemTime(
				String uuid, LocalDateTime dateTime);
				
	protected abstract void addToMetrics(String action, Long duration);

}




/******* S.D.G. *******/



