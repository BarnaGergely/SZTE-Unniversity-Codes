# Assembly Intel X86 Linux - 2.ZH Jegyzet 

Ez egy jegyzetet az Intel x86 Linux Assembly témaköréből. Nem teljes és egyáltalán nem biztos hogy hibátlan, de aránylag rövid és érhető.
- Szöveggel elmagyaráztam mindent
- tele van példa programokkal
- Házi feladatok megoldása elmagyarázva

Ha hibát találsz vagy valamivel kiegészítenéd, nyugodtan módosítsd a dokumentumot vagy jelezd felém.

## Alap assembly program

[Tutoriál videjó](https://youtu.be/p6InUolGGvA?t=24)

### Regiszterek

[Tutoriál videjó](https://youtu.be/p6InUolGGvA?t=250)

### adat mozhatás (mov)

`mov eax, ebx` - eax értéke legyen ebx

### Verem kezelése

[Tutoriál videjó](https://youtu.be/V3ySVsubdzQ?t=190)

- `push eax` - eax mnetése a stack-re
- `pop eax` - a stack "tetején" lévő (legutóbb bele rakott) elem kivétele az `eax`-be
- `esp` regiszter (stack pointer regiszter): mindig a verem "tetejére" mutat, a legutóbb berakott elemre

### Indirekt címzés

Kapcsoszárójelekkel tudjuk elérni egy memória címen tárolt adatok értékét.

- `[eax]` - az eax regiszterben lévő számot memória címként használva megpróbál betölteni a memóriából egy adatot. Ha memória cím volt benne vagy szerencséd volt és pont létező memóriára mutat a benne lévő szám, megkapot az általa mutatott értéket.

- `[CIMKE]` - A CIMKE címkén (ami ugye egy memória címként működik) lévő adatot kéri be a memóriából

A kapcsoszárójelekben lévő memóriacímhez tudunk hozzá adni. Ez annyi bájttal elcsúsztatja a memóriacímet, amennyit hozzá adtunk. Az integer tipusú változóink 4 bájtosak, szóval ha egy tömb következő eleme érdekel 4-et kell hozzá adnod a memória címhez.

- `[TOMB+0*4]` - TOMB címkén lévő adatsorozat _(ismertedd nevén tömb)_ 1. eleme
- `[TOMB+1*4]` - TOMB címkén lévő tömb 2. eleme
- `[TOMB+2*4]` - TOMB címkén lévő tömb 3. eleme

Ha megfigyeled ez olyan, mint egy C-s tömb: _NULLÁTÓL indexelünk!!!_

Egy másik regiszterrel akár dinamikusan is indexelhetünk egy tömböt

- `[SZAMOK+eax*4]` - TOMB címkén lévő "tomb" eax-edik eleme

És persze az eltolás is működik regiszterekből bejövő címekkel is.

- `[ebx+0*4]` - ebx-ben lévő memóriacímen lévő tömb 1. eleme
- `[ebx+eck*4]` - ebx-ben lévő memóriacímen lévő tömb eck-edik eleme

mov, add, cmp, stb. utasításokban **nem használhatunk mindkét paramétérben indirekt címzést!!!!!!!**

Ez hibás (el se olvasd!): `mov [TOMB + 2*4], [eax + edx*4] // HIBÁS`

```assembly
mov ecx, [eax + edx*4]
mov [TOMB + 2*4], ecx // HELYES
```

## Aritmetika (matek műveletek)

### Összeadés

```assembly
add eax, ebx              // összedja az eax-ben lévő számból az ebx-ben lévő számot, az eredményt az eax-be rakja
add ecx, 3                // ecx = ecx + 3
add ecx, [SZAMOK + 0*4]   // ecx = ecx + SZAMOK tömb nulladik indexű, az az 1. eleme
add [SZAMOK + 1*4], ecx   // SZAMOK 2. eleme = SZAMOK 2. eleme + ecx
```

### Kivonás

```assembly
sub eax, ebx              // kivinja az eax-ben lévő szából az ebx-ben lévő számot, az eredményt az eax-be rakja
sub ecx, 3                // ecx = ecx - 3
sub ecx, [SZAMOK + 0*4]   // ecx = ecx- SZAMOK tömb nulladik indexű, az az 1. eleme
sub [SZAMOK + 1*4], ecx   // SZAMOK 2. eleme - SZAMOK 2. eleme + ecx
```

### Szorzás

Külön van előjeles (`imul`) és elője nélküli (`mul`) szorzás utasítás. Mindkettő ugyan úgy működik: adsz neki egy számot és megszorozza fixen az eax-ben lévő számmal, az eredmény az eax-be kerül, ha nem fér bele, az edx-ben folytatódik, ezért szorzásnál nem szabad semmi fontosat az edx-ben hagyni.

#### Előjel nélküli szorzás (`mul`)

```assembly
push edx      // biztonsági mentjük edx-et
mul ebx       // eax = eax * ebx
mul 2         // eax = eax * 2
mul eax       // eax = eax * eax
mul [LENGTH]  // eax = eax * LENGTH "változó"
pop edx       // mentés vissza töltése
```

#### Előjeles szorzás (`imul`)

pont ugyan úgy működik mint az előjel nélküli, csak kaphatunk negatív eredményt is

```assembly
imul ebx       // eax = eax * ebx
imul 2         // eax = eax * 2
imul eax       // eax = eax * eax
imul [LENGTH]  // eax = eax * LENGTH "változó"
```

### Osztás

Külön van elője nélküli (`div`) és előjeles (`idiv`) osztás utasítás. Mindkettő ugyan úgy működik: elosztja az eax-ben lévő számot az általad megadott számmal, az eredmény az eax-be kerül, a maradék pedig az edx-be, ezért az edx-be nullát kell rakni osztás előtt.

#### Előjel nélküli osztás (`div`)

```assembly
mov edx, 0
div ebx       // eax = eax / ebx
mov edx, 0
div 2         // eax = eax / 2
mov edx, 0
div eax       // eax = eax / eax
mov edx, 0
div [LENGTH]  // eax = eax / LENGTH "változó"
```

#### Előjeles osztás (`idiv`)

```assembly
mov edx, 0
idiv ebx       // eax = eax / ebx
mov edx, 0
idiv 2         // eax = eax / 2
mov edx, 0
idiv eax       // eax = eax / eax
mov edx, 0
idiv [LENGTH]  // eax = eax / LENGTH "változó"
```

#### Osztás példa

```assembly
.intel_syntax noprefix
.data
  SZAMLALO: .int 4
  NEVEZO: .int 2
  EREDMENY: .int 0
  MARADEK: .int 0
.text
  // ha lenne valami a regiszterekben lementeni őket a stack-re
  push edx
  push eax

  mov ebx, [NEVEZO]     // ebx = NEVEZO "változó"

  mov eax, [SZAMLALO]   // eax = SZAMLALO "változó"
  mov edx, 0            // edx kinullázása osztás előtt (ide kerül majd a maradék)
  idiv eax, ebx         // előjeles osztás: eax = eax / ebx

  // eredmények vissza mentése
  mov [EREDMENY], eax
  mov [MARADEK], edx

  // vissza tölteni a lementett regisztereket
  pop eax
  pop edx

```


## Ciklusok működése

Assembly-ben nincsenek ciklusok. Fut a program vegrehajtasa fentrol lefele sorrol sorra,
mi csak a ugralni tudunk benne feljebb es lejebb cimkekre.

## Ugrás működése

`jmp <memoriacim>` - a vezerles a memoriacim-re ugrik es onnan folytatodik

Ne feledd a cimkek sima memoriacimekkent viselkednek, igy lehet rajuk ugrani.

### Vegtelen ciklus:

```assembly
kezdet:
// program sorai
jmp kezdet
```

### Feltételes ugrás

Vannak feteteles ugro utasitasok is, amik csak akkor ugranak, ha egy feltetel teljesul. _(ez nem igaz, a háttérben nem igy mukodik, de igy a legerthetobb és a használata a mi feladatainkban ilyen)_

```assembly
cmp edx, 42  // edx es a 42 osszehasonlitasa
jz kezdet    // ha az elozo cmp utasitasban megadott adatok egyenlok, a kezdet cimkere ugrik
```

#### Osszehasonlito utasitasok:

| Relacio | Eloojeles | Elojeltelen |
| ------- | --------- | ----------- |
| ==      | je, jz    | je, jz      |
| !=      | jne, jnz  | jne, jnz    |
| <       | jl, jnge  | jb, jnae    |
| <=      | jle, jng  | jbe, jna    |
| >       | jg, jnle  | ja, jnbe    |
| >=      | jge, jnl  | jae, jnb    |

### for ciklus

```assembly
mov ecx, 0      // legyen ez az "i" valtozo, amiben taroljuk epp hanyadik elemnel jar a ciklus

for:  // for ciklus kezdetet jelzo cimke

    // kilepesi feltetel, ha ez teljesul, lepjunk ki a for-bol
    cmp ecx, 5        // ha ecx >= 5, az az ha az aktualis elem a tomb 5. egyben utolsó eleme,
    jge forend        // akkor ugorjunk a forend cimkere

    // kod helye, amit a forban akarunk futtatni

    inc ecx         // i + 1
jmp for         // ha ide ert a program vissza ugrik a for elejere

forend:  // for ciklus vege
```

## 2. Házifeladat megoldása:

### feladat által meghatározott változók

- esi = integer (4 bajtos) tomb kezdetenek a cime
- edx = tomb elemeinek szama
- edi = kimeneti tomb cime
- eax = masolt elemek szama

### feladatok:

- Azokat a tomb elemeket melyek 2-nel nagyobbak vagy egyenloek masoljuk egymas
  uton a kimeneti tombbe.
- Ekozben az eax regiszterbe szamoljuk, hogy hany elemet masoltunk a kimeneti
  tombbe. A ciklus vegere ennek az eax regiszternek kell tartalmaznia hany elem
  kerult osszesen a kimeneti tombbe.

### Megoldás

```assembly
mov ecx, 0      // legyen ez az "i" valtozo, amiben taroljuk epp hanyadik elemnel jar a ciklus
mov eax, 0      // ide fog kerulni hogy hany elemet raktunk a kimeneti tombbe

for:  // for ciklus kezdetet jelzo cimke

    // kilepesi feltetel, ha ez teljesul, lepjunk ki a for-bol
    cmp ecx, edx        // ha ecx >= edx, az az ha az aktualis elem a tomb utolso utani eleme,
    jge forend          // akkor ugorjunk a forend cimkere


    push ebx        // elfogytak a regisztereink, de kéne még egy, ezért az épp nem haszáltat
                    // lementem a stack-re
    mov ebx, [esi + 4*ecx]      // ebx-be rakom, a bemeneti tomb aktualis elemet.
                                // Azert van erre szukseg, mert mov és cmp mindket oldalan
                                // nem lehet indirekt címzés ([eax])

    // if elagazas
    cmp ebx, 2
    jl hamis        // if(elem < 2) -> hamis | ha a a tomb eleme kisebb mint ketto, atugorjuk az igaz kodot

        // igaz a feltetel
        mov [edi + 4*eax], ebx      // kiementi tomb kovetkezo ures helyere masolom a bemeneti tomb i-edik elemet
        inc eax         // masolt elem szamlalo + 1

    hamis:      // ha hamis, nem csinalunk semmit

    pop ebx         // regiszter régi értékének (tomb elemeinek szama) vissza töltése,
                    // mivel újra szükség lesz rá a for elején
    inc ecx         // i + 1

jmp for         // ha ide ert a program vissza ugrik a for elejere

forend:  // for ciklus vege
```
## Függvények készítése

[Tutoriál videjó](https://www.youtube.com/watch?v=V3ySVsubdzQ&list=PLTD6Kt9p80KBVDCvFy6vAZ8tuhLH5AzAW&index=4)

[**Nem érdekel az elmélet, csak a példa program**](https://github.com/BarnaGergely/SZTE-Unniversity-Codes/blob/main/Assembly/Assembly_ZH2_Vazlat.md#ez-pedig-egy-t%C3%B6k%C3%A9letes-f%C3%BCggv%C3%A9ny-h%C3%ADv%C3%A1s)

Ha szeretnél egy C-ből hívható Assembly függvényt csinálni, egy globális címkét kell csinálnod olyan nevűt, ami a függvény neve lesz.

pl. ezt a C függvényt valósítsuk meg Assembly-ben: `void eljaras(){ return; }`

```assembly
// ez a rész a függvény nevét adja meg
.global eljaras
eljaras:

  ret // ez az utasítás vissza ugrik, oda ahonnan meghívták a függvényt, szóval nagyjából return; megfelelője
```

Assembly-ból egy függvényt a `call fuggvenyNeve` utasítással tudunk meghívni.

### Visszatérési érték

Okos programozók réges régen egy messzi-messzi... kitalálták, hogy a függvény visszatérési értékét adjuk vissza az eax regiszterben.

`int ketto(){ return 2; }`

```assembly
.global ketto
ketto:

  mov eax, 2 // ami az eax-ben van a függvény vége előtt, az lesz a visszatérési érték

  ret
```

### cdecl eljarashívás konvenció

A főprogram, ahonnan meghíták a függvényt is tárolna egy rakás dolgot a regiszterekben. Ha te a metódusodban neki állsz és felül írod a regiszterek tartalmát, jól szétcseszed a főprogramot. Erre találták ki "szídepöl" konvenciót, ami annyit mond: metsél le mindent stack-ra, amit használsz, aztán ha végeztél töltsed vissza.

**A legbiztosabb ha lementesz mindent az eax-en és az esp-n kívül:**

```assembly
.global nemRontElMindent
nemRontElMindent:
    push ebp
    push ebx
    push ecx
    push edx
    push esi

    mov eax, 69

    pop esi
    pop edx
    pop ecx
    pop ebx
    pop ebp
  ret
```

### Paraméterek és ret

Ez bonyolult lesz. ;( A függvény paraméterei a stack (verem) en adódnak át. A stack utolsó elemére mutat az esp regiszter, így a `[esp]` címzéssel elérhetjük a legutóbb berakott elemet, az `[esp + 1*4]`-el a következőt, stb. A stack megfordítja a beadott értékek sorrendjét: amit utoljára bele raktunk azt fogjuk tudni elsőnek kivenni.

A függvény hívás tesz egy akkor nagy szivességet hogy fordított sorrendben dobálja be a verembe a paramétereket, így mi jó sorrendben tudjuk kivenni, viszont utána jön a köcsög függvény hívás és bele teszi a címet, amire majd a függvény végén a `ret` utasításnak vissza kell térni.

Szóval így néz ki a verem egy függvény meghívásakor:

| Név                    | Címzés      |
| ---------------------- | ----------- |
| 3. parameter           | [esp + 3*4] |
| 2. parameter           | [esp + 2*4] |
| 1. parameter           | [esp + 1*4] |
| Visszateresi cim (ret) | [esp + 0*4] |

Ne ilyesszen meg hogy fejjel lefelé van a verem, valamiért lefele bővül: ha bele rakunk egy elemet az az aljára kerül és az esp pointer is lentebb mutat egyel.

A gond ott kezdődik, hogy ha rakunk még dolgokat a verembe, mondjuk mivel cdecl fegyvert fog a fejünkhöz és kötelez rá, akkor egy idő után foggalmunk nem lesz hogy mennyit kéne az esp-hez hozzá adni, hogy elérjük a paramétereket. 

Erre nyújt megoldást az `ebp` (bázi pointer): a függvény elején ebbe elmentjük az esp értékét, így már pakolhatunk akár mit a verembe, az ebp-vel, mindig el fogjuk tudni érni a paramétereket. Annyira kell figyelni, hogy ebp-t is le kell menteni a cdecl szerint, mielőtt még módosulna.

ebp használatával cdecl konvenzió mellett így néz ki a vermünk:

| Név                    | Címzés      |
| ---------------------- | ----------- |
| 3. parameter           | [ebp + 4*4] |
| 2. parameter           | [ebp + 3*4] |
| 1. parameter           | [ebp + 2*4] |
| Visszateresi cim (ret) | [ebp + 1*4] |
| cdecl miatt régi ebx   | [ebp + 0*4] |
| cdecl miatt régi ecx   | [esp + 2*4] |
| cdecl miatt régi edx   | [esp + 1*4] |
| cdecl miatt régi esi   | [esp + 0*4] |

#### Ez pedig egy tökéletes függvény hívás:

Ha így hívod meg a függvényt, csinálhatsz bármit a regiszterekkel, rakhatsz bármit a stack-re, nem lesz gond és bármikor el fogod tudni érni a paramétereket.

c függvény:

```c
int tokeletesFuggveny(int szam1, int szam2){
  return szam1 + szam2;
}
```

**A prológust, epilógust és a paraméterek elérését érdemes utasításól utasításra megtanulni.**

Assembly megvalósítás:

```assembly
.global tokeletesFuggveny
tokeletesFuggveny:
  // Prológus:
  push ebp
  mov ebp, esp
  push ebx
  push ecx
  push edx
  push esi

  mov ebx, [ebp + 2*4]  // 1. paraméter metöltése az ebx-be
  mov ecx, [ebp + 3*4]  // 2. paraméter metöltése az ecx-be

  add ebx, ecx  // ebx = ebx + eck (szam1 + szam2)

  mov eax, ebx  // visszatérési érték beállítása az összeadás eredményére

  // Epilógus:
  pop esi
  pop edx
  pop ecx
  pop ebx
  mov esp, ebp
  pop ebp
  ret
```


## 3. Házi feladat megoldás
```assembly
.intel_syntax noprefix

.text

/*
Elvárt függvény prototípus (C-ben):
int filterElements(int input[] , int length, int output[]);
*/

.global filterElements
filterElements:
    # Függvény epilógus
    push ebp
    mov ebp, esp
    push ebx
    push ecx
    push edx
    push esi


    /*
        sum = edx
        idx = ecx
        average = eax

        length = ebx
        input = ebx
        output = ecx
        outIdx = edx
    */

    mov edx, 0              # sum = 0
    mov ebx, [ebp + 4 + 2*4]    # ebx = length

    # for (idx = 0 ; idx < length ; idx++)
    mov ecx, 0  # idx = 0
    for1:
        cmp ecx, ebx       # ha idx >= length
        jge for1end        # akkor ugorjunk a for1end cimkere (kilép a for-ból)

        mov eax, [ebp+4+4]        # eax = input tömb címe
        add edx, [eax + ecx*4]  # sum += input[idx];

        inc ecx         # idx++
        jmp for1        # ha ide ert a program vissza ugrik a for elejere

    for1end:  # for ciklus vege

    # average = sum / length;
    mov eax, edx
    mov edx, 0
    cdq         # előjel helyes osztáshoz meg kell hívnunk
    idiv ebx

    # 2. for ciklus
    mov ecx, 0  # idx = 0
    mov edx, 0  # outIdx = 0
    for2:
        cmp ecx, ebx        # ha idx >= length
        jge for2end        # akkor ugorjunk a for1end cimkere (kilép a for-ból)


        
        push ebx        # length lementése
        mov ebx, [ebp+4+4]  # ebx = input tömb címe

        # If (input [idx] > average)
        cmp eax, [ebx + ecx*4]        # ha avarage >= input[idx]
        jge false                     # akkor ugorjunk a false cimkere

            # output[outIdx] = input[idx]
            push eax # avarage lementése
            mov eax, [ebx + ecx*4] # eax = input[idx]
            push ecx # idx lementése
            mov ecx, eax
            mov eax, [ebp +4+ 3*4] # eax = output címe
            mov [eax + edx*4], ecx
            pop ecx # idx vissza töltése
            pop eax # avarage vissza töltése

            inc edx # outIdx++

        false:

        pop ebx # length vissza töltése


        inc ecx         # idx++
        jmp for2        # ha ide ert a program vissza ugrik a for elejere
    for2end:  # for ciklus vege

    # visszatérési érték
    mov eax, edx

    # függvény epilógus

    # regiszterek vissza töltése

    pop esi
    pop edx
    pop ecx
    pop ebx

    mov esp, ebp
    pop ebp

    ret
```

## 4. Házi feladat megoldás

```assembly
.intel_syntax noprefix
.text

.global caseInvert
caseInvert:

/*
input = ebx
output = ecx
idx = edx
ch = al
ideiglenes matek izé = bl
*/

# Függvény epilógus
    push ebp
    mov ebp, esp
    push ebx
    push ecx
    push edx
    push esi

    mov ebx, [ebp + 4 + 1*4]    # Input betöltése
    mov ecx, [ebp + 4 + 2*4]    # output betöltése

# for(; input[idx]!=0; idx++)
    mov edx, 0      # idx = 0
    for:
        cmp byte ptr [ebx + 1 * edx], 0
        je forend

        mov al, [ebx + 1 * edx]

        # elágazás: if (’a’<= ch && ch <= ’z’ )
        cmp al, 'a'
        jl nemkisbetu
        cmp al, 'z'
        jg nemkisbetu

        # output [idx] = ’A’ + (ch−’a’)
        push ebx
        mov bl, al
        sub al, 'a'
        add al, 'A'
        mov byte ptr [ecx + 1 * edx], al
        pop ebx

        jmp kesz

        # elseif ( ’A’ <= ch && ch <= ’Z’ )
        nemkisbetu:
        cmp al, 'A'
        jl nemnagybetu
        cmp al, 'Z'
        jg nemnagybetu

        # output [idx] = ’a’ + (ch−’A’);
        push ebx
        mov bl, al
        sub al, 'A'
        add al, 'a'
        mov byte ptr [ecx + 1 * edx], al
        pop ebx

        jmp kesz

        # ág else
        nemnagybetu:
        mov byte ptr [ecx + 1 * edx], al # output [idx] = ch
        
        kesz:

        inc edx     # idx++
        jmp for
    forend:

    mov byte ptr [ecx+1*edx], 0x00 # output [idx] = ch

# függvény epilógus
    pop esi
    pop edx
    pop ecx
    pop ebx
    mov esp, ebp
    pop ebp
    ret
```