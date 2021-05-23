package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Entity
public class Station extends Model {
    public String name;
    public double latitude;
    public double longitude;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<>();

    public Station(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName(){ // Used to sort stations in alphabetically
        return name;
    }

    public double celsiusToFarenheit() {
        if (!readings.isEmpty()) {
            return (readings.get(readings.size() - 1).temperature * (1.8) + 32);
        }
        return 0.0;
    }

    public double celsiusLatest() {
        if (!readings.isEmpty()) {
            return readings.get(readings.size() - 1).temperature;
        }
        return 0.0;
    }


    public int pressureLatest() {
        if (!readings.isEmpty()) {
            return readings.get(readings.size() - 1).pressure;
        }
        return 0;
    }

    public String weatherLatest() {
        if (!readings.isEmpty()) {
            int weatherCode = readings.get(readings.size() - 1).code;
            HashMap<Integer, String> weatherIcons = new HashMap<Integer, String>();
            weatherIcons.put(100, "Clear");
            weatherIcons.put(200, "Partial Clouds");
            weatherIcons.put(300, "Cloudy");
            weatherIcons.put(400, "Light Showers");
            weatherIcons.put(500, "Heavy Showers");
            weatherIcons.put(600, "Rain");
            weatherIcons.put(700, "Snow");
            weatherIcons.put(800, "Thunder");
            return weatherIcons.get(weatherCode);
        }
        return "No data entered";
    }


    public String weatherIcon(){
        if(!readings.isEmpty()) {
            int weatherCode = readings.get(readings.size() - 1).code;
            HashMap<Integer, String> weatherIcons = new HashMap<Integer, String>();
            weatherIcons.put(100, "sun icon");
            weatherIcons.put(200, "cloud sun icon");
            weatherIcons.put(300, "cloud icon");
            weatherIcons.put(400, "cloud sun rain icon");
            weatherIcons.put(500, "cloud showers heavy icon");
            weatherIcons.put(600, "cloud rain icon");
            weatherIcons.put(700, "snowflake icon");
            weatherIcons.put(800, "poo storm icon");
            return weatherIcons.get(weatherCode);
        }
        return "No data entered";
    }

    public String tempTrend() {
        if (readings.size() >= 3) {
            double Trend1 = (readings.get(readings.size() - 1).temperature);
            double Trend2 = (readings.get(readings.size() - 2).temperature);
            double Trend3 = (readings.get(readings.size() - 3).temperature);
            if (Trend1 > Trend2 && Trend2 > Trend3) {
                return "arrow up icon";
            } else if (Trend1 < Trend2 && Trend2 < Trend3) {
                return "arrow down icon";
            } else{
                return "arrows alternate horizontal icon";
            }
        }
        return "arrows alternate horizontal icon";
    }



    public String pressureTrend() {
        if (readings.size() >= 3) {
            double Trend1 = (readings.get(readings.size() - 1).pressure);
            double Trend2 = (readings.get(readings.size() - 2).pressure);
            double Trend3 = (readings.get(readings.size() - 3).pressure);

            if (Trend1 > Trend2 && Trend2 > Trend3) {
                return "arrow up icon";
            } else if (Trend1 < Trend2 && Trend2 < Trend3) {
                return "arrow down icon";
            } else{
                return "arrows alternate horizontal icon";
            }
        }
        return "arrows alternate horizontal icon";
    }

    public String windTrend() {
        if (readings.size() >= 3) { // Making sure there is 3 readings in the ArrayList before it allows the variables to be assigned values.
            double Trend1 = (readings.get(readings.size() - 1).windSpeed); //
            double Trend2 = (readings.get(readings.size() - 2).windSpeed);
            double Trend3 = (readings.get(readings.size() - 3).windSpeed);

            if (Trend1 > Trend2 && Trend2 > Trend3) {
                return "arrow up icon";
            } else if (Trend1 < Trend2 && Trend2 < Trend3) {
                return "arrow down icon";
            } else{
                return "arrows alternate horizontal icon";
            }
        }
        return "arrows alternate horizontal icon";
    }

    public double chillLatest() {
        if(!readings.isEmpty()) {
            double windSpeed = readings.get(readings.size() - 1).windSpeed;
            double result = ((13.12 + (0.6215 * celsiusLatest()) - (11.37 * (Math.pow(windSpeed, 0.16))) + ((0.3965 * celsiusLatest()) * (Math.pow(windSpeed, 0.16)))));
            double roundedResult = Math.round(result * 100.0) / 100.0;
            return roundedResult;
        }
        return 0.0;
    }

    public String windCompass() {
        String weatherCompass = "";
        if (!readings.isEmpty()) {
            if (readings.get(readings.size() - 1).windDirection >= 348.77 && readings.get(readings.size() - 1).windDirection < 11.25) {
                weatherCompass = weatherCompass + "North";
            } else if (readings.get(readings.size() - 1).windDirection >= 11.25 && readings.get(readings.size() - 1).windDirection < 33.75) {
                weatherCompass = weatherCompass + "North North East";
            } else if (readings.get(readings.size() - 1).windDirection >= 33.75 && readings.get(readings.size() - 1).windDirection < 56.25) {
                weatherCompass = weatherCompass + "North East";
            } else if (readings.get(readings.size() - 1).windDirection >= 56.25 && readings.get(readings.size() - 1).windDirection < 78.75) {
                weatherCompass = weatherCompass + "East North East";
            } else if (readings.get(readings.size() - 1).windDirection >= 78.75 && readings.get(readings.size() - 1).windDirection < 101.25) {
                weatherCompass = weatherCompass + "East";
            } else if (readings.get(readings.size() - 1).windDirection >= 101.25 && readings.get(readings.size() - 1).windDirection < 123.75) {
                weatherCompass = weatherCompass + "East South East";
            } else if (readings.get(readings.size() - 1).windDirection >= 123.75 && readings.get(readings.size() - 1).windDirection < 146.25) {
                weatherCompass = weatherCompass + "South East";
            } else if (readings.get(readings.size() - 1).windDirection >= 146.25 && readings.get(readings.size() - 1).windDirection < 168.75) {
                weatherCompass = weatherCompass + "South South East";
            } else if (readings.get(readings.size() - 1).windDirection >= 168.75 && readings.get(readings.size() - 1).windDirection < 191.25) {
                weatherCompass = weatherCompass + "South";
            } else if (readings.get(readings.size() - 1).windDirection >= 191.25 && readings.get(readings.size() - 1).windDirection < 213.75) {
                weatherCompass = weatherCompass + "South South West";
            } else if (readings.get(readings.size() - 1).windDirection >= 213.75 && readings.get(readings.size() - 1).windDirection < 236.25) {
                weatherCompass = weatherCompass + "South West";
            } else if (readings.get(readings.size() - 1).windDirection >= 236.25 && readings.get(readings.size() - 1).windDirection < 258.75) {
                weatherCompass = weatherCompass + "West South West";
            } else if (readings.get(readings.size() - 1).windDirection >= 258.75 && readings.get(readings.size() - 1).windDirection < 281.25) {
                weatherCompass = weatherCompass + "West";
            } else if (readings.get(readings.size() - 1).windDirection >= 281.25 && readings.get(readings.size() - 1).windDirection < 303.75) {
                weatherCompass = weatherCompass + "West North West";
            } else if (readings.get(readings.size() - 1).windDirection >= 303.75 && readings.get(readings.size() - 1).windDirection < 303.75) {
                weatherCompass = weatherCompass + "North West";
            } else if (readings.get(readings.size() - 1).windDirection >= 326.25 && readings.get(readings.size() - 1).windDirection < 303.75) {
                weatherCompass = weatherCompass + "North North West";
            }
        }
        return weatherCompass;
    }

    public int windSpeedLatest() {
        if (!readings.isEmpty()) {
            if (readings.get(readings.size() - 1).windSpeed <= 1) {
                return 0;
            } else if (readings.get(readings.size() - 1).windSpeed > 1 && readings.get(readings.size() - 1).windSpeed <= 5) {
                return 1;
            } else if (readings.get(readings.size() - 1).windSpeed > 6 && readings.get(readings.size() - 1).windSpeed <= 11) {
                return 2;
            } else if (readings.get(readings.size() - 1).windSpeed > 12 && readings.get(readings.size() - 1).windSpeed <= 19) {
                return 3;
            } else if (readings.get(readings.size() - 1).windSpeed > 20 && readings.get(readings.size() - 1).windSpeed <= 28) {
                return 4;
            } else if (readings.get(readings.size() - 1).windSpeed > 29 && readings.get(readings.size() - 1).windSpeed <= 38) {
                return 5;
            } else if (readings.get(readings.size() - 1).windSpeed > 39 && readings.get(readings.size() - 1).windSpeed <= 49) {
                return 6;
            } else if (readings.get(readings.size() - 1).windSpeed > 58 && readings.get(readings.size() - 1).windSpeed <= 61) {
                return 7;
            } else if (readings.get(readings.size() - 1).windSpeed > 62 && readings.get(readings.size() - 1).windSpeed <= 74) {
                return 8;
            } else if (readings.get(readings.size() - 1).windSpeed > 75 && readings.get(readings.size() - 1).windSpeed <= 88) {
                return 9;
            } else if (readings.get(readings.size() - 1).windSpeed > 89 && readings.get(readings.size() - 1).windSpeed <= 102) {
                return 10;
            } else
                return 11;
        }
        return 0;
    }

    public double getMinWindSpeed() {
        Reading minWindSpeed = new Reading(null, 0, 0.0, 0.0, 0, 0.0);
        if (readings.size() > 0) {
            minWindSpeed = readings.get(0);
            for (Reading reading : readings) {
                if (reading.windSpeed < minWindSpeed.windSpeed) {
                    minWindSpeed = reading;
                }
            }
        }
        return minWindSpeed.windSpeed;
    }

    public double getMaxWindSpeed() {
        Reading maxWindSpeed = new Reading(null, 0, 0.0, 0.0, 0, 0.0); //Creating an object of type reading but assigning all the values a number to prevent NullPointerException
        if (readings.size() > 0) {
            maxWindSpeed = readings.get(0);
            for (Reading reading : readings) {
                if (reading.windSpeed > maxWindSpeed.windSpeed) {
                    maxWindSpeed = reading;
                }
            }
        }
        return maxWindSpeed.windSpeed;
    }

    public double getMinPressure() {
        Reading minPressure = new Reading(null, 0, 0.0, 0.0, 0, 0.0);
        if (readings.size() > 0) {
            minPressure = readings.get(0);
            for (Reading reading : readings) {
                if (reading.pressure < minPressure.pressure) {
                    minPressure = reading;
                }
            }
        }
        return minPressure.pressure;
    }

    public double getMaxPressure() {
        Reading maxPressure = new Reading(null, 0, 0.0, 0.0, 0, 0.0);
        if (readings.size() > 0) {
            maxPressure = readings.get(0);
            for (Reading reading : readings) {
                if (reading.pressure > maxPressure.pressure) {
                    maxPressure = reading;
                }
            }
        }
        return maxPressure.pressure;
    }

    public double getMinTemp() {
        Reading minTemp = new Reading(null, 0, 0.0, 0.0, 0, 0.0);
        if (readings.size() > 0) {
            minTemp = readings.get(0);
            for (Reading reading : readings) {
                if (reading.temperature < minTemp.temperature) {
                    minTemp = reading;
                }
            }
        }
        return minTemp.temperature;
    }

    public double getMaxTemp() {
        Reading maxTemp = new Reading(null, 0, 0.0, 0.0, 0, 0.0);
        if (readings.size() > 0) {
            maxTemp = readings.get(0);
            for (Reading reading : readings) {
                if (reading.temperature > maxTemp.temperature) {
                    maxTemp = reading;
                }
            }
        }
        return maxTemp.temperature;
    }
}










