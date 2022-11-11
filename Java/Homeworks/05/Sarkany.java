public class Sarkany {
    private int eletero;
    private boolean ehes;


    public int getEletero() {
        return eletero;
    }
    public boolean isEhes() {
        return ehes;
    }



    public Sarkany() {
        this.eletero = 100;
        this.ehes = true;
    }
    public Sarkany(int eletero, boolean ehes) {
        if (eletero < 0){
            this.eletero = 0;
        }else {
            this.eletero=eletero;
        }

            this.ehes = true;


    }


    public void setEletero(int ujEletero){
        if (ujEletero<0) {
            this.eletero = 0;
            System.err.println("megolted a sarkanyt");
        } else {
            this.eletero = ujEletero;
        }
    }
    public void setEhes(boolean ujEhes){
        if (this.eletero < 50){
            this.ehes=true;
        } else {
            this.ehes=ujEhes;
        }
    }




    public String toString() {
        if (this.ehes==true){
            return "A sarkany eletereje " + this.eletero + ", es jelenleg rettenetesen ehes.";
        }
        return "A sarkany eletereje " + this.eletero + ", es jelenleg veletlenul nem ehes.";
    }



    public void eszik(int szam){
        if (szam<=0){
            System.err.println("en a helyedben nem eheztetnek egy sarkanyt");
        } else {
            this.eletero+=szam;
        }

    }

    public boolean vajonElMeg(){
        if (this.eletero>0){
            return true;
        } else {
            return false;
        }

    }



}
