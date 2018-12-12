package hu.bme.mit.ga.adapters.emf;

import com.google.common.collect.ImmutableList;
import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.adapters.GraphIndexer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import java.util.Iterator;

public class EmfGraphAdapter extends GraphAdapter<EObject, String> {

    public void init(EObject root) {
        init(root.eAllContents());
    }

    protected void init(Iterator<EObject> iterator) {
        final ImmutableList<EObject> eObjects = ImmutableList.copyOf(iterator);
        indexer = new GraphIndexer<>(eObjects.size());
        for (EObject object : eObjects) {
            indexer.addNode(object);
        }
        for (EObject object : eObjects) {
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

}
