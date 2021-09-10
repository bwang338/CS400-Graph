// --== CS400 File Header Information ==--
// Name: David Wissink
// Email: dwissink@wisc.edu
// Team: BA
// Role: Front End Developer 2
// TA: Bri
// Lecturer: Dahl
// Notes to Grader: None

import java.util.List;
import javafx.application.Platform;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;

public class FlightFinder extends Application {

static CS400Graph<String> graph = new CS400Graph<>();

	/**
	 * Function that builds the main menu and navigation bar, then displays it to the
	 * application window
	 * @param stage
	 */
	@Override
	public void start(final Stage stage) {
		Button homeButton = new Button("Home");
		Button findButton = new Button("Find a Flight");
		Button importButton = new Button("Import Custom Flight Data");
		Button helpButton = new Button ("Help");
		Button quitButton = new Button("Quit");
		quitButton.setAlignment(Pos.BOTTOM_RIGHT);
		VBox menuOptions = new VBox (homeButton, findButton, importButton, helpButton);
		
		menuOptions.setSpacing(20);
		menuOptions.setPadding(new Insets(0,10,0,0));
		menuOptions.setFillWidth(true);
		menuOptions.setAlignment(Pos.CENTER_LEFT);
		WebView webView = new WebView();
		BorderPane menuPane = new BorderPane();
		menuPane.setLeft(menuOptions);
		menuPane.setBottom(quitButton);
		FrontEnd.loadMainMenu(menuPane);
		menuPane.getStyleClass().add("pane");
		Scene scene = new Scene(menuPane, 800, 600);
		scene.getStylesheets().add("ffStyle.css");
		stage.setScene(scene);
		stage.setTitle("Flight Finder");
		stage.show();
		findButton.setOnAction(event -> {
			loadFindMenu(menuPane);
		});
		importButton.setOnAction(event -> {
			loadImportMenu(menuPane);
		});
		homeButton.setOnAction(event -> {
			FrontEnd.loadMainMenu(menuPane);
		});
		quitButton.setOnAction( event -> {
			Platform.exit();
		});
		helpButton.setOnAction(event -> {
			loadHelpMenu(menuPane, webView);
		});
	}

	public static void main(String[] args) {
		try {
			FileReader.loadFile(graph, "SampleFlightNetwork.txt");
		} catch(Exception e) {
			;
		}
		Application.launch();
	}

	/**
	 * Deprecated:
	 * Function that loads a webpage containing a welcome message
	 * @param menuPane
	 * @param webView
	 */
	private void loadHomeMenu(BorderPane menuPane, WebView webView) {
		WebEngine engine = webView.getEngine();
		engine.load("https://pages.cs.wisc.edu/~wissink/ffHomePage.html");
		menuPane.setCenter(webView);
	}

	/**
	 * Loads the contents of the import menu
	 * @param menuPane BorderPane whos center value will be assigned the contents
	 * of the import Menu
	 */
	private void loadImportMenu(BorderPane menuPane) {
		Label importInstr = new Label("Please submit the file containing your flight data");
		importInstr.setWrapText(true);
		GridPane importPane = new GridPane();
		importPane.setVgap(20.0);
		Label feedback = new Label("");
		feedback.setWrapText(true);
		TextField inputField = new TextField("File Name");
		Button submitButton = new Button("Submit");
		importPane.add(importInstr,0,0,2,1);
		importPane.add(inputField,0,1,1,1);
		importPane.add(submitButton,1,1,1,1);
		importPane.add(feedback,0,2,2,1);
		submitButton.setOnAction(event -> {
			String input = inputField.getText();
			try {
				FileReader.loadFile(graph, input);
				feedback.setText("Success! Data imported from " + input);
			} catch (Exception e) {
				feedback.setText("Sorry! Data could not be imported from " + input + "\nNote: The file being loaded must be in the same directory as the program files");
			}
		});
		ColumnConstraints c1 = new ColumnConstraints();
		c1.setPercentWidth(50);
		ColumnConstraints c2 = new ColumnConstraints();
		c2.setPercentWidth(50);
		importPane.getColumnConstraints().addAll(c1,c2);
		importPane.setHgap(20);
		menuPane.setCenter(importPane);
		menuPane.setAlignment(importPane, Pos.CENTER);
	}

	/**
	 * Loads the find menu where users can select a departure location and destination
	 * in order to find flights (or sereies of flights) in a database
	 * 
	 * @param menuPane BorderPane whos center value will be assigned the contents
	 * of the find Menu
	 */
	private void loadFindMenu(BorderPane menuPane) {
		GridPane findPane = new GridPane();
		Label instr = new Label("Please select a location from which you would like to depart and where you would like to arrive");
		instr.setWrapText(true);
		instr.setMaxWidth(500);
		findPane.add(instr, 0,0,2,1);
		ComboBox<String> departures = new ComboBox<String>();
		for(CS400Graph.Vertex v : graph.vertices.values()) {
			departures.getItems().add(v.data.toString());
		}
		departures.setPromptText("Departure");
		ComboBox<String> arrivals = new ComboBox<String>();
		for(CS400Graph.Vertex v : graph.vertices.values()) {
			arrivals.getItems().add(v.data.toString());
		}
		arrivals.setPromptText("Arrival");
		findPane.add(departures,0,1);
		findPane.add(arrivals,1,1);
		Button searchButton = new Button("Find Flights");
		List<String> flightPath = null;
		Label flightInfo = new Label("");
		flightInfo.setWrapText(true);
		searchButton.setOnAction(event -> {
			if (departures.getValue() != null && arrivals.getValue() != null) {
				try {
					String dep = departures.getValue();
					String arr = arrivals.getValue();
					flightInfo.setText(ShortestPathToString.findShortestPath(graph, dep, arr));
				} catch(Exception e) {
					flightInfo.setText("Sorry, there are no flights currently" +
					" available for your selections");
				}
			}
		});
		ColumnConstraints c0 = new ColumnConstraints();
		c0.setMinWidth(20);
		c0.setPrefWidth(150);
		c0.setMaxWidth(200);
		ColumnConstraints c1 = new ColumnConstraints();
		c1.setPercentWidth(50);
		findPane.getColumnConstraints().addAll(c0,c1);
		findPane.setVgap(20);
		findPane.add(searchButton,0,2,2,1);
		findPane.add(flightInfo,0,3,2,1);
		menuPane.setCenter(findPane);
	}

	/**
	 * Loads the webpage containing a brief helpful message to users of the application
	 * @param menuPane BorderPane whos center value will be assigned the contents
	 * of the help menu
	 * @param webView The WebView element that will house the webpage being loaded
	 */
	private void loadHelpMenu(BorderPane menuPane, WebView webView) { 
		WebEngine engine = webView.getEngine();
		engine.load("https://pages.cs.wisc.edu/~wissink/ffHelpPage.html");

		menuPane.setCenter(webView);

	}

	/**
	 * Deprecated, was used as a loophole to lambda expression requirements
	 * @param from
	 * @param to
	 */
	private void copyList(List<String> from, List<String> to) {
		to = from;	
	}
}
