#include <stdio.h>
#include "stdlib.h"

typedef struct MERET{
    int x;
    int y;
}MERET;

int betoltoMeret(char forras[100], MERET *meret){
    FILE *infile;

    if (!(infile = fopen(forras, "r"))) {
        return 1;
    }

    int alma;

    fscanf(infile,"%d %d", &meret->x, &meret->y);

    fclose(infile);
    return 0;
}

int betoltoMeteor(char forras[100], int **meteorok, MERET *meret){
    FILE *infile;

    if (!(infile = fopen(forras, "r"))) {
        return 1;
    }
    
    int temp;
    fscanf(infile,"%d %d", &temp, &temp);

    for (int i = 0; i < meret->x; ++i) {
        for (int j = 0; j < meret->y; ++j) {
            fscanf(infile, "%d", &meteorok[i][j]);
            printf("%d ", meteorok[i][j]);
        }
        printf("\n");
    }
    
    fclose(infile);
    return 0;
}

int kiiro(int szam, char file[100]){
    FILE *outfile;

    if (!(outfile = fopen(file, "w"))) {
        fclose(outfile);
        return 1;
    }
    fprintf(outfile, "%d\n", szam);
    fclose(outfile);

    return 0;
}

int robot(int *helyzet, int *sor, MERET *meret){
    int megsemmisitettMeteor = 0;

    int meteorokElotte = 0;
    int meteorokUtana = 0;
    int meteorFelette = 0;
    int megtudsemmisiteni = 0;

    for (int i = 0; i < meret->y; ++i) {

        /*
         * Meg tud semmisiteni meteort
         * 1 - megsemmisit
         * 2 - nem csinal semmit
         *
         * Nem tud megsemmisiteni - arra lep amerre tobb van
         * 0 vagy egyenlő - marad egyhelyben
         */

        // Meg tud semmisiteni
        if ((*helyzet-i) >= -1 && (*helyzet-i) <= 1){

            // Felette, meg tud semmisiteni
            if ((*helyzet-i) == 0) {
                if(sor[i] == 1) {
                    meteorFelette++;
                    megtudsemmisiteni++;
                }
            }

            // Elotte, meg tud semmisiteni
            if ((*helyzet-i) == 1) {
                if(sor[i] == 1) {
                    meteorokElotte++;
                    megtudsemmisiteni++;
                }
            }

            // Utana, meg tud semmisiteni
            if ((*helyzet-i) == -1) {
                if(sor[i] == 1) {
                    meteorokUtana++;
                    megtudsemmisiteni++;
                }
            }
        }

        // Nem tud megsemmisiteni
        if (((*helyzet-i) > 1) && ((*helyzet-i) < -1)) {

            if ((*helyzet-i) > 1) {
                if(sor[i] == 1) {
                    meteorokElotte++;
                }
            }

            if ((*helyzet-i) < -1) {
                if(sor[i] == 1) {
                    meteorokUtana++;
                }
            }
        }
    }

    // Meg tud semmisiteni
    if(megtudsemmisiteni > 0) {

        // Tobbet is meg tud semmisiteni
        if (megtudsemmisiteni >= 2) {
            if (meteorokUtana == 1 && meteorokElotte == 1 && megtudsemmisiteni == 2) {
                return 0;
            } else {
                return 1;
            }
        } else {

            // Felete
            if(meteorFelette == 1){
                return 1;
            }

            // Elotte
            if(meteorokElotte == 1){
                *helyzet--;
                return 1;
            }

            // Utana
            if(meteorokUtana == 1){
                *helyzet++;
                return 1;
            }

        }

    } else { // Nem tud megsemmisiteni
        if (meteorokElotte > meteorokUtana ){
            *helyzet--;
            return 0;
        }
        if (meteorokElotte < meteorokUtana ){
            *helyzet++;
            return 0;
        }
        if (meteorokElotte == meteorokUtana ){
            return 0;
        }

    }

    /*
    printf("\n");
    for (int i = 0; i < meret->y; ++i) {
        printf("%d ", sor[i]);
    }
    printf("\n");
    */

    return 0;
}


int main() {

    /* BETOLTES */

    // Meretek betoltese
    MERET meret;

    if (betoltoMeret("be.txt", &meret) == 1){
        return 1;
    }

    // Meteorok betoltese
    int** meteorok = (int**) malloc(meret.x * sizeof(int*));
    for (int i = 0; i < meret.x; ++i) {
        meteorok[i] = (int*) malloc(meret.y * sizeof(int));
    }
    
    if (betoltoMeteor("be.txt", &meteorok[0], &meret) == 1){
        return 1;
    }
    
    /* SZAMOLAS */
    int megsemmisitettMeteor = 0;
    int osszesMeteor = 0;
    int  helyzet = 0;

    // Kezdeti helyzet
    for (int i = 0; i < meret.y; ++i) {
        if(meteorok[0][i] == 1){
            helyzet = i;
            megsemmisitettMeteor ++;
            break;
        }
    }

    for (int i = 1; i < meret.x; ++i) {
        megsemmisitettMeteor += robot(&helyzet, meteorok[i], &meret);
        printf("%d", helyzet);
    }

    for (int i = 0; i < meret.x; ++i) {
        for (int j = 0; j < meret.y; ++j) {
            if(meteorok[i][j] == 1){ osszesMeteor++; }
        }
    }

    int atjutottMeteor = osszesMeteor - megsemmisitettMeteor;
    printf("\n%d = %d - %d\n", atjutottMeteor, osszesMeteor, megsemmisitettMeteor);



    
    /* KIIRAS */
    if (kiiro( atjutottMeteor, "ki.txt") == 1){
        return 1;
    }


    /* Valtozo felszabaditas */
    for (int i = 0; i < meret.x ; ++i) {
        free(meteorok[i]);
    }
    free(meteorok);
    
    return 0;
}
