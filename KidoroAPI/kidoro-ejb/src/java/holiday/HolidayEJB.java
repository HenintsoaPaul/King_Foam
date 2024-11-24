package holiday;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.ejb.Stateless;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

@Stateless
public class HolidayEJB implements IHolidayEJB{

    @Override
    public List<Holiday> getAll()
            throws FileNotFoundException {
        String projectPath = "D:\\ITU\\INF301-Architecture_Logicielle\\King_Foam\\KidoroAPI",
                filePath = projectPath + "\\kidoro-ejb\\src\\java\\holiday\\data\\holidays.json";

        FileReader reader = new FileReader( filePath );

        // Parse the JSON file into a List of Holiday objects
        Type listType = new TypeToken<List<Holiday>>() {}.getType();
        return new Gson().fromJson( reader, listType );
    }
}
