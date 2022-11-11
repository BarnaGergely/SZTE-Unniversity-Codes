import Egyseg.*;
import Public.Csateter;
import Public.Hos;

public class Portal {

    public static String formatNarrator(String szoveg) {
        Main.delay(500);
        return KonzolSzinek.ITALIC + "     - " + szoveg + KonzolSzinek.RESET;

    }
    public static void hosKiirat(Hos hos, Elokeszites elokeszites) {
        Main.delay(200);
        System.out.println();
        System.out.println(Main.BOLD + "             ADATOK" + Main.RESET);
        Main.delay(200);
        System.out.println("----------------------------------------" + Main.RESET);
        System.out.println("      - Arany: " + elokeszites.getArany());
        Main.delay(200);
        System.out.println("      - Frakció: " + hos.getFrakcio());

        Main.delay(200);
        System.out.println();
        System.out.println(Main.BOLD + "Hős tulajdonságai:" + Main.RESET);
        Main.delay(200);
        System.out.println("  - Támadás: " + hos.getTamadas());
        Main.delay(200);
        System.out.println("  - Védekezés: " + hos.getVedekezes());
        Main.delay(200);
        System.out.println("  - Varázserő: " + hos.getVarazsero());
        Main.delay(200);
        System.out.println("  - Morál: " + hos.getMoral());
        Main.delay(200);
        System.out.println("  - Szerencse: " + hos.getSzerencse());
        Main.delay(200);
        System.out.println("  - Manna: " + hos.getManna());

        System.out.println("----------------------------------------" + Main.RESET);
    }

    public static void varazslatKiirat(Hos hos) {
        Main.delay(200);
        System.out.println();
        System.out.println("----------------------------------------" + Main.RESET);
        System.out.println(Main.BOLD + "             VARÁZSLATOK" + Main.RESET);

        for (String i:
             hos.getVarazslatok().keySet()) {
            System.out.println("  - " + i);
        }
        System.out.println("  Még " + hos.getManna() + " mannád van.");
        System.out.println("----------------------------------------" + Main.RESET);
        System.out.println();

    }

    public static void egysegKiirat(Egyseg egyseg) {
        Main.delay(200);
        System.out.println(Main.BOLD + egyseg.getNev() + ":" + Main.RESET);
        String leftAlignFormat = "   %20s %-4d %n";
        System.out.format(leftAlignFormat, "Ár: ", egyseg.getAr());
        System.out.format(leftAlignFormat, "Sebzés minimum: ", egyseg.getSebzesMin());
        System.out.format(leftAlignFormat, "Sebzés maximum: ", egyseg.getSebzesMax());
        System.out.format(leftAlignFormat, "Életerő: ", egyseg.getEletero());
        System.out.format(leftAlignFormat, "Kezdeményezés: ", egyseg.getKezdemenyezes());
        if (egyseg.getKepesseg() == null) {
            System.out.format("   %-18s %-4s %n", "Speciális képesség: ", "nincs");
        } else {
            System.out.format("   %-18s %-4s %n", "Speciális képesség: ", egyseg.getKepessegNev());
        }
    }

    public static void sorrendKiirat(Public.Csateter csateter, int hosMoral, int ellenfelMoral){
        int i = 0;

        System.out.println();
        System.out.println("   Egységek sorrendje:");
        for (Csateter.EgysegCsataban egysegCsataban2 : csateter.getSorrend(hosMoral, ellenfelMoral)) {
            i++;
            if (egysegCsataban2 != null) {
                System.out.println(      i+". "+egysegCsataban2.egyseg.getNev()+" "+(egysegCsataban2.ellenfel ? "ellenfél":""));
            }
        }
    }

    public static void csataterKiirat(Public.Csateter csateter) {

        String leftAlignFormat = "| %-3s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s |%n";

        boolean ellenfel = false;
        Main.delay(200);
        System.out.println(Main.BLUE+"     Egységeid: " + Main.RESET);
        Main.delay(200);
        if (Math.round(csateter.getEgysegEletereje("Földműves", ellenfel)) != 0) {
            System.out.println(Main.BLUE+formatNarrator(Main.BLUE+"Földműves: " + Math.round(csateter.getEgysegEletereje("Földműves", ellenfel) / 3) + "db sérülésmentes, 1db " + csateter.getEgysegEletereje("Földműves", ellenfel) % 3)+Main.BLUE + " életerős"+Main.RESET);
        }
        Main.delay(200);
        if (Math.round(csateter.getEgysegEletereje("Íjász", ellenfel)) != 0) {
            System.out.println(Main.BLUE+formatNarrator(Main.BLUE+"Íjász: " + Math.round(csateter.getEgysegEletereje("Íjász", ellenfel) / 7) + "db sérülésmentes, 1db " + csateter.getEgysegEletereje("Íjász", ellenfel) % 7)+Main.BLUE + " életerős"+Main.RESET);
        }
        Main.delay(200);
        if (Math.round(csateter.getEgysegEletereje("Griff", ellenfel)) != 0) {
            System.out.println(Main.BLUE+formatNarrator(Main.BLUE+"Griff: " + Math.round(csateter.getEgysegEletereje("Griff", ellenfel) / 30) + "db sérülésmentes, 1db " + csateter.getEgysegEletereje("Griff", ellenfel) % 30)+Main.BLUE + " életerős"+Main.RESET);
        }
        Main.delay(200);
        if (Math.round(csateter.getEgysegEletereje("Varázsló", ellenfel)) != 0) {
            System.out.println(Main.BLUE+formatNarrator(Main.BLUE+"Varázsló: " + Math.round(csateter.getEgysegEletereje("Varázsló", ellenfel) / 10) + "db sérülésmentes, 1db " + csateter.getEgysegEletereje("Varázsló", ellenfel) % 10)+Main.BLUE + " életerős"+Main.RESET);
        }
        Main.delay(200);
        if (Math.round(csateter.getEgysegEletereje("Lovas", ellenfel)) != 0) {
            System.out.println(Main.BLUE+formatNarrator(Main.BLUE+"Lovas: " + Math.round(csateter.getEgysegEletereje("Lovas", ellenfel) / 1) + "db sérülésmentes, 1db " + csateter.getEgysegEletereje("Lovas", ellenfel) % 1)+Main.BLUE + " életerős"+Main.RESET);
        }

        ellenfel = true;
        Main.delay(200);
        System.out.println();
        System.out.println(Main.RED+"     Ellenfél egységei (ha már vannak): "+Main.RESET);
        Main.delay(200);
        if (Math.round(csateter.getEgysegEletereje("Földműves", ellenfel)) != 0) {
            System.out.println(Main.RED+formatNarrator(Main.RED+"Földműves: " + Math.round(csateter.getEgysegEletereje("Földműves", ellenfel) / 3) + "db sérülésmentes, 1db " + csateter.getEgysegEletereje("Földműves", ellenfel) % 3)+Main.RED + " életerős"+Main.RESET);
        }
        Main.delay(200);
        if (Math.round(csateter.getEgysegEletereje("Íjász", ellenfel)) != 0) {
            System.out.println(Main.RED+formatNarrator(Main.RED+"Íjász: " + Math.round(csateter.getEgysegEletereje("Íjász", ellenfel) / 7) + "db sérülésmentes, 1db " + csateter.getEgysegEletereje("Íjász", ellenfel) % 7)+Main.RED + " életerős"+Main.RESET);
        }
        Main.delay(200);
        if (Math.round(csateter.getEgysegEletereje("Griff", ellenfel)) != 0) {
            System.out.println(Main.RED+formatNarrator(Main.RED+"Griff: " + Math.round(csateter.getEgysegEletereje("Griff", ellenfel) / 30) + "db sérülésmentes, 1db " + csateter.getEgysegEletereje("Griff", ellenfel) % 30)+Main.RED + " életerős"+Main.RESET);
        }
        Main.delay(200);
        if (Math.round(csateter.getEgysegEletereje("Varázsló", ellenfel)) != 0) {
            System.out.println(Main.RED+formatNarrator("Varázsló: " + Math.round(csateter.getEgysegEletereje("Varázsló", ellenfel) / 10) + "db sérülésmentes, 1db " + csateter.getEgysegEletereje("Varázsló", ellenfel) % 10)+Main.RED + " életerős"+Main.RESET);
        }
        Main.delay(200);
        if (Math.round(csateter.getEgysegEletereje("Lovas", ellenfel)) != 0) {
            System.out.println(Main.RED+formatNarrator(Main.RED+"Lovas: " + Math.round(csateter.getEgysegEletereje("Lovas", ellenfel) / 1) + "db sérülésmentes, 1db " + csateter.getEgysegEletereje("Lovas", ellenfel) % 1)+Main.RED + " életerős"+Main.RESET);
        }


        Main.delay(200);
        Main.delay(200);
        System.out.format("+-----+------------+------------+------------+------------+------------+------------+------------+------------+------------+------------+------------+------------+%n");
        System.out.format("|     |        A   |        B   |        C   |        D   |        E   |        F   |        G   |        H   |        I   |        J   |        K   |        L   |%n");
        System.out.format("+-----+------------+------------+------------+------------+------------+------------+------------+------------+------------+------------+------------+------------+%n");
        for (int i = 0; i < 10; i++) {
            String szoveg[] = new String[12];
            for (int j = 0; j < 12; j++) {
                if (csateter.matrix[i][j] != null) {
                    szoveg[j] = csateter.matrix[i][j].egyseg.getNev();

                    for (int k = 0; k < (12-szoveg[j].length()); k++) {
                        szoveg[j] = szoveg[j]+" ";
                    }

                    if (csateter.matrix[i][j].ellenfel){
                        szoveg[j] = Main.RED+szoveg[j]+Main.RESET;
                    } else {
                        szoveg[j] = Main.BLUE+szoveg[j]+Main.RESET;
                    }
                } else {
                    szoveg[j] = "";
                }
            }
            //System.out.format(leftAlignFormat, (i+1), "","Földműves","","","","","","","Varázsló", "", "", "");
            System.out.format(leftAlignFormat, (i + 1), szoveg[0], szoveg[1], szoveg[2], szoveg[3], szoveg[4], szoveg[5], szoveg[6], szoveg[7], szoveg[8], szoveg[9], szoveg[10], szoveg[11]);
            System.out.format("+-----+------------+------------+------------+------------+------------+------------+------------+------------+------------+------------+------------+------------+%n");
        }
        //System.out.format(leftAlignFormat, (i+1), "","Földműves","","","","","","","Varázsló", "", "", "");

    }
}
