package gra;

import java.awt.Color;
import java.util.EventObject;

public class ZmianaJezykaEvent extends EventObject {
	public int jezyk;
	public ZmianaJezykaEvent(Object source,int i) {
		super(source);
		jezyk=i;
	}
     
}
