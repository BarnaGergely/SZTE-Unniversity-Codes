# Nev: Barna Gergely
# Neptun: FJKXGG
# h: h144988

def szekem(azonosito):
    sor = azonosito // 14
    temp = azonosito % 14
    if 0 < temp <= 7:
        oldal = "jobb"
        szam = (temp * (-1)) + 8
        sor = sor + 1
    elif temp == 0:
        oldal = "bal"
        szam = 7
    else:
        oldal = "bal"
        szam = temp - 7
        sor = sor + 1
    return str(sor) + ". sor, " + str(oldal) + " " + str(szam) + ". szek"
