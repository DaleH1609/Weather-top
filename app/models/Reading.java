package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;


@Entity
public class Reading extends Model {
    public Date date;
    public int code;
    public double temperature;
    public double windSpeed;
    public int pressure;
    public double windDirection;


    public Reading(Date date, int code, double temperature, double windSpeed, int pressure, double windDirection) {
        this.date = date;
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;
    }

    public Date getDate(){
        return date;
    }

}


