package com.redsoft.copperfx.ui;

import java.io.IOException;
import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/* DesktopManager class is used to load desktop fxml definitions and
 * display requested desktop. Used inside application window layout.
 */

public class DesktopManager extends javafx.scene.layout.Pane {

  private static final Logger logger = LoggerFactory.getLogger(DesktopManager.class);

  ObservableMap<String, Desktop> desktops = FXCollections.observableHashMap();
  private Desktop currentDesktop;

  public DesktopManager() {
    // load desktop FXML definitions
    logger.debug("loading desktop fxml definitions ...");

    File desktopsDirectory = new File("/Users/max/dev/Copper/resources/desktops");
    String [] desktopsDirectoryContents = desktopsDirectory.list();

    for (String filename : desktopsDirectoryContents) {
      Desktop desk = new Desktop(new File(String.valueOf(desktopsDirectory), filename));
      desktops.put(desk.getId(), desk);
    }

    setStyle("-fx-background-color: gray;"); // for layouts testing
    /*
    this.setMaxWidth(Double.MAX_VALUE);
    this.setMaxHeight(Double.MAX_VALUE);

    setPrefWidth(Double.MAX_VALUE);
    setPrefHeight(Double.MAX_VALUE);

    this.widthProperty().addListener(new ChangeListener() {
      public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        System.out.println(oldValue + "|" + newValue);
      }
    });
    */
  }

  public DesktopManager(String defaultDisplayName) {
    this();
    showDesktop(defaultDisplayName);
    this.setMaxWidth(Double.MAX_VALUE);
    this.setMaxHeight(Double.MAX_VALUE);
  }

  public String[] desktopNames() {
    String[] ret = new String[desktops.size()];

    int i = 0;
    for( String key: desktops.keySet()) {
      ret[i] = key; i++;
    }
    return ret;
  }

  public void showDesktop(String desktopName) {
    if ( desktops.containsKey(desktopName)) {
      getChildren().remove(currentDesktop); // detach current desktop from manager pane
      currentDesktop = desktops.get(desktopName);
      logger.debug("switching to desktop: " + desktopName);
      getChildren().add(currentDesktop);
    } else {
      logger.error("can not switch to unavailable desktop: " + desktopName);
    }
  }

}