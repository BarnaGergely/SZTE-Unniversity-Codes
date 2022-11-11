public class Hos extends Ember{
    private int tamadas;
    private int sikerSzam;

    public int getSikerSzam() {
        return sikerSzam;
    }

    public int getTamadas() {
        return tamadas;
    }

    public void setSikerSzam(int sikerSzam) {
        if (this.sikerSzam>sikerSzam){
        }else {
            this.sikerSzam = sikerSzam;
        }
    }

    public Hos(String nev, int eletEro, int tamadas, int sikerSzam){
        super(nev, eletEro);
        if (tamadas<0){
            tamadas=0;
        }
        if (sikerSzam<0){
            sikerSzam=0;
        }
        this.tamadas= tamadas;
        this.sikerSzam = sikerSzam;
    }

    @Override
    public String toString() {
        String allapot = "";
        if (this.eletEro>10){
            allapot = "majd kicsattan az egeszsegtol";
        }
        if (this.eletEro<=10 && eletEro>=1){
            allapot =  "atlagos az allapota";
        }
        if (this.eletEro==0){
            allapot =  "halott";
        }

        return "Emberunk neve " + nev + ", es jelenleg " + allapot + ". Ez az ember egy sarkanyolo hos, tamadasa " + this.tamadas + ", es eddig " + this.sikerSzam + "} darab sarkanyt olt meg.";
    }

    public void gyogyul(int mennyitGyogyul){
        this.eletEro += mennyitGyogyul;
    }

    public void edzes(){
        if (vajonElMeg() == true){
            tamadas++;
        }
    }
}
