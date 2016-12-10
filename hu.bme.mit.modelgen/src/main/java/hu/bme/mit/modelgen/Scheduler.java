package hu.bme.mit.modelgen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import hu.bme.mit.modelgen.guide.MetricGuideDescriptor;
import hu.bme.mit.modelgen.guide.Priorities;
import hu.bme.mit.modelgen.op.Operation;

public class Scheduler<N, T> {

    private final static Logger logger = Logger.getLogger(Scheduler.class);

    List<MetricGuideDescriptor<N, T>> guideDescriptors;
    // private LinkedListMultimap<Priorities, Operation<N, T>> operations;
    private HashMap<Priorities, LinkedList<Operation<N, T>>> operations;

    public Scheduler() {
        guideDescriptors = new ArrayList<>();
        operations = new HashMap<>();
        for (Priorities p : Priorities.values()) {
            operations.put(p, new LinkedList<>());
        }
    }

    public List<MetricGuideDescriptor<N, T>> getGuideDescriptors() {
        return guideDescriptors;
    }

    public void add(MetricGuideDescriptor<N, T> guideDescriptor) {
        this.guideDescriptors.add(guideDescriptor);
    }

    public void add(List<MetricGuideDescriptor<N, T>> guideDescriptors) {
        this.guideDescriptors.addAll(guideDescriptors);
    }

    public List<Operation<N, T>> schedule() {
        logger.info(String.format("Scheduling operations, number of guides: %d, number of TOP operations: %d",
                guideDescriptors.size(), operations.get(Priorities.TOP).size()));
        if (operations.get(Priorities.TOP).isEmpty()) {
            logger.debug("TOP operations are not found, ask new operations from guides: " + guideDescriptors);
            guideDescriptors.forEach(gd -> gd.getGuide().provide());
        }
        guideDescriptors.forEach(guideDescriptor -> {
            if (guideDescriptor.hasActiveOperations()) {
                put(guideDescriptor.getPriority(), guideDescriptor.pollActiveOperation());
            }
        });

        return Arrays.asList(operations.get(Priorities.TOP).pop());
    }

    private void put(Priorities priority, Operation<N, T> op) {
        operations.get(priority).add(op);
    }

}
