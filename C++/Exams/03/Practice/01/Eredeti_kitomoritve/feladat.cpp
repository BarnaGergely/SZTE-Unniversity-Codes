#include <iostream>
#include <string>
#include "exception"
#include "vector"
#include "map"
#include "set"
#include "algorithm"

using namespace std;

// Amelyik reszt szeretned tesztelni, az ne legyen komment
#ifndef TEST_BIRO
  #define HIBA_ES_OSZTALYT_KERES
//   #define ATLAGTALANIT
//   #define SZANT_KESZIT
//   #define EGYEDIEKET_RENDEZ
#endif

#ifdef HIBA_ES_OSZTALYT_KERES
// 1. Feladat

// Hiba osztaly helye //

// 2. Feladat
bool osztalytKeres(map<string, unsigned> osztalyok, const string& keresettOsztaly) {
    // megoldas
    return false; // ezt a sort modosithatod
}
#endif

#ifdef ATLAGTALANIT
// 3. Feladat
void atlagtalanit(vector<int>& vektor){
    // megoldas
}
#endif

// 4. Feladat
#ifdef SZANT_KESZIT
struct Renszarvas {
    unsigned sebesseg;
    Renszarvas(unsigned sebesseg = 0): sebesseg(sebesseg) {}
    // megoldas
};

vector<Renszarvas> szantKeszit(unsigned db, unsigned sebesseg) {
    // megoldas
    return vector<Renszarvas>(); // ezt a sort modosithatod
}
#endif

#ifdef EGYEDIEKET_RENDEZ
// 5. Feladat
vector<int> egyedieketRendez(int* tomb, unsigned tombHossza){
    // megoldas
    return vector<int>(); // ezt a sort modosithatod
}
#endif