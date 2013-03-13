package de.ka.javacity;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class main extends Application {
    @Override public void start(Stage stage) {
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

        PointLight pointLight = new PointLight(Color.ANTIQUEWHITE);
        pointLight.setTranslateX(800);
        pointLight.setTranslateY(-100);
        pointLight.setTranslateZ(-1000);
 
        Group root = new Group(meshView);
        root.getChildren().add(pointLight);
         
        Scene scene = new Scene(root, 800, 600, true);
        scene.setFill(Color.rgb(10, 10, 40));
        scene.setCamera(new PerspectiveCamera(false));
        stage.setScene(scene);
        stage.show();
        
        // Game loop
        final Duration oneFrameAmt = Duration.millis(1000/60);
        @SuppressWarnings({ "rawtypes", "unchecked" })
		final KeyFrame oneFrame = new KeyFrame(oneFrameAmt,
           new EventHandler() {

			@Override
			public void handle(Event arg0) {
				// TODO: Game loop
			}
        });

        // sets the game world's game loop (Timeline)
        TimelineBuilder.create()
           .cycleCount(Animation.INDEFINITE)
           .keyFrames(oneFrame)
           .build()
           .play();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}