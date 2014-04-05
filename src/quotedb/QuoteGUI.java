/** Author Name: Evan Armour
 */
package quotedb;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class QuoteGUI extends JFrame {
  
  public static QuoteGUI mainFrame = new QuoteGUI();
  public static int quoteIndex = -1;
  public static QuoteCat currentCat = null;

  public static void main(String[] args) {
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);
    
    QuoteCat qc = new QuoteCat("TestCat", 'P', null);
    Quote q = new Quote("TextS", "Shakespeare", "Source (1101)", qc);
    q = new Quote("TextA","Armour","Source (222)",qc);
    currentCat = qc;
  }
  
  JButton btnPrev = new JButton("<-");
  JButton btnNext = new JButton("->");
  JButton btnAddQ = new JButton("Add Quote");
  JButton btnDeleteQ = new JButton("Delete Quote");
  JButton btnAddC = new JButton("Add Category");
  JButton btnDeleteC = new JButton("Delete Category");
  JList lstCats = new JList(QuoteCat.listCats());
  JTextArea txtQuote = new JTextArea(10, 40);
  JTextField txtAuthor = new JTextField(40);
  JTextField txtSource = new JTextField(40);
  
  public QuoteGUI(){
    //INITIALIZING FRAME AND SETTING PROPERTIES
    setTitle("Quote Database");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(675, 350);
//    txtQuote.setEditable(false);
//    txtAuthor.setEditable(false);
//    txtSource.setEditable(false);

    //GUI BUTTONS IN GRID LAYOUT
    JPanel buttonPanel = new JPanel(new GridLayout());
    buttonPanel.add(btnAddC);
    buttonPanel.add(btnDeleteC);
    buttonPanel.add(btnAddQ);
    buttonPanel.add(btnDeleteQ);
    this.add(buttonPanel, BorderLayout.SOUTH);
    
    //TEXT FIELDS FOR OUTPUT IN FLOW LAYOUT
    JPanel displayPanel = new JPanel(new FlowLayout());
    displayPanel.add(new LabeledComponent("Quote", txtQuote, BorderLayout.WEST, SwingConstants.TOP));
    displayPanel.add(new LabeledComponent("Author", txtAuthor, BorderLayout.WEST));
    displayPanel.add(new LabeledComponent("Source", txtSource, BorderLayout.WEST));
    JPanel navPanel = new JPanel(new BorderLayout());
    navPanel.add(btnPrev, BorderLayout.CENTER);
    navPanel.add(btnNext, BorderLayout.EAST);
    displayPanel.add(navPanel);
    this.add(displayPanel, BorderLayout.CENTER);
    
    //LIST FOR CATEGORY DISPLAY IN GRID LAYOUT
    JPanel catPanel = new JPanel(new FlowLayout());
    catPanel.add(new LabeledComponent("Categories:", lstCats, BorderLayout.NORTH));
    this.add(catPanel, BorderLayout.WEST);
    
    //POPULATE FIELDS
    lstCats.setBackground(Color.GREEN);
    
    //ACTION LISTENERS
    btnAddQ.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        QuoteCat.writeXML();
      }
    });
    
    btnNext.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        if(quoteIndex < currentCat.quotes.size()) {
          quoteIndex++;
          dispQuote(quoteIndex);
        }
      }
    });
    
    btnPrev.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        if(quoteIndex > 0){
          quoteIndex--;
          dispQuote(quoteIndex);
        }
      }
    });
  }
  
  public void dispQuote(int quoteIndex) {
    Quote q = currentCat.quotes.get(quoteIndex);
    txtQuote.setText(q.getText());
    txtAuthor.setText(q.getAuthor());
    txtSource.setText(q.getSource());
  }
  
  class LabeledComponent extends JPanel {
    JLabel label;

    public LabeledComponent(String labelText, Component component, String borderDirection)
    {
      setLayout(new BorderLayout(5, 5));
      this.label = new JLabel(labelText);
      this.label.setHorizontalAlignment(SwingConstants.CENTER);               
      add(this.label, borderDirection);
      add(component, BorderLayout.CENTER);
    }
    
    public LabeledComponent(String labelText, Component component, String borderDirection, int vertAlign)
    {
      setLayout(new BorderLayout(5, 5));
      this.label = new JLabel(labelText);
      this.label.setHorizontalAlignment(SwingConstants.CENTER);
      this.label.setVerticalAlignment(vertAlign);               
      add(this.label, borderDirection);
      add(component, BorderLayout.CENTER);
    }
  }
}
