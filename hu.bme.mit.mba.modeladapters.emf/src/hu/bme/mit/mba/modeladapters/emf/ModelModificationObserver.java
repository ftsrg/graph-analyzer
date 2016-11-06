package hu.bme.mit.mba.modeladapters.emf;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;

public class ModelModificationObserver extends EContentAdapter {

    @Override
    public void notifyChanged(Notification notification) {
        super.notifyChanged(notification);
    }

}
