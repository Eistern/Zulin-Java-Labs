package Services;

import commObjects.BaseForm;
import serverMain.ConnectionManager;

public interface ServiceInterface {
    void serve(BaseForm infoMessage, ConnectionManager.Client srcClient);
}
