
package com.TaskOne;

import java.io.IOException;
import java.io.RandomAccessFile;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;


public class addressBook extends Application{
	
	ChoiceBox<String> fieldProv = new ChoiceBox<>();
	
	// index to find new line
	private static int index = 0;
	
	// randomly accessed record
	private static String tempRecord = "";
	
	//Filename
	static String filename = "addressBook.txt";
	
	// line size
	static int lineLen = 44;
	
	// file size
	static long fileBytes;

	TextField fieldFname = new TextField();
	TextField fieldLname = new TextField();
	TextField fieldCity = new TextField();
	TextField fieldPostal = new TextField();
	
	 // array of set length 
	static char[] fName = new char[10];
	static char[] lName = new char[10];
	static char[] city = new char[10];
	
	public static void main(String[] args) throws IOException {
		clearArray();
		getFileLen();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception{
		primaryStage.setTitle("Address Book");
		fieldProv.getItems().addAll("AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE", "QC", "SK", "YT");
		
		// labels
		Label labelFname = new Label("First Name: ");
		Label labelLname = new Label("Last Name: ");
		Label labelCity = new Label("City: ");
		Label labelProv = new Label("Province: ");
		Label labelPostal = new Label("Postal Code: ");
				
		fieldFname.setPrefWidth(420);		
		fieldLname.setPrefWidth(420);		
		fieldCity.setPrefWidth(100);
		fieldPostal.setPrefWidth(100);
		
		// buttons
		Button btnAdd = new Button("Add");
		Button btnFirst = new Button("First");
		Button btnNext = new Button("Next");
		Button btnPrev = new Button("Previous");
		Button btnLast = new Button("Last");
		Button btnUpdate = new Button("Update");
		
		// button size
		btnAdd.setPrefWidth(80);
		btnFirst.setPrefWidth(80);
		btnNext.setPrefWidth(80);
		btnPrev.setPrefWidth(80);
		btnLast.setPrefWidth(80);
		btnUpdate.setPrefWidth(80);

		// Grid Panel
        GridPane mainGrid = new GridPane();
        GridPane btnGrid = new GridPane();
        GridPane nameGrid = new GridPane();
        GridPane addressGrid = new GridPane();
        
        // Adjusting main grid panel
        mainGrid.setPadding(new Insets(5, 5, 5, 5));
        mainGrid.setHgap(10);
        mainGrid.setVgap(10);
      
        // Adjusting name grid panel
        nameGrid.setPadding(new Insets(5, 5, 5, 5));
        nameGrid.setHgap(5);
        nameGrid.setVgap(5);
        
        // Adjusting address grid panel
        addressGrid.setPadding(new Insets(5, 5, 5, 5));
        addressGrid.setHgap(5);
        addressGrid.setVgap(5);
        
        // Adjusting button grid panel
        btnGrid.setPadding(new Insets(5, 5, 5, 5));
        btnGrid.setHgap(5);
        btnGrid.setVgap(5);
        
        // adding in labels and fields
        nameGrid.add(labelFname, 1, 1);
        nameGrid.add(fieldFname, 2, 1);
        nameGrid.add(labelLname, 1, 2);
        nameGrid.add(fieldLname, 2, 2);
        
        addressGrid.add(labelCity, 1, 1);
        addressGrid.add(fieldCity, 2, 1);
        addressGrid.add(labelProv, 3, 1);
        addressGrid.add(fieldProv, 4, 1);
        addressGrid.add(labelPostal, 5, 1);
        addressGrid.add(fieldPostal, 6, 1);
        
        btnGrid.add(btnAdd, 1, 1);
        btnGrid.add(btnFirst, 2, 1);
        btnGrid.add(btnNext, 3, 1);
        btnGrid.add(btnPrev, 4, 1);
        btnGrid.add(btnLast, 5, 1);
        btnGrid.add(btnUpdate, 6, 1);
        
        // adding grids to mail grid
        mainGrid.add(nameGrid, 1, 1);
        mainGrid.add(addressGrid, 1, 2);
        mainGrid.add(btnGrid, 1, 3);
        
		Scene scene = new Scene(mainGrid);
		primaryStage.setTitle("Address Book");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		// Event handling 
		btnAdd.setOnAction(e -> {
			
			String newRecord = getText();
			
			try {
				writeRecord(newRecord, false);
				getFileLen();
				System.out.println(newRecord + " has been added!");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		btnFirst.setOnAction(e ->{
			index = 0;
			populateFields();
		});
		
		btnNext.setOnAction(e ->{
			if((index + 1) * lineLen < fileBytes) {
				index++;
			}
			populateFields();
		});
		
		btnPrev.setOnAction(e ->{
			if(index > 0) {
				index--;
			}
			populateFields();
		});
		
		btnLast.setOnAction(e ->{
			index = (int) (fileBytes / lineLen);
			populateFields();
		});
		
		btnUpdate.setOnAction(e ->{

			String update = getText();
			
			try {
				writeRecord(update, true);
				System.out.println("Record Updated to: " + update);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});
		
	}
	
	public void populateFields() {
		
		try {
			readRecords(index);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] temp;
		
		temp = tempRecord.split(",");
		
		fieldFname.setText(temp[0]);
		fieldLname.setText(temp[1]);
		fieldCity.setText(temp[2]);
		fieldProv.setValue(temp[3]);
		fieldPostal.setText(temp[4]);
		
	}
	
	public String getText() {
		
		clearArray();
		
		for(int i = 0; i < fieldFname.getText().length() && i < 10; i++) {
			fName[i] = fieldFname.getText().charAt(i);
		}
		for(int i = 0; i < fieldLname.getText().length() && i < 10; i++) {
			lName[i] = fieldLname.getText().charAt(i);
		}
		for(int i = 0; i < fieldCity.getText().length() && i < 10; i++) {
			city[i] = fieldCity.getText().charAt(i);
		}
		
		String tempFname = new String(fName);
		String tempLname = new String(lName);
		String tempCity = new String(city);
		
		
		String newRecord = String.join(",", tempFname, tempLname, tempCity,
				fieldProv.getValue(), fieldPostal.getText());
		
		return newRecord;		
	}
	
	// gets the length of the file
	public static void getFileLen() throws IOException{
		
		try {
			RandomAccessFile addressBook = new RandomAccessFile(filename, "r");
			fileBytes = addressBook.length();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void readRecords(int pos) throws IOException {
		
		try {
			RandomAccessFile addressBook = new RandomAccessFile(filename, "r");
			addressBook.seek(pos * lineLen);
			tempRecord = new String(addressBook.readLine());
			addressBook.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void writeRecord(String rec, Boolean update) throws IOException {
		
		try {
			RandomAccessFile addressBook = new RandomAccessFile(filename, "rw");
			if(update) {
				addressBook.seek(index * lineLen);
				addressBook.write(rec.getBytes());
			}
			else {
				addressBook.seek(fileBytes);
				addressBook.writeBytes(System.getProperty("line.separator"));
				addressBook.write(rec.getBytes());
			}
			
			addressBook.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void clearArray() {
		for(char val: fName) {
			val = ' ';
		}
		for(char val: lName) {
			val = ' ';
		}
		for(char val: city) {
			val = ' ';
		}
	}
	
}
