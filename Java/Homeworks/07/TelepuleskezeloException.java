public class TelepuleskezeloException extends Exception{
    private Telepules okozo;

    public TelepuleskezeloException(Telepules okozo, String kivetel){
        super(kivetel);
        this.okozo = okozo;
    }

    public Telepules getOkozo() {
        return okozo;
    }
}
