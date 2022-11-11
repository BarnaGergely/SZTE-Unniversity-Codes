import java.util.ArrayList;
import java.util.List;

public class Megye {
    private String nev;
    private List<Telepules> telepulesek;
    private String web;

    public Megye(String nev) {
        this.nev = nev;
        telepulesek = new ArrayList<>();

    }

    public boolean ujTelepules(String telepules) {

        return true;
    }
    public void webcimFrissites(String szoveg){
        if(szoveg.toLowerCase().contains(nev.toLowerCase())){
            web = szoveg;
        } else {
            throw new IllegalArgumentException("Hibas webcim: "+szoveg);
        }
    }
    public void ujLakok(int index, String kerulet, int lakok){

    }
    public int keres(String szoveg){
        int szam = 0;
        for (Telepules telepules:
             telepulesek) {
            if (telepules.getNev().toLowerCase().contains(szoveg.toLowerCase())){
                szam++;
            }
        }
        return szam;
    }
    public int lakossag(){
        int szam = 0;
        for (Telepules telepules:
                telepulesek) {
            szam += telepules.getLakosokSzama();
        }
        return szam;
    }

    @Override
    public String toString() {
        return  nev+" megye ("+web+")";
    }
}
