/* CRITTERS Critter1.java
 * EE422C Project 5 submission by
 * Minh Van-Dinh
 * mv24772
 * 16475
 * Garrett Custer
 * gsc535c
 * 16475
 * Slip days used: <0>
 * Git URL: https://github.com/mvandinh/mv24772_gsc535_Lab5
 * Fall 2016
 */

package assignment5;

/*
 * Example critter
 */
public class Critter1 extends Critter {
	
	@Override
	public String toString() { return "1"; }
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	public Critter1() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}
	
	public boolean fight(String not_used) {
		/* 50 - 50 chance to fight */
		if(Critter.getRandomInt(1) == 1){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public void doTimeStep() {
		/* never does anything, waits for others to come */
		
		if (getEnergy() > 150) {
			Critter1 child = new Critter1();
			for (int k = 0; k < 8; k += 1) {
				child.genes[k] = this.genes[k];
			}
			int g = Critter.getRandomInt(8);
			while (child.genes[g] == 0) {
				g = Critter.getRandomInt(8);
			}
			child.genes[g] -= 1;
			g = Critter.getRandomInt(8);
			child.genes[g] += 1;
			reproduce(child, Critter.getRandomInt(8));
		}
		
		/* pick a new direction based on our genes */
		int roll = Critter.getRandomInt(GENE_TOTAL);
		int turn = 0;
		while (genes[turn] <= roll) {
			roll = roll - genes[turn];
			turn = turn + 1;
		}
		assert(turn < 8);
		
		dir = (dir + turn) % 8;
	}

	public static void runStats(java.util.List<Critter> critter1) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : critter1) {
			Critter1 c = (Critter1) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		String output = new String();
		output = critter1.size() + " total Craig    ";
		output += (Math.round(total_straight / (GENE_TOTAL * 0.01 * critter1.size()) * 100) / 100.00) + "% straight   ";
		output += (Math.round(total_back / (GENE_TOTAL * 0.01 * critter1.size()) * 100) / 100.00) + "% back   ";
		output += (Math.round(total_right / (GENE_TOTAL * 0.01 * critter1.size()) * 100) / 100.00) + "% right   ";
		output += (Math.round(total_left / (GENE_TOTAL * 0.01 * critter1.size()) * 100) / 100.00) + "% left   ";
		Main.results.setText(output);
	}

	@Override
	public CritterShape viewShape() {
		return(CritterShape.STAR);
	}
	public javafx.scene.paint.Color viewColor() {
		return javafx.scene.paint.Color.RED;
	}
	public javafx.scene.paint.Color viewOutlineColor() { 
		return viewColor(); 
	}
	public javafx.scene.paint.Color viewFillColor() { 
		return javafx.scene.paint.Color.ORANGE; 
	}
}