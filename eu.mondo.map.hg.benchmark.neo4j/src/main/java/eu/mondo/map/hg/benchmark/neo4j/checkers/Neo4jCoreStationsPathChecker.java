package eu.mondo.map.hg.benchmark.neo4j.checkers;

import java.util.Collection;
import java.util.HashSet;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

import eu.mondo.map.hg.benchmark.neo4j.driver.Neo4jDriver;
import eu.mondo.map.hg.benchmark.neo4j.matches.Neo4jStationsPathMatch;

public class Neo4jCoreStationsPathChecker extends Neo4jCoreChecker<Neo4jStationsPathMatch> {

	public Neo4jCoreStationsPathChecker(Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public Collection<Neo4jStationsPathMatch> check() throws Exception {
		final Collection<Neo4jStationsPathMatch> matches = new HashSet<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (Transaction tx = graphDb.beginTx()) {

		}
		return matches;
	}

}
