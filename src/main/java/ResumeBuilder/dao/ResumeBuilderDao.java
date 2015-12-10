package ResumeBuilder.dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.language.LanguageIdentifier;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ToXMLContentHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import ResumeBuilder.domain.ResumeBuilderDom;

@Repository
public class ResumeBuilderDao implements IResumeBuilderDao {

	//@Autowired
	//CassandraOperations cassandraOperations;

	@Override
	public MultipartFile pdfExtractor(ResumeBuilderDom resumeBuilderDom)
			throws FileNotFoundException {
		// Import any file and save it in .doc format
		try {
			byte[] bytes = resumeBuilderDom.getFileResume().getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("D:\\resume\\new\\"
							+ resumeBuilderDom.getFileName() + ".doc")));
			stream.write(bytes);
			stream.close();
			System.out.println("You successfully uploaded "
					+ resumeBuilderDom.getFileName() + "!");
		} catch (Exception e) {
			System.out.println("You failed to upload "
					+ resumeBuilderDom.getFileName() + " => " + e.getMessage());
		}// end of import

		// Read Content of PDf file using PDFBox api
		
		/*try {
			File file = new File("D:\\resume\\new\\"
					+ resumeBuilderDom.getFileName() + ".pdf");
			PDFParser parser = new PDFParser(new FileInputStream(file));
			parser.parse();
			COSDocument cosDoc = parser.getDocument();
			PDFTextStripper pdfStripper = new PDFTextStripper();
			PDDocument pdDoc = new PDDocument(cosDoc);
			pdDoc.getNumberOfPages();
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(pdDoc.getNumberOfPages());
			String Text = pdfStripper.getText(pdDoc);
			System.out.println(Text);

		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		//Parse .doc file using apache tika java api and extract content in Plain Text
		/*try {
			BodyContentHandler handler = new BodyContentHandler();
			Metadata metadata = new Metadata();
			Parser parser = new AutoDetectParser();
			TikaInputStream inputstream = TikaInputStream.get(new File(
					"D:\\resume\\new\\" + resumeBuilderDom.getFileName()
							+ ".doc"));
			ParseContext context = new ParseContext();
			parser.parse(inputstream, handler, metadata, context);
			System.out.println("Extracted Content: " + handler.toString());
			System.out.println("Metadata Is: " + metadata);
			
			// Identify language of doc file
			LanguageIdentifier identifier = new LanguageIdentifier(
					handler.toString());
			System.out.println("Language Used in Resume: "
					+ identifier.getLanguage());
		}catch(IOException | SAXException | TikaException e){
			e.printStackTrace();
		}*/

		// Parse .doc file in html format using apache tika 
		try{
			ToXMLContentHandler handler = new ToXMLContentHandler();
			AutoDetectParser parser = new AutoDetectParser();
			Metadata metadata = new Metadata();
			FileInputStream fistream = new FileInputStream("D:\\resume\\new\\"
					+ resumeBuilderDom.getFileName() + ".html");
			parser.parse(fistream, handler, metadata);
			
			// Extract html tag elements and content of tags 
			Document doc = Jsoup.parse(handler.toString());
			System.out.println("Title is: " + doc.title());
			System.out.println("Head is:" + doc.head());
			System.out.println("Hyperlink  is:"
					+ doc.getElementsByAttribute("href"));
			System.out.println("Tag Bold is: " + doc.getElementsByTag("b"));
			System.out.println("Tag Italic is " + doc.getElementsByTag("i"));
			System.out.println("Header is " + doc.getElementsByClass("header"));
			System.out.println("subtitle is "
					+ doc.getElementsByClass("subtitle"));
			System.out
					.println("footer is: " + doc.getElementsByClass("footer"));
			System.out.println("Searched text is: "
					+ doc.getElementsContainingOwnText("Sample Word Document"));

		} catch (IOException | SAXException | TikaException e) {
			e.printStackTrace();
		}
		return null;
	}
}
