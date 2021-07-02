package datastorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific DAOimp-SQL-queries.
 * @param <T>
 */
public abstract class DAOimp<T> implements DAO<T>{

    protected Connection conn;

    public DAOimp(Connection conn) {
        this.conn = conn;
    }
    /**
     *
     * @param t
     * @throws SQLException
     */
    @Override
    public void create(T t) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getCreateStatementString(t));
    }
    /**
     *
     * @param key  for which a specific LOCK is to be created
     * @return
     * @throws SQLException
     */
    @Override
    public T read(long key) throws SQLException {
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadByIDStatementString(key));
        if (result.next()) {
            object = getInstanceFromResultSet(result);
        }
        return object;
    }
    /**
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<T> readAll() throws SQLException {
        ArrayList<T> list = new ArrayList<T>();
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllStatementString());
        list = getListFromResultSet(result);
        return list;
    }
    /**
     *
     * @param t
     * @throws SQLException
     */
    @Override
    public void update(T t) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getUpdateStatementString(t));
    }
    /**
     *
     * @param key for which a specific LOCK is to be created
     * @throws SQLException
     */
    @Override
    public void deleteById(long key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getDeleteStatementString(key));
    }
    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @throws SQLException
     */
    @Override
    public void LockById(long key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getLockStatementString(key));
        st.executeUpdate(getLockStatementStringTreatment(key));
    }


    protected abstract String getCreateStatementString(T t);

    protected abstract String getReadByIDStatementString(long key);

    protected abstract T getInstanceFromResultSet(ResultSet set) throws SQLException;

    protected abstract String getReadAllStatementString();

    protected abstract ArrayList<T> getListFromResultSet(ResultSet set) throws SQLException;

    protected abstract String getUpdateStatementString(T t);

    protected abstract String getDeleteStatementString(long key);

    protected abstract String getLockStatementString(long key);

    protected abstract String getLockStatementStringTreatment(long key);

}
