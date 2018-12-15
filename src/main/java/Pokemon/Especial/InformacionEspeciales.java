package Pokemon.Especial;

import Pokemon.Especial.Legendario.Gym;
import Pokemon.Especial.Mistico.SpecialResearch;

public class InformacionEspeciales {

    private Integer id;
    private String kind;
    private Gym gym;
    private SpecialResearch specialResearch;


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