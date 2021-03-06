package quotedb;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class QuoteCat {

	private String name;
	private char type; // T = Topic, P = Person
	public ArrayList<Quote> quotes = new ArrayList<Quote>();
	public static ArrayList<QuoteCat> categories = new ArrayList<QuoteCat>();
	public static File xmlLocation = new File("quotes.xml");

	public QuoteCat(String name, char type) {
		this.name = name;
		this.type = type;
		quotes = new ArrayList<Quote>();
		categories.add(this);
	}

	public static void sortCategories() {
		ArrayList<QuoteCat> personCats = new ArrayList<QuoteCat>();

		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getType() == 'P') {
				personCats.add(categories.get(i));
			}
		}
		categories.removeAll(personCats);
		Collections.sort(categories, new CompareCat());
		Collections.sort(personCats, new CompareCat());

		for (int j = 0; j < personCats.size(); j++) {
			categories.add(personCats.get(j));
		}
	}

	public void addQuote(Quote quote) {
		quotes.add(quote);
	}

	public void removeQuote(Quote quote) {
		quotes.remove(quote);
	}

	public void dispQuote(int index) {
		Quote dispQ = quotes.get(index);
	}

	public void remove() {
		categories.remove(this);
	}

	public void outputQuotes() {
		for (int i = 0; i < quotes.size(); i++) {
			Quote temp = quotes.get(i);
			System.out.println("Quote: " + temp.getText());
			System.out.println("Author: " + temp.getAuthor());
			System.out.println("Source: " + temp.getSource());
		}
	}

	public static QuoteCat[] listCats() {
		sortCategories();
		QuoteCat[] a = new QuoteCat[categories.size()];
		return categories.toArray(a);
	}

	public static boolean writeXML() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			Element root = doc.createElement("QuoteCategories");
			doc.appendChild(root);

			for (int i = 0; i < categories.size(); i++) {
				QuoteCat cat = categories.get(i);
				Element category = doc.createElement("Category");
				category.setAttribute("name", cat.getName());
				category.setAttribute("type", String.valueOf(cat.getType()));
				root.appendChild(category);
				for (int j = 0; j < cat.quotes.size(); j++) {
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

	public static boolean readFile(File xmlFile) {
		xmlLocation = xmlFile;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);

			NodeList categories = doc.getElementsByTagName("Category");
			for (int i = 0; i < categories.getLength(); i++) {
				Element category = (Element) categories.item(i);
				String name = category.getAttribute("name");
				char type = category.getAttribute("type").charAt(0);
				QuoteCat currCat = new QuoteCat(name, type);
				NodeList quotes = category.getElementsByTagName("Quote");
				for (int j = 0; j < quotes.getLength(); j++) {
					Element quote = (Element) quotes.item(j);
					NodeList quoteContents = quote.getChildNodes();
					// Empty node of newline between each actual text element
					// for some reason
					String text = quoteContents.item(1).getTextContent();
					String author = quoteContents.item(3).getTextContent();
					String source = quoteContents.item(5).getTextContent();
					new Quote(text, author, source, currCat);
				}
			}
			sortCategories();
			return true;
		} catch (Exception e) {
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

	static class CompareCat implements Comparator<QuoteCat> {
		@Override
		public int compare(QuoteCat c1, QuoteCat c2) {
			return c1.getName().compareToIgnoreCase(c2.getName());
		}
	}

	@Override
	public String toString() {
		return name;
	}
}