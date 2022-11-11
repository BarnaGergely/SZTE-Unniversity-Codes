#include <iostream>
#include <string>
#include <algorithm>

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

public:
    Telepes(const std::string &nev, const std::string &szul_bolygo, const std::string &bolygo, unsigned int ero = 1)
            : nev(
            nev), szul_bolygo(szul_bolygo), bolygo(bolygo), ero(ero) {}

    Telepes() {
        this->nev = "";
        this->szul_bolygo = "";
        this->bolygo = "";
        this->ero = 1;
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


    const bool kivandorlo() {
        if (upperCase(get_bolygo()) == upperCase(get_szul_bolygo())) {
            return 0;
        }
        return 1;
    }

    const std::string dolgozik(std::string munkak) {
        unsigned hatralevoEnergia = get_ero();
        int i = 0;
        for (char munka: munkak) {
            if (hatralevoEnergia <= 0) {
                break;
            } else if (munka == ';') {
                hatralevoEnergia--;
            }
            i++;
        }
        return munkak.substr(i);
    }
};

int main() {
    Telepes telepes;
    telepes.set_ero(2);
    std::cout << telepes.dolgozik("ak;ashjk;15;sdsdf;;") << std::endl;
    return 0;
}
