/** Author Name: Evan Armour
 */
package quotedb;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class QuoteGUI extends JFrame {
  
  public static QuoteGUI mainFrame = new QuoteGUI();

  public static void main(String[] args) {
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);
    
    QuoteCat qc = new QuoteCat("TestCat", 'P', null);
    Quote q = new Quote("Text", "Shakespeare", "Source", qc);
    qc.addQuote(q,qc);
    qc.addQuote(new Quote("Text","Armour","Source",qc), qc);
    qc.outputQuotes(qc);
  }
  
  JButton btnPrev = new JButton("<-");
  JButton btnNext = new JButton("->");
  JButton btnAdd = new JButton("Add Quote");
  JButton btnDelete = new JButton("Delete Quote");
  JTextArea txtQuote = new JTextArea(10, 40);
  JTextField txtAuthor = new JTextField(40);
  JTextField txtSource = new JTextField(40);
  
  public QuoteGUI(){
    //INITIALIZING FRAME AND SETTING PROPERTIES
    setTitle("Quote Database");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(700, 350);
    txtQuote.setEditable(false);
    txtAuthor.setEditable(false);
    txtSource.setEditable(false);
    
    txtQuote.setText(BorderLayout.WEST);
    //GUI BUTTONS IN GRID LAYOUT
    JPanel buttonPanel = new JPanel(new GridLayout());
    buttonPanel.add(btnPrev);
    buttonPanel.add(btnNext);
    buttonPanel.add(btnAdd);
    buttonPanel.add(btnDelete);
    this.add(buttonPanel, BorderLayout.SOUTH);
    
    //TEXT FIELDS FOR OUTPUT IN FLOW LAYOUT
    JPanel displayPanel = new JPanel(new FlowLayout());
    displayPanel.add(new LabeledComponent("Quote", txtQuote, BorderLayout.WEST));
    displayPanel.add(new LabeledComponent("Author", txtAuthor, BorderLayout.WEST));
    displayPanel.add(new LabeledComponent("Source", txtSource, BorderLayout.WEST));
    this.add(displayPanel, BorderLayout.CENTER);
    
    
    btnAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        QuoteCat.writeXML();
      }
    });
    
    btnNext.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        QuoteCat.quotes.get(0).test();
      }
    });
  }
  
  class LabeledComponent extends JPanel {
    JLabel label;

    public LabeledComponent(String text, Component component, String alignment)
    {
      setLayout(new BorderLayout(5, 5));
      this.label = new JLabel(text);
      this.label.setHorizontalAlignment(SwingConstants.CENTER);               
      add(this.label, alignment);
      add(component, BorderLayout.CENTER);
    }
  }
}
