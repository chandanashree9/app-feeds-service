# app-feeds-service
Service to publish and display feeds data

- Steps to deploy application
	- Install apache maven and run >> mvn clean install
	- Install mysql database server, connect to mysql and execute below sql
		- mysql > CREATE DATABASE app_feed;
		- mysql > connect app_feed;
		- mysql > CREATE CREATE TABLE FEEDS (
					ID MEDIUMINT NOT NULL AUTO_INCREMENT,
					CONTENT VARCHAR(5000) NULL,
					PUBLISH_DATE DATE NULL,
					PRIMARY KEY (ID)
				);
	- Install Tomcat Web Server and copy generated app-feeds.war to "WEBAPPS" folder
	- Endpoint URL's
		- List of News feeds - http://localhost:8081/app-feeds/feeds/list
		- Publish News feeds - http://localhost:8081/app-feeds/feeds/publish
		
