package com.openshift.notebook.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.foursquare.fongo.Fongo;

@Configuration
@Profile("in-memory")
public class FongoDbFactory implements MongoDbFactoryConfig {

	@Override
	public MongoDbFactory mongoDbFactory() throws Exception {
		Fongo fongo = new Fongo("fake mongodb server");
		String databaseName = "notebook";
		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(fongo.getMongo(), databaseName);
		return mongoDbFactory;
	}

}
