package gra;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class PrzesunPionka {
	private List<OperacjePionki> listeners = new ArrayList<OperacjePionki>();
    public synchronized void addListener(OperacjePionki toAdd) {
        listeners.add(toAdd);
    }
    public synchronized void przesunpionek(int nr,int pozx,int pozy) {
        PrzesunPionkaEvent x=new PrzesunPionkaEvent(this,nr,pozx,pozy);
        for (OperacjePionki hl : listeners)
            hl.przesunpionka(x);
    }
}
