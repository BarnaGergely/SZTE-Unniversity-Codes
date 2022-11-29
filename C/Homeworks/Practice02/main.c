#include <stdio.h>
#include <stdlib.h>

int hanyvarosbollathato(int varos, int latotavolsag, int varosokszama, int* tavolsagokkozottuk){
    int lathatovarosok = 1;

    if(varos == 0){
        int tavolsag = 0;
        for (int i = 0; tavolsag <= latotavolsag; ++i) {
            tavolsag += tavolsagokkozottuk[i];

            if (tavolsag <= latotavolsag ){
                lathatovarosok++;
            }
        }
    } else {
        if (varos == (varosokszama-1)){
            int tavolsag = 0;
            for (int i = (varosokszama-2); tavolsag <= latotavolsag; --i) {
                tavolsag += tavolsagokkozottuk[i];
                if ( tavolsag <= latotavolsag ){
                    lathatovarosok++;
                }
            }
        } else {
            int tavolsag = 0;
            for (int i = 0; (tavolsag <= latotavolsag) && (varos + i != varosokszama-1); ++i) {
                int temp3 = varos+i;
                int temp2 = tavolsagokkozottuk[varos + i];
                tavolsag += temp2;
                if ( tavolsag <= latotavolsag ){
                    lathatovarosok++;
                }
            }
            tavolsag = 0;
            for (int i = 1; (tavolsag <= latotavolsag) && (varos - i != -1); ++i) {
                int temp3 = varos-i;
                int temp2 = tavolsagokkozottuk[varos - i];
                tavolsag += temp2;
                if ( tavolsag <= latotavolsag ){
                    lathatovarosok++;
                }
            }
        }
    }
    return lathatovarosok;
}

int main() {
    int latotavolsag;
    int varosokszama;

    FILE *infile;
    infile = fopen("be.txt", "r");
    fscanf(infile, "%d\n%d", &latotavolsag, &varosokszama);


    int* tavolsagokkozottuk = (int*) malloc((varosokszama-1)* sizeof(int));

    int temp;
    for (int i = 0; (fscanf(infile, "%d\n", &temp)) != -1; ++i) {
        tavolsagokkozottuk[i] = temp;
    }
    fclose(infile);

    temp = 0;
    int* varosok = (int*) malloc((varosokszama-1)* sizeof(int));
    int legnagyobbvaros = INT_MIN;
    int legnagyobbvarosindex = -1;

    for (int i = 0; i < (varosokszama-1); ++i) {
        varosok[i] = hanyvarosbollathato(i, latotavolsag, varosokszama, tavolsagokkozottuk);
        printf("%d\n", varosok[i]);
        if (legnagyobbvaros<varosok[i]){
            legnagyobbvaros = varosok[i];
            legnagyobbvarosindex = i;
        }
    }


    FILE *outfile;
    outfile = fopen("ki.txt", "w");

    fprintf(outfile, "%d", legnagyobbvarosindex);

    fclose(outfile);

    return 0;
}
