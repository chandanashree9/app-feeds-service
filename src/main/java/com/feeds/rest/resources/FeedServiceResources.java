package com.feeds.rest.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.feeds.model.NewsFeed;
import com.feeds.service.FeedService;
import com.feeds.service.FeedServiceImpl;
import com.feeds.service.ProcessStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Path("/feeds")
public class FeedServiceResources {

	private FeedService feedService = new FeedServiceImpl();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response feeds() {
		List<NewsFeed> list = feedService.retrieveFeeds();

		Gson gson = new GsonBuilder().create();
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("number_of_records", (list != null) ? list.size() : 0);
		JsonArray array = new JsonArray();
		if (list != null) {
			array = gson.toJsonTree(list).getAsJsonArray();
		}
		jsonObj.add("feeds", array);

		return Response.status(200).entity(gson.toJson(jsonObj)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
				.header("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia,Authorization")
				.build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/publish")
	public Response storeFeeds(NewsFeed feed) {
		ProcessStatus status = feedService.publishFeeds(feed);
		String message = "Successfully published feed";
		int code = 200;

		if (ProcessStatus.FAILURE.equals(status)) {
			code = 500;
			message = "Failed to publish feed";
		}
		return Response.status(code).entity(message).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
				.header("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia,Authorization")
				.build();
	}
}
