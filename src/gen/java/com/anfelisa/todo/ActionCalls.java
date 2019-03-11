/* 
 * Copyright (c) 2019, Annette Pohl, Koblenz, Germany
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
 */




package com.anfelisa.todo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClientBuilder;

@SuppressWarnings("unused")
public class ActionCalls {

	public static Response callGetAllTodos(String uuid, int port) {
		Client client = new JerseyClientBuilder().build();
		Builder builder = client.target(String.format("http://localhost:%d/api/todos/all?uuid=" + uuid, port)).request(); 
		return builder.get();
	}
	
	public static Response callCreateTodo(String uuid, String description, int port) {
		Client client = new JerseyClientBuilder().build();
		Builder builder = client.target(String.format("http://localhost:%d/api/todos/create", port)).request(); 
		com.anfelisa.todo.data.ITodoData data = new com.anfelisa.todo.data.TodoData(uuid);
		data.setDescription(description);
		return builder.post(Entity.json(data));
	}
	
	public static Response callToggleTodo(String uuid, String id, int port) {
		Client client = new JerseyClientBuilder().build();
		Builder builder = client.target(String.format("http://localhost:%d/api/todos/toggle?uuid=" + uuid + "&id=" + id, port)).request();
		com.anfelisa.todo.data.ITodoToggleData data = new com.anfelisa.todo.data.TodoToggleData(uuid);
		return builder.put(Entity.json(null));
	}
	
	public static Response callToggleAll(String uuid, int port) {
		Client client = new JerseyClientBuilder().build();
		Builder builder = client.target(String.format("http://localhost:%d/api/todos/toggle-all?uuid=" + uuid, port)).request();
		com.anfelisa.todo.data.IToggleAllData data = new com.anfelisa.todo.data.ToggleAllData(uuid);
		return builder.put(Entity.json(null));
	}
	
	public static Response callUpdateTodo(String uuid, String id, String description, int port) {
		Client client = new JerseyClientBuilder().build();
		Builder builder = client.target(String.format("http://localhost:%d/api/todos/update?uuid=" + uuid, port)).request();
		com.anfelisa.todo.data.ITodoData data = new com.anfelisa.todo.data.TodoData(uuid);
		data.setId(id);
		data.setDescription(description);
		return builder.put(Entity.json(data));
	}
	
	public static Response callDeleteTodo(String uuid, String id, int port) {
		Client client = new JerseyClientBuilder().build();
		Builder builder = client.target(String.format("http://localhost:%d/api/todos/delete?uuid=" + uuid + "&id=" + id, port)).request();
		return builder.delete();
	}
	
	public static Response callClearDone(String uuid, int port) {
		Client client = new JerseyClientBuilder().build();
		Builder builder = client.target(String.format("http://localhost:%d/api/todos/clear-done?uuid=" + uuid, port)).request();
		return builder.delete();
	}
	
	public static Response callBatchCreate(String uuid, int port) {
		Client client = new JerseyClientBuilder().build();
		Builder builder = client.target(String.format("http://localhost:%d/api/batch-create", port)).request(); 
		com.anfelisa.todo.data.ITodoListData data = new com.anfelisa.todo.data.TodoListData(uuid);
		return builder.post(Entity.json(data));
	}
	
	
}




/******* S.D.G. *******/



