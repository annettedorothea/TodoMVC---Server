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
 * generated with de.acegen 0.9.2
 *
 */




package de.acegen;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Command<T extends IDataContainer> implements ICommand {

	protected T commandData;
	private String commandName;
	protected ObjectMapper mapper;
	protected IDaoProvider daoProvider;
	protected ViewProvider viewProvider;
	protected CustomAppConfiguration appConfiguration;

	public Command(String commandName, T commandData, IDaoProvider daoProvider, ViewProvider viewProvider, CustomAppConfiguration appConfiguration) {
		super();
		this.commandData = commandData;
		this.commandName = commandName;
		mapper = new ObjectMapper();
		this.daoProvider = daoProvider;
		this.viewProvider = viewProvider;
		this.appConfiguration = appConfiguration;
	}

	protected abstract void executeCommand(PersistenceHandle readonlyHandle);

	public void execute(PersistenceHandle readonlyHandle, PersistenceHandle timelineHandle) {
		this.executeCommand(readonlyHandle);
		if (appConfiguration.getConfig().writeTimeline()) {
			daoProvider.getAceDao().addCommandToTimeline(this, timelineHandle);
		}
	}

	public IDataContainer getCommandData() {
		return commandData;
	}
	
	@SuppressWarnings("unchecked")
	public void setCommandData(IDataContainer data) {
		commandData = (T)data;
	}

	public String getCommandName() {
		return commandName;
	}

	protected void throwSecurityException() {
		throw new SecurityException();
	}

	protected void throwIllegalArgumentException(String error) {
		throw new IllegalArgumentException(error);
	}

}		



/******* S.D.G. *******/



