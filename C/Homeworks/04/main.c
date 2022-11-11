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

Az emberiseg egy onellato mukodesre kepes koloniat letesitett a Marson. Az
epitkezes es bovites meg javaban folyik. Azonban nehany naponta homokviharok
sopornek vegig a kolonia teruleten, amelyek miatt sajnos mar tobb kint rekedt
telepes eletet vesztette.

Dr. Zhang, a kolonia meteorologiai szakertoje feljegyezte a homokviharok
elofordulasait, es azt allapitotta meg, hogy a helyi specialis mikroklima
kovetkezteben pontos rendszer van a viharok erkezeseben: a koztuk eltelt napok
egy ismetlodo mintat kovetnek. Arrol nincs adatunk, hogy a minta milyen
hosszu, de tudjuk azt, hogy mostanra mar legalabb egyszer teljesen
feljegyzesre kerult, valamint az is ismert, hogy az osszes feljegyzett nap
illeszkedik ehhez a mintahoz.

Sajnos elodod es mentorod, Dr. Halliburton szinten odaveszett az elozo
viharban, igy matol te latod el az informatikai feladatokat a kolonian. Mint a
szuperszamitogep uj kezeloje, minden felelosseg rad harul. A feladatod elerni,
hogy a szuperszamitogep meg tudja josolni a kovetkezo vihar joveteleig varhato
napok szamat. Ennek sikeren emberi eletek es a Mars kolonizaciojanak teljes
jovoje mulhat.

Ird meg a szuperszamitogep vihar-elorejelzo fuggvenyet, amely egy egesz
szamokbol allo tombben kapja parameterul a viharok kozott eltelt napok szamat,
majd az ismetlodo minta meghatarozasa utan kiszamitja, hogy mely ertekkel
folytatodna a tomb. A fuggveny ezzel az ertekkel, azaz a kovetkezo viharig
varhato napok szamaval terjen vissza. Az input tombot a -1 ertek zarja
(ez az ertek mar nem resze a feljegyzesek sorozatanak). A minta mindig a tomb
elejen kezdodik.

Kodold le a fuggvenyt C nyelven! A fuggveny fejlecen ne valtoztass! A fuggveny
inputjai a parameterek, outputja a visszateresi ertek. A fuggveny nem vegez IO
muveleteket!
*/

int elorejelez(int feljegyzesek[]) {
    int kovetkezo = 0;

    // megkeresi a mintat
    int i;
    for (i = 0; feljegyzesek[i] != -1; ++i) {
        int temp = feljegyzesek[i];

        // Feljegyzesek osszes lehetséges sorozatát betölti a sorozat tömbbe
        int sorozat[i+1];
        for (int j = 0; j <= i; ++j) {
            sorozat[j] = feljegyzesek[j];
        }

        // Összehasonlítja a feljegyzesekkel a sorozatot
        // Feladat: Végig menni a feljegyzéseken, össze hasonlítani a sorozattal és ha a sorozat véget ér, vissza téríteni az első elemhez
        short int megoldas = 0;
        int count = 0;
        for (int j = 0; feljegyzesek[j] != -1 ; ++j) {

            if (feljegyzesek[j] == sorozat[j-count*i]) {
                megoldas = 1;
            } else {
                megoldas = 0;
                break;
            }

            // Ha a sorozat tomb vegere er visszalep
            if (i+1 == (j+1)-count*(i+1)){
                count++;
            }
        }

        if (megoldas == 1) { i = i - 1; break; }
    }

    // Minta betöltése
    int minta[i+1];
    for (int j = 0; j <= i; ++j) {
        minta[j] = feljegyzesek[j];
    }

    // Megszámolja hány elemű a feljegyzések tömb
    int j;
    for (j = 0; feljegyzesek[j] != -1 ; ++j) {
    }
    j=j-1;

    return kovetkezo = minta[(j+1)%(i+1)];
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
    int i, eredmeny, feljegyzesek[64];
    for (i = 0; i < 63; ++i) {
        if (fscanf(stdin, "%d", feljegyzesek + i) != 1) {
            fprintf(stderr, "HIBA: Nem olvasható adat!\n");
            return;
        }
        if (feljegyzesek[i] == -1) {
            break;
        }
    }
    eredmeny = elorejelez(feljegyzesek);
    fprintf(stdout, "%d\n", eredmeny);
}
void test_1()
{
    int i, j, eredmeny;
    struct {int feljegyzesek[20]; int eredmeny;} testlist[2] = {
            {{1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, -1}, 1},
            {{1, 2, 1, 1, 2, 1, 2, 3, 1, 2, 1, 1, 2, 1, 2, -1},       3},

    };
    for (i = 0; i < 2; ++i) {
        eredmeny = elorejelez(testlist[i].feljegyzesek);
        if (eredmeny != testlist[i].eredmeny) {
            fprintf(stderr, "HIBA: elorejelez({");
            for(j = 0; testlist[i].feljegyzesek[j] != -1; ++j) {
                fprintf(stderr, "%d,", testlist[i].feljegyzesek[j]);
            }
            fprintf(stderr, "-1})\n"
                            "\telvárt eredmény: %d\n"
                            "\tkapott eredmény: %d\n", testlist[i].eredmeny, eredmeny);
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
