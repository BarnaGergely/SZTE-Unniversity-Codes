#include <stdio.h>

typedef struct HASZNALAT {
    int kezdet;
    double mertek;
}HASZNALAT;

typedef struct KEVEREK {
    double korido;
    HASZNALAT kopas;
    HASZNALAT elhasznalodas;
}KEVEREK;


typedef struct CSEREK {
    int kor;
     int keverek;
}CSEREK;

typedef struct STRATEGIA {
    KEVEREK keverek[3];
    int korokszama;
    double kiallas;
    CSEREK csere[100];
    int cserekSzama;
}STRATEGIA;

double kerekit(double bemenet){
    double kimenet = 0.0;
    int temp = bemenet*1000.0 + 0.5;
    kimenet = temp;
    kimenet = kimenet/1000.0;


    return kimenet;
}

int beolvas(char forras[100], STRATEGIA *strategia){
    FILE *infile;

    if (!(infile = fopen(forras, "r"))) {
        return 1;
    }

    char gumi;
    for (int i = 0; i < 3; ++i) {
        KEVEREK keverekTemp;
        fscanf(infile, "%c %lf %d %lf %d %lf\n", &gumi, &keverekTemp.korido, &keverekTemp.kopas.kezdet, &keverekTemp.kopas.mertek, &keverekTemp.elhasznalodas.kezdet, &keverekTemp.elhasznalodas.mertek);
        keverekTemp.korido = kerekit(keverekTemp.korido);

        if(gumi == 'A'){
            strategia->keverek[0].korido = keverekTemp.korido;
            strategia->keverek[0].kopas.kezdet = keverekTemp.kopas.kezdet;
            strategia->keverek[0].kopas.mertek = keverekTemp.kopas.mertek;
            strategia->keverek[0].elhasznalodas.kezdet = keverekTemp.elhasznalodas.kezdet;
            strategia->keverek[0].elhasznalodas.mertek = keverekTemp.elhasznalodas.mertek;
        }
        if(gumi == 'B'){
            strategia->keverek[1].korido = keverekTemp.korido;
            strategia->keverek[1].kopas.kezdet = keverekTemp.kopas.kezdet;
            strategia->keverek[1].kopas.mertek = keverekTemp.kopas.mertek;
            strategia->keverek[1].elhasznalodas.kezdet = keverekTemp.elhasznalodas.kezdet;
            strategia->keverek[1].elhasznalodas.mertek = keverekTemp.elhasznalodas.mertek;
        }
        if(gumi == 'C'){
            strategia->keverek[2].korido = keverekTemp.korido;
            strategia->keverek[2].kopas.kezdet = keverekTemp.kopas.kezdet;
            strategia->keverek[2].kopas.mertek = keverekTemp.kopas.mertek;
            strategia->keverek[2].elhasznalodas.kezdet = keverekTemp.elhasznalodas.kezdet;
            strategia->keverek[2].elhasznalodas.mertek = keverekTemp.elhasznalodas.mertek;
        }

    }

    /*
    fscanf(infile, "%c %lf %d %lf %d %lf\n", &gumi, &strategia->keverek[0].korido, &strategia->keverek[0].kopas.kezdet, &strategia->keverek[0].kopas.mertek, &strategia->keverek[0].elhasznalodas.kezdet, &strategia->keverek[0].elhasznalodas.mertek);
    fscanf(infile, "%c %lf %d %lf %d %lf\n", &gumi, &strategia->keverek[1].korido, &strategia->keverek[1].kopas.kezdet, &strategia->keverek[1].kopas.mertek, &strategia->keverek[1].elhasznalodas.kezdet, &strategia->keverek[1].elhasznalodas.mertek);
    fscanf(infile, "%c %lf %d %lf %d %lf\n", &gumi, &strategia->keverek[2].korido, &strategia->keverek[2].kopas.kezdet, &strategia->keverek[2].kopas.mertek, &strategia->keverek[2].elhasznalodas.kezdet, &strategia->keverek[2].elhasznalodas.mertek);
    */

    fscanf(infile, "%d\n", &strategia->korokszama);
    fscanf(infile, "%lf\n", &strategia->kiallas);
    strategia->kiallas = kerekit(strategia->kiallas);


    char temp[20];
    int szamlalo = 0;
    while (fscanf(infile, "%s\n", &temp) == 1){
        int i = 0;
        for (i = 0; temp[i] <= '9'; ++i);

        if (temp[i] == 'A') {
            strategia->csere[szamlalo].keverek = 0;
        }
        if (temp[i] == 'B') {
            strategia->csere[szamlalo].keverek = 1;
        }
        if (temp[i] == 'C') {
            strategia->csere[szamlalo].keverek = 2;
        }

        temp[i] = ' ';
        sscanf(temp, "%d", &strategia->csere[szamlalo].kor );
        //printf("kor: %d\n", strategia->csere[szamlalo].kor);
        //printf("keverek: %d\n", strategia->csere[szamlalo].keverek);

        szamlalo++;
    }
    strategia->cserekSzama = szamlalo;
    //printf("cserekSzama: %d\n", strategia->cserekSzama);

    strategia->csere[strategia->cserekSzama].kor = strategia->korokszama;



    // Hiba kereses: kevesebb mint 1 kiallas
    if (strategia->cserekSzama < 2) {return 1;}

    // Hiba kereses: kevesebb mint 2 keverek
    int keverekekSzama = 1;
    for (int i = 1; (i < strategia->cserekSzama) && (keverekekSzama < 2); ++i) {
        if(strategia->csere[i].keverek != strategia->csere[i-1].keverek) {
            keverekekSzama++;
        }
    }
    if(keverekekSzama < 2) {return 1;}

    fclose(infile);

    return 0;
}


int szovegKiir(char szoveg[100], char file[100]){
    FILE *outfile;

    if (!(outfile = fopen(file, "w"))) {
        fclose(outfile);
        return 1;
    }
    fprintf(outfile, "%s\n", szoveg);
    fclose(outfile);

    return 0;
}

int doubleKiir(double szam, char file[100]){
    FILE *outfile;

    if (!(outfile = fopen("ki.txt", "w"))) {
        fclose(outfile);
        return 1;
    }
    fprintf(outfile, "%.3lf\n", szam);
    fclose(outfile);

    return 0;
}


double menet(int kor, int keverekk, STRATEGIA *strategia){
    double megoldas = 0;
    double kopas = 0;
    double elhasznalodas = 0;
    double korido = strategia->keverek[keverekk].korido;
    for (int i = 1; i <= kor; ++i) {
        if(i > strategia->keverek[keverekk].kopas.kezdet){ korido += strategia->keverek[keverekk].kopas.mertek; }
        if(i > (strategia->keverek[keverekk].elhasznalodas.kezdet + strategia->keverek[keverekk].kopas.kezdet)){elhasznalodas += strategia->keverek[keverekk].elhasznalodas.mertek; }
        korido += elhasznalodas;
        megoldas += korido;
        //printf("%d kor ideje: %lf\n", i, korido);
    }
    return megoldas;
}


int main() {

    STRATEGIA strategia;

    /* ADATBETOLTES */
    if (beolvas("be.txt", &strategia) == 1){
        szovegKiir("HIBA", "ki.txt");
        return 0;
    }

    /* SZAMOLAS */

    double megoldas = 0 - strategia.kiallas;
    for (int i = 0; (i < strategia.cserekSzama); ++i) {

        double korokSzama = 0;
        if (strategia.csere[i].kor >= (strategia.korokszama-1)){

        } else {
            if ((strategia.csere[i + 1].kor >= strategia.korokszama-1) && ((i+1) == (strategia.cserekSzama-1)) ) {
                megoldas += strategia.kiallas;
                korokSzama = strategia.korokszama - strategia.csere[i].kor;
                megoldas += menet(korokSzama, strategia.csere[i].keverek, &strategia);
            } else {
                megoldas += strategia.kiallas;
                korokSzama = strategia.csere[i + 1].kor - strategia.csere[i].kor;
                megoldas += menet(korokSzama, strategia.csere[i].keverek, &strategia);
            }
        }
        //printf("<<OsszIdo : %lf>>\n", megoldas);
    }

    /* ADATKIIRAS */
    doubleKiir(megoldas, "ki.txt");

    return 0;
}
