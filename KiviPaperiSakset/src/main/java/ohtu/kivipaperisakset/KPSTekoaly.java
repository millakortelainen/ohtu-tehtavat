package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KPSPeli {
    private Scanner scanner;

    KPSTekoaly(Scanner scanner) {
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
        Tekoaly ai = new Tekoaly();
        String tokanSiirto = ai.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        return tokanSiirto;
    }
}