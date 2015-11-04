package eu.mondo.map.hg.benchmark.jena.match.schedule;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Resource;

import eu.mondo.map.hg.benchmark.jena.match.JenaMatch;
import eu.mondo.map.hg.benchmark.matches.schedule.ScheduleCountMatch;

public class JenaNavigationsMatch extends JenaMatch implements ScheduleCountMatch {

	public JenaNavigationsMatch(QuerySolution qs) {
		super(qs);
	}

	@Override
	public Object getCount() {
		return null;
	}

	@Override
	public Resource[] toArray() {
		return null;
	}

}
