package com.anfelisa.ace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.ws.rs.WebApplicationException;

import org.skife.jdbi.v2.Handle;

import com.anfelisa.ace.encryption.EncryptionService;
import com.fasterxml.jackson.core.JsonProcessingException;

public class AceController {

	private static AceExecutionMode aceExecutionMode;

	private final static JodaObjectMapper mapper = new JodaObjectMapper();

	private final static Map<String, List<BiConsumer<? extends IDataContainer, Handle>>> consumerMap = new HashMap<String, List<BiConsumer<? extends IDataContainer, Handle>>>();

	private final static AceDao aceDao = new AceDao();

	public static AceExecutionMode getAceExecutionMode() {
		return aceExecutionMode;
	}

	public static void setAceExecutionMode(AceExecutionMode aceExecutionMode) {
		AceController.aceExecutionMode = aceExecutionMode;
	}

	public static void addActionToTimeline(IAction action) {
		try {
			String json = mapper.writeValueAsString(action.getActionData());
			addItemToTimeline("action", action.getHttpMethod().name(), action.getActionName(), json,
					action.getActionData().getUuid(), action.getDatabaseHandle());
		} catch (JsonProcessingException e) {
			throw new WebApplicationException(e);
		}
	}

	public static void addCommandToTimeline(ICommand command) {
		try {
			addItemToTimeline("command", null, command.getCommandName(),
					mapper.writeValueAsString(command.getCommandData()), command.getCommandData().getUuid(),
					command.getDatabaseHandle());
		} catch (JsonProcessingException e) {
			throw new WebApplicationException(e);
		}
	}

	public static void addEventToTimeline(IEvent event) {
		try {
			addItemToTimeline("event", null, event.getEventName(), mapper.writeValueAsString(event.getEventData()),
					event.getEventParam().getUuid(), event.getDatabaseHandle());
		} catch (JsonProcessingException e) {
			throw new WebApplicationException(e);
		}
	}

	public static void addPreparingEventToTimeline(IEvent event, String uuid) {
		try {
			addItemToTimeline("preparing event", null, event.getEventName(), mapper.writeValueAsString(event.getEventData()),
					uuid, event.getDatabaseHandle());
		} catch (JsonProcessingException e) {
			throw new WebApplicationException(e);
		}
	}

	public static void addExceptionToTimeline(String uuid, Throwable x, DatabaseHandle databaseHandle) {
		aceDao.insertIntoErrorTimeline(databaseHandle.getErrorHandle(), "exception", null, x.getClass().getName(),
				x.getMessage(), uuid);
	}

	private static void addItemToTimeline(String type, String method, String name, String json, String uuid,
			DatabaseHandle databaseHandle) {
		if (databaseHandle == null) {
			throw new WebApplicationException("no database handle");
		}
		aceDao.insertIntoTimeline(databaseHandle.getHandle(), type, method, name, EncryptionService.encrypt(json),
				uuid);
		aceDao.insertIntoErrorTimeline(databaseHandle.getErrorHandle(), type, method, name,
				EncryptionService.encrypt(json), uuid);
	}

	public static void addConsumer(String eventName, BiConsumer<? extends IDataContainer, Handle> createUserTable) {
		List<BiConsumer<? extends IDataContainer, Handle>> consumerForEvent = consumerMap.get(eventName);
		if (consumerForEvent == null) {
			consumerForEvent = new ArrayList<BiConsumer<? extends IDataContainer, Handle>>();
			consumerMap.put(eventName, consumerForEvent);
		}
		consumerForEvent.add(createUserTable);
	}

	public static List<BiConsumer<? extends IDataContainer, Handle>> getConsumerForEvent(String eventName) {
		return consumerMap.get(eventName);
	}

}
