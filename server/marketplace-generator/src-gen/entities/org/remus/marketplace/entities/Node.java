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
package org.remus.marketplace.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Version;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.Hibernate;

/**
 * This is a generated class. DO NOT MODIFY THE CONTENT!!
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
@javax.persistence.Entity
public class Node implements Serializable {

	public static final String ID = "id";

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private Integer id;

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public static final String NAME = "name";

	private java.lang.String name;

	public java.lang.String getName() {
		return this.name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}

	public static final String URL = "url";

	private java.lang.String url;

	public java.lang.String getUrl() {
		return this.url;
	}
	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	public static final String FAVORITED = "favorited";

	private Integer favorited;

	public Integer getFavorited() {
		return this.favorited;
	}
	public void setFavorited(Integer favorited) {
		this.favorited = favorited;
	}

	public static final String CATEGORIES = "categories";

	@javax.persistence.ManyToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "nodes")
	private java.util.Set<Category> categories = new java.util.HashSet<Category>();

	public java.util.Set<Category> getCategories() {
		return this.categories;
	}
	public void setCategories(java.util.Set<Category> categories) {
		this.categories = categories;
	}

	public static final String TYPE = "type";

	private java.lang.String type;

	public java.lang.String getType() {
		return this.type;
	}
	public void setType(java.lang.String type) {
		this.type = type;
	}

	public static final String OWNER = "owner";

	private java.lang.String owner;

	public java.lang.String getOwner() {
		return this.owner;
	}
	public void setOwner(java.lang.String owner) {
		this.owner = owner;
	}

	public static final String SHORTDESCRIPTION = "shortdescription";

	@javax.persistence.Column(length = Integer.MAX_VALUE)
	private java.lang.String shortdescription;

	public java.lang.String getShortdescription() {
		return this.shortdescription;
	}
	public void setShortdescription(java.lang.String shortdescription) {
		this.shortdescription = shortdescription;
	}

	public static final String BODY = "body";

	@javax.persistence.Column(length = Integer.MAX_VALUE)
	private java.lang.String body;

	public java.lang.String getBody() {
		return this.body;
	}
	public void setBody(java.lang.String body) {
		this.body = body;
	}

	public static final String CREATED = "created";

	private Integer created;

	public Integer getCreated() {
		return this.created;
	}
	public void setCreated(Integer created) {
		this.created = created;
	}

	public static final String CHANGED = "changed";

	private Integer changed;

	public Integer getChanged() {
		return this.changed;
	}
	public void setChanged(Integer changed) {
		this.changed = changed;
	}

	public static final String FOUNDATIONMEMBER = "foundationmember";

	private Integer foundationmember;

	public Integer getFoundationmember() {
		return this.foundationmember;
	}
	public void setFoundationmember(Integer foundationmember) {
		this.foundationmember = foundationmember;
	}

	public static final String HOMEPAGEURL = "homepageurl";

	private java.lang.String homepageurl;

	public java.lang.String getHomepageurl() {
		return this.homepageurl;
	}
	public void setHomepageurl(java.lang.String homepageurl) {
		this.homepageurl = homepageurl;
	}

	public static final String IMAGE = "image";

	private java.lang.String image;

	public java.lang.String getImage() {
		return this.image;
	}
	public void setImage(java.lang.String image) {
		this.image = image;
	}

	public static final String SCREENSHOT = "screenshot";

	private java.lang.String screenshot;

	public java.lang.String getScreenshot() {
		return this.screenshot;
	}
	public void setScreenshot(java.lang.String screenshot) {
		this.screenshot = screenshot;
	}

	public static final String VERSION2 = "version2";

	private java.lang.String version2;

	public java.lang.String getVersion2() {
		return this.version2;
	}
	public void setVersion2(java.lang.String version2) {
		this.version2 = version2;
	}

	public static final String LICENSE = "license";

	private java.lang.String license;

	public java.lang.String getLicense() {
		return this.license;
	}
	public void setLicense(java.lang.String license) {
		this.license = license;
	}

	public static final String COMPANYNAME = "companyname";

	private java.lang.String companyname;

	public java.lang.String getCompanyname() {
		return this.companyname;
	}
	public void setCompanyname(java.lang.String companyname) {
		this.companyname = companyname;
	}

	public static final String STATUS = "status";

	private java.lang.String status;

	public java.lang.String getStatus() {
		return this.status;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public static final String ECLIPSEVERSION = "eclipseversion";

	private java.lang.String eclipseversion;

	public java.lang.String getEclipseversion() {
		return this.eclipseversion;
	}
	public void setEclipseversion(java.lang.String eclipseversion) {
		this.eclipseversion = eclipseversion;
	}

	public static final String SUPPORTURL = "supporturl";

	private java.lang.String supporturl;

	public java.lang.String getSupporturl() {
		return this.supporturl;
	}
	public void setSupporturl(java.lang.String supporturl) {
		this.supporturl = supporturl;
	}

	public static final String UPDATEURL = "updateurl";

	private java.lang.String updateurl;

	public java.lang.String getUpdateurl() {
		return this.updateurl;
	}
	public void setUpdateurl(java.lang.String updateurl) {
		this.updateurl = updateurl;
	}

	public static final String PLATFORMS = "platforms";

	@javax.persistence.ManyToMany(cascade = javax.persistence.CascadeType.ALL, fetch = javax.persistence.FetchType.LAZY)
	private java.util.Set<Platform> platforms = new java.util.HashSet<Platform>();

	public java.util.Set<Platform> getPlatforms() {
		return this.platforms;
	}
	public void setPlatforms(java.util.Set<Platform> platforms) {
		this.platforms = platforms;
	}

	public static final String INSTALLABLEUNITS = "installableUnits";

	@javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, fetch = javax.persistence.FetchType.LAZY, mappedBy = "node")
	private java.util.Set<InstallableUnit> installableUnits = new java.util.HashSet<InstallableUnit>();

	public java.util.Set<InstallableUnit> getInstallableUnits() {
		return this.installableUnits;
	}
	public void setInstallableUnits(
			java.util.Set<InstallableUnit> installableUnits) {
		this.installableUnits = installableUnits;
	}

	public static final String RESOURCE = "resource";

	private java.lang.String resource;

	public java.lang.String getResource() {
		return this.resource;
	}
	public void setResource(java.lang.String resource) {
		this.resource = resource;
	}

	public static final String CLICKS = "clicks";

	@javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, fetch = javax.persistence.FetchType.LAZY, mappedBy = "node")
	private java.util.Set<Clickthrough> clicks = new java.util.HashSet<Clickthrough>();

	public java.util.Set<Clickthrough> getClicks() {
		return this.clicks;
	}
	public void setClicks(java.util.Set<Clickthrough> clicks) {
		this.clicks = clicks;
	}

	public static final String DOWNLOADS = "downloads";

	@javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, fetch = javax.persistence.FetchType.LAZY, mappedBy = "node")
	private java.util.Set<Download> downloads = new java.util.HashSet<Download>();

	public java.util.Set<Download> getDownloads() {
		return this.downloads;
	}
	public void setDownloads(java.util.Set<Download> downloads) {
		this.downloads = downloads;
	}

	@javax.persistence.Version
	@javax.persistence.Column(name = "OPTLOCK")
	private Integer version;

	@javax.xml.bind.annotation.XmlTransient
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public boolean equals(final Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || // looks into the target class of a proxy if necessary
				!Hibernate.getClass(other).equals(Hibernate.getClass(this))) {
			return false;
		}
		// if pks are both set, compare
		if (getId() != null) {
			Serializable otherPk = ((Node) other).getId();
			if (otherPk != null) {
				return getId().equals(otherPk);
			}
		}
		return false;
	}

	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getId());
		return builder.toHashCode();
	}

}
