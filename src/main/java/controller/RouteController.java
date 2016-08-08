package controller;

import com.google.gson.Gson;

import config.Routes;

import model.Project;
import model.User;


import static spark.Spark.*;

/**
 * Contains all the web service routes
 *
 * Created by Joel on 08/08/16.
 */
public class RouteController {

    private static Gson gson;

    /**
     * prepares the api entry points for GET, POST, DELETE and defines the request actions
     */
    public static void configure() {
        Gson gson = new Gson();

        setupGETEntryPoints(gson);

        setupPOSTEntryPoints();
    }

    /**
     * setup all GET routes as the api entry points
     */
    private static void setupGETEntryPoints(Gson gson) {

        // GET user data
        get(Routes.USER, (req, res) -> gson.toJson(new User()));

        // Get all projects
        get(Routes.PROJECT , (req, res) -> {
            Project project = new Project();
            return gson.toJson(project);
        });

        // GET project details including all teammembers
        get(Routes.PROJECTS + Routes.ID, (req, res) -> gson.toJson(new User()));

        // GET criteria catalogue
        get(Routes.CRITERIA, (req, res) -> gson.toJson(["Ist gut", "Ist nicht gut"]));

        // GET project related criteria
        get(Routes.PROJECTS + Routes.ID + Routes.CRITERIA, (req, res) -> gson.toJson(new User()));

        // GET all roles
        get(Routes.ROLES, (req, res) -> gson.toJson("Ist gut"));

        // GET rating by team member
        get(Routes.PROJECTS+Routes.ID+Routes.TEAM+Routes.ID, (req, res) -> gson.toJson("Ratings"));

        /**
         * @TestOnly to stop the web-server
         */
        get("/end", (req, res) -> {
            stop();
            return false;
        });
    }

    private static void setupPOSTEntryPoints() {
        post(Routes.PROJECTS, (req, res) -> {
            res.status(200);
            return "Project saved";
        });
    }
}
