package Varazslat;
import Public.*;

public class KedvetlenitesVarazslat extends Varazslat {
    public KedvetlenitesVarazslat() {
        nev = "Kedvetlenítés";
        ar = 110;
        manna = 7;
    }

    public Csateter.EgysegCsataban[][] hasznal(Csateter.EgysegCsataban[][] matrix, Hos hos, Koordinata hely){
        // Egy kiválasztott mezon álló hősök kedvezményezését csökkenti 1-el
        Csateter.EgysegCsataban[][] ujMatrix = matrix;
        if (ujMatrix[hely.getHosszusag()][hely.getSzelesseg()] != null){
            ujMatrix[hely.getHosszusag()][hely.getSzelesseg()].egyseg.setKezdemenyezes(ujMatrix[hely.getHosszusag()][hely.getSzelesseg()].egyseg.getKezdemenyezes() -1);
            System.out.println("        - Egység új kezdeményezése: "+ujMatrix[hely.getHosszusag()][hely.getSzelesseg()].egyseg.getKezdemenyezes());
            System.out.println("        - "+this.getNev()+" varázslat végrehajtva.");
        } else {
            System.out.println("        - Itt nincs egység.");
        }
        return ujMatrix;
    }
}