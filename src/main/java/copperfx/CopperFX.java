package com.redsoft.copperfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;  
import java.io.IOException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import com.redsoft.copperfx.ui.*;

/* Main CopperFX application class.
 */

public class CopperFX extends Application {
	 Map<String, Desktop> desktops = new HashMap<String, Desktop>(); 

	public CopperFX() {
    //Optional constructor
    System.out.println("constructor");
  }

  @Override
  public void start(Stage primaryStage) {
  	primaryStage.setTitle("CopperFX App");

  	// load desktop FXML definitions
  	System.out.println("loading desktops ...");

  	File desktopsDirectory = new File("/Users/max/dev/Copper/resource/desktops");
  	String [] desktopsDirectoryContents = desktopsDirectory.list();

  	for (String filename : desktopsDirectoryContents) {
  		Desktop desktop = new Desktop(new File(String.valueOf(desktopsDirectory), filename));
  		desktops.put(desktop.getId(), desktop);
		}

		// build desktops menu
    Menu desktops_menu = new Menu("Desktop");
    
    // create desktop menu items
    for (Map.Entry<String, Desktop> entry : desktops.entrySet()) {
    	String desktopTitle = entry.getKey(); 
			desktopTitle = desktopTitle.substring(0,1).toUpperCase() + desktopTitle.substring(1).toLowerCase();
    	MenuItem m = new MenuItem(desktopTitle);
    	m.setOnAction(new EventHandler<ActionEvent>() {
    		@Override public void handle(ActionEvent e) {
      		//System.out.println("Setting desktop: " + desktopTitle);
    		}
			});
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

    // set default desktop to main layout 
    vBox.getChildren().addAll(desktops.get("build"));

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

  public void switchToDesktop(String desktopName) {

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
