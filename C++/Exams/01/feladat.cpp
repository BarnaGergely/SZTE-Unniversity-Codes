#include <iostream>

using namespace std;

class Nyuszi{
private:
    string nev;
    unsigned sebesseg;

public:
    Nyuszi(): nev("Muszi"), sebesseg(90) {}

    Nyuszi(const string &nev, unsigned int sebesseg) : nev(nev), sebesseg(sebesseg) {}

    const string &get_nev() const {
        return nev;
    }

    void set_nev(const string &nev) {
        Nyuszi::nev = nev;
    }

    unsigned int get_sebesseg() const {
        return sebesseg;
    }

    void set_sebesseg(const unsigned int sebesseg) {
        Nyuszi::sebesseg = sebesseg;
    }

    Nyuszi& operator++(){
        sebesseg++;
        return *this;
    }

    Nyuszi operator++(int){
        Nyuszi uj = *this;
        operator++();
        return uj;
    }
};

class Repa{
protected:
    float meret;
    Nyuszi nyuszik[5];
    unsigned nyusziszam;

public:
    explicit Repa(float meret) : meret((meret>10)?meret:10), nyusziszam(0) {}
    Repa() = default;

    float get_meret() const {
        return meret;
    }

    const Nyuszi *get_nyuszik() const {
        return nyuszik;
    }

    unsigned int get_nyusziszam() const {
        return nyusziszam;
    }

    Repa& operator+=(const Nyuszi nyuszi){
        if (nyusziszam<5){
            for (int i = 0; i < nyusziszam; ++i) {
                if (nyuszik[i].get_nev()==nyuszi.get_nev()) {
                    return *this;
                }
            }
            nyuszik[nyusziszam] = nyuszi;
            nyusziszam++;
        } else {
            cout << "A repa mar fogyasztas alatt all." << endl;
        }
        return *this;
    }

    operator string() const{
        int topNyuszi = 0;
        if (nyusziszam!=0) {
            unsigned maxsebesseg = 0;
            for (int i = 0; i < nyusziszam; ++i) {
                if (nyuszik[i].get_sebesseg() > maxsebesseg) {
                    maxsebesseg = nyuszik[i].get_sebesseg() ;
                }
            }
            if (maxsebesseg == 0) {
                return "";
            } else {
                for (int i = 0; i < nyusziszam; ++i) {
                    if (nyuszik[i].get_sebesseg() > nyuszik[topNyuszi].get_sebesseg()) {
                        topNyuszi = i;
                    }
                }
                return nyuszik[topNyuszi].get_nev();
            }
        } else {
            return "";
        }
    }
};

