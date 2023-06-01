#include <iostream>
#include <string>

using namespace std;

//===========================Jarmu================
class Jarmu {
protected:
    string nev;
public:
    Jarmu( const string& nev ) : nev(nev) {}

    virtual void leszed(int db) = 0;

    virtual ~Jarmu(){}
};


//===========================Motor===============
class Disz {
private:
    string nev;
    unsigned meret;
public:
    Disz( const string& nev, unsigned meret ) : nev(nev), meret(meret) {}
    Disz() = default;
    bool operator==(const Disz& masik) const{
        return this->nev == masik.nev && this->meret == masik.meret;
    }

    unsigned int getMeret() const {
        return meret;
    }
};

//=====================Uj megoldasok==============

class Motor: public Jarmu{
    Disz* diszek;
    unsigned motor_szabadresz;
    unsigned max_disz;
    unsigned motor_oldal;
    unsigned aktualis_disz = 0;

public:
    Motor(const string &nev, unsigned max_disz, unsigned motor_oldal) : Jarmu(nev), max_disz(max_disz), diszek(new Disz[max_disz]), motor_oldal(motor_oldal) {}

    Motor& operator+=(const Disz& disz){
        if(aktualis_disz < max_disz){
            diszek[aktualis_disz++] = disz;
            if(diszek[aktualis_disz] == disz) {
                return *this;
            }
        } else {
            leszed(aktualis_disz);
        }
        return *this;
    }

    void leszed(int db){
        unsigned levett = 0;

        if(db > aktualis_disz){
            db = aktualis_disz;
        }

        for(int i = aktualis_disz-1; i >= 0 && i >= aktualis_disz-db; i--){
            levett += diszek[i].getMeret();
        }
        motor_szabadresz += levett;

        if(db >= max_disz){
            Disz* seged = new Disz[max_disz+2];
            for(int i = 0; i < aktualis_disz; i++){
                seged[i] = diszek[i];
            }
            delete[] diszek;
            diszek = seged;
            motor_szabadresz = 0;
        }
    }

    Motor(const Motor &masik) : Jarmu(masik.nev), max_disz(masik.max_disz), diszek(new Disz[max_disz]), motor_oldal(masik.motor_oldal), aktualis_disz(masik.aktualis_disz){
        for(unsigned i = 0; i < aktualis_disz; i++){
            diszek[i] = masik.diszek[i];
        }
    }

    Motor& operator=(const Motor &masik){
        if(this == &masik) {
            return *this;
        }
        delete[] diszek;
        nev = masik.nev;
        max_disz = masik.max_disz;
        diszek = new Disz[max_disz];
        motor_oldal = masik.motor_oldal;
        aktualis_disz = masik.aktualis_disz;
        for(unsigned i = 0; i < aktualis_disz; i++){
            diszek[i] = masik.diszek[i];
        }
        return *this;
    }


    virtual ~Motor(){
        delete[] diszek;
    }
};