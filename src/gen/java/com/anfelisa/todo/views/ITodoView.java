/********************************************************************************
 * generated by de.acegen 0.9.10
 ********************************************************************************/




package com.anfelisa.todo.views;


import de.acegen.IDataContainer;
import de.acegen.PersistenceHandle;
import com.anfelisa.todo.data.ITodoData;
import com.anfelisa.todo.data.ITodoToggleData;
import com.anfelisa.todo.data.IToggleAllData;
import com.anfelisa.todo.data.ITodoIdData;
import com.anfelisa.todo.data.ITodoData;
import com.anfelisa.todo.data.IClearDoneData;

@SuppressWarnings("all")
public interface ITodoView {

	void create(ITodoData data, PersistenceHandle handle);
	void toggle(ITodoToggleData data, PersistenceHandle handle);
	void toggleAll(IToggleAllData data, PersistenceHandle handle);
	void delete(ITodoIdData data, PersistenceHandle handle);
	void update(ITodoData data, PersistenceHandle handle);
	void clearDone(IClearDoneData data, PersistenceHandle handle);

}




/******* S.D.G. *******/



