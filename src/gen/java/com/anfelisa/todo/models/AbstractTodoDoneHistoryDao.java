package com.anfelisa.todo.models;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
public class AbstractTodoDoneHistoryDao {
	
	public void insert(Handle handle, ITodoDoneHistoryModel todoDoneHistoryModel) {
		Update statement = handle.createUpdate("INSERT INTO tododonehistory (id, description, donedatetime) VALUES (:id, :description, :donedatetime)");
		statement.bind("id",  todoDoneHistoryModel.getId() );
		statement.bind("description",  todoDoneHistoryModel.getDescription() );
		statement.bind("donedatetime",  todoDoneHistoryModel.getDoneDateTime() );
		statement.execute();
	}
	
	
	public void updateById(Handle handle, ITodoDoneHistoryModel todoDoneHistoryModel) {
		Update statement = handle.createUpdate("UPDATE tododonehistory SET id = :id, description = :description, donedatetime = :donedatetime WHERE id = :id");
		statement.bind("id",  todoDoneHistoryModel.getId() );
		statement.bind("description",  todoDoneHistoryModel.getDescription() );
		statement.bind("donedatetime",  todoDoneHistoryModel.getDoneDateTime() );
		statement.bind("id",  todoDoneHistoryModel.getId()  );
		statement.execute();
	}

	public void deleteById(Handle handle, String id) {
		Update statement = handle.createUpdate("DELETE FROM tododonehistory WHERE id = :id");
		statement.bind("id", id);
		statement.execute();
	}

	public ITodoDoneHistoryModel selectById(Handle handle, String id) {
		Optional<ITodoDoneHistoryModel> optional = handle.createQuery("SELECT id, description, donedatetime FROM tododonehistory WHERE id = :id")
			.bind("id", id)
			.map(new TodoDoneHistoryMapper())
			.findFirst();
		return optional.isPresent() ? optional.get() : null;
	}
	
	public List<ITodoDoneHistoryModel> selectAll(Handle handle) {
		return handle.createQuery("SELECT id, description, donedatetime FROM tododonehistory")
			.map(new TodoDoneHistoryMapper())
			.list();
	}

	public void truncate(Handle handle) {
		Update statement = handle.createUpdate("TRUNCATE TABLE tododonehistory");
		statement.execute();
	}

}

/*       S.D.G.       */
