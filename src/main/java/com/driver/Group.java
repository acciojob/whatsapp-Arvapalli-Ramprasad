package com.driver;

public class Group {
    private String name;
    private int numberOfParticipants;
    private User admin;


    public Group(String name, int numberOfParticipants, User admin) {
        this.name = name;
        this.numberOfParticipants = numberOfParticipants;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
}
