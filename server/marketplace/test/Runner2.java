import org.remus.marketplace.dao.gen.CategoryDao;
import org.remus.marketplace.dao.gen.MarketDao;
import org.remus.marketplace.dao.gen.NodeDao;
import org.remus.marketplace.entities.Category;
import org.remus.marketplace.entities.InstallableUnit;
import org.remus.marketplace.entities.Market;
import org.remus.marketplace.entities.Node;
import org.remus.marketplace.entities.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Transactional(propagation = Propagation.REQUIRED)
public class Runner2 {

	@Autowired
	private final HibernateTransactionManager transactionManager;

	private final MarketDao marketDao;

	private final CategoryDao categoryDao;

	private final NodeDao nodeDao;

	public void testCompletedQualifications() throws Exception {

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus transaction = null;
		transaction = transactionManager.getTransaction(def);

		Market market = new Market();
		market.setName("Information types");
		marketDao.create(market);

		Category category = new Category();

		category.setMarket(market);
		category.setName("Uncategorized");
		categoryDao.create(category);

		Node node = new Node();
		node.setShortdescription("The twitter information type connects Remus Information Mangagement to your twitter account and provides tweeting, photo-uploads, twitter searches and many more.");
		node.setBody(node.getShortdescription());
		node.setCreated(1077150540);
		node.setChanged(1275561022);
		node.setHomepageurl("http://andrei.gmxhome.de/anyedit/index.html");
		node.setImage("http://remus-software.org/images/stories/message.png");
		node.setScreenshot("http://marketplace.eclipse.org/sites/default/files/anyedit_marketplace.png");
		node.setVersion2("2.3.1");
		node.setLicense("EPL");
		node.setStatus("Production/Stable");
		node.setCompanyname("Remus Software");
		node.setName("Twitter Tools");
		node.setEclipseversion("3.2, 3.3, 3.4, 3.5, 3.6");
		node.setFavorited(2);
		node.setFoundationmember(1);
		node.setSupporturl("mailto:loskutov@gmx.de");
		node.setUpdateurl("http://andrei.gmxhome.de/eclipse/");
		Platform pf = new Platform();
		pf.setName("Windows");
		node.getPlatforms().add(pf);
		pf = new Platform();
		pf.setName("Mac");
		node.getPlatforms().add(pf);
		pf = new Platform();
		pf.setName("Linux/GTK");
		node.getPlatforms().add(pf);

		InstallableUnit installableUnit = new InstallableUnit();
		installableUnit.setName("AnyEditTools");
		node.getInstallableUnits().add(installableUnit);
		installableUnit.setNode(node);

		node.getCategories().add(category);
		category.getNodes().add(node);
		nodeDao.create(node);

		node = new Node();

		node.setShortdescription("The audio information type is the perfect way to integrate mp3, wav or other audio types into your information management. Includes also a player.");
		node.setBody(node.getShortdescription());
		node.setCreated(1077150540);
		node.setChanged(1275561022);
		node.setHomepageurl("http://andrei.gmxhome.de/anyedit/index.html");
		node.setImage("http://remus-software.org/images/stories/loudspeaker.png");
		node.setScreenshot("http://marketplace.eclipse.org/sites/default/files/anyedit_marketplace.png");
		node.setVersion2("2.3.1");
		node.setLicense("EPL");
		node.setStatus("Production/Stable");
		node.setCompanyname("Remus Software");
		node.setName("Audio Information type");
		node.setEclipseversion("3.2, 3.3, 3.4, 3.5, 3.6");
		node.setFavorited(2);
		node.setFoundationmember(1);
		node.setSupporturl("mailto:loskutov@gmx.de");
		node.setUpdateurl("http://andrei.gmxhome.de/eclipse/");
		pf = new Platform();
		pf.setName("Windows");
		node.getPlatforms().add(pf);
		pf = new Platform();
		pf.setName("Mac");
		node.getPlatforms().add(pf);
		pf = new Platform();
		pf.setName("Linux/GTK");
		node.getPlatforms().add(pf);

		installableUnit = new InstallableUnit();
		installableUnit.setName("AnyEditTools");
		node.getInstallableUnits().add(installableUnit);
		installableUnit.setNode(node);

		node.getCategories().add(category);
		category.getNodes().add(node);
		nodeDao.create(node);

		node = new Node();

		node.setShortdescription("Rich-Text editing capabilities make Remus Information Management to an universal nodetaking tool. For plain notes, but also for notes with embedded images this component is a must-have.");
		node.setBody(node.getShortdescription());
		node.setCreated(1077150540);
		node.setChanged(1275561022);
		node.setHomepageurl("http://andrei.gmxhome.de/anyedit/index.html");
		node.setImage("http://remus-software.org/images/stories/chart_line.png");
		node.setScreenshot("http://marketplace.eclipse.org/sites/default/files/anyedit_marketplace.png");
		node.setVersion2("2.3.1");
		node.setLicense("EPL");
		node.setStatus("Production/Stable");
		node.setCompanyname("Remus Software");
		node.setName("Rich Text");
		node.setEclipseversion("3.2, 3.3, 3.4, 3.5, 3.6");
		node.setFavorited(2);
		node.setFoundationmember(1);
		node.setSupporturl("mailto:loskutov@gmx.de");
		node.setUpdateurl("http://andrei.gmxhome.de/eclipse/");
		pf = new Platform();
		pf.setName("Windows");
		node.getPlatforms().add(pf);
		pf = new Platform();
		pf.setName("Mac");
		node.getPlatforms().add(pf);
		pf = new Platform();
		pf.setName("Linux/GTK");
		node.getPlatforms().add(pf);

		installableUnit = new InstallableUnit();
		installableUnit.setName("AnyEditTools");
		node.getInstallableUnits().add(installableUnit);
		installableUnit.setNode(node);

		node.getCategories().add(category);
		category.getNodes().add(node);
		nodeDao.create(node);

		node = new Node();
		node.setShortdescription("With the BIRT Reporting Framework you can create/modify Business Reports based on the data of your Remus Information Management workspace. Enjoy the power of BIRT with the easyness of collection data from your local workspace");
		node.setBody(node.getShortdescription());
		node.setCreated(1077150540);
		node.setChanged(1275561022);
		node.setHomepageurl("http://andrei.gmxhome.de/anyedit/index.html");
		node.setImage("http://remus-software.org/images/stories/edit.png");
		node.setScreenshot("http://remus-software.org/images/stories/chart_line.png");
		node.setVersion2("2.3.1");
		node.setLicense("EPL");
		node.setStatus("Production/Stable");
		node.setCompanyname("Remus Software");
		node.setName("Reporting components");
		node.setEclipseversion("3.2, 3.3, 3.4, 3.5, 3.6");
		node.setFavorited(2);
		node.setFoundationmember(1);
		node.setSupporturl("mailto:loskutov@gmx.de");
		node.setUpdateurl("http://andrei.gmxhome.de/eclipse/");
		pf = new Platform();
		pf.setName("Windows");
		node.getPlatforms().add(pf);
		pf = new Platform();
		pf.setName("Mac");
		node.getPlatforms().add(pf);
		pf = new Platform();
		pf.setName("Linux/GTK");
		node.getPlatforms().add(pf);

		installableUnit = new InstallableUnit();
		installableUnit.setName("AnyEditTools");
		node.getInstallableUnits().add(installableUnit);
		installableUnit.setNode(node);

		node.getCategories().add(category);
		category.getNodes().add(node);
		nodeDao.create(node);
		// Download dl = new Download();
		// dl.setTime(new Date());
		// dl.setNode(node);
		// node.getDownloads().add(dl);
		// dl = new Download();
		// dl.setTime(new Date());
		// dl.setNode(node);
		// node.getDownloads().add(dl);

		transactionManager.commit(transaction);
	}

	public Runner2() {
		// open/read the application context file
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		marketDao = (MarketDao) ctx.getBean("marketBean");
		categoryDao = (CategoryDao) ctx.getBean("categoryBean");
		nodeDao = (NodeDao) ctx.getBean("nodeBean");
		transactionManager = (HibernateTransactionManager) ctx
				.getBean("transactionManager");
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Runner2 runner = new Runner2();
		runner.testCompletedQualifications();
		System.out.println("TEST");

		System.exit(0);

	}

}
