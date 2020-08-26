/* 
 * Copyright (c) 2020, Annette Pohl, Koblenz, Germany
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.

 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 * generated with de.acegen 0.9.10
 *
 */




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



