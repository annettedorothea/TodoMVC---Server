package com.anfelisa.todo.app.bug;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import org.skife.jdbi.v2.Update;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@SuppressWarnings("all")
@JsonIgnoreType
public class BugDao {
	
	public Integer insert(Handle handle, IBugModel bugModel) {
		Query<Map<String, Object>> statement = handle.createQuery("INSERT INTO public.bug (description, data, reporter, resolved, createddatetime, updateddatetime) VALUES (:description, :data, :reporter, :resolved, :createddatetime, :updateddatetime) RETURNING id");
		statement.bind("description",  bugModel.getDescription() );
		statement.bind("data",  bugModel.getData() );
		statement.bind("reporter",  bugModel.getReporter() );
		statement.bind("resolved",  bugModel.getResolved() );
		statement.bind("createddatetime",  bugModel.getCreatedDateTime() );
		statement.bind("updateddatetime",  bugModel.getUpdatedDateTime() );
		Map<String, Object> first = statement.first();
		return (Integer) first.get("id");
	}
	
	
	public void updateById(Handle handle, IBugModel bugModel) {
		Update statement = handle.createStatement("UPDATE public.bug SET description = :description, data = :data, reporter = :reporter, resolved = :resolved, createddatetime = :createddatetime, updateddatetime = :updateddatetime WHERE id = :id");
		statement.bind("description",  bugModel.getDescription() );
		statement.bind("data",  bugModel.getData() );
		statement.bind("reporter",  bugModel.getReporter() );
		statement.bind("resolved",  bugModel.getResolved() );
		statement.bind("createddatetime",  bugModel.getCreatedDateTime() );
		statement.bind("updateddatetime",  bugModel.getUpdatedDateTime() );
		statement.execute();
	}

	public void updateResolvedById(Handle handle, Integer id, DateTime updateddatetime) {
		Update statement = handle.createStatement("UPDATE public.bug SET resolved = true, updateddatetime = :updateddatetime WHERE id = :id");
		statement.bind("id",  id );
		statement.bind("updateddatetime",  updateddatetime );
		statement.execute();
	}
	
	public void deleteById(Handle handle, Integer id) {
		Update statement = handle.createStatement("DELETE FROM public.bug WHERE id = :id");
		statement.bind("id", id);
		statement.execute();
	}

	public IBugModel selectById(Handle handle, Integer id) {
		return handle.createQuery("SELECT id, description, data, reporter, resolved, createddatetime, updateddatetime FROM public.bug WHERE id = :id")
			.bind("id", id)
			.map(new BugMapper())
			.first();
	}
	
	public List<IBugModel> selectAll(Handle handle) {
		return handle.createQuery("SELECT id, description, data, reporter, resolved, createddatetime, updateddatetime FROM public.bug order by createddatetime")
			.map(new BugMapper())
			.list();
	}

	public void truncate(Handle handle) {
		Update statement = handle.createStatement("TRUNCATE public.bug");
		statement.execute();
		statement = handle.createStatement("ALTER SEQUENCE public.bug_id_seq RESTART");
		statement.execute();
	}

}

/*       S.D.G.       */
