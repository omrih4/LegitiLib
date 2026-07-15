package me.omrih.legiti.client;

public class World {
    private final String name;
    private final String description;
    private final String owner;
    private final int votes; // defaults to 0 for worlds part of an ongoing jam
    private final int visits; // defaults to 0 for worlds part of an ongoing jam
    private final String created;
    private final String uuid;

    public World(String name, String description, String owner, int votes, int visits, String created, String uuid) {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.votes = votes;
        this.visits = visits;
        this.created = created;
        this.uuid = uuid;
    }
}
