package de.ka.javacity;

import de.ka.javacity.cam.GameCamera;
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
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
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

	// FPS stuff
	private double fps = 60;
	private double FPS = 60;
	private int fpsCount = 0;
	private int SAMPLE_SIZE = 30;
	private long startTime;
	private long endTime;
	private long diffTime = 1000L / 60L;
	private double displayFPS = 0.0;
	private double SPRINGNESS = 0.5;
	private Text fpsText;

	// blob counter
	private int blobCount = 0;
	private Text blobText;

	private GameCamera gameCam;

	/**
	 * Create Window
	 */
	@Override
	public void start(final Stage primaryStage) {

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
		Scene primaryScene = createScene();
		primaryStage.setScene(primaryScene);
		primaryStage.show();
		
		gameCam.init(primaryStage, primaryScene);

		// startup game
		game.startUp();

		// fps
		startTime = java.lang.System.currentTimeMillis();

		// Initialize game loop
		Duration oneFrameDuration = Duration.millis(1000.0 / FPS);
		KeyFrame oneFrame = new KeyFrame(oneFrameDuration,
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if (fps < FPS-5) {
							timeline.stop();
						}
						
						// update Systems
						game.update();
						
						// calculate and display FPS
						fpsCount++;
						if (fpsCount > SAMPLE_SIZE) {
							endTime = java.lang.System.currentTimeMillis();
							diffTime = (endTime - startTime);
							fps = fpsCount / (diffTime / 1000.0);
							fpsCount = 0;
							startTime = java.lang.System.currentTimeMillis();
							displayFPS += (fps - displayFPS) * SPRINGNESS;
						}

						primaryStage.setTitle(game.getTitle() + "FPS: "+ String.valueOf(Math.floor(displayFPS)));
						
						timeline.play();
					}
				});

//		timeline = TimelineBuilder.create().cycleCount(Animation.INDEFINITE)
//				.keyFrames(oneFrame).build();
		timeline = new Timeline(FPS);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.getKeyFrames().add(oneFrame);
		
		timeline.play();

		initEventHandler(primaryStage);
	}

	/**
	 * Create Scene.
	 * 
	 * @return Scene
	 */
	private Scene createScene() {
		// Setup Camera
    	gameCam = new GameCamera();
    	
    	// Create new Scene
    	Scene scene = new Scene(gameCam.getCamOffset(), 800, 800, true);
        scene.setFill(Color.rgb(10, 10, 40));
        scene.setCamera(new PerspectiveCamera());
        
        // Setup 3d Objects
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.LIGHTGRAY);
        material.setSpecularColor(Color.rgb(30, 30, 30));

        Shape3D[] meshView = new Shape3D[] {
            new Box(200, 200, 200),
            new Sphere(100),
            new Cylinder(100, 200),
        };

        for (int i=0; i!=3; ++i) {
            meshView[i].setMaterial(material);
            meshView[i].setTranslateX((i + 1) * 220);
            meshView[i].setTranslateY(500);
            meshView[i].setTranslateZ(20);
            meshView[i].setDrawMode(DrawMode.FILL);
            meshView[i].setCullFace(CullFace.BACK);
        };
        
        // Setup light
        PointLight pointLight = new PointLight(Color.ANTIQUEWHITE);
        pointLight.setTranslateX(800);
        pointLight.setTranslateY(-100);
        pointLight.setTranslateZ(-1000);

        Group root = new Group(meshView);
        gameCam.addNodes(root);
        gameCam.addNodes(pointLight);
        
		// Setup FPS & blob count
		this.fpsText = new Text(550, 50, "FPS: "
				+ String.valueOf(this.displayFPS));

		gameCam.addNodes(this.fpsText);

		// Set game-canvas
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
