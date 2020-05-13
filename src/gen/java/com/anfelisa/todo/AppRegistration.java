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




package com.anfelisa.todo;

import io.dropwizard.setup.Environment;
import de.acegen.CustomAppConfiguration;
import de.acegen.IDaoProvider;
import de.acegen.ViewProvider;
import de.acegen.ServerConfiguration;
import de.acegen.E2E;
import de.acegen.PersistenceConnection;

import com.anfelisa.todo.actions.*;

@SuppressWarnings("all")
public class AppRegistration {

	public static void registerResources(Environment environment, PersistenceConnection persistenceConnection, CustomAppConfiguration appConfiguration, 
			IDaoProvider daoProvider, ViewProvider viewProvider, E2E e2e) {
		environment.jersey().register(new GetAllTodosAction(persistenceConnection, appConfiguration, daoProvider, viewProvider, e2e));
		environment.jersey().register(new CreateTodoAction(persistenceConnection, appConfiguration, daoProvider, viewProvider, e2e));
		environment.jersey().register(new ToggleTodoAction(persistenceConnection, appConfiguration, daoProvider, viewProvider, e2e));
		environment.jersey().register(new ToggleAllAction(persistenceConnection, appConfiguration, daoProvider, viewProvider, e2e));
		environment.jersey().register(new UpdateTodoAction(persistenceConnection, appConfiguration, daoProvider, viewProvider, e2e));
		environment.jersey().register(new DeleteTodoAction(persistenceConnection, appConfiguration, daoProvider, viewProvider, e2e));
		environment.jersey().register(new ClearDoneAction(persistenceConnection, appConfiguration, daoProvider, viewProvider, e2e));
	}

	public static void registerConsumers(ViewProvider viewProvider, String mode) {
		viewProvider.addConsumer("com.anfelisa.todo.events.CreateTodoSuccessEvent", (dataContainer, handle) -> {
			viewProvider.todoView.create((com.anfelisa.todo.data.TodoData) dataContainer, handle);
		});
		
		viewProvider.addConsumer("com.anfelisa.todo.events.ToggleTodoSuccessEvent", (dataContainer, handle) -> {
			viewProvider.todoView.toggle((com.anfelisa.todo.data.TodoToggleData) dataContainer, handle);
		});
		
		viewProvider.addConsumer("com.anfelisa.todo.events.ToggleAllSuccessEvent", (dataContainer, handle) -> {
			viewProvider.todoView.toggleAll((com.anfelisa.todo.data.ToggleAllData) dataContainer, handle);
		});
		
		viewProvider.addConsumer("com.anfelisa.todo.events.UpdateTodoSuccessEvent", (dataContainer, handle) -> {
			viewProvider.todoView.update((com.anfelisa.todo.data.TodoData) dataContainer, handle);
		});
		
		viewProvider.addConsumer("com.anfelisa.todo.events.DeleteTodoSuccessEvent", (dataContainer, handle) -> {
			viewProvider.todoView.delete((com.anfelisa.todo.data.TodoIdData) dataContainer, handle);
		});
		
		viewProvider.addConsumer("com.anfelisa.todo.events.ClearDoneSuccessEvent", (dataContainer, handle) -> {
			viewProvider.todoView.clearDone((com.anfelisa.todo.data.ClearDoneData) dataContainer, handle);
		});
		
    }
}




/******* S.D.G. *******/



