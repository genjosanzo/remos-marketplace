import java.util.Date;

import org.remus.marketplace.dao.gen.CategoryDao;
import org.remus.marketplace.dao.gen.MarketDao;
import org.remus.marketplace.dao.gen.NodeDao;
import org.remus.marketplace.entities.Category;
import org.remus.marketplace.entities.Download;
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
public class Runner {

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
		market.setName("Plug-Ins");
		marketDao.create(market);

		Category category = new Category();

		category.setMarket(market);
		category.setName("Application development");
		categoryDao.create(category);

		Node node = new Node();
		node.setBody("<a href=\"http://www.eclipse.org/org/foundation/eclipseawards/winners09.php\" class=\"none\" title=\"Eclipse ready\"><img src=\"http://andrei.gmxhome.de/images/finalist09.jpg\" alt=\"Eclipse community awards 2009 finalist\" width=\"144\" height=\"144\" border=\"0\" align=\"right\" class=\"none\" /></a>\r\n"
				+ "AnyEdit Tools plugin adds several new tools to the context menu of text- based Eclipse editors,  to output consoles, to Eclipse main menu, editor toolbar and navigator/explorer.\r\n"
				+ "<!--break-->\r\n"
				+ "<b>Here is a short overview about some *visible* contributions provided by AnyEdit Tools:</b>\r\n"
				+ "\r\n"
				+ "<ul>\r\n"
				+ "<li>\r\n"
				+ "AnyEdit contributes Import/Export working sets wizards.\r\n"
				+ "</li>\r\n"
				+ "<li>\r\n"
				+ "AnyEdit can show the withespace (tabs vs spaces) in editors and may use custom project settings for tab<->spaces auto-convert-on-save feature.\r\n"
				+ "</li>\r\n"
				+ "<li>\r\n"
				+ "AnyEdit can perform tabs <-> spaces convert on multiple files or entire directories and supports also file exclusion filter for tabs <-> spaces action.\r\n"
				+ "</li>\r\n"
				+ "<li>\r\n"
				+ "AnyEdit allows you automatically remove trailing whitespaces and/or perform tabs<->spaces conversion on a \"save\" action in all text- based Eclipse editors. It can automatically create a new line at the end of the file if the last line was not terminated by new line.\r\n"
				+ "</li>\r\n"
				+ "<li>\r\n"
				+ "AnyEdit adds \"Save All\", \"Open File\" and \"Show whitespace in editor\" buttons to the global Eclipse toolbar and can remove \"Print\" button from it. Additionally it provides File->\"Count (Selected) Resources\" menu.\r\n"
				+ "</li>\r\n"
				+ "<li>\r\n"
				+ "AnyEdit adds \"Open File\", \"Open Type\" and \"Save to file...\" actions to supported output consoles and \"Save to file...\" menu/toolbar button. \r\n"
				+ "</li>\r\n"
				+ "<li>\r\n"
				+ "AnyEdit adds four new context menu actions to each \"Compare With\" and \"Replace With\" menus for both editors and files, also for external files.\r\n"
				+ "</li>\r\n"
				+ "</ul>\r\n"
				+ "<b>\r\n"
				+ "Some of context menu contributed by AnyEdit to editors/files/console: \r\n"
				+ "</b>\r\n"
				+ "<ul>\r\n"
				+ "<li>Open file under cursor</li>\r\n"
				+ "<li>Open java type under cursor</li>\r\n"
				+ "<li>Convert -> Tabs <-> Spaces</li>  \r\n"
				+ "<li>Convert -> Camel <-> Underscores</li>\r\n"
				+ "<li>Convert -> Chars to html / Html to chars</li> \r\n"
				+ "<li>Convert -> Capitalize/Invert case</li> \r\n"
				+ "<li>Convert -> To upper case/To lower case</li> \r\n"
				+ "<li>Convert -> To / From Unicode notation</li>\r\n"
				+ "<li>Convert -> To / From Base64</li>\r\n"
				+ "<li>Compare / Replace With -> Clipboard</li>\r\n"
				+ "<li>Compare / Replace With -> Workspace file...</li>\r\n"
				+ "<li>Compare / Replace With -> External file...</li>\r\n"
				+ "<li>Compare / Replace With -> Opened Editor...</li>\r\n"
				+ "</ul>\r\n"
				+ "\r\n"
				+ "Current version supports only Eclipse 3.5 and higher, but older AnyEdit versions for Eclipse 2.1 up to 3.4 may be downloaded from the homepage too.\r\n"
				+ "\r\n"
				+ "<a href=\"http://andrei.gmxhome.de/anyedit/index.html\">See more details at the AnyEdit Tools homepage...</a>\r\n"
				+ "\r\n"
				+ "<b>My other plugins on EPIC</b>:\r\n"
				+ "<ul>\r\n"
				+ "<li><a href=\"http://marketplace.eclipse.org/content/bytecode-outline\">Bytecode Outline</a>\r\n"
				+ "</li>\r\n"
				+ "<li><a href=\"http://marketplace.eclipse.org/content/extended-vs-presentation\">Extended VS Presentation</a>\r\n"
				+ "</li>\r\n"
				+ "<li><a href=\"http://marketplace.eclipse.org/content/filesync\">FileSync</a>\r\n"
				+ "</li>\r\n"
				+ "<li><a href=\"http://marketplace.eclipse.org/content/jdepend4eclipse\">JDepend4Eclipse</a>\r\n"
				+ "</li>\r\n"
				+ "<li><a href=\"http://marketplace.eclipse.org/content/data-hierarchy\">Data Hierarchy</a>\r\n"
				+ "</li>\r\n" + "</ul>");
		node.setShortdescription("<a href=\"http://www.eclipse.org/org/foundation/eclipseawards/winners09.php\" class=\"none\" title=\"Eclipse ready\"><img src=\"http://andrei.gmxhome.de/images/finalist09.jpg\" alt=\"Eclipse community awards 2009 finalist\" width=\"144\" height=\"144\" border=\"0\" align=\"right\" class=\"none\" /></a>\r\n"
				+ "AnyEdit Tools plugin adds several new tools to the context menu of text- based Eclipse editors,  to output consoles, to Eclipse main menu, editor toolbar and navigator/explorer.\r\n"
				+ "");
		node.setCreated(1077150540);
		node.setChanged(1275561022);
		node.setHomepageurl("http://andrei.gmxhome.de/anyedit/index.html");
		node.setImage("http://marketplace.eclipse.org/sites/default/files/finalist09_5.jpg");
		node.setScreenshot("http://marketplace.eclipse.org/sites/default/files/anyedit_marketplace.png");
		node.setVersion2("2.3.1");
		node.setLicense("EPL");
		node.setStatus("Production/Stable");
		node.setCompanyname("Andrei Loskutov");
		node.setName("AnyEdit Tools");
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

		Download dl = new Download();
		dl.setTime(new Date());
		dl.setNode(node);
		node.getDownloads().add(dl);
		dl = new Download();
		dl.setTime(new Date());
		dl.setNode(node);
		node.getDownloads().add(dl);
		node.getCategories().add(category);
		category.getNodes().add(node);

		Node findById = nodeDao.findById((Integer) nodeDao.create(node));
		nodeDao.update(findById);

		transactionManager.commit(transaction);
	}

	public Runner() {
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
		Runner runner = new Runner();
		runner.testCompletedQualifications();
		System.out.println("TEST");

		System.exit(0);

	}

}
