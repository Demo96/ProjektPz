package gra;

import java.awt.Color;

public interface ZmianaUstawienListener {
	public void zmientlo(ZmianaTlaEvent e);
    public void zmienjezyk(ZmianaJezykaEvent e);
    public void zmiengraczy(ZmianaGraczyEvent e);
}
