# Nev: Barna Gergely
# Neptun: FJKXGG
# h: h144988

def hogolyo_csata(list):
    temp = {}
    for kor in list:
        for ember, ertek in kor.items():
            if ember not in temp:
                temp[ember] = {'eldobott_hogolyok': 0, 'talalt': 0, 'fejtalalat': 0}

            for kulcs, ertek in ertek.items():
                if kulcs in temp[ember]:
                    temp[ember][kulcs] += ertek
                else:
                    temp[ember][kulcs] = ertek

    return temp


def hogolyo_pontossag(list):
    for i, e in list.items():
        e['pontossag'] = e.get("talalt") / e.get("eldobott_hogolyok")

    return list
