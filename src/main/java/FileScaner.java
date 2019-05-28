import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Dmitry on 20.05.2019.
 */
public class FileScaner {

    public FileScaner () {
    }

    /*
    public boolean fileScan (String xml) throws Exception {
        FileReader fileReader = new FileReader(xml);
        Scanner scan = new Scanner(fileReader);
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine().matches("<?xml"));
        }
        return false;
    }
    */

    public boolean fileScanFirstRow (String xml) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(xml));
        StringBuilder stringBuilder = new StringBuilder();
        //stringBuilder.append(bufferedReader.readLine().trim().indexOf("\"<?xml\""));
        //System.out.println(stringBuilder.append(bufferedReader.readLine().trim().indexOf("<?xml")).getClass());
        //stringBuilder.append(bufferedReader.readLine().trim().indexOf("<?xml")).equals(0)? true : false;
        String result = stringBuilder.append(bufferedReader.readLine().trim().indexOf("<?xml")).toString();

        if (result.equals("0")) {
            return true;
        }else {
            return false;
        }

        //System.out.println(stringBuilder.append(bufferedReader.readLine().trim().indexOf("<?xml")).getClass());
        //String result = stringBuilder.append(bufferedReader.readLine().trim().indexOf("<?xml")).toString();
        //.out.println(result.equals(0));
    }
}
