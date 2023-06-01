.syntax unified
.cpu cortex-a7

.data
    // Előre megadott nagy konstans számok mentése a memóriába, mivel túl nagyon konstans-nak
    
    SZAM1: .int 0xbadc0ffe
    SZAM2: .int 0x11223344
    SZAM3: .int -2048
    

.text

/*
r0 - input
r1 - op
r2 - opArg
r3 - output
r4 - counter
r5 - idx
r6 - value, input[idx]
r7 - 4
r8 - címzés eltolás
r9 - 0x11223344
*/
.global feladatX
feladatX:
    // prológus
    push {r4-r11 , lr}

    mov r7, #4  // r7 = 4
    mov r4, #0  // counter = 0

    mov r5, #0  // idx = 0
    for:
        // for ciklus kilépési feltétele
        mul r8, r5, r7  // r8 = idx * 4
        ldr r6, [r0, r8] // r6 = input[idx], value
        cmp r6, #0
        beq endfor

        // if (op == 0xbadc0ffe)
        ldr r10, =SZAM1
        ldr r10, [r10]
        cmp r1, r10
        bne elseif

            // if (value % opArg != 0)
            sdiv r11, r6, r2    // r11 = value/opArg
            mul r11, r11, r2    // r11 = (value/opArg)*opArg
            sub r11, r6, r11    // r11= value-((value/opArg)*opArg) -> így kell kiszámolni a maradékot ;)
            cmp r11, #0
            beq if11end
                mul r8, r4, r7      // r8 = counter * 4
                str r6, [r3, r8]    // output[counter] = value

                add r4, r4, #1      // counter++
            if11end:
            b if1end

        // else if (op == 0x11223344)
        elseif:
        ldr r10, =SZAM2
        ldr r10, [r10]
        cmp r1, r10
        bne else

            // if (value / 3 < opArg)
            mov r10, #3
            sdiv r11, r6, r10  // r11 = value/3
            cmp r11, r2
            bge if2end
                mul r8, r4, r7      // r8 = counter * 4
                str r6, [r3, r8]    // output[counter] = value

                add r4, r4, #1      // counter++
            if2end:
            b if1end

        else:
            ldr r10, =SZAM3     // r10 = SZAM3 címe
            ldr r10, [r10]      // r10 = -2048
            mul r8, r4, r7      // r8 = counter * 4
            str r10, [r3, r8]   // output[counter] = -2048

            add r4, r4, #1      // counter++
        if1end:
    
        add r5, r5, #1  // idx++
        b for
    endfor:

    mov r0, r4         // counter legyen a visszatérési érték

    // epilógus
    pop {r4-r11 , pc}