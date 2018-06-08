package co.uk.squishling.paint.objects;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ColorOption extends Option {
	Paint color;
	
	public ColorOption(double x, double y, int size, String text, Paint color, Paint color2) {
		super(x, y, size, text, color2);
		this.color = color;
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(color2);
		gc.fillRect(x - (size / 2), y - (size / 2), size, size);
		
		gc.setFill(color);
		gc.fillRect(x - ((size / 2) - 5), y - ((size / 2) - 5), size - 10, size - 10);
		
		gc.setFill(color2);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFont(new Font("Arial", 30));
		gc.fillText(text, x, y);
	}
	
	public Paint getColor() {
		return color;
	}
}
