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

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractBaseScenario {

	protected final JodaObjectMapper mapper = new JodaObjectMapper();

	protected DaoProvider daoProvider;

	protected PersistenceHandle handle;
	
	protected ObjectMapper objectMapper;
	
	public AbstractBaseScenario() {
		objectMapper = new ObjectMapper();
	}

	protected abstract String randomString();
	
	protected abstract String randomUUID();

	protected abstract String authorization(String username, String password);

	protected abstract void assertThat(int actual, int expected);

	protected abstract void assertThat(Object actual, Object expected);

	protected abstract void assertIsNull(Object actual);

	protected abstract void assertIsNotNull(Object actual);

	protected abstract void assertFail(String message);

	protected abstract String scenarioName();

}




/******* S.D.G. *******/



