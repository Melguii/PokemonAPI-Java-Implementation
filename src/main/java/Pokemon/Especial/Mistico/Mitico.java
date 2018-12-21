package Pokemon.Especial.Mistico;
import Pokemon.Pokemon;

public class Mitico extends Pokemon {
    private SpecialResearch special_research;
    private boolean enCurso = false;
    private boolean finalizada = false;

    public void SpecialResearchCompleted(){
        finalizada = true;
    }


    public boolean getEnCurso() {
        return enCurso;
    }

    private void setEnCurso() {
        this.enCurso = true;
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
        if (!finalizada){
            int x = special_research.checkSpecialResearch(id);
            if (x != 0){
                setEnCurso();
            }
            if (x == 100){
                return true;
            }
            return false;
        }else {
            return false;
        }
    }

    public void setSpecial_Research(SpecialResearch special_research) {
        this.special_research = special_research;
    }

    /**
     * Método aplicado en un polimorfismo que calcula la probabilidad de captura de un pokemon mítico
     * @param pb: el capture_rate de la pokeball
     * @return el valor que define la probabilidad de captura
     */
    @Override
    public double captureEcuation(double pb) {
        return (pb/pb)+(getCapture_rate()/getCapture_rate())/2;
    }
}
