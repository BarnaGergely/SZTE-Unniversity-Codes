.intel_syntax noprefix

.text

/*
cd /mnt/d/SZTE/assembly/HF3


Elvárt függvény prototípus (C-ben):
int filterElements(int input[] , int length, int output[]);

*/
.global filterElements
filterElements:
    push ebp
    mov ebp, esp

    # Függvény epilógus
    # regiszterek mentése
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