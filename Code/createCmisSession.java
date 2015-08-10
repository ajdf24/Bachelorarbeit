private Session createCmisSession() {
	SessionFactory f = SessionFactoryImpl.newInstance();
	Map<String, String> parameter = new HashMap<String, String>();

	// user credentials
	parameter.put(SessionParameter.USER, "sebastian.rieger2");
	parameter.put(SessionParameter.PASSWORD, "1234");

	// connection settings
	parameter.put(SessionParameter.ATOMPUB_URL, "http://localhost:8080/alfresco/api/-default-/public/cmis/versions/1.1/atom");
	parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
	parameter.put(SessionParameter.REPOSITORY_ID, "-default-");

	// session locale
	parameter.put(SessionParameter.LOCALE_ISO3166_COUNTRY, "");
	parameter.put(SessionParameter.LOCALE_ISO639_LANGUAGE, "de");

	// create session
	return f.createSession(parameter);
}