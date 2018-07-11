package com.anfelisa.ace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.skife.jdbi.v2.Handle;

import com.anfelisa.todo.views.TodoView;

public class ViewProvider {

	private final Map<String, List<BiConsumer<? extends IDataContainer, Handle>>> consumerMap;

	public TodoView todoView;

	public ViewProvider(IDaoProvider daoProvider) {
		todoView = new TodoView(daoProvider);
		
		consumerMap = new HashMap<String, List<BiConsumer<? extends IDataContainer, Handle>>>();
	}
	
	public void addConsumer(String eventName, BiConsumer<? extends IDataContainer, Handle> createUserTable) {
		List<BiConsumer<? extends IDataContainer, Handle>> consumerForEvent = consumerMap.get(eventName);
		if (consumerForEvent == null) {
			consumerForEvent = new ArrayList<BiConsumer<? extends IDataContainer, Handle>>();
			consumerMap.put(eventName, consumerForEvent);
		}
		consumerForEvent.add(createUserTable);
	}

	public List<BiConsumer<? extends IDataContainer, Handle>> getConsumerForEvent(String eventName) {
		return consumerMap.get(eventName);
	}

}

