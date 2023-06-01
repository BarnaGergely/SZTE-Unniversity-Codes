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

// TODO TulKevesPont osztaly helye //
#include <iostream>
class TulKevesPont: public exception{
    string uzenet;

public:
    explicit TulKevesPont(const float &szam) {
        if (szam<50) {
            uzenet = "Megbuktam! :(";
        } else if (szam<65) {
            uzenet = "Meglett a kettes!";
        } else {
            uzenet =  "A kettesnel nincs jobb, csak szebb";
        }
    }

    const char * what() const noexcept override {
        return uzenet.c_str();
    }
};

int main() {
    std::cout << "Hello, World!" << std::endl;
    return 0;
}

// 2. FELADAT
int jegyek_szama(const map<string, unsigned>& eredmenyek) {
    unsigned osszeg = 0;
    for_each(eredmenyek.begin(), eredmenyek.end(), [&](const pair<string, unsigned> elem){
        osszeg+=elem.second;
    });

    float atlag = osszeg/eredmenyek.size();

    if (atlag<85) {
        throw TulKevesPont(atlag);
    }

    return (int)count_if(eredmenyek.begin(), eredmenyek.end(), [&](const pair<string, unsigned> elem){
        return (elem.second<85);
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

void teljesitetteket_kitorol(vector<Hazi>& hazik, float teljesitesi_arany) {
    auto it = remove_if(hazik.begin(), hazik.end(), [&](Hazi elem){
        return ((float)elem.teljesitett_hazik/(float)elem.hazik_szama > teljesitesi_arany);
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

    bool operator<(const Konyv& masik)const{
        return this->hossz<masik.hossz;
    }
};
#endif

set<Konyv> olvasas(Konyv* konyvek, unsigned meret) {
    set<Konyv> konyvekSet = set<Konyv>();
    for (int i = 0; i < meret; ++i) {
        konyvekSet.insert(konyvek[i]);
    }

    auto it = find_if(konyvekSet.begin(), konyvekSet.end(), [](const Konyv &elem){
        return (elem.nyelv=="magyar");
    });

    if (it == konyvekSet.end()){
        cout << "Konyv elolvasasa ezen a nyelven: angol" << endl;
    } else {
        cout << "Konyv elolvasasa ezen a nyelven: magyar" << endl;
    }

    return konyvekSet;
}
#endif

#ifdef KEDVENC_ITAL
// 5. FELADAT
struct KedvencItal {
    string kinek; // kinek a kedvenc itala
    string mi; // mi a kedvenc itala
};

vector<string> kedvenc_italok(KedvencItal* ital_infok, unsigned darab) {
    vector<string> kedvencekNevei = vector<string>();

    map<string, unsigned> kedvencek = map<string, unsigned>();

    for (int i = 0; i < darab; ++i) {
        auto it = find_if(kedvencek.begin(), kedvencek.end(), [&](pair<string, unsigned> elem){
            return elem.first == ital_infok[i].mi;
        });
        if (it == kedvencek.end()){
            kedvencek.insert(pair<string, unsigned >(ital_infok[i].mi,1));
        } else {
            it->second++;
        }
    }

    /*
    sort(kedvencek.begin(), kedvencek.end(), [](const auto &a, const auto &b){
        return (a.second > b.second);
    });
     */

    for_each(kedvencek.begin(), kedvencek.end(), [&](pair<string, unsigned> elem){
        kedvencekNevei.push_back(elem.first);
    });

    return kedvencekNevei;


}
#endif
