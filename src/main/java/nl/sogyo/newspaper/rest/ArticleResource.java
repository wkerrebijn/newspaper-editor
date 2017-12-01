package nl.sogyo.newspaper.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.util.JSON;
import nl.sogyo.newspaper.model.*;
import nl.sogyo.newspaper.persistence.*;
import org.bson.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by wkerrebijn on 28-11-2017.
 */
@Path("/articles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArticleResource {

    private final DatabaseManager dbm = DatabaseManager.getInstance();
    private final Gson gson = new Gson();

    @GET
    public String getAll() {
        //Get alle article docs uit db
        //Omzetten naar Article object
        return gson.toJson(dbm.getDocumentsFromTable("Articles"));
    }

    @GET
    @Path("/{id}")
    public String getById(@PathParam("id") String id) {
        //Get article doc uit db obv id
        //Omzetten naar Article object
        return gson.toJson(dbm.getDocumentByIdFromTable(id, "Articles"));
    }

    @PUT
    public Response create(String articleJSON) {
        System.out.println(articleJSON);
//        Article article = gson.fromJson(articleJSON, Article.class);
//        System.out.println(article);
        dbm.createDocumentInTable(articleJSON,"Articles");
        return Response.created(URI.create("/"+(int)(Math.random()*1000))).build();

//        Article a = new Article();
//        dbm.createDocumentInTable(a, "Articles");
//        return Response.created(URI.create("/"+a.getId())).build();
    }

    @POST
    @Path("/{id}")
    public Response update(Article article) {
        //Get article doc uit db obv id
        // Bijwerken doc
        //Omzetten naar Article object
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        //Get article doc uit db obv id
        // verwijderen doc
        // return
        dbm.deleteDocumentByIdFromTable(id, "Articles");
        return Response.ok().build();
    }

//    public Document JsontoDocument(String json) {
//        gson.toJson(json);
//        Document document = new Document();
////        document
//        gson.newJsonReader()
//        return document;
//    }
}
