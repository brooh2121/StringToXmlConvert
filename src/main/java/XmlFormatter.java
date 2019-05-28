import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Properties;

/**
 * Created by Dmitry on 02.05.2019.
 */
public class XmlFormatter {

    public static void main(String[] args) throws Exception{
        Properties properties = new Properties();
        FileInputStream fos = new FileInputStream("application.properties");
        properties.load(fos);
        //System.out.println(new File(properties.getProperty("directory.path")));
        //System.out.println(new File(properties.getProperty("directory.path")));
        //System.out.println(new File("D:\\JavaWebService\\testXML\\"));
        //File XmlFile = new File("D:\\JavaWebService\\testXML\\");
        File XmlFile = new File(properties.getProperty("directory.path"));
        File[] Files = XmlFile.listFiles();
        String[] FileNames = new String[Files.length];
        for (int i = 0; i < FileNames.length; i++) {
            FileNames[i] = Files[i].getName();
            FileScaner fileScaner = new FileScaner();
            if (FileNames[i].matches("(.*).xml") && fileScaner.fileScanFirstRow(XmlFile + "\\" + FileNames[i])) {
                System.out.println("Считываем файл"+ ' ' + FileNames[i]);
                try {
                    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
                    InputStream inputStream = new FileInputStream(XmlFile + "\\" + FileNames[i]);
                    Reader reader = new InputStreamReader(inputStream, "UTF-8");
                    InputSource inputSource = new InputSource(reader);
                    Document document = documentBuilder.parse(inputSource);
                    OutputFormat format = new OutputFormat(document);
                    format.setLineWidth(65);
                    format.setIndenting(true);
                    format.setIndent(2);
                    Writer out = new StringWriter();
                    XMLSerializer serializer = new XMLSerializer(out, format);
                    serializer.serialize(document);
                    FileOutputStream fileOutputStream = new FileOutputStream(XmlFile + "\\" + FileNames[i]);
                    byte[] buffer = out.toString().getBytes();
                    fileOutputStream.write(buffer, 0, buffer.length);
                    System.out.println("Перезаписали XML:" + " " + FileNames[i]);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Файл"+ ' ' + FileNames[i] + ' ' + "не является XML");
            }

        }

    }
}
