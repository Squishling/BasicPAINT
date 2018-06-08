package co.uk.squishling.paint.objects;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public abstract class Option {
	double x;
	double y;
	String text;
	int size;
	Paint color2;
	
	public Option(double x, double y, int size, String text, Paint color2) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.text = text;
		this.color2 = color2;
	}
	
	public abstract void draw(GraphicsContext gc);
	
	public boolean clicked(double x, double y) {
		if (x > this.x - (size / 2) && x < (this.x - (size / 2)) + size && y > this.y - (size / 2) && y < (this.y - (size / 2)) + size) {
			return true;
		}
		
		return false;
	}
}
