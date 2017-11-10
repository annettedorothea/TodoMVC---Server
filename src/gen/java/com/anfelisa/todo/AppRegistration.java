package com.anfelisa.todo;

import io.dropwizard.setup.Environment;
import com.anfelisa.ace.AceController;
import com.anfelisa.ace.AceExecutionMode;

import org.skife.jdbi.v2.DBI;

import com.anfelisa.todo.views.TodoView;
import com.anfelisa.todo.actions.*;

@SuppressWarnings("all")
public class AppRegistration {

	public static void registerResources(Environment environment, DBI jdbi, DBI jdbiTimeline) {
		environment.jersey().register(new GetAllTodosAction(jdbi, jdbiTimeline));
		environment.jersey().register(new CreateTodoAction(jdbi, jdbiTimeline));
		environment.jersey().register(new ToggleTodoAction(jdbi, jdbiTimeline));
		environment.jersey().register(new ToggleAllAction(jdbi, jdbiTimeline));
		environment.jersey().register(new UpdateTodoAction(jdbi, jdbiTimeline));
		environment.jersey().register(new DeleteTodoAction(jdbi, jdbiTimeline));
		environment.jersey().register(new ClearDoneAction(jdbi, jdbiTimeline));
	}

	public static void registerConsumers() {
			AceController.addConsumer("com.anfelisa.todo.events.CreateTodoEvent", TodoView.create);
			AceController.addConsumer("com.anfelisa.todo.events.ToggleTodoEvent", TodoView.toggle);
			AceController.addConsumer("com.anfelisa.todo.events.ToggleAllEvent", TodoView.toggleAll);
			AceController.addConsumer("com.anfelisa.todo.events.UpdateTodoEvent", TodoView.update);
			AceController.addConsumer("com.anfelisa.todo.events.DeleteTodoEvent", TodoView.delete);
			AceController.addConsumer("com.anfelisa.todo.events.ClearDoneEvent", TodoView.clearDone);
    }
}

/*                    S.D.G.                    */
