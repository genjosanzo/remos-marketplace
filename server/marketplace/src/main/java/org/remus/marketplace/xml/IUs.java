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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class IUs {

	private List<IU> iu = new ArrayList<IU>();

	/**
	 * @return the iu
	 */
	@XmlElement
	public List<IU> getIu() {
		return iu;
	}

	/**
	 * @param iu
	 *            the iu to set
	 */
	public void setIu(List<IU> iu) {
		this.iu = iu;
	}

}
