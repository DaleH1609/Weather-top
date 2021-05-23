package controllers;


import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.util.Date;
import java.sql.Timestamp;

public class StationCtrl extends Controller {
    public static void index(Long id)
    {
        Station station = Station.findById(id);
        Logger.info("Station id = " + id);
        render("station.html", station);
    }

    public static void deleteReading(Long id, Long readingid)
    {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info ("Removing " + station.name);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        render("station.html", station);
    }

    public static void addReading(Long id, int code, double temperature, double windSpeed, int pressure, double windDirection)
    {
        Station station = Station.findById(id);
        Date date = new Date(System.currentTimeMillis());
        Reading reading = new Reading(date, code, temperature, windSpeed, pressure, windDirection);
        station.readings.add(reading);
        station.save();
        redirect ("/stations/" + id);
    }
}
