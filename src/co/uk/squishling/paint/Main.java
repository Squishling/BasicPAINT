package co.uk.squishling.paint;

import java.util.ArrayList;

import co.uk.squishling.paint.objects.ColorOption;
import co.uk.squishling.paint.objects.SizeOption;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Main extends Application {

	private Pane root;
	private Scene scene;
	private Canvas canvas;
	
	private double WIDTH;
	private double HEIGHT;
	
	private double lastX = 0;
	private double lastY = 0;
	
	private Paint drawColor = Color.BLACK;
	private int drawSize = 10;
	
	private ArrayList<ColorOption> colorOptions = new ArrayList<ColorOption>();
	private ArrayList<SizeOption> sizeOptions = new ArrayList<SizeOption>();
	
	public void start(Stage stage) {
		try {
			root = new Pane();
			
			scene = new Scene(root, 1, 1);
			
			stage.setScene(scene);
			stage.setTitle("BasicPAINT");
			stage.setFullScreen(true);
			stage.setResizable(false);
			stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
			
			stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent e) -> {
		        if (KeyCode.ESCAPE == e.getCode()) {
		            stage.close();
		        }
		    });
			
//			stage.getIcons().add(new Image("/icon.png"));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		WIDTH = root.getWidth();
		HEIGHT = root.getHeight();
		
		colorOptions.add(new ColorOption(((WIDTH / 15) / 2), ((WIDTH / 15) / 2), (int) WIDTH / 20, "Black", Color.BLACK, Color.WHITE));
		colorOptions.add(new ColorOption(((WIDTH / 15) / 2), (((WIDTH / 15) / 2) * 2) + 40, (int) WIDTH / 20, "Red", Color.RED, Color.WHITE));
		colorOptions.add(new ColorOption(((WIDTH / 15) / 2), (((WIDTH / 15) / 2) * 3) + 80, (int) WIDTH / 20, "Green", Color.GREEN, Color.WHITE));
		colorOptions.add(new ColorOption(((WIDTH / 15) / 2), (((WIDTH / 15) / 2) * 4) + 120, (int) WIDTH / 20, "Blue", Color.BLUE, Color.WHITE));
		colorOptions.add(new ColorOption(((WIDTH / 15) / 2), (((WIDTH / 15) / 2) * 5) + 160, (int) WIDTH / 20, "Brown", Color.SADDLEBROWN, Color.BLACK));
		colorOptions.add(new ColorOption(((WIDTH / 15) / 2), (((WIDTH / 15) / 2) * 6) + 200, (int) WIDTH / 20, "Yellow", Color.YELLOW, Color.BLACK));
		colorOptions.add(new ColorOption(((WIDTH / 15) / 2), (((WIDTH / 15) / 2) * 7) + 240, (int) WIDTH / 20, "Aqua", Color.AQUA, Color.BLACK));
		colorOptions.add(new ColorOption(((WIDTH / 15) / 2), (((WIDTH / 15) / 2) * 8) + 280, (int) WIDTH / 20, "Lime", Color.LIME, Color.BLACK));
		colorOptions.add(new ColorOption(((WIDTH / 15) / 2), HEIGHT - ((WIDTH / 15) / 2), (int) WIDTH / 20, "White", Color.WHITE, Color.BLACK));
		
		sizeOptions.add(new SizeOption(WIDTH - ((WIDTH / 15) / 2), ((WIDTH / 15) / 2), (int) WIDTH / 20, "100", 100, Color.WHITE));
		sizeOptions.add(new SizeOption(WIDTH - ((WIDTH / 15) / 2), (((WIDTH / 15) / 2) * 2) + 40, (int) WIDTH / 20, "50", 50, Color.WHITE));
		sizeOptions.add(new SizeOption(WIDTH - ((WIDTH / 15) / 2), (((WIDTH / 15) / 2) * 3) + 80, (int) WIDTH / 20, "35", 35, Color.WHITE));
		sizeOptions.add(new SizeOption(WIDTH - ((WIDTH / 15) / 2), (((WIDTH / 15) / 2) * 4) + 120, (int) WIDTH / 20, "25", 25, Color.WHITE));
		sizeOptions.add(new SizeOption(WIDTH - ((WIDTH / 15) / 2), (((WIDTH / 15) / 2) * 5) + 160, (int) WIDTH / 20, "15", 15, Color.WHITE));
		sizeOptions.add(new SizeOption(WIDTH - ((WIDTH / 15) / 2), (((WIDTH / 15) / 2) * 6) + 200, (int) WIDTH / 20, "10", 10, Color.WHITE));
		sizeOptions.add(new SizeOption(WIDTH - ((WIDTH / 15) / 2), (((WIDTH / 15) / 2) * 7) + 240, (int) WIDTH / 20, "5", 5, Color.WHITE));
		sizeOptions.add(new SizeOption(WIDTH - ((WIDTH / 15) / 2), (((WIDTH / 15) / 2) * 8) + 280, (int) WIDTH / 20, "3", 3, Color.WHITE));
		sizeOptions.add(new SizeOption(WIDTH - ((WIDTH / 15) / 2), (((WIDTH / 15) / 2) * 9) + 320, (int) WIDTH / 20, "1", 1, Color.WHITE));
		
		canvas = new Canvas(scene.getWidth(), scene.getHeight());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		final long startNanoTime = System.nanoTime();
		 
	    new AnimationTimer() {
	        public void handle(long currentNanoTime) {
	            double t = (currentNanoTime - startNanoTime) / 1000000000.0;
	            
	            gc.setFill(Color.GREY);
	            gc.fillRect(0, 0, WIDTH / 15, HEIGHT);
	            gc.fillRect(WIDTH - (WIDTH / 15), 0, WIDTH / 15, HEIGHT);
	            
	            for (int i = 0; i < colorOptions.size(); i++) {
	            	colorOptions.get(i).draw(gc);
	            }
	            
	            for (int i = 0; i < sizeOptions.size(); i++) {
	            	sizeOptions.get(i).draw(gc);
	            }
	        }
	    }.start();
		
		canvas.setOnMouseClicked((MouseEvent e) -> {
			for (int i = 0; i < colorOptions.size(); i++) {
				if (colorOptions.get(i).clicked(e.getX(), e.getY())) {
					drawColor = colorOptions.get(i).getColor();
				}
			}
			
			for (int i = 0; i < sizeOptions.size(); i++) {
				if (sizeOptions.get(i).clicked(e.getX(), e.getY())) {
					drawSize = sizeOptions.get(i).getSize();
				}
			}
		});
		
		canvas.setOnMousePressed((MouseEvent e) -> {
			lastX = e.getX();
			lastY = e.getY();
		});
		
		canvas.setOnMouseDragged((MouseEvent e) -> {
        	if (e.isPrimaryButtonDown()) {
        		if (lastX == 0) {
            		lastX = e.getX();
            	}
            	
            	if (lastY == 0) {
            		lastY = e.getY();
            	}
            	
            	gc.setStroke(drawColor);
            	gc.setLineWidth(drawSize);
            	
                gc.moveTo(lastX, lastY);
                gc.lineTo(e.getX(), e.getY());
                   
                gc.stroke();
                    
                lastX = e.getX();
                lastY = e.getY();
        	}
        });
		
		root.getChildren().add(canvas);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
