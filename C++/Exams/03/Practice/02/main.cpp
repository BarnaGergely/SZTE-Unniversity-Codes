#include <iostream>
#include <string>
#include "exception"
#include "vector"
#include "map"
#include "set"
#include "algorithm"

using namespace std;

// Amelyik reszt szeretned tesztelni, azt kommentezd ki!
#ifndef TEST_BIRO
#define EXCEPTION_N_KATALOGUS
#define MAZSOLA_VEKTOR
#define ONTOFORMA
#define JO_GYEREK
#endif

#ifdef EXCEPTION_N_KATALOGUS

// 1. Feladat

class AjandekHiba: public exception{
    string nev;
    unsigned tomeg;
public:
    AjandekHiba(const string &nev, unsigned int tomeg) : nev(nev), tomeg(tomeg) {}
};

// 2. Feladat
bool ajandekKatalogus(string nevek[], unsigned tomegek[], unsigned hossz, unsigned maxTomeg){ return false; }
#endif
#ifdef MAZSOLA_VEKTOR

// 3. Feladat
struct Nasi{
    string nev;
    string tipus;

    Nasi(const string &nev, const string &tipus) : nev(nev), tipus(tipus) {}

};

unsigned mazsolatValogat(Nasi nasik[], unsigned hossz){
    return 0;
}
#endif
#ifdef ONTOFORMA

// 4. Feladat
struct Ontoforma{
    unsigned magassag;
    unsigned szelesseg;
    string nev;
    Ontoforma(unsigned int magassag, unsigned int szelesseg, const string &nev) : magassag(magassag),
                                                                                  szelesseg(szelesseg), nev(nev) {}
};

void ontoformak(Ontoforma formak[], unsigned hossz){}
#endif

#ifdef JO_GYEREK
// 5. Feladat
struct Gyerek{
    string nev;
    unsigned josag;
    Gyerek(const string &nev, unsigned int josag) : nev(nev), josag(josag) {}
};

unsigned legjobbGyerekek(vector<Gyerek> gyerekek){
    return 0;
}
#endif
