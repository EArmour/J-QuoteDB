/*
 * Text, Author, Source (optional), Category
 * 
*/
package quotedb;
import javax.swing.*;

public class Quote {
  
  private String text;
  private String author;
  private String source;
  private QuoteCat category;

  public Quote(String text, String author, String source, QuoteCat category){
    this.text = text;
    this.author = author;
    this.source = source;
    this.category = category;
    category.quotes.add(this);
  }

  public String getText() {
    return text;
  }

  public String getAuthor() {
    return author;
  }

  public String getSource() {
    return source;
  }

  public QuoteCat getCategory() {
    return category;
  }
  
  public void test(){
    System.out.println(QuoteCat.listCats()[0]);
  }
}