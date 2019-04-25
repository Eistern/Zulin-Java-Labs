package Services;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ServerServices {

    private static final Logger logger = Logger.getLogger(ServerServices.class.getName());
    private Map<String, Class<ServiceInterface>> _classMap;

    private static volatile ServerServices instance;

    public static ServerServices getInstance() {
        ServerServices localInstance = instance;
        if (instance == null)
            synchronized (ServerServices.class) {
                if (instance == null) {
                    localInstance = new ServerServices();
                    instance = localInstance;
                }
            }
        return localInstance;
    }

    private ServerServices() {
        try {
            _classMap = new HashMap<>();
            Properties properties = new Properties();

            properties.load(ServerServices.class.getResourceAsStream("services.properties"));
            properties.forEach((action, className) -> {
                try {
                    Class classQual = Class.forName(className.toString());
                    _classMap.put(action.toString(), classQual);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error occurred: ", e.fillInStackTrace());
                }
            });
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred: ", e.fillInStackTrace());
        }
    }

    public ServiceInterface getHandler(String blockName) {
        try {
            Class<ServiceInterface> currentConstructor =  _classMap.get(blockName);
            if (currentConstructor == null)
                throw new ClassNotFoundException();
            return currentConstructor.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred: ", e.fillInStackTrace());
        }
        return null;
    }
}