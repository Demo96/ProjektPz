package gra;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ZmianaJezyka {
	private List<ZmianaUstawienListener> listeners = new ArrayList<ZmianaUstawienListener>();
    public synchronized void addListener(ZmianaUstawienListener toAdd) {
        listeners.add(toAdd);
    }
    public synchronized void ustawjezyk(int i) {
        ZmianaJezykaEvent x=new ZmianaJezykaEvent(this,i);
        for (ZmianaUstawienListener hl : listeners)
            hl.zmienjezyk(x);
    }
}
