/*27 pontos megoldás. A generátoros-rénszarvasos nem jó
 *
 *
 * itt a generátorra egy megoldás:
 *
 * vector<Renszarvas> szantKeszit(unsigned db, unsigned sebesseg) {
    // megoldas
    vector<Renszarvas> rsz(db);

    generate(rsz.begin(),rsz.end(), [&sebesseg](){
        Renszarvas r(sebesseg);
        sebesseg = sebesseg / 2;
        return r;
    });
    return rsz;
}
 *
 * */

#include <iostream>
#include <string>
#include "exception"
#include "vector"
#include "map"
#include "set"
#include "algorithm"

using namespace std;

// Amelyik reszt szeretned tesztelni, az ne legyen komment
#ifndef TEST_BIRO
  #define HIBA_ES_OSZTALYT_KERES
  #define ATLAGTALANIT
  #define SZANT_KESZIT
  #define EGYEDIEKET_RENDEZ
#endif

#ifdef HIBA_ES_OSZTALYT_KERES
// 1. Feladat

// Hiba osztaly helye //

class Hiba: public exception {
public:
    string uzenet;
    Hiba(unsigned szam) {
        if(szam>50) this->uzenet="Sulyos hiba tortent";
        else if( szam>25) this->uzenet="Komoly hiba tortent";
        else this->uzenet="Kis hiba tortent";
    }

    const char *what() const _GLIBCXX_TXN_SAFE_DYN _GLIBCXX_NOTHROW override {
        return uzenet.c_str();
    }
};

// 2. Feladat

bool osztalytKeres(map<string, unsigned> osztalyok, const string& keresettOsztaly) {
    for(auto& osztaly : osztalyok) {
        if(osztaly.second>20) throw Hiba(osztaly.second);
    }
    auto valtozo = find_if(osztalyok.begin(),osztalyok.end(), [keresettOsztaly](pair<string, unsigned> objektum){
        return objektum.first==keresettOsztaly && objektum.second>10;
    });
    return valtozo!=osztalyok.end();
}
#endif

#ifdef ATLAGTALANIT
// 3. Feladat
void atlagtalanit(vector<int>& vektor){
    int atlagosszeg = 0;
    int elemekszama = 0;
    for(int elem : vektor) {
        atlagosszeg+=elem;
        elemekszama++;
    }
    atlagosszeg/=elemekszama;
    auto i = std::remove_if(vektor.begin(), vektor.end(),[atlagosszeg](int elem){
        return elem%atlagosszeg==0;
    });
    vektor.erase(i,vektor.end());
}
#endif

// 4. Feladat
#ifdef SZANT_KESZIT
struct Renszarvas {
    unsigned sebesseg;
    Renszarvas(unsigned sebesseg = 0): sebesseg(sebesseg) {}
    bool operator<(const Renszarvas& masik) const {
        return this->sebesseg<masik.sebesseg;
    }
};

vector<Renszarvas> szantKeszit(unsigned db, unsigned sebesseg) {
    // megoldas
    vector<Renszarvas> rsz(db);
    unsigned aktualisSebesseg = sebesseg;
    vector<Renszarvas> rssz(db);
    for(Renszarvas r : rssz) {
        r.sebesseg=aktualisSebesseg;
        aktualisSebesseg/=2;
    }
    auto i = rssz.begin();
    generate(rsz.begin(),rsz.end(), [i]() {
        Renszarvas reno;
        reno.sebesseg/i->sebesseg;
        return reno;
    });
}
#endif

#ifdef EGYEDIEKET_RENDEZ
// 5. Feladat

vector<int> egyedieketRendez(int* tomb, unsigned tombHossza){
    // megoldas
    set<int> halmaz;
    for(int i = 0; i<tombHossza;i++) {
        halmaz.insert(tomb[i]);
    }
    unsigned halmazElemei = halmaz.size();
    int t[halmazElemei];
    unsigned i = 0;
    for(int h : halmaz) {
        t[i++]=h;
    }
    for(i = 0; i<halmazElemei;i++) {
        for(unsigned j = i+1;j<halmazElemei;j++) {
            if(t[j]>t[i]) {
                int swap = t[j];
                t[j]=t[i];
                t[i]=swap;
            }
        }
    }
    vector<int> vektor(halmazElemei);
    for(i = 0;i<halmazElemei;i++) {
        vektor.at(i)=t[i];
    }
    return vektor; // ezt a sort modosithatod
}
#endif


/*int main() {
    int tomb[] = {1, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9};
    vector<int> v = egyedieketRendez(tomb, 11);
    return 0;
}*/