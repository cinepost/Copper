package com.redsoft.copperfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;  
import java.io.IOException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class CopperFX extends Application {
	 Map<String, javafx.scene.layout.VBox> desktops = new HashMap(); 

	public CopperFX() {
    //Optional constructor
    System.out.println("constructor");
  }

  @Override
  public void start(Stage primaryStage) {
  	primaryStage.setTitle("CopperFX App");

  	// load desktop FXML definitions
  	System.out.println("loading desktops...");

  	FXMLLoader loader = new FXMLLoader();
  	File desktopsDirectory = new File("/Users/max/dev/Copper/resource/desktops");
  	String [] desktopsDirectoryContents = desktopsDirectory.list();

  	for (String filename : desktopsDirectoryContents) {
    	File temp = new File(String.valueOf(desktopsDirectory), filename);
    	try{
    		URL url = new URL(temp.toURI().toString());
    		System.out.println("loading desktop definition: " + url);

    		try {
    			loader.setLocation(url);
    			VBox desktop = loader.<VBox>load();
    			desktops.put(desktop.getId(), desktop); 
    			System.out.println("desktop: " + desktop.getId() + " loaded"); 
    		}catch(IOException ex) {
    			//do exception handling here
    			System.out.println(ex); 
    		}
    	}catch(MalformedURLException ex){
				//do exception handling here
			}
		}

		// build desktops menu
    Menu desktops_menu = new Menu("Desktop");
    
    // create desktop menu items
    for (Map.Entry<String, javafx.scene.layout.VBox> entry : desktops.entrySet()) {
    	String desktopTitle = entry.getKey(); 
			desktopTitle = desktopTitle.substring(0,1).toUpperCase() + desktopTitle.substring(1).toLowerCase();
    	MenuItem m = new MenuItem(desktopTitle);
    	desktops_menu.getItems().add(m);
		} 


  	// main toolbar
    ToolBar toolBar = new ToolBar();

    // test buttons for main toolbar
    Button button1 = new Button("Button 1");
    toolBar.getItems().add(button1);

    Button button2 = new Button("Button 2");
    toolBar.getItems().add(button2);

    // create a menubar 
    MenuBar menuBar = new MenuBar();
    if( System.getProperty("os.name","UNKNOWN").equals("Mac OS X")) {
  		menuBar.setUseSystemMenuBar(true);
		}
  
    // add menu to menubar 
    menuBar.getMenus().add(desktops_menu);

    // main vbox layout
    VBox vBox = new VBox(menuBar);
    vBox.getChildren().addAll(toolBar);

    // set desktop to main layout 
    //vBox.getChildren().addAll(hBox);

    // create app window and set style
    Scene scene = new Scene(vBox, 800, 600);
    scene.getStylesheets().add("default.css");

    primaryStage.setScene(scene);
    primaryStage.show();

    new Thread(() -> {
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        throw new RuntimeException("Should not happen!");
      }

      //System.exit(0);
    }).start();
  }

  @Override
  public void stop() {
		//By default this does nothing
		//It runs if the user clicks the go-away button
		//closing the window or if Platform.exit() is called.
		//Use Platform.exit() instead of System.exit(0).
		//This is where you should offer to save any unsaved
		//stuff that the user may have generated.
		System.out.println("stop");
  }

  public static void main(String[] args) {
  	System.out.println("main");
		launch();
	}
}

/*public class CopperFX extends Application {
  public CopperFX() {
    //Optional constructor
  }
  @Override
  public void init() {
		//By default this does nothing, but it
		//can carry out code to set up your app.
		//It runs once before the start method,
		//and after the constructor.
  }
  
  @Override
  public void start(Stage primaryStage) {
		String javaVersion = System.getProperty("java.version");
		String javafxVersion = System.getProperty("javafx.version");

		// Creating the Java button
		final Button button = new Button();
		// Setting text to button
		button.setText("Hello World");
		// Registering a handler for button
		button.setOnAction((ActionEvent event) -> {
			// Printing Hello World! to the console
			System.out.println("Hello World!");
		});
		// Initializing the StackPane class
		final StackPane root = new StackPane();
		// Adding all the nodes to the StackPane
		root.getChildren().add(button);
		// Creating a scene object
		final Scene scene = new Scene(root, 300, 250);
		// Adding the title to the window (primaryStage)
		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene);
		// Show the window(primaryStage)
		primaryStage.show();
  }
  @Override
  public void stop() {
		//By default this does nothing
		//It runs if the user clicks the go-away button
		//closing the window or if Platform.exit() is called.
		//Use Platform.exit() instead of System.exit(0).
		//This is where you should offer to save any unsaved
		//stuff that the user may have generated.
  }

  public static void main(String[] args) {
		launch();
	}
}*/