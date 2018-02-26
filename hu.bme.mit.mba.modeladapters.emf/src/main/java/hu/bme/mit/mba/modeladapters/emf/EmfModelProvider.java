package hu.bme.mit.mba.modeladapters.emf;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import hu.bme.mit.mba.modeladapters.Triple;
import hu.bme.mit.mba.modeladapters.ModelIndexBuilder;
import hu.bme.mit.mba.modeladapters.ModelProvider;

public class EmfModelProvider implements ModelProvider<EObject, String> {

    private EObject root;
    private ModelIndexBuilder builder;

    public EmfModelProvider(ModelIndexBuilder builder) {
        this.builder = builder;
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
            builder.build(new Triple<>(object, neighbor, reference.getName()));
        }
    }

    public EObject getRoot() {
        return root;
    }

    @Override
    public List<Triple<EObject, String>> getOperations() {
        // TODO Auto-generated method stub
        return null;
    }

}