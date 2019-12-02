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
public class Nollaa implements Komento {

    TextField tuloskentta;
    TextField syotekentta;
    Button nollaa;
    Button undo;
    Sovelluslogiikka sovellus;
    int vanhaSyote;
    int vanhaTulos;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.vanhaSyote = 0;
        this.vanhaTulos = 0;

    }

    @Override
    public void peru() {
        this.sovellus.setTulos(vanhaTulos);
        this.tuloskentta.setText(this.sovellus.tulos() + "");
        this.syotekentta.setText(vanhaTulos + "");
    }

    @Override
    public void suorita() {
        vanhaSyote = Integer.parseInt(this.syotekentta.getText());
        vanhaTulos = Integer.parseInt(this.tuloskentta.getText());
        this.tuloskentta.clear();
        this.syotekentta.clear();
        this.sovellus.nollaa();
    }

}
