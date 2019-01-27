package com.anfelisa.todo.views;

import org.jdbi.v3.core.Handle;

import com.anfelisa.ace.IDataContainer;
import com.anfelisa.todo.data.ITodoData;
import com.anfelisa.todo.data.ITodoToggleData;
import com.anfelisa.todo.data.IToggleAllData;
import com.anfelisa.todo.data.ITodoIdData;
import com.anfelisa.todo.data.ITodoData;
import com.anfelisa.todo.data.IClearDoneData;

@SuppressWarnings("all")
public interface ITodoView {

	void create(ITodoData data, Handle handle);
	void toggle(ITodoToggleData data, Handle handle);
	void toggleAll(IToggleAllData data, Handle handle);
	void delete(ITodoIdData data, Handle handle);
	void update(ITodoData data, Handle handle);
	void clearDone(IClearDoneData data, Handle handle);

}

/*                    S.D.G.                    */
