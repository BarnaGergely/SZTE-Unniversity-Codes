package Varazslat;
import Public.*;

public class VillamcsapasVarazslat extends Varazslat {
    public VillamcsapasVarazslat() {
        nev= "Villámcsapás";
        ar = 60;
        manna = 5;
    }

    public Csateter.EgysegCsataban[][] hasznal(Csateter.EgysegCsataban[][] matrix, Hos hos, Koordinata koordinata){
        // TODO Egy kiválasztott ellenséges egységre (varázsero * 30) sebzés okozása
        Csateter.EgysegCsataban[][] ujMatrix = matrix;
        if (ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()] != null){
            System.out.println("        - Sebzés mértéke: "+hos.getVarazsero()*30);
            ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].elet = ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].elet - (hos.getVarazsero()*30);
            ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].mennyiseg = (float) ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].elet / ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].egyseg.getEletero();
            System.out.println("        - "+this.getNev()+" varázslat végrehajtva.");
        } else {
            System.out.println("        - Itt nincs egység.");
        }
        return ujMatrix;
    }

}
