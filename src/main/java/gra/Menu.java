package gra;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Menu extends JPanel implements ActionListener, ZmianaUstawienListener {
	PolandTime country = null;
	public MyButton2 nowa;
	public MyButton2 wczytaj1;
	public MyButton2 wczytaj2;
	public MyButton2 wczytaj3;
	public MyButton2 ustawienia;
	public ActionFrame main;
	public String nowat[] = { "Nowa gra", "New Game" };
	public String wczytaj1t[] = { "Wczytaj gre: Zapis1", "Load Game: Slot1" };
	public String wczytaj2t[] = { "Wczytaj gre: Zapis2", "Load Game: Slot2" };
	public String wczytaj3t[] = { "Wczytaj gre: Zapis3", "Load Game: Slot3" };
	public String ustawieniat[] = { "Ustawienia", "Seetings" };
	public int jezykMenu;

	public Menu(ActionFrame m,int j) {

		main = m;
		jezykMenu=j;
		nowa = new MyButton2(nowat[jezykMenu],Color.YELLOW);
		wczytaj1 = new MyButton2(wczytaj1t[jezykMenu],Color.GREEN);
		wczytaj2 = new MyButton2(wczytaj2t[jezykMenu],Color.GREEN);
		wczytaj3 = new MyButton2(wczytaj3t[jezykMenu],Color.GREEN);
		ustawienia = new MyButton2(ustawieniat[jezykMenu],Color.CYAN);
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(layout);
		c.insets = new Insets(85, 10, 5, 5);
		c.ipadx = 80;
		c.ipady = 80;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		add(nowa, c);
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		add(wczytaj1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		add(wczytaj2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		add(wczytaj3, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 2;
		add(ustawienia, c);
		nowa.addActionListener(this);
		wczytaj1.addActionListener(this);
		wczytaj2.addActionListener(this);
		wczytaj3.addActionListener(this);
		ustawienia.addActionListener(this);
	}

	public void paint(Graphics g) {
		super.paint(g);
		setBackground(main.tlo);
		g.setColor(Color.BLACK);
		g.setFont(new Font("", Font.BOLD | Font.ITALIC, 60));
		g.drawString("PROSTERNO", 200, 100);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// width = getWidth();
		// height = getHeight();
		if (e.getSource() == nowa) {
			main.log.info("utworzenie nowej gry");
			Prosterno prosterno = new Prosterno(main, 0,main.tlo,jezykMenu);
			main.prosterno = prosterno;
			main.cardPanel.remove(prosterno);
			main.cardPanel.add(main.prosterno, "prosterno");
			main.cardLayout.show(main.cardPanel, "prosterno");
		} else if (e.getSource() == wczytaj1) {
			main.log.info("zaladowanie gry: zapis1");
			Prosterno prosterno = new Prosterno(main, 1,main.tlo,jezykMenu);
			main.prosterno = prosterno;
			main.cardPanel.remove(prosterno);
			main.cardPanel.add(main.prosterno, "prosterno");
			main.cardLayout.show(main.cardPanel, "prosterno");
		} else if (e.getSource() == wczytaj2) {
			main.log.info("zaladowanie gry: zapis2");
			Prosterno prosterno = new Prosterno(main, 2,main.tlo,jezykMenu);
			main.prosterno = prosterno;
			main.cardPanel.remove(prosterno);
			main.cardPanel.add(main.prosterno, "prosterno");
			main.cardLayout.show(main.cardPanel, "prosterno");
		} else if (e.getSource() == wczytaj3) {
			main.log.info("zaladowanie gry: zapis3");
			Prosterno prosterno = new Prosterno(main, 3,main.tlo,jezykMenu);
			main.prosterno = prosterno;
			main.cardPanel.remove(prosterno);
			main.cardPanel.add(main.prosterno, "prosterno");
			main.cardLayout.show(main.cardPanel, "prosterno");
		} else if (e.getSource() == ustawienia) {
			main.log.info("przejscie do ustawien");
			main.ustawienia.setCurr(main.kolor1, main.kolor2, main.tlo, jezykMenu);
			main.cardLayout.show(main.cardPanel, "ustawienia");
		}

	}

	@Override
	public void zmientlo(ZmianaTlaEvent e) {
	}

	@Override
	public void zmienjezyk(ZmianaJezykaEvent e) {
		jezykMenu=e.jezyk;
		nowa.setLabel(main.menu.nowat[jezykMenu]);
		wczytaj1.setLabel(main.menu.wczytaj1t[jezykMenu]);
		wczytaj2.setLabel(main.menu.wczytaj2t[jezykMenu]);
		wczytaj3.setLabel(main.menu.wczytaj3t[jezykMenu]);
		ustawienia.setLabel(main.menu.ustawieniat[jezykMenu]);
		main.log.info(e.toString());
		
	}

	@Override
	public void zmiengraczy(ZmianaGraczyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
