/** Author Name: Evan Armour
 */
package quotedb;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class QuoteGUI extends JFrame {
  
  public static QuoteGUI mainFrame = new QuoteGUI();
  public static int quoteIndex = -1;
  public static QuoteCat currentCat = null;
  JButton btnPrev = new JButton("<-");
  JButton btnNext = new JButton("->");
  JButton btnAddQ = new JButton("Add Quote");
  JButton btnDeleteQ = new JButton("Delete Quote");
  JButton btnAddC = new JButton("Add Category");
  JButton btnDeleteC = new JButton("Delete Category");
  JButton btnLoad = new JButton("Load Quote File");
  JList lstCats = new JList();
  JTextArea txtQuote = new JTextArea(10, 40);
  JTextField txtAuthor = new JTextField(40);
  JTextField txtSource = new JTextField(40);

  public static void main(String[] args) {
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);
  }
  
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
   
    this.add(btnLoad, BorderLayout.WEST);
    
    //ACTION LISTENERS
    btnLoad.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        JFileChooser fc = new JFileChooser("C:\\Users\\Evan\\Dropbox\\NetBeans\\QuoteDB");
        int choice = fc.showOpenDialog(QuoteGUI.this);
        if (choice == JFileChooser.APPROVE_OPTION) {
          File xmlFile = fc.getSelectedFile();
          if (QuoteCat.readFile(xmlFile)) {
            btnLoad.setVisible(false);
            currentCat = QuoteCat.categories.get(0);
            lstCats.setListData(QuoteCat.listCats());
            JPanel catPanel = new JPanel(new FlowLayout());
            catPanel.add(new LabeledComponent("Categories:", lstCats, BorderLayout.NORTH));
            mainFrame.add(catPanel, BorderLayout.WEST);
          }
        }
      }
    });
    
    lstCats.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent lse) {
        System.out.println(lstCats.getSelectedIndex());
        currentCat = QuoteCat.categories.get(lstCats.getSelectedIndex());
        quoteIndex = 0;
        dispQuote();
      }
    });
    
    btnAddQ.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        QuoteCat.writeXML();
      }
    });
    
    btnNext.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        if(quoteIndex < currentCat.quotes.size() - 1) {
          quoteIndex++;
          dispQuote();
        }
      }
    });
    
    btnPrev.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        if(quoteIndex > 0){
          quoteIndex--;
          dispQuote();
        }
      }
    });
  }
  
  public void dispQuote() {
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
