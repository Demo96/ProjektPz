package gra;

import java.util.EventObject;

public class PrzesunPionkaEvent extends EventObject  {
	int nrpionka, x, y;
	public PrzesunPionkaEvent(Object source,int nr,int pozx,int pozy) {
		super(source);
		nrpionka=nr;
		x=pozx;
		y=pozy;
	}

}
