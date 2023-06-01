#include <iostream>
#include <string>

using namespace std;

//===========================Allat================
class Allat {
protected:
    std::string nev;
public:
    Allat(const string &nev) : nev(nev) {}

    virtual void eszik(int szam);
};

//===========================Gyumolcs===============
class Gyumolcs {
private:
    std::string nev;
    unsigned tapertek;
public:
    Gyumolcs(const string &nev, unsigned tapertek) : nev(nev), tapertek(tapertek) {}

    Gyumolcs() = default;

    bool operator==(const Gyumolcs &masik) const {
        return this->nev == masik.nev && this->tapertek == masik.tapertek;
    }

    virtual ~Gyumolcs(){}
};

//=====================Uj megoldasok==============

class Suni : Allat {
    Gyumolcs *gyumolcsok = nullptr;
    unsigned evett = 0;
    unsigned elfogyasztott;
    unsigned almaTombben;
    unsigned tombMeret;
public:
    explicit Suni(string nev, unsigned almaMennyiseg, unsigned almaEnni) : Allat(nev) {
        elfogyasztott = almaEnni;
        gyumolcsok = new Gyumolcs[almaMennyiseg];
        tombMeret = almaMennyiseg;
        elfogyasztott = almaEnni;
        this->almaTombben = 0;
    }

    Suni(const Suni &suni) :
    Allat(nev),
    gyumolcsok(new Gyumolcs[tombMeret]),
    elfogyasztott(suni.elfogyasztott),
    tombMeret(suni.tombMeret),
    almaTombben(suni.almaTombben) {
        for (int i = 0; i < almaTombben; ++i) {
            gyumolcsok[i] = suni.gyumolcsok[i];
        }
    }

    Suni &operator=(const Suni &suni) {
        if (this == &suni) return *this;

        delete[] gyumolcsok;
        nev = suni.nev;
        evett = suni.evett;
        elfogyasztott=suni.elfogyasztott;
        tombMeret = suni.tombMeret;
        almaTombben = suni.almaTombben;
        gyumolcsok = new Gyumolcs[tombMeret];
        for (int i = 0; i < almaTombben; ++i) {
            gyumolcsok[i] = suni.gyumolcsok[i];
        }

        return *this;
    }

    Suni &operator+=(const Gyumolcs &gyumolcs) {
        bool bennevan = false;
        for (int i = 0; i < almaTombben; ++i) {
            if (gyumolcsok[i] == gyumolcs) {
                bennevan = true;
                break;
            }
        }
        if (!bennevan && almaTombben < tombMeret - 1) {
            gyumolcsok[almaTombben] = gyumolcs;
            almaTombben++;
        } else if (!bennevan && almaTombben == tombMeret) {
            eszik(tombMeret);
            almaTombben = 0;
            gyumolcsok[almaTombben] = gyumolcs;
            almaTombben++;
        }
        return *this;
    }

    void eszik(int szam) override {
        for (int i = szam - 1; i >= 0; --i) {
            //evett+=gyumolcsok[i].tapertek;
            gyumolcsok[i] = Gyumolcs();
        }

        if (evett > elfogyasztott)
            evett = evett - elfogyasztott;
        Gyumolcs *ujGyumolcsok = new Gyumolcs[tombMeret + 2];
        tombMeret += 2;
        ujGyumolcsok = gyumolcsok;
        delete[] gyumolcsok;
        gyumolcsok = new Gyumolcs[tombMeret];
        gyumolcsok = ujGyumolcsok;
        elfogyasztott = elfogyasztott * 2;
        almaTombben = 0;
    }

};

void Main(){
    Suni* sun = new Suni("suni", 12, 64);
    Suni* sun1 = new Suni("suni1", 13, 65);

    Suni* sunMasolt = new Suni(*sun);

    sunMasolt->eszik(2);
}