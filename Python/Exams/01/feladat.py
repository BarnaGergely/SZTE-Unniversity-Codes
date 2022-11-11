# Nev: Barna Gergely
# Neptun: FJKXGG
# h: h144988

def ketszer(parameter):
    if isinstance(parameter, str):
        lista = parameter.split(", ")

        elemek = dict()
        for elem in lista:
            if elemek.get(elem) == None:
                elemek[elem] = 1
            else:
                elemek[elem] = elemek[elem] + 1

        vissza = list()
        for kulcs in elemek:
            if elemek[kulcs] == 2:
                vissza.append(kulcs)
        return vissza

class Temeto:
    def __init__(self, nev, szam = 10):
        self.nev = nev

        if szam <= 10:
            self._sirok_szama = 10
        else:
            self._sirok_szama = szam

        self.szellemek = list()

    @property
    def sirok_szama(self):
        return self._sirok_szama

    @sirok_szama.setter
    def sirok_szama(self, szam):
        if szam <= 10:
            self._sirok_szama = 10
        else:
            self._sirok_szama = szam

    def __str__(self):
        if len(self.szellemek) == 0:
            ertek = "nem kiserti szellem"
        else:
            ertek= str(len(self.szellemek)) + " szellem kiserti"
        return f"A {str(self.nev)} temetoben {str(self._sirok_szama)} sir van, es {ertek}"

    def __iadd__(self, other):
        lista = self.szellemek
        if isinstance(other, str):
            if lista.count(other) == 0:
                lista.append(other)
            else:
                lista[lista.index(other)] = lista[lista.index(other)]+"1"
                lista.append(other+"2")
            return lista
        else:
            raise ValueError("Nem szellem")
    def __eq__(self, other):
        return self.nev == other.nev and self.sirok_szama == other.sirok_szama