/********************************************************************************
 * generated by de.acegen
 ********************************************************************************/




package de.acegen;

public interface IAction<T> {

	String getActionName();
	
    T apply(T data);
    
    T initActionData(T data);
    
}




/******* S.D.G. *******/



