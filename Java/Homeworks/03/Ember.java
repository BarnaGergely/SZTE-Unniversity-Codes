public class Ember {
    protected String nev;
    protected int eletEro;

    public void setEletEro(int eletEro) {
        if (eletEro < 0) {
            this.eletEro = 0;
        } else {
            this.eletEro = eletEro;
        }
    }

    public int getEletEro() {
        return eletEro;
    }

    public String getNev() {
        return nev;
    }

    public Ember(){
        this.nev="ismeretlen";
        this.eletEro = 10;
    }

    public Ember(String nev, int eletEro){
        this.nev=nev;
        if (eletEro<0){
            this.eletEro = -1 * eletEro;
        } else {
            this.eletEro = eletEro;
        }
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

        return "Emberunk neve " + nev + ", es jelenleg " + allapot + ".";
    }

    public boolean vajonElMeg(){
        if (this.eletEro==0){
            return false;
        } else {
            return true;
        }
    }

    public void gyogyul(int mennyitGyogyul){
        if (this.eletEro==0){
            System.err.println("sajnalom, elkestetek");
            return;
        } else {
            this.eletEro += mennyitGyogyul;
        }
    }
}
