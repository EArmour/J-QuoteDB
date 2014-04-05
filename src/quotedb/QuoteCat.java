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
  private File file;
  public ArrayList<Quote> quotes = new ArrayList();
  public static ArrayList<QuoteCat> categories = new ArrayList();

  public QuoteCat(String name, char type, File file){
    this.name = name;
    this.type = type;
    this.file = file;
    this.quotes = new ArrayList<Quote>();
    categories.add(this);
  }
  
  public void addQuote(Quote quote){
    this.quotes.add(quote);
  }
  
  public void dispQuote(int index){
    Quote dispQ = (Quote) this.quotes.get(index);
    dispQ.test();
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
        Element category = doc.createElement(cat.getName());
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
      StreamResult streamResult = new StreamResult(new File("createFile.xml"));

      transformer.transform(domSource, streamResult);
      
      System.out.println("File saved to specified path!");
      return true;
    } catch (Exception e) {
      System.out.println(e.toString());
      return false;
    }
  }
  
  public static boolean readFile(){
    return true;
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

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }
}