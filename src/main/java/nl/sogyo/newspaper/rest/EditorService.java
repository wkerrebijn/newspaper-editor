package nl.sogyo.newspaper.rest;

import nl.sogyo.newspaper.model.Article;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by wkerrebijn on 23-11-2017.
 */

@Path("/service")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EditorService {

    @GET
    @Path("/{param}")
    public Article getMessage(@PathParam("param") String message) {

        String output = "Service started: " + message;

        return new Article("999", output);
    }

//    public class Result
//    {
//        private String message;
//
//        Result(String message) {
//
//            this.message = message;
//        }
//    }
}
