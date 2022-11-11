import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import Egyseg.*;
import Egyseg.Kepesseg.GyogyitasKepesseg;
import Egyseg.Kepesseg.LovesKepesseg;
import Egyseg.Kepesseg.VegtelenVisszatamadasKepesseg;
import Varazslat.*;
import Public.*;

public class Main {

    // Konzol Szinek és formázás
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String BOLD = "\033[1m";  // BOLD
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN


    /*******************************************    MAIN FÜGGVÉNY   ****************************************************/
    public static void main(String[] args) {

/**********      Teszteleshez pelda input     *************************************************************************
 try {
 System.setIn(new FileInputStream(new File("be.txt"))); // a be.txt-ből fogja beolvasni az inputot
 } catch (Exception ex) {
 return;
 }
 */


        Scanner in = new Scanner(System.in);
        Elokeszites sajatElokeszites = new Elokeszites();
        String bejovo;
        int bejovoSzam;
        Hos hos = new Hos("");

        float egysegekSzama[] = new float[5];

        LovesKepesseg lovesKepesseg = new LovesKepesseg("Lövés");
        VegtelenVisszatamadasKepesseg vegtelenVisszatamadasKepesseg = new VegtelenVisszatamadasKepesseg("Végtelen visszatámadás");
        GyogyitasKepesseg gyogyitasKepesseg = new GyogyitasKepesseg("Gyógyítás");

        Egyseg foldmuvesEgyseg = new Egyseg("Földműves", 2, 1, 1, 3, 4, 8, null);
        Egyseg ijaszEgyseg = new Egyseg("Íjász", 6, 2, 4, 7, 4, 9, lovesKepesseg);
        Egyseg griffEgyseg = new Egyseg("Griff", 15, 5, 10, 30, 7, 15, vegtelenVisszatamadasKepesseg);
        Egyseg varazsloEgyseg = new Egyseg("Varázsló", 10, 1, 2, 10, 6, 10, gyogyitasKepesseg);
        Egyseg lovasEgyseg = new Egyseg("Lovas", 4, 1, 2, 1, 10, 7, null);

        System.out.println(GREEN_BRIGHT + "<><><><><><><><><><><><><><><><><><>  Kötelező Program  <><><><><><><><><><><><><><><><>" + RESET);


        System.out.println(Portal.formatNarrator("Üdvözöllek a mátrixban idegen! Én vagyok a játékmester. :)"));

        // Nehézség kiválasztása
        do {
            System.out.println(Portal.formatNarrator("Milyen nehézségi szinten szeretnél játszani?  " + GREEN + "-könnyű-  -közepes-  -nehéz-"));
            System.out.print("Add meg a nehézségi szintet: ");
            bejovo = in.nextLine();

            if (bejovo.equals("könnyű")) {
                sajatElokeszites.setArany(1300);

            } else if (bejovo.equals("közepes")) {
                sajatElokeszites.setArany(1000);

            } else if (bejovo.equals("nehéz")) {
                sajatElokeszites.setArany(700);

            } else {
                System.out.println(Portal.formatNarrator("Ezt nem értem. A kötőjelek között lévő szövegek közül válassz egyet."));
            }

        } while (!bejovo.matches("könnyű|közepes|nehéz"));
        System.out.println(Portal.formatNarrator("Nehézség beállítva " + bejovo + "re."));
        System.out.println(Portal.formatNarrator(sajatElokeszites.getArany() + " aranyad van."));

        // frakció választás
        do {
            System.out.println();
            System.out.println(Portal.formatNarrator("Milyen frakcióhoz szeretnél tartozni?  " + GREEN + "-ember-  -ork-  -macska-"));
            System.out.print("Add meg a frakciód nevét: ");
            bejovo = in.nextLine();

            // TODO példányosítás frakciók szerint
            if (bejovo.equals("ember")) {
                // hos, egysegek, varazslatok, kepessegek
                hos.setFrakcio(bejovo);


            } else if (bejovo.equals("ork")) {
                hos.setFrakcio(bejovo);


            } else if (bejovo.equals("macska")) {
                hos.setFrakcio(bejovo);


            } else {
                System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
            }

        } while (!bejovo.matches("ember|ork|macska"));
        System.out.println(Portal.formatNarrator("Kiválasztott frakció: " + bejovo));


        // TODO Hős létrehozása tesztelése
        // TODO tulajdonsag ár átalakítása
        // TODO max 10 tulajdonság pont
        // Hős létrehozása
        System.out.println();
        System.out.println(Portal.formatNarrator("Itt az ideje beállítani a hősöd tulajdonságait."));
        System.out.println(Portal.formatNarrator("Ne költsd itt el minden aranyad, mert kelleni fog még másra is."));

        Portal.hosKiirat(hos, sajatElokeszites);

        // Támadás előkészítése

        int osszTulajdonsag = 0;
        int tulAr = 0;
        do {
            bejovo = "";
            System.out.println();
            System.out.println(Portal.formatNarrator("A Hősöd jelenlegi támadása: " + hos.getTamadas()));
            System.out.print("Add meg hősöd támadásának új értékét: ");

            try {
                bejovoSzam = in.nextInt();
                in.nextLine();
            } catch (Exception e) {
                System.out.println(Portal.formatNarrator("Ez nem egy szám. Kérleg egy egész számot írj be!"));
                in.nextLine();
                continue;
            }

            // Lekezelni, ha rossz szamot adnak meg, ha elfogy a pénze
            if (bejovoSzam < 1) {
                System.out.println(Portal.formatNarrator("Kérlek pozitív egész számot adj meg!"));
                continue;
            }
            // ha elfogy a pénze
            if (bejovoSzam > 10) {
                System.out.println(Portal.formatNarrator("10 nél nem lehet nagyobb ez az érték."));
                continue;
            }
            if (hos.getAr(bejovoSzam) > sajatElokeszites.getArany()) {
                System.out.println(Portal.formatNarrator("Ehhez nincs elég aranyad."));
                continue;
            }
            osszTulajdonsag += bejovoSzam;
            if (bejovoSzam == 1) {
                System.out.println(0 + " aranyba fog kerülni. Mehet?" + GREEN + "  -igen-  -nem-" + RESET);
                tulAr = 0;
            } else {
                tulAr = hos.getAr(osszTulajdonsag);
                System.out.println(Portal.formatNarrator(tulAr + " aranyba fog kerülni. Mehet?" + GREEN + "  -igen-  -nem-" + RESET));
            }
            System.out.print("Mentés? ");


            // Véglegesíteni az értéket
            bejovo = in.nextLine();
            if (bejovo.equals("igen")) {
                hos.setTamadas(bejovoSzam);
                sajatElokeszites.setArany(sajatElokeszites.getArany() - tulAr);
                System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));

            } else if (bejovo.equals("nem")) {
                osszTulajdonsag -= bejovoSzam;
                System.out.println(Portal.formatNarrator("Azt az értéket kell megadnod, hogy összesen hány támadás pontja legyen a hősnek."));
            } else {
                System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
                osszTulajdonsag -= bejovoSzam;
            }

        } while (!bejovo.matches("igen"));


        // Védekezés előkészítése
        do {
            bejovo = "";
            System.out.println();
            System.out.println(Portal.formatNarrator("A Hősöd jelenlegi védekezése: " + hos.getVedekezes()));
            System.out.print("Add meg hősöd védekezésének új értékét: ");

            try {
                bejovoSzam = in.nextInt();
                in.nextLine();
            } catch (Exception e) {
                System.out.println(Portal.formatNarrator("Ez nem egy szám. Kérleg egy egész számot írj be!"));
                in.nextLine();
                continue;
            }

            // Lekezelni, ha rossz szamot adnak meg, ha elfogy a pénze
            if (bejovoSzam < 1) {
                System.out.println(Portal.formatNarrator("Kérlek pozitív egész számot adj meg!"));
                continue;
            }
            // ha elfogy a pénze
            if (bejovoSzam > 10) {
                System.out.println(Portal.formatNarrator("10 nél nem lehet nagyobb ez az érték."));
                continue;
            }
            if (hos.getAr(bejovoSzam) > sajatElokeszites.getArany()) {
                System.out.println(Portal.formatNarrator("Ehhez nincs elég aranyad."));
                continue;
            }
            osszTulajdonsag += bejovoSzam;
            if (bejovoSzam == 1) {
                System.out.println(0 + " aranyba fog kerülni. Mehet?" + GREEN + "  -igen-  -nem-" + RESET);
            } else {
                tulAr = hos.getAr(osszTulajdonsag);
                System.out.println(Portal.formatNarrator(tulAr + " aranyba fog kerülni. Mehet?" + GREEN + "  -igen-  -nem-" + RESET));
            }
            System.out.print("Mentés? ");

            // Véglegesíteni az értéket
            bejovo = in.nextLine();
            if (bejovo.equals("igen")) {
                hos.setVedekezes(bejovoSzam);
                sajatElokeszites.setArany(sajatElokeszites.getArany() - tulAr);
                System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));

            } else if (bejovo.equals("nem")) {
                osszTulajdonsag -= bejovoSzam;
                System.out.println(Portal.formatNarrator("Azt az értéket kell megadnod, hogy összesen hány védekezés pontja legyen a hősnek."));
            } else {
                System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
                osszTulajdonsag -= bejovoSzam;
            }

        } while (!bejovo.matches("igen"));

        // Varázslat előkészítése
        do {
            bejovo = "";
            System.out.println();
            System.out.println(Portal.formatNarrator("A Hősöd jelenlegi varázsereje: " + hos.getVarazsero()));
            System.out.print("Add meg hősöd varázserejének új értékét: ");

            try {
                bejovoSzam = in.nextInt();
                in.nextLine();
            } catch (Exception e) {
                System.out.println(Portal.formatNarrator("Ez nem egy szám. Kérleg egy egész számot írj be!"));
                in.nextLine();
                continue;
            }

            // Lekezelni, ha rossz szamot adnak meg, ha elfogy a pénze
            if (bejovoSzam < 0) {
                System.out.println(Portal.formatNarrator("Kérlek pozitív egész számot adj meg!"));
                continue;
            }
            // ha elfogy a pénze
            if (bejovoSzam > 10) {
                System.out.println(Portal.formatNarrator("10 nél nem lehet nagyobb ez az érték."));
                continue;
            }
            if (hos.getAr(bejovoSzam) > sajatElokeszites.getArany()) {
                System.out.println(Portal.formatNarrator("Ehhez nincs elég aranyad."));
                continue;
            }

            osszTulajdonsag += bejovoSzam;
            if (bejovoSzam == 1) {
                System.out.println(0 + " aranyba fog kerülni. Mehet?" + GREEN + "  -igen-  -nem-" + RESET);
                tulAr = 0;
            } else {
                tulAr = hos.getAr(osszTulajdonsag);
                System.out.println(Portal.formatNarrator(tulAr + " aranyba fog kerülni. Mehet?" + GREEN + "  -igen-  -nem-" + RESET));
            }
            System.out.print("Mentés? ");


            // Véglegesíteni az értéket
            bejovo = in.nextLine();
            if (bejovo.equals("igen")) {
                hos.setVarazsero(bejovoSzam);
                sajatElokeszites.setArany(sajatElokeszites.getArany() - tulAr);
                System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));

            } else if (bejovo.equals("nem")) {
                osszTulajdonsag -= bejovoSzam;
                System.out.println(Portal.formatNarrator("Azt az értéket kell megadnod, hogy összesen hány varázserő pontja legyen a hősnek."));
            } else {
                System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
                osszTulajdonsag -= bejovoSzam;
            }
        } while (!bejovo.matches("igen"));

        // Tudás előkészítése
        do {
            bejovo = "";
            System.out.println();
            System.out.println(Portal.formatNarrator("A Hősöd jelenlegi tudása: " + hos.getTudas()));
            System.out.print("Add meg hősöd tudásának új értékét: ");

            try {
                bejovoSzam = in.nextInt();
                in.nextLine();
            } catch (Exception e) {
                System.out.println(Portal.formatNarrator("Ez nem egy szám. Kérleg egy egész számot írj be!"));
                in.nextLine();
                continue;
            }

            // Lekezelni, ha rossz szamot adnak meg, ha elfogy a pénze
            if (bejovoSzam < 0) {
                System.out.println(Portal.formatNarrator("Kérlek pozitív egész számot adj meg!"));
                continue;
            }
            // ha elfogy a pénze
            if (bejovoSzam > 10) {
                System.out.println(Portal.formatNarrator("10 nél nem lehet nagyobb ez az érték."));
                continue;
            }
            if (hos.getAr(bejovoSzam) > sajatElokeszites.getArany()) {
                System.out.println(Portal.formatNarrator("Ehhez nincs elég aranyad."));
                continue;
            }

            osszTulajdonsag += bejovoSzam;
            if (bejovoSzam == 1) {
                System.out.println(0 + " aranyba fog kerülni. Mehet?" + GREEN + "  -igen-  -nem-" + RESET);
                tulAr = 0;
            } else {
                tulAr = hos.getAr(osszTulajdonsag);
                System.out.println(Portal.formatNarrator(tulAr + " aranyba fog kerülni. Mehet?" + GREEN + "  -igen-  -nem-" + RESET));
            }
            System.out.print("Mentés? ");


            // Véglegesíteni az értéket
            bejovo = in.nextLine();
            if (bejovo.equals("igen")) {
                hos.setTudas(bejovoSzam);
                sajatElokeszites.setArany(sajatElokeszites.getArany() - tulAr);
                System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));

            } else if (bejovo.equals("nem")) {
                System.out.println(Portal.formatNarrator("Azt az értéket kell megadnod, hogy összesen hány tudás pontja legyen a hősnek."));
                osszTulajdonsag -= bejovoSzam;
            } else {
                System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
                osszTulajdonsag -= bejovoSzam;
            }
        } while (!bejovo.matches("igen"));

        // Morál előkészítése
        do {
            bejovo = "";
            System.out.println();
            System.out.println(Portal.formatNarrator("A Hősöd jelenlegi morála: " + hos.getMoral()));
            System.out.print("Add meg hősöd morálának új értékét: ");

            try {
                bejovoSzam = in.nextInt();
                in.nextLine();
            } catch (Exception e) {
                System.out.println(Portal.formatNarrator("Ez nem egy szám. Kérleg egy egész számot írj be!"));
                in.nextLine();
                continue;
            }

            // Lekezelni, ha rossz szamot adnak meg, ha elfogy a pénze
            if (bejovoSzam < 0) {
                System.out.println(Portal.formatNarrator("Kérlek pozitív egész számot adj meg!"));
                continue;
            }
            // ha elfogy a pénze
            if (bejovoSzam > 10) {
                System.out.println(Portal.formatNarrator("10 nél nem lehet nagyobb ez az érték."));
                continue;
            }
            if (hos.getAr(bejovoSzam) > sajatElokeszites.getArany()) {
                System.out.println(Portal.formatNarrator("Ehhez nincs elég aranyad."));
                continue;
            }

            osszTulajdonsag += bejovoSzam;
            if (bejovoSzam == 1) {
                System.out.println(0 + " aranyba fog kerülni. Mehet?" + GREEN + "  -igen-  -nem-" + RESET);
                tulAr = 0;
            } else {
                tulAr = hos.getAr(osszTulajdonsag);
                System.out.println(Portal.formatNarrator(tulAr + " aranyba fog kerülni. Mehet?" + GREEN + "  -igen-  -nem-" + RESET));
            }
            System.out.print("Mentés? ");


            // Véglegesíteni az értéket
            bejovo = in.nextLine();
            if (bejovo.equals("igen")) {
                hos.setMoral(bejovoSzam);
                sajatElokeszites.setArany(sajatElokeszites.getArany() - tulAr);
                System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));

            } else if (bejovo.equals("nem")) {
                osszTulajdonsag -= bejovoSzam;
                System.out.println(Portal.formatNarrator("Azt az értéket kell megadnod, hogy összesen hány morál pontja legyen a hősnek."));
            } else {
                System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
                osszTulajdonsag -= bejovoSzam;
            }

        } while (!bejovo.matches("igen"));

        // Szerencse előkészítése
        do {
            bejovo = "";
            System.out.println();
            System.out.println(Portal.formatNarrator("A Hősöd jelenlegi szerencséje: " + hos.getSzerencse()));
            System.out.print("Add meg hősöd szerencséjének új értékét: ");

            try {
                bejovoSzam = in.nextInt();
                in.nextLine();
            } catch (Exception e) {
                System.out.println(Portal.formatNarrator("Ez nem egy szám. Kérleg egy egész számot írj be!"));
                in.nextLine();
                continue;
            }

            // Lekezelni, ha rossz szamot adnak meg, ha elfogy a pénze
            if (bejovoSzam < 0) {
                System.out.println(Portal.formatNarrator("Kérlek pozitív egész számot adj meg!"));
                continue;
            }
            // ha elfogy a pénze
            if (bejovoSzam > 10) {
                System.out.println(Portal.formatNarrator("10 nél nem lehet nagyobb ez az érték."));
                continue;
            }
            if (hos.getAr(bejovoSzam) > sajatElokeszites.getArany()) {
                System.out.println(Portal.formatNarrator("Ehhez nincs elég aranyad."));
                continue;
            }

            osszTulajdonsag += bejovoSzam;
            if (bejovoSzam == 1) {
                System.out.println(0 + " aranyba fog kerülni. Mehet?" + GREEN + "  -igen-  -nem-" + RESET);
                tulAr = 0;
            } else {
                tulAr = hos.getAr(osszTulajdonsag);
                System.out.println(Portal.formatNarrator(tulAr + " aranyba fog kerülni. Mehet?" + GREEN + "  -igen-  -nem-" + RESET));
            }
            System.out.print("Mentés? ");


            // Véglegesíteni az értéket
            bejovo = in.nextLine();
            if (bejovo.equals("igen")) {
                hos.setSzerencse(bejovoSzam);
                sajatElokeszites.setArany(sajatElokeszites.getArany() - tulAr);
                System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));

            } else if (bejovo.equals("nem")) {
                osszTulajdonsag -= bejovoSzam;
                System.out.println(Portal.formatNarrator("Azt az értéket kell megadnod, hogy összesen hány szerencse pontja legyen a hősnek."));
            } else {
                osszTulajdonsag -= bejovoSzam;
                System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
            }

        } while (!bejovo.matches("igen"));

        Portal.hosKiirat(hos, sajatElokeszites);


        // Varázslat Setup
        System.out.println();
        System.out.println(Portal.formatNarrator("Itt az ideje keválasztani a hősöd varázslatait."));
        System.out.println(Portal.formatNarrator("Ne költsd itt el minden aranyad, mert kelleni fog még másra is."));


        // Villámcsapás előkészítése
        do {
            int ar = 60;
            bejovo = "";
            System.out.println();
            System.out.println(Portal.formatNarrator("A villámcsapás varázslat egy kiválasztott ellenséges egységre (varázsero * 30) sebzés okoz. 5 mannába kerül."));

            if (sajatElokeszites.getArany() - ar <= 0) {
                delay(200);
                System.out.println(Portal.formatNarrator("Erre a varázslatra sajnos nincs elég aranyad, ezért nem tudod megvenni."));
                bejovo = "nem";
                continue;
            }

            System.out.println(Portal.formatNarrator(ar + " aranyba fog kerülni. Megveszed?" + GREEN + "  -igen-  -nem-" + RESET));
            System.out.print("Megveszed? ");


            // Véglegesíteni az értéket
            bejovo = in.nextLine();
            if (bejovo.equals("igen")) {
                VillamcsapasVarazslat villamcsapasVarazslat = new VillamcsapasVarazslat();
                hos.addVarazslatok("Villámcsapás", villamcsapasVarazslat);

                sajatElokeszites.setArany(sajatElokeszites.getArany() - ar);
                System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));

            } else if (bejovo.equals("nem")) {
            } else {
                System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
            }

        } while (!bejovo.matches("igen|nem"));

        // Tuzlabda előkészítése
        do {
            int ar = 120;
            bejovo = "";
            System.out.println();
            System.out.println(Portal.formatNarrator("A tűzlabda varázslat a kiválasztott mezo körüli 3x3-as területen lévő összes \n (saját, illetve ellenséges) egységre (varázsero * 20) sebzés okoz. 9 mannába kerül."));

            if (sajatElokeszites.getArany() - ar <= 0) {
                delay(200);
                System.out.println(Portal.formatNarrator("Erre a varázslatra sajnos nincs elég aranyad, ezért nem tudod megvenni."));
                bejovo = "nem";
                continue;
            }

            System.out.println(Portal.formatNarrator(ar + " aranyba fog kerülni. Megveszed?" + GREEN + "  -igen-  -nem-" + RESET));
            System.out.print("Megveszed? ");

            // Véglegesíteni az értéket
            bejovo = in.nextLine();
            if (bejovo.equals("igen")) {
                TuzlabdaVarazslat tuzlabdaVarazslat = new TuzlabdaVarazslat();
                hos.addVarazslatok("Tűzlabda", tuzlabdaVarazslat);

                sajatElokeszites.setArany(sajatElokeszites.getArany() - ar);
                System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));

            } else if (bejovo.equals("nem")) {
            } else {
                System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
            }

        } while (!bejovo.matches("igen|nem"));

        // Feltámasztás előkészítése
        do {
            int ar = 120;
            bejovo = "";
            System.out.println();
            System.out.println(Portal.formatNarrator("A feltámasztás varázslat egy kiválasztott saját egység feltámasztása.\n Maximális gyógyítás mértéke: (varázserő * 50) (de az eredeti egységszámnál több nem lehet. 6 mannába kerül."));

            if (sajatElokeszites.getArany() - ar <= 0) {
                delay(200);
                System.out.println(Portal.formatNarrator("Erre a varázslatra sajnos nincs elég aranyad, ezért nem tudod megvenni."));
                bejovo = "nem";
                continue;
            }

            System.out.println(Portal.formatNarrator(ar + " aranyba fog kerülni. Megveszed?" + GREEN + "  -igen-  -nem-" + RESET));
            System.out.print("Megveszed? ");

            // Véglegesíteni az értéket
            bejovo = in.nextLine();
            if (bejovo.equals("igen")) {
                FeltamasztasVarazslat feltamasztasVarazslat = new FeltamasztasVarazslat();
                hos.addVarazslatok("Feltámasztás", feltamasztasVarazslat);

                sajatElokeszites.setArany(sajatElokeszites.getArany() - ar);
                System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));

            } else if (bejovo.equals("nem")) {
            } else {
                System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
            }

        } while (!bejovo.matches("igen|nem"));

        // Kedvetlenítés előkészítése
        do {
            int ar = 110;
            bejovo = "";
            System.out.println();
            System.out.println(Portal.formatNarrator("A kedvetlenítés varázslat a kiválasztott mezőn álló egységek kezdeményezését csökkenti 1-el. 7 mannába kerül."));

            if (sajatElokeszites.getArany() - ar <= 0) {
                delay(200);
                System.out.println(Portal.formatNarrator("Erre a varázslatra sajnos nincs elég aranyad, ezért nem tudod megvenni."));
                bejovo = "nem";
                continue;
            }

            System.out.println(Portal.formatNarrator(ar + " aranyba fog kerülni. Megveszed?" + GREEN + "  -igen-  -nem-" + RESET));
            System.out.print("Megveszed? ");

            // Véglegesíteni az értéket
            bejovo = in.nextLine();
            if (bejovo.equals("igen")) {
                KedvetlenitesVarazslat kedvetlenitesVarazslat = new KedvetlenitesVarazslat();
                hos.addVarazslatok("Kedvetlenítés", kedvetlenitesVarazslat);

                sajatElokeszites.setArany(sajatElokeszites.getArany() - ar);
                System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));

            } else if (bejovo.equals("nem")) {
            } else {
                System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
            }
        } while (!bejovo.matches("igen|nem"));

        // Random varazslat előkészítése
        do {
            int ar = 100;
            bejovo = "";
            System.out.println();
            System.out.println(Portal.formatNarrator("A random varázslat egy véletlenszerű varázslatot véletlenszerű helyre ledob. 6 mannába kerül."));

            if (sajatElokeszites.getArany() - ar <= 0) {
                delay(200);
                System.out.println(Portal.formatNarrator("Erre a varázslatra sajnos nincs elég aranyad, ezért nem tudod megvenni."));
                bejovo = "nem";
                continue;
            }

            System.out.println(Portal.formatNarrator(ar + " aranyba fog kerülni. Megveszed?" + GREEN + "  -igen-  -nem-" + RESET));
            System.out.print("Megveszed? ");

            // Véglegesíteni az értéket
            bejovo = in.nextLine();
            if (bejovo.equals("igen")) {
                RandomVarazslat randomVarazslat = new RandomVarazslat();
                hos.addVarazslatok("Random", randomVarazslat);

                sajatElokeszites.setArany(sajatElokeszites.getArany() - ar);
                System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));

            } else if (bejovo.equals("nem")) {
            } else {
                System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
            }
        } while (!bejovo.matches("igen|nem"));

        // egység setup
        boolean vettegyseget = false;
        do {

            System.out.println();
            System.out.println(Portal.formatNarrator("Itt az ideje összeállítanod hadseregedet."));

            // Földműves előkészítése
            do {
                bejovo = "";
                System.out.println();
                Portal.egysegKiirat(foldmuvesEgyseg);
                System.out.print("Add meg hány földművest szeretnél csatába vinni: ");
                try {
                    bejovoSzam = in.nextInt();
                    in.nextLine();
                } catch (Exception e) {
                    System.out.println(Portal.formatNarrator("Ez nem egy szám. Kérleg egy egész számot írj be!"));
                    in.nextLine();
                    continue;
                }

                // Lekezelni, ha rossz szamot adnak meg, ha elfogy a pénze
                if (bejovoSzam < 0) {
                    System.out.println(Portal.formatNarrator("Kérlek pozitív egész számot adj meg!"));
                }
                if (bejovoSzam * foldmuvesEgyseg.getAr() > sajatElokeszites.getArany()) {
                    System.out.println(Portal.formatNarrator("Ehhez nincs elég aranyad."));
                    continue;
                }

                System.out.println(Portal.formatNarrator(bejovoSzam * foldmuvesEgyseg.getAr() + " aranyba fog kerülni. Mehet?" + GREEN + "  -igen-  -nem-" + RESET));
                System.out.print("Mentés? ");

                // Véglegesíteni az értéket
                bejovo = in.nextLine();
                if (bejovo.equals("igen")) {
                    egysegekSzama[0] = bejovoSzam;
                    sajatElokeszites.setArany(sajatElokeszites.getArany() - bejovoSzam * foldmuvesEgyseg.getAr());
                    System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));
                } else if (bejovo.equals("nem")) {
                    System.out.println(Portal.formatNarrator("Azt az értéket kell megadnod, hogy összesen hány ilyen egységet szeretnél venni."));
                } else {
                    System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
                }
            } while (!bejovo.matches("igen"));
            System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));


            // Íjász előkészítése
            do {
                bejovo = "";
                System.out.println();
                Portal.egysegKiirat(ijaszEgyseg);
                System.out.print("Add meg hány íjászt szeretnél csatába vinni: ");
                try {
                    bejovoSzam = in.nextInt();
                    in.nextLine();
                } catch (Exception e) {
                    System.out.println(Portal.formatNarrator("Ez nem egy szám. Kérleg egy egész számot írj be!"));
                    in.nextLine();
                    continue;
                }

                // Lekezelni, ha rossz szamot adnak meg, ha elfogy a pénze
                if (bejovoSzam < 0) {
                    System.out.println(Portal.formatNarrator("Kérlek pozitív egész számot adj meg!"));
                }
                if (bejovoSzam * ijaszEgyseg.getAr() > sajatElokeszites.getArany()) {
                    System.out.println(Portal.formatNarrator("Ehhez nincs elég aranyad."));
                    continue;
                }

                System.out.println(Portal.formatNarrator(bejovoSzam * foldmuvesEgyseg.getAr() + " aranyba fog kerülni. Mehet?" + GREEN + "  -igen-  -nem-" + RESET));
                System.out.print("Mentés? ");

                // Véglegesíti az értéket
                bejovo = in.nextLine();
                if (bejovo.equals("igen")) {
                    egysegekSzama[1] = bejovoSzam;
                    sajatElokeszites.setArany(sajatElokeszites.getArany() - bejovoSzam * ijaszEgyseg.getAr());
                    System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));
                } else if (bejovo.equals("nem")) {
                    System.out.println(Portal.formatNarrator("Azt az értéket kell megadnod, hogy összesen hány ilyen egységet szeretnél venni."));
                } else {
                    System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
                }
            } while (!bejovo.matches("igen"));
            System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));


            // Griff előkészítése
            do {
                bejovo = "";
                System.out.println();
                Portal.egysegKiirat(griffEgyseg);
                System.out.print("Add meg hány griffet szeretnél csatába vinni: ");
                try {
                    bejovoSzam = in.nextInt();
                    in.nextLine();
                } catch (Exception e) {
                    System.out.println(Portal.formatNarrator("Ez nem egy szám. Kérleg egy egész számot írj be!"));
                    in.nextLine();
                    continue;
                }

                // Lekezelni, ha rossz szamot adnak meg, ha elfogy a pénze
                if (bejovoSzam < 0) {
                    System.out.println(Portal.formatNarrator("Kérlek pozitív egész számot adj meg!"));
                }
                if (bejovoSzam * griffEgyseg.getAr() > sajatElokeszites.getArany()) {
                    System.out.println(Portal.formatNarrator("Ehhez nincs elég aranyad."));
                    continue;
                }

                System.out.println(Portal.formatNarrator(bejovoSzam * griffEgyseg.getAr() + " aranyba fog kerülni. Mehet?" + GREEN + "  -igen-  -nem-" + RESET));
                System.out.print("Mentés? ");

                // Véglegesíti az értéket
                bejovo = in.nextLine();
                if (bejovo.equals("igen")) {
                    egysegekSzama[2] = bejovoSzam;
                    sajatElokeszites.setArany(sajatElokeszites.getArany() - bejovoSzam * griffEgyseg.getAr());
                    System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));
                } else if (bejovo.equals("nem")) {
                    System.out.println(Portal.formatNarrator("Azt az értéket kell megadnod, hogy összesen hány ilyen egységet szeretnél venni."));
                } else {
                    System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
                }
            } while (!bejovo.matches("igen"));
            System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));


            // Varázsló előkészítése
            do {
                bejovo = "";
                System.out.println();
                Portal.egysegKiirat(varazsloEgyseg);
                System.out.print("Add meg hány varázslót szeretnél csatába vinni: ");
                try {
                    bejovoSzam = in.nextInt();
                    in.nextLine();
                } catch (Exception e) {
                    System.out.println(Portal.formatNarrator("Ez nem egy szám. Kérleg egy egész számot írj be!"));
                    in.nextLine();
                    continue;
                }

                // Lekezelni, ha rossz szamot adnak meg, ha elfogy a pénze
                if (bejovoSzam < 0) {
                    System.out.println(Portal.formatNarrator("Kérlek pozitív egész számot adj meg!"));
                }
                if (bejovoSzam * varazsloEgyseg.getAr() > sajatElokeszites.getArany()) {
                    System.out.println(Portal.formatNarrator("Ehhez nincs elég aranyad."));
                    continue;
                }

                System.out.println(Portal.formatNarrator(bejovoSzam * varazsloEgyseg.getAr() + " aranyba fog kerülni. Mehet?" + GREEN + "  -igen-  -nem-" + RESET));
                System.out.print("Mentés? ");

                // Véglegesíti az értéket
                bejovo = in.nextLine();
                if (bejovo.equals("igen")) {
                    egysegekSzama[3] = bejovoSzam;
                    sajatElokeszites.setArany(sajatElokeszites.getArany() - bejovoSzam * varazsloEgyseg.getAr());
                    System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));
                } else if (bejovo.equals("nem")) {
                    System.out.println(Portal.formatNarrator("Azt az értéket kell megadnod, hogy összesen hány ilyen egységet szeretnél venni."));
                } else {
                    System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
                }
            } while (!bejovo.matches("igen"));
            System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));


            // Lovas előkészítése
            do {
                bejovo = "";
                System.out.println();
                Portal.egysegKiirat(lovasEgyseg);
                System.out.print("Add meg hány lovast szeretnél csatába vinni: ");
                try {
                    bejovoSzam = in.nextInt();
                    in.nextLine();
                } catch (Exception e) {
                    System.out.println(Portal.formatNarrator("Ez nem egy szám. Kérleg egy egész számot írj be!"));
                    in.nextLine();
                    continue;
                }

                // Lekezelni, ha rossz szamot adnak meg, ha elfogy a pénze
                if (bejovoSzam < 0) {
                    System.out.println(Portal.formatNarrator("Kérlek pozitív egész számot adj meg!"));
                }
                if (bejovoSzam * lovasEgyseg.getAr() > sajatElokeszites.getArany()) {
                    System.out.println(Portal.formatNarrator("Ehhez nincs elég aranyad."));
                    continue;
                }

                System.out.println(Portal.formatNarrator(bejovoSzam * lovasEgyseg.getAr() + " aranyba fog kerülni. Mehet?" + GREEN + "  -igen-  -nem-" + RESET));
                System.out.print("Mentés? ");

                // Véglegesíti az értéket
                bejovo = in.nextLine();
                if (bejovo.equals("igen")) {
                    egysegekSzama[4] = bejovoSzam;
                    sajatElokeszites.setArany(sajatElokeszites.getArany() - bejovoSzam * lovasEgyseg.getAr());
                    System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));
                } else if (bejovo.equals("nem")) {
                    System.out.println(Portal.formatNarrator("Azt az értéket kell megadnod, hogy összesen hány ilyen egységet szeretnél venni."));
                } else {
                    System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
                }
            } while (!bejovo.matches("igen"));
            System.out.println(Portal.formatNarrator("Még " + sajatElokeszites.getArany() + " aranyad van."));

            for (int i = 0; i < egysegekSzama.length; i++) {
                if (egysegekSzama[i] > 0) {
                    vettegyseget = true;
                    break;
                }
            }
            if (!vettegyseget) {
                System.out.println(Portal.formatNarrator("Legalább egy egységet muszáj venned."));
            }
        } while (!vettegyseget);

        // TODO Matrix csak elso ket sorba lehessen rakni
        // TODO lekezelni, hogy csak akkor kell elhelyezni, ha 0-nál több van belőle
        Csateter csatater = new Csateter();

        Koordinata koordinata = null;
        boolean sikerult = false;

        System.out.println();
        System.out.println(Portal.formatNarrator("Itt az ideje elhejezned hadsereged a csatatéren."));

        // Földműves elhelyezése
        if (egysegekSzama[0] != 0) {
            do {
                do {
                    sikerult = false;
                    Portal.csataterKiirat(csatater);
                    System.out.println(Portal.formatNarrator("Hová kerüljenek a Földművesek?"));
                    System.out.print("Oszlop Sor (pl.: " + GREEN + "B 10" + RESET + "): ");
                    bejovo = in.nextLine();

                    try {
                        koordinata = new Koordinata(bejovo);
                    } catch (Exception ex) {
                        System.out.println(Portal.formatNarrator("" + ex.getMessage()));
                        sikerult = false;
                        continue;
                    }
                } while (koordinata == null);
                try {
                    if (koordinata.getSzelesseg() > 1) {
                        System.out.println(Portal.formatNarrator("Csak az első két oszlopba rakhatod az egységeidet."));
                        sikerult = false;
                        continue;
                    } else {
                        csatater.setMatrix(koordinata, foldmuvesEgyseg, egysegekSzama[0], false);
                        sikerult = true;
                    }
                } catch (Exception ex) {
                    System.out.println(Portal.formatNarrator("" + ex.getMessage()));
                    sikerult = false;
                }
            } while (!sikerult);
        }


        // Íjász elhelyezése
        if (egysegekSzama[1] != 0) {
            do {
                do {
                    sikerult = false;
                    Portal.csataterKiirat(csatater);
                    System.out.println(Portal.formatNarrator("Hová kerüljenek az Íjászok?"));
                    System.out.print("Oszlop Sor (pl.: " + GREEN + "B 10" + RESET + "): ");
                    bejovo = in.nextLine();

                    try {
                        koordinata = new Koordinata(bejovo);
                        if (koordinata.getSzelesseg() >= 1) {
                            System.out.println(Portal.formatNarrator("Csak az első két oszlopba rakhatod az egységeidet."));
                            sikerult = false;
                            continue;
                        }
                    } catch (Exception ex) {
                        System.out.println(Portal.formatNarrator("" + ex.getMessage()));
                        sikerult = false;
                        continue;
                    }
                } while (koordinata == null);
                try {
                    csatater.setMatrix(koordinata, ijaszEgyseg, egysegekSzama[1], false);
                    sikerult = true;
                } catch (Exception ex) {
                    System.out.println(Portal.formatNarrator("" + ex.getMessage()));
                    sikerult = false;
                }
            } while (!sikerult);
        }


        // Griff elhelyezése
        if (egysegekSzama[2] != 0) {
            do {
                do {
                    sikerult = false;
                    Portal.csataterKiirat(csatater);
                    System.out.println(Portal.formatNarrator("Hová kerüljenek a Griffek?"));
                    System.out.print("Oszlop Sor (pl.: " + GREEN + "B 10" + RESET + "): ");
                    bejovo = in.nextLine();

                    try {
                        koordinata = new Koordinata(bejovo);
                        if (koordinata.getSzelesseg() >= 1) {
                            System.out.println(Portal.formatNarrator("Csak az első két oszlopba rakhatod az egységeidet."));
                            sikerult = false;
                            continue;
                        }
                    } catch (Exception ex) {
                        System.out.println(Portal.formatNarrator("" + ex.getMessage()));
                        sikerult = false;
                        continue;
                    }
                } while (koordinata == null);
                try {
                    if (koordinata.getSzelesseg() > 1) {
                        System.out.println(Portal.formatNarrator("Csak az első két oszlopba rakhatod az egységeidet."));
                        sikerult = false;
                        continue;
                    } else {
                        csatater.setMatrix(koordinata, griffEgyseg, egysegekSzama[2], false);
                        sikerult = true;
                    }
                } catch (Exception ex) {
                    System.out.println(Portal.formatNarrator("" + ex.getMessage()));
                    sikerult = false;

                }
            } while (!sikerult);
        }


        // Varázsló elhelyezése
        if (egysegekSzama[3] != 0) {
            do {
                do {
                    sikerult = false;
                    Portal.csataterKiirat(csatater);
                    System.out.println(Portal.formatNarrator("Hová kerüljenek a Varázsló?"));
                    System.out.print("Oszlop Sor (pl.: " + GREEN + "B 10" + RESET + "): ");
                    bejovo = in.nextLine();

                    try {
                        koordinata = new Koordinata(bejovo);
                        if (koordinata.getSzelesseg() >= 1) {
                            System.out.println(Portal.formatNarrator("Csak az első két oszlopba rakhatod az egységeidet."));
                            sikerult = false;
                            continue;
                        }
                    } catch (Exception ex) {
                        System.out.println(Portal.formatNarrator("" + ex.getMessage()));
                        sikerult = false;
                        continue;
                    }
                } while (koordinata == null);
                try {
                    if (koordinata.getSzelesseg() > 1) {
                        System.out.println(Portal.formatNarrator("Csak az első két oszlopba rakhatod az egységeidet."));
                        sikerult = false;
                        continue;
                    } else {
                        csatater.setMatrix(koordinata, varazsloEgyseg, egysegekSzama[3], false);
                        sikerult = true;
                    }
                } catch (Exception ex) {
                    System.out.println(Portal.formatNarrator("" + ex.getMessage()));
                    sikerult = false;

                }
            } while (!sikerult);
        }


        // Lovas elhelyezése
        if (egysegekSzama[4] != 0) {
            do {
                do {
                    sikerult = false;
                    Portal.csataterKiirat(csatater);
                    System.out.println(Portal.formatNarrator("Hová kerüljenek a Lovasok?"));
                    System.out.print("Oszlop Sor (pl.: " + GREEN + "B 10" + RESET + "): ");
                    bejovo = in.nextLine();

                    try {
                        koordinata = new Koordinata(bejovo);
                        if (koordinata.getSzelesseg() >= 1) {
                            System.out.println(Portal.formatNarrator("Csak az első két oszlopba rakhatod az egységeidet."));
                            sikerult = false;
                            continue;
                        }
                    } catch (Exception ex) {
                        System.out.println(Portal.formatNarrator("" + ex.getMessage()));
                        continue;
                    }
                } while (koordinata == null);
                try {
                    if (koordinata.getSzelesseg() > 1) {
                        System.out.println(Portal.formatNarrator("Csak az első két oszlopba rakhatod az egységeidet."));
                        sikerult = false;
                        continue;
                    } else {
                        csatater.setMatrix(koordinata, lovasEgyseg, egysegekSzama[4], false);
                        sikerult = true;
                    }
                } catch (Exception ex) {
                    System.out.println(Portal.formatNarrator("" + ex.getMessage()));
                    sikerult = false;

                }
            } while (!sikerult);
        }


        // TODO ellenfel elokeszitese


        Hos ellenfel = new Hos("ork");
        Elokeszites ellenfelElokeszites = new Elokeszites();
        ellenfelElokeszites.setArany(0);


        koordinata = new

                Koordinata(11, 4);
        csatater.setMatrix(koordinata, varazsloEgyseg, 2, true);
        koordinata = new

                Koordinata(10, 2);
        csatater.setMatrix(koordinata, foldmuvesEgyseg, 10, true);
        koordinata = new

                Koordinata(10, 7);
        csatater.setMatrix(koordinata, griffEgyseg, 5, true);
        //Portal.csataterKiirat(csatater);

        ellenfel.setTamadas(5);
        ellenfel.setVedekezes(8);
        ellenfel.setVarazsero(2);
        ellenfel.setTudas(3);
        ellenfel.setSzerencse(2);

        VillamcsapasVarazslat villamcsapasVarazslat = new VillamcsapasVarazslat();
        ellenfel.addVarazslatok("Villámcsapás", villamcsapasVarazslat);
        TuzlabdaVarazslat tuzlabdaVarazslat = new TuzlabdaVarazslat();
        ellenfel.addVarazslatok("Tűzlabda", tuzlabdaVarazslat);


        //ellenfel, hos kiirat

        System.out.println("   Ellenfél tudása:");
        Portal.hosKiirat(ellenfel, ellenfelElokeszites);
        Portal.varazslatKiirat(ellenfel);
        Portal.csataterKiirat(csatater);


        // Csata
        // TODO egység nélkül nem lehet csatázni
        System.out.println(Portal.formatNarrator("Vége a játéknak." + (

                csata(csatater, hos, ellenfel) ? "Te győztél" : "az ellefél győzött")));

    }

    public static boolean csata(Csateter csateter, Hos hos, Hos ellenfel) {
        Scanner in = new Scanner(System.in);
        String bejovo;
        String hely;


        System.out.println();
        System.out.println(Portal.formatNarrator("Kezdődjön a harc!"));
        boolean csataVege = false;
        int fordulo = 0;


        while (!csataVege) {

            if (csateter.getSorrend(hos.getMoral(), ellenfel.getMoral()) == null) {
                System.out.println(Portal.formatNarrator("Mind ketten meghaltatok, de ezt szerintem tekinthetjük úgy mintha te nyertél volna. Döntetlen"));
                return true;
            } else if (csateter.egysegekSzama == 1) {
                for (Csateter.EgysegCsataban egysegCsataban3 : csateter.getSorrend(hos.getMoral(), ellenfel.getMoral())) {
                    if (egysegCsataban3.ellenfel) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }

            Portal.sorrendKiirat(csateter, hos.getMoral(), ellenfel.getMoral());

            fordulo++;
            for (Csateter.EgysegCsataban egysegCsataban : csateter.getSorrend(hos.getMoral(), ellenfel.getMoral())) {
                csateter.matrix[csateter.getHely(egysegCsataban).getHosszusag()][csateter.getHely(egysegCsataban).getSzelesseg()].visszatamad = false;
            }
            // kiszamolni milyen sorrendben lépnek az egységek
            // TODO moral nem jol mukodik
            // TODO getSorrend(hos.getMoral()) nem jol mukodik null-al tér vissza


            System.out.println();
            System.out.println("- Ez a " + fordulo + ". kör.");
            for (Csateter.EgysegCsataban egysegCsataban : csateter.getSorrend(hos.getMoral(), ellenfel.getMoral())) {
                if (csateter.getSorrend(hos.getMoral(), ellenfel.getMoral()) == null) {
                    System.out.println(Portal.formatNarrator("Mind ketten meghaltatok, de ezt szerintem tekinthetjük úgy mintha te nyertél volna. Döntetlen"));
                    return true;
                } else if (csateter.egysegekSzama == 1) {
                    for (Csateter.EgysegCsataban egysegCsataban3 : csateter.getSorrend(hos.getMoral(), ellenfel.getMoral())) {
                        if (egysegCsataban3.ellenfel) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                }

                System.out.println();
                Portal.csataterKiirat(csateter);

                // TODO varázslat kiválasztás, varázslat megvalósítás
                if (!egysegCsataban.ellenfel) {
                    if (hos.getVarazslatok() != null) {
                        boolean varazsolt = false;
                        do {
                            Portal.varazslatKiirat(hos);
                            System.out.println(Portal.formatNarrator("Szeretnél varázsolni?" + GREEN + "  -igen-  -nem-" + RESET));
                            System.out.print("Varázsolsz? ");
                            bejovo = in.nextLine();

                            if (bejovo.equals("igen")) {
                                do {
                                    System.out.println(Portal.formatNarrator("Melyik varázslatod akarod használni?"));
                                    for (String i : hos.getVarazslatok().keySet()) {
                                        System.out.println(GREEN + "               -" + i + "-" + RESET);
                                    }
                                    System.out.print("Varázslat: ");
                                    bejovo = in.nextLine();
                                    if (bejovo.matches("Villámcsapás|Tűzlabda|Feltámasztás|Random|Kedvetlenítés")) {
                                        break;
                                    } else {
                                        System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
                                    }
                                } while (!bejovo.matches("Villámcsapás|Tűzlabda|Feltámasztás|Random|Kedvetlenítés"));
                                System.out.println(Portal.formatNarrator(bejovo + " varázslat kiválasztva."));

                                String bejovo2;
                                do {
                                    System.out.println(Portal.formatNarrator(hos.getVarazslat(bejovo).getMana() + " mannába fog kerülni. Mehet?" + GREEN + "  -igen-  -nem-" + RESET));
                                    System.out.print("Mehet? ");
                                    bejovo2 = in.nextLine();

                                    if (bejovo2.equals("igen")) {
                                        boolean sikerult;
                                        Koordinata koordinata = null;
                                        do {
                                            System.out.println(Portal.formatNarrator("Hová?"));
                                            System.out.print("Oszlop Sor (pl.: " + GREEN + "B 10" + RESET + "): ");
                                            hely = in.nextLine();
                                            try {
                                                koordinata = new Koordinata(hely);
                                            } catch (Exception ex) {
                                                System.out.println(Portal.formatNarrator("" + ex.getMessage()));
                                            }
                                        } while (koordinata == null);

                                        //varázslat megvalósítás
                                        csateter.varazsol(hos.getVarazslat(bejovo), hos, koordinata);
                                        hos.setManna(hos.getManna() - hos.getVarazslat(bejovo).getMana());
                                        varazsolt = true;
                                    } else if (bejovo2.equals("nem")) {
                                        break;
                                    } else {
                                        System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
                                    }
                                } while (!bejovo2.matches("igen|nem"));

                                System.out.println(Portal.formatNarrator("Még " + hos.getManna() + " mannád van."));
                                System.out.println();

                            } else if (bejovo.equals("nem")) {
                                break;
                            } else {
                                System.out.println(Portal.formatNarrator("Ezt nem értem. A fentiek közül válassz."));
                            }
                        } while (!varazsolt);
                    }

                }

                System.out.println(Portal.formatNarrator(egysegCsataban.egyseg.getNev() + " egység következik."));


                // ellenfel jon
                if (egysegCsataban.ellenfel) {
                    System.out.println(Portal.formatNarrator("Most az ellenfél jön."));
                    int cselekves = csateter.getRandomNumber(0, 13);
                    if (cselekves > 7) {
                        Koordinata ellenfelKoordinata = new Koordinata(csateter.getRandomNumber(0, 11), csateter.getRandomNumber(0, 9));
                        csateter.mozog(egysegCsataban, ellenfelKoordinata);
                    } else if (cselekves < 4) {
                        for (Csateter.EgysegCsataban egysegCsataban2 : csateter.getSorrend(hos.getMoral(), ellenfel.getMoral())) {
                            if (!egysegCsataban2.ellenfel) {
                                csateter.tamad(egysegCsataban, csateter.getHely(egysegCsataban2), ellenfel, hos, false);
                                break;
                            }
                        }

                    } else {
                        csateter.varakozik(egysegCsataban);
                    }


                    // jatekos jon
                } else {
                    // megkerdezni mit akar csinálni az egyseggel
                    do {
                        System.out.println(Portal.formatNarrator("Mit szeretnél vele csinálni?" + GREEN + " -várakozik- -támad- -mozog-" + RESET));
                        System.out.print("Mit tegyen egységed: ");
                        bejovo = in.nextLine();

                        if (bejovo.equals("várakozik")) {


                        } else if (bejovo.equals("támad")) {


                        } else if (bejovo.equals("mozog")) {


                        } else {
                            System.out.println(Portal.formatNarrator("Ezt nem értem. A kötőjelek között lévő szövegek közül válassz egyet."));
                        }

                    } while (!bejovo.matches("várakozik|támad|mozog"));
                    System.out.println(Portal.formatNarrator(bejovo + " kiválasztva."));

                    if (!bejovo.matches("várakozik")) {
                        // megkerdezni hova akarja alkalmazni
                        Koordinata koordinata = null;
                        boolean sikerult = false;
                        do {
                            do {
                                sikerult = false;
                                System.out.println(Portal.formatNarrator("Hová?"));
                                System.out.print("Oszlop Sor (pl.: " + GREEN + "B 10" + RESET + "): ");
                                hely = in.nextLine();

                                try {
                                    koordinata = new Koordinata(hely);
                                    sikerult = true;
                                } catch (Exception ex) {
                                    System.out.println(Portal.formatNarrator("" + ex.getMessage()));

                                }

                            } while (koordinata == null);
                            try {
                                // vegrehajt
                                if (bejovo.equals("támad")) {
                                    csateter.tamad(egysegCsataban, koordinata, hos, ellenfel, false);
                                    if (csateter.getEgysegekSzama() <= 1) {
                                        csataVege = true;
                                        continue;
                                    }
                                } else {
                                    csateter.mozog(egysegCsataban, koordinata);
                                }
                                sikerult = true;
                            } catch (Exception ex) {
                                System.out.println(Portal.formatNarrator("" + ex.getMessage()));
                                ex.printStackTrace();
                                sikerult = false;
                                continue;
                            }
                        } while (!sikerult);
                    } else {
                        csateter.varakozik(egysegCsataban);
                    }
                }
            }
        }
        return true;
    }

    public static void delay(int millis) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        // https://stackoverflow.com/questions/2663419/sleep-from-main-thread-is-throwing-interruptedexception
        // https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java
    }
}
