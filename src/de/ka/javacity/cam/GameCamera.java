package de.ka.javacity.cam;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

/**
    Represents a camera movement like in an ego perspective shooter.
**/
public class GameCamera {

    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;
    private double mouseDeltaX;
    private double mouseDeltaY;

    private Cam camOffset;
    private Cam cam;
    
    /**
        Innerclass for an extended camera
    **/
    class Cam extends Group {
        private Translate t  = new Translate();
        private Translate p  = new Translate();
        private Translate ip = new Translate();
        private Rotate rx = new Rotate();
        { rx.setAxis(Rotate.X_AXIS); }
        
        private Rotate ry = new Rotate();
        { ry.setAxis(Rotate.Y_AXIS); }
        
        private Rotate rz = new Rotate();
        { rz.setAxis(Rotate.Z_AXIS); }
        
        private Scale s = new Scale();
        
        public Cam() { 
            super(); 
            getTransforms().addAll(t, p, rx, rz, ry, s, ip); 
        }
    }

    /**
        Public constructor.
    **/
    public GameCamera() {
        camOffset = new Cam();
        cam = new Cam();
    }

    /**
        Initial Setup 
    **/
    public void init(Stage stage, Scene scene) {
        double halfSceneWidth = scene.getWidth()/2.0;
        double halfSceneHeight = scene.getHeight()/2.0;
        cam.p.setX(halfSceneWidth);
        cam.ip.setX(-halfSceneWidth);
        cam.p.setY(halfSceneHeight);
        cam.ip.setY(-halfSceneHeight);

        frameCam(stage, scene);
        camOffset.getChildren().add(cam);
        resetCam();

        // Adding EventHandlers
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                mousePosX = me.getX();
                mousePosY = me.getY();
                mouseOldX = me.getX();
                mouseOldY = me.getY();
            }
        });
        
        // ZOOM
        scene.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent arg0) {
                double scale = cam.s.getX();
                double newScale = scale + arg0.getDeltaY()*0.01;
               
                if (newScale >= 0) {
                    cam.s.setX(newScale);
                    cam.s.setY(newScale);
                    cam.s.setZ(newScale);
                }
            }
            
        });
        
        // ROTATE
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getX();
                mousePosY = me.getY();
                mouseDeltaX = mousePosX - mouseOldX;
                mouseDeltaY = mousePosY - mouseOldY;
                if (me.isAltDown() && me.isPrimaryButtonDown()) {
                    double rzAngle = cam.rz.getAngle();
                    cam.rz.setAngle(rzAngle - mouseDeltaX);
                }
                else if (me.isPrimaryButtonDown()) {
                    double ryAngle = cam.ry.getAngle();
                    cam.ry.setAngle(ryAngle - mouseDeltaX);
                    double rxAngle = cam.rx.getAngle();
                    cam.rx.setAngle(rxAngle + mouseDeltaY);
                }
            }
        });
        
        // MOVE
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (KeyCode.W.equals(ke.getCode())) {
                    cam.p.setZ(cam.p.getZ() - (Math.cos(cam.rz.getAngle())*10.0));
                }
                if (KeyCode.S.equals(ke.getCode())) {
                    cam.p.setZ(cam.p.getZ() + (Math.cos(cam.rz.getAngle())*10.0));
                }
                if (KeyCode.A.equals(ke.getCode())) {
                    cam.p.setX(cam.p.getX() + 10.0);
                }
                if (KeyCode.D.equals(ke.getCode())) {
                    cam.p.setX(cam.p.getX() - 10.0);
                }
            }
        });

    }

    /**
        frameCam
    **/
    public void frameCam(final Stage stage, final Scene scene) {
        setCamOffset(camOffset, scene);
        // cam.resetTSP();
        setCamPivot(cam);
        setCamTranslate(cam);
        setCamScale(cam, scene);
    }

    /**
        setCamOffset
    **/
    public void setCamOffset(final Cam camOffset, final Scene scene) {
        double width = scene.getWidth();
        double height = scene.getHeight();
        camOffset.t.setX(width/2.0);
        camOffset.t.setY(height/2.0);
    }

    /**
        setCamScale
    **/
    public void setCamScale(final Cam cam, final Scene scene) {
        final Bounds bounds = cam.getBoundsInLocal();

        double width = scene.getWidth();
        double height = scene.getHeight();

        double scaleFactor = 1.0;
        double scaleFactorY = 1.0;
        double scaleFactorX = 1.0;
        if (bounds.getWidth() > 0.0001) {
            scaleFactorX = width / bounds.getWidth(); // / 2.0;
        }
        if (bounds.getHeight() > 0.0001) {
            scaleFactorY = height / bounds.getHeight(); //  / 1.5;
        }
        if (scaleFactorX > scaleFactorY) {
            scaleFactor = scaleFactorY;
        } else {
            scaleFactor = scaleFactorX;
        }
        cam.s.setX(scaleFactor);
        cam.s.setY(scaleFactor);
        cam.s.setZ(scaleFactor);
    }


    /**
        setCamPivot
    **/
    public void setCamPivot(final Cam cam) {
        final Bounds bounds = cam.getBoundsInLocal();
        final double pivotX = bounds.getMinX() + bounds.getWidth()/2;
        final double pivotY = bounds.getMinY() + bounds.getHeight()/2;
        final double pivotZ = bounds.getMinZ() + bounds.getDepth()/2;
        cam.p.setX(pivotX);
        cam.p.setY(pivotY);
        cam.p.setZ(pivotZ);
        cam.ip.setX(-pivotX);
        cam.ip.setY(-pivotY);
        cam.ip.setZ(-pivotZ);
    }

    /**
        setCamTranslate
    **/
    public void setCamTranslate(final Cam cam) {
        final Bounds bounds = cam.getBoundsInLocal();
        final double pivotX = bounds.getMinX() + bounds.getWidth()/2;
        final double pivotY = bounds.getMinY() + bounds.getHeight()/2;
        cam.t.setX(-pivotX);
        cam.t.setY(-pivotY);
    }

    /**
        resetCam
    **/
    public void resetCam() {
        cam.t.setX(0.0);
        cam.t.setY(0.0);
        cam.t.setZ(0.0);
        cam.rx.setAngle(45.0);
        cam.ry.setAngle(-7.0);
        cam.rz.setAngle(0.0);
        cam.s.setX(1.25);
        cam.s.setY(1.25);
        cam.s.setZ(1.25);


        cam.p.setX(0.0);
        cam.p.setY(0.0);
        cam.p.setZ(0.0);

        cam.ip.setX(0.0);
        cam.ip.setY(0.0);
        cam.ip.setZ(0.0);

        final Bounds bounds = cam.getBoundsInLocal();
        final double pivotX = bounds.getMinX() + bounds.getWidth() / 2;
        final double pivotY = bounds.getMinY() + bounds.getHeight() / 2;
        final double pivotZ = bounds.getMinZ() + bounds.getDepth() / 2;

        cam.p.setX(pivotX);
        cam.p.setY(pivotY);
        cam.p.setZ(pivotZ);

        cam.ip.setX(-pivotX);
        cam.ip.setY(-pivotY);
        cam.ip.setZ(-pivotZ);
    }
    
    /**
     * Adds another node.
     * @param newNode
     */
    public void addNodes(Node newNode) {
        this.cam.getChildren().add(newNode);
    }
    
    
    public Cam getCamOffset() {
    	return this.camOffset;
    }
}
