package gra;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Ustawienia extends JPanel implements ActionListener {
	public MyButton jezyk;
	public MyButton gracz1;
	public MyButton gracz2;
	public MyButton tlo;
	public MyButton zapisz;
	public MyButton domyslne;
	public MyTextField nazwa1;
	public MyTextField nazwa2;
	public ActionFrame main;
	public int currjezyk;
	public Color currgracz1, currgracz2, currtlo;
	public String[] jezykt = { "jezyk: Polski", "language: English" };
	public String[] gracz1bt = { "kolor gracza1= Bialy", "color player1= White" };
	public String[] gracz1ct = { "kolor gracza1= Cyjanowy", "color player1= Cyan" };
	public String[] gracz2ct = { "kolor gracza2= Czarny", "color player2= Black" };
	public String[] gracz2mt = { "kolor gracza2= Magnetowy", "color player2= Magneta" };
	public String[] tlost = { "kolor tla= Szary", "background color= Gray" };
	public String[] tlozt = { "kolor tla= Zolty", "background color= Yellow" };
	public String[] domyslnet = { "Przywroc ustawienia domyslne", "Restore Default" };
	public String[] zapiszt = { "ZAPISZ I WYJDZ", "SAVE AND QUIT" };
	public ZmianaTla zt = new ZmianaTla();
	public ZmianaJezyka zj = new ZmianaJezyka();
	public ZmianaGraczy zg = new ZmianaGraczy();

	public Ustawienia(ActionFrame af) {
		this.main = af;
		zt.addListener(main);
		zj.addListener(main.menu);
		zj.addListener(main);
		zg.addListener(main);
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		jezyk = new MyButton(jezykt[main.jezyk]);
		if (main.kolor1 == Color.WHITE)
			gracz1 = new MyButton(gracz1bt[main.jezyk]);
		else
			gracz1 = new MyButton(gracz1ct[main.jezyk]);
		if (main.kolor2 == Color.BLACK)
			gracz2 = new MyButton(gracz2ct[main.jezyk]);
		else
			gracz2 = new MyButton(gracz2mt[main.jezyk]);
		if (main.tlo == Color.GRAY)
			tlo = new MyButton(tlost[main.jezyk]);
		else
			tlo = new MyButton(tlozt[main.jezyk]);
		zapisz = new MyButton(zapiszt[main.jezyk]);
		domyslne = new MyButton(domyslnet[main.jezyk]);
		jezyk.addActionListener(this);
		gracz1.addActionListener(this);
		gracz2.addActionListener(this);
		tlo.addActionListener(this);
		zapisz.addActionListener(this);
		domyslne.addActionListener(this);
		nazwa1 = new MyTextField(main.nazwa1);
		nazwa2 = new MyTextField(main.nazwa2);
		nazwa1.setPreferredSize(new Dimension(315, 25));
		layout.putConstraint(SpringLayout.WEST, nazwa1, 75, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, nazwa1, 75, SpringLayout.NORTH, this);
		nazwa2.setPreferredSize(new Dimension(315, 25));
		layout.putConstraint(SpringLayout.WEST, nazwa2, 410, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, nazwa2, 75, SpringLayout.NORTH, this);
		gracz1.setPreferredSize(new Dimension(315, 60));
		layout.putConstraint(SpringLayout.WEST, gracz1, 75, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, gracz1, 130, SpringLayout.NORTH, this);
		gracz2.setPreferredSize(new Dimension(315, 60));
		layout.putConstraint(SpringLayout.WEST, gracz2, 410, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, gracz2, 130, SpringLayout.NORTH, this);
		tlo.setPreferredSize(new Dimension(315, 60));
		layout.putConstraint(SpringLayout.WEST, tlo, 75, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, tlo, 220, SpringLayout.NORTH, this);
		jezyk.setPreferredSize(new Dimension(315, 60));
		layout.putConstraint(SpringLayout.WEST, jezyk, 410, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, jezyk, 220, SpringLayout.NORTH, this);
		domyslne.setPreferredSize(new Dimension(650, 60));
		layout.putConstraint(SpringLayout.WEST, domyslne, 75, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, domyslne, 310, SpringLayout.NORTH, this);
		zapisz.setPreferredSize(new Dimension(650, 60));
		layout.putConstraint(SpringLayout.WEST, zapisz, 75, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, zapisz, 400, SpringLayout.NORTH, this);
		add(nazwa1);
		add(nazwa2);
		add(gracz1);
		add(gracz2);
		add(tlo);
		add(jezyk);
		add(domyslne);
		add(zapisz);
	}

	public void setCurr(Color g1, Color g2, Color t, int j) {
		currgracz1 = g1;
		currgracz2 = g2;
		currtlo = t;
		currjezyk = j;
		odswierznapisy(currjezyk);
		nazwa1.setText(main.nazwa1);
		nazwa2.setText(main.nazwa2);
		setBackground(currtlo);
		nazwa1.setBackground(Color.WHITE);
		nazwa2.setBackground(Color.WHITE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gracz1) {
			if (currgracz1 == Color.WHITE) {
				main.log.info("gracz1 zmiana koloru na cyan");
				currgracz1 = Color.CYAN;
				gracz1.setLabel(gracz1ct[currjezyk]);
			} else {
				main.log.info("gracz1 zmiana koloru na white");
				currgracz1 = Color.WHITE;
				gracz1.setLabel(gracz1bt[currjezyk]);
			}
		} else if (e.getSource() == gracz2) {
			if (currgracz2 == Color.BLACK) {
				main.log.info("gracz2 zmiana koloru na magenta");
				currgracz2 = Color.MAGENTA;
				gracz2.setLabel(gracz2mt[currjezyk]);
			} else {
				main.log.info("gracz2 zmiana koloru na black");
				currgracz2 = Color.BLACK;
				gracz2.setLabel(gracz2ct[currjezyk]);
			}
		} else if (e.getSource() == tlo) {
			if (currtlo == Color.GRAY) {
				main.log.info("tlo zmiana koloru na yellow");
				currtlo = Color.YELLOW;
				tlo.setLabel(tlozt[currjezyk]);
			} else {
				main.log.info("tlo zmiana koloru na gray");
				currtlo = Color.GRAY;
				tlo.setLabel(tlost[currjezyk]);
			}
			setBackground(currtlo);
		} else if (e.getSource() == jezyk) {
			if (currjezyk == 0) {
				main.log.info("zmiana jezyka na angielski");
				currjezyk = 1;
				odswierznapisy(1);
			} else {
				main.log.info("zmiana jezyka na polski");
				currjezyk = 0;
				odswierznapisy(0);
			}
			jezyk.setLabel(jezykt[currjezyk]);
		} else if (e.getSource() == domyslne) {
			setCurr(Color.WHITE, Color.BLACK, Color.GRAY, 0);
			zj.ustawjezyk(0);
			jezyk.setLabel(jezykt[main.jezyk]);
			zt.ustawtlo(Color.GRAY);
			tlo.setLabel(tlost[main.jezyk]);
			gracz2.setLabel(gracz2ct[main.jezyk]);
			gracz1.setLabel(gracz1bt[main.jezyk]);
			nazwa1.setText("John");
			nazwa2.setText("James");
			zg.ustawgraczy("John", "James", Color.WHITE, Color.BLACK);
			main.log.info("przywrocenie ustawien domyslnych");
		} else if (e.getSource() == zapisz) {
			nazwa1.setBackground(Color.WHITE);
			nazwa2.setBackground(Color.WHITE);
			if (nazwa1.getText().length() > 11) {
				main.log.warning("TooLongNameException(\"nazwa gracz1 ma wiecej niz 11 znakow\")");
				nazwa1.setBackground(Color.RED);
				throw new TooLongNameException("nazwa gracz1 ma wiecej niz 11 znakow");
			}
			if (nazwa2.getText().length() > 11) {
				main.log.warning("TooLongNameException(\"nazwa gracz2 ma wiecej niz 11 znakow\")");
				nazwa2.setBackground(Color.RED);
				throw new TooLongNameException("nazwa gracz2 ma wiecej niz 11 znakow");
			}
			if (nazwa1.getText().length() == 0) {
				main.log.warning("NoNameException(\"brak nazwy gracza1\")");
				nazwa1.setBackground(Color.RED);
				throw new NoNameException("brak nazwy gracza1");
			}
			if (nazwa2.getText().length() == 0) {
				main.log.warning("NoNameException(\"brak nazwy gracza2\")");
				nazwa2.setBackground(Color.RED);
				throw new NoNameException("brak nazwy gracza2");
			}

			zg.ustawgraczy(nazwa1.getText(), nazwa2.getText(), currgracz1, currgracz2);
			zt.ustawtlo(currtlo);
			zj.ustawjezyk(currjezyk);
			main.cardLayout.show(main.cardPanel, "menu");
			zapisz();
			main.log.info("zapisano ustawienia");
		}

	}

	public void zapisz() {
		PrintWriter zapis;
		Properties prop = new Properties();
		OutputStream output = null;
		try {
			output = new FileOutputStream("config.properties");
			prop.setProperty("width", Integer.toString(main.width));
			prop.setProperty("height", Integer.toString(main.height));
			prop.setProperty("name1", nazwa1.getText());
			prop.setProperty("name2", nazwa2.getText());
			if (main.tlo == Color.GRAY)
				prop.setProperty("background", "1");
			else
				prop.setProperty("background", "2");
			if (main.kolor1 == Color.WHITE)
				prop.setProperty("color1", "1");
			else
				prop.setProperty("color1", "2");
			if (main.kolor2 == Color.BLACK)
				prop.setProperty("color2", "1");
			else
				prop.setProperty("color2", "2");
			if (main.jezyk == 0)
				prop.setProperty("language", "1");
			else
				prop.setProperty("language", "2");
			prop.store(output, null);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void odswierznapisy(int i) {
		if (currgracz1 == Color.WHITE)
			gracz1.setLabel(gracz1bt[i]);
		else
			gracz1.setLabel(gracz1ct[i]);
		if (currgracz2 == Color.BLACK)
			gracz2.setLabel(gracz2ct[i]);
		else
			gracz2.setLabel(gracz2mt[i]);
		if (currtlo == Color.GRAY)
			tlo.setLabel(tlost[i]);
		else
			tlo.setLabel(tlozt[i]);
		jezyk.setLabel(jezykt[i]);
		domyslne.setLabel(domyslnet[i]);
		zapisz.setLabel(zapiszt[i]);

	}

}
