/* CRITTERS Critter.java
 * EE422C Project 5 submission by
 * Minh Van-Dinh
 * mv24772
 * 16475
 * Garrett Custer
 * gsc535
 * 16475
 * Slip days used: <0>
 * Git URL: https://github.com/mvandinh/mv24772_gsc535_Lab4
 * Fall 2016
 */

package assignment5;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import assignment5.Critter.CritterShape;

public class Painter {

	/*
	 * Returns a square or a circle, according to shapeIndex
	 */
	static Shape getIcon(CritterShape shapeIndex,javafx.scene.paint.Color fill, javafx.scene.paint.Color outline) {
		Shape s = null;
		int size = 20;
		
		switch(shapeIndex) {
		case SQUARE: s = new Rectangle(size, size); 
			s.setFill(fill); break;
		case CIRCLE: s = new Circle(size/2); 
			s.setFill(fill); break;
		case TRIANGLE: s = new Polygon();
		((Polygon) s).getPoints().addAll(new Double[]{
			    0.0, 0.0,
			    (double) size, (double) (size/2),
			    (double) size, 0.0 });
			s.setFill(fill);
		case DIAMOND: s = new Polygon();
		((Polygon) s).getPoints().addAll(new Double[]{
				(double) (size/2), 0.0,
			    (double) size, (double) (size/2),
			    (double) (size/2), (double) size,
			    0.0, (double) (size/2) });
			s.setFill(fill);
		case STAR: s = new Polygon();
		((Polygon) s).getPoints().addAll(new Double[]{
				(double) (size/5), (double) size,
			    (double) (size/2), (double) 0.0,
			    (double) (4*size/5), (double) size,
			    0.0, (double) (2*size/5),
			    (double) size, (double) (2*size/5)});
			s.setFill(fill);
		}
		// set the outline of the shape
		s.setStroke(outline); // outline
		return s;
	}
}
