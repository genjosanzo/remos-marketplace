/*******************************************************************************
 * Copyright (c) 2010 Tom Seidel, Remus Software
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/
package org.remus.marketplace.xml;

import java.io.OutputStream;
import java.util.Set;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.remus.marketplace.entities.Category;
import org.remus.marketplace.entities.InstallableUnit;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class XMLBuilder {

	public static Node buildNode(String serverPrefix,
			org.remus.marketplace.entities.Node findById) {
		org.remus.marketplace.xml.Node node = new org.remus.marketplace.xml.Node();
		node.setId(findById.getId());
		node.setBody(findById.getBody());
		node.setChanged(findById.getChanged() == null ? 0 : findById
				.getChanged());
		node.setCompanyname(findById.getCompanyname());
		node.setCreated(findById.getCreated() == null ? 0 : findById
				.getCreated());
		node.setEclipseversion(findById.getEclipseversion());
		node.setFavorited(findById.getFavorited() == null ? 0 : findById
				.getFavorited());
		node.setFoundationmember(findById.getFoundationmember() == null ? 0
				: findById.getFoundationmember());
		node.setHomepageurl(findById.getHomepageurl());
		node.setImage(findById.getImage());
		node.setLicense(findById.getLicense());
		node.setName(findById.getName());
		node.setOwner(findById.getOwner());
		node.setResource(findById.getResource());
		node.setScreenshot(findById.getScreenshot());
		node.setStatus(findById.getStatus());
		node.setSupporturl(findById.getSupporturl());
		node.setUpdateurl(findById.getUpdateurl());
		node.setVersion(findById.getVersion2());
		node.setUrl(serverPrefix + "content/" + findById.getId());

		Platforms platforms2 = new Platforms();

		Set<org.remus.marketplace.entities.Platform> platforms = findById
				.getPlatforms();
		for (org.remus.marketplace.entities.Platform platform : platforms) {
			Platform platform2 = new Platform();
			platform2.setName(platform.getName());
			platforms2.getPlatform().add(platform2);
		}
		if (platforms2.getPlatform().size() > 0) {
			node.setPlatforms(platforms2);
		}
		IUs iUs = new IUs();
		Set<InstallableUnit> installableUnits = findById.getInstallableUnits();
		for (InstallableUnit installableUnit : installableUnits) {
			IU iu = new IU();
			iu.setName(installableUnit.getName());
			iUs.getIu().add(iu);
		}
		if (iUs.getIu().size() > 0) {
			node.setIus(iUs);
		}

		Categories cats = new Categories();
		Set<Category> categories = findById.getCategories();
		for (Category category : categories) {
			org.remus.marketplace.xml.Category category2 = new org.remus.marketplace.xml.Category();
			category2.setId(String.valueOf(category.getId()));
			category2.setName(node.getName());
			category2.setUrl(serverPrefix + "taxonomy/term/"
					+ category.getMarket().getId() + "," + category.getId());
			cats.getCategories().add(category2);
		}
		node.setCategories(cats);

		return node;

	}

	public static XMLSerializer getXMLSerializer(OutputStream out) {
		// configure an OutputFormat to handle CDATA
		OutputFormat of = new OutputFormat();

		// specify which of your elements you want to be handled as CDATA.
		// The use of the '^' between the namespaceURI and the localname
		// seems to be an implementation detail of the xerces code.
		// When processing xml that doesn't use namespaces, simply omit the
		// namespace prefix as shown in the third CDataElement below.
		of.setCDataElements(new String[] {
				"^shortdescription", // <ns1:foo>
				"^supporturl", // <ns2:bar>
				"^homepageurl", "^body", "^image", "^companyname",
				"^eclipseversion", "^updateurl" }); // <baz>

		// set any other options you'd like
		of.setPreserveSpace(true);
		of.setIndenting(true);

		// create the serializer
		XMLSerializer serializer = new XMLSerializer(of);
		serializer.setOutputByteStream(out);

		return serializer;
	}

}
