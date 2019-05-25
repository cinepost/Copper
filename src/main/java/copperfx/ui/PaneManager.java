package com.redsoft.copperfx.ui;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/* PaneManager class is the main ui element used in Desktops.
 * All internal and pluggable panes are loaded by this class on
 * startup and possible later on at run time. 
 * Basically it's a simple TabPane with extra functionality such as
 * new panes creation, panes detachment, movement and so on.
 */

public class PaneManager extends javafx.scene.control.TabPane {

  public PaneManager() {

    // some tabs for testing here
    Tab tab1 = new Tab();
    tab1.setText("Rectangle");
    tab1.setContent(new Rectangle(100, 100, Color.LIGHTSTEELBLUE));
   
    Tab tab2 = new Tab();
    tab2.setText("Line");
    tab2.setContent(new Line(0, 0, 100, 100)); 
   
    Tab tab3 = new Tab();
    tab3.setText("Circle");
    tab3.setContent(new Circle(0, 0, 50, Color.LIGHTGREEN)); 

    getSelectionModel().select(1);
    getTabs().addAll(tab1, tab2, tab3);
  }

}