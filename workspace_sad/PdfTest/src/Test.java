import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class Test {

	public static void main(String[] args) throws DocumentException, MalformedURLException, IOException {
		// TODO Auto-generated method stub
		Document document = new Document();
		
		PdfWriter.getInstance(document, new FileOutputStream("HelloWorld3.pdf"));
		Image image = Image.getInstance("police.png");
		image.scaleAbsolute(150f, 150f);
		document.open();
		document.add(image);
		document.add(new Paragraph("A Hello World PDF document." + "\n"+"test"));
		document.close();
	}

}
