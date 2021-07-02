package datastorage;

import model.Pfleger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Implements the Interface <code>LoginDAO</code>. Overrides methods to generate specific LoginDAO-SQL-queries
 */
public class LoginDAO extends DAOimp<Pfleger>{

    public LoginDAO(Connection conn) {
        super(conn);
    }
    /**
     * generates a <code>INSERT</code>-Statement for a given pfleger
     * @param pfleger for which a specific INSERT is to be created
     * @return <code>String</code> with the generated SQL.
     */
    protected String getCreateStatementString(Pfleger pfleger) {
        return String.format("INSERT INTO pfleger (firstname, surname, telefonnumber, username, password) VALUES ('%s', '%s', '%s', '%s', '%s')",
                pfleger.getFirstName(), pfleger.getSurname(), pfleger.getTelefonNumber(), pfleger.getUsername(), pfleger.getPassword());
    }
    /**
     * generates a <code>select</code>-Statement for a given key
     * @param key for which a specific SELECT is to be created
     * @return <code>String</code> with the generated SQL.
     */
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM pfleger WHERE pfid = %d", key);
    }
    /**
     *
     * @param  result
     * @return
     * @throws  <code>SQLException</code> with the generated SQL.
     */
    protected Pfleger getInstanceFromResultSet(ResultSet result) throws SQLException {
        Pfleger p = null;
        p = new Pfleger(result.getInt(1), result.getString(2),
                result.getString(3), result.getString(4), result.getString(5), result.getString(6));
        return p;
    }
    /**
     *  Read all Statement
     * @return <code>String</code> with the generated SQL.
     */
    protected String getReadAllStatementString() {
        return "SELECT * FROM pfleger";
    }
    /**
     * @param result
     * @return
     * @throws SQLException
     */
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
    /**
     *  generates a <code>getUpdate</code>-Statement for a given pfleger
     * @param pfleger for which a specific getUpdate is to be created
     * @return <code>String</code> with the generated SQL.
     */
    protected String getUpdateStatementString(Pfleger pfleger) {
        return String.format("UPDATE pfleger SET firstname = '%s', surname = '%s', telefonnumber = '%s' " +
                "WHERE pfid = %d", pfleger.getFirstName(), pfleger.getSurname(), pfleger.getTelefonNumber(), pfleger.getPfid() , pfleger.getUsername(), pfleger.getPassword());
    }
    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM pfleger WHERE pfid=%d", key);
    }
    /**
     *  generates a <code>Lock</code>-Statement for a given key
     * @param key for which a specific Lock is to be created
     * @return <code>String</code> with the generated SQL.
     */
    protected String getLockStatementString ( long key) {
        return String.format("Update patient SET lock = false WHERE pid=%d", key);
    }

    protected String getLockStatementStringTreatment ( long key) {
        return String.format("Delete FROM pfleger WHERE pfid=%d", key);
    }



}
