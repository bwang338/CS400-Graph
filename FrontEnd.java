// --== CS400 File Header Information ==--
// Name: <Brian Wang>
// Email: <bwang338@wisc.edu>
// Team: <BA>
// TA: <Brianna Cochran>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class FrontEnd extends Application {

    static CS400Graph<String> graph = new CS400Graph<>();

	@Override
	public void start(final Stage stage) {
		BorderPane homePage = new BorderPane();
		Scene start = new Scene(homePage, 1600, 800);
		loadMainMenu(homePage);
		stage.setTitle("Flight Terminal");
		stage.setScene(start);
		stage.show();
	}

    public static void loadHelpMenu(BorderPane menuPane) {
	BorderPane mainPane = new BorderPane();
	Label cantFindFlight = new Label("There are no flights! SOLUTION: You will have to make a layover");
	Label noSeats = new Label("There are no seats! SOLUTION: You will have to find a new flight at a different time");
	Label cancelFlight = new Label("I need to cancel my flight! SOLUTION: Unfortunately, at this time you can't cancel your flight");
	VBox box = new VBox(cantFindFlight, noSeats, cancelFlight);
    }
	
	//A helper method that creates the main page, when the home button is pushed.
    public static void loadMainMenu(BorderPane menuPane) {
        BorderPane mainPane = new BorderPane();
	Label welcomeMessage = new Label("Welcome to the Ticket Terminal.");
	Label list = new Label("Here are the airport terminals that we are currently operating out of:");
	list.setWrapText(true);
	list.setFont(new Font("Arial", 24));
	Label warning = new Label("Due to COVID-19, there are fewer flights, so you may not be able to find the direct flight that you are looking for.");
	warning.setWrapText(true);
	Label JFK = new Label("JFK");
	JFK.setFont(new Font(18));
	Label LaGuardia = new Label("LaGuardia");
	LaGuardia.setFont(new Font(18));
	Label DaneCounty = new Label("Dane County");
	DaneCounty.setFont(new Font(18));
	Label Chicago = new Label("Chicago");
	Chicago.setFont(new Font(18));
	Label Minnesota = new Label("Minnesota");
	Minnesota.setFont(new Font(18));
	Label SanFrancisco = new Label("San Francisco");
	SanFrancisco.setFont(new Font(18));
	Label LA = new Label("Los Angeles");
	LA.setFont(new Font(18));
	Label Seoul = new Label("Seoul");
	Seoul.setFont(new Font(18));
	Label Paris = new Label("Paris");
	Paris.setFont(new Font(18));
	Label London = new Label("London");
	London.setFont(new Font(18));
	Label filler = new Label("");
	Label fill = new Label("");
	Label findFlight = new Label("To book a flight, click Find a Flight on the left");
	Label importData = new Label("To import your custom data, please click Import Custom Flight Data on the left");
	Label help = new Label("For more information, click Help");
	VBox menuItems = new VBox(findFlight, importData, help);
	VBox listOfAirports = new VBox(list, filler, JFK, LaGuardia, DaneCounty, Chicago, Minnesota, SanFrancisco, LA, Seoul, Paris, London);
	VBox boxes = new VBox(welcomeMessage, fill, warning);
	mainPane.setTop(boxes);
	boxes.setAlignment(Pos.CENTER);
	mainPane.setCenter(listOfAirports);
	listOfAirports.setAlignment(Pos.CENTER);
	mainPane.setBottom(menuItems);
	//menuItems.setAlignment(Pos.CENTER);
	menuPane.setCenter(mainPane);
        
    }
    
	public static void main(String[] args) {
	    try {
		FileReader.loadFile(graph, "SampleFlightNetwork.txt");
	    } catch (Exception e){
		System.out.println("The file was not loaded correctly.");
	    }
	    
	    Application.launch();
	}

}
