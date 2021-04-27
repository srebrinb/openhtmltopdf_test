package bg.srebrinb;


import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.w3c.dom.Document;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author srebrin
 */
public class Test {

    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
    //    String html = "<body>test тест ccc123<br/><div style=\"position: absolute;top: 0;right: 0;left: 0;bottom: 0;z-index: 1;background: rgba(255,255,255,0.5) url('./dar.png') no-repeat center;background-size: 135px;display: none;\">test</div></body>";
       File inF=new File("test/out.html");
         final W3CDom w3cDom = new W3CDom();
     //   Document doc = Jsoup.parse(inF, "unicode-16");
        final Document w3cDoc = w3cDom.fromJsoup(Jsoup.parse(inF, "UTF-8"));
        FileOutputStream fo = new FileOutputStream("o.pdf");
        PdfRendererBuilder builder = new PdfRendererBuilder();
       //  builder.withHtmlContent(html, srebrin.class.getResource("../../").toExternalForm());
        Path fontDirectory = Paths.get("./fonts");
        System.out.println(fontDirectory);
        // PERF: Should only be called once, as each font must be parsed for font family name.
        List<AutoFont.CSSFont> fonts = AutoFont.findFontsInDirectory(fontDirectory);

        // Use this in your template for the font-family property.
        //String fontFamily = AutoFont.toCSSEscapedFontFamily(fonts);

        // Add fonts to builder.
        AutoFont.toBuilder(builder, fonts);

       // builder.useFastMode();
       // builder.withHtmlContent(html, Test.class.getResource("./test/").toString());
        String baseDir = new File("").getAbsolutePath();
        
         builder.withW3cDocument(w3cDoc, "file:"+baseDir+"/test/");
       //builder.withFile(new File("out.html"));
        builder.toStream(fo);
        //  builder.useFastMode();
        builder.run();
    }
}
