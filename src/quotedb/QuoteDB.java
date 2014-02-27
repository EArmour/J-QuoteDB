package quotedb;

public class QuoteDB extends javax.swing.JFrame {

  //Creates new form QuoteDBUI
  public QuoteDB() {
    initComponents();
    
    String strings[] = new String[]{"Please","Work","Asshole"};
    lstTopics.setListData(strings);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    lstTopics = new javax.swing.JList();
    lblTopics = new javax.swing.JLabel();
    jScrollPane2 = new javax.swing.JScrollPane();
    lstPeople = new javax.swing.JList();
    lblPeople = new javax.swing.JLabel();
    btnAddCat = new javax.swing.JButton();
    btnAddQuote = new javax.swing.JButton();
    jScrollPane3 = new javax.swing.JScrollPane();
    txtQuote = new javax.swing.JTextArea();
    txtAuthor = new javax.swing.JTextField();
    txtSource = new javax.swing.JTextField();
    lblQuote = new javax.swing.JLabel();
    lblAuthor = new javax.swing.JLabel();
    lblSource = new javax.swing.JLabel();
    scrlQuotes = new javax.swing.JScrollBar();
    btnDelete = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    lstTopics.setModel(new javax.swing.AbstractListModel() {
      String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
      public int getSize() { return strings.length; }
      public Object getElementAt(int i) { return strings[i]; }
    });
    jScrollPane1.setViewportView(lstTopics);

    lblTopics.setLabelFor(lstTopics);
    lblTopics.setText("Topics:");

    lstPeople.setModel(new javax.swing.AbstractListModel() {
      String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
      public int getSize() { return strings.length; }
      public Object getElementAt(int i) { return strings[i]; }
    });
    jScrollPane2.setViewportView(lstPeople);

    lblPeople.setLabelFor(lstPeople);
    lblPeople.setText("People:");

    btnAddCat.setText("Add Category");

    btnAddQuote.setText("Add Quote");

    txtQuote.setEditable(false);
    txtQuote.setColumns(20);
    txtQuote.setRows(5);
    jScrollPane3.setViewportView(txtQuote);

    txtAuthor.setEditable(false);

    txtSource.setEditable(false);

    lblQuote.setLabelFor(txtQuote);
    lblQuote.setText("Quote:");

    lblAuthor.setLabelFor(txtAuthor);
    lblAuthor.setText("Author:");

    lblSource.setLabelFor(txtSource);
    lblSource.setText("Source:");

    scrlQuotes.setOrientation(javax.swing.JScrollBar.HORIZONTAL);

    btnDelete.setText("Delete");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(lblTopics)
              .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(lblPeople)
              .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addComponent(btnAddCat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(btnAddQuote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGap(0, 132, Short.MAX_VALUE))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(lblQuote)
              .addComponent(lblSource)
              .addComponent(lblAuthor))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrlQuotes, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addComponent(jScrollPane3)
              .addComponent(txtAuthor)
              .addComponent(txtSource))))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(lblPeople)
            .addGap(7, 7, 7)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addGroup(layout.createSequentialGroup()
                .addComponent(btnAddCat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddQuote))))
          .addGroup(layout.createSequentialGroup()
            .addComponent(lblTopics)
            .addGap(7, 7, 7)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(lblQuote))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(lblAuthor))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(txtSource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(lblSource))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(scrlQuotes, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btnDelete))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(QuoteDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(QuoteDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(QuoteDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(QuoteDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new QuoteDB().setVisible(true);
      }
    });
    
    QuoteCat qc = new QuoteCat("TestCat", 'P', null);
    Quote q = new Quote("Text", "Shakespeare", "Source", qc);
    qc.addQuote(q,qc);
    qc.addQuote(new Quote("Text","Armour","Source",qc), qc);
    qc.outputQuotes(qc);
    
    
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnAddCat;
  private javax.swing.JButton btnAddQuote;
  private javax.swing.JButton btnDelete;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JScrollPane jScrollPane3;
  private javax.swing.JLabel lblAuthor;
  private javax.swing.JLabel lblPeople;
  private javax.swing.JLabel lblQuote;
  private javax.swing.JLabel lblSource;
  private javax.swing.JLabel lblTopics;
  private javax.swing.JList lstPeople;
  private javax.swing.JList lstTopics;
  private javax.swing.JScrollBar scrlQuotes;
  private javax.swing.JTextField txtAuthor;
  private javax.swing.JTextArea txtQuote;
  private javax.swing.JTextField txtSource;
  // End of variables declaration//GEN-END:variables
}
