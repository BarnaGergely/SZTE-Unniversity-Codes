package Varazslat;
import Public.*;

public class RandomVarazslat extends Varazslat {
    public RandomVarazslat() {
        nev = "Random";
        ar = 100;
        manna = 6;
    }
    public Csateter.EgysegCsataban[][] hasznal(Csateter.EgysegCsataban[][] matrix,Hos hos,Koordinata koordinata){
        // Egy véletlenszerű varázslatot véletlenszerű helyre meghív
        Csateter.EgysegCsataban[][] ujMatrix = matrix;
        int varazslat = Public.Csateter.getRandomNumber(0, 5);

        switch(varazslat) {
            case 1:
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

                break;
            case 2:
                if (ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()] != null){
                    System.out.println("        - Sebzés mértéke: "+hos.getVarazsero()*30);
                    ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].elet = ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].elet - (hos.getVarazsero()*30);
                    ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].mennyiseg = (float) ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].elet / ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].egyseg.getEletero();
                } else {
                    System.out.println("        - Itt nincs egység.");
                }

                break;
            default:
                if (ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()] != null){
                    ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].egyseg.setKezdemenyezes(ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].egyseg.getKezdemenyezes() -1);
                    System.out.println("        - Egység új kezdeményezése: "+ujMatrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].egyseg.getKezdemenyezes());
                } else {
                    System.out.println("        - Itt nincs egység.");
                }
        }
        System.out.println("        - "+this.getNev()+" varázslat végrehajtva.");
        return ujMatrix;
    }
}
