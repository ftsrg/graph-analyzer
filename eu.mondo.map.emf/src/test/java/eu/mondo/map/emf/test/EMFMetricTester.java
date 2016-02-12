package eu.mondo.map.emf.test;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import hu.bme.mit.trainbenchmark.railway.RailwayPackage;

@RunWith(Parameterized.class)
public abstract class EMFMetricTester {

	protected EObject container;
	protected Resource resource;
	protected final ResourceSet resourceSet = new ResourceSetImpl();
	protected String modelPath;
	protected String currentKey;

	protected static final String CONNECTEDSEGMENTS = "connectedsegments";
	protected static final String POSLENGTH = "poslength";
	protected static final String ROUTESENSOR = "routesensor";
	protected static final String SEMAPHORENEIGHBOR = "semaphoreneighbor";
	protected static final String SWITCHSENSOR = "switchsensor";
	protected static final String SWITCHSET = "switchset";

	protected static final String ROUTE = "Route";
	protected static final String SENSOR = "Sensor";
	protected static final String SEGMENT = "Segment";
	protected static final String SEMAPHORE = "Semaphore";
	protected static final String SWITCH = "Switch";
	protected static final String SWITCHPOSITION = "SwitchPosition";
	protected static final String REGION = "Region";

	protected static final String CONNECTSTO = "connectsTo";
	protected static final String SENSORS = "sensors";
	protected static final String NEIGHBORS = "neighbors";
	protected static final String ELEMENTS = "elements";
	protected static final String SEMAPHORES = "semaphores";
	protected static final String MONITOREDBY = "monitoredBy";
	protected static final String MONITORS = "monitors";
	protected static final String ENTRY = "entry";
	protected static final String EXIT = "exit";
	protected static final String GATHERS = "gathers";
	protected static final String LEFT = "left";
	protected static final String POSITIONS = "positions";
	protected static final String FROM = "from";
	protected static final String RIGHT = "right";
	protected static final String TARGET = "target";
	protected static final String FOLLOWS = "follows";
	protected static final String ROUTE_REFERENCE = "route";

	public EMFMetricTester(String modelPath, String key) {
		this.modelPath = modelPath;
		this.currentKey = key;
	}

	public void readModel() {
		RailwayPackage.eINSTANCE.eClass();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());

		final URI resourceURI = URI.createFileURI(modelPath);
		resource = resourceSet.getResource(resourceURI, true);

		for (EObject object : resource.getContents()) {
			container = object;
			break;
		}
	}

	@Parameters(name = "{index}: {1} minimal model")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { 
				{ "src/test/resources/railway-minimal-poslength.xmi", POSLENGTH },
				{ "src/test/resources/railway-minimal-connectedsegments.xmi", CONNECTEDSEGMENTS },
				{ "src/test/resources/railway-minimal-routesensor.xmi", ROUTESENSOR },
				{ "src/test/resources/railway-minimal-semaphoreneighbor.xmi", SEMAPHORENEIGHBOR },
				{ "src/test/resources/railway-minimal-switchsensor.xmi", SWITCHSENSOR },
				{ "src/test/resources/railway-minimal-switchset.xmi", SWITCHSET } });
	}
}
