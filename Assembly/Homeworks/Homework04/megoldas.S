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