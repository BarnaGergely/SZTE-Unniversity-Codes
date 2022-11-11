#include <iostream>

using namespace std;

class VilagitoDisz {
private:
    unsigned fenyesseg;
    bool bekapcsolva;

public:
    unsigned int get_fenyesseg() const {
        if (bekapcsolva) {
            return fenyesseg;
        } else {
            return 0;
        }
    }

    bool is_bekapcsolva() const {
        return bekapcsolva;
    }

    explicit VilagitoDisz(unsigned int fenyesseg = 0) : fenyesseg(fenyesseg) {}

    VilagitoDisz &operator++() {
        bekapcsolva = true;
        return *this;
    }

    VilagitoDisz &operator--() {
        bekapcsolva = false;
        return *this;
    }
};

class KisKaracsonyfa {
private:
    VilagitoDisz *csucs_disz;
    std::string fa_tipus;

public:
    explicit KisKaracsonyfa(const string &faTipus = "luc") : fa_tipus(faTipus) {
        csucs_disz = nullptr;
    }

    VilagitoDisz *get_csucs_disz() const {
        return csucs_disz;
    }

    const string &get_fa_tipus() const {
        return fa_tipus;
    }

    void disz_felhelyezese(const VilagitoDisz dolog) {
        delete this->csucs_disz;
        try {
            this->csucs_disz = new VilagitoDisz(dolog);
        } catch (bad_alloc &error) {
            cerr << "Nem sikerult a memoria foglalas" << endl;
            return;
        }
    }

    void bekapcsol() {
        if (csucs_disz != nullptr) {
            csucs_disz->operator++();
        }
    }

    void kikapcsol() {
        if (csucs_disz != nullptr) {
            csucs_disz->operator--();
        }
    }

    unsigned get_fenyesseg() {
        if (csucs_disz != nullptr) {
            return csucs_disz->get_fenyesseg();
        } else {
            return 0;
        }
    }


    KisKaracsonyfa(const KisKaracsonyfa &fa) : fa_tipus(fa.fa_tipus), csucs_disz(new VilagitoDisz) {
        *csucs_disz = *(fa.csucs_disz);
    }

    KisKaracsonyfa &operator=(const KisKaracsonyfa &fa) {
        delete csucs_disz;
        csucs_disz = new VilagitoDisz;
        *csucs_disz = *(fa.csucs_disz);
        return *this;
    }


    virtual ~KisKaracsonyfa() {
        delete csucs_disz;
    }
};

class NagyKaracsonyfa {
private:
    VilagitoDisz *diszek;
    unsigned diszek_szama;
    unsigned act;

public:
    NagyKaracsonyfa(unsigned int diszekSzama) : diszek(new VilagitoDisz[diszekSzama]),
                                                diszek_szama(diszekSzama), act(0) {}

    void disz_felhelyezese(const VilagitoDisz &disz) {
        if (act <= diszek_szama - 1) {
            diszek[act] = disz;
            act++;
        }
    }

    void bekapcsol() {
        for (int i = 0; i < act; ++i) {
            diszek[i].operator++();
        }
    }

    void kikapcsol() {
        for (int i = 0; i < act; ++i) {
            diszek[i].operator--();
        }
    }

    unsigned get_fenyesseg() {
        unsigned ossz = 0;
        for (int i = 0; i < act; ++i) {
            ossz += diszek[i].get_fenyesseg();
        }
        return ossz;
    }


    NagyKaracsonyfa(const NagyKaracsonyfa &fa) : diszek_szama(fa.diszek_szama), act(fa.act),
                                                     diszek(new VilagitoDisz[fa.diszek_szama]) {
        *diszek = *(fa.diszek); // az érték másolása az új memória területre
    }


    NagyKaracsonyfa &operator=(const NagyKaracsonyfa &fa) {
        delete[] diszek;
        diszek = new VilagitoDisz[fa.diszek_szama];
        *diszek = *(fa.diszek);
        return *this;
    }

    virtual ~NagyKaracsonyfa() {
        delete[] diszek;
    }
};
