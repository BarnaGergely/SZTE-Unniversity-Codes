package Public;

import Egyseg.Egyseg;
import Public.Koordinata;
import Varazslat.Varazslat;

import java.lang.Integer;

public class Csateter {

    public class EgysegCsataban {
        public Egyseg egyseg;
        public int elet;
        public float mennyiseg;
        public boolean ellenfel;
        public boolean visszatamad;
    }

    public EgysegCsataban matrix[][];
    public int egysegekSzama;


    public Csateter() {
        this.matrix = new EgysegCsataban[10][12];
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public EgysegCsataban getMatrix(int hosszusag, int szelesseg) {
        return matrix[hosszusag][szelesseg];
    }

    public void setMatrix(Koordinata koordinata, Egyseg egyseg, float mennyiseg, boolean ellenfel) {
        if (this.matrix[koordinata.getHosszusag()][koordinata.getSzelesseg()] != null) {
            throw new RuntimeException("Ez a mező már foglallt. Válassz másikat!");
        } else {
            this.matrix[koordinata.getHosszusag()][koordinata.getSzelesseg()] = new EgysegCsataban();
            this.matrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].egyseg = egyseg;
            this.matrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].elet = (int) mennyiseg * egyseg.getEletero();
            this.matrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].mennyiseg = mennyiseg;
            this.matrix[koordinata.getHosszusag()][koordinata.getSzelesseg()].ellenfel = ellenfel;
        }
    }

    public void setMatrixNull(Koordinata koordinata) {
        this.matrix[koordinata.getHosszusag()][koordinata.getSzelesseg()] = null;
    }

    public void setMatrixEgyseg(Koordinata koordinata, EgysegCsataban egysegCsataban) {
        if (this.matrix[koordinata.getHosszusag()][koordinata.getSzelesseg()] != null) {
            throw new RuntimeException("Ez a mező már foglallt. Ide nem léphetsz");
        } else {
            this.matrix[koordinata.getHosszusag()][koordinata.getSzelesseg()] = egysegCsataban;
        }
    }

    public int getEgysegEletereje(String egyseg, boolean ellenfel) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                if (matrix[i][j] != null && matrix[i][j].egyseg.getNev() == egyseg && matrix[i][j].ellenfel == ellenfel) {
                    return matrix[i][j].elet;
                }
            }
        }
        return 0;
    }

    public EgysegCsataban[] getSorrend(int hosMoral, int ellenfelMoral) {
        egysegekSzama = 0;
        int moral;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                if (matrix[i][j] != null) {
                    egysegekSzama++;
                }
            }
        }

        EgysegCsataban[] egysegSorrend = new EgysegCsataban[egysegekSzama];
        int k = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                if (matrix[i][j] != null) {
                    egysegSorrend[k] = matrix[i][j];
                    k++;
                }
            }
        }

        boolean sorted = false;
        EgysegCsataban temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < egysegekSzama - 1; i++) {
                if (egysegSorrend[i].ellenfel){
                    moral = ellenfelMoral;
                } else {
                    moral = hosMoral;
                }
                if ((egysegSorrend[i].egyseg.getKezdemenyezes() + moral) * egysegSorrend[i].mennyiseg > (egysegSorrend[i + 1].egyseg.getKezdemenyezes() + moral) * egysegSorrend[i + 1].mennyiseg) {
                    temp = egysegSorrend[i];
                    egysegSorrend[i] = egysegSorrend[i + 1];
                    egysegSorrend[i + 1] = temp;
                    sorted = false;
                }
            }
        }
        return egysegSorrend;
    }

    public Koordinata getHely(EgysegCsataban egysegCsataban) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                if (matrix[i][j] == egysegCsataban) {
                    Koordinata hely = new Koordinata(j, i);
                    return hely;
                }
            }
        }
        return null;
    }

    public int getEgysegekSzama() {
        int egysegekSzama = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                if (matrix[i][j] != null) {
                    egysegekSzama++;
                }
            }
        }
        return egysegekSzama;
    }

    public void tamad(EgysegCsataban egysegCsataban, Koordinata koordinata, Hos hos, Hos ellenfel, boolean visszatamad) {
        // TODO visszatámadás
        if (!visszatamad) {
            System.out.print("      - Támadás indul ");
        } else {
            System.out.print("      - Vissza támadás indul ");
        }

        System.out.println("a szélesség: "+(koordinata.getSzelesseg()+1)+", hosszúság: "+(koordinata.getHosszusag()+1)+" koordinátára.");

        if (matrix[koordinata.hosszusag][koordinata.szelesseg] != null) {
            if (matrix[koordinata.hosszusag][koordinata.szelesseg].ellenfel != egysegCsataban.ellenfel) {
                int egysegelsosebzese = getRandomNumber(egysegCsataban.egyseg.getSebzesMin(), egysegCsataban.egyseg.getSebzesMax());
                float egysegalapsebzese = egysegelsosebzese * egysegCsataban.mennyiseg;
                float hostamadasa = ((float) hos.getTamadas() + 10) / 10;
                float ellenfelvedekezese = ((float) ellenfel.getVedekezes() * 5) / 100;

                int egysegSebzese = Math.round(egysegalapsebzese * hostamadasa * ellenfelvedekezese);
                // random kiritikus sebzés és ennek jelzése
                int kritikus = getRandomNumber(0, 100);
                if (kritikus < hos.getSzerencse() * 5) {
                    kritikus = 2;
                    System.out.println("      -Az egység kritikus támadást mér.");
                } else {
                    kritikus = 1;
                }

                // eldönti milyen támadás
                if (egysegCsataban.egyseg.getKepesseg() == null || egysegCsataban.egyseg.getKepessegNev().matches("Végtelen visszatámadás")) {

                    if (Math.abs(koordinata.hosszusag - getHely(egysegCsataban).hosszusag) <= 1 && Math.abs(koordinata.szelesseg - getHely(egysegCsataban).szelesseg) <= 1) {  // Egység egy sugarú környezetében lehet csak támadni

                        System.out.println("         -Sebzés mértéke: " + egysegSebzese * kritikus);

                        matrix[koordinata.hosszusag][koordinata.szelesseg].elet = matrix[koordinata.hosszusag][koordinata.szelesseg].elet - egysegSebzese * kritikus;
                        matrix[koordinata.hosszusag][koordinata.szelesseg].mennyiseg = (float) matrix[koordinata.hosszusag][koordinata.szelesseg].elet / matrix[koordinata.hosszusag][koordinata.szelesseg].egyseg.getEletero();

                        if (matrix[koordinata.hosszusag][koordinata.szelesseg].elet < 1) {
                            matrix[koordinata.hosszusag][koordinata.szelesseg] = null;
                            System.out.println("      -Az egység meghalt.");
                            return;
                        }

                    } else {
                        System.out.println("   -Ide nem támadhatsz.");
                        visszatamad = true;
                    }

                } else if (egysegCsataban.egyseg.getKepessegNev().matches("Lövés")) {
                    // melletted van az ellenfel
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 12; j++) {
                            if (Math.abs(i - getHely(egysegCsataban).hosszusag) <= 1 && Math.abs(j - getHely(egysegCsataban).szelesseg) <= 1) {
                                if (matrix[i][j] != null) {
                                    if (matrix[i][j].ellenfel) {
                                        System.out.println("   -Melletted van egy ellenfél, ezért nem támadhatsz");
                                        return;
                                    }
                                }
                            }
                        }
                    }

                    System.out.println("            -Sebzés mértéke: " + egysegSebzese * kritikus);

                    matrix[koordinata.hosszusag][koordinata.szelesseg].elet = matrix[koordinata.hosszusag][koordinata.szelesseg].elet - egysegSebzese * kritikus;
                    matrix[koordinata.hosszusag][koordinata.szelesseg].mennyiseg = (float) matrix[koordinata.hosszusag][koordinata.szelesseg].elet / matrix[koordinata.hosszusag][koordinata.szelesseg].egyseg.getEletero();

                    if (matrix[koordinata.hosszusag][koordinata.szelesseg].elet < 1) {
                        matrix[koordinata.hosszusag][koordinata.szelesseg] = null;
                        System.out.println("      -Az egység meghalt.");
                    }


                } else if (egysegCsataban.egyseg.getKepessegNev().matches("Végtelen visszatámadás")) {


                } else if (egysegCsataban.egyseg.getKepessegNev().matches("Gyógyítás")) {

                    System.out.println("         -Gyógyítás mérétéke: " + egysegSebzese * kritikus);

                    matrix[koordinata.hosszusag][koordinata.szelesseg].elet = matrix[koordinata.hosszusag][koordinata.szelesseg].elet + egysegSebzese * kritikus;
                    matrix[koordinata.hosszusag][koordinata.szelesseg].mennyiseg = (float) matrix[koordinata.hosszusag][koordinata.szelesseg].elet / matrix[koordinata.hosszusag][koordinata.szelesseg].egyseg.getEletero();
                }

                // TODO visszatámadás
                if (!visszatamad) {
                    if (egysegCsataban.egyseg.getKepessegNev().matches("Végtelen visszatámadás")) {
                        tamad(matrix[koordinata.hosszusag][koordinata.szelesseg], getHely(egysegCsataban), ellenfel, hos, true);
                    } else if (!matrix[koordinata.hosszusag][koordinata.szelesseg].visszatamad) {
                        tamad(matrix[koordinata.hosszusag][koordinata.szelesseg], getHely(egysegCsataban), ellenfel, hos, true);
                    } else {
                        System.out.println("   - Az egység ebben a körben már nem tud visszatámadni.");
                    }
                }


            } else {
                System.out.println("Saját hadsereged nem támadhatod meg.");
            }
        } else {
            System.out.println("Itt nincs megtámadható egység.");
        }
    }

    public void varazsol(Varazslat varazslat, Hos hos, Koordinata koordinata){
            this.matrix = varazslat.hasznal(this.matrix,hos, koordinata);
    }


    public void mozog(EgysegCsataban egysegCsataban, Koordinata koordinata) {
        int valtozo1 = Math.abs(koordinata.hosszusag - getHely(egysegCsataban).hosszusag);
        int valtozo2 = Math.abs(koordinata.szelesseg - getHely(egysegCsataban).szelesseg);
        if (valtozo1 <= egysegCsataban.egyseg.getSebesseg() &&  valtozo2 <= egysegCsataban.egyseg.getSebesseg()) {
            System.out.println("   - Az egység most mozog.");
            setMatrixNull(getHely(egysegCsataban));
            try {
                setMatrixEgyseg(koordinata, egysegCsataban);
            } catch (Exception ex) {
                System.out.println("    -" + ex);
            }
        } else {
            System.out.println("       - Ilyen messze nem tudsz elmenni. Csak "+egysegCsataban.egyseg.getSebesseg()+" az egység sebessége.");
        }
    }

    public void varakozik(EgysegCsataban egysegCsataban) {
        System.out.println("       - Az egység most várakozik.");
    }

    public void hosTamad(Koordinata koordinata, Hos hos) {

    }

}
