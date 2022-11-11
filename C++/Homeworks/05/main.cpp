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

    void disz_felhelyezese(const VilagitoDisz disz) {
        delete this->csucs_disz;
        try {
            this->csucs_disz = new VilagitoDisz(disz);
        } catch (bad_alloc &error) {
            cerr << "Nem sikerult a disz_felhejezese memoria foglalas" << endl;
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

    // dimanikus másolás
    KisKaracsonyfa(const KisKaracsonyfa &kisfa) : fa_tipus(kisfa.fa_tipus), csucs_disz(new VilagitoDisz) {
        *csucs_disz = *(kisfa.csucs_disz); // az érték másolása az új memória területre
    }

    // Assignment operator (=)
    KisKaracsonyfa &operator=(const KisKaracsonyfa &kisfa) {
        delete csucs_disz;
        csucs_disz = new VilagitoDisz;
        *csucs_disz = *(kisfa.csucs_disz);
        return *this;
    }

    // Desktruktor
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
        unsigned osszFenyesseg = 0;
        for (int i = 0; i < act; ++i) {
            osszFenyesseg += diszek[i].get_fenyesseg();
        }
        return osszFenyesseg;
    }

    // dimanikus másolás
    NagyKaracsonyfa(const NagyKaracsonyfa &nagyfa) : diszek_szama(nagyfa.diszek_szama), act(nagyfa.act),
                                                    diszek(new VilagitoDisz[nagyfa.diszek_szama]) {
        *diszek = *(nagyfa.diszek); // az érték másolása az új memória területre
    }

    // Assignment operator (=)
    NagyKaracsonyfa &operator=(const NagyKaracsonyfa &nagyfa) {
        delete[] diszek;
        diszek = new VilagitoDisz[nagyfa.diszek_szama];
        *diszek = *(nagyfa.diszek);
        return *this;
    }

    virtual ~NagyKaracsonyfa() {
        delete[] diszek;
    }
};

int main() {
    std::cout << "Hello, World!" << std::endl;
    KisKaracsonyfa alma = KisKaracsonyfa("fenyo");
    VilagitoDisz disz = VilagitoDisz(3);
    disz.operator++();

    alma.disz_felhelyezese(disz);
    return 0;
}
