#include <iostream>
#include <string>

using namespace std;

//===================Tel======================
class Tel {
    unsigned homerseklet;
    bool meleg;

    mutable unsigned szamlalo = 0;  // mutable adattag const metódusból is szerkeszthető
public:
    Tel(unsigned homerseklet) : homerseklet(homerseklet) {
        if (homerseklet > 10) {
            meleg = true;
        } else {
            meleg = false;
        }
    }

    unsigned get_homerseklet() const { // mutable adattag const metódusból is szerkeszthető
        szamlalo++;
        return homerseklet;
    }

    /*
     A virtual function is a member function which is declared within a base class and is re-defined (overridden) by a derived class. When you refer to a derived
     class object using a pointer or a reference to the base class, you can call a virtual function for that object and execute the derived class’s version of the function.

     A virtualizáció lényegében egy jelzés az objektumon, hogy ha Ősosztály típust is vár pl. a pointer ne csak azt dereferálja, hanem figyeljen oda arra is, hogy Gyerek típust kapot, azt máshogy kell kezelje.
     */
    virtual ~Tel() {    // szerintem ide virtual kell, mivel így képes lesz felismerni az objektum hogy az ősosztály vagy az ősosztály destruktorát kell e meghívni
        if (szamlalo > 3) {
            cout << "Itt a tavasz" << endl;
        }
    }
};

//====================Apo======================
class Apo {
    string nev;
public:
    Apo(const string &nev) : nev(
            nev) {} // Eredeti:  Apo(string nev) : nev(nev) {} -> ponterkénti átadás kell sima változó másolás helyett

    virtual ~Apo() {} // Eredeti: ~Apo() {} -> virtuallá tettem, magyarázat fentebb
};

//===================TelApo====================
class TelApo : public Tel, Apo {
    unsigned ajandekok;

public:
    unsigned get_ajandekok() const {    // Eredeti: unsigned get_ajandekok() {  -> const példányból csak a const metódusok érhetők el
        return ajandekok;
    }

    // konstruktorok
    // Először meg kell hívni az ősosztályok konstruktorát, csak utána szabad a saját változókat deklarálni
    TelApo() : Tel(36), Apo("Miklos"), ajandekok(
            0) {}  // a Tel és az Apo default konstruktorát hívom meg, nem konkrétan az adattagjaiknak adok értéket.

    TelApo(unsigned int homerseklet, const string &nev, unsigned int ajandekok) : Tel(homerseklet), Apo(nev),
                                                                                  ajandekok(ajandekok) {}
};

//==============Tobbi megoldas=================

class MikulasTalalkozo {
    /*
     Saját változók:
        A telapokSzama, a következő üres helyet jelőli a tömbben. 0-tól maxTelapokSzama-1 -ig szabad csak írni vele a tömbbe.
        A maxTelapokSzama a tömb méretét jelöli.
        Ha maxTelapokSzama == telapokSzama , akkor a tömb megtelt. Ha a maxTelapokSzama -dik helyre próbálnánk írni, hibát kapnánk, mert túlindexelnénk a tömböt.
    */
    int maxTelapokSzama;    // erre azért van szükség, hogy ga töröljük az adattagot, akkor is melékezzünk mekkora volt
    unsigned telapokSzama = 0;

    TelApo *telapok = nullptr;  // alapértelmezetten nullptr, így tudni, hogy deklarátuk e már a változót
public:
    MikulasTalalkozo(int maxTelapokSzama) : maxTelapokSzama(maxTelapokSzama) {
        telapok = new TelApo[maxTelapokSzama];
    }

    MikulasTalalkozo &operator+=(const TelApo &telapo) {
        /*
         A telapókSzama 0-tól maxTelapokSzama-1-ig írható
         A maxTelapokSzama a tömb méretét jelöli
        */

        if (maxTelapokSzama > telapokSzama) {
            telapok[telapokSzama] = telapo;
            telapokSzama++;
        } else {    // ha megtelt a tömb
            maxTelapokSzama = maxTelapokSzama + 2;
            // Egy tömb átméretezéshez ki kell törölnünk és létre kell hoznunk egy nagyobbat, viszont ilyenkor elvesznek belőle az datok, ezért kell a tempTelapok
            TelApo tempTelapok[maxTelapokSzama]; // ideiglenes tömb a másolás alatt adattárolásra. Nem dinamikus, így fel sem kell szabadítani.

            for (int i = 0; i < telapokSzama; ++i) {    // telapok másolása az ideiglenes tárolóba
                tempTelapok[i] = telapok[i];
            }

            // telapok tömb törlése majd új nagyobb tömb létrehozása
            delete[] telapok;
            telapok = new TelApo[maxTelapokSzama];

            for (int i = 0; i < telapokSzama; ++i) {    // adatok vissza másolása az ideiglenes tárolóból a telapok-ba.
                telapok[i] = tempTelapok[i];
            }
        }
        return *this;
    }

    void felez() {
        // ideiglenes tároló, maxTelapokSzama a feladat szerint páros, ezért az osztás nem dobhat hibát
        TelApo tempTelapok[maxTelapokSzama / 2];

        /*
         * Régi tömb indexe - Felezett tömb indexe
         * 1-0, 3-1, 5-2, 7-3, 9-4

         Több féle képpen is meg lehet csinálni ezt a másolást:
        for (int i = 1; i < telapokSzama; i = i + 2) {
            tempTelapok[i] = telapok[(i - 1) / 2];
        }
         */
        for (int i = 0; i < (maxTelapokSzama / 2); ++i) {
            tempTelapok[i] = telapok[2 * i + 1];
        }

        //  Adattagok frissítése
        maxTelapokSzama = maxTelapokSzama / 2;
        telapokSzama = telapokSzama / 2;

        delete[] telapok;
        telapok = new TelApo[maxTelapokSzama];
        for (int i = 0; i < telapokSzama; ++i) {
            telapok[i] = tempTelapok[telapokSzama];
        }
    }

    /*
     * Copy Construktor
     *
     * Ezzel az objektum létrehozásakor megadhatunk egy másik objektumot a konstruktorban
     * pl.:
     *  Osztaly objektum2();
     *  Osztaly objektum1(objektum2);   -> ehhez kell a copy construktor
     *
     * 1) Beszúrsz egy teljes konstruktort Alt+Ins-el
     * 2) A paramétereket egyetlen saját osztály tipusúra cseréled (const MikulasTalalkozo& masik)
     * 3) Az értékadásoknál megadod hogy a masik objektum adattagjait használja (masik.XY)
     * 4) A dinamikus tömb értékadásában létre hozol egy új, megfelelő méretű tömböt (new TelApo[maxTelapokSzama])
     * 4) Egy for ciklussal átmásolod a masik tömb értékeit a saját objektum tömbjébe
     * */
    MikulasTalalkozo(const MikulasTalalkozo &masik) : maxTelapokSzama(
            masik.maxTelapokSzama), telapokSzama(masik.telapokSzama), telapok(new TelApo[maxTelapokSzama]) {
        for (int i = 0; i < maxTelapokSzama; ++i) {
            telapok[i] = masik.telapok[i];
        }
    }

    /*
     * Assigment operator
     * sima = felüldefiniálása
     *
     * Ezzel úgy tudunk értéket adni egy objektumnak mint egy változónak.
     * pl.:
     *  Osztaly objektum2();
     *  Osztaly objektum1 = objektum2;   -> ehhez kell az assignment operator
     * */
    MikulasTalalkozo &operator=(const MikulasTalalkozo &masik) {
        // 1) megnézed hogy a masik objektum ugyan az e mint a jelenlegi objektum, ha igen, simán vissza adod a jelenlegit (ilyenkor nincs értelme módosítani a osztály változóit, mivel már megegyeznek)
        if (this == &masik) {
            return *this;
        }

        // 2) átmásolod a NEM dimanikus változókat
        // this. -ot vagy this-> akkor kötelező kiírni egy osztály változó elé, ha van másik ugyan olyan nevű változó a scope-ban is. Jelen esetben nincs rá szükség, de nem gond ha kiírod.
        maxTelapokSzama = masik.maxTelapokSzama;
        telapokSzama = masik.telapokSzama;

        // 3) Törlöd a régi dinamikus változókat majd létrehozol egy újat, ami már az új mertű lesz
        delete[]telapok;
        telapok = new TelApo[maxTelapokSzama];

        // 4) átmásolod a masik objektum dinamikus változóiból az értékeket az előbb létrehozott dinamikus változókba
        for (int i = 0; i < maxTelapokSzama; ++i) {
            telapok[i] = masik.telapok[i];
        }

        // 5) Vissza adjuk a módosított objektum referenciáját
        return *this;
    }

    virtual ~MikulasTalalkozo() {   // gyerek osztályba nem kötelező kiírni, ha kiírod nem hiba, de elméletileg elég az ősosztályban meglennie
        delete[] telapok;
    }
};

float statisztika(TelApo **telapok) {
    int i;
    unsigned osszeg = 0;
    for (i = 0; telapok[i] != nullptr; ++i) {
        osszeg += telapok[i]->get_ajandekok();
    }

    return float(osszeg)/float(i);
}

/* ROSSZ, de csak tesztelésre lenne:
int main(int argc, char *argv[]) {
    unsigned tombMeret = 5;

    TelApo &telapok;
    TelApo telapo(12,"pista", 2);
    for (int i = 0; i < tombMeret; ++i) {
        telapok[i] = telapo;
    }
    float szam = statisztika(telapok);
    return 0;
}
*/
