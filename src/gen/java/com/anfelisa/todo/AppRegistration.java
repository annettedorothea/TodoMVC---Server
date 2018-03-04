package com.anfelisa.todo;

import io.dropwizard.setup.Environment;
import com.anfelisa.ace.AceController;
import com.anfelisa.ace.AceExecutionMode;

import org.skife.jdbi.v2.DBI;

import com.anfelisa.todo.views.TodoView;
import com.anfelisa.todo.views.TodoHistoryView;
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
		AceController.addConsumer("com.anfelisa.todo.events.CreateTodoEvent", TodoView.create);

		if (AceController.getAceExecutionMode() == AceExecutionMode.LIVE || AceController.getAceExecutionMode() == AceExecutionMode.DEV) {
			AceController.addConsumer("com.anfelisa.todo.events.CreateTodoEvent", TodoHistoryView.create);
		}
		
		AceController.addConsumer("com.anfelisa.todo.events.ToggleTodoEvent", TodoView.toggle);

		if (AceController.getAceExecutionMode() == AceExecutionMode.LIVE || AceController.getAceExecutionMode() == AceExecutionMode.DEV) {
			AceController.addConsumer("com.anfelisa.todo.events.ToggleTodoEvent", TodoHistoryView.toggle);
		}
		
		AceController.addConsumer("com.anfelisa.todo.events.ToggleAllEvent", TodoView.toggleAll);

		if (AceController.getAceExecutionMode() == AceExecutionMode.LIVE || AceController.getAceExecutionMode() == AceExecutionMode.DEV) {
			AceController.addConsumer("com.anfelisa.todo.events.ToggleAllEvent", TodoHistoryView.toggleAll);
		}
		
		AceController.addConsumer("com.anfelisa.todo.events.UpdateTodoEvent", TodoView.update);

		if (AceController.getAceExecutionMode() == AceExecutionMode.LIVE || AceController.getAceExecutionMode() == AceExecutionMode.DEV) {
			AceController.addConsumer("com.anfelisa.todo.events.UpdateTodoEvent", TodoHistoryView.update);
		}
		
		if (AceController.getAceExecutionMode() == AceExecutionMode.LIVE || AceController.getAceExecutionMode() == AceExecutionMode.DEV) {
			AceController.addConsumer("com.anfelisa.todo.events.DeleteTodoEvent", TodoHistoryView.delete);
		}
		
		AceController.addConsumer("com.anfelisa.todo.events.DeleteTodoEvent", TodoView.delete);

		if (AceController.getAceExecutionMode() == AceExecutionMode.LIVE || AceController.getAceExecutionMode() == AceExecutionMode.DEV) {
			AceController.addConsumer("com.anfelisa.todo.events.ClearDoneEvent", TodoHistoryView.clearDone);
		}
		
		AceController.addConsumer("com.anfelisa.todo.events.ClearDoneEvent", TodoView.clearDone);

    }
}

/*                    S.D.G.                    */
