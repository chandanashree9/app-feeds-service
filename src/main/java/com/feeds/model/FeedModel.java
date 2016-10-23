package com.feeds.model;

import java.io.Serializable;
import java.time.LocalDate;

public class FeedModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6150427923227665291L;

	private String content;
	private LocalDate publishDate;

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the publishDate
	 */
	public LocalDate getPublishDate() {
		return publishDate;
	}

	/**
	 * @param publishDate
	 *            the publishDate to set
	 */
	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

}
