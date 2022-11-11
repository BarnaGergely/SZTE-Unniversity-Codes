#include <iostream>
#include <string>

using namespace std;

struct Etel {
public:
    string nev;
    int hutes;

    Etel(const string &nev, int hutes) : nev(nev), hutes(hutes) {}

    Etel() {
        nev = "";
        hutes = 0;
    }

    string print() const {
        return "nev:" + nev + ",hofok:" + to_string(hutes);
    }
};

class Hiba : std::exception {
public:
    virtual string hiba() const {
        return "Hiba tortent";
    }
};

class Megromlik : public Hiba {
private:
    Etel etel;
public:
    explicit Megromlik(const Etel &etel) : etel(etel) {}

    virtual string hiba() const {
        return "A " + etel.nev + " tarolasahoz legalabb " + to_string(etel.hutes) + " fok kell";
    }
};

class Megtelt : public Hiba {
private:
    string mit;
public:
    explicit Megtelt(const string &mit) : mit(mit) {}

    virtual string hiba() const {
        return "Nem sikerult letarolni a " + mit + "-t";
    }
};

class Hutoegyseg {
    int hofok;
    Etel tartalom[10];
    unsigned aktualis;
public:
    explicit Hutoegyseg(string hofok) {
        try {
            this->hofok = stoi(hofok);
        } catch (const std::exception &error) {
            this->hofok = -1;
        }
        aktualis = 0;
    }

    int get_hofok() const {
        return hofok;
    }

    const Etel *get_tartalom() const {
        return tartalom;
    }

    unsigned int get_aktualis() const {
        return aktualis;
    }

    Hutoegyseg operator+=(const Etel &etel) {
        if (etel.hutes >= this->hofok) {
            if (aktualis < 10) {
                tartalom[aktualis] = etel;
                aktualis++;
            } else {
                throw Megtelt(etel.nev);
            }
        } else {
            throw Megromlik(etel);
        }
        return *this;
    }

    void print() const {
        bool vanElem = false;
        for (int i = 0; i < 10; ++i) {
            if (!tartalom[i].nev.empty() && tartalom[i].hutes != 0) {
                vanElem = true;
                std::cout << tartalom[i].print() << std::endl;
            }
            if (!vanElem) {
                throw Hiba();
            }
        }
    }

};

bool feltolt(Hutoegyseg &h, Etel etelek[], unsigned etelszam) {
    try {
        for (int i = 0; i < etelszam; ++i) {
            h += etelek[i];
        }
        return true;
    } catch (...) {
        return false;
    }
}

int main() {
    /*
    Hutoegyseg huto = Hutoegyseg("8");
    //std::cout << huto.get_hofok() << std::endl;
    Etel etel =Etel("alma", 11);
    huto+=etel;
    huto.print();
     */


    return 0;
}
