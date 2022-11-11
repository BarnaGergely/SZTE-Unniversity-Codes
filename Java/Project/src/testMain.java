import Egyseg.Egyseg;
import Egyseg.Kepesseg.GyogyitasKepesseg;
import Egyseg.Kepesseg.LovesKepesseg;
import Egyseg.Kepesseg.VegtelenVisszatamadasKepesseg;
import Public.*;
import Varazslat.KedvetlenitesVarazslat;
import Varazslat.RandomVarazslat;
import org.junit.jupiter.api.*;

public class testMain {
    @Test
    void testCsata(){
        float[] egysegek = new float[5];
        egysegek[2] = 3;
        egysegek[1] = 4;
        Csateter csatater = new Csateter();

        LovesKepesseg lovesKepesseg = new LovesKepesseg("Lövés");
        VegtelenVisszatamadasKepesseg vegtelenVisszatamadasKepesseg = new VegtelenVisszatamadasKepesseg("Végtelen visszatámadás");
        GyogyitasKepesseg gyogyitasKepesseg = new GyogyitasKepesseg("Gyógyítás");

        Egyseg foldmuvesEgyseg = new Egyseg("Földműves", 2, 1, 1, 3, 4, 8, null);
        Egyseg ijaszEgyseg = new Egyseg("Íjász", 6, 2, 4, 7, 4, 9, lovesKepesseg);
        Egyseg griffEgyseg = new Egyseg("Griff", 15, 5, 10, 30, 7, 15, vegtelenVisszatamadasKepesseg);
        Egyseg varazsloEgyseg = new Egyseg("Varázsló", 10, 1, 2, 10, 6, 10, gyogyitasKepesseg);
        Egyseg lovasEgyseg = new Egyseg("Lovas", 4, 1, 2, 1, 10, 7, null);

        Koordinata koordinata = new Koordinata(0,2);
        csatater.setMatrix(koordinata,foldmuvesEgyseg, 13, false);

        koordinata = new Koordinata(11,4);
        csatater.setMatrix(koordinata,foldmuvesEgyseg, 42, true);
        //Portal.csataterKiirat(csatater);

        Hos hos = new Hos("ember");
        Hos ellenfel = new Hos("ork");
        hos.setTamadas(7);
        ellenfel.setVedekezes(10);

        System.out.println(Portal.formatNarrator("Vége a játéknak." + (Main.csata(csatater, hos, ellenfel) ? "Te győztél" : "az ellefél győzött")));
    }

    public static void main(String[] args) {
        float[] egysegek = new float[5];
        egysegek[2] = 3;
        egysegek[1] = 4;
        Csateter csatater = new Csateter();
        LovesKepesseg lovesKepesseg = new LovesKepesseg("Lövés");
        VegtelenVisszatamadasKepesseg vegtelenVisszatamadasKepesseg = new VegtelenVisszatamadasKepesseg("Végtelen visszatámadás");
        GyogyitasKepesseg gyogyitasKepesseg = new GyogyitasKepesseg("Gyógyítás");

        Egyseg foldmuvesEgyseg = new Egyseg("Földműves", 2, 1, 1, 3, 4, 8, null);
        Egyseg ijaszEgyseg = new Egyseg("Íjász", 6, 2, 4, 7, 4, 9, lovesKepesseg);
        Egyseg griffEgyseg = new Egyseg("Griff", 15, 5, 10, 30, 7, 15, vegtelenVisszatamadasKepesseg);
        Egyseg varazsloEgyseg = new Egyseg("Varázsló", 10, 1, 2, 10, 6, 10, gyogyitasKepesseg);
        Egyseg lovasEgyseg = new Egyseg("Lovas", 4, 1, 2, 1, 10, 7, null);

        Koordinata koordinata = new Koordinata(0,5);
        csatater.setMatrix(koordinata,griffEgyseg, 13, false);

        koordinata = new Koordinata(11,4);
        csatater.setMatrix(koordinata,foldmuvesEgyseg, 2, true);
        koordinata = new Koordinata(10,4);
        csatater.setMatrix(koordinata,lovasEgyseg, 2, true);
        koordinata = new Koordinata(10,1);
        csatater.setMatrix(koordinata,griffEgyseg, 2, true);
        //Portal.csataterKiirat(csatater);

        Hos hos = new Hos("ember");
        Hos ellenfel = new Hos("ork");
        hos.setTamadas(7);
        hos.setVedekezes(10);
        hos.setMoral(4);
        hos.setTudas(6);
        ellenfel.setTamadas(7);
        ellenfel.setVedekezes(10);
        ellenfel.setMoral(6);

        RandomVarazslat randomVarazslat = new RandomVarazslat();
        KedvetlenitesVarazslat kedvetlenitesVarazslat = new KedvetlenitesVarazslat();

        hos.addVarazslatok("Random", randomVarazslat);
        hos.addVarazslatok("Kedvetlenítés", kedvetlenitesVarazslat);

        ellenfel.addVarazslatok("Random", randomVarazslat);
        ellenfel.addVarazslatok("Kedvetlenítés", kedvetlenitesVarazslat);

        System.out.println(Portal.formatNarrator("Vége a játéknak." + (Main.csata(csatater, hos, ellenfel) ? "Te győztél" : "az ellefél győzött")));
    }
}
