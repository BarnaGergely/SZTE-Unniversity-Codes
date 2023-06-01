#include <iostream>
#include <string>
#include <exception>
#include <vector>
#include <map>
#include <set>
#include <algorithm>

using namespace std;

// Amelyik reszt szeretned tesztelni, az ne legyen komment
#ifndef TEST_BIRO
#define PROG_PONT
#define HAZI
#define KONYV
#define KEDVENC_ITAL
#endif

#ifdef PROG_PONT
// 1. FELADAT

#include <iostream>

class TulKevesPont : public exception {
    string uzenet;

public:
    explicit TulKevesPont(const float &szam) {
        if (szam < 50) {
            uzenet = "Megbuktam! :(";
        } else if (szam < 65) {
            uzenet = "Meglett a kettes!";
        } else {
            uzenet = "A kettesnel nincs jobb, csak szebb";
        }
    }

    const char *what() const noexcept override {
        return uzenet.c_str();
    }
};

// 2. FELADAT
int jegyek_szama(const map<string, unsigned> &eredmenyek) {
    unsigned osszeg = 0;
    for_each(eredmenyek.begin(), eredmenyek.end(), [&](const pair<string, unsigned> &elem) {
        osszeg += elem.second;
    });

    float atlag = (float) osszeg / (float) eredmenyek.size();

    if (atlag < 85) {
        throw TulKevesPont(atlag);
    }

    return (int) count_if(eredmenyek.begin(), eredmenyek.end(), [&](const pair<string, unsigned> elem) {
        return (elem.second < 85);
    });
}

#endif

#ifdef HAZI
#ifndef BIRO_TESTER
// 3. FELADAT
struct Hazi {
    unsigned hazik_szama; // ennyi hazi van a felevben
    unsigned teljesitett_hazik; // ennyi hazit teljesitettunk
};
#endif

void teljesitetteket_kitorol(vector<Hazi> &hazik, float teljesitesi_arany) {
    auto it = remove_if(hazik.begin(), hazik.end(), [&](Hazi elem) {
        return ((float) elem.teljesitett_hazik / (float) elem.hazik_szama > teljesitesi_arany);
    });

    hazik.erase(it, hazik.end());
}

#endif

#ifdef KONYV
#ifndef BIRO_TESTER_KONYV

// 4. FELADAT
struct Konyv {
    int hossz; // a könyv hossza
    string nyelv; // a könyv nyelve

    bool operator<(const Konyv &masik) const {
        return this->hossz < masik.hossz;
    }
};

#endif

set<Konyv> olvasas(Konyv *konyvek, unsigned meret) {
    set<Konyv> konyvekSet = set<Konyv>();
    for (int i = 0; i < meret; ++i) {
        konyvekSet.insert(konyvek[i]);
    }

    auto it = find_if(konyvekSet.begin(), konyvekSet.end(), [](const Konyv &elem) {
        return (elem.nyelv == "magyar");
    });

    if (it == konyvekSet.end()) {
        cout << "Konyv elolvasasa ezen a nyelven: angol" << endl;
    } else {
        cout << "Konyv elolvasasa ezen a nyelven: magyar" << endl;
    }

    return konyvekSet;
}

#endif

#ifdef KEDVENC_ITAL
// 5. FELADAT
typedef struct KedvencItal {
    string kinek; // kinek a kedvenc itala
    string mi; // mi a kedvenc itala
} KedvencItal;

// kedvenc italok előfordulási számának táolásához és rendezéséhez használt struktúra
typedef struct ItalElofordulas {
public:
    string mi;
    unsigned elofordulasokSzama;

    explicit ItalElofordulas(const string &mi) : mi(mi), elofordulasokSzama(1) {}

    bool operator<(const ItalElofordulas &rhs) const {
        return elofordulasokSzama < rhs.elofordulasokSzama;
    }

    bool operator>(const ItalElofordulas &rhs) const {
        return rhs < *this;
    }

    bool operator<=(const ItalElofordulas &rhs) const {
        return !(rhs < *this);
    }

    bool operator>=(const ItalElofordulas &rhs) const {
        return !(*this < rhs);
    }

} ItalElofordulas;

vector<string> kedvenc_italok(KedvencItal *ital_infok, unsigned darab) {

    vector<ItalElofordulas> kedvencek;

    // Megszámolja hányszor szerepel adott ital az ital_infok-ban és eltárolja az előforduálok számát a kedvencek-ben
    for (int i = 0; i < darab; ++i) {
        auto it = find_if(kedvencek.begin(), kedvencek.end(), [&](ItalElofordulas elem) {
            return elem.mi == ital_infok[i].mi;
        });
        if (it == kedvencek.end()) {
            kedvencek.emplace_back(ital_infok[i].mi);
        } else {
            it->elofordulasokSzama++;
        }
    }

    // Kedvencek rendezése csökkenő sorrendbe
    sort(kedvencek.begin(), kedvencek.end(), greater<>());

    // átlalakítás olyan formátumba, ahogy kérte a feladat
    vector<string> kedvencekNevei = vector<string>();
    for_each(kedvencek.begin(), kedvencek.end(), [&](ItalElofordulas elem) {
        kedvencekNevei.push_back(elem.mi);
    });

    return kedvencekNevei;
}

#endif

int main() {
    // 1. HIBA: Nem tudok osztani :(

    unsigned osszeg = 13;   //tömb elemeinek értékenek összege
    size_t size = 7;    // tömb mérete

    float rosszAtlag = osszeg / size;
    cout << "Hibasan szamolt atlag: " << rosszAtlag << endl;

    float joAtlag = (float) osszeg / size;  // még az osztás előtt float-á konvertálom az egyik számot
    cout << "Jol szamolt atlag: " << joAtlag << endl;

    float joAtlag2 = (float) osszeg / (float) size;
    cout << "Jol szamolt atlag V2: " << joAtlag2 << endl;

    // Tanulság: Ha nem konvertáljuk osztásnál legalább az egyik számot float-á, nem törtet fogunk kapni

    // Ezen 7 pontom ment el, mert egy helyen az utolsó pillanatban észre vettem ezt, de ti nem, így 14 pontot buktatok



    // 2. HIBA: Nem néztem a compile fájlokat
    /* A bíró hibás volt és nem sima struktúraként próbálta használni az utolsó feladatban a KedvencItal-t, hanem
     osztályként vagy typedef struct ként, így le sem fordult az uccsó feladat, amit a fél ZH alatt írtam
     */

    // 3. HIBA: sort függvénnyel valószínőleg nem lehet map-et rendezni (de én azt próbáltam). Vektort kellett volna használnom helyette

    //  4. HIBA: Nem hoztam létre külön struktúrát vagy osztályt az italok előfordulási számának tárolására, ami nagyban
    //  gyorsította volna a munkát
}

