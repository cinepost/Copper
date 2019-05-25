package com.redsoft.copperfx.ui;

import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.net.MalformedURLException;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/* Desktop class defined by fxml is used to display all necessary
 * ui elements (mostly PaneManagers). You can have multiple desktops
 * loaded and managed by DesktopManager class.
 *
 * e.g. Separate desktops tailored for specific tasks such as modeling,
 * compositing, image editing, animation .... whatever.
 */

public class Desktop extends javafx.scene.layout.VBox {
  @FXML private TextField textField;

  public Desktop(File desktopFXMLFile) {
    FXMLLoader fxmlLoader = new FXMLLoader();//getClass().getResource(desktopFXMLFileName));

    try{
      URL desktopFXMLUrl = new URL(desktopFXMLFile.toURI().toString());

      fxmlLoader.setLocation(desktopFXMLUrl);
      fxmlLoader.setRoot(this);
      fxmlLoader.setController(this);

    } catch(MalformedURLException ex) {
      //do exception handling here
    }

    try {
      System.out.println("Loading desktop fxml definition: " + desktopFXMLFile.toURI().toString());
      fxmlLoader.load();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  public String getText() {
    return textProperty().get();
  }

  public void setText(String value) {
    textProperty().set(value);
  }

  public StringProperty textProperty() {
    return textField.textProperty();
  }

  @FXML
  protected void doSomething() {
    System.out.println("The button was clicked!");
  }
}