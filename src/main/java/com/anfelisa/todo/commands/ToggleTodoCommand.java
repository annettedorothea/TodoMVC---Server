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




package com.anfelisa.todo.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.ITodoToggleData;
import com.anfelisa.todo.models.ITodoModel;

import de.acegen.CustomAppConfiguration;
import de.acegen.IDaoProvider;
import de.acegen.PersistenceHandle;
import de.acegen.ViewProvider;

public class ToggleTodoCommand extends AbstractToggleTodoCommand {

	static final Logger LOG = LoggerFactory.getLogger(ToggleTodoCommand.class);

	public ToggleTodoCommand(ITodoToggleData commandData, IDaoProvider daoProvider, ViewProvider viewProvider, 
			CustomAppConfiguration appConfiguration) {
		super(commandData, daoProvider, viewProvider, appConfiguration);
	}

	@Override
	protected void executeCommand(PersistenceHandle readonlyHandle) {
		this.commandData.setUpdatedDateTime(this.commandData.getSystemTime());
		ITodoModel todo = daoProvider.getTodoDao().selectById(readonlyHandle, commandData.getId());
		this.commandData.setTodoToBeToggled(todo);
		this.commandData.setDone(!todo.getDone());
		this.commandData.setOutcome(success);
	}

}




/******* S.D.G. *******/



