/* CRITTERS 2 Main.java
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

package assignment5; // cannot be in default package

import java.lang.reflect.Method;
import java.util.List;

import assignment5.Critter;
import assignment5.InvalidCritterException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	static GridPane worldgrid = new GridPane();
	static GridPane grid = new GridPane();
	static GridPane grid2 = new GridPane();
	static Button update = new Button();
	static TextField param1 = new TextField();
	static TextField param2 = new TextField();
	static Label results = new Label();
	static ChoiceBox<String> commandsList = new ChoiceBox<String>();
	static String lastCritter = new String();
	
	private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
	
	static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Critter Simulator");
			Critter.displayWorld();
			worldgrid.setGridLinesVisible(true);
			grid.setGridLinesVisible(false);
			grid2.setGridLinesVisible(false);
			worldgrid.setPadding(new Insets(5, 5, 5, 5));
			worldgrid.setHgap(2);
			worldgrid.setVgap(2);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.setHgap(2);
			grid.setVgap(2);
			grid2.setPadding(new Insets(5, 5, 5, 5));
			grid2.setHgap(2);
			grid2.setVgap(2);
			lastCritter = "";
			
			// UPDATE BUTTON
			update.setText("Create");
			GridPane.setConstraints(update, 4, 1);
			grid.getChildren().add(update);
			
			// PARAMETER 1
			param1.setPromptText("Critter Type");
			param1.setPrefColumnCount(10);
			GridPane.setConstraints(param1, 2, 1);
			grid.getChildren().add(param1);
			param1.setText("");
			
			// PARAMETER 2
			param2.setPromptText("(Critter Quantity)");
			param2.setPrefColumnCount(10);
			GridPane.setConstraints(param2, 3, 1);
			grid.getChildren().add(param2);
        	param2.setText("");
        	
        	// RESULTS WINDOW
        	results.setText("");
    		GridPane.setConstraints(results, 1, 1);
    		grid2.getChildren().add(results);
			
			// DROP DOWN MENU FOR COMMANDS
			commandsList.getItems().addAll("make", "step", "stats", "seed", "clear", "quit");
			//set default value
			commandsList.setValue("make");
			GridPane.setConstraints(commandsList, 1, 1);
			grid.getChildren().add(commandsList);
			
			commandsList.valueProperty().addListener(new ChangeListener<String>() {
		        @Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		            String command = commandsList.getValue();
		        	if (command.equals("step")) {
		        		param1.setPromptText("(Step Count)");
		            	param2.setPromptText("n/a");
		            	param1.setText("");
		            	param2.setText("");
		            	update.setText("Run");
		        	}
		        	else if (command.equals("seed")) {
		        		param1.setPromptText("Seed Number");
		            	param2.setPromptText("n/a");
		            	param1.setText("");
		            	param2.setText("");
		            	update.setText("Set");
		        	}
		        	else if (command.equals("make")) {
		        		param1.setPromptText("Critter Type");
		            	param2.setPromptText("(Critter Count)");
		            	param1.setText("");
		            	param2.setText("");
		            	update.setText("Create");
		        	}
		        	else if (command.equals("stats")) {
		        		param1.setPromptText("Critter Type");
		            	param2.setPromptText("n/a");
		            	param1.setText("");
		            	param2.setText("");
		            	update.setText("Run");
		        	}
		        	else if (command.equals("clear")) {
		        		param1.setPromptText("n/a");
		            	param2.setPromptText("n/a");
		            	param1.setText("");
		            	param2.setText("");
		            	update.setText("Clear");
		        	}
		        	else { // command.equals("quit") 
		        		param1.setPromptText("n/a");
		            	param2.setPromptText("n/a");
		            	param1.setText("");
		            	param2.setText("");
		            	update.setText("Quit");
		        	}
		        }
		    });
			
			update.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	try {
	            		String command = commandsList.getValue();
		            	String p1 = param1.getText();
		            	p1 = p1.replaceAll("\\s+","");
		            	String p2 = param2.getText();
		            	p2 = p2.replaceAll("\\s+","");
			        	if (command.equals("step")) {
			        		int num_steps = 1;
	            			if (!p1.equals("")) {
	            				num_steps = Integer.parseInt(p1);
	            			}
	            			for (int i = 0; i < num_steps; i++) {
	            				Critter.worldTimeStep();
	            			}
	            			if (!lastCritter.equals("")) {
	            				List<Critter> critStats = Critter.getInstances(lastCritter);
				        		Class<?> critClass;
		        				critClass = Class.forName(myPackage + "." + lastCritter);
		            			Method runStats = critClass.getMethod("runStats", List.class);
		        				results.setText(runStats.invoke(null, critStats).toString());			
	            			}
	            			Critter.displayWorld();
			        	}
			        	else if (command.equals("seed")) {
			        		if (!p1.equals("")) {
	            				Critter.setSeed(Integer.parseInt(p1));
	            			}
			        		else {
			        			throw new Exception();
			        		}
			        		Critter.displayWorld();
			        	}
			        	else if (command.equals("make")) {
			        		int num_make = 1;
	            			if (!p2.equals("")) {
	            				num_make = Integer.parseInt(p2);
	            			}
	            			for (int i = 0; i < num_make; i++) {
	            				Critter.makeCritter(p1);
	            			}
	            			lastCritter = p1;
	            			List<Critter> critStats = Critter.getInstances(p1);
			        		Class<?> critClass;
	        				critClass = Class.forName(myPackage + "." + p1);
	            			Method runStats = critClass.getMethod("runStats", List.class);
	        				results.setText(runStats.invoke(null, critStats).toString());			
	            			Critter.displayWorld();
			        	}
			        	else if (command.equals("stats")) {
			        		try {
			        			List<Critter> critStats = Critter.getInstances(p1);
			        			Class<?> critClass;
			        			if (p1.equals("")) {
			        				throw new Exception();
			        			}
	        					critClass = Class.forName(myPackage + "." + p1);
	            				Method runStats = critClass.getMethod("runStats", List.class);
	        					results.setText(runStats.invoke(null, critStats).toString());
	        					lastCritter = p1;
	        				} catch (ClassNotFoundException e) {
	        					throw new InvalidCritterException(p1);
	        				}
	            		}
			        	else if (command.equals("clear")) {
			        		Critter.clearWorld();
			        		Critter.displayWorld();
			        	}
			        	else { // command.equals("quit") 
			        		System.exit(0);
			        	}
	            	} catch (Exception e) {
	            		String command = commandsList.getValue();
		            	String p1 = param1.getText();
		            	String p2 = param2.getText();
	            		results.setText("error processing: " + command + " " + p1 + " " + p2);
	            	}
	            }
	        });
			VBox vbox = new VBox();
			vbox.setStyle("-fx-background-color: #FFFFFF;");
			vbox.getChildren().addAll(grid, grid2, worldgrid);
			Scene scene = new Scene(vbox, 540, 540, Color.WHITE);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// Paints the icons.
			//Painter.paint();
			
		} catch(Exception e) {
			e.printStackTrace();		
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}