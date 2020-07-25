package break_out.controller;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import break_out.Constants;

/**
 * Der <code>JSONReader</code> liest eine JSON-Datei ein.
 * 
 * @author 
 *
 */
public class JSONReader {

	/**
	 * Der Pfad der JSON-Datei
	 */
	private String path;

	/**
	 * Die Steine als List<List<Long>>
	 */
	private List<List<Long>> rects = new ArrayList<List<Long>>();
		
	/**
	 * Die Steine als zweidimensionales Array 
	 * (rml: eckige Steine heißen im Englischen "bricks")
	 */
	private int[][] stones = new int[Constants.SQUARES_Y][Constants.SQUARES_X];
	
	/**
	 * Der Zähler, der anzeigt, wie oft man einen Stein treffen muss, damit er verschwindet.
	 */
	private Long lifecount = null;
	
	/**
	 * Der Konstruktor benötigt einen Dateipfad, um den JSONReader
	 * zu initialisieren.
	 * 
	 * @param path
	 *            Der absolute Pfad der JSON-Datei
	 */
	public JSONReader(String path) {
		this.path = path;
		loadJsonValues();
	}

	/**
	 * Getter für die Steine, die in der JSON-Datei beschrieben sind.
	 * 
	 * @return eine List<List<Long>> der Steine
	 */
	public List<List<Long>> getStonesListOfLists() {
		return rects;
	}
	
	/**
	 * Getter für die Steine, die in der JSON-Datei beschrieben sind.
	 * 
	 * @return Die Steine als zweidimensionales Array.
	 */
	public int[][] getStones2DArray() {	
		//Zeile
		for (int i = 0; i < rects.size(); i++) {
			//Spalte
			for (int j = 0; j < rects.get(i).size(); j++) {
				// Zelle
				stones[i][j] = (int)rects.get(i).get(j).longValue();
			}
		}
		return stones;
	}
	
	/**
	 * Getter für den lifeCounter, wie in der JSON-Datei festgelegt
	 * 
	 * @return den lifeCounter
	 */
	public int getLifeCounter() {
		return (int)lifecount.longValue();
	}
	
	/**
	 * Diese Methode lädt Werte sowohl für "fields" und "maxDrops" aus der JSON-Datei.
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void loadJsonValues() {
		JSONParser parser = new JSONParser();
		try {
			// 
			Object obj = parser.parse(new FileReader(path));

			String jsonStr = obj.toString();
			JSONObject json = (JSONObject) JSONValue.parse(jsonStr);
			// rects sind die Rechtecke, aus denen das Spielfeld besteht (Die roten Zellen)
			rects = (List<List<Long>>) json.get("field");
			lifecount = (Long)json.get("maxLives");

		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
