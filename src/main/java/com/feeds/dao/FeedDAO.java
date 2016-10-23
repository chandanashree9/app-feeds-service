package com.feeds.dao;

import java.util.List;

import com.feeds.model.FeedModel;

public interface FeedDAO {

	public abstract void persistData(FeedModel feed) throws DAOException;

	public abstract List<FeedModel> getFeedList() throws DAOException;
}
