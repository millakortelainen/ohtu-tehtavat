package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Peli {

    public static KPS uusiPeli(String tyyppi) {
        if (tyyppi.equals("a")) {
            return new KPSPelaajaVsPelaaja(new Scanner(System.in));
        }
        if (tyyppi.equals("b")) {
            return new KPSTekoaly(new Scanner(System.in));
        }
        if (tyyppi.equals("c")) {
            return new KPSParempiTekoaly(new Scanner(System.in));
        }

        return null;
    }

}