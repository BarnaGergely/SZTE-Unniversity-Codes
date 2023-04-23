```c
    if (’a’ <= ch && ch <= ’z’) {
        output [idx] = ’A’ + (ch−’a’) ;
    } elseif ( ’A’ <= ch && ch <= ’Z’ ) {
        output [idx] = ’a’ + (ch−’A’);
    } else {
        output [idx] = ch ;
    }



    if(ch < ’a’){
        goto nem kis betu
    }

    if(ch > ’z’){
        goto nem kis betu
    }
output [idx] = ’A’ + (ch−’a’) ;
goto kész

nem kis betu:
    if(ch < ’A’){
        goto nem nagy betu
    }

    if(ch > ’Z’){
        goto nem nagy betu
    }
output [idx] = ’a’ + (ch−’A’);
goto kész

nem nagy betu:
output [idx] = ch ;

kész:

```