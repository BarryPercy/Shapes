import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos; 
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Shapes extends Application{ // Shapes is defined as a subclass of Application which is what allows it to run in an application window
	private Shape currentShape = null; // variable used to store the current Shape active in the scene.
	@Override //Override is declared because we are modifying an existing method from the Application Class
	public void start(Stage stage){ //start is an abstract method of Application so it must be defined. This is what is run to display the application
		//Circle
		Circle theCircle = new Circle(60); //We have imported the Circle Class from the javafx.scene.shape Package.
		theCircle.setFill(Color.WHITE); //Setting the initial fill colour of the shape
		theCircle.setStroke(Color.BLACK);
				
		//Rectangle
		Rectangle theRectangle = new Rectangle(150,100); //We have imported the Rectangle Class from the javafx.scene.shape Package.
		theRectangle.setFill(Color.WHITE); //Setting the initial fill colour of the shape
		theRectangle.setStroke(Color.BLACK);	
		
		//Octagon
		Polygon theOctagon = new Polygon(); //We have imported the Polygon Class from the javafx.scene.shape Package. Octagon's are a type of polygon, there isn't a defined octagon class.
		
		theOctagon.getPoints().addAll(new Double[]{ //setting all the coordinate points of the Octagon.
		         35.36, 0.0, 
		         85.36, 0.0, 
		         120.72, 35.36, 
		         120.72, 85.36,
		         85.36, 120.72, 
		         36.36, 120.72, 
		         0.0, 85.36, 
		         0.0, 35.36, 
		         
		      }); 
		theOctagon.setFill(Color.WHITE); //Setting the initial fill colour of the shape
		theOctagon.setStroke(Color.BLACK);
		//Text field for entering shape
		TextField shapeTextField = new TextField();
		
		//Label for errors
		Label errorLabel= new Label();
		errorLabel.setTextFill(Color.RED);
		errorLabel.setFont(Font.font("Verdana", 20)); 
		
		//Text field for entering colour
		TextField colourTextField = new TextField();
		shapeTextField.setMaxWidth(250);
		colourTextField.setMaxWidth(250);
		//HBox container for the shapes which will be added to after events in the application
		HBox shapeHBox = new HBox();
		shapeHBox.setSpacing(10);
		shapeHBox.setAlignment(Pos.CENTER);
		
		//Button for submitting shape
		Button shapeSubmitButton = new Button();
		shapeSubmitButton.setText("Submit");
		shapeSubmitButton.setOnAction(e -> {						//error checking for empty or incorrect strings
			if(shapeTextField.getText().isEmpty()) {
				errorLabel.setText("No Shape was submitted!"); 
			}
			else {
				switch(shapeTextField.getText().toLowerCase()) {	//Checks if the submitted text is any of the 3 colours, if not it runs the default code
					case "circle":
						shapeHBox.getChildren().clear();
						shapeHBox.getChildren().add(theCircle);
						currentShape = theCircle; //set chosen shape as the variable currentShape so that we can assign a colour
						errorLabel.setText(""); //clear any previous errors
						break;
					case "rectangle":
						shapeHBox.getChildren().clear();
						shapeHBox.getChildren().add(theRectangle);
						currentShape = theRectangle;
						errorLabel.setText("");
						break;
					case "octagon":
						shapeHBox.getChildren().clear();
						shapeHBox.getChildren().add(theOctagon);
						currentShape = theOctagon;
						errorLabel.setText("");
						break;
					default: 
						errorLabel.setText("\""+shapeTextField.getText()+"\""+" is not a valid shape!");
				}
			}
			shapeTextField.setText(""); //clears the current text in the shape text field
		}
				);
		
		//Button for submitting colour
		Button colourSubmitButton = new Button();
		colourSubmitButton.setText("Submit");
		colourSubmitButton.setOnAction(e -> { 						
			if(colourTextField.getText().isEmpty()) {	//error checking for empty string
				errorLabel.setText("No colour was submitted!"); 
			}
			else if(currentShape==null) { //checks if there's no shape and tells the user they can't enter a colour while theere's no shape
				errorLabel.setText("There's no shape!");
			}
			else {
				switch(colourTextField.getText().toLowerCase()) { //Checks if the submitted text is any of the 3 colours, if not it runs the default code
					case "blue":
						currentShape.setFill(Color.BLUE); //set the new colour of the currentShape which was set while choosing a shape
						errorLabel.setText(""); //clear any previous errors
						break;
					case "yellow":
						currentShape.setFill(Color.YELLOW);
						errorLabel.setText("");
						break;
					case "green":
						currentShape.setFill(Color.GREEN);
						errorLabel.setText("");
						break;
					default: 
						errorLabel.setText("\""+colourTextField.getText() + "\"" + " is not a valid colour"); 
				}
			}
			colourTextField.setText("");
		}	
				); 
		//Shape Type Text
		Text shapeTypeText = new Text(98, 240, " Shape:");
		shapeTypeText.setFill(Color.BLACK);
		shapeTypeText.setFont(Font.font("Verdana", 15));
		
		//Shape Type Text
		Text colourOfShapeText = new Text(98, 260, "Colour: ");
		colourOfShapeText.setFill(Color.BLACK);
		colourOfShapeText.setFont(Font.font("Verdana", 15)); 
		
		//HBox for containing shape submission elements
		HBox shapeTextHBox = new HBox();
		shapeTextHBox.setSpacing(10); //amount of horizontal space between each child
		shapeTextHBox.setAlignment(Pos.CENTER);
		shapeTextHBox.getChildren().addAll(shapeTypeText,shapeTextField,shapeSubmitButton);
		
		//HBox for containing colour submission elements
		HBox colourHBox = new HBox();
		colourHBox.setSpacing(10); //amount of horizontal space between each child
		colourHBox.setAlignment(Pos.CENTER); //aligns the elements to the center
		colourHBox.getChildren().addAll(colourOfShapeText,colourTextField,colourSubmitButton); //adds all the elements to the HBOX

		//VBox to vertically contain all the elements of the scene
		VBox root = new VBox();
		root.setSpacing(10); //amount of horizontal space between each child
		root.setAlignment(Pos.TOP_CENTER); //sets position of the contain
		//Adding all the previously defined elements to the root VBOX
		root.getChildren().addAll(shapeTextHBox, colourHBox, errorLabel, shapeHBox); //adds all the elements to the VBox
		root.setPadding(new Insets(10,0,0,0)); //Puts a padding of 10 pixels from the top of the application
		
		// Create scene with Parent root, width, height and colour of background
		Scene scene = new Scene(root, 400, 250, Color.WHITE);
		
		//Add Scene to Stage
		stage.setScene(scene);
		
		//Set title for the stage which will appear at the top of the application
		stage.setTitle("Shapes");
		
		//show the stage
		stage.show();
	}

	public static void main(String[] args){
		launch(args);
	}
}