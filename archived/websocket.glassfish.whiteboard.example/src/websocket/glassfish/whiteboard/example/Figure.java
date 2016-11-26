package websocket.glassfish.whiteboard.example;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

import websocket.glassfish.whiteboard.core.Coordinates;

public class Figure {
	
	private JsonObject json;	
	private Coordinates coordinates;

	public Figure(JsonObject json) {
		this.json = json;
		coordinates = new Coordinates(100, 100);
		System.out.println("Coordinates from core: " + coordinates);
	}

	public JsonObject getJson() {
		return json;
	}

	public void setJson(JsonObject json) {
		this.json = json;
	}
	
	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		Json.createWriter(writer).write(json);
		String s= writer.toString();
		return json.toString();
	}

}
