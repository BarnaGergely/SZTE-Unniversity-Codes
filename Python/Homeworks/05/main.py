# Nev:utvonal=Nonely
# Neptun: FJKXGG
# h: h144988

def legnagyobb_stadion(utvonal):
    with open(utvonal, "r") as file:
        tartalom = file.readlines()

        if tartalom is None or (tartalom == ""):
            with open("legnagyobb.txt", "w") as f:
                f.write("Nincs (Nincs)\n")
            return

        legnagyobb = -1
        legnagyobbstadium = ""
        legnagyobbvaros = ""
        ures = True
        for sor in tartalom:
            sor = sor.rstrip()
            sor = sor.rsplit(",")
            if (sor[0] != "Team"):
                if (int(sor[4]) != 0):
                    ures = False

                if (int(sor[4]) > legnagyobb):
                    legnagyobb = int(sor[4])
                    legnagyobbstadium = sor[3].rstrip()
                    legnagyobbvaros = sor[2].rstrip()

    with open("legnagyobb.txt", "w") as f:
        if ures:
            f.write("Nincs (Nincs)\n")
        else:
            f.write(legnagyobbstadium + " (" + legnagyobbvaros + ")")


def osszes_arena(utvonal):
        with open(utvonal, "r") as file:
            with open("arena_park.csv", "w") as f:
                f.write("Stadium,City,Country,Big" + "\n")

            tartalom = file.readlines()
            for sor in tartalom:
                sor = sor.rstrip()
                sor = sor.rsplit(",")
                sor[3] = sor[3].rstrip()
                sor[2] = sor[2].rstrip()
                sor[7] = sor[7].rstrip()
                anera = sor[3][:-6:-1]
                if (anera == "anerA"):
                    if (int(sor[4]) > 50000):
                        big = "True"
                    else:
                        big = "False"
                    with open("arena_park.csv", "a") as f1:
                        f1.write(sor[3] + "," + sor[2] + "," + sor[7] + "," + big + "\n")

def osszes_park(utvonal):
    with open(utvonal, "r") as file:

        tartalom = file.readlines()
        for sor in tartalom:
            sor = sor.rstrip()
            sor = sor.rsplit(",")
            sor[3] = sor[3].rstrip()
            sor[2] = sor[2].rstrip()
            sor[7] = sor[7].rstrip()
            anera = sor[3][:-5:-1]
            if anera == "kraP":
                if int(sor[4]) > 20000:
                    big = "True"
                else:
                    big = "False"
                with open("arena_park.csv", "a") as f1:
                    f1.write(sor[3] + "," + sor[2] + "," + sor[7] + "," + big + "\n")
def varosok_szama(utvonal, *args):
    if len(args) == 0:
        raise Exception("Nincs megadva egy orszag sem!")
        return
    orszagok = list(args)
    with open("varosok.txt", "w") as file:
        file.write("")
    for orszag in orszagok:
        with open("varosok.txt", "a") as file:
            file.write(orszag+" varosai:\n")
        varosai = dict()
        with open(utvonal, "r") as file:
            tartalom = file.readlines()
            for sor in tartalom:
                sor = sor.rstrip()
                sor = sor.rsplit(",")
                sor[7] = sor[7].rstrip()
                sor[2] = sor[2].rstrip()

                if sor[7]==orszag:
                    varosai[sor[2]] = sor[2]
            varosai = list(varosai)
            varosai.sort()
            with open("varosok.txt", "a") as file:
                for varos in varosai:
                    file.write("\t"+varos+"\n")
        with open("varosok.txt", "a") as file:
            file.write("----------\n")

#legnagyobb_stadion("stadium.csv")
#osszes_arena("stadium.csv")
#osszes_park("stadium.csv")
#try:
#    varosok_szama("stadium.csv")
#except Exception as e:
#    print(e)
