import controller.RouteController;

import static spark.Spark.port;

public class Main {
    public static void main(String[] args) {
        port(8000);

        // Set routes for web services
        RouteController.configure();

    }
}