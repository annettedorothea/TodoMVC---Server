/********************************************************************************
 * generated by de.acegen 0.9.10
 ********************************************************************************/




package com.anfelisa.todo;

import io.dropwizard.setup.Environment;
import de.acegen.PersistenceConnection;
import de.acegen.CustomAppConfiguration;
import de.acegen.IDaoProvider;
import de.acegen.ViewProvider;

import com.anfelisa.todo.resources.*;

@SuppressWarnings("all")
public class AppRegistration {

	public static void registerResources(Environment environment, PersistenceConnection persistenceConnection, CustomAppConfiguration appConfiguration, 
			IDaoProvider daoProvider, ViewProvider viewProvider) {
		environment.jersey().register(new GetAllTodosResource(persistenceConnection, appConfiguration, daoProvider, viewProvider));
		environment.jersey().register(new GetTodoResource(persistenceConnection, appConfiguration, daoProvider, viewProvider));
		environment.jersey().register(new GetOrderedTodosResource(persistenceConnection, appConfiguration, daoProvider, viewProvider));
		environment.jersey().register(new CreateTodoResource(persistenceConnection, appConfiguration, daoProvider, viewProvider));
		environment.jersey().register(new ToggleTodoResource(persistenceConnection, appConfiguration, daoProvider, viewProvider));
		environment.jersey().register(new ToggleAllResource(persistenceConnection, appConfiguration, daoProvider, viewProvider));
		environment.jersey().register(new UpdateTodoResource(persistenceConnection, appConfiguration, daoProvider, viewProvider));
		environment.jersey().register(new DeleteTodoResource(persistenceConnection, appConfiguration, daoProvider, viewProvider));
		environment.jersey().register(new ClearDoneResource(persistenceConnection, appConfiguration, daoProvider, viewProvider));
	}
	
	public static void registerConsumers(ViewProvider viewProvider) {
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



