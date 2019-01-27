package com.anfelisa.todo.views;

import org.jdbi.v3.core.Handle;

import com.anfelisa.ace.IDataContainer;
import com.anfelisa.todo.data.ITodoToggleData;
import com.anfelisa.todo.data.IToggleAllData;

@SuppressWarnings("all")
public interface ITodoDoneHistoryView {

	void toggle(ITodoToggleData data, Handle handle);
	void toggleAll(IToggleAllData data, Handle handle);

}

/*                    S.D.G.                    */
