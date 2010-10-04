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
public class Platform implements Serializable {

	public static final String NAME = "name";

	private java.lang.String name;

	public java.lang.String getName() {
		return this.name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}

	public static final String NODES = "nodes";

	@javax.persistence.ManyToMany(cascade = {
			javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.MERGE}, mappedBy = "platforms")
	private java.util.Set<Node> nodes = new java.util.HashSet<Node>();

	public java.util.Set<Node> getNodes() {
		return this.nodes;
	}
	public void setNodes(java.util.Set<Node> nodes) {
		this.nodes = nodes;
	}

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
			Serializable otherPk = ((Platform) other).getId();
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
