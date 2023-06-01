#include <iostream>
#include <string>

using namespace std;

//===================Tel======================
class Tel {
    unsigned homerseklet;
    bool meleg;
    unsigned szamlalo = 0;
public:
    Tel(unsigned homerseklet): homerseklet(homerseklet), meleg(false) {
    }

    unsigned get_homerseklet() const {
        return homerseklet;
    }
};

//====================Apo======================
class Apo {
    string nev;
public:
    Apo(string nev) : nev(nev) {}

    ~Apo() {}
};

//===================TelApo====================
class TelApo {
    unsigned ajandekok;

public:
    unsigned get_ajandekok() {
        return ajandekok;
    }

    // konstruktorok
};

//==============Tobbi megoldas=================