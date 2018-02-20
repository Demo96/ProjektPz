package gra;

import java.awt.Color;
import java.util.EventObject;

public class ZmianaTlaEvent extends EventObject {
	public Color tlo;
	public ZmianaTlaEvent(Object source,Color i) {
		super(source);
		tlo=i;
	}
     
}
