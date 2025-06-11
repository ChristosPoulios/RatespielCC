package de.bbq;

import java.util.Random;
import java.util.Scanner;

/**
 * @Autor Dr. Tikko
 * 

 *        Ein Spiel, in dem das Programm eine Zahl ausgibt und der Spieler versuch sie zu erraten.
 *        
 */
public class RatespielCC {

	// Konstanten
	private static final String SPIEL_GESTARTET_PREFIX = "Ich habe eine Zahl zwischen ";
	private static final String SPIEL_GESTARTET_SUFFIX = " ausgedacht. Rate sie!\n";
	private static final String ZAHL_EINGEBEN = "Gebe die Zahl ein und drücke die Enter-Taste\n";
	private static final String RICHTIG = "Richtig!\n";
	private static final String FALSCH = "Falsch! Versuch es noch mal!\n";
	private static final String NOCH_MAL = "Noch mal spielen? Wenn ja, tippe 1 (und Enter), sonst egal was (und Enter).\n";
	private static final String TSCHUESS = "Tschüss! Bis zum nächsten Mal :-)";
	private static final String JA = "1";
	private static final String KEINE_ZAHL = "Das war keine Zahl. Interpretiere als ";
	private static final String WILLKOMMEN = "Willkommen zum Ratespiel!\n";
	private static final String FRAGE_UNTERGRENZE = "Bitte gebe deine Untergrenze ein: ";
	private static final String FRAGE_OBERGRENZE = "Bitte geben deine Obergrenze ein: ";
	private static final String FALSCHE_OBERGRENZE = "Die Obergrenze muss kleiner als die Untergrenze sein. Bitte versuche es noch einmal.\n";

	private int meineZahl;
	Random zufall = new Random();
	Scanner scan = new Scanner(System.in);
	private int obergrenze = -1;
	private int untergrenze = -1;
	int counter = 1;

	/**
	 * Standardkonstruktor
	 */
	public RatespielCC() {
		super();
		starteEinSpiel();
	}

	/**
	 * In dieser Methode wird das eigentliche Spiel gestartet.
	 */
	private void starteEinSpiel() {
		ausgabe(WILLKOMMEN);
		setzeGrenzen();
		meineZahl = ausdenke();
		System.out.println(meineZahl);
		ausgabe(erstelleStartNachricht());
		spieleEineRunde();
	}

	/**
	 * Hier wird eine komplette Spielrunde absolviert.
	 */
	private void spieleEineRunde() {

		ausgabe(ZAHL_EINGEBEN);
		int zahl = zahlEinlesen();

		if (zahl == meineZahl) {
			ausgabe(RICHTIG);
			ausgabe("Du hast " + (counter - 1) + " Versuche gebraucht.");
			wiederholeDasSpiel();
		} else {
			ausgabe(FALSCH);
			counter++;
			spieleEineRunde();
		}
	}

	/**
	 * Hier wird evaluiert, ob der Spieler noch mal spelen möchte. Davon hängt die
	 * weitere Vorgehensweise ab.
	 */
	private void wiederholeDasSpiel() {

		ausgabe(NOCH_MAL);
		String antwort = textEinlesen();

		if (antwort.equals(JA))
			starteEinSpiel();
		else
			verabschiede();
	}

	/**
	 * Hier wird das Spiel beendet. Wir denken vorausschauend: Hier können
	 * eventuelle weitere Aufgaben erledigt werden, z.B. speichern
	 */
	private void verabschiede() {
		ausgabe(TSCHUESS);
		scan.close();
	}

	/**
	 * Hier werden die Grenzen des Ratespiels gesetzt. Der Spieler gibt die
	 * Unter- und Obergrenze ein. Die Obergrenze muss dabei größer als die
	 * Untergrenze sein.
	 */
	private void setzeGrenzen() {
		ausgabe(FRAGE_UNTERGRENZE);
		untergrenze = zahlEinlesen();
		ausgabe(FRAGE_OBERGRENZE);
		obergrenze = zahlEinlesen();

		if (obergrenze <= untergrenze) {
			ausgabe(FALSCHE_OBERGRENZE);
			setzeGrenzen();
        }
	}

	/**
	 * Gibt den Text "Ich habe eine Zahl zwischen Untergrenze und Obergrenze
	 * ausgedacht. Rate sie!" aus.
	 */
	private String erstelleStartNachricht() {
		return SPIEL_GESTARTET_PREFIX + untergrenze + " und " + obergrenze + SPIEL_GESTARTET_SUFFIX;
	}

	/**
	 * Generiert eine Zufalszahl aus
	 * 
	 * @return
	 */
	private int ausdenke() {
		return zufall.nextInt(untergrenze, obergrenze + 1);
	}

	/**
	 * Liest die Benutzereingabe als Text ein
	 * 
	 * @return Text
	 */
	private String textEinlesen() {
		return scan.next();
	}

	/**
	 * Liest die Benutzereingabe als eine Zahl ein
	 * 
	 * @return eine Zahl, wenn die Eingabe eine Zahl war, char-Wert entsprechend dem
	 *         ersten Zeichen der Eingabe
	 */
	private int zahlEinlesen() {
		String eingabe = textEinlesen();
		int zahl;
		try {
			zahl = Integer.valueOf(eingabe);
		} catch (NumberFormatException e) {
			zahl = eingabe.charAt(0);
			ausgabe(KEINE_ZAHL + zahl);
		}
		return zahl;
	}

	/**
	 * Gibt einen Text auf die Konsole aus
	 * 
	 * @param text auszugebene Text
	 */
	private void ausgabe(String text) {
		System.out.println(text);
	}

	/**
	 * Hier startet das Programm.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		new RatespielCC();
	}

}
