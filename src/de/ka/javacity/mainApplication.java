package de.ka.javacity;

import de.ka.javacity.game.AbstractGame;
import de.ka.javacity.game.impl.BaseGame;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Main for JavaCity
 */
public class mainApplication extends Application {

	// MainWindow
	@SuppressWarnings("unused")
	private Stage primaryStage;

	private Canvas canvas;

	// Animation (GameLoop)
	private Timeline timeline;

	// KeyEvent Handler
	private EventHandler<KeyEvent> keyEventHandler;

	// Game object
	private AbstractGame game;

	/**
	 * Create Window
	 */
	@Override
	public void start(Stage primaryStage) {

		// Setup Game
		this.game = new BaseGame();

		// Setup Stage
		this.primaryStage = primaryStage;
		primaryStage.setTitle(game.getTitle());
		primaryStage.setWidth(game.getWindow_width());
		primaryStage.setHeight(game.getWindow_height());
		primaryStage.setResizable(false);
		primaryStage.setFullScreen(game.isFullscreen());
		primaryStage.setX(50);
		primaryStage.setY(50);

		// Create Scene and show stage
		primaryStage.setScene(createScene());
		primaryStage.show();

		// startup game
		game.startUp();
		this.game.createTestBlob();
		
		// Initialize game loop
		final Duration oneFrameDuration = Duration.millis(1000 / 60);
		final KeyFrame oneFrame = new KeyFrame(oneFrameDuration,
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						// TODO GameLoop, update Systems
						game.update();
					}
				});

		timeline = TimelineBuilder.create().cycleCount(Animation.INDEFINITE)
				.keyFrames(oneFrame).build();
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

		this.canvas = new Canvas();
		canvas.setWidth(game.getWindow_width());
		canvas.setHeight(game.getWindow_height());
		g.getChildren().add(canvas);
		this.game.setCanvas(canvas);

		return scene;
	}

	/**
	 * Start EventHandling
	 * 
	 * @param primaryStage
	 *            Hauptanzeigefenster.
	 */
	private void initEventHandler(Stage primaryStage) {
		// KeyHandler
		keyEventHandler = new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent keyEvent) {
				switch (keyEvent.getCode()) {
				case ESCAPE:
					System.out.println("Exit Game.");
					System.exit(0);
					break;
				default:
					System.out.println("KeyPressed: "
							+ keyEvent.getCode().toString());
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
