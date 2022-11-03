package edu.kau.fcit.cpit252.receiptStrategy;

import edu.kau.fcit.cpit252.shopping.Product;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

public class PdfReceipt extends Receipt {

    private String subject;
    private String path;

    public PdfReceipt(String path, String subject, List<Product> products) {
        super(products);
        this.path = path;
        this.subject = subject;
    }


    @Override
    public void generate() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\t" + this.subject + "\n");
        sb.append("-----------\t\t\t --------------\n");
        sb.append("Item\t\t\t\t Price\n");
        sb.append("-----------\t\t\t --------------\n");
        double total = 0;
        for (Product p : this.getProducts()) {

            sb.append("\t" + p.getName() + "\t\t\t" + p.getPrice() + "\n");
            total += p.getPrice();
        }
        sb.append("\t\t\t\t\t Total:\t" + total);

        PDDocument document = new PDDocument();
        PDDocumentInformation pdfDocInfo = document.getDocumentInformation();
        PDPage pdfPage = new PDPage();
        pdfDocInfo.setTitle(this.subject);
        pdfDocInfo.setCreationDate(new GregorianCalendar());
        try {
            document.addPage(pdfPage);
            PDPageContentStream contentStream = new PDPageContentStream(document, pdfPage);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 725);

            String content = sb.toString().replaceAll("\t", " ");
            String[]lines = content.split("\n");

            for(String line : lines){
                contentStream.showText(line);
                contentStream.newLine();
                contentStream.newLineAtOffset(0,-10);
            }
            contentStream.endText();
            contentStream.close();
            document.save(this.path);
            document.close();
        }
        catch(IOException e){
            System.err.println("Error: Failed to print a PDF receipt. Reason: " + e.getMessage());
        }
    }
}
