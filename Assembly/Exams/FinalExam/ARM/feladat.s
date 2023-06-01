.syntax unified
.cpu cortex - a7

.text

/*
r0 - input
r1 - length
r2 - limit
r3 - output

r4 - 4
r5 - count
r6 - idx
r7 - current
r8 - idx * 4, current * current, current * 2
r9 - count * 4
r10 - 2

*/
.global feladatCopyCount
feladatCopyCount:
  push { r4 - r10 , lr }  // prológus
    mov r4, #4  // r4 = 4
    mov r5, #0  // r5 = count = 0
    mov r6, #0  // r6 = idx = 0
  
    for:
      cmp r6, r1
      bge forend  // kilépési feltétel: ha idx >= length , ugorj ki a forból
    
    
      mul r8, r6, r4    // r8 = idx * 4
      ldr r7, [r0, r8]  // r7 = input[idx]

      // if ((current * current) > limit)
      mul r8, r7, r7  // r8 = current * current
      cmp r8, r2
      ble iffalse     // if current * current 

        mul r9, r5, r4  // r9 = count * 4
        mov r10, #2     // r10 = 2
        mul r8, r7, r10  // r8 = current * 2
        str r8, [r3, r9]  // output[count*4] = current * 2

        add r5, r5, #1    // count++

      iffalse:

      add r6, r6, #1    // idx++
      b for             // ha ide ért a végrehajtás vissza ugrik a for elejére
    forend:

    mov r0, r5  // Visszatérési érték beállítása count-ra
  pop { r4 - r10 , pc } // epilógus

