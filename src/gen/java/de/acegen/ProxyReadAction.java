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
 * generated with de.acegen 0.9.5
 *
 */




package de.acegen;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ProxyReadAction<T extends IDataContainer> extends Action<T> {

	static final Logger LOG = LoggerFactory.getLogger(ReadAction.class);
	
	private PersistenceConnection persistenceConnection;
	protected CustomAppConfiguration appConfiguration;
	protected IDaoProvider daoProvider;
	private E2E e2e;
	
	public ProxyReadAction(String actionName, PersistenceConnection persistenceConnection, CustomAppConfiguration appConfiguration, 
			IDaoProvider daoProvider, ViewProvider viewProvider, E2E e2e) {
		super(actionName);
		this.persistenceConnection = persistenceConnection;
		this.appConfiguration = appConfiguration;
		this.daoProvider = daoProvider;
		this.e2e = e2e;
	}

	protected abstract void loadDataForGetRequest(PersistenceHandle readonlyHandle);
	
	protected abstract void initActionDataFrom(ITimelineItem timelineItem);

	protected abstract void initActionDataFromNotReplayableDataProvider();

	protected abstract T createDataFrom(ITimelineItem timelineItem);

	public void apply() {
		DatabaseHandle databaseHandle = new DatabaseHandle(persistenceConnection.getJdbi(), appConfiguration);
		databaseHandle.beginTransaction();
		try {
			if (Config.DEV.equals(appConfiguration.getConfig().getMode())
					|| Config.LIVE.equals(appConfiguration.getConfig().getMode())
					|| Config.TEST.equals(appConfiguration.getConfig().getMode())) {
				if (!daoProvider.getAceDao().checkUuid(this.actionData.getUuid())) {
					databaseHandle.rollbackTransaction();
					LOG.warn("duplicate request {} {} ", actionName, this.actionData.getUuid());
					databaseHandle.rollbackTransaction();
					return;
				}
				this.actionData.setSystemTime(LocalDateTime.now());
				this.initActionData();
			} else if (Config.REPLAY.equals(appConfiguration.getConfig().getMode())) {
				ITimelineItem timelineItem = e2e.selectAction(this.actionData.getUuid());
				initActionDataFrom(timelineItem);
			}
			if (Config.TEST.equals(appConfiguration.getConfig().getMode())) {
				initActionDataFromNotReplayableDataProvider();
			}
			if (Config.REPLAY.equals(appConfiguration.getConfig().getMode())) {
				ITimelineItem timelineItem = e2e.selectAction(this.actionData.getUuid());
				T originalData = this.createDataFrom(timelineItem);
				this.setActionData(originalData);
			} else {
				this.loadDataForGetRequest(databaseHandle.getReadonlyHandle());
			}
			
			if (appConfiguration.getConfig().writeTimeline()) {
				daoProvider.getAceDao().addActionToTimeline(this, databaseHandle.getTimelineHandle());
			}
			databaseHandle.commitTransaction();
		} catch (IllegalArgumentException x) {
			LOG.error(actionName + " IllegalArgumentException {} ", x.getMessage());
			try {
				if (appConfiguration.getConfig().writeError()) {
					daoProvider.getAceDao().addExceptionToTimeline(this.actionData.getUuid(), x, databaseHandle.getTimelineHandle());
				}
				databaseHandle.rollbackTransaction();
			} catch (Exception ex) {
				LOG.error(actionName + ": failed to rollback or to save or report exception " + ex.getMessage());
			}
			throw x;
		} catch (SecurityException x) {
			LOG.error(actionName + " SecurityException {} ", x.getMessage());
			try {
				if (appConfiguration.getConfig().writeError()) {
					daoProvider.getAceDao().addExceptionToTimeline(this.actionData.getUuid(), x, databaseHandle.getTimelineHandle());
				}
				databaseHandle.rollbackTransaction();
			} catch (Exception ex) {
				LOG.error(actionName + ": failed to rollback or to save or report exception " + ex.getMessage());
			}
			throw x;
		} catch (Exception x) {
			LOG.error(actionName + " Exception {} ", x.getMessage());
			try {
				if (appConfiguration.getConfig().writeError()) {
					daoProvider.getAceDao().addExceptionToTimeline(this.actionData.getUuid(), x, databaseHandle.getTimelineHandle());
				}
				databaseHandle.rollbackTransaction();
			} catch (Exception ex) {
				LOG.error(actionName + ": failed to rollback or to save or report exception " + ex.getMessage());
			}
			throw x;
		} finally {
			databaseHandle.close();
		}
		
	}

}




/******* S.D.G. *******/



