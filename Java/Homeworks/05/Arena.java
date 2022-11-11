public class Arena {
    private final Sarkany ellenfel;
    private Hos hosok[];

    public Arena(Sarkany ellenseg, int hosokmaxszama) {
        this.ellenfel = ellenseg;
        if (hosokmaxszama<1){
            hosokmaxszama = 1;
        }
        hosok = new Hos[hosokmaxszama];
    }

    public boolean hosErkezik(Hos hos){
        for (int i = 0; i < hosok.length; i++) {
            if (hosok[i] == null){
                hosok[i] = hos;
                return true;
            }
        }
        System.err.println("az arena megtelt");
        return false;
    }
    public boolean gyoztunk(){
        if (ellenfel.vajonElMeg() == true) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hosTamad(Hos hos){
        if (hos.vajonElMeg() == false){
            return false;
        } else if (ellenfel.vajonElMeg() == false){
            return true;
        } else {
            ellenfel.setEletero(ellenfel.getEletero()-hos.getTamadas());

            if (ellenfel.vajonElMeg() == false){
                hos.setSikerSzam(hos.getSikerSzam()+1);
                return true;
            } else {
                ellenfel.eszik(hos.getEletEro());
                hos.setEletEro(0);
                return false;
            }

        }
    }

    public int altalanosTamadas () {
        int halottak = 0;
        for (int i = 0; i < hosok.length; i++) {
            if (hosok[i] != null) {
                if(!hosTamad(hosok[i])){
                    halottak ++;
                }
            }
            if (!ellenfel.vajonElMeg()){
                break;
            }
        }
        return halottak;
    }

    public int eroTamadas(){

        int halottak = 0;
        int legnagyobbHos = -1;

        for (int i = 0; i < hosok.length; i++) {
            for (int j = 0; j < hosok.length; j++) {

                if (hosok[j] != null) {
                    if(legnagyobbHos == -1){
                        if (hosok[j].vajonElMeg()) {
                            legnagyobbHos = j;
                        }
                    } else {
                        if (hosok[j].vajonElMeg() == true && hosok[legnagyobbHos].getTamadas() < hosok[j].getTamadas()) {
                            legnagyobbHos = j;
                        }
                    }
                }
            }

            if (legnagyobbHos == -1){
                return halottak;
            }

            hosTamad(hosok[legnagyobbHos]);
            if (ellenfel.vajonElMeg()) {
                halottak++;
            } else {
                break;
            }

            legnagyobbHos = -1;
        }
        return halottak;
    }

    public int strategiaiTamadas(){
        int halottak = 0;
        for (int i = 0; i < hosok.length; i++) {
            if (hosok[i] != null) {
                if (hosok[i].getTamadas() > hosok[i].getEletEro()){
                    if(!hosTamad(hosok[i])){
                        halottak++;
                    }
                }
            }
            if (!ellenfel.vajonElMeg()){
                break;
            }
        }
        return halottak;
    }

}
