package Public;

import Varazslat.Varazslat;

import java.util.HashMap;


public class Hos {
    int tamadas; // az egységek sebzését növeli meg, tulajdonságpontonként 10%-kal
    int vedekezes; // az egységeket ért sebzést csökkenti, tulajdonságpontonként 5%-kal
    int varazsero;  //a hos által idézett varázslatok erosségét növeli
    int tudas;  //  a hos maximális mannapontjait növeli, tulajdonságpontonként 10-zel
    int moral;  //  az egységek kezdeményezését növeli, tulajdonságpontonként 1-gyel
    int szerencse; //  az egységek kritikus támadásának esélyét növeli, tulajdonságpontonként 5%-kal
    // TODO tulajdonság maximális értéke 10 lehet

    int manna;

    String frakcio;

    HashMap<String, Varazslat> varazslatok = new HashMap<String, Varazslat>();

    public Hos(String frakcio) {
        this.tamadas = 1;
        this.vedekezes = 1;
        this.varazsero = 1;
        this.tudas = 1;
        this.moral = 1;
        this.szerencse = 1;
        this.frakcio = frakcio;
        this.manna = tudas*10;
    }

    // TODO maximum

    public int getTamadas() {
        return tamadas;
    }

    public void setTamadas(int tamadas) {
        this.tamadas = tamadas;
    }

    public int getVedekezes() {
        return vedekezes;
    }

    public void setVedekezes(int vedekezes) {
        this.vedekezes = vedekezes;
    }

    public int getVarazsero() {
        return varazsero;
    }

    public void setVarazsero(int varazsero) {
        this.varazsero = varazsero;
    }

    public int getTudas() {
        return tudas;
    }

    public void setTudas(int tudas) {
        this.tudas = tudas;
        this.manna = 10*tudas;
    }

    public int getMoral() {
        return moral;
    }

    public void setMoral(int moral) {
        this.moral = moral;
    }

    public int getSzerencse() {
        return szerencse;
    }

    public void setSzerencse(int szerencse) {
        this.szerencse = szerencse;
    }

    public String getFrakcio() {
        return frakcio;
    }

    public void setFrakcio(String frakcio) {
        this.frakcio = frakcio;
    }

    public int getManna() {
        return manna;
    }

    public void setManna(int manna) {
        this.manna = manna;
    }

    public void addVarazslatok(String nev, Varazslat varazslat) {
        this.varazslatok.put(nev, varazslat);
    }
    public int getsizeVarazslatok() {
        return this.varazslatok.size();
    }
    public HashMap<String, Varazslat> getVarazslatok() {
        return this.varazslatok;
    }
    public Varazslat getVarazslat(String nev) {
        return this.varazslatok.get(nev);
    }

    // TODO tulajdonsag ar tesztelese
    public int getAr(int szint) {
        int ar = 5;
        for (int i = 1; i < szint; i++) {
            ar = (int) Math.ceil(ar*1.1);
        }
        return ar;
    }

}
