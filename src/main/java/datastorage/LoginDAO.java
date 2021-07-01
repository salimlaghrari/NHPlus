package datastorage;

import model.Pfleger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDAO extends DAOimp<Pfleger>{

    public LoginDAO(Connection conn) {
        super(conn);
    }

    protected String getCreateStatementString(Pfleger pfleger) {
        return String.format("INSERT INTO pfleger (firstname, surname, telefonnumber, username, password) VALUES ('%s', '%s', '%s', '%s', '%s')",
                pfleger.getFirstName(), pfleger.getSurname(), pfleger.getTelefonNumber(), pfleger.getUsername(), pfleger.getPassword());
    }

    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM pfleger WHERE pfid = %d", key);
    }

    protected Pfleger getInstanceFromResultSet(ResultSet result) throws SQLException {
        Pfleger p = null;
        p = new Pfleger(result.getInt(1), result.getString(2),
                result.getString(3), result.getString(4), result.getString(5), result.getString(6));
        return p;
    }

    protected String getReadAllStatementString() {
        return "SELECT * FROM pfleger";
    }

    protected ArrayList<Pfleger> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Pfleger> list = new ArrayList<Pfleger>();
        Pfleger p = null;
        while (result.next()) {
            p = new Pfleger(result.getInt(1), result.getString(2),
                    result.getString(3), result.getString(4), result.getString(5), result.getString(6));
            list.add(p);
        }
        return list;
    }

    protected String getUpdateStatementString(Pfleger pfleger) {
        return String.format("UPDATE pfleger SET firstname = '%s', surname = '%s', telefonnumber = '%s' " +
                "WHERE pfid = %d", pfleger.getFirstName(), pfleger.getSurname(), pfleger.getTelefonNumber(), pfleger.getPfid() , pfleger.getUsername(), pfleger.getPassword());
    }

    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM pfleger WHERE pfid=%d", key);
    }

}
