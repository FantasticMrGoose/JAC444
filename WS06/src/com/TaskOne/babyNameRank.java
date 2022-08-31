
package com.TaskOne;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class babyNameRank extends Application {
	private String year;
	private String gender;
	private String name;
	private String rank;
	
	static TextField fieldYear = new TextField();
    static TextField fieldGender = new TextField();
    static TextField fieldName = new TextField();
	
	public void start(Stage primaryStage) throws Exception{
		primaryStage.setTitle("Search Name Ranking Application");
		
		// Labels
        Label labelYear = new Label("Enter the Year:");
        Label labelGender = new Label("Enter the Gender:");
        Label labelName = new Label("Enter the Name:");
        
        // Buttons
        Button btnSubmit = new Button("Submit Query");
        Button btnExit = new Button("Exit");
        btnSubmit.setPrefWidth(90);
        btnExit.setPrefWidth(90);
        
        // configuration of main grid
        GridPane mainGrid = new GridPane();
        mainGrid.setPadding(new Insets(30,30,30,30));
        mainGrid.setHgap(10);
        mainGrid.setVgap(10);
        
        // Adjusting button grid panel
		GridPane btnGrid1 = new GridPane();
		GridPane btnGrid2 = new GridPane();
        btnGrid1.setPadding(new Insets(0, 0, 0, 0));
        btnGrid2.setPadding(new Insets(0, 0, 0, 50));
        
        btnGrid1.add(btnSubmit, 0, 0);
        btnGrid2.add(btnExit, 1, 0);
        
        mainGrid.add(labelYear, 0, 0);
        mainGrid.add(labelGender, 0, 1);
        mainGrid.add(labelName, 0, 2);
        mainGrid.add(fieldYear, 1, 0);
        mainGrid.add(fieldGender, 1, 1);
        mainGrid.add(fieldName, 1, 2);
        mainGrid.add(btnGrid1, 0, 3);
        mainGrid.add(btnGrid2, 1, 3);
        
        
		fieldGender.setMaxWidth(28);
		
		// Ranking Pane
		GridPane rankPane = new GridPane();
		rankPane.setPadding(new Insets(30, 30, 30, 30));
		rankPane.setVgap(10);
		rankPane.setHgap(10);
		
		Text rankText = new Text();
		Text searchText = new Text("Do you want to search for another name:");

		Button btnYes = new Button("Yes");
        Button btnNo = new Button("No");
        btnYes.setPrefWidth(90);
        btnNo.setPrefWidth(90);
        
        rankPane.add(rankText, 0, 0, 5, 1);
        rankPane.add(searchText, 0, 1, 5, 1);
        rankPane.add(btnYes, 0, 2);
        rankPane.add(btnNo, 1, 2);
        
        Stage rankStage = new Stage();
        rankStage.setTitle("Search Name Ranking Application");
        
        Scene rankScene = new Scene(rankPane);
        rankStage.setScene(rankScene);
		
		// Event handling
        btnSubmit.setOnAction(e -> {
        	year = fieldYear.getText();
        	gender = fieldGender.getText().toUpperCase();
        	name = fieldName.getText();
        	try {
				
				String nameRank;
				
				if(getRank() && gender.equals("M")) {
					nameRank = "Boy name " + name + " is ranked #" + rank +
							" in " + year + " year";
				}
				else if(getRank() && gender.equals("F")) {
					nameRank = "Girl name " + name + " is ranked #" + rank +
							" in " + year + " year";
				}
				else {
					nameRank = "Name not found!";
				}
				rankText.setText(nameRank);
				rankStage.show();
				clearField();
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        });
        
        btnExit.setOnAction(e -> primaryStage.close());
        
        btnYes.setOnAction(e -> {
        	rankStage.hide();
        });
        
        btnNo.setOnAction(e -> {
        	rankStage.close();
        	primaryStage.close();
        });
        
        Scene scene = new Scene(mainGrid);
        primaryStage.setScene(scene);
        primaryStage.show();	
		
	}
	
	private Boolean getRank() throws IOException{
		
		Boolean found = false;
		
		try { 
			BufferedReader buffRead = new BufferedReader(
					new FileReader("Babynames files\\babynamesranking" + year + ".txt")
					);
			System.out.println("File found!");
			
			String line;

			//looks for the line that contains the name substring
			while ((line = buffRead.readLine()) != null && !found) {
				if(line.contains(name)) {
					// Splits the line by tab
					String[] namesRank = line.split("\t");
					rank = namesRank[0];
					found = true;
				}
			}
			
			buffRead.close();
			
			
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found!");
		}
		return found;
	}
	
    public void clearField() {
    	fieldYear.setText("");        	
    	fieldGender.setText("");        	
    	fieldName.setText("");        	

    }
	
	public static void main(String[] args) throws IOException{
		launch(args);
	}

}
