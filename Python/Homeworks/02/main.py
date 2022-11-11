# Nev: Barna Gergely
# Neptun: FJKXGG
# h: h144988

def nyertes_korok(listA = None, listB = None):
    if listA is None or listB is None or len(listA) != len(listB):
        return -1

    adamNyer = 0
    for elem in range(len(listA)):
        if listA[elem] > listB[elem]:
            adamNyer = adamNyer + 1
    return adamNyer