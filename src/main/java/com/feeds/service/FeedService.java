package com.feeds.service;

import java.util.List;

import com.feeds.model.NewsFeed;

public interface FeedService {

	public abstract ProcessStatus publishFeeds(NewsFeed feed);
	public abstract List<NewsFeed> retrieveFeeds();
}
