#include <iostream>
#include <string>

using namespace std;

/*A tanár szerint még roszabb lett mint az órai*/

/*CHANGE LIST:
 * Rájönni mért nem jó a felszabadítás
    * Megoldás: Disz-be nem kellett struktor
 * Megjavítani a +=-t
    * Megoldás: nem tároltuk el, hogy mekkora helyet foglalnak a díszek, csak azt hogy hány dísz van
 * Leszed metódus megjavítása
    *
 *
*/

//===========================Jarmu================
class Jarmu {
protected:
    string nev;
public:
    Jarmu(const string &nev) : nev(nev) {}

    virtual void leszed(int mennyiseg) = 0;

    virtual ~Jarmu() {}
};


//===========================Motor===============
class Disz {
private:
    string nev;
    unsigned meret;
public:
    Disz(const string &nev, unsigned meret) : nev(nev), meret(meret) {}

    Disz() = default;

    bool operator==(const Disz &masik) const {
        return this->nev == masik.nev && this->meret == masik.meret;
    }

    unsigned int getMeret() const {
        return meret;
    }
};

//=====================Uj megoldasok==============
class Motor : public Jarmu {
    Disz *diszek;
    unsigned diszMeret;
    unsigned diszKovetkezoSzabadHely = 0;
    unsigned oldalMeret;
    unsigned oldalSzabad;

public:
    Motor(const string &nev, unsigned diszMeret, unsigned oldalMeret) : Jarmu(nev),
                                                                        diszMeret(diszMeret),
                                                                        oldalMeret(oldalMeret),
                                                                        oldalSzabad(oldalMeret) {
        diszek = new Disz[diszMeret];
    }

    void leszed(int megadottMennyiseg) override {
        // Ha több díszt szeretnénk leszedni, mint amennyi elfér a tömbben, megnöveljük 2-vel a méretét
        if (diszMeret <= megadottMennyiseg) {
            Disz tempdiszek[diszMeret+2];
            for (int i = 0; i < diszMeret; ++i) {   // adatok átmásolása az ideiglenes tömbbe
                tempdiszek[i] = diszek[i];
            }

            diszMeret += 2; // Megnöveljük 2-vel a díszek tömb alapértelmezett méretének értékét
            delete[] diszek;
            diszek = new Disz[diszMeret];   // Újra deklaráljuk a tömböt a megnövelt mérettel

            for (int i = 0; i < diszMeret; ++i) {   // adatok vissza másolása az eredeti tömbbe
                diszek[i] = tempdiszek[i];
            }
        }


        // Feladat: Ha a motor kifogyna a díszekből mielőtt leszedtük volna az összeset,
        // ne történjen semmi különleges, a metódus haladjon tovább hiba nélkül!
        int ellenorzottMennyiseg = megadottMennyiseg;
        if (megadottMennyiseg > diszMeret) {
            // Így hiába adunk meg nagyobb értéket, nem fog a tömb méreténél tovább futni a feldolgozás
            ellenorzottMennyiseg = diszMeret;
        }

        for (int i = (ellenorzottMennyiseg-1); i >= 0; --i) {  // Visszafele járjuk be a tömböt
            oldalSzabad += diszek[i].getMeret();
            diszKovetkezoSzabadHely--;
        }
    }

    Motor &operator+=(const Disz &disz) {   // Ha már szerepel ilyen elem a listában, akkor módosítás nélkül tér vissza
        for (int i = 0; i < diszMeret; ++i) {
            if (disz == diszek[i]) {
                return *this;
            }
        }

        int temp = (int) oldalSzabad - disz.getMeret();
        if (diszMeret > diszKovetkezoSzabadHely && ( temp >= 0)) {  // Ha van hely a listában és bele fér a rendelkezésre álló méretbe a dísz
            diszek[diszKovetkezoSzabadHely] = disz;
            diszKovetkezoSzabadHely++;
            oldalSzabad -= disz.getMeret();
        } else if (diszMeret == diszKovetkezoSzabadHely) {  // Ha nincs a listában, de túl se indexeltük még a tömböt
            leszed(diszMeret);  // vissza állatja 0-ra a diszKovetkezoSzabadHely-et és az oldalszabad-ot oldal meret-re
            diszek[diszKovetkezoSzabadHely] = disz;   // Berakjuk az 1. helyre a díszt
            diszKovetkezoSzabadHely++;
        }

        return *this;
    }

    Motor(const Motor &m) : Jarmu(m.nev),
                            diszMeret(m.diszMeret),
                            oldalMeret(m.oldalMeret),
                            oldalSzabad(m.oldalSzabad),
                            diszKovetkezoSzabadHely(m.diszKovetkezoSzabadHely),
                            diszek(new Disz[diszMeret]) {
        // dinamikus tömb értékeinek másolása
        for (int i = 0; i < diszMeret; ++i) {
            diszek[i] = m.diszek[i];
        }
    }

    Motor &operator=(const Motor &m) {
        if (this == &m) {   // Ha önmagunkat akarjuk értékül adni saját magunknak, ne változzun semmi
            return *this;
        }

        // osztály változóinak másolása
        diszMeret = m.diszMeret;
        nev = m.nev;
        oldalMeret = m.oldalMeret;
        oldalSzabad = m.oldalSzabad;
        diszKovetkezoSzabadHely = m.diszKovetkezoSzabadHely;

        // dinamikus változók másolása
        delete[] diszek;
        diszek = new Disz[diszMeret];
        for (int i = 0; i < diszMeret; ++i) {
            diszek[i] = m.diszek[i];
        }

        return *this;
    }

    virtual ~Motor() {
        delete[] diszek;
        cout << diszek[0].getMeret() << diszek[1].getMeret() << diszek[2].getMeret() << endl;
    }
};


int main() {

    /* Amiket teszteltem és elvileg jók:
     *
     * Bele fer a tombbe a hozza daott matricak mennyisege és merete
     * matricák méretének az összege nagyobb mint a maximum, amit az elején megadtunk
     * Több matricát adunk meg mint lehet
     *

    Disz disz1("matrica1",1);
    Disz disz2("matrica2",1);
    Disz disz3("matrica3",1);
    Motor motor("Járgány", 2, 3);
    motor+=disz1;
    motor+=disz2;
    motor+=disz3;
    cout << "Main" << endl;
     */
    return 0;
}