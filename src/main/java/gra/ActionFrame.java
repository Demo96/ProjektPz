package gra;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ActionFrame extends JFrame implements ActionListener {
	public Color kolor1 = Color.WHITE, kolor2 = Color.BLACK, tlo = Color.GRAY;
	CardLayout cardLayout = new CardLayout();
	JPanel cardPanel = new JPanel(cardLayout);
	public String nazwa1 = "John";
	public String nazwa2 = "James";
	public int jezyk = 0;
	public Ustawienia ustawienia;
	public Menu menu;
	public Prosterno prosterno;
	public int width, height;
	public JMenuBar menuBar;
	public JMenu styl, przejdz;
	public JRadioButtonMenuItem styl1, styl2, styl3;
	public JMenuItem ust, load1, load2, load3, nowa, men;
	public String[] przejdzt = { "Przejdz", "Go to" };
	public String[] stylt = { "Styl", "Style" };
	public String[] nowat = { "Nowa gra", "New game" };
	public String[] ustt = { "Ustawienia", "Seetings" };
	public String[] ladujt = { "Laduj gre ", "Load slot " };
	public final Logger log = Logger.getLogger(Main.class.getName());
    public PlTime plczas;
	public ActionFrame() {
		super("PROSTERNO");
		try {
			FileHandler fh;
			int limit = 5242880;
			fh = new FileHandler("ProsternoLog.log", limit, 3, true);
			log.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		zaladuj();
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		setResizable(false);
		prosterno = new Prosterno(this, 0);
		ustawienia = new Ustawienia(this);
		menu = new Menu(this);
		log.info("rozpoczecie programu");
		cardPanel.add(menu, "menu");
		cardPanel.add(prosterno, "prosterno");
		cardPanel.add(ustawienia, "ustawienia");
		add(cardPanel, BorderLayout.CENTER);
		menuBar = new JMenuBar();
		styl = new JMenu(stylt[jezyk]);
		przejdz = new JMenu(przejdzt[jezyk]);
		styl1 = new JRadioButtonMenuItem(stylt[jezyk] + "1");
		styl2 = new JRadioButtonMenuItem(stylt[jezyk] + "2");
		styl3 = new JRadioButtonMenuItem(stylt[jezyk] + "3");
		ust = new JMenuItem(ustt[jezyk]);
		load1 = new JMenuItem(ladujt[jezyk] + "1");
		load2 = new JMenuItem(ladujt[jezyk] + "2");
		load3 = new JMenuItem(ladujt[jezyk] + "3");
		men = new JMenuItem("Menu");
		nowa = new JMenuItem(nowat[jezyk]);
		ust.addActionListener(this);
		nowa.addActionListener(this);
		men.addActionListener(this);
		load1.addActionListener(this);
		load2.addActionListener(this);
		load3.addActionListener(this);
		styl1.addActionListener(this);
		styl1.setEnabled(true);
		styl2.addActionListener(this);
		styl3.addActionListener(this);
		styl.add(styl1);
		styl.add(styl2);
		styl.add(styl3);
		przejdz.add(nowa);
		przejdz.add(load1);
		przejdz.add(load2);
		przejdz.add(load3);
		przejdz.add(men);
		przejdz.add(ust);
		menuBar.add(przejdz);
		menuBar.add(styl);
		setJMenuBar(menuBar);
		plczas=new PlTime(this,"","");
		plczas.execute();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void zaladuj() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			nazwa1 = prop.getProperty("name1");
			nazwa2 = prop.getProperty("name2");
			if (nazwa1.length() > 11) {
				log.warning("TooLongNameException(\"nazwa gracz1 ma wiecej niz 11 znakow\")");
				throw new TooLongNameException("nazwa gracz1 ma wiecej niz 11 znakow");
			}
			if (nazwa2.length() > 11) {
				log.warning("TooLongNameException(\"nazwa gracz2 ma wiecej niz 11 znakow\")");
				throw new TooLongNameException("nazwa gracz2 ma wiecej niz 11 znakow");
			}
			if (nazwa1.length() == 0) {
				log.warning("NoNameException(\"brak nazwy gracza1\")");
				throw new NoNameException("brak nazwy gracza1");
			}
			if (nazwa2.length() == 0) {
				log.warning("NoNameException(\"brak nazwy gracza2\")");
				throw new NoNameException("brak nazwy gracza2");
			}

			width = Integer.parseInt(prop.getProperty("width"));
			height = Integer.parseInt(prop.getProperty("height"));
			if (Integer.parseInt(prop.getProperty("background")) == 1)
				tlo = Color.GRAY;
			else if (Integer.parseInt(prop.getProperty("background")) == 2)
				tlo = Color.YELLOW;
			else
				throw new WrongDataInConfigException("background");
			if (Integer.parseInt(prop.getProperty("color1")) == 1)
				kolor1 = Color.WHITE;
			else if (Integer.parseInt(prop.getProperty("color1")) == 2)
				kolor1 = Color.CYAN;
			else
				throw new WrongDataInConfigException("color1");
			if (Integer.parseInt(prop.getProperty("color2")) == 1)
				kolor2 = Color.BLACK;
			else if (Integer.parseInt(prop.getProperty("color2")) == 2)
				kolor2 = Color.MAGENTA;
			else
				throw new WrongDataInConfigException("color2");
			if (Integer.parseInt(prop.getProperty("language")) == 1)
				jezyk = 0;
			else if (Integer.parseInt(prop.getProperty("language")) == 2)
				jezyk = 1;
			else
				throw new WrongDataInConfigException("language");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongDataInConfigException e) {
			// TODO Auto-generated catch block
			log.warning(e.getClass().getName() + " message: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}

	}

	public void odswierz() {
		styl.setText(stylt[jezyk]);
		przejdz.setText(przejdzt[jezyk]);
		styl1.setText(stylt[jezyk] + "1");
		styl2.setText(stylt[jezyk] + "2");
		styl3.setText(stylt[jezyk] + "3");
		ust.setText(ustt[jezyk]);
		load1.setText(ladujt[jezyk] + "1");
		load2.setText(ladujt[jezyk] + "2");
		load3.setText(ladujt[jezyk] + "3");
		nowa.setText(nowat[jezyk]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == styl1) {
			log.info("zmiana stylu: styl1");
			styl2.setSelected(false);
			styl3.setSelected(false);
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				SwingUtilities.updateComponentTreeUI(this);
				this.pack();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			;
		} else if (e.getSource() == styl2) {
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
				SwingUtilities.updateComponentTreeUI(this);
				this.pack();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			log.info("zmiana stylu: styl2");
			styl1.setSelected(false);
			styl3.setSelected(false);
		} else if (e.getSource() == styl3) {
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				SwingUtilities.updateComponentTreeUI(this);
				this.pack();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			log.info("zmiana stylu: styl3");
			styl1.setSelected(false);
			styl2.setSelected(false);
		} else if (e.getSource() == ust) {
			log.info("przejscie do ustawien");
			ustawienia.setCurr(kolor1, kolor2, tlo, jezyk);
			cardLayout.show(cardPanel, "ustawienia");
		} else if (e.getSource() == nowa) {
			log.info("utworzenie nowej gry");
			Prosterno prosternox = new Prosterno(this, 0);
			prosterno = prosternox;
			cardPanel.remove(prosterno);
			cardPanel.add(prosterno, "prosterno");
			cardLayout.show(cardPanel, "prosterno");
		} else if (e.getSource() == load1) {
			log.info("zaladowanie gry: zapis1");
			Prosterno prosternox = new Prosterno(this, 1);
			prosterno = prosternox;
			cardPanel.remove(prosterno);
			cardPanel.add(prosterno, "prosterno");
			cardLayout.show(cardPanel, "prosterno");
		} else if (e.getSource() == load2) {
			log.info("zaladowanie gry: zapis2");
			Prosterno prosternox = new Prosterno(this, 2);
			prosterno = prosternox;
			cardPanel.remove(prosterno);
			cardPanel.add(prosterno, "prosterno");
			cardLayout.show(cardPanel, "prosterno");
		} else if (e.getSource() == load3) {
			log.info("zaladowanie gry: zapis3");
			Prosterno prosternox = new Prosterno(this, 3);
			prosterno = prosternox;
			cardPanel.remove(prosterno);
			cardPanel.add(prosterno, "prosterno");
			cardLayout.show(cardPanel, "prosterno");
		} else if (e.getSource() == men) {
			log.info("przejscie do menu");
			cardLayout.show(cardPanel, "menu");
		}

	}
}
