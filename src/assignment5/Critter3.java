/* CRITTERS Critter3.java
 * EE422C Project 5 submission by
 * Minh Van-Dinh
 * mv24772
 * 16475
 * Garrett Custer
 * gsc535
 * 16475
 * Slip days used: <0>
 * Git URL: https://github.com/mvandinh/mv24772_gsc535_Lab5
 * Fall 2016
 */

package assignment5;

/*
 * Critter3 always runs during time steps
 * Critter3 always attempts to fight during an encounter
 * Critter3 never has babies to conserve its energy
 */
public class Critter3 extends Critter {
	
	@Override
	public String toString() { return "3"; }
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	public Critter3() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}
	
	public boolean fight(String not_used) {
		return true;
	}

	@Override
	public void doTimeStep() {
		String check = new String();
		check = look(dir, true);
		if (check == null) {
			run(dir);
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

	public static String runStats(java.util.List<Critter> critter3) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : critter3) {
			Critter3 c = (Critter3) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		String output = new String();
		output = critter3.size() + " total Critter3    ";
		output += (Math.round(total_straight / (GENE_TOTAL * 0.01 * critter3.size()) * 100) / 100.00) + "% straight   ";
		output += (Math.round(total_back / (GENE_TOTAL * 0.01 * critter3.size()) * 100) / 100.00) + "% back   ";
		output += (Math.round(total_right / (GENE_TOTAL * 0.01 * critter3.size()) * 100) / 100.00) + "% right   ";
		output += (Math.round(total_left / (GENE_TOTAL * 0.01 * critter3.size()) * 100) / 100.00) + "% left   ";
		return output;
	}

	@Override
	public CritterShape viewShape() {
		return(CritterShape.TRIANGLE);
	}
	public javafx.scene.paint.Color viewColor() {
		return javafx.scene.paint.Color.PINK;
	}
	public javafx.scene.paint.Color viewOutlineColor() { 
		return javafx.scene.paint.Color.PURPLE;  
	}
	public javafx.scene.paint.Color viewFillColor() { 
		return viewColor();
	}
}
