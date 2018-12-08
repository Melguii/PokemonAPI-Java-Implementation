package com.Pokemon.Legendario;

public class Pokedex {

    private Integer id;
    private String kind;
    private Gym gym;
    private SpecialResearch specialResearch;

    /**
     * No args constructor for use in serialization
     *
     */
    public Pokedex() {
    }

    /**
     *
     * @param id
     * @param specialResearch
     * @param gym
     * @param kind
     */
    public Pokedex(Integer id, String kind, Gym gym, SpecialResearch specialResearch) {
        super();
        this.id = id;
        this.kind = kind;
        this.gym = gym;
        this.specialResearch = specialResearch;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public SpecialResearch getSpecialResearch() {
        return specialResearch;
    }

    public void setSpecialResearch(SpecialResearch specialResearch) {
        this.specialResearch = specialResearch;
    }

}