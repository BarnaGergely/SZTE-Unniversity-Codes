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
1. feladat (3 pont)

Irj egy programot, amely a harom oldalhosszbol kiszamitja egy teglatest
felszinet es terfogatat!

A program beolvas harom nemnegativ valos szamot a standard inputrol.
Ezek a szamok a teglatest oldalhosszai. A program csak jo inputot kaphat,
igy a hibakezelessel nem kell foglalkozni. A program outputja ket sor.
Az elsoben a "V = " szoveg utan a teglatest terfogata, a masodik sorban
az "A = " szoveg utan a teglatest felulete szerepel. Mindket szamot 10
karakteren jobbra igazitva 3 tizedesjegy pontossaggal kell kiiratni.
Ugyelj ra, hogy a kiiratast sorvege jel zarja es a program ne irjon ki
mas egyebet a fentieken kivul!

A terfogat es a felulet kiszamitasara erdemes lehet egy-egy kulon fuggvenyt
kesziteni, esetleg felhasznalva a teglalap teruletenek kiszamitasat vegzo
fuggvenyt is.

A programot main helyett main_t neven keszitsd el,
de a tartalma olyan legyen, mintha egy programot irnal!
*/

double felszin(double a, double b, double c){
    return ((a*b)+(a*c)+(b*c))*2;
}

double terfogat(double a, double b, double c){
    return a*b*c;
}

int main_t() {
    double a, b, c;
    scanf("%lf %lf %lf", &a, &b, &c);
    printf("V = %10.3lf\n", terfogat(a, b, c));
    printf("A = %10.3lf\n", felszin(a, b, c));
    return 0;
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
    int check;
    if((check = fgetc(stdin)) == EOF) {
        fprintf(stderr, "HIBA: Nem olvasható adat!\n");
        return;
    }
    if(main_t() && (check == 'R')) {
        fputs("RETURNS NONZERO", stdout);
    }
}
void test_1()
{
    main_t();
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
