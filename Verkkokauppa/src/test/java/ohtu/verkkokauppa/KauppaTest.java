package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    Pankki pankki;
    Varasto varasto;
    Viitegeneraattori viite;
    Kauppa k;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        varasto = mock(Varasto.class);
        viite = mock(Viitegeneraattori.class);
        k = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {

        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava k

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {

        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava k

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    // aloitetaan asiointi, koriin lisätään kaksi eri tuotetta, joita varastossa on
    // ja suoritetaan ostos, varmista
    // että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla
    // ja summalla
    @Test
    public void kaupastaVoiOstaaTuotteita() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(15);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kaakao", 3));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 8);
    }

    // aloitetaan asiointi, koriin lisätään kaksi samaa tuotetta, jota on varastossa
    // tarpeeksi ja suoritetaan ostos, varmista että kutsutaan pankin metodia
    // tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
    @Test
    public void kaupastaOstetaanMontaSamaa() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 10);
    }

    // aloitetaan asiointi, koriin lisätään tuote, jota on varastossa tarpeeksi
    // ja tuote joka on loppu ja suoritetaan ostos, varmista että kutsutaan
    // pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
    @Test
    public void kaupastaEiVoiOstaaLoppunutta() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kaakao", 3));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }

    // varmista, että metodin aloitaAsiointi kutsuminen nollaa edellisen
    // ostoksen tiedot (eli edellisen ostoksen hinta ei näy uuden ostoksen
    // hinnassa),
    @Test
    public void aloitaAsiointiNollaaOstokset() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("elsa", "54321");

        verify(pankki).tilisiirto("elsa", 42, "54321", "33333-44455", 5);
    }

    // varmista, että k pyytää uuden viitenumeron jokaiselle
    // maksutapahtumalle
    @Test
    public void kauppaPyytääUudenViitenumeronJokaiselleOstokselle() {
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(5)).thenReturn(10);
        when(varasto.haeTuote(5)).thenReturn(new Tuote(5, "maito5", 5));
        when(varasto.saldo(3)).thenReturn(10);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(1, "maito3", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(5);
        k.tilimaksu("elsa", "1111");

        verify(viite, times(1)).uusi();

        k.aloitaAsiointi();
        k.lisaaKoriin(5);
        k.tilimaksu("elsa2", "111");

        // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
        // on kutsuttu kaksi kertaa
        verify(viite, times(2)).uusi();

        k.aloitaAsiointi();
        k.lisaaKoriin(5);
        k.tilimaksu("elsa3", "11");

        // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
        // on kutsuttu kolme kertaa
        verify(viite, times(3)).uusi();
    }

    @Test
    public void poistaKorista() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.poistaKorista(1);
        verify(varasto, times(1)).palautaVarastoon(anyObject());
    }
}
