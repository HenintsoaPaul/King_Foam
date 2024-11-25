package holiday;

import java.io.Serializable;

public class Holiday implements Serializable {
    String country, iso;
    int year;
    String date, day, name, type;

    // Getters n Setters
    public String getCountry() {
        return country;
    }

    public void setCountry( String country ) {
        this.country = country;
    }

    public String getIso() {
        return iso;
    }

    public void setIso( String iso ) {
        this.iso = iso;
    }

    public int getYear() {
        return year;
    }

    public void setYear( int year ) {
        this.year = year;
    }

    public String getDate() {
        return date;
    }

    public void setDate( String date ) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay( String day ) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }
}
