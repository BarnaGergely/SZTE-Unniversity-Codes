/***********************************************************************
* A PROGRAMBAN NEM SZEREPELHETNEK AZ ALÁBBI SOROK:
* #include <string.h>
* #include <math.h>
***********************************************************************/
#include <stdio.h>
#include <stdlib.h>

/***********************************************************************
************************************************************************
**		ETTŐL A PONTTÓL DOLGOZHATSZ A FELADATOKON
************************************************************************
***********************************************************************/

/*
1. feladat (5 pont)

Az ESA csillagaszai kulonos radiojelet fogtak a vilagurbol. A jel viszont
egy ismert termeszetes radioforras, egy neutroncsillag iranyabol erkezik,
igy a ket jel osszekeveredett. Szerencsere a csillagot regota figyelik, igy
pontosan tudjak, hogy milyen periodikus jelet bocsajt ki. A csillagaszoknak
szet kellene valasztani neutroncsillag ismert periodikus jelet az ismeretlen
jeltol.

A fogott jelet csakugy, mint a neutroncsillag jelet a csillagaszok
digitalizalo gepei nemnegativ egesz szamok sorozatava alakitjak. A ket
sorozat kulonbsege lesz a kulonos radiojel digitalizalt valtozata.

Irj fuggvenyt, amely megtisztitja a zajos jelet a neutroncsillag ismert
jeletol. A fuggveny harom tombot kap parameterkent. Az elso ketto a zajos
jel es a csillag radiojele. Mindketto nemnegativ szamok sorozata, amelyet a
-1 ertek zar (ami viszont mar nem resze a sorozatnak, csak lezarja azt). A
zajos jel nem rovidebb, mint a csillag jele. Utobbi csak egy periodust
tartalmaz a csillag folyamatosan ismetlodo radiojelebol. A fuggveny harmadik
parametere szinten egy tomb, amelybe a tiszta radiojelet kell eloallitani
ugy, hogy a zajos tomb adott elemebol kivonjuk a csillag tomb azonos
sorszamu elemet. (Ha a csillag tomb kozben "elfogy", akkor az elejetol
kezdve ujra felhasznaljuk az elemeit, ahanyszor csak szukseges.) A tiszta
jel szinten csak nemnegativ erekeket fog tartalmazni (ezt nem kell kulon
ellenorizni), es a tiszta tombot is a -1 erekkel kell lezarni. (A hossza
ugyanaz lesz, mint a zajos tombe.)

Kodold le a fuggvenyt C nyelven! A fuggveny fejlecen ne valtoztass! A
fuggveny inputjai es outputja a parameterek. A fuggveny nem vegez IO
muveleteket!
*/

void jeltisztitas(int zajos[], int csillag[], int tiszta[]) {
    int count = 0;
    int i = 0;
    int csillagSzama = 0;

    for (i = 0; zajos[i] != -1; ++i) {

        // Elso csillag -1-nel eltarolja mennyi elemu a csillag tomb
        if (csillag[i] == -1 && csillagSzama == 0){
            csillagSzama = i;
            count++;

            //printf("o: %d\n", csillagSzama);

            // visszaforditja az elejere a csillag tombot, ha a vegere er
        } else if (csillag[i-csillagSzama*count] == -1){
            count++;
        }

        /* printf("Index: %d\n", i-csillagSzama*count);
        printf("Eretek: %d\n", csillag[i-csillagSzama*count]); */

        tiszta[i] = zajos[i]-csillag[i-csillagSzama*count];
    }
    // Zaro elem visszarakása
    tiszta[i] = -1;

    //printf("Program vege\n\n");
    return;
}
/***********************************************************************
************************************************************************
**
**	EZEN A PONTON TÚL NE VÁLTOZTASS SEMMIT SEM A FÁJLON!
**
************************************************************************
***********************************************************************/

void call_1()
{
    int i, zajos[64], csillag[64], eredmeny[64];
    for (i = 0; i < 63; ++i) {
        if (fscanf(stdin, "%d", zajos + i) != 1) {
            fprintf(stderr, "HIBA: Nem olvasható adat!\n");
            return;
        }
        if (zajos[i] == -1) {
            break;
        }
    }
    zajos[i] = -1;
    for (i = 0; i < 63; ++i) {
        if (fscanf(stdin, "%d", csillag + i) != 1) {
            fprintf(stderr, "HIBA: Nem olvasható adat!\n");
            return;
        }
        if (csillag[i] == -1) {
            break;
        }
    }
    csillag[i] = -1;
    jeltisztitas(zajos, csillag, eredmeny);
    for (i = 0; eredmeny[i] != -1 && i < 63; ++i) {
        fprintf(stdout, "%d, ", eredmeny[i]);
    }
    if (eredmeny[i] == -1) {
        fprintf(stdout, "-1\n");
    } else {
        fprintf(stdout, "%d, ...\n", eredmeny[i]);
    }
}
void test_1()
{
    int i, j, eredmeny[10];
    struct {int zajos[10]; int csillag[10]; int eredmeny[10];} testlist[2] = {
            {{20, 20, 20, 20, -1},                 { 0,  5, 10, 15, -1}, {20, 15, 10,  5, -1}},
            {{30, 10, 40, 40, 20, 30, 30, 30, -1}, {10,  0, 20, -1},     {20, 10, 20, 30, 20, 10, 20, 30, -1}},
    };
    for (i = 0; i < 2; ++i) {
        jeltisztitas(testlist[i].zajos, testlist[i].csillag, eredmeny);
        for(j = 0; eredmeny[j] != -1 && testlist[i].eredmeny[j] != -1 && eredmeny[j] == testlist[i].eredmeny[j]; ++j);
        if (eredmeny[j] != testlist[i].eredmeny[j]) {
            fprintf(stderr, "HIBA: jeltisztitas({");
            for(j = 0; testlist[i].zajos[j] != -1; ++j) {
                fprintf(stderr, "%d,", testlist[i].zajos[j]);
            }
            fprintf(stderr, "-1},{");
            for(j = 0; testlist[i].csillag[j] != -1; ++j) {
                fprintf(stderr, "%d,", testlist[i].csillag[j]);
            }
            fprintf(stderr, "-1},{...})\n"
                            "\telvárt eredmény: {...} = {");
            for(j = 0; testlist[i].eredmeny[j] != -1; ++j) {
                fprintf(stderr, "%d,", testlist[i].eredmeny[j]);
            }
            fprintf(stderr, "-1}\n"
                            "\tkapott eredmény: {...} = {");
            for(j = 0; eredmeny[j] != -1 && j < 10; ++j) {
                fprintf(stderr, "%d,", eredmeny[j]);
            }
            if (j < 10) {
                fprintf(stderr, "-1}\n");
            } else {
                fprintf(stderr, "...}\n");
            }
        }
    }
}

typedef void (*call_function)();

call_function call_table[] = {
        call_1,
        NULL
};


call_function test_table[] = {
        test_1,
        NULL
};

static int __arg_check_helper(const char * exp, const char * arg) {
    while (*exp && *arg && *exp == *arg) {
        ++exp;
        ++arg;
    }
    return *exp == *arg;
}

int main(int argc, char *argv[])
{
    int feladat, calltabsize;
    if ((argc > 1) && !(__arg_check_helper("-t", argv[1]) && __arg_check_helper("--test", argv[1]))) {
        if (argc > 2) {
            feladat = atoi(argv[2]);
            for (calltabsize=0; test_table[calltabsize]; calltabsize++);
            if (feladat <= 0 || calltabsize < feladat) {
                fprintf(stderr, "HIBA: rossz feladat sorszám: %d!\n", feladat);
                return 1;
            }
            (*test_table[feladat-1])();
        } else {
            for (feladat=0; test_table[feladat]; ++feladat) {
                (*test_table[feladat])();
            }
        }
        return 0;
    }
    if (!freopen("be.txt", "r", stdin)) {
        fprintf(stderr, "HIBA: Hiányzik a `be.txt'!\n");
        return 1;
    }
    if (!freopen("ki.txt", "w", stdout)) {
        fprintf(stderr, "HIBA: A `ki.txt' nem írható!\n");
        return 1;
    }
    for (calltabsize=0; call_table[calltabsize]; calltabsize++);
    if (fscanf(stdin, "%d%*[^\n]", &feladat)!=1) {
        fprintf(stderr, "HIBA: Nem olvasható a feladat sorszám!\n");
        return 1;
    }
    fgetc(stdin);
    if (0<feladat && feladat<=calltabsize) {
        (*call_table[feladat-1])();
    } else {
        fprintf(stderr, "HIBA: Rossz feladat sorszám: %d!\n", feladat);
        return 1;
    }
    fclose(stdin);
    fclose(stdout);
    return 0;
}