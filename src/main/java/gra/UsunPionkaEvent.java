package gra;

import java.util.EventObject;

public class UsunPionkaEvent extends EventObject {
     int i;
	public UsunPionkaEvent(Object source,int pionek) {
		super(source);
		this.i=pionek;
	}

}
