import com.mobsec.jasmin.JasminReaderImpl;
import com.mobsec.jasmin.models.JasminClass;

/**
 * Created by snouto on 12/03/2015.
 */
public class commonLauncher {


    public static void main(String... args)
    {
        String fileName = "C:\\Users\\snouto\\Desktop\\infosec\\malwares\\output\\jasmin\\com\\allen\\txthej\\LZWEncoder.j";
        JasminReaderImpl reader = new JasminReaderImpl(fileName);
        JasminClass jasminClass = reader.parseClass();
        System.out.println(jasminClass);
    }
}
