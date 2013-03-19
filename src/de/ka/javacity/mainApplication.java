package de.ka.javacity;

import de.ka.javacity.game.BaseGame;
import de.ka.javacity.game.AbstractGame;
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
 * Main for JavaCity
 */
public class mainApplication extends Application {

	// MainWindow
	@SuppressWarnings("unused")
	private Stage primaryStage;

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
		game.startUp();

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

		// Initialize game loop
		final Duration oneFrameDuration = Duration.millis(1000 / 60);
		final KeyFrame oneFrame = new KeyFrame(oneFrameDuration,
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						// TODO GameLoop
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
