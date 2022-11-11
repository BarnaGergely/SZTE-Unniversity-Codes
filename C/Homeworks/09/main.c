#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE *outfile;

int matrix[20][20];
int hanyraleptemra = 0;
int meretY = 0;
int meretX = 0;

int ralep(int x, int y){
    int temp = meretX;
    int temp2 = meretY;
    if((0<=x) && (x<meretX) && (0<=y) && (y<meretY)){
        if(hanyraleptemra!=0) {fprintf(outfile,"%s",","); }
        fprintf(outfile,"%d", matrix[y][x]);
        hanyraleptemra++;
    }
    return 0;
}


int main() {
    FILE *infile = fopen("be.txt", "r");
     outfile = fopen("ki.txt", "w");

    int helyzetX;
    int helyzetY;

    fscanf(infile, "%d,%d\n", &helyzetY, &helyzetX);
    helyzetX--;
    helyzetY--;

    int oszlop = 0;
    int i;

    char temp[99];
    fscanf(infile, " %99[^\n]", &temp);
    for (i = 0; temp[0] != '-' ; ++i) {
        printf("%s\n", temp);
        oszlop = 0;

        int j = 0;
        int elozovesszo = 0;
        char szam[20];
        for (j = 0;  temp[j] != '\000'; ++j) {
            char temp2;

            if(temp[j] == ',') {
                int k;
                for (k = elozovesszo; k < j; ++k) {
                    szam[k-elozovesszo] = temp[k];
                }
                szam[k-elozovesszo] = '\0';
                matrix[i][oszlop] = atoi(szam);
                elozovesszo = j+1;
                oszlop++;
            }
        }
        int k;
        for (k = elozovesszo; k < j; ++k) {
            szam[k-elozovesszo] = temp[k];
        }
        szam[k-elozovesszo] = '\0';
        matrix[i][oszlop] = atoi(szam);
        elozovesszo = j+1;
        oszlop++;

        fscanf(infile, " %99[^\n]", &temp);
    }
    meretX = oszlop;
    meretY = i;

    int itttartokX = helyzetX;
    int itttartokY = helyzetY;
    int irany = 1;
    hanyraleptemra = 0;


    ralep(itttartokX, itttartokY);
    for (int j = 0; hanyraleptemra < meretX*meretY; ++j) {
        for (int i = 0; i <= j/2; ++i) {

            if(irany == 1){
                itttartokY--;
            }
            if(irany == 2){
                itttartokX--;
            }
            if(irany == 3){
                itttartokY++;
            }
            if(irany == 4){
                itttartokX++;
            }
            ralep(itttartokX, itttartokY);

        }
        irany++;
        if(irany==5){ irany=1; }
    }


    fprintf(outfile,"%s","\n");
    fclose(infile);
    fclose(outfile);


    return 0;
}
