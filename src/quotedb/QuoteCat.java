/*
 * Name, Type (topic/person), File Location, Length?
*/
package quotedb;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class QuoteCat {
  
  private String name;
  private char type; // T = Topic, P = Person
  public ArrayList<Quote> quotes = new ArrayList();
  public static ArrayList<QuoteCat> categories = new ArrayList();
  public static File xmlLocation = new File("quotes.xml");

  public QuoteCat(String name, char type){
    this.name = name;
    this.type = type;
    this.quotes = new ArrayList<Quote>();
    categories.add(this);
  }
  
  public void addQuote(Quote quote){
    this.quotes.add(quote);
  }
  
  public void removeQuote(Quote quote){
    this.quotes.remove(quote);
  }
  
  public void dispQuote(int index){
    Quote dispQ = (Quote) this.quotes.get(index);
  }
  
  public void remove(){
    categories.remove(this);
  }
  
  public void outputQuotes(){
    for(int i=0; i<this.quotes.size(); i++){
      Quote temp = (Quote) this.quotes.get(i);
      System.out.println("Quote: " + temp.getText());
      System.out.println("Author: " + temp.getAuthor());
      System.out.println("Source: " + temp.getSource());
    }
  }
  
  public static String[] listCats() {
    ArrayList<String> arrayList = new ArrayList();
    for(int i=0;i<categories.size();i++){
      arrayList.add(categories.get(i).getName());
    }
    return arrayList.toArray(new String[arrayList.size()]);
  }
  
  public static boolean writeXML(){
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.newDocument();
      Element root = doc.createElement("QuoteCategories");
      doc.appendChild(root);
      
      for(int i=0;i<categories.size();i++) {
        QuoteCat cat = categories.get(i);
        Element category = doc.createElement("Category");
        category.setAttribute("name", cat.getName());
        category.setAttribute("type", String.valueOf(cat.getType()));
        root.appendChild(category);
        for(int j=0;j<cat.quotes.size();j++) {
          Quote q = cat.quotes.get(j);
          Element quote = doc.createElement("Quote");
          quote.setAttribute("id", Integer.toString(j));

          Element text = doc.createElement("Text");
          text.appendChild(doc.createTextNode(q.getText()));

          Element author = doc.createElement("Author");
          author.appendChild(doc.createTextNode(q.getAuthor()));

          Element source = doc.createElement("Source");
          source.appendChild(doc.createTextNode(q.getSource()));

          category.appendChild(quote);
          quote.appendChild(text);
          quote.appendChild(author);
          quote.appendChild(source);
        }
      }
      
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      DOMSource domSource = new DOMSource(doc);
      StreamResult streamResult = new StreamResult(xmlLocation);

      transformer.transform(domSource, streamResult);
      
      System.out.println("File saved to specified path!");
      return true;
    } catch (Exception e) {
      System.out.println(e.toString());
      return false;
    }
  }
  
  public static boolean readFile(File xmlFile){
    xmlLocation = xmlFile;
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(xmlFile);
      
      NodeList categories = doc.getElementsByTagName("Category");
      for (int i=0; i<categories.getLength(); i++) {
        Element category = (Element) categories.item(i);
        String name = category.getAttribute("name");
        char type = category.getAttribute("type").charAt(0);
        QuoteCat currCat = new QuoteCat(name, type);
        NodeList quotes = category.getElementsByTagName("Quote");
        for (int j=0; j<quotes.getLength(); j++) {
          Element quote = (Element) quotes.item(j);
          NodeList quoteContents = quote.getChildNodes();
          //Empty node of newline between each actual text element for some reason
          String text = quoteContents.item(1).getTextContent();
          String author = quoteContents.item(3).getTextContent();
          String source = quoteContents.item(5).getTextContent();
          new Quote(text, author, source, currCat);
        }
      }
      return true;
    }
    catch (Exception e) {
      System.out.println(e.toString());
      return false;
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public char getType() {
    return type;
  }

  public void setType(char type) {
    this.type = type;
  }
}