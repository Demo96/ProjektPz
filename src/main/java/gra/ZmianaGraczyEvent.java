package gra;

import java.awt.Color;
import java.util.EventObject;

public class ZmianaGraczyEvent extends EventObject{
	public String g1,g2;
	public Color k1,k2;
	public ZmianaGraczyEvent(Object source,String gracz1,String gracz2,Color kolor1,Color kolor2) {
		super(source);
		g1=gracz1;
		g2=gracz2;
		k1=kolor1;
		k2=kolor2;
}
}