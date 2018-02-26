package hu.bme.mit.modelgen;

import java.util.List;

import org.apache.log4j.Logger;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modeladapters.ModelProvider;
import hu.bme.mit.modelgen.guide.MetricGuideDescriptor;
import hu.bme.mit.modelgen.guide.MetricGuideDescriptorFactory;
import hu.bme.mit.modelgen.handler.EdgeOperationHandler;
import hu.bme.mit.modelgen.handler.NodeOperationHandler;
import hu.bme.mit.modelgen.model.DomainAnalysisResult;
import hu.bme.mit.modelgen.model.DomainAnalyzer;
import hu.bme.mit.modelgen.model.EvolvingModelProvider;

public class ModelGeneratorRunner {

    private static final Logger logger = Logger.getLogger(ModelGeneratorRunner.class);

    public <N, T> void run(List<ModelProvider<N, T>> modelProviders, EvolvingModelProvider<N, T> targetModel) {
        MetricGuideDescriptorFactory guideManagerFactory = new MetricGuideDescriptorFactory();
        Scheduler<N, T> scheduler = new Scheduler<>();
        DomainAnalyzer domainAnalyzer = new DomainAnalyzer();

        DomainAnalysisResult<T> result = domainAnalyzer.analyze(modelProviders);
        List<MetricGuideDescriptor<N, T>> guideDescriptors = guideManagerFactory
                .getGuideDescriptors(result.getMetricEqualities());
        logger.debug("Use the following guides for generation: " + guideDescriptors);

        scheduler.add(guideDescriptors);

        ModelGeneratorEngine<N, T> engine = new ModelGeneratorEngine<>();
        NodeOperationHandler<N, T> nodeOpHandler = new NodeOperationHandler<>();
        EdgeOperationHandler<N, T> edgeOpHandler = new EdgeOperationHandler<>();
        ModelAdapter modelAdapter = new ModelAdapter();
        nodeOpHandler.setModelAdater(modelAdapter);
        nodeOpHandler.setDomainDescriptor(result.getDomainDescriptor());
        edgeOpHandler.setModelAdater(modelAdapter);
        edgeOpHandler.setDomainDescriptor(result.getDomainDescriptor());

        engine.add(edgeOpHandler);
        engine.add(nodeOpHandler);

        engine.generate(targetModel, modelAdapter, scheduler, result.getDomainDescriptor().getMaxNumberOfNodes());
    }

}
