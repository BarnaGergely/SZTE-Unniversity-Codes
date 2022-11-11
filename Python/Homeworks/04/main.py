# Nev: Barna Gergely
# Neptun: FJKXGG
# h: h144988

class Palack:
    def  __init__(self, ital, max_urtartalom, _jelenlegi_urtartalom = 1):
        self.ital = ital
        self.max_urtartalom = max_urtartalom
        self._jelenlegi_urtartalom = _jelenlegi_urtartalom

    @property
    def jelenlegi_urtartalom(self):
        return self._jelenlegi_urtartalom

    @jelenlegi_urtartalom.setter
    def jelenlegi_urtartalom(self, ertek):
        if ertek > self.max_urtartalom:
            self._jelenlegi_urtartalom = self.max_urtartalom
        elif ertek == 0:
            self.ital = None
        else:
            self._jelenlegi_urtartalom = ertek

    def suly(self):
        return self.max_urtartalom/35 + self._jelenlegi_urtartalom
    def __str__(self):
        return "Palack, benne levo ital: "+self.ital+", jelenleg " + str(self._jelenlegi_urtartalom) + " ml van benne, maximum " + str(self.max_urtartalom) + " ml fer bele."

    def __eq__(self, other):
        if not isinstance(other, Palack):
            return False
        else:
            if self.ital == other.ital and self._jelenlegi_urtartalom == other.jelenlegi_urtartalom and self.max_urtartalom == other.max_urtartalom:
                return True
            else:
                return False
    def __iadd__(self, other):
        if not isinstance(other, Palack):
            print("WTF")
        else:
            if self.ital == other.ital or other.ital == None:
                self._jelenlegi_urtartalom += other.jelenlegi_urtartalom
                if self._jelenlegi_urtartalom > self.max_urtartalom:
                    self._jelenlegi_urtartalom = self.max_urtartalom
            elif self.ital != other.ital and other.ital != None:
                self._jelenlegi_urtartalom += other.jelenlegi_urtartalom
                if self._jelenlegi_urtartalom > self.max_urtartalom:
                    self._jelenlegi_urtartalom = self.max_urtartalom
                self.ital = "keverek"
        return self

class VisszavalthatoPalack(Palack):
    def __init__(self,ital, max_urtartalom, _jelenlegi_urtartalom, palackdij = 25):
        super().__init__(ital, max_urtartalom, _jelenlegi_urtartalom)
        self.palackdij = palackdij

    def __str__(self):
        return  "Visszavalthato"+Palack.__str__(self)






alma = Palack("lore", 2)
alma2 = VisszavalthatoPalack("loe", 2, 3)

print(alma2)