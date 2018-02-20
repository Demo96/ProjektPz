package gra;

import java.applet.Applet;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class Prosterno extends JPanel implements MouseListener, ActionListener,OperacjePionki {
	public int width, height, rPola, rOdstepu, rPionka;
	public LinkedList<Pionek> listapionkow;
	public int aktualnyPionek = -1;
	public Color kolor1, kolor2;
	public Color czyjruch;
	public Color kolorTla = Color.WHITE;
	public int pionki1;
	public int pionki2;
	public ActionFrame main;
	public Button powrot;
	public Button zapisz1;
	public Button zapisz2;
	public Button zapisz3;
	public String[] powrott = { "Wroc do Menu", "Return to Menu" };
	public String[] zapisz1t = { "Zapisz gre: zapis1", "Save game: slot1" };
	public String[] zapisz2t = { "Zapisz gre: zapis2", "Save game: slot2" };
	public String[] zapisz3t = { "Zapisz gre: zapis3", "Save game: slot3" };
	public String[] turat = { "Tura gracza:", "Player's turn:" };
	public String[] gracz1t = { "Gracz1: ", "Player1: " };
	public String[] gracz2t = { "Gracz2: ", "Player2: " };
	public String[] wygranat = { "Wygrywa gracz: ", "Winner player:  " };
	private String url = "";
	private String user = "";
	private String password = "";
	public boolean wygrana = false;
	public int jezykGra;
	public PrzesunPionka pp;
	public UsunPionka up;
	public void MouseSpyApplet() {
		addMouseListener(this);
	}

	public Prosterno(ActionFrame actionFrame, int zapis,Color kt,int jezyk) {
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		MouseSpyApplet();
		listapionkow = new LinkedList<Pionek>();
		main = actionFrame;
		kolor1 = main.kolor1;
		kolor2 = main.kolor2;
		kolorTla = kt;
		jezykGra=jezyk;
		powrot = new Button(powrott[jezykGra]);
		zapisz1 = new Button(zapisz1t[jezykGra]);
		zapisz2 = new Button(zapisz2t[jezykGra]);
		zapisz3 = new Button(zapisz3t[jezykGra]);
		powrot.addActionListener(this);
		zapisz1.addActionListener(this);
		zapisz2.addActionListener(this);
		zapisz3.addActionListener(this);
		powrot.setPreferredSize(new Dimension(200, 60));
		layout.putConstraint(SpringLayout.WEST, powrot, 550, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, powrot, 380, SpringLayout.NORTH, this);
		zapisz1.setPreferredSize(new Dimension(200, 30));
		layout.putConstraint(SpringLayout.WEST, zapisz3, 550, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, zapisz3, 320, SpringLayout.NORTH, this);
		zapisz2.setPreferredSize(new Dimension(200, 30));
		layout.putConstraint(SpringLayout.WEST, zapisz2, 550, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, zapisz2, 280, SpringLayout.NORTH, this);
		zapisz3.setPreferredSize(new Dimension(200, 30));
		layout.putConstraint(SpringLayout.WEST, zapisz1, 550, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, zapisz1, 240, SpringLayout.NORTH, this);
		add(powrot);
		add(zapisz1);
		add(zapisz2);
		add(zapisz3);
		StworzPionki(kolor1, kolor2, zapis);
		pp=new PrzesunPionka();
		up=new UsunPionka();
		pp.addListener(this);
		up.addListener(this);

	}

	public void paint(Graphics g) {
		width = getWidth();
		height = getHeight();
		g.setColor(kolorTla);
		g.fillRect(0, 0, width, height);
		if (width > height) {
			rPola = (int) (0.09 * height);
			rOdstepu = (int) (0.09 * height);
			rPionka = (int) (rPola * 0.70);
		} else {
			rPola = (int) (0.09 * width);
			rOdstepu = (int) (0.09 * width);
			rPionka = (int) (rPola * 0.70);
		}
		g.setColor(kolor1);
		g.setFont(new Font("", Font.BOLD | Font.ITALIC, 30));
		g.drawString(gracz1t[jezykGra] + main.nazwa1, (int) (0.5 * width), (int) (0.87 * height));
		g.setColor(kolor2);
		g.drawString(gracz2t[jezykGra] + main.nazwa2, (int) (0.5 * width), (int) (0.94 * height));
		g.setColor(Color.BLACK);
		if (wygrana)
			g.drawString(wygranat[jezykGra], (int) (0.69 * width), (int) (0.18 * height));
		else
			g.drawString(turat[jezykGra], (int) (0.69 * width), (int) (0.18 * height));
		g.setColor(czyjruch);
		if (czyjruch == kolor1) {
			g.drawString(main.nazwa1, (int) (0.69 * width), (int) (0.3 * height));
		} else if (czyjruch == kolor2) {
			g.drawString(main.nazwa2, (int) (0.69 * width), (int) (0.3 * height));
		}
		g.setFont(new Font("", Font.BOLD | Font.ITALIC, 30));
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 8; j++) {

				if ((i + j) % 2 == 1)
					g.setColor(Color.BLUE);
				else
					g.setColor(Color.DARK_GRAY);
				g.fillRect(rOdstepu + rPola * i, rOdstepu + rPola * j, rPola, rPola);
			}
		g.setColor(Color.BLACK);
		g.setFont(new Font("", Font.BOLD | Font.ITALIC, (int) rOdstepu / 5));
		for (int i = 0; i < 9; i++)
			g.drawString(Character.toString((char) (i + 65)), (int) (rPola * i + 1.45 * rOdstepu),
					(int) (0.85 * rOdstepu));
		for (int i = 0; i < 8; i++)
			g.drawString(Integer.toString((i + 1)), (int) (0.7 * rOdstepu), (int) (rPola * i + 1.45 * rOdstepu));
		Graphics2D g2 = (Graphics2D) g;
		g.setFont(new Font("", Font.BOLD | Font.ITALIC, 60));
		g.drawString(main.plczas.getTime(), (int)(0.08*width), (int) (0.95*height) );
		if (aktualnyPionek >= 0) {
			PodswietlPionka(aktualnyPionek, g);

		}
		for (int i = 0; i < listapionkow.size(); i++) {
			g.setColor(listapionkow.get(i).kolor);
			g2.fillOval((rPola - rPionka) / 2 + rOdstepu + listapionkow.get(i).kolumna * rPola,
					(rPola - rPionka) / 2 + rOdstepu + listapionkow.get(i).rzad * rPola, rPionka, rPionka);
		}
	}

	// sprawdza czy podany pionek nie jest pionkiem atakujacym jesli jest to
	// wywoluje funkcje atakowany dla zaatakowanych pionkow
	public void sasiedzi(Pionek p) {
		int x = p.kolumna;
		int y = p.rzad;
		int nrp = czyZajete(x + 1, y);
		if (nrp > -1)
			atakowany(nrp, 1, 0);
		nrp = czyZajete(x, y - 1);
		if (nrp > -1)
			atakowany(nrp, 0, -1);
		nrp = czyZajete(x, y + 1);
		if (nrp > -1)
			atakowany(nrp, 0, 1);
		nrp = czyZajete(x - 1, y);
		if (nrp > -1)
			atakowany(nrp, -1, 0);
	}

	// przesuwa pionka o 2 pola w podanym kierunku (nalezy podac wartosci {-1,0,1}
	// dla x oraz y) i sprawdza czy atakowany pionek nie staje sie atakujacym
	public void atakowany(int p, int x, int y) {
		int nowek = listapionkow.get(p).kolumna + 2 * x;
		int nower = listapionkow.get(p).rzad + 2 * y;

		if (nowek > 8 || nowek < 0 || nower < 0 || nower > 7 || czyZajete(nowek, nower) != -1) {
			up.usunpionek(p);
		} else {
			pp.przesunpionek(p, nowek, nower);
		}

	}

	public void wygrana(int x) {
		System.out.println("wygral gracz: " + x + "\nGRATULACJE");
		// main.cardLayout.show(main.cardPanel, "menu");
		wygrana = true;

	}

	public void sprawdzKoniec(Pionek p) {
		if (p.kolor == kolor1) {
			if (p.rzad == 7)
				wygrana(1);
		}

		else {
			if (p.rzad == 0)
				wygrana(2);
		}
	}

	public int czyZajete(int x, int y) {
		for (int i = 0; i < listapionkow.size(); i++) {
			if (listapionkow.get(i).kolumna == x)
				if (listapionkow.get(i).rzad == y)
					return i;
		}
		return -1;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// System.out.println("POLE: " + Character.toString((char) ((e.getX() -
		// rOdstepu) / rPola + 65))
		// + (1 + (e.getY() - rOdstepu) / rPola));
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		main.plczas=new PlTime(main,main.plczas.getDate(),main.plczas.getTime());
		main.plczas.execute();
		if (!wygrana) {
			int x = e.getX();
			int y = e.getY();
			int nrx = (x - rOdstepu) / rPola;
			int nry = (y - rOdstepu) / rPola;
			boolean czypustepole = true;
			if (nrx < 9 && nry < 8) {
				for (int i = 0; i < listapionkow.size(); i++) {
					if (nrx == listapionkow.get(i).kolumna && nry == listapionkow.get(i).rzad) {
						czypustepole = false;
						if (czyjruch == listapionkow.get(i).kolor) {
							aktualnyPionek = i;
							main.log.info("wybrano pionka nr " + i);
						} else {
							main.log.warning("WrongPawnException(\"tura drugiego gracza\")");
							throw new WrongPawnException("tura drugiego gracza");
						}
					}
				}
				boolean temp = false;// czy prawidlowy ruch
				if (czypustepole && aktualnyPionek >= 0) {
					if (listapionkow.get(aktualnyPionek).kolor == kolor1) {
						if (listapionkow.get(aktualnyPionek).kolumna == (nrx + 1)
								|| listapionkow.get(aktualnyPionek).kolumna == (nrx - 1)) {
							if ((listapionkow.get(aktualnyPionek).rzad + 1) == nry)
								temp = true;
							if ((listapionkow.get(aktualnyPionek).rzad - 1) == nry) {
								if (czyZajete(nrx - 1, nry) >= 0)
									temp = true;
								if (czyZajete(nrx + 1, nry) >= 0)
									temp = true;
								if (czyZajete(nrx, nry - 1) >= 0)
									temp = true;
							}
							if (temp) {
								pp.przesunpionek(aktualnyPionek, nrx, nry);
								aktualnyPionek = -1;
								if (!wygrana) {
									AktualnyGracz(kolor2);
								}
							}
						}
						if (!temp) {
							main.log.warning("UnavaibleMoveException(\"ruch niedozwolony\")");
							throw new UnavaibleMoveException("ruch niedozwolony");
						}
					} else {
						if (listapionkow.get(aktualnyPionek).kolumna == (nrx + 1)
								|| listapionkow.get(aktualnyPionek).kolumna == (nrx - 1)) {
							if ((listapionkow.get(aktualnyPionek).rzad - 1) == nry)
								temp = true;
							if ((listapionkow.get(aktualnyPionek).rzad + 1) == nry) {
								if (czyZajete(nrx + 1, nry) >= 0)
									temp = true;
								if (czyZajete(nrx - 1, nry) >= 0)
									temp = true;
								if (czyZajete(nrx, nry + 1) >= 0)
									temp = true;
							}
							if (temp) {
								pp.przesunpionek(aktualnyPionek, nrx, nry);
								aktualnyPionek = -1;
								if (!wygrana)
									AktualnyGracz(kolor1);
							}
						}
						if (!temp) {
							main.log.warning("UnavaibleMoveException(\"ruch niedozwolony\")");
							throw new UnavaibleMoveException("ruch niedozwolony");
						}

					}
				}
				
			}
		}repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == powrot) {
			main.log.info("przejscie do menu");
			main.cardLayout.show(main.cardPanel, "menu");
		} else if (e.getSource() == zapisz1) {
			main.log.info("zapisanie gry zapis1");
			zapisz(1);
		} else if (e.getSource() == zapisz2) {
			main.log.info("zapisanie gry zapis2");
			zapisz(2);
		} else if (e.getSource() == zapisz3) {
			main.log.info("zapisanie gry zapis3");
			zapisz(3);
		}

	}

	public void zapisz(int zapis) {
		String SQL = "DELETE FROM pionek WHERE id_gra=" + zapis;

		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SQL)) {

		} catch (SQLException ex) {
			main.log.warning(ex.getMessage());
		}

		for (int i = 0; i < listapionkow.size(); i++) {
			Pionek temp = listapionkow.get(i);
			int pom;
			if (temp.kolor == kolor1)
				pom = 1;
			else
				pom = 2;
			SQL = "INSERT INTO pionek(poz_x, poz_y, czyjpionek, id_gra) VALUES (" + temp.kolumna + ", " + temp.rzad
					+ ", " + pom + ", " + zapis + ")";

			try (Connection conn = connect();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SQL)) {

			} catch (SQLException ex) {
				main.log.warning(ex.getMessage());
			}
		}
		int pom = 2;
		if (czyjruch == kolor1)
			pom = 1;
		SQL = "UPDATE gra SET id=" + zapis + ", wygrana=" + wygrana + ", czyjruch=" + pom + " WHERE id=" + zapis;

		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SQL)) {

		} catch (SQLException ex) {
			main.log.warning(ex.getMessage());
		}
	}

	public Connection connect() {
		wczytajxml();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			main.log.info("Connected to the PostgreSQL server successfully.");
		} catch (SQLException e) {
			main.log.warning(e.getMessage());
		}

		return conn;
	}

	public void wczytajxml() {
		try {
			File inputFile = new File("danebd.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("bazakonto");
			Node nNode = nList.item(0);
			Element eElement = (Element) nNode;
			url = eElement.getElementsByTagName("url").item(0).getTextContent();
			user = eElement.getElementsByTagName("user").item(0).getTextContent();
			password = eElement.getElementsByTagName("password").item(0).getTextContent();

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
	public void StworzPionki(Color c1, Color c2, int zapis) {
		if (zapis == 0) {
			listapionkow.add(new Pionek(c1, 1, 0));
			listapionkow.add(new Pionek(c1, 3, 0));
			listapionkow.add(new Pionek(c1, 5, 0));
			listapionkow.add(new Pionek(c1, 7, 0));
			listapionkow.add(new Pionek(c1, 2, 1));
			listapionkow.add(new Pionek(c1, 4, 1));
			listapionkow.add(new Pionek(c1, 6, 1));
			listapionkow.add(new Pionek(c1, 8, 1));
			listapionkow.add(new Pionek(c1, 0, 1));
			listapionkow.add(new Pionek(c2, 2, 6));
			listapionkow.add(new Pionek(c2, 4, 6));
			listapionkow.add(new Pionek(c2, 6, 6));
			listapionkow.add(new Pionek(c2, 8, 6));
			listapionkow.add(new Pionek(c2, 1, 7));
			listapionkow.add(new Pionek(c2, 3, 7));
			listapionkow.add(new Pionek(c2, 5, 7));
			listapionkow.add(new Pionek(c2, 7, 7));
			listapionkow.add(new Pionek(c2, 0, 6));
			pionki1 = 9;
			pionki2 = 9;
			AktualnyGracz(kolor1);
		} else {
			String SQL = "SELECT poz_x,poz_y, czyjpionek FROM pionek WHERE id_gra=" + zapis;
			pionki1 = 0;
			pionki2 = 0;
			try (Connection conn = connect();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SQL)) {
				while (rs.next()) {
					int poz_x = rs.getInt("poz_x");
					int poz_y = rs.getInt("poz_y");
					if (rs.getInt("czyjpionek") == 1) {
						listapionkow.add(new Pionek(c1, poz_x, poz_y));
						pionki1 += 1;
					} else {
						listapionkow.add(new Pionek(c2, poz_x, poz_y));
						pionki2 += 1;
					}
				}

			} catch (SQLException ex) {
				main.log.warning(ex.getMessage());
			}
			SQL = "SELECT czyjruch, wygrana FROM gra WHERE id=" + zapis;
			try (Connection conn = connect();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SQL)) {
				rs.next();
				if (rs.getInt("czyjruch") == 1)
					AktualnyGracz(kolor1);
				else
					AktualnyGracz(kolor2);
				if (rs.getBoolean("wygrana"))
					wygrana = true;
			} catch (SQLException ex) {
				main.log.warning(ex.getMessage());
			}

		}
	}

	
	public void PodswietlPionka(int pionek, Graphics g) {
		int pom = (int) (rPionka * 1.2);
		g.setColor(Color.RED);
		g.fillOval((rPola - pom) / 2 + rOdstepu + listapionkow.get(pionek).kolumna * rPola,
				(rPola - pom) / 2 + rOdstepu + listapionkow.get(pionek).rzad * rPola, pom, pom);
	}

	
	public void AktualnyGracz(Color x) {
		czyjruch = x;
	}

	@Override
	public void przesunpionka(PrzesunPionkaEvent e) {
		main.log.info(e.toString()+"przesuniecie pionka nr " + e.nrpionka + " na pole " + (char) (65 + e.x) + (e.y + 1));
		listapionkow.get(e.nrpionka).kolumna = e.x;
		listapionkow.get(e.nrpionka).rzad = e.y;
		sasiedzi(listapionkow.get(e.nrpionka));
		sprawdzKoniec(listapionkow.get(e.nrpionka));
		
	}

	@Override
	public void usunpionka(UsunPionkaEvent e) {
		if (listapionkow.get(e.i).kolor == kolor1) {
			pionki1 -= 1;
			main.log.info(e.toString()+"usunieto pionka nr " + e.i + "graczowi 1 pozostalo " + pionki1 + " pionkow");
			if (pionki1 == 0)
				wygrana(2);
		} else {
			pionki2 -= 1;
			main.log.info(e.toString()+"usunieto pionka nr " + e.i + "graczowi 2 pozostalo " + pionki2 + " pionkow");
			if (pionki2 == 0)
				wygrana(1);
		}
		listapionkow.remove(e.i);
		
	}

}