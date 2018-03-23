package hu.bme.mit.mba.modeladapters.emf;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import java.util.Iterator;

public class EmfModelAdapter extends ModelAdapter<EObject, String> {

    private EObject root;

    @Override
    public Iterator<EObject> getModelIterator() {
        return root.eAllContents();
    }

    public void init(EObject root) {
        this.root = root;
        init(root.eAllContents());
    }

    protected void init(Iterator<EObject> iterator) {
        while (iterator.hasNext()) {
            final EObject object = iterator.next();
            for (final EReference reference : object.eClass().getEReferences()) {
                if (reference.isMany()) { // many
                    for (final EObject neighbor : getNeighbors(object, reference)) {
                        addEdge(object, reference, neighbor);
                    }
                } else { // single
                    EObject neighbor = (EObject) object.eGet(reference, true);
                    addEdge(object, reference, neighbor);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected EList<EObject> getNeighbors(final EObject object, final EReference reference) {
        return (EList<EObject>) object.eGet(reference, true);
    }

    protected void addEdge(final EObject object, final EReference reference, EObject neighbor) {
        if (neighbor != null && reference != null) {
            indexer.addEdge(reference.getName(), object, neighbor);
        }
    }

    public EObject getRoot() {
        return root;
    }

}
