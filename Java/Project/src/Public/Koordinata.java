package Public;
import java.util.regex.*;

public class Koordinata {
    int szelesseg;
    int hosszusag;

    public Koordinata(int szelesseg, int hosszusag) {
        if (szelesseg>=0 && szelesseg<12) {
            this.szelesseg = szelesseg;
        } else {
            throw new NumberFormatException("Nem megfelelő szélességet adtál meg.");
        }

        if (hosszusag>=0 && hosszusag<10) {
            this.hosszusag = hosszusag;
        }  else {
            throw new NumberFormatException("Nem megfelelő hosszúságot adtál meg.");
        }
    }

    public Koordinata(String bejovo) {
        // TODO tesztelni

        if(!Pattern.matches("^[A-L][ ]{1}[1-9]$|^[A-L][ ]{1}10$", bejovo)) {
            throw new NumberFormatException("Nem megfelelő koordinátát adtál meg.");
        }
        String bejovoKoordinata[] = bejovo.split(" ");

        char ch = bejovoKoordinata[0].charAt(0);
        int szelessegSzam = ch - 'A' + 1;
        int hosszusagSzam = Integer.parseInt(bejovoKoordinata[1]);

        this.szelesseg=szelessegSzam-1;
        this.hosszusag=hosszusagSzam-1;

    }

    public int getSzelesseg() {
        return szelesseg;
    }

    public void setSzelesseg(int szelesseg) {
        this.szelesseg = szelesseg;
    }

    public int getHosszusag() {
        return hosszusag;
    }

    public void setHosszusag(int hosszusag) {
        this.hosszusag = hosszusag;
    }
}
