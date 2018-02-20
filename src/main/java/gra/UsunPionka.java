package gra;

import java.util.ArrayList;
import java.util.List;

public class UsunPionka {
	private List<OperacjePionki> listeners = new ArrayList<OperacjePionki>();
    public synchronized void addListener(OperacjePionki toAdd) {
        listeners.add(toAdd);
    }
    public synchronized void usunpionek(int nr) {
        UsunPionkaEvent x=new UsunPionkaEvent(this,nr);
        for (OperacjePionki hl : listeners)
            hl.usunpionka(x);
    }
}
