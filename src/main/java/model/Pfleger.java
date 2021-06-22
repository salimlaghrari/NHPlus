package model;


public class Pfleger extends Person{
    private long pfId;
    private String telefonNumber;

    public Pfleger(String firstname, String surname, String telefon){
        super(firstname, surname);
        this.telefonNumber = telefon;
    }

    public Pfleger(long pdId, String firstname, String surname, String telefonNumber){
        super(firstname, surname);
        this.pfId = pdId;
        this.telefonNumber = telefonNumber;
    }

    public long getPfid() {
        return pfId;
    }

    public String getTelefonNumber(){
        return telefonNumber;
    }

    public void setTelefonNumber(String telefon){
        this.telefonNumber = telefon;
    }

    public String toString() {
        return "Pfleger" + "\nMNID: " + this.pfId +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nTelefonnumber: " + this.telefonNumber +
                "\n";
    }

    public String labelTreatmentView() {
        return this.getFirstName() + " " + this.getSurname() + " " + this.telefonNumber;
    }

}