package de.ka.javacity.graphic.impl;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import de.ka.javacity.graphic.IView3D;

public class Blob3D extends Box implements IView3D {


	public Blob3D() {
		super(10, 10, 10);
		PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.GREEN);//rgb(r, g, b));
        material.setSpecularColor(Color.rgb(10, 50, 10));
        
		this.setMaterial(material);
        this.setDrawMode(DrawMode.FILL);
        this.setCullFace(CullFace.BACK);		
	}
	
	@Override
	public void draw(double x, double y, double z, double rx, double ry,
			double rz) {		
		this.relocate(x, y);
	}

}
