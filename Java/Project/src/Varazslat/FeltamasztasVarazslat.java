package Varazslat;
import Public.*;

public class FeltamasztasVarazslat extends Varazslat {
    public FeltamasztasVarazslat() {
        nev = "Feltámasztás";
        ar = 120;
        manna = 6;
    }

        
    public Csateter.EgysegCsataban[][] hasznal(Csateter.EgysegCsataban[][] matrix, Hos hos, Koordinata koordinata){
        // Egy kiválasztott saját egység feltámasztása. Maximális gyógyítás mértéke: (varázsero * 50) (de az eredeti egységszámnál több nem lehet)
        Csateter.EgysegCsataban[][] ujMatrix = matrix;
        if (ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()] != null){
            ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].elet = ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].elet + (hos.getVarazsero()*50);
            ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].mennyiseg = (float) ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].elet / ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].egyseg.getEletero();
        } else {
            System.out.println("        - Itt nincs egység.");
        }
        System.out.println("        - "+this.getNev()+" varázslat végrehajtva.");
        return ujMatrix;
    }
}
