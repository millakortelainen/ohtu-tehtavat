
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int DEFKAPASITEETTI = 5, // aloitustalukon koko
            DEFUUSIKAPASITEETTI = 5; // luotava uusi taulukko on
    // näin paljon isompi kuin vanha
    private int uusiKapasiteetti; // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukonLuvut; // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLkm; // Tyhjässä joukossa alkioiden_määrä on nolla.

    // Konstruktori ilman parametreja luo 5 pituisen taulun
    public IntJoukko() {
        this.joukonLuvut = new int[5];
        alkioidenLkm = 0;
        this.uusiKapasiteetti = DEFUUSIKAPASITEETTI;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        this.joukonLuvut = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.uusiKapasiteetti = DEFUUSIKAPASITEETTI;

    }

    public IntJoukko(int kapasiteetti, int uusiKapasiteetti) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Joukon kapaisteetti täytyy olla suurempaa kuin nolla.");// heitin vaan
                                                                                                         // jotain :D
        }
        if (uusiKapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Joukon uusi kapasiteetti täytyy olla suurempaa kuin nolla.");// heitin
                                                                                                              // vaan
                                                                                                              // jotain
                                                                                                              // :D
        }
        joukonLuvut = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.uusiKapasiteetti = uusiKapasiteetti;

    }

    public boolean lisaa(int luku) {

        if (kuuluu(luku)) {
            return false;
        }
        joukonLuvut[alkioidenLkm] = luku;
        alkioidenLkm++;
        taulukonHallinta();
        return true;
    }

    public void taulukonHallinta() {
        if (taulukkoOnTäynnä()) {
            joukonLuvut = kopioiTaulukko(joukonLuvut, new int[joukonLuvut.length + uusiKapasiteetti]);
        }
    }

    public boolean kuuluu(int luku) {

        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukonLuvut[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int poistettavanIndeksi = etsiLuvunIndeksiTaulukosta(luku);

        if (poistettavanIndeksi == -1) {
            return false;
        }

        poistaTaulukostaYksiAlkio(poistettavanIndeksi);
        return true;

    }

    private void poistaTaulukostaYksiAlkio(int poistettavanAlkionIndeksi) {
        for (int i = poistettavanAlkionIndeksi; i < alkioidenLkm; i++) {
            if (i == alkioidenLkm - 1) {
                joukonLuvut[i] = 0;
            }
            joukonLuvut[i] = joukonLuvut[i + 1];
        }
        alkioidenLkm--;
    }

    private int etsiLuvunIndeksiTaulukosta(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (joukonLuvut[i] == luku) {
                return i;
            }
        }
        return -1;
    }

    private int[] kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
        return uusi;

    }

    private boolean taulukkoOnTäynnä() {
        return this.alkioidenLkm % this.joukonLuvut.length == 0;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + joukonLuvut[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += joukonLuvut[i];
                tuotos += ", ";
            }
            tuotos += joukonLuvut[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = joukonLuvut[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }

        return z;
    }

}
