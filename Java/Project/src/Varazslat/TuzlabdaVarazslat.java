package Varazslat;
import Public.*;

public class TuzlabdaVarazslat extends Varazslat {
    public TuzlabdaVarazslat() {
        nev= "Tűzlabda";
        ar = 120;
        manna = 9;
    }


    public Csateter.EgysegCsataban[][] hasznal(Csateter.EgysegCsataban[][] matrix,Hos hos,Koordinata koordinata){
        // TODO Egy kiválasztott mezo körüli 3x3-as területen lév® összes (saját, illetve ellenséges) egységre (varázsero * 20) sebzés okozása
        Csateter.EgysegCsataban[][] ujMatrix = matrix;
        System.out.println("        - Sebzés mértéke: "+hos.getVarazsero()*20);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                if (Math.abs(koordinata.getHosszusag() - i) <= 1 && Math.abs(koordinata.getSzelesseg() - j )<= 1) {
                    if (ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()] != null){
                        ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].elet = ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].elet - hos.getVarazsero()*20;
                        ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].mennyiseg = (float) ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].elet / ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].egyseg.getEletero();
                    } else {
                        System.out.println("        - Itt nincs egység.");
                    }
                }
            }
        }
        System.out.println("        - "+this.getNev()+" varázslat végrehajtva.");
        return ujMatrix;
    }
}
