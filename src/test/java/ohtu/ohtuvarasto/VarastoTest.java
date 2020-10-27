package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto, varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void kakkoskonstruktoriLuoTyhjanVaraston() {
        varasto2 = new Varasto(10, 0);
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kakkoskonstruktoriLuoVajaanVaraston() {
        varasto2 = new Varasto(10, 8);
        assertEquals(8, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kakkoskonstruktoriEiLuoNegatiivistaSaldoa() {
        varasto2 = new Varasto(10, -1);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kakkoskonstruktoriEiLuoYlisuurtaSaldoa() {
        varasto2 = new Varasto(10, 11);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kakkoskonstruktoriEiLuoNegatiivisenKokoistaVarastoa() {
        varasto2 = new Varasto(-1, 0);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudelleVarastolleEiNegatiivistaTilavuutta() {
        varasto2 = new Varasto(-1);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void NegatiivinenisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-1);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ylisuuriLisaysEiLisaaSaldoaLiikaa() {
        varasto.lisaaVarastoon(11);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void negatiivisenOttaminenEiMuutaSaldoa() {
        varasto.otaVarastosta(-1);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ylisuuriOttoEiPalautaYlisuurtaMaaraa() {
        varasto.lisaaVarastoon(8);
        double test = varasto.otaVarastosta(10);
        assertEquals(8, test, vertailuTarkkuus);
    }

    @Test
    public void toStringTest(){
        String test = varasto.toString();
        String expected = "saldo = 2.0, vielä tilaa 10.0";
        assertEquals(test.equals(expected), true);
    }

}