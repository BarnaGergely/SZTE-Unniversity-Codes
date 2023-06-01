#include <iostream>
#include <string>

//===========================Jarmu================
class Jarmu {
protected:
    std::string nev;
public:
    Jarmu( const std::string& nev ) : nev(nev) {}

    virtual void leszed(int szam) = 0;

    virtual ~Jarmu() {}
};


//===========================Motor===============
class Disz {
private:
    std::string nev;
    unsigned meret;
public:
    Disz( const std::string& nev, unsigned meret ) : nev(nev), meret(meret) {}
    Disz() = default;
    bool operator==(const Disz& masik) const{
        return this->nev == masik.nev && this->meret == masik.meret;
    }

    unsigned int getMeret() const {
        return meret;
    }

    virtual ~Disz() {}
};

//=====================Uj megoldasok==============
class Motor: public Jarmu{
    Disz *diszek;
    unsigned tombKapacitas;
    unsigned tombOldalMeret;
    unsigned szabadOldal = 0;

public:
    Motor(const std::string &nev, unsigned tombKapacitas, unsigned tombOldalMeret) : Jarmu(nev), tombKapacitas(tombKapacitas), tombOldalMeret(tombOldalMeret) {
        diszek = new Disz[tombKapacitas];
    }

    void leszed(int szam) override{
        if (tombKapacitas>=szam) {
            Disz tempdiszek[tombKapacitas+2];
            for (int i = 0; i < tombKapacitas; ++i) {
                tempdiszek[i] = diszek[i];
            }

            tombKapacitas+=2;
            delete[] diszek;
            diszek = new Disz[tombKapacitas];
            for (int i = 0; i < tombKapacitas; ++i) {
                 diszek[i] = tempdiszek[i];
            }
        }

        if(szam>tombKapacitas) {
            szam = tombKapacitas;
        }

        for (int i = szam; i >= 0; --i) {
            tombOldalMeret += diszek[i].getMeret();
            szabadOldal--;
        }
    }

    Motor& operator+=(const Disz& disz) {
        for (int i = 0; i < tombKapacitas; ++i) {
            if (disz == diszek[i]) {
                return *this;
            }
        }
        if (tombKapacitas == szabadOldal) {
            leszed(tombKapacitas);
            diszek[0] = disz;
            szabadOldal++;
        } else if (tombKapacitas>szabadOldal){
            diszek[szabadOldal] = disz;
            szabadOldal++;
        }
        return *this;
    }

    Motor(const Motor& m) : Jarmu(m.nev), tombKapacitas(m.tombKapacitas),
                                      tombOldalMeret(m.tombOldalMeret), szabadOldal(m.szabadOldal), diszek(new Disz[tombKapacitas]) {
        for (int i = 0; i < tombKapacitas; ++i) {
            diszek[i] = m.diszek[i];
        }
    }

    Motor& operator=(const Motor& m) {
        if (this == &m) {
            return *this;
        }
        tombKapacitas = m.tombKapacitas;
        nev = m.nev;
        tombOldalMeret = m.tombOldalMeret;
        szabadOldal = m.szabadOldal;

        delete[] diszek;
        diszek = new Disz[tombKapacitas];
        for (int i = 0; i < tombKapacitas; ++i) {
            diszek[i] = m.diszek[i];
        }

    }

    virtual ~Motor() {
        delete[] diszek;
    }
};



int main() {
    Disz disz();
    Motor motor();
    std::cout << "Hello, World!" << std::endl;
    return 0;
}
