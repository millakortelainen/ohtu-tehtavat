/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author kortemil
 */
public class Erotus implements Komento {

    TextField tuloskentta;
    TextField syotekentta;
    Button nollaa;
    Button undo;
    Sovelluslogiikka sovellus;
    int syote;

    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        syote = 0;
    }

    @Override
    public void peru() {

        this.sovellus.plus(syote);
        this.tuloskentta.setText(this.sovellus.tulos() + "");
    }

    @Override
    public void suorita() {
        syote = Integer.parseInt(this.syotekentta.getText());
        this.sovellus.miinus(syote);
        this.tuloskentta.setText(this.sovellus.tulos() + "");
    }
}
