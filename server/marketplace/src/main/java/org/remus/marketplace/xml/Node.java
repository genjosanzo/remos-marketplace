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

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class Node {

	private int id;

	private String name;

	private String url;

	private String resource;

	private String owner;

	private int favorited;

	private String shortdescription;

	private String body;

	private int created;

	private int changed;

	private int foundationmember;

	private String homepageurl;

	private String image;

	private String screenshot;

	private String version;

	private String license;

	private String companyname;

	private String status;

	private String eclipseversion;

	private String supporturl;

	private String updateurl;

	private IUs ius;

	private Platforms platforms;

	private Categories categories;

	/**
	 * @return the id
	 */
	@XmlAttribute
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	@XmlAttribute
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the url
	 */
	@XmlAttribute
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the resource
	 */
	public String getResource() {
		return resource;
	}

	/**
	 * @param resource
	 *            the resource to set
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return the favorited
	 */
	public int getFavorited() {
		return favorited;
	}

	/**
	 * @param favorited
	 *            the favorited to set
	 */
	public void setFavorited(int favorited) {
		this.favorited = favorited;
	}

	/**
	 * @return the shortdescription
	 */
	public String getShortdescription() {
		return shortdescription;
	}

	/**
	 * @param shortdescription
	 *            the shortdescription to set
	 */
	public void setShortdescription(String shortdescription) {
		this.shortdescription = shortdescription;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body
	 *            the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the create
	 */
	public int getCreated() {
		return created;
	}

	/**
	 * @param create
	 *            the create to set
	 */
	public void setCreated(int create) {
		created = create;
	}

	/**
	 * @return the changed
	 */
	public int getChanged() {
		return changed;
	}

	/**
	 * @param changed
	 *            the changed to set
	 */
	public void setChanged(int changed) {
		this.changed = changed;
	}

	/**
	 * @return the foundationmember
	 */
	public int getFoundationmember() {
		return foundationmember;
	}

	/**
	 * @param foundationmember
	 *            the foundationmember to set
	 */
	public void setFoundationmember(int foundationmember) {
		this.foundationmember = foundationmember;
	}

	/**
	 * @return the homepageurl
	 */
	public String getHomepageurl() {
		return homepageurl;
	}

	/**
	 * @param homepageurl
	 *            the homepageurl to set
	 */
	public void setHomepageurl(String homepageurl) {
		this.homepageurl = homepageurl;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the screenshot
	 */
	public String getScreenshot() {
		return screenshot;
	}

	/**
	 * @param screenshot
	 *            the screenshot to set
	 */
	public void setScreenshot(String screenshot) {
		this.screenshot = screenshot;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the license
	 */
	public String getLicense() {
		return license;
	}

	/**
	 * @param license
	 *            the license to set
	 */
	public void setLicense(String license) {
		this.license = license;
	}

	/**
	 * @return the companyname
	 */
	public String getCompanyname() {
		return companyname;
	}

	/**
	 * @param companyname
	 *            the companyname to set
	 */
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the eclipseversion
	 */
	public String getEclipseversion() {
		return eclipseversion;
	}

	/**
	 * @param eclipseversion
	 *            the eclipseversion to set
	 */
	public void setEclipseversion(String eclipseversion) {
		this.eclipseversion = eclipseversion;
	}

	/**
	 * @return the supporturl
	 */
	public String getSupporturl() {
		return supporturl;
	}

	/**
	 * @param supporturl
	 *            the supporturl to set
	 */
	public void setSupporturl(String supporturl) {
		this.supporturl = supporturl;
	}

	/**
	 * @return the updateurl
	 */
	public String getUpdateurl() {
		return updateurl;
	}

	/**
	 * @param updateurl
	 *            the updateurl to set
	 */
	public void setUpdateurl(String updateurl) {
		this.updateurl = updateurl;
	}

	/**
	 * @return the ius
	 */
	public IUs getIus() {
		return ius;
	}

	/**
	 * @param ius
	 *            the ius to set
	 */
	public void setIus(IUs ius) {
		this.ius = ius;
	}

	/**
	 * @return the platforms
	 */
	public Platforms getPlatforms() {
		return platforms;
	}

	/**
	 * @param platforms
	 *            the platforms to set
	 */
	public void setPlatforms(Platforms platforms) {
		this.platforms = platforms;
	}

	/**
	 * @return the categories
	 */
	public Categories getCategories() {
		return categories;
	}

	/**
	 * @param categories
	 *            the categories to set
	 */
	public void setCategories(Categories categories) {
		this.categories = categories;
	}

}
