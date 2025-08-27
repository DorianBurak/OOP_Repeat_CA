package org.example.DTOs;

public class weaponDTO {
    private int id;
    private String name;
    private String type;
    private double weight;
    private int durability;
    private int attack;
    private String motivity;
    private String technique;

    // Default constructor
    public weaponDTO() {
    }

    // Constructor with all fields
    public weaponDTO(int id, String name, String type, double weight, int durability, int attack, String motivity, String technique) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.durability = durability;
        this.attack = attack;
        this.motivity = motivity;
        this.technique = technique;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getMotivity() {
        return motivity;
    }

    public void setMotivity(String motivity) {
        this.motivity = motivity;
    }

    public String getTechnique() {
        return technique;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }

    // Override toString method

    @Override
    public String toString() {
        return "weaponDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                ", durability=" + durability +
                ", attack=" + attack +
                ", motivity='" + motivity + '\'' +
                ", technique='" + technique + '\'' +
                '}';
    }
}


