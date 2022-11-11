import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Telepules {
    private String nev;
    private double terulet;
    private List<Kerulet> keruletek;
    private String email;

    /*
    public Telepules(Telepules telepules, java.lang.String szoveg , int szam) {
        new Telepules.Kerulet(telepules, szoveg, szam);
    }
    */


    public class Kerulet {
        private final String nev;
        private int lakosokSzama;

        private Kerulet(java.lang.String szoveg, int szam) {
            this.nev = szoveg;
            this.lakosokSzama = szam;
        }

        public String getNev() {
            return nev;
        }

        public int getLakosokSzama() {
            return lakosokSzama;
        }

        public void setLakosokSzama(int lakosokSzama) {
            this.lakosokSzama = lakosokSzama;
        }

        public double lakokAranya() {
            return getLakosokSzama();
        }

        @Override
        public String toString() {
            return nev + " (" + Telepules.this.getNev() + ")";
        }
    }


    public String getNev() {
        return nev;
    }

    public double getTerulet() {
        return terulet;
    }

    public String getEmail() {
        return email;
    }

    public void setTerulet(double terulet) {
        if (terulet <= 0) {
            this.terulet = 1;
        } else {
            this.terulet = terulet;
        }
    }

    public Telepules(String nev, double terulet) {
        String regEx = "[A-Z]\\w*";
        if(!nev.matches(regEx)){
            throw new IllegalArgumentException("Hibas varosnev: "+nev);
        } else {
            this.nev = nev;
        }
        if (terulet <= 0) {
            this.terulet = 1;
        } else {
            this.terulet = terulet;
        }
        keruletek = new ArrayList<Kerulet>();
        this.email = null;
    }


    public void emailFrissitese(String szoveg) throws TelepuleskezeloException {
        String[] szovegvagott = szoveg.split("@", 2);


        email = szoveg;

    }

    public void ujKerulet(String szoveg, int szam) {
        Kerulet kerulet = new Kerulet(szoveg, szam);
        keruletek.add(kerulet);
    }

    public void ujLakok(String szoveg, int szam) throws TelepuleskezeloException {
        if (keruletek != null) {
            for (Kerulet kerulet :
                    keruletek) {
                if (kerulet != null) {
                    if (szoveg.equals(kerulet.nev.toLowerCase())) {
                        kerulet.setLakosokSzama(kerulet.getLakosokSzama() + szam);
                        return;
                    }
                }
            }
        }
        throw new TelepuleskezeloException(this, "Nem talalhato a megadott kerulet: " + szoveg);
    }

    public int getLakosokSzama() {
        int lakosokszama = 0;
        for (Kerulet kerulet :
                this.keruletek) {
            lakosokszama += kerulet.getLakosokSzama();
        }
        return lakosokszama;
    }

    public double nepsuruseg() {
        return getLakosokSzama() / this.terulet;
    }
}
