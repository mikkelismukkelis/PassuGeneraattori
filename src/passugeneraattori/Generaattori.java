
package passugeneraattori;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Miki
 */
public class Generaattori {
    private Asetukset asetukset;
    private int merkkimaaraKayttoliittymasta;

    public Generaattori() {
        this.asetukset = new Asetukset();
        this.merkkimaaraKayttoliittymasta = 0;
    }
    
        
    public void setMerkkimaara(int uusimerkkimaara) {
        this.merkkimaaraKayttoliittymasta = uusimerkkimaara;
    }
    
    public int getMerkkimaaraKayttoliittymasta() {
        return this.merkkimaaraKayttoliittymasta;
    }

    
    public String generoiSatunnainen() {
        ArrayList<String> kaytettavatMerkit = asetukset.getKaikkiMerkit();
        int merkkimaara = 0;
        String SatunnainenSalasana = "";
        
        if (this.merkkimaaraKayttoliittymasta <= 0) {
            merkkimaara = Integer.parseInt(asetukset.getMerkkimaaraKonffista());
        } else {
            merkkimaara = this.merkkimaaraKayttoliittymasta;
        }

        for(int i=0; i<merkkimaara; i++) {
            String lisattava = kaytettavatMerkit.get(randomInt());
            SatunnainenSalasana += lisattava;
        }

        return SatunnainenSalasana;
    }
    
    public int randomInt() {
        int merkkimaara = asetukset.getKaikkiMerkit().size();
        int satunnaisnumero = ThreadLocalRandom.current().nextInt(0, merkkimaara);
        return satunnaisnumero;
    }
    
    public String generoiSelko() {
        String adjekstiivi = asetukset.getAdjektiivi();
        String substantiivi = asetukset.getSubstantiivi();
        String numerot = asetukset.getNumerot();
        String erikoismerkit = asetukset.getErikoismerkit();
        
        Random random = new Random();
        int index1 = random.nextInt(erikoismerkit.length());
        int index2 = random.nextInt(numerot.length());
           
        return adjekstiivi + erikoismerkit.charAt(index1) + substantiivi + numerot.charAt(index2);
    }
    
}
