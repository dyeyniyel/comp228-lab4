package exercise1;
	
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
	   
	    stage.setTitle("Student Information");  //Set the title of the stage    
	    Image icon = new Image("file:src/java-icon.png"); //Load the Java logo image file
	    stage.getIcons().add(icon); // Set the Java logo as the stage icon
		
		//declare BorderPane
        BorderPane root = new BorderPane();
        
        //declare Grid Pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(5);
        pane.setVgap(5);
        
        //Create the labels and text fields
        TextField nameField = new TextField();
        nameField.setPrefWidth(200); //Set the preferred width to 200 pixels. This will be applied to all text fields.
        pane.add(new Label("Name:"), 0, 0);
        pane.add(new Label("                                 "), 1, 0); //whitespace to provide space between the labels and text fields
        pane.add(nameField, 2, 0);
        
        TextField addressField = new TextField();
        pane.add(new Label("Address:"), 0, 1);
        pane.add(new Label("                                 "), 1, 1);
        pane.add(addressField, 2, 1);

        TextField provinceField = new TextField();
        pane.add(new Label("Province:"), 0, 2);
        pane.add(new Label("                                 "), 1, 2);
        pane.add(provinceField, 2, 2);

        TextField cityField = new TextField();
        pane.add(new Label("City:"), 0, 3);
        pane.add(new Label("                                 "), 1, 3);
        pane.add(cityField, 2, 3);

        TextField postalCodeField = new TextField();
        pane.add(new Label("Postal Code:"), 0, 4);
        pane.add(new Label("                                 "), 1, 4);
        pane.add(postalCodeField, 2, 4);

        TextField phoneNumberField = new TextField();
        pane.add(new Label("Phone Number:"), 0, 5);
        pane.add(new Label("                                 "), 1, 5);
        pane.add(phoneNumberField, 2, 5);

        TextField emailField = new TextField();
        pane.add(new Label("Email:"), 0, 6);
        pane.add(new Label("                                 "), 1, 6);
        pane.add(emailField, 2, 6);
        
        //Add the checkboxes
        CheckBox studentCouncil = new CheckBox("Student Council");
        pane.add(studentCouncil, 3, 1);

        CheckBox volunteerWork = new CheckBox("Volunteer Work");
        pane.add(volunteerWork, 3, 5);
        
        //Add the radio buttons

        RadioButton csRButton = new RadioButton("Computer Science");
        pane.add(csRButton, 5, 0);
        RadioButton businessRButton = new RadioButton("Business");
        pane.add(businessRButton, 6, 0);
        
        //Create a ToggleGroup for the radio buttons so that only one can be selected
        ToggleGroup majorGroup = new ToggleGroup();
        csRButton.setToggleGroup(majorGroup);
        businessRButton.setToggleGroup(majorGroup);
        
        //Add the combo boxes
        ComboBox<String> coursesBox = new ComboBox<>();
        ListView<String> selectedCoursesList = new ListView<>();
        
        coursesBox.prefWidthProperty().bind(selectedCoursesList.widthProperty());   //Bind the width of the combo box to the width of the list view
        coursesBox.setPrefHeight(50); // Set the preferred height of the combo box
        selectedCoursesList.setPrefHeight(100); // Set the preferred height of the list view
        
        //Create HBox for the radio buttons
        HBox radioButtonBox = new HBox(csRButton, businessRButton);
        radioButtonBox.setSpacing(10);
        radioButtonBox.setPadding(new Insets(20, 0, 0, 0));   // Add padding to the top of the radioButtonBox
        
        //Create VBox for the combo box and list view
        VBox comboBoxListViewBox = new VBox(coursesBox, selectedCoursesList);
        comboBoxListViewBox.setSpacing(5); 
        
        //Add the HBox and VBox to a VBox
        VBox majorBox = new VBox(radioButtonBox, comboBoxListViewBox);
        majorBox.setSpacing(30); 
        
        //Add the VBox majorBox to the right of the BorderPane
        root.setRight(majorBox);
        
        //Add the Display button above the text area
        Button displayButton = new Button("Display");
        displayButton.setPrefWidth(100);
          
        //Add the Clear button above the text area
        Button clearButton = new Button("Clear");
        clearButton.setPrefWidth(100);
       
        //Create the TextArea
        TextArea textArea = new TextArea();
        textArea.setPrefHeight(200); 

        //Create HBox to hold the buttons
        HBox buttonsBox = new HBox(displayButton, clearButton);
        buttonsBox.setAlignment(Pos.CENTER); // align buttons to the center
        buttonsBox.setSpacing(100);

        //Create a VBox to hold the button HBox and the text area
        VBox vbox = new VBox(10); // spacing 
        vbox.setAlignment(Pos.CENTER); // align contents to the center
        vbox.getChildren().addAll(buttonsBox, textArea);

        //Add the TextArea to the bottom of the BorderPane
        root.setBottom(vbox);
  
        //Set GridPane to the center of BorderPane
        root.setCenter(pane);
        
        
        //////EVENT HANDLERS
        //Add event handlers for the radio buttons
        csRButton.setOnAction(e -> {
            coursesBox.getItems().clear();  //this will clear all items on coursesBox upon clicking a new radio button
            coursesBox.getItems().addAll("Java","C++","SQL", "Python", "C#" ); //possible options on coursesBox
       //     selectedCoursesList.getItems().clear(); //this will clear all items on courses list upon clicking a new radio button
        });

        businessRButton.setOnAction(e -> {
            coursesBox.getItems().clear();
            coursesBox.getItems().addAll("Business 101", "Accounting 101", "Comm 101");
         //   selectedCoursesList.getItems().clear();
        });

        //Add event handler for the the combo box
        coursesBox.setOnAction(e -> {
            String selectedCourse = coursesBox.getSelectionModel().getSelectedItem();
            //add item selected on the combo box to the list view. Added items will not be added again
            if (selectedCourse != null && !selectedCoursesList.getItems().contains(selectedCourse)) {
                selectedCoursesList.getItems().add(selectedCourse);
            }
        });
        
        //Add event handler for the Display button
        displayButton.setOnAction(e -> {
        	//display error message when all fields are not populated
            if (nameField.getText().isEmpty() || addressField.getText().isEmpty() ||
                    provinceField.getText().isEmpty() || cityField.getText().isEmpty() ||
                    postalCodeField.getText().isEmpty() || phoneNumberField.getText().isEmpty() ||
                    emailField.getText().isEmpty()) {
            	JOptionPane.showMessageDialog(null,"PLEASE FILL IN ALL THE FIELDS");
            }
            //if a course has not been selected, display message
            else if ( selectedCoursesList.getItems().isEmpty()) {
            	JOptionPane.showMessageDialog(null,"PLEASE SELECT A COURSE");
            }
            //if the phone number field contains anything else except a number, ")", "(" and "-", display message
            else if (!phoneNumberField.getText().matches("[0-9()\\- ]*"))
            {
            	JOptionPane.showMessageDialog(null,"PLEASE ENTER A VALID NUMBER");
            }
            //if the email address field does not contain a domain address (i.e, an "@" symbol and ".com" or ".ca", etc), display message
            else if (!emailField.getText().contains("@") || !emailField.getText().contains(".") || emailField.getText().endsWith(".")) {
            	JOptionPane.showMessageDialog(null,"Email address should be valid. A valid email address should have a domain");
            }
            else //if all fields are properly populated, display values
            {
            String studentInfo = "Student Info " + textArea.getText().split("Student Info ").length + "\n"; // Header with count 
            	studentInfo += "Student Name: " + nameField.getText() + ",  "
                    + "Address: " + addressField.getText() + ",  "
                    + "Province: " + provinceField.getText() + ",  "
                    + "City: " + cityField.getText() + ",  "
                    + "Postal Code: " + postalCodeField.getText() + ",  "
                    + "Phone Number: " + phoneNumberField.getText() + ",  "
                    + "Email: " + emailField.getText() + "\n"
                    + "Student Major: " + ((RadioButton) majorGroup.getSelectedToggle()).getText() + "\n"
                    + "Student Council: " + (studentCouncil.isSelected() ? "Yes" : "No") + "\n"
                    + "Volunteer Work: " + (volunteerWork.isSelected() ? "Yes" : "No");
            
            //Append each course to the studentInfo string
            studentInfo += "\nStudent Courses:\n";
            for (String course : selectedCoursesList.getItems()) {
                studentInfo += course + "\n";
            }        
            textArea.appendText(studentInfo + "\n"); // Append instead of setText
            
            // Clear all text fields once Display button is clicked
            nameField.clear();
            addressField.clear();
            provinceField.clear();
            cityField.clear();
            postalCodeField.clear();
            phoneNumberField.clear();
            emailField.clear();
            
            // Clear checkboxes
            studentCouncil.setSelected(false);
            volunteerWork.setSelected(false);
            
            // Clear radio buttons
            majorGroup.selectToggle(null);
            
            // Clear combo box and list view
            coursesBox.getSelectionModel().clearSelection();
            selectedCoursesList.getItems().clear();
            }});
        
        	//Add event handler for the Clear button
        	clearButton.setOnAction(e -> {
        		
            // Clear all text fields
            nameField.clear();
            addressField.clear();
            provinceField.clear();
            cityField.clear();
            postalCodeField.clear();
            phoneNumberField.clear();
            emailField.clear();
            
            // Clear checkboxes
            studentCouncil.setSelected(false);
            volunteerWork.setSelected(false);
            
            // Clear radio buttons
            majorGroup.selectToggle(null);
            
            // Clear combo box and list view
            coursesBox.getSelectionModel().clearSelection();
            selectedCoursesList.getItems().clear();
            
            // Clear text area
            textArea.clear();
        });
   
        // Create the scene and show the stage
        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
        stage.show();
        
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
