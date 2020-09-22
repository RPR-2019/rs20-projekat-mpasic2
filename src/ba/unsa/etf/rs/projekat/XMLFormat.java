package ba.unsa.etf.rs.projekat;

import ba.unsa.etf.rs.projekat.Candidats;
import ba.unsa.etf.rs.projekat.VotingDAO;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.function.Function;

public class XMLFormat {
    private ArrayList<Candidats> candidats = new ArrayList<>();
    private ArrayList<Function> functions = new ArrayList<>();
    private VotingDAO baza = new VotingDAO();


    public void zapisi(File file)  {
        String help="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";

        if(baza.getCandidats().size()==0)
            help+="<kandidati/>";
        else {
            help += "<kandidati>\n";

                help += "<predsjednici>\n";

                for (int i = 0; i < baza.getPresidents().size(); i++) {
                    if (baza.getPresidents().get(i) == baza.getWinnerPresident()) {
                        help += "<pobjednik> <ime i prezime> \n" + baza.getPresidents().get(i).getName() + " " + baza.getPresidents().get(i).getLastname() + "\n</ime i prezime> </pobjednik>\n";
                    } else
                        help += " <ime i prezime> \n" + baza.getPresidents().get(i).getName() + " " + baza.getPresidents().get(i).getLastname() + "\n</ime i prezime>\n";

                }



                help += "<potpredsjednici>\n";

                for (int i = 0; i < baza.getUnderPresidents().size(); i++) {
                    if (baza.getUnderPresidents().get(i) == baza.getWinnerUnderPresident()) {
                        help += "<pobjednik> <ime i prezime> \n" + baza.getUnderPresidents().get(i).getName() + " " + baza.getUnderPresidents().get(i).getLastname() + "\n</ime i prezime> </pobjednik>\n";
                    } else
                        help += " <ime i prezime> \n" + baza.getUnderPresidents().get(i).getName() + " " + baza.getUnderPresidents().get(i).getLastname() + "\n</ime i prezime>\n";

                }



                help += "<zamjenici>\n";

                for (int i = 0; i < baza.getDeputy().size(); i++) {
                    if (baza.getDeputy().get(i) == baza.getWinnerDeputy()) {
                        help += "<pobjednik> <ime i prezime> \n" + baza.getDeputy().get(i).getName() + " " + baza.getDeputy().get(i).getLastname() + "\n</ime i prezime> </pobjednik>\n";
                    } else
                        help += " <ime i prezime> \n" + baza.getDeputy().get(i).getName() + " " + baza.getDeputy().get(i).getLastname() + "\n</ime i prezime>\n";

                }

            help += "</kandidati>\n";
        }

        if(baza.getUsers().size()==0)
            help+="<glasaci/>";
        else{
            help+="<glasac>\n";
            for(int i=0;i<baza.getUsers().size();i++){
                help+="<galsac> <broj licne i jmbg>\n" + baza.getUsers().get(i).getCardNumber() + " " + baza.getUsers().get(i).getJmbg() + "\n</galsac> </broj licne i jmbg>\n";
            }
            help+="</glasac>";
        }



        PrintWriter out = null;
        try {
            out = new PrintWriter(file.getName());
            out.print(help);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}