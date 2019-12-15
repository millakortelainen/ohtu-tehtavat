package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KPSPeli {

    private Scanner scanner;

    public KPSPelaajaVsPelaaja(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    protected String toinenSiirto() {
        System.out.print("Toisen pelaajan siirto: ");
        String tokanSiirto = scanner.nextLine();
        return tokanSiirto;
    }

    @Override
    protected String ensimmainenSiirto() {
        System.out.print("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = scanner.nextLine();
        return ekanSiirto;
    }

}