
package passugeneraattori;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Miki
 */
public class Asetukset {
    private Map asetukset;
    private ArrayList<String> adjektiiveja;
    private ArrayList<String> substantiiveja;
    

    public Asetukset() {
        this.asetukset = new HashMap();
        this.adjektiiveja = new ArrayList<>();
        this.substantiiveja = new ArrayList<>();
        
        
        try(Scanner lukija = new Scanner(new File("Asetukset.txt"))) {
            while (lukija.hasNextLine()) {
                String[] asetuspari = lukija.nextLine().split("=");
                this.asetukset.put(asetuspari[0], asetuspari[1]);
            }
        } catch (Exception e) {
            System.out.println("Virhe: " + e.getMessage());
        }        
        
        try(Scanner lukijaA = new Scanner(new File("adjektiiveja.txt"))) {
            while (lukijaA.hasNextLine()) {
                this.adjektiiveja.add(lukijaA.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Virhe: " + e.getMessage());
        }
        
        try(Scanner lukijaS = new Scanner(new File("substantiiveja.txt"))) {
            while (lukijaS.hasNextLine()) {
                this.substantiiveja.add(lukijaS.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Virhe: " + e.getMessage());
        }         
        
    }

    public void tulostaAsetukset() {

        Iterator it = this.asetukset.entrySet().iterator();
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
           // it.remove();
        }
    }
    
    public String getMerkkimaaraKonffista() {
        String merkkimaara = this.asetukset.getOrDefault("merkkimaara", 16).toString();
        //System.out.println("merkit " + merkkimaara);
        return merkkimaara;
    }



    public ArrayList<String> getKaikkiMerkit() {
        ArrayList<String> kaikkiMerkit= new ArrayList<>();
        
        String numerot = this.asetukset.getOrDefault("numerot", "23456789").toString();
        String pienetKirjaimet = this.asetukset.getOrDefault("pienetKirjaimet", "abcdefghjkmnpqrstuvwxyz").toString();
        String isotKirjaimet = this.asetukset.getOrDefault("isotKirjaimet", "ABCDEFGHJKLMNOPQRSTUVWXYZ").toString();
        String erikoismerkit = this.asetukset.getOrDefault("erikoismerkit", "!#$%&()*-<=>?@[]_").toString();

        String kaikkiMerkitString = numerot + pienetKirjaimet + isotKirjaimet + erikoismerkit;
        
        for (int i = 0; i < kaikkiMerkitString.length(); i++) {
            kaikkiMerkit.add(kaikkiMerkitString.substring(i,i+1));
        } 
        
        return kaikkiMerkit;
    }
    
    public String getSubstantiivi() {
        int sanoja = this.substantiiveja.size();
        int satunnaisnumero = ThreadLocalRandom.current().nextInt(0, sanoja);
        String arvottuSana = this.substantiiveja.get(satunnaisnumero);
        String palautettava = arvottuSana.substring(0,1).toUpperCase() + arvottuSana.substring(1).toLowerCase();
        return palautettava;
    }    
    
    public String getAdjektiivi() {
        int sanoja = this.adjektiiveja.size();
        int satunnaisnumero = ThreadLocalRandom.current().nextInt(0, sanoja);
        String arvottuSana = this.adjektiiveja.get(satunnaisnumero);
        String palautettava = arvottuSana.substring(0,1).toUpperCase() + arvottuSana.substring(1).toLowerCase();
        return palautettava;
    }
    
    public String getErikoismerkit() {
        String erikoismerkit = this.asetukset.getOrDefault("erikoismerkit", "!#$%&()*-<=>?@[]_").toString();
        return erikoismerkit;
    }
    
    public String getNumerot() {
        String numerot = this.asetukset.getOrDefault("numerot", "23456789").toString();
        return numerot;
    }
    
     public String getPienetKirjaimet() {
        String pienetKirjaimet = this.asetukset.getOrDefault("pienetKirjaimet", "abcdefghjkmnpqrstuvwxyz").toString();
        return pienetKirjaimet;
    }   
    
     public String getIsotKirjaimet() {
        String isotKirjaimet = this.asetukset.getOrDefault("isotKirjaimet", "ABCDEFGHJKLMNOPQRSTUVWXYZ").toString();
        return isotKirjaimet;
    }      
    
}  

