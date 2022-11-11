#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

std::string upperCase(std::string data) {
    std::for_each(data.begin(), data.end(), [](char &car) {
        car = ::toupper(car);
    });
    return data;
}

class Telepes {
private:
    std::string nev;
    std::string szul_bolygo;
    std::string bolygo;
    unsigned ero;
    bool vegan = false;

public:
    Telepes(const std::string &nev, const std::string &szul_bolygo, const std::string &bolygo, unsigned int ero = 1)
            : nev(
            nev), szul_bolygo(szul_bolygo), bolygo(bolygo), ero(ero) {}

    Telepes() {
        this->nev = "";
        this->szul_bolygo = "";
        this->bolygo = "";
        this->ero = 1;
        this->vegan = false;
    }

    Telepes(const std::string &nev, const std::string &bolygo, unsigned int ero = 1) : nev(nev), bolygo(bolygo),
                                                                                       szul_bolygo(bolygo), ero(ero) {}

    const std::string &get_nev() const {
        return nev;
    }

    const std::string &get_szul_bolygo() const {
        return szul_bolygo;
    }

    const std::string &get_bolygo() const {
        return bolygo;
    }

    unsigned int get_ero() const {
        return ero;
    }

    void set_nev(const std::string &nev) {
        Telepes::nev = nev;
    }

    void set_szul_bolygo(const std::string &szul_bolygo) {
        Telepes::szul_bolygo = szul_bolygo;
    }

    void set_bolygo(const std::string &bolygo) {
        Telepes::bolygo = bolygo;
    }

    void set_ero(unsigned int ero) {
        Telepes::ero = ero;
    }

    bool is_vegan() const {
        return vegan;
    }

    void set_vegan(bool vegan) {
        Telepes::vegan = vegan;
    }

    bool operator==(const Telepes t) const {

        return this->nev == t.nev && this->ero == t.ero && upperCase(this->bolygo) == upperCase(t.bolygo) &&
               upperCase(this->szul_bolygo) == upperCase(t.szul_bolygo) && this->vegan == t.vegan;
    }
};

class Kolonia {
private:
    string nev;
    string bolygo;
    unsigned letszam;
    Telepes lakok[25];
public:
    Kolonia(const string &nev, const string &bolygo) : nev(nev), bolygo(bolygo) {
        this->letszam = 0;
    }

    Kolonia() {
        this->nev = "";
        this->bolygo = "";
        this->letszam = 0;
    }

    const string &get_nev() const {
        return nev;
    }

    const string &get_bolygo() const {
        return bolygo;
    }

    unsigned int get_letszam() const {
        return letszam;
    }

    const Telepes *get_lakok() const {
        return lakok;
    }

    void set_nev(const string &nev) {
        Kolonia::nev = nev;
    }

    void set_bolygo(const string &bolygo) {
        Kolonia::bolygo = bolygo;
    }

    const Kolonia &operator+=(const Telepes t) {
        Telepes telepules2 = t;

        if (this->letszam >= 24) {
            cout << "A kolonia megtelt" << endl;
            return *this;
        } else {
            bool vegane = ~*this;
            if (!t.is_vegan() && vegane) {
                cout << "A kolonia vegan" << endl;
                return *this;
            } else {
                telepules2.set_bolygo(bolygo);
                lakok[letszam] = telepules2;
                letszam++;
                return *this;
            }
        }
    }

    bool operator~() const {
        bool vegakKolonia = false;
        unsigned veganLakok = 0;
        for (const Telepes &lako: this->lakok) {
            if (lako.is_vegan()) veganLakok++;
        }
        if ((float) veganLakok > ((float) this->letszam / (float) 2)) {
            vegakKolonia = true;
        }
        return vegakKolonia;
    }


    const Kolonia &operator+=(const Kolonia kolonia) const {
        return *this;
    }

    Kolonia operator-(const Telepes t) const {
        Kolonia ujKolonia = *this;

        bool vanTalalat = false;
        for (int i = 0; i < sizeof(lakok); ++i) {
            if (lakok[i] == t && !vanTalalat) {
                vanTalalat = true;
                ujKolonia.letszam--;
            }
            if (vanTalalat && i < ujKolonia.letszam) {
                ujKolonia.lakok[i] = this->lakok[i + 1];
            }
        }
        return ujKolonia;
    }
};

int main() {
    return 0;
}
