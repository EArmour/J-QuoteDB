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
  public static ArrayList<Quote> quotes = new ArrayList();

  public QuoteCat(String name, char type, File file){
    this.name = name;
    this.type = type;
    this.file = file;
  }
  
  public void addQuote(Quote quote, QuoteCat cat){
    cat.quotes.add(quote);
  }
  
  public void dispQuote(QuoteCat cat, int index){
    Quote dispQ = (Quote) cat.quotes.get(index);
    dispQ.test();
  }
  
  public void outputQuotes(QuoteCat cat){
    for(int i=0; i<cat.quotes.size(); i++){
      Quote temp = (Quote) cat.quotes.get(i);
      System.out.println("Quote: " + temp.getText());
      System.out.println("Author: " + temp.getAuthor());
      System.out.println("Source: " + temp.getSource());
    }
  }
  
  public static boolean writeXML(){
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.newDocument();
      
      Element root = doc.createElement("QuoteCategories");
      
      Element category = doc.createElement("Beautiful_Words");
      
      Element quote = doc.createElement("Quote");
      quote.setAttribute("id", "1");
      
      Element text = doc.createElement("Text");
      text.appendChild(doc.createTextNode("This is the text of the quote"));
      
      Element author = doc.createElement("Author");
      author.appendChild(doc.createTextNode("Mr. Author"));
      
      Element source = doc.createElement("Source");
      source.appendChild(doc.createTextNode("Book of Stuff (2899)"));
      
      doc.appendChild(root);
      root.appendChild(category);
      category.appendChild(quote);
      quote.appendChild(text);
      quote.appendChild(author);
      quote.appendChild(source);
      
      TransformerFactory transformerFactory = TransformerFactory.newInstance();  
      Transformer transformer = transformerFactory.newTransformer();  
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      DOMSource domSource = new DOMSource(doc);  
      StreamResult streamResult = new StreamResult(new File("createFile.xml"));  
  
      transformer.transform(domSource, streamResult);  
  
      System.out.println("File saved to specified path!");
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return true;
  }
  
  public static boolean readFile(){
    return true;
  }
}

  