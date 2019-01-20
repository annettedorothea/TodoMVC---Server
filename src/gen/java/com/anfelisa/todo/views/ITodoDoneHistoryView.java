package com.anfelisa.todo.views;

import org.jdbi.v3.core.Handle;

import com.anfelisa.ace.IDataContainer;
import com.anfelisa.todo.data.TodoToggleData;
import com.anfelisa.todo.data.ToggleAllData;

@SuppressWarnings("all")
public interface ITodoDoneHistoryView {

	void toggle(TodoToggleData data, Handle handle);
	void toggleAll(ToggleAllData data, Handle handle);

}

/*                    S.D.G.                    */
