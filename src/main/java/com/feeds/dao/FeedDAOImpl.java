package com.feeds.dao;

import java.util.List;

import com.feeds.model.FeedModel;

public class FeedDAOImpl implements FeedDAO {

	public void persistData(FeedModel feed) throws DAOException {
		DBManager.getDBInstance().insert(feed);
	}

	public List<FeedModel> getFeedList() throws DAOException {
		return DBManager.getDBInstance().select();
	}
}
