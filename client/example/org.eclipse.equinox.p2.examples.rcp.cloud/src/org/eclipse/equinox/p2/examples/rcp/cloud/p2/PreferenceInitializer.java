/*******************************************************************************
 *  Copyright (c) 2010 IBM Corporation and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.equinox.p2.examples.rcp.cloud.p2;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.*;
import org.eclipse.equinox.p2.core.IAgentLocation;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.engine.ProfileScope;
import org.eclipse.equinox.p2.examples.rcp.cloud.Activator;
import org.eclipse.equinox.p2.ui.Policy;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.ServiceReference;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

/**
 * @since 3.6
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		Preferences node = new DefaultScope().getNode(Activator.PLUGIN_ID); //$NON-NLS-1$
		// default values
		node.putBoolean(PreferenceConstants.REPOSITORIES_VISIBLE, false);
		node.putBoolean(PreferenceConstants.SHOW_LATEST_VERSION_ONLY, true);
		node.putBoolean(PreferenceConstants.AVAILABLE_SHOW_ALL_BUNDLES, false);
		node.putBoolean(PreferenceConstants.INSTALLED_SHOW_ALL_BUNDLES, false);
		node.putBoolean(PreferenceConstants.AVAILABLE_GROUP_BY_CATEGORY, true);
		node.putBoolean(PreferenceConstants.SHOW_DRILLDOWN_REQUIREMENTS, false);
		node.putInt(PreferenceConstants.RESTART_POLICY, Policy.RESTART_POLICY_PROMPT_RESTART_OR_APPLY);
	}
}
