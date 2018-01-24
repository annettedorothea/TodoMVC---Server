package com.anfelisa.ace.bug;

import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import org.skife.jdbi.v2.Update;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import com.anfelisa.ace.encryption.EncryptionService;

@SuppressWarnings("all")
@JsonIgnoreType
public class BugDao {
	
	public Integer insert(Handle handle, IBugModel bugModel) {
		Query<Map<String, Object>> statement = handle.createQuery("INSERT INTO public.bug (description, timeline, reporter, resolved, createddatetime, updateddatetime, serverversion, clientversion, device) VALUES (:description, :timeline, :reporter, :resolved, :createddatetime, :updateddatetime, :serverversion, :clientversion, :device) RETURNING id");
		statement.bind("description",  bugModel.getDescription() );
		statement.bind("timeline",  bugModel.getTimeline() );
		statement.bind("reporter",  bugModel.getReporter() );
		statement.bind("resolved",  bugModel.getResolved() );
		statement.bind("createddatetime",  bugModel.getCreatedDateTime() );
		statement.bind("updateddatetime",  bugModel.getUpdatedDateTime() );
		statement.bind("serverversion",  bugModel.getServerVersion() );
		statement.bind("clientversion",  bugModel.getClientVersion() );
		statement.bind("device",  bugModel.getDevice() );
		Map<String, Object> first = statement.first();
		return (Integer) first.get("id");
	}
	
	
	public void updateById(Handle handle, IBugModel bugModel) {
		Update statement = handle.createStatement("UPDATE public.bug SET description = :description, timeline = :timeline, reporter = :reporter, resolved = :resolved, createddatetime = :createddatetime, updateddatetime = :updateddatetime, serverversion = :serverversion, clientversion = :clientversion, device = :device WHERE id = :id");
		statement.bind("description",  bugModel.getDescription() );
		statement.bind("timeline",  bugModel.getTimeline() );
		statement.bind("reporter",  bugModel.getReporter() );
		statement.bind("resolved",  bugModel.getResolved() );
		statement.bind("createddatetime",  bugModel.getCreatedDateTime() );
		statement.bind("updateddatetime",  bugModel.getUpdatedDateTime() );
		statement.bind("serverversion",  bugModel.getServerVersion() );
		statement.bind("clientversion",  bugModel.getClientVersion() );
		statement.bind("device",  bugModel.getDevice() );
		statement.bind("id",  bugModel.getId()  );
		statement.execute();
	}

	public void updateResolvedById(Handle handle, Integer id, org.joda.time.DateTime updateddatetime) {
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
		return handle.createQuery("SELECT id, description, timeline, reporter, resolved, createddatetime, updateddatetime, serverversion, clientversion, device FROM public.bug WHERE id = :id")
			.bind("id", id)
			.map(new BugMapper())
			.first();
	}
	
	public List<IBugModel> selectAll(Handle handle) {
		return handle.createQuery("SELECT id, description, timeline, reporter, resolved, createddatetime, updateddatetime, serverversion, clientversion, device FROM public.bug")
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
		

