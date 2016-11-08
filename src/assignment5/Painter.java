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

	
	static int size = 20;
	/*
	 * Returns a square or a circle, according to shapeIndex
	 */
	static void Paint(CritterShape shapeIndex,javafx.scene.paint.Color fill, javafx.scene.paint.Color outline, int x_coord, int y_coord) {
		Shape s = null;
		
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
		Main.grid.add(s, x_coord, y_coord + 2); // add the shape to the grid.
		return;
	}
	public static void blankgrid(){
		for(int i = 0; i < Params.world_width; i++){
			for(int j = 0; j < Params.world_height; j++){
				Main.grid.add(new Rectangle(size, size), i, j + 2);
			}
		}
	}
}
