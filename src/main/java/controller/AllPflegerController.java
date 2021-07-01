package controller;

import datastorage.PflegerDAO;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Pfleger;
import datastorage.DAOFactory;
import java.sql.SQLException;
import java.util.List;

public class AllPflegerController {

    @FXML
    private TableView<Pfleger> tableView;
    @FXML
    private TableColumn<Pfleger, Integer> colID;
    @FXML
    private TableColumn<Pfleger, String> colFirstName;
    @FXML
    private TableColumn<Pfleger, String> colSurname;
    @FXML
    private TableColumn<Pfleger, String> colTelefonNumber;

    @FXML
    Button btnDelete;
    @FXML
    Button btnAdd;
    @FXML
    TextField txtFirstname;
    @FXML
    TextField txtSurname;
    @FXML
    TextField txtTelefonNumber;
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtPassword;
    @FXML
    TextField txtConfirmPassword;

    private ObservableList<Pfleger> tableviewContent = FXCollections.observableArrayList();
    private PflegerDAO dao;

    /**
     * Initializes the corresponding fields. Is called as soon as the corresponding FXML file is to be displayed.
     */
    public void initialize() {
        readAllAndShowInTableView();

        this.colID.setCellValueFactory(new PropertyValueFactory<Pfleger, Integer>("pfid"));

        //CellValuefactory zum Anzeigen der Daten in der TableView
        this.colFirstName.setCellValueFactory(new PropertyValueFactory<Pfleger, String>("firstName"));
        //CellFactory zum Schreiben innerhalb der Tabelle
        this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colSurname.setCellValueFactory(new PropertyValueFactory<Pfleger, String>("surname"));
        this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colTelefonNumber.setCellValueFactory(new PropertyValueFactory<Pfleger, String>("telefonNumber"));
        this.colTelefonNumber.setCellFactory(TextFieldTableCell.forTableColumn());

        //Anzeigen der Daten
        this.tableView.setItems(this.tableviewContent);
    }

    /**
     * handles new firstname value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditFirstname(TableColumn.CellEditEvent<Pfleger, String> event){
        event.getRowValue().setFirstName(event.getNewValue());
        doUpdate(event);
    }

    /**
     * handles new surname value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditSurname(TableColumn.CellEditEvent<Pfleger, String> event){
        event.getRowValue().setSurname(event.getNewValue());
        doUpdate(event);
    }

    /**
     * handles new birthdate value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditTelefonNumber(TableColumn.CellEditEvent<Pfleger, String> event){
        event.getRowValue().setTelefonNumber(event.getNewValue());
        doUpdate(event);
    }

    /**
     * updates a patient by calling the update-Method in the {@link PflegerDAO}
     * @param t row to be updated by the user (includes the patient)
     */
    private void doUpdate(TableColumn.CellEditEvent<Pfleger, String> t) {
        try {
            dao.update(t.getRowValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * calls readAll in {@link PflegerDAO} and shows patients in the table
     */
    private void readAllAndShowInTableView() {
        this.tableviewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createPflegerDAO();
        List<Pfleger> allPflegers;
        try {
            allPflegers = dao.readAll();
            for (Pfleger p : allPflegers) {
                this.tableviewContent.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a delete-click-event. Calls the delete methods in the {@link PflegerDAO}
     */
    @FXML
    public void handleDeleteRow() {
        TreatmentDAO tDao = DAOFactory.getDAOFactory().createTreatmentDAO();
        Pfleger selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        try {
            tDao.deleteByPid(selectedItem.getPfid());
            dao.deleteById(selectedItem.getPfid());
            this.tableView.getItems().remove(selectedItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a add-click-event. Creates a patient and calls the create method in the {@link PflegerDAO}
     */
    @FXML
    public void handleAdd() {
        boolean confirmPass = validatePassword(this.txtPassword.getText(), this.txtConfirmPassword.getText());

        if(!confirmPass || this.txtUsername.getText() == "") {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password bestätigen");
            alert.setHeaderText("Wählen Sie ein Password mit mehr als 8 Ziffern");
            alert.setContentText("Bitte bestätigen Sie Ihr Password");

            alert.showAndWait();
        }else{
        String firstname = this.txtFirstname.getText();
        String surname = this.txtSurname.getText();
        String telefonnumber = this.txtTelefonNumber.getText();
        String username = this.txtUsername.getText();
        String password = this.txtPassword.getText();
        try {
            Pfleger p = new Pfleger(firstname, surname, telefonnumber, username, password);
            dao.create(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        readAllAndShowInTableView();
        clearTextfields();
        }
    }

    /**
     * removes content from all textfields
     */
    private void clearTextfields() {
        this.txtFirstname.clear();
        this.txtSurname.clear();
        this.txtTelefonNumber.clear();
        this.txtUsername.clear();
        this.txtPassword.clear();
        this.txtConfirmPassword.clear();
    }


    private boolean validatePassword(String pass, String confirmPass) {
        if(pass.equals(confirmPass) && pass != "" && pass.length() > 8) {
            return true;
        }
        else {
            return false;
        }
    }

}
