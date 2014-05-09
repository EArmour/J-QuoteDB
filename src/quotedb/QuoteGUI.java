/** Author Name: Evan Armour
 */
package quotedb;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class QuoteGUI extends JFrame {
  
  public static QuoteGUI mainFrame = new QuoteGUI();
  public static int quoteIndex = -1;
  public static QuoteCat currentCat = null;
  public static Quote currentQuote = null;
  JButton btnPrev = new JButton("<-");
  JButton btnNext = new JButton("->");
  JButton btnAddQ = new JButton("Add Quote");
  JButton btnDeleteQ = new JButton("Delete Quote");
  JButton btnAddC = new JButton("Add Category");
  JButton btnDeleteC = new JButton("Delete Category");
  JButton btnLoad = new JButton("Load Quote File");
  JLabel lblStatus = new JLabel("Ready to go!");
  JList lstCats = new JList();
  JTextArea txtQuote = new JTextArea(10, 40);
  JScrollPane scrlQuote = new JScrollPane(txtQuote);
  JTextField txtAuthor = new JTextField(40);
  JTextField txtSource = new JTextField(40);

  public static void main(String[] args) {
    mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    mainFrame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        if(currentCat != null) {
          int choice = JOptionPane.showConfirmDialog(mainFrame, 
                  "Do you wish to save the changes you have made in this session?", 
                  "Save Changes", JOptionPane.YES_NO_CANCEL_OPTION);
          if (choice == JOptionPane.YES_OPTION) {
            QuoteCat.writeXML();
            mainFrame.dispose();
          }
          else if (choice == JOptionPane.NO_OPTION) {
            mainFrame.dispose();
          }
        }
      }
    });
    
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);
  }
  
  public QuoteGUI(){
    //INITIALIZING FRAME AND SETTING PROPERTIES
    setTitle("Quote Database");
    setSize(675, 350);

    //GUI BUTTONS IN GRID LAYOUT
    final JPanel buttonPanel = new JPanel(new GridLayout());
    buttonPanel.add(btnAddC);
    buttonPanel.add(btnDeleteC);
    buttonPanel.add(btnAddQ);
    buttonPanel.add(btnDeleteQ);
    this.add(buttonPanel, BorderLayout.SOUTH);
    buttonPanel.setVisible(false);
    
    //Tweak text field settings (word wrap, disable tabbing)
    txtQuote.setWrapStyleWord(true);
    txtQuote.setLineWrap(true);
    txtQuote.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
          if (e.getKeyCode() == KeyEvent.VK_TAB) {
            if (e.getModifiers() > 0) {
                txtQuote.transferFocusBackward();
            } else {
                txtQuote.transferFocus();
            }
            e.consume();
          }
        }
      });
    
    //TEXT FIELDS FOR OUTPUT IN FLOW LAYOUT
    JPanel displayPanel = new JPanel(new FlowLayout());
    displayPanel.add(new LabeledComponent("Quote", scrlQuote, BorderLayout.WEST, SwingConstants.TOP));
    displayPanel.add(new LabeledComponent("Author", txtAuthor, BorderLayout.WEST));
    displayPanel.add(new LabeledComponent("Source", txtSource, BorderLayout.WEST));
    JPanel navPanel = new JPanel(new BorderLayout());
    lblStatus.setBorder(new EmptyBorder(0, 0, 0, 200));
    navPanel.add(lblStatus, BorderLayout.WEST);
    navPanel.add(btnPrev, BorderLayout.CENTER);
    navPanel.add(btnNext, BorderLayout.EAST);
    displayPanel.add(navPanel);
    this.add(displayPanel, BorderLayout.CENTER);
    this.add(btnLoad, BorderLayout.WEST);
    
    //Enter submits quote
    this.getRootPane().setDefaultButton(btnAddQ);
    
    //ACTION LISTENERS
    btnLoad.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        JFileChooser fc = new JFileChooser("C:\\Temp");
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
            buttonPanel.setVisible(true);
            lblStatus.setText("XML file loaded!");
          }
          else {
            lblStatus.setText("XML loading failed! Check your input file integrity.");
          }
        }
      }
    });
    
    lstCats.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent lse) {
        if (lstCats.getSelectedIndex() > -1) {
          currentCat = QuoteCat.categories.get(lstCats.getSelectedIndex());
          quoteIndex = 0;
          if (currentCat.quotes.isEmpty()){
            emptyFields();
          }
          else {
            dispQuote();
          }
        }
      }
    });
    
    btnAddQ.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        new Quote(txtQuote.getText(), txtAuthor.getText(), txtSource.getText(), currentCat);
        quoteIndex = currentCat.quotes.size() - 1;
        dispQuote();
        lblStatus.setText("Quote added!");
      }
    });
    
    btnDeleteQ.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        if (currentQuote != null) {
          currentQuote.remove();
          if (quoteIndex > 0) {
            quoteIndex--;
            dispQuote();
          }
          lblStatus.setText("Quote deleted!");
        }
      }
    });
        
    btnAddC.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        String cName = JOptionPane.showInputDialog(null, "Please enter the new category's name:");
        String[] types = {"Topic", "Person"};
        Object cType = JOptionPane.showInputDialog(null, "What type of category is is?", "Type Selection", 1, null, types, types[0]);
        currentCat = new QuoteCat(cName, cType.toString().charAt(0));
        lstCats.setListData(QuoteCat.listCats());
        lstCats.setSelectedValue(currentCat.getName(), true);
        lblStatus.setText("Category added!");
      }
    });

    btnDeleteC.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        currentCat.remove();
        lblStatus.setText("Category deleted!");
        currentCat = QuoteCat.categories.get(0);
        lstCats.setListData(QuoteCat.listCats());
        lstCats.setSelectedIndex(0);
      }
    });

    btnNext.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        lblStatus.setText("");
        if (quoteIndex < currentCat.quotes.size() - 1) {
          quoteIndex++;
          dispQuote();
        }
        else if (quoteIndex == currentCat.quotes.size() - 1) {
          quoteIndex++;
          emptyFields();
        }
      }
    });
    
    btnPrev.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        lblStatus.setText("");
        if (quoteIndex > 0) {
          quoteIndex--;
          dispQuote();
        }
        else if (quoteIndex == 0) {
          quoteIndex--;
          emptyFields();
        }
      }
    });
  }
  
  public void dispQuote() {
    currentQuote = currentCat.quotes.get(quoteIndex);
    txtQuote.setText(currentQuote.getText());
    txtAuthor.setText(currentQuote.getAuthor());
    txtSource.setText(currentQuote.getSource());
    txtQuote.setCaretPosition(0);
  }
  
  public void emptyFields() {
    currentQuote = null;
    if (currentCat.getType() == 'P') {
      txtAuthor.setText(currentCat.getName());
    }
    else {
      txtAuthor.setText((""));
    }
    txtQuote.setText("");
    txtSource.setText("");
  }
  
  class LabeledComponent extends JPanel {
    JLabel label;

    public LabeledComponent(String labelText, Component component, String borderDirection) {
      setLayout(new BorderLayout(5, 5));
      this.label = new JLabel(labelText);
      this.label.setHorizontalAlignment(SwingConstants.CENTER);               
      add(this.label, borderDirection);
      add(component, BorderLayout.CENTER);
    }
    
    public LabeledComponent(String labelText, Component component, String borderDirection, int vertAlign) {
      setLayout(new BorderLayout(5, 5));
      this.label = new JLabel(labelText);
      this.label.setHorizontalAlignment(SwingConstants.CENTER);
      this.label.setVerticalAlignment(vertAlign);               
      add(this.label, borderDirection);
      add(component, BorderLayout.CENTER);
    }
  }
}
