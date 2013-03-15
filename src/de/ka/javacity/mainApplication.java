package de.ka.javacity;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Bomb-Invaders
 */
public class mainApplication extends Application {

	// MainWindow
	@SuppressWarnings("unused")
	private Stage primaryStage;
	
	// Animation (GameLoop)
	private Timeline timeline;
	
	// KeyEvent Handler 
	private EventHandler<KeyEvent> keyEventHandler;

	/**
	 * Create Window
	 */
	@Override
	public void start(Stage primaryStage) {
		
		// Setup Stage
		primaryStage.setTitle("JavaCity");
		this.primaryStage = primaryStage;
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		
		primaryStage.setScene(createScene());
		primaryStage.show();
		
		// Game loop
		final Duration oneFrameDuration = Duration.millis(1000/60);
		final KeyFrame oneFrame = new KeyFrame(oneFrameDuration, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO GameLoop
			}
		});

		timeline = TimelineBuilder.create().cycleCount(Animation.INDEFINITE).keyFrames(oneFrame).build();
		timeline.play();

		initEventHandler(primaryStage);
	}

	
	/**
	 * Create Scene.
	 * 
	 * @return Scene
	 */
	private Scene createScene() {
		Group g = new Group();
		Scene scene = new Scene(g);
		return scene;
	}
	
	/**
	 * Start EventHandling
	 * @param primaryStage Hauptanzeigefenster.
	 */
	private void initEventHandler(Stage primaryStage) {
		// KeyHandler
		keyEventHandler = new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent keyEvent) {
				switch (keyEvent.getCode()) {
					default: System.out.println("KeyPressed");
				}
				keyEvent.consume();
			}
		};
			
		primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, keyEventHandler);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
