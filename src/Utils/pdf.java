package Utils;


import Entities.Produit;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;


public class pdf {
      
        //webcam.main(args);  
    public void GeneratePdf(String filename,Produit v,int qnt) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
        document.add(new Paragraph("Author :"+v.getName()+"\n"));
        document.add(new Paragraph("Titre :"+v.getImage()+"\n"));
        document.add(new Paragraph("Prix unitaire :"+v.getPrice()+"\n"));
        document.add(new Paragraph("Quantité :"+qnt+"\n"));
        double total=qnt*v.getPrice();
        document.add(new Paragraph("Prix Total :"+total+"\n"));
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        document.close();
        //Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
}

