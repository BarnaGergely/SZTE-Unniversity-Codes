/**
 * A programunkban egy kutyat reprezentalo osztaly.
 * <p>
 * Minden kutyanak van neve, agresszivitasa, ehsege, es ismer valamennyi embert.
 *
 * @author <a href="mailto:antal@inf.u-szeged.hu">Antal Gabor</a>
 * @version 1.0
 */
public class Kutya {
    /**
     * A kutya teljes neve, amin torzskonyvezve van.
     */
    private final String nev;

    /**
     * Aktualis agresszivitasi szint.
     * <p>
     * Ez az adattag 0-100 kozotti ertekekkel hasznalhato.
     */
    private int agresszivitas;

    /**
     * Aktualis ehseget jelzo valtozo.
     * <p>
     * Ez az adattag 0-100 kozotti ertekekkel hasznalhato.
     */
    private int ehseg;

    /**
     * A kutya altal ismert emberek.
     * <p>
     * A kutya letrehozasakor kell megadni a meretet.
     */
    private final String[] ismertEmberek;

    public Kutya(String nev, int agresszivitas, int ehseg, int ismertEmberekSzama) {
        this.nev = nev;
        setAgresszivitas(agresszivitas);
        setEhseg(ehseg);
        this.ismertEmberek = new String[ismertEmberekSzama];
    }

    /**
     * Megadja, hogy eppen harapasveszelyes-e a kutyat megsimogatni.
     * <p>
     * A megsimogatas implementacioja egyelore nincs benne a programban,
     *
     * @return A harapasveszely merteke, ami 0-100 kozotti valos ertek.
     */
    public double harapasVeszely() {
        return Math.sqrt(ehseg * agresszivitas);
    }

    /**
     * Jatek a kutyaval.
     * <p>
     * Amennyiben a kutya agresszivitasa 0 felett van, akkor a jatek hatasara pontosan 1 ertekkel csokken az agresszivitas.
     * Amennyiben jatszunk a kutyaval:
     * - ha a kutya eddig nem ismerte az embert, akkor "megismeri", amennyiben van meg hely a memoriajaban.
     * - ha a kutya ismerte az embert, aki jatszik vele, nem adjuk hozza ujra a kutya altal ismert emberek tombjebe.
     *
     * @param ember
     */
    public void jatek(String ember) {
        if (this.agresszivitas > 0) {
            this.agresszivitas--;
        } else {
            // Nem szukseges a jatek, mert nem agressziv a kutya.
            return;
        }

        // Jatszottunk a kutyaval
        setEhseg(ehseg + 1);
        for (int i = 0; i < ismertEmberek.length; i++) {
            // mar ismeri az adott embert
            if (ismertEmberek[i] != null && ismertEmberek[i].equals(ember)) {
                break;
            }
            // Nem talalta meg az ismerosei kozott, es van hely a memoriajaban
            if (ismertEmberek[i] == null) {
                ismertEmberek[i] = ember;
                break;
            }
        }
    }

    /**
     * A kutya megetetese.
     *
     * @param etel Az etel, amit kap a kutya.
     * @return true, ha a kutya eszik.
     */
    public boolean etet(String etel) {
        // Nem ehes a kutya, visszaterunk hamissal.
        if (this.ehseg == 0) {
            return false;
        }
        // Evett a kutya
        this.ehseg--;
        // Ha tobb, mint 10 volt az agresszivitasa, csokkentjuk.
        // Tovabbi agresszivitas-csokkentes csak jatekkal lehetseges.
        if (agresszivitas > 10) {
            agresszivitas--;
        }
        return true;
    }

    /**
     * A kutya meglat egy adott embert, es eldontjuk, hogy ugatnia kell-e.
     *
     * @param ember Az ember, akit meglat a kutya.
     * @return true, ha ugat a kutya az adott ember lattan.
     */
    public boolean ugat(String ember) {
        // Ha agressziv a kutya, akkor mindenkire ugat.
        if (agresszivitas > 80) {
            return true;
        }
        // Megnezzuk, hogy ismeri-e a kutya az adott embert.
        for (int i = 0; i < ismertEmberek.length; i++) {
            // Ha ismeri, akkor nem ugat.
            if (ember.equals(ismertEmberek[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * A kutya megismer egy embert.
     * Ezt a programban jelenleg nem akarjuk hasznalni, de megkonnyiti a tesztelest.
     *
     * @param ember Az ember, akit megismer a kutya.
     */
    public void megismer(String ember) {
        for (int i = 0; i < ismertEmberek.length; i++) {
            // mar ismeri az adott embert
            if (ismertEmberek[i] != null && ismertEmberek[i].equals(ember)) {
                break;
            }
            // Nem talalta meg az ismerosei kozott, es van hely a memoriajaban
            if (ismertEmberek[i] == null) {
                ismertEmberek[i] = ember;
                break;
            }
        }
    }

    /**
     * Visszaadja, hogy hány embert ismer az adott kutya.
     * Ez az érték mindig kisebb, vagy egyenlő, mint az ismertEmberek tömb mérete.
     * 
     * @return
     */
    public int ismertEmberekSzama() {
        int ismert = 0;
        for (int i = 0; i < ismertEmberek.length; i++) {
            if (ismertEmberek[i] != null) {
                ismert++;
            }
        }
        return ismert;
    }

    // Getterek es szetterek
    public String getNev() {
        return nev;
    }

    public int getAgresszivitas() {
        return agresszivitas;
    }

    public void setAgresszivitas(int agresszivitas) {
        this.agresszivitas = Math.min(Math.max(0, agresszivitas), 100);
    }

    public String[] getIsmertEmberek() {
        String[] returnValue = new String[ismertEmberek.length];
        System.arraycopy(ismertEmberek, 0, returnValue, 0, ismertEmberek.length);
        return returnValue;
    }

    public int getEhseg() {
        return ehseg;
    }

    public void setEhseg(int ehseg) {
        this.ehseg = Math.min(Math.max(0, ehseg), 100);
    }

    /**
     * Ez a metódus csak tesztelési célokból van a kódban, éles használatra nem való.
     * <p>
     * A valóságban ilyen metódus helyett egyéb módon oldanánk meg a privát adattagok láthatóságát, de az nem tananyag.
     *
     * @return
     */
    public String[] getIsmertEmberekCsakTeszt() {
        return ismertEmberek;
    }
}
