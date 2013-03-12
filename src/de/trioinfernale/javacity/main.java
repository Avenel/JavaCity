package de.trioinfernale.javacity;
import javafx.application.Application;
import javafx.stage.Stage;


public class main extends Application{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
		System.out.println("Hello World");
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("JavaCity - v.0.1a");
		stage.setWidth(800);
		stage.setHeight(600);
		
		stage.show();
	}
	

}
