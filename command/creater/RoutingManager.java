package command.creater;

import java.util.ResourceBundle;

public class RoutingManager {
    private final static ResourceBundle rout = ResourceBundle.getBundle("routing.route");
    private RoutingManager(){}
    public static String getRoute(String key){
        return rout.getString(key);
    }
}
