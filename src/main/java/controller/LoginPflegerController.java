package controller;

import datastorage.LoginDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Pfleger;
import datastorage.DAOFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class LoginPflegerController extends Main{

    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    Button button;
    @FXML
    Label wronglogin;

    private LoginDAO dao;
    private Pfleger pfleger;

    public void validateFields() throws SQLException, IOException {

        String userName = this.username.getText();
        String passWord = this.password.getText();

        StringBuilder errors = new StringBuilder();
        if (userName.equals(""))  errors.append("Username fehlt\n");
        if (passWord.equals("")) errors.append("Passwort fehlt");

        if (errors.toString() != "") {
            this.wronglogin.setText(errors.toString());
        } else {
            this.dao = DAOFactory.getDAOFactory().createLoginDAO();
            List<Pfleger> allPflegers = dao.readAll();
            for (Pfleger p : allPflegers) {
                if(userName.equals(p.getUsername()) && passWord.equals(p.getPassword())){
                    this.pfleger = p;
                }
            }
            if(pfleger == null) {
                errors.append("Falsch Eingaben. Prüfen Sie bitte Ihre Eingaben");
                this.wronglogin.setText(errors.toString());
            }else{
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("/MainWindowView.fxml"));
                BorderPane pane = loader.load();
                Scene scene = new Scene(pane);
                Stage loginStage = (Stage)username.getScene().getWindow();
                loginStage.setScene(scene);
                clearForm();
            }
        }

    }

    private void clearForm() {
        this.password.clear();
        this.username.clear();
        this.wronglogin.setText("");
    }


}







