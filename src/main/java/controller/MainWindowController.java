package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Pfleger;

import java.io.IOException;

/**
 * The <code>MainWindowController</code> contains the entire logic of the patient view. It determines which data is displayed and how to react to events.
 */
public class MainWindowController {

    @FXML
    private Button logout;
    @FXML
    private BorderPane mainBorderPane;

    public Pfleger pfleger;

    /**
     * get window handle Show All Caregiver
     * @param e
     */
    @FXML
    private void handleShowAllCaregiver(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllCaregiverView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllPflegerController controller = loader.getController();
    }
    /**
     * get window handle Show All Patient
     * @param e
     */
    @FXML
    private void handleShowAllPatient(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllPatientView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllPatientController controller = loader.getController();
    }
    /**
     * get window handle Show All Treatments
     * @param e
     */
    @FXML
    private void handleShowAllTreatments(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllTreatmentView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllTreatmentController controller = loader.getController();
    }
    /**
     * get window handle Logout
     * @param e
     */
    @FXML
    private void handleLogoutCaregiver(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/LoginView.fxml"));
        BorderPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage primaryStage = (Stage)logout.getScene().getWindow();
        primaryStage.setScene(scene);
    }

    /**
     * pass Caregiver from LoginView to MainWindowView
     * @param p
     */
    public void addPfleger(Pfleger p) {
        this.pfleger = p;
    }




}
