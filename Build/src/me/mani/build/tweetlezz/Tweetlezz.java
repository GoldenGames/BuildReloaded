package me.mani.build.tweetlezz;

import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Tweetlezz {
	
	private Twitter twitter;
	private TwitterStream stream;
		
	public void connect() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("5EcuPwAGny5BIpvcls5grBEXS")
		  .setOAuthConsumerSecret("RcQMXeQXIipFzBgVqKyK9wFJSoGv3oIj5YIB5r8LwUCKuvAsIQ")
		  .setOAuthAccessToken("711245528-Mx7DL7mezgu3r9CfaEpiFHew6TVtwb0TbwpRvVJo")
		  .setOAuthAccessTokenSecret("kyvu6jYBzxZbI0FGnWkCoMf4HarYmLdgXbhK4F5jsGhSN");
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}
	
	public void disconnect() {
		stream.clearListeners();
		stream.cleanUp();
		stream.shutdown();
	}
	
	public Twitter getAPI() {
		return twitter;
	}
	
	public void registerStreamListener(StatusListener listener) {
		if (stream == null)
			stream = new TwitterStreamFactory().getInstance(twitter.getAuthorization());
		stream.addListener(listener);
		stream.user();
	}

}
