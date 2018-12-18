package Pokemon.Especial.Mistico;
import Pokemon.Pokemon;

public class Mitico extends Pokemon {
    private SpecialResearch special_research;
    private Boolean enCurso = false;
    private Boolean finalizada = false;

    public void SpecialResearchCompleted(){
        finalizada = true;
    }


    public Boolean getEnCurso() {
        return enCurso;
    }

    public void setEnCurso(Boolean enCurso) {
        this.enCurso = enCurso;
    }

    public SpecialResearch getSpecial_Research() {
        System.out.println("\t- "+special_research.getName() + " (" + getName() + "):");
        return special_research;
    }

    public  void ResetSpecialResearch(){
        special_research.reset();
        enCurso = false;
        finalizada = false;
    };

    public boolean checkSpecialResearch(int id){
        return special_research.checkSpecialResearch(id);
    }

    public void setSpecial_Research(SpecialResearch special_research) {
        this.special_research = special_research;
    }

    @Override
    public double captureEcuation(double pb) {
        return (pb/pb)+(getCapture_rate()/getCapture_rate())/2;
    }
}
