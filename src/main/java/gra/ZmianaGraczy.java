package gra;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ZmianaGraczy {
	private List<ZmianaUstawienListener> listeners = new ArrayList<ZmianaUstawienListener>();
    public synchronized void addListener(ZmianaUstawienListener toAdd) {
        listeners.add(toAdd);
    }
    public synchronized void ustawgraczy(String g1,String g2,Color k1, Color k2) {
        ZmianaGraczyEvent x=new ZmianaGraczyEvent(this,g1,g2,k1,k2);
        for (ZmianaUstawienListener hl : listeners)
            hl.zmiengraczy(x);
    }
}
