package com.anfelisa.todo;

import io.dropwizard.setup.Environment;
import com.anfelisa.ace.AceController;
import com.anfelisa.ace.AceExecutionMode;

import org.skife.jdbi.v2.DBI;

import com.anfelisa.todo.views.TodoView;
import com.anfelisa.todo.actions.*;

@SuppressWarnings("all")
public class AppRegistration {

	public static void registerResources(Environment environment, DBI jdbi) {
		environment.jersey().register(new GetAllTodosAction(jdbi));
		environment.jersey().register(new CreateTodoAction(jdbi));
		environment.jersey().register(new ToggleTodoAction(jdbi));
		environment.jersey().register(new ToggleAllAction(jdbi));
		environment.jersey().register(new UpdateTodoAction(jdbi));
		environment.jersey().register(new DeleteTodoAction(jdbi));
		environment.jersey().register(new ClearDoneAction(jdbi));
	}

	public static void registerConsumers() {
				AceController.addConsumer("com.anfelisa.todo.events.CreateTodoSuccessEvent", TodoView.create);
				AceController.addConsumer("com.anfelisa.todo.events.ToggleTodoSuccessEvent", TodoView.toggle);
				AceController.addConsumer("com.anfelisa.todo.events.ToggleAllSuccessEvent", TodoView.toggleAll);
				AceController.addConsumer("com.anfelisa.todo.events.UpdateTodoSuccessEvent", TodoView.update);
				AceController.addConsumer("com.anfelisa.todo.events.DeleteTodoSuccessEvent", TodoView.delete);
				AceController.addConsumer("com.anfelisa.todo.events.ClearDoneSuccessEvent", TodoView.clearDone);
    }
}

/*                    S.D.G.                    */
