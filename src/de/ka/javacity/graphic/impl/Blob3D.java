package de.ka.javacity.graphic.impl;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import de.ka.javacity.graphic.IView3D;

public class Blob3D extends Sphere implements IView3D {


	public Blob3D() {
		super(10);
		PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.LIGHTGRAY);
        material.setSpecularColor(Color.rgb(30, 30, 30));
        
		this.setMaterial(material);
        this.setTranslateX(220);
        this.setTranslateY(500);
        this.setTranslateZ(20);
        this.setDrawMode(DrawMode.FILL);
        this.setCullFace(CullFace.BACK);		
	}
	
	@Override
	public void draw(double x, double y, double z, double rx, double ry,
			double rz) {		
		this.relocate(x, y);
	}

}
