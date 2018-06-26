
package passugeneraattori;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Miki
 */
public class PassuGeneraattori extends Application {
    
    //alustetaan generaattori ja haetaan ekat salsanat
    Generaattori gene = new Generaattori();
    String selkoSalasana = gene.generoiSelko();
    String satunnaisSalasana = gene.generoiSatunnainen();
    
    //Alustetaan asetukset jotta voidaan näyttää niitä
    Asetukset asetukset = new Asetukset();
    
    //Tehdään leikepöytäkomponentit
    Clipboard leikepoyta = Clipboard.getSystemClipboard();
    ClipboardContent leikepoyttaSisalto = new ClipboardContent();


    @Override
    public void start(Stage ikkuna) {
        
        //nappeja, tekstikenttia yms generointi-ikkunaan

        String jarriPolku = PassuGeneraattori.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        Button generoiSelkoBtn = new Button("Generoi");
        generoiSelkoBtn.setMinWidth(145);
        generoiSelkoBtn.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        
        Button kopioiSelkoBtn = new Button("Kopioi");
        kopioiSelkoBtn.setMinWidth(145);
        kopioiSelkoBtn.setFont(Font.font("Verdana", FontWeight.BOLD, 13));        
        
        Button generoiSatunnainenBtn = new Button("Generoi");
        generoiSatunnainenBtn.setMinWidth(145);
        generoiSatunnainenBtn.setFont(Font.font("Verdana", FontWeight.BOLD, 13));        
        
        Button kopioiSatunnainenBtn = new Button("Kopioi"); 
        kopioiSatunnainenBtn.setMinWidth(145);
        kopioiSatunnainenBtn.setFont(Font.font("Verdana", FontWeight.BOLD, 13));          
        
        TextField selkoField = new TextField(this.selkoSalasana);
        selkoField.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        selkoField.setMaxWidth(300);
        
        TextField satunnainenField = new TextField(this.satunnaisSalasana);
        satunnainenField.setFont(Font.font("Verdana", FontWeight.BOLD, 13));        
        satunnainenField.setMaxWidth(300);

        Separator viiva1 = new Separator();
        Separator viiva2 = new Separator();
        
        //Asetusikkunaan infoa
        
        //Label jarpolku = new Label(jarriPolku.substring(1));
        TextField merkkimaaraField = new TextField(asetukset.getMerkkimaaraKonffista());
        merkkimaaraField.setMaxWidth(40);
        merkkimaaraField.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        
        Label merkkimaaraLabel = new Label("Merkkimäärä: "); 
        merkkimaaraLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        
        Button asetaMerkkimaara = new Button("Aseta"); 
        asetaMerkkimaara.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        
        Label pienetKirjaimetLabel = new Label("Pienet kirjaimet: " + asetukset.getPienetKirjaimet());
        pienetKirjaimetLabel.setFont(Font.font("Verdana",  13)); 
        
        Label isotKirjaimetLabel = new Label("Isot kirjaimet: " + asetukset.getIsotKirjaimet());
        isotKirjaimetLabel.setFont(Font.font("Verdana",13));  
        
        Label numerotLabel = new Label("Numerot: " + asetukset.getNumerot());
        numerotLabel.setFont(Font.font("Verdana",13));         
        
        Label erikoismerkitLabel = new Label("Erikoismerkit: " + asetukset.getErikoismerkit());
        erikoismerkitLabel.setFont(Font.font("Verdana", 13));
        
               
        
        //Generointi -ikkunan asettelut
        BorderPane asettelu = new BorderPane();

	HBox keskiNapit = new HBox();
	keskiNapit.setSpacing(10);
	keskiNapit.getChildren().add(generoiSelkoBtn);
	keskiNapit.getChildren().add(kopioiSelkoBtn);
        keskiNapit.setAlignment(Pos.CENTER);
        
	HBox alaNapit = new HBox();
	alaNapit.setSpacing(10);
	alaNapit.getChildren().add(generoiSatunnainenBtn);
	alaNapit.getChildren().add(kopioiSatunnainenBtn);
        alaNapit.setAlignment(Pos.CENTER);        
        
	VBox keskikombo = new VBox();        
        keskikombo.setSpacing(20);
        keskikombo.setPadding(new Insets(30, 12, 15, 12));
        keskikombo.getChildren().addAll(selkoField, keskiNapit, viiva1, satunnainenField, alaNapit);
        keskikombo.setAlignment(Pos.TOP_CENTER);
        
	HBox merkkimaarahbox = new HBox();
	merkkimaarahbox.setSpacing(10);
        merkkimaarahbox.getChildren().addAll(merkkimaaraLabel, merkkimaaraField, asetaMerkkimaara);
        merkkimaarahbox.setAlignment(Pos.CENTER);
        
	VBox alakombo = new VBox();        
        alakombo.setSpacing(5);
        alakombo.setPadding(new Insets(15, 15, 15, 15));
        alakombo.getChildren().addAll(merkkimaarahbox,viiva2, pienetKirjaimetLabel,isotKirjaimetLabel,numerotLabel, erikoismerkitLabel);
        alakombo.setAlignment(Pos.BOTTOM_CENTER);        
        
        asettelu.setCenter(keskikombo);
        asettelu.setBottom(alakombo);


        //Nappien toimintaa
        
        generoiSelkoBtn.setOnAction((event) -> {
            selkoSalasana = gene.generoiSelko();
            selkoField.setText(selkoSalasana);

        });
        
        kopioiSelkoBtn.setOnAction((event) -> {
            leikepoyttaSisalto.putString(selkoField.getText());
            leikepoyta.setContent(leikepoyttaSisalto);
        });
        
        generoiSatunnainenBtn.setOnAction((event) -> {
            satunnaisSalasana = gene.generoiSatunnainen();
            satunnainenField.setText(satunnaisSalasana);

        });        
        
        kopioiSatunnainenBtn.setOnAction((event) -> {
            leikepoyttaSisalto.putString(satunnainenField.getText());
            leikepoyta.setContent(leikepoyttaSisalto);
        }); 
        
        asetaMerkkimaara.setOnAction((event) -> {
            gene.setMerkkimaara(Integer.parseInt(merkkimaaraField.getText()));
        });       
        
        //lyödään kaikki kasaan
        
        Scene generaattoriScene = new Scene(asettelu, 500, 400);
    
        
        ikkuna.setTitle("Passugeneraattori");
        ikkuna.setScene(generaattoriScene);
        ikkuna.show();
    }


    public static void main(String[] args) {
        launch(PassuGeneraattori.class);
    }
    
}
