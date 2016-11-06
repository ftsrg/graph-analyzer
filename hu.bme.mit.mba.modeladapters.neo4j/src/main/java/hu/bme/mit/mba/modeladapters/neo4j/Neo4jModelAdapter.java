package hu.bme.mit.mba.modeladapters.neo4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.neo4j.graphdb.Node;

import hu.bme.mit.mba.modeladapters.ModelIndexer;
import hu.bme.mit.mba.modeladapters.TypedModelAdapter;

public class Neo4jModelAdapter extends TypedModelAdapter<Node, String> {

    @Override
    public Iterator<Node> getModelIterator() {
        return null; // TODO
    }

    protected void init(final Iterator<EObject> iterator) {
        indexer = new ModelIndexer<Node, String>();

        while (iterator.hasNext()) {
//            final EObject object = iterator.next();
//            for (final EReference reference : object.eClass().getEReferences()) {
//                if (reference.isMany()) { // many
//                    for (final EObject neighbor : getNeighbors(object, reference)) {
//                        addEdge(object, reference, neighbor);
//                    }
//                } else { // single
//                    final EObject neighbor = (EObject) object.eGet(reference, true);
//                    addEdge(object, reference, neighbor);
//                }
//            }
        }
    }

    protected List<Node> getNeighbors(final EObject object, final EReference reference) {
        return new ArrayList<>(); // TODO
    }

    protected void addEdge(final EObject object, final EReference reference, final EObject neighbor) {
        if (neighbor != null && reference != null) {
//            indexer.addEdge(reference.getName(), object, neighbor);
        }
    }

}