package utils;

public class CheckType {
    /**
     * Metodo que devuelve si el tipo de elemento que le pasamos es numerico o un string
     * @param var
     */
    public boolean checkType(String var) {

        boolean numeric = true;

        try {

            Double num = Double.parseDouble(var);

        } catch (NumberFormatException e) {

            numeric = false;

        }

        return numeric;
    }
}
