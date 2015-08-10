public class TestListener2 implements ModelListener<RepositoryEntry> {

	@Override
	public void onAfterCreate(RepositoryEntry arg0)
			throws ModelListenerException {
		try {
			Session s = createCmisSession();

			ObjectId id = s.createObjectId(arg0.getMappedId().toString());

			CmisObject o = s.getObject(arg0.getMappedId());

			if (o.getProperty(PropertyIds.BASE_TYPE_ID).getValueAsString().contains("cmis:folder")) {
				//Es handelt sich um einen Ordner
			} else {
				List<Property<?>> l = s.getObject(id).getProperties();
				Iterator<Property<?>> i = l.iterator();

				while (i.hasNext()) {
					Property<?> p = i.next();
					Object value = p.getValue();
					PropertyType t = p.getType();

					try {
						if (!arg0.getExpandoBridge().hasAttribute(p.getLocalName())) {
							arg0.getExpandoBridge().addAttribute(p.getLocalName());
						}
						arg0.getExpandoBridge().setAttribute(p.getLocalName(), String.valueOf(value));
					} catch (PortalException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
...