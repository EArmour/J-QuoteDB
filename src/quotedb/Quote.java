package quotedb;

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
    category.addQuote(this);
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
  
  public void remove(){
    category.removeQuote(this);
  }
}