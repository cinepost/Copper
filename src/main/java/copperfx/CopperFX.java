package com.redsoft.copperfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Group;
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

	private DesktopManager desktopManager;

	public CopperFX() {
    desktopManager = new DesktopManager();
  }

  @Override
  public void start(Stage primaryStage) {
  	primaryStage.setTitle("CopperFX App");

		// build desktops menu
    Menu desktopsMenu = new Menu("Desktop");
    
    // create desktop menu items
    for ( String desktopName : desktopManager.desktopNames()) {
    	String desktopMenuEntryTitle = desktopName.substring(0,1).toUpperCase() + desktopName.substring(1).toLowerCase();
    	MenuItem menuItem = new MenuItem(desktopMenuEntryTitle);

    	menuItem.setOnAction(new EventHandler<ActionEvent>() {
    		@Override public void handle(ActionEvent e) {
    			desktopManager.showDesktop(desktopName);
    		}
			});
    	desktopsMenu.getItems().add(menuItem);
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
    	// in case of MacOS app we put it on a system bar to look more like a native application
  		menuBar.setUseSystemMenuBar(true);
		}
  
    // add menu to menubar 
    menuBar.getMenus().add(desktopsMenu);

    // main vbox layout
    VBox vBox = new VBox(); // main window layout
    vBox.setStyle("-fx-background-color: green;"); // for layouts testing
    vBox.getChildren().addAll(menuBar, toolBar);

    // set default desktop to main layout 
    vBox.getChildren().add(desktopManager);
    VBox.setVgrow(desktopManager, Priority.ALWAYS); // ensure desktop manager pane uses all available layout space

    // create app window and set style
    Group root = new Group();
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
