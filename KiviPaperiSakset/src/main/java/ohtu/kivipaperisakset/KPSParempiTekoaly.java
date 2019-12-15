package ohtu.kivipaperisakset;

import java.util.Scanner;


// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KPSPeli {

    private Scanner scanner;

    public KPSParempiTekoaly(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    protected String ensimmainenSiirto() {
        System.out.print("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = scanner.nextLine();
        return ekanSiirto;
    }

    @Override
    protected String toinenSiirto() {
        TekoalyParannettu tekoaly = new TekoalyParannettu(20);
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        return tokanSiirto;
    }
}
