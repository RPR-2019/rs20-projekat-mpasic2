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
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

public class XMLFormat {
    private ArrayList<Candidats> candidats = new ArrayList<>();
    private ArrayList<Function> functions = new ArrayList<>();
    private VotingDAO baza = new VotingDAO();

    public void zapisi(File file)  {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document = null;

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();
        } catch (ParserConfigurationException err) {
            err.printStackTrace();
        }

        Element root = document.createElement("Glasanje");
        document.appendChild(root);

        for (Candidats cand : candidats) {
            Element eCand = document.createElement("Kandidat");
            if (cand==baza.getWinnerPresident()) {
                Attr winnerPresident = document.createAttribute("Izabrani predsjednik");
                eCand.setAttributeNode(winnerPresident);
            }
            if (cand==baza.getWinnerUnderPresident()) {
                Attr winnerUnderPresident = document.createAttribute("Izabrani potpredsjednik");
                eCand.setAttributeNode(winnerUnderPresident);
            }
            if (cand==baza.getWinnerDeputy()) {
                Attr winnerDeputy = document.createAttribute("Izabrani zamjenik");
                eCand.setAttributeNode(winnerDeputy);
            }

            Element nameCandidat = document.createElement("Ime i prezime");
            nameCandidat.appendChild(document.createTextNode(cand.getName()+" "+cand.getLastname()));
            eCand.appendChild(nameCandidat);



            root.appendChild(eCand);
        }

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            StreamResult streamResult = new StreamResult(file);
            transformer.transform(source, streamResult);
        } catch(TransformerException err) {
            err.printStackTrace();
        }
    }


}