package com.anfelisa.todo.actions;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.TodoListData;
import com.anfelisa.todo.models.CustomTodoDao;
import com.anfelisa.todo.models.ITodoModel;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetAllTodosAction extends AbstractGetAllTodosAction {

	static final Logger LOG = LoggerFactory.getLogger(GetAllTodosAction.class);

	private CustomTodoDao todoDao = new CustomTodoDao();

	public GetAllTodosAction(DBI jdbi) {
		super(jdbi);
	}

	@GET
	@Timed
	@Path("/all")
	@PermitAll
	public Response get(@NotNull @QueryParam("uuid") String uuid) throws JsonProcessingException {
		this.actionData = new TodoListData(uuid);
		return this.apply();
	}

	protected final void loadDataForGetRequest() {
		List<ITodoModel> todos = todoDao.selectAllOrderedByCreatedDate(getHandle());
		this.actionData.setTodoList(todos);
	}

}

/* S.D.G. */
