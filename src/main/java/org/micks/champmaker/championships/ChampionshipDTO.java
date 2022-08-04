package org.micks.champmaker.championships;

public class ChampionshipDTO {

    private String name;
    private String city;
    private String date;

    public ChampionshipDTO() {
    }

    public ChampionshipDTO(String name, String city, String date) {
        this.name = name;
        this.city = city;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }
}
