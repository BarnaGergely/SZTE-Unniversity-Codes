#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct {
    int magassag;
    int szelesseg;
    int max_intenzitas;
    int** matrix;
} Kep;

void beolvasas(Kep* beolvasott, char* honnan) {
    // -> (nyil) ha stuktura pointerunk van, ezzel elerhetjuk az elemeit

    //ez jo lenne, ha nem lenne benne #-es komment
    /*
    char fajbol_jott[1000];
    fscanf(be,"%s",fajbol_jott);
    int szelesseg, magassag;
    fscanf(be,"%d %d",&szelesseg,&magassag);
    int max_intenzitas;
    fscanf(be,"%d",&max_intenzitas);
    while(fscanf(be,"%s",fajbol_jott)>0) {
        printf("%s\n",fajbol_jott);
    }
    */

    //A kommenteket is kezeljuk - ehhez egesz sorokat olvasunk be

    FILE* be = fopen(honnan,"r");
    char fajbol_jott[1000];
    fscanf(be," %99[^\n]",fajbol_jott); //max 99 karakter olvas \n-ig
    while(fajbol_jott[0] == '#') fscanf(be," %99[^\n]",fajbol_jott); //ugorja a kommentet
    //int szelesseg, magassag;
    fscanf(be," %99[^\n]",fajbol_jott);
    while(fajbol_jott[0] == '#') fscanf(be," %99[^\n]",fajbol_jott); //ugorja a kommentet
    //szetbontjuk szamokra ha nem volt komment
    //sscanf - fscanf, de fajl helyett sztringbol
    sscanf(fajbol_jott,"%d %d",&beolvasott->szelesseg,&beolvasott->magassag);
    fscanf(be," %99[^\n]",fajbol_jott);
    while(fajbol_jott[0] == '#') fscanf(be," %99[^\n]",fajbol_jott); //ugorja a kommentet
    //int max_intenzitas;
    sscanf(fajbol_jott,"%d",&beolvasott->max_intenzitas);

    //2d dinamikus tomb - 2-es verzio - a valodi 2d tomb
    int** matrix = malloc(beolvasott->magassag * sizeof(int *));
    for(int i=0;i<beolvasott->magassag;i++) {
        matrix[i] = malloc(beolvasott->szelesseg * sizeof(int));
    }

    //eltaroljuk a konkret ertekeket is 2d tombben
    for(int i=0;i<beolvasott->magassag;i++) {
        for(int j=0;j<beolvasott->szelesseg;j++) {
            fscanf(be," %99[^\n]",fajbol_jott);
            while(fajbol_jott[0] == '#') fscanf(be," %99[^\n]",fajbol_jott); //ugorja a kommentet
            int aktualis_intenzitas = 0;
            sscanf(fajbol_jott,"%d",&aktualis_intenzitas);
            matrix[i][j] = aktualis_intenzitas;
            beolvasott->matrix=matrix;
        }
    }

    /*for(int i=0;i<beolvasott->magassag;i++) {
        for(int j=0;j<beolvasott->szelesseg;j++) {
            printf("%3d ",matrix[i][j]);
        }
        printf("\n");
    }*/

    fclose(be);
}

void kiiras(Kep* kiirando, char* hova) {
    FILE* ki = fopen(hova,"w");

    fprintf(ki,"P2\n");
    //ezt megfordithatjuk ha macsa hatterkepet akarunk generalni
    fprintf(ki,"%d %d\n",kiirando->szelesseg,kiirando->magassag);
    fprintf(ki,"%d\n",kiirando->max_intenzitas);

    for(int i=0;i<kiirando->magassag;i++) {
        for(int j=0;j<kiirando->szelesseg;j++) {
            fprintf(ki,"%d\n",kiirando->matrix[i][j]);
        }
    }

    fclose(ki);
}

int main() {
    char befile[] = "Invertalt.pgm";
    char kifile[] = "Kimenet.pgm";

    //2d dinamikus tomb - 2-es verzio - a valodi 2d tomb
    Kep beolvasott;



    beolvasas(&beolvasott,befile);


    printf("A kep adatai:\n");
    printf("Meret: %dx%d\n",beolvasott.szelesseg,beolvasott.magassag);
    printf("Max intenzitas: %d\n",beolvasott.max_intenzitas);



    kiiras(&beolvasott,kifile);



    for(int i=0;i<beolvasott.magassag;i++) {
        free(beolvasott.matrix[i]);
    }
    free(beolvasott.matrix);


    return 0;
}
