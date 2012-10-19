package com.openshift.notebook.web;

public class WebContextLoader extends GenericWebContextLoader {

	public WebContextLoader() {
		super("src/main/webapp/WEB-INF/", false);
	}

}
