package com.feeds.model;

import java.io.Serializable;

public class NewsFeed implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5127766087427712653L;

	private String content;
	private String publishDate;

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
	public String getPublishDate() {
		return publishDate;
	}

	/**
	 * @param publishDate
	 *            the publishDate to set
	 */
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NewsFeed [content=" + content + ", publishDate=" + publishDate + "]";
	}

}
