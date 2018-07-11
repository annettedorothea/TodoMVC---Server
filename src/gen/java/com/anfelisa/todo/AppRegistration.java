package com.anfelisa.todo;

import io.dropwizard.setup.Environment;
import com.anfelisa.ace.CustomAppConfiguration;
import com.anfelisa.ace.AceExecutionMode;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.ace.ServerConfiguration;

import org.skife.jdbi.v2.DBI;

import com.anfelisa.todo.views.TodoView;
import com.anfelisa.todo.actions.*;

@SuppressWarnings("all")
public class AppRegistration {

	public void registerResources(Environment environment, DBI jdbi, CustomAppConfiguration appConfiguration, IDaoProvider daoProvider, ViewProvider viewProvider) {
		environment.jersey().register(new GetAllTodosAction(jdbi, appConfiguration, daoProvider, viewProvider));
		environment.jersey().register(new CreateTodoAction(jdbi, appConfiguration, daoProvider, viewProvider));
		environment.jersey().register(new ToggleTodoAction(jdbi, appConfiguration, daoProvider, viewProvider));
		environment.jersey().register(new ToggleAllAction(jdbi, appConfiguration, daoProvider, viewProvider));
		environment.jersey().register(new UpdateTodoAction(jdbi, appConfiguration, daoProvider, viewProvider));
		environment.jersey().register(new DeleteTodoAction(jdbi, appConfiguration, daoProvider, viewProvider));
		environment.jersey().register(new ClearDoneAction(jdbi, appConfiguration, daoProvider, viewProvider));
	}

	public void registerConsumers(ViewProvider viewProvider, String mode) {
				viewProvider.addConsumer("com.anfelisa.todo.events.CreateTodoSuccessEvent", viewProvider.todoView.create);
				viewProvider.addConsumer("com.anfelisa.todo.events.ToggleTodoSuccessEvent", viewProvider.todoView.toggle);
				viewProvider.addConsumer("com.anfelisa.todo.events.ToggleAllSuccessEvent", viewProvider.todoView.toggleAll);
				viewProvider.addConsumer("com.anfelisa.todo.events.UpdateTodoSuccessEvent", viewProvider.todoView.update);
				viewProvider.addConsumer("com.anfelisa.todo.events.DeleteTodoSuccessEvent", viewProvider.todoView.delete);
				viewProvider.addConsumer("com.anfelisa.todo.events.ClearDoneSuccessEvent", viewProvider.todoView.clearDone);
    }
}

/*                    S.D.G.                    */
