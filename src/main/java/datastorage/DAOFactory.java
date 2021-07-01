package datastorage;

public class DAOFactory {

    private static DAOFactory instance;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public PflegerDAO createPflegerDAO() {
        return new PflegerDAO(ConnectionBuilder.getConnection());
    }

    public TreatmentDAO createTreatmentDAO() {
        return new TreatmentDAO(ConnectionBuilder.getConnection());
    }

    public PatientDAO createPatientDAO() {
        return new PatientDAO(ConnectionBuilder.getConnection());
    }

    public PflegerDAO createpPflegerDAO() { return new PflegerDAO(ConnectionBuilder.getConnection()); }

    public LoginDAO createLoginDAO() { return new LoginDAO(ConnectionBuilder.getConnection()); }


}