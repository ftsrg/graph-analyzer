package eu.mondo.map.hg.benchmark.neo4j.matches;

import java.util.Map;

import org.neo4j.graphdb.Node;

import eu.mondo.map.hg.benchmark.matches.schedule.ScheduleCountMatch;

public class Neo4jStationsPathMatch extends Neo4jMatch implements ScheduleCountMatch {

	public Neo4jStationsPathMatch(Map<String, Object> match) {
		super(match);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getCount() {
		return null;
	}

	@Override
	public Node[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

}
