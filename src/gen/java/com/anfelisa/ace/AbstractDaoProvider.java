package com.anfelisa.ace;

public abstract class AbstractDaoProvider implements IDaoProvider {

	protected final AceDao aceDao = new AceDao();

	@Override
	public AceDao getAceDao() {
		return this.aceDao;
	}
	
}

