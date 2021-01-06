package Pokemon.Especial.Mistico;
import Pokemon.Pokemon;

public class Mitico extends Pokemon {
    private SpecialResearch special_research;
    private boolean enCurso = false;
    private boolean finalizada = false;

    public void SpecialResearchCompleted(){
        finalizada = true;
    }

    /**
     * Getter de si la SpecialResearch esta en curso o no
     * @return si la SpecialResearch esta en curso o no
     */
    public boolean getEnCurso() {
        return enCurso;
    }

    /**
     * Setter de la SpecialResearch para saber si esta en curso o no
     */
    private void setEnCurso() {
        this.enCurso = true;
    }

    /**
     * Obtiene una SpecialResearch
     * @return la SpecialResearch del pokemon
     */
    public SpecialResearch getSpecial_Research() {
        System.out.println("\t- "+special_research.getName() + " (" + getName() + "):");
        return special_research;
    }

    /**
     * Pone la SpecialResearch a su estado incial
     */
    public  void ResetSpecialResearch(){
        special_research.reset();
        enCurso = false;
        finalizada = false;
    };

    /**
     * Comprueba si la SpecialResearch esta finalizada o no
     * @param id
     * @return True si esta finalizada, false si no lo esta
     */
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

    /**
     * Setter de una SpecialResearch
     * @param special_research
     */
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
