package com.anfelisa.todo.views;

import org.jdbi.v3.core.Handle;

import com.anfelisa.ace.IDataContainer;
import com.anfelisa.todo.data.TodoData;
import com.anfelisa.todo.data.TodoToggleData;
import com.anfelisa.todo.data.ToggleAllData;
import com.anfelisa.todo.data.TodoIdData;
import com.anfelisa.todo.data.TodoData;
import com.anfelisa.todo.data.ClearDoneData;

@SuppressWarnings("all")
public interface ITodoView {

	void create(TodoData data, Handle handle);
	void toggle(TodoToggleData data, Handle handle);
	void toggleAll(ToggleAllData data, Handle handle);
	void delete(TodoIdData data, Handle handle);
	void update(TodoData data, Handle handle);
	void clearDone(ClearDoneData data, Handle handle);

}

/*                    S.D.G.                    */
