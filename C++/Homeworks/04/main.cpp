#include <iostream>

using namespace std;

class MediaTartalom{
protected:
    string nev;
    string utvonal;
public:
    MediaTartalom(const string &nev, const string &utvonal = "") : nev(nev), utvonal(utvonal) {}

    const string &get_Nev() const {
        return nev;
    }

    const virtual double &get_meret() const =0;

    const string &fajl_utvonal() const {
        string s3;
        s3 = nev + "/" + utvonal;

        return s3;
    }

    void operator/=(const string szoveg){
        utvonal= utvonal + "/" + szoveg;
    }
    const bool hibas() const{
        return (this->nev=="" && this->utvonal =="");
    }
};

int main() {
    cout << "alma" << endl;
    return 0;
}
