package ohtu.kivipaperisakset;



public abstract class KPSPeli implements KPS{


    public void pelaa() {
        Tuomari tuomari = new Tuomari();

        while (true) {
            String ekanSiirto = ensimmainenSiirto();
            String tokanSiirto = toinenSiirto();

            if (!onkoOkSiirto(ekanSiirto) || !onkoOkSiirto(tokanSiirto)) {
                break;
            }

            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    protected abstract String ensimmainenSiirto();

    protected abstract String toinenSiirto();

}