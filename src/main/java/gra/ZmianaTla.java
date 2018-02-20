package gra;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ZmianaTla {
	private List<ZmianaUstawienListener> listeners = new ArrayList<ZmianaUstawienListener>();
    public synchronized void addListener(ZmianaUstawienListener toAdd) {
        listeners.add(toAdd);
    }
    public synchronized void ustawtlo(Color i) {
        ZmianaTlaEvent x=new ZmianaTlaEvent(this,i);
        for (ZmianaUstawienListener hl : listeners)
            hl.zmientlo(x);
    }
}
