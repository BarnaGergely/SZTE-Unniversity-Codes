#include <stdio.h>
#include <stdlib.h>

typedef struct MERET{
    int x;
    int y;
}MERET;

typedef struct ADAT{
    MERET meret;
    int* matrix;
}ADAT;

ADAT beolvas(char forras[100]){

    FILE *infile;
    if (!(infile = fopen(forras, "r"))) {
        printf("hiba");
    }
    ADAT adat;
    adat.meret.x = 0;
    adat.meret.y = 0;
    fscanf(infile, "%d %d", &adat.meret.y, &adat.meret.x);

    //printf("X: %d Y:%d\n", adat.meret.x, adat.meret.y);

    adat.matrix = (int*) malloc(adat.meret.x*adat.meret.y* sizeof(int));

    for (int i = 0; i < adat.meret.x*adat.meret.y; ++i) {
        fscanf(infile, "%d", &adat.matrix[i]);
    }
    fclose(infile);

    return adat;
}

int kiiras(char hely[100], int kiiarndo){
    FILE *outfile;
    if(!(outfile = fopen(hely, "w"))){
        return -1;
    }

    fprintf(outfile, "%d\n", kiiarndo);

    fclose(outfile);
}

int main() {

    /*  Beolvasas  */
    ADAT adat = beolvas("be.txt");

    /*
    for (int i = 0; i < adat.meret.y*adat.meret.x; ++i) {

        printf("%d ", adat.matrix[i]);
    }
     */

    /* SZAMOLAS */

    int nyeregpontindex = INT_MIN;

    // Maximalis a soraban es Minimalis az oszlopaban

    for (int i = 0; i < adat.meret.y; ++i) {
        // megkesessuk a maximum erteket
        int maximumErtek = INT_MIN;
        int minimumErtek = INT_MAX;
        for (int j = 0; j < adat.meret.x; ++j) {
            if(maximumErtek<adat.matrix[i*adat.meret.x+j]) {
                maximumErtek = adat.matrix[i*adat.meret.x+j];
            }
        }
        printf("\nMaximum reteke az %d sorban: %d\n", i, maximumErtek);

        // megkeressuk a maximum oszlopat
        for (int j = 0; j < adat.meret.x; ++j) {
            if(maximumErtek == adat.matrix[i*adat.meret.x+j]) {
                printf("Maximum helye: %d, %d\n",j, i);

                // megkeressuk a maximumok oszlopaban a minimumok erteket
                minimumErtek = INT_MAX;
                for (int k = 0; k < adat.meret.y; ++k) {
                    if(minimumErtek > adat.matrix[k*adat.meret.x+j]) {
                        minimumErtek = adat.matrix[k*adat.meret.x+j];
                    }
                }
                printf("Minimum ereteke az %d oszlopban: %d\n", j, minimumErtek);

                // megkeressuk a minimumok helyet
                printf("Min ertek kereso:\n");
                for (int k = 0; k < adat.meret.y; ++k) {
                    printf("  %d == %d\n", minimumErtek, adat.matrix[k*adat.meret.x+j]);
                    if(minimumErtek == adat.matrix[k*adat.meret.x+j]) {
                        printf("    Minimum helye: %d, %d\n",j, k);

                        // ha a maximum es a minimum helye megegyezik, megvan a nyeregpont
                        printf("    %d == %d\n", k*adat.meret.x+j, i*adat.meret.x+j);
                        if(k*adat.meret.x+j == i*adat.meret.x+j){
                            nyeregpontindex = k*adat.meret.x+j;


                            printf("\nNyeregpont: %d\n", nyeregpontindex);


                            /* KIIRAS */

                            if(!kiiras("ki.txt", nyeregpontindex)){
                                return -1;
                            }

                            for (int l = 0; l < adat.meret.y; ++l) {
                                free(adat.matrix[l]);
                            }
                            free(adat.matrix);

                            return 0;
                        }
                    }
                }
            }
        }
    }

    for (int l = 0; l < adat.meret.y; ++l) {
        free(adat.matrix[l]);
    }
    free(adat.matrix);
    return -1;
}
