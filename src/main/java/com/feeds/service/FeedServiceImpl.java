/**
 * 
 */
package com.feeds.service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import com.feeds.dao.DAOException;
import com.feeds.dao.FeedDAO;
import com.feeds.dao.FeedDAOImpl;
import com.feeds.model.FeedModel;
import com.feeds.model.NewsFeed;

public class FeedServiceImpl implements FeedService {

	private FeedDAO feedDAO = new FeedDAOImpl();

	/* (non-Javadoc)
	 * @see com.feeds.service.FeedService#publishFeeds(com.feeds.model.NewsFeed)
	 */
	public ProcessStatus publishFeeds(NewsFeed feed) {
		ProcessStatus status = ProcessStatus.SUCCESS;
		try {
			FeedModel feedModel = new FeedModel();
			feedModel.setContent(feed.getContent());
			feedModel.setPublishDate(convertStringToDate(feed.getPublishDate()));
			feedDAO.persistData(feedModel);
		} catch (DAOException daoExp) {
			status = ProcessStatus.FAILURE;
		}
		return status;
	}

	/* (non-Javadoc)
	 * @see com.feeds.service.FeedService#retrieveFeeds()
	 */
	public List<NewsFeed> retrieveFeeds() {
		List<NewsFeed> result = new LinkedList<NewsFeed>();
		try {
			List<FeedModel> rs = feedDAO.getFeedList();
			if (rs != null && !rs.isEmpty()) {
				for (FeedModel feedModel : rs) {
					NewsFeed newsfeed = new NewsFeed();
					newsfeed.setContent(feedModel.getContent());
					newsfeed.setPublishDate(convertDateToString(feedModel.getPublishDate()));
					result.add(newsfeed);
				}
			}
		} catch (DAOException daoExp) {
		}
		return result;
	}

	/**
	 * @param input
	 * @return
	 */
	private LocalDate convertStringToDate(String input) {
		LocalDate result = LocalDate.now();
		try {
			result = LocalDate.parse(input, DateTimeFormatter.ISO_DATE.withZone(ZoneOffset.UTC));
		} catch (DateTimeParseException exp) {
		}
		return result;
	}

	/**
	 * @param input
	 * @return
	 */
	private String convertDateToString(LocalDate input) {
		String result = "";
		try {
			if (input != null) {
				result = input.format(DateTimeFormatter.ISO_DATE.withZone(ZoneOffset.UTC));
			}
		} catch (DateTimeException exp) {
		}
		return result;
	}
}