/* ######## DO NOT MODIFY BELOW (TILL MENTIONED OTHERWISE ############## */
.intel_syntax noprefix
.section .data

.section .text
.global feladat
feladat: // int feladat(int* array, int length, int* output)
    push ebp
    mov ebp, esp

    push ebx
    push ecx
    push edx
    push esi
    push edi

    mov esi, [ebp + 4*2]    // int* array => esi
    mov edx, [ebp + 4*3]    // int length => ecx
    mov edi, [ebp + 4*4]    // int* output => edi

    mov eax, 0              // result!
/* ######## DO NOT MODIFY ABOVE ############## */
/* |||||||| TASK 1 START, MODIFY AFTER THIS ||||||||||||||||||||||| */
/* Ez után dolgozhatunk. */
/* feladat:
 *  Jarjuk be az integer tombot aminek a kezdo cimet az "esi" regiszter tarolja.
 *  A tomb elemeinek a szamat az "edx" regiszter tartalmazza.
 *  Masoljunk minden integert ami nagyobb 2-nel a kimeneti tombbe.
 *  A kimeneti tomb kezdo cimet az "edi" regiszter tartalmazza.
 *  Az "eax" regiszterbe szamoljuk, hogy hany elemet masoltunk a kimeneti tombbe.
 *
 *  Az ellenorzes soran az kimeneti tomb tartalma es az "eax" regiszter is vizsgalasra kerul.
 *  A bemeneti es kimeneti tombok elojeles egesz ertekeket tarolnak.
 *  A bemeneti tomb elemeit ne modositsuk.
 */



/* ------------------------------------- MEGOLDAS ------------------------------------- */
/* Assembly-ben nincsenek ciklusok. Fut a program vegrehajtasa fentrol lefele sorrol sorra,
   mi csak a ugralni tudunk benne feljebb es lejebb cimkekre.

    jmp <memoriacim> - a vezerles a <memoriacim>-re ugrik es onnan folytatodik

    Ne feledd a cimkek sima memoriacimekkent viselkednek, igy lehet rajuk ugrani.
    Vegtelen ciklus:

    kezdet:
        // program sorai
    jmp kezdet


    Vannak feteteles ugro utasitasok is, amik csak akkor ugranak, ha egy feltetel etljesul.
    (ez nem igaz, nem igy mukodik, de igy a legerthetobb)

    cmp edx, 42 // edx es a 42 osszehasonlitasa
    jz kezdet // ha az elozo cmp utasitasban megadott adatok egyenlok, a kezdet cimkere ugrik

    Osszehasonlito utasitasok:
    Relaacio | Eloojeles | Elojeltelen
    == | je, jz | je, jz
    != | jne, jnz | jne, jnz
    < | jl, jnge | jb, jnae
    <= | jle, jng | jbe, jna
    > | jg, jnle | ja, jnbe
    >= | jge, jnl | jae, jnb
*/

/* Feladat jegyzet:
    esi = integer (4 bajtos) tomb kezdetenek a cime
    edx = tomb elemeinek szama
    edi = kimeneti tomb cime
    eax = masolt elemek szama

    - Azokat a tomb elemeket melyek 2-nel nagyobbak vagy egyenloek masoljuk egymas
      uton a kimeneti tombbe.
    - Ekozben az eax regiszterbe szamoljuk, hogy hany elemet masoltunk a kimeneti
      tombbe. A ciklus vegere ennek az eax regiszternek kell tartalmaznia hany elem
      kerult osszesen a kimeneti tombbe.
*/

mov ecx, 0 // legyen ez az "i" valtozo, amiben taroljuk epp hanyadik elemnel jar a ciklus
mov eax, 0 // ide fog kerulni hogy hany elemet raktunk a kimeneti tombbe

for: // for ciklus kezdetet jelzo cimke

    // kilepesi feltetel, ha ez teljesul, lepjunk ki a for-bol
    cmp ecx, edx    // ha ecx >= edx, az az ha az aktualis elem a tomb utolso utani eleme,
    jge forend      // akkor ugorjunk a forend cimkere


    push ebx // elfogytak a regisztereink, de kéne még egy, ezért az épp nem haszáltat
             // lementem a stack-re
    mov ebx, [esi + 4*ecx] // ebx-be rakom, a bemeneti tomb aktualis elemet.
                           // Azert van erre szukseg, mert mov és cmp mindket oldalan
                           // nem lehet indirekt címzés ([eax])

    // if elagazas
    cmp ebx, 2
    jl hamis // if(elem < 2) -> hamis | ha a a tomb eleme kisebb mint ketto, atugorjuk az igaz kodot

        // igaz a feltetel
        mov [edi + 4*eax], ebx // kiementi tomb kovetkezo ures helyere masolom a bemeneti tomb i-edik elemet
        inc eax // masolt elem szamlalo + 1

    hamis: // ha hamis, nem csinalunk semmit

    pop ebx // regiszter régi értékének (tomb elemeinek szama) vissza töltése, 
            // mivel újra szükség lesz rá a for elején
    inc ecx // i + 1
jmp for // ha ide ert a program vissza ugrik a for elejere

forend: // for ciklus vege



/* Ez alatt ne modositsunk semmit se! */
/* |||||||| TASK 1 END, MODIFY BEFORE THIS ||||||||||||||||||||||| */
/* ######## DO NOT MODIFY BELOW ############## */
    pop edi
    pop esi
    pop edx
    pop ecx
    pop ebx

    mov esp, ebp
    pop ebp
    ret

/* ######## DO NOT MODIFY BELOW ############## */
/* ######## DO NOT MODIFY BELOW ############## */
/* ######## DO NOT MODIFY BELOW ############## */
/* ######## DO NOT MODIFY BELOW ############## */
/* ######## DO NOT MODIFY BELOW ############## */
/* ######## DO NOT MODIFY BELOW ############## */
/* ######## DO NOT MODIFY BELOW ############## */
/* ######## DO NOT MODIFY BELOW ############## */
/* ######## DO NOT MODIFY BELOW ############## */
/* ######## DO NOT MODIFY BELOW ############## */
/* ######## DO NOT MODIFY BELOW ############## */
/* ######## DO NOT MODIFY BELOW ############## */
/* ######## DO NOT MODIFY BELOW ############## */
/* ######## DO NOT MODIFY BELOW ############## */
/* ########   HERE BE DRAGONS!  ############## */
/* ###### YOU HAVE BEEN WARNED!! ############# */

/* #                IF YOU STILL MODIFY THE CODE BELOW                         # */
/* #           YOU WILL SUMMON THE GREATE OLD ONES FROM THEIR SLUMBER!         # */
/* # FOR THE LOVE OF ALL THAT IS HOLY PLEASE DON'T EVEN GAZE ON THE CODE BELOW # */
/* #    IF YOU GAZE LONG INTO THE ABYSS, THE ABYSS WILL GAZE BACK INTO YOU!    # */


.section .data
.global TASK_A_DATA
TASK_A_DATA:
  TASK_A_ARRAY: .int -1, -2, 0, 3, 4, 0, 0, 0, 0, 0
  TASK_A_OUTPUT: .skip 10*4, -1
  TASK_A_LENGTH: .int 5
  TASK_A_RESULT: .int 2
  TASK_A_EXPECTED: .int 3, 4
                   .skip 8*4, -1

.global TASK_B_DATA
TASK_B_DATA:
  TASK_B_ARRAY: .int -1, -2, 2, -4, 0, 0, 0, 0, 0, 0
  TASK_B_OUTPUT: .skip 10*4, -1
  TASK_B_LENGTH: .int 4
  TASK_B_RESULT: .int 1
  TASK_B_EXPECTED: .int 2
                   .skip 9*4, -1

MSG_TASK_RETURN_OK: .asciz "[  OK  ] Task: Return value check: expected: %d (0x%0.8x) == actual: %d (0x%0.8x)\n"
MSG_TASK_RETURN_FL: .asciz "[ FAIL ] Task: Return value check: expected: %d (0x%0.8x) != actual: %d (0x%0.8x)\n"

MSG_TASK_ARRAY_IDX_OK: .asciz "[  OK  ] Task: Array value check at idx: %d: expected value : %d (0x%0.8x) == actual value: %d (0x%0.8x)\n"
MSG_TASK_ARRAY_IDX_FL: .asciz "[ FAIL ] Task: Array value check at idx: %d: expected value : %d (0x%0.8x) != actual value: %d (0x%0.8x)\n"

.section .text
.section .data

.section .text
task_2:
    push ebp
    mov ebp, esp

    push offset TASK_B_DATA
    call task_run
    add esp, 4

    mov esp, ebp
    pop ebp
    ret

task_run:   // int task_run(void* data_offset)
    // stack top data start offset!
    push ebp
    mov ebp, esp

    push esi
    push edi
    push ebx

    sub esp, 4*7
    // [ebp - 4*1] => void* data_start => int* array
    // [ebp - 4*2] => int length
    // [ebp - 4*3] => int* output_array
    // [ebp - 4*4] => int exptected
    // [ebp - 4*5] => int result
    // [ebp - 4*6] => int number of errors
    mov dword ptr [ebp - 4*6], 0

    mov eax, [ebp + 4*2]    // data_start
    mov [ebp - 4*1], eax    // array

    mov eax, [ebp + 4*2]    // data_start
    add eax, offset (TASK_A_OUTPUT - TASK_A_DATA) // data_start + 10*4
    mov [ebp - 4*3], eax    // output_array

    mov eax, [ebp + 4*2]    // data_start
    add eax, offset (TASK_A_LENGTH - TASK_A_DATA) // *(data_start + 10*4*2)
    mov eax, [eax]
    mov [ebp - 4*2], eax    // length

    mov eax, [ebp + 4*2]    // data_start
    add eax, offset (TASK_A_RESULT - TASK_A_DATA) // *(data_start + 10*4*2 + 4)
    mov eax, [eax]
    mov [ebp - 4*4], eax    // exptected_result


    push dword ptr [ebp - 4*3]
    push dword ptr [ebp - 4*2]
    push dword ptr [ebp - 4*1]
    call feladat // feladat(data_start + 0, *(data_start + 10*4*2), data_start+10*4)
    add esp, 4*3

    mov [ebp - 4*5], eax    // save result

    // print return value
    cmp eax, [ebp - 4*4]    // compare expected result
    je task_run_result_ok
    mov esi, offset MSG_TASK_RETURN_FL
    inc dword ptr [ebp - 4*6] // number of errors ++
    jmp task_run_result_end
 task_run_result_ok:
    mov esi, offset MSG_TASK_RETURN_OK

 task_run_result_end:

    push eax
    push eax
    push [ebp - 4*4]
    push [ebp - 4*4]
    push esi
    call printf
    add esp, 4*5

    // print index values
    mov ecx, 0

  task_run_array_loop:
    cmp ecx, ((TASK_A_OUTPUT - TASK_A_ARRAY) / 4)
    jge task_run_array_loop_end

    mov esi, [ebp - 4*3]    // int* output_array
    mov esi, [esi + ecx*4]  // output_array[ebx]

    mov edi, [ebp + 4*2]    // void* data_start
    add edi, offset (TASK_A_EXPECTED - TASK_A_DATA) // int* expected_data
    mov edi, [edi + ecx*4]  // expected_data[ebx]

    cmp esi, edi
    je task_run_array_ok
    mov eax, offset MSG_TASK_ARRAY_IDX_FL
    inc dword ptr [ebp - 4*6] // number of errors ++
    jmp task_run_array_end
   task_run_array_ok:
    mov eax, offset MSG_TASK_ARRAY_IDX_OK
   task_run_array_end:

    push ecx

    push esi
    push esi
    push edi
    push edi
    push ecx
    push eax
    call printf
    add esp, 4*6

    pop ecx

    inc ecx
    jmp task_run_array_loop
  task_run_array_loop_end:

    pop ebx
    pop edi
    pop esi

    // calculate return value
    mov eax, [ebp - 4*6]

    mov esp, ebp
    pop ebp
    ret

task_1:
    push ebp
    mov ebp, esp

    push offset TASK_A_DATA
    call task_run
    add esp, 4

    mov esp, ebp
    pop ebp
    ret


.section .data
.global TASKS
.global TASKS_LAST
.global TASK_ENTRY_SIZE
.global TASK_COUNT
TASKS: // { <type>, <task ptr>, <value> },
    .int 0, offset task_1, 0
    .int 0, offset task_2, 0
 TASKS_LAST:
    .int 0, 0, 0

TASK_ENTRY_SIZE:
    .int (. - TASKS_LAST)
TASK_COUNT:
    .int ((TASKS_LAST - TASKS) / (. - TASKS_LAST - 4))

.section .text
.global main
main:
    push ebp
    mov ebp, esp

    sub esp, 4*4
    // ebp - 4: tmp value
    mov dword ptr [ebp - 4], 0
    // ebp - 8: test count
    mov dword ptr [ebp - 8], 0
    // ebp - 12: test pass
    mov dword ptr [ebp - 12], 0
    mov dword ptr [ebp - 16], 0

  _run_task:
    mov ecx, [ebp - 8]
    imul ecx, TASK_ENTRY_SIZE
    mov eax, [TASKS + ecx + 4]
    cmp eax, 0
    je _no_more_tasks

    /* call task */
    pushad
    mov edx, 0
    call eax            // (*eax)(...)
    mov [ebp - 4], eax  // save result
    popad

    inc dword ptr [ebp - 8] // (test count)++

    /* validate task */
    lea eax, [ebp - 12] // &test pass
    push eax // &output
    push dword ptr [ebp - 4]  // tmp value
    push dword ptr [TASKS + ecx + 8] // exp
    push dword ptr [TASKS + ecx] // type
    push dword ptr [ebp - 8]  // idx
    call validate
    add esp, 4*5

    jmp _run_task
  _no_more_tasks:

#if defined(BIRO_MAGIC) && 0
    // [ebp - 16] : magic
    mov ecx, [ebp - 8]
    cmp dword ptr [ebp - 16], 1
    jge _biro_inited
    call _biro_magic_init
    mov dword ptr [ebp - 8], 0
    mov dword ptr [ebp - 16], 1
    jmp _run_task
   _biro_inited:
    add ecx, ecx
    mov dword ptr [ebp - 8], ecx
#endif



    /* print stats */
    push dword ptr [ebp - 12]
    push dword ptr [ebp - 8]
    call print_stats
    add esp, 4*2

    mov eax, 0

    mov esp, ebp
    pop ebp
    ret

.section .data
// t: 0
MSG_TEST_INT_OK: .asciz "[  OK  ] Task %d.: number of errors: expected: %d (0x%0.8x) == actual: %d (0x%0.8x)\n"
MSG_TEST_INT_FL: .asciz "[ FAIL ] Task %d.: number of errors: expected: %d (0x%0.8x) != actual: %d (0x%0.8x)\n"
// t: 1
MSG_TEST_CHAR_OK: .asciz "[  OK  ] Task %d.: expected: '%c' (0x%x) == actual: '%c' (0x%x)\n"
MSG_TEST_CHAR_FL: .asciz "[ FAIL ] Task %d.: expected: '%c' (0x%x) != actual: '%c' (0x%x)\n"
// table
MSG_TEST_BY_TYPE: .int offset MSG_TEST_INT_FL, offset MSG_TEST_INT_OK, offset MSG_TEST_CHAR_FL, offset MSG_TEST_CHAR_OK
MSG_STAT:
    .ascii "[ STAT ] Tests TOTAL:  %3d\n"
    .ascii "[ STAT ] Tests PASSED: %3d (%6.2f%%)\n"
    .asciz "[ STAT ] Tests FAILED: %3d (%6.2f%%)\n"

MSG_EMPTY: .asciz "[      ]\n"

.section .text
validate: // validate(idx, type, expected, actual, *pass_count)
    push ebp
    mov ebp, esp
    mov eax, 0
    push esi
    push edi

    mov ecx, [ebp + 8]  // 1: idx
    mov edx, [ebp + 12] // 2: type
    mov esi, [ebp + 16] // 3: expected
    mov edi, [ebp + 20] // 4: actual

    lea edx, [MSG_TEST_BY_TYPE + 8*edx] // format ptr

    push edi
    push edi
    push esi
    push esi
    push ecx

    cmp esi, edi
    jne _validate_fail
    mov eax, [ebp + 24] // 5: &pass_count
    inc dword ptr [eax] // (*pass_count)++
    mov eax, 1

  _validate_fail:
    push dword ptr [edx + 4*eax] // format
    call printf
    add esp, 4*6


    push offset MSG_EMPTY
    call printf
    add esp, 4

    pop edi
    pop esi
    mov esp, ebp
    pop ebp
    ret

.section .text
print_stats: // print_stats(test_count, test_pass)
    push ebp
    mov ebp, esp
    sub esp, 4*1
    // ebp - 4: test_fail

    mov eax, [ebp + 8]  // 1: eax := test_count
    mov ecx, [ebp + 12] // 2: ecx := test_pass

    // test fail: [ebp - 4] := eax - ecx
    mov dword ptr [ebp - 4], eax
    sub dword ptr [ebp - 4], ecx

    // test fail percent %6.2lf %
    sub esp, 4*2 // allocate place for output
    push esp
    push eax
    push dword ptr [ebp - 4]
    call _percent_calc // _percent_calc(test_fail, test_count, &output)
    add esp, 4*3

    // test_fail
    push dword ptr [ebp - 4]

    // test pass percent %6.2lf %
    sub esp, 4*2 // allocate place for output
    push esp
    push eax
    push ecx
    call _percent_calc
    add esp, 4*3

    push ecx
    push eax
    push offset MSG_STAT
    call printf // printf(MSG_STAT, test_count, test_pass, [64bit] test_pass_percent, test_fail, [64bit] test_fail_percent)
    add esp, 4*3

    mov esp, ebp
    pop ebp
    ret


HUNDRED: .int 100
_percent_calc:
    // output: *edi, input esi, edx, calc: *edi = (double) ((esi / edx) * 100)
    push ebp
    mov ebp, esp
    push edi
    // [ebp + 8]: 1. value
    // [ebp + 12]: 2. divider
    // [ebp + 16]: 3. target
    mov edi, [ebp + 16]

    fild dword ptr [ebp + 8] // st(): value, ...
    fild dword ptr [ebp + 12]// st(): divider, value, ...
    fdivp //st(1), st(0)     // st(): value/divider, ...
    fild dword ptr [HUNDRED] // st(): 100, value/divider, ...
    fmulp                    // st(): (value/divider)*100, ...
    fstp qword ptr [edi]     // st(): ...

    pop edi
    mov esp, ebp
    pop ebp
    ret
