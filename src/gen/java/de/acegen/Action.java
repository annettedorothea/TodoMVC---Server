/********************************************************************************
 * generated by de.acegen 1.2.1
 ********************************************************************************/




package de.acegen;

public abstract class Action<T extends IDataContainer> implements IAction<T> {

	protected String actionName;

	public Action(String actionName) {
		super();
		this.actionName = actionName;
	}
	
	public String getActionName() {
		return this.actionName;
	}

	protected void throwSecurityException() {
		throw new SecurityException();
	}

	protected void throwIllegalArgumentException(String error) {
		throw new IllegalArgumentException(error);
	}

}



/******* S.D.G. *******/



