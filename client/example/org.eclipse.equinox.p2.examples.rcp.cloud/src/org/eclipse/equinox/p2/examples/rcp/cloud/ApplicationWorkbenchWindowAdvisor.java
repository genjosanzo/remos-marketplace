package org.eclipse.equinox.p2.examples.rcp.cloud;

import java.util.List;

import org.eclipse.epp.internal.mpc.ui.CatalogRegistry;
import org.eclipse.epp.mpc.ui.CatalogDescriptor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(600, 400));
        configurer.setShowCoolBar(true);
        // XXX Set the status line and progress indicator so that update
        // information can be shown there
        configurer.setShowStatusLine(true);
		configurer.setShowProgressIndicator(true);

    }
    
    @Override
    public void postWindowOpen() {
    	
    	super.postWindowOpen();
    }
    
}
