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

//IDE TEDD AZ EXCEPTIONT!!!

class AjandekHiba : public exception
{
public:
    string uzenet;

    AjandekHiba(const string& nev, unsigned int tomeg)
    {
        this->uzenet = nev + " nevu ajandek " + to_string(tomeg) + " grammal meghaladja a maximum tomeget!";
    }

    const char *what() const noexcept override {
        return uzenet.c_str();
    }
};

// 2. Feladat
bool ajandekKatalogus(string nevek[], unsigned tomegek[], unsigned hossz, unsigned maxTomeg)
{
    map<string, unsigned int> tarolo = map<string, unsigned int>();

    for (int i = 0; i < hossz; ++i) {
        if(tomegek[i] > maxTomeg)
        {
            throw AjandekHiba(nevek[i],tomegek[i]);
        }
        else
        {
            tarolo[nevek[i]] = tomegek[i];
        }
    }

    auto itr = find_if(tarolo.begin(), tarolo.end(), [](pair<string,unsigned int> pair){return pair.second % 7 == 0;});

    if(itr != tarolo.end())
    {
        cout << itr->first << endl;

        return true;
    }

    return false;
}
#endif
#ifdef MAZSOLA_VEKTOR

// 3. Feladat
struct Nasi{
    string nev;
    string tipus;
    
    Nasi(const string &nev, const string &tipus) : nev(nev), tipus(tipus) {}
};

unsigned mazsolatValogat(Nasi nasik[], unsigned hossz){
    vector<Nasi> vektor = vector<Nasi>();

    for (int i = 0; i < hossz; ++i)
    {
        vektor.push_back(nasik[i]);
    }

    vektor.erase(remove_if(vektor.begin(), vektor.end(), [](Nasi item){return item.nev == "Mazsola";}), vektor.end());

    auto ct = count_if(vektor.begin(), vektor.end(), [](Nasi item){return item.tipus == "Edes";});

    return ct;
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
     bool operator<(const Ontoforma& masik) const
     {
        return magassag * szelesseg < masik.magassag * masik.szelesseg;
     }
};

void ontoformak(Ontoforma formak[], unsigned hossz)
{
    set<Ontoforma> container = set<Ontoforma>();

    for (int i = 0; i < hossz; ++i)
    {
        container.insert(formak[i]);
    }

    for_each(container.begin(), container.end(), [](Ontoforma item){cout << item.nev << endl;});
}
#endif

#ifdef JO_GYEREK
// 5. Feladat
struct Gyerek{
    string nev;
    unsigned josag;
    Gyerek(const string &nev, unsigned int josag) : nev(nev), josag(josag) {}

    bool operator<(const Gyerek& masik)
    {
        return josag > masik.josag;
    }
};

unsigned legjobbGyerekek(vector<Gyerek> gyerekek)
{
    sort(gyerekek.begin(), gyerekek.end());

    for (int i = 0; i < 3; ++i)
    {
        cout << gyerekek[i].nev << endl;
    }

    double avg = 0.0;

    double josag = 0.0;

    for (int i = 0; i < gyerekek.size(); ++i)
    {
        josag += gyerekek[i].josag;
    }

    avg = josag / gyerekek.size();

    auto ct = count_if(gyerekek.begin(), gyerekek.end(), [&](Gyerek item){return item.josag < avg;});

    return ct;
}
#endif