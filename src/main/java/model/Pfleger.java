package model;


public class Pfleger extends Person{
    private long pfId;
    private String telefon;
    private String username;
    private String password;


    public Pfleger(String firstname, String surname, String telefon, String username, String password){
        super(firstname, surname);
        this.telefon = telefon;
        this.username = username;
        this.password = password;
    }

    public Pfleger(long pdId, String firstname, String surname, String telefon, String username, String password){
        super(firstname, surname);
        this.pfId = pdId;
        this.telefon = telefon;
        this.username = username;
        this.password = password;
    }

    public long getPfid() {
        return pfId;
    }

    public String getTelefonNumber(){
        return telefon;
    }

    public void setTelefonNumber(String telefon){
        this.telefon = telefon;
    }

    public String toString() {
        return "Pfleger" + "\nMNID: " + this.pfId +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nTelefonnumber: " + this.telefon +
                "\n";
    }

    public String labelTreatmentView() {
        return this.getFirstName() + " " + this.getSurname() + " " + this.telefon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}