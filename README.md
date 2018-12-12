# Graph analyzer

[![Build Status](https://travis-ci.org/FTSRG/graph-analyzer.svg)](https://travis-ci.org/FTSRG/graph-analyzer)

Analysis and metric visualisation toolkit for typed graphs, based on our [MODELS 2016 paper](https://inf.mit.bme.hu/sites/default/files/publications/models2016-metrics.pdf) ([ACM DL](https://dl.acm.org/citation.cfm?id=2976786), [DBLP](https://dblp.uni-trier.de/rec/html/conf/models/SzarnyasKSV16)).

## Prerequisites

Java 8

## Getting Started

To run the analysis, edit the `analyzer-app/src/Analyzer.groovy` file and issue the following command:

```console
./gradlew analyze
```

Make sure [the Gradle process has enough memory](https://docs.gradle.org/current/userguide/build_environment.html#sec:configuring_jvm_memory) - the more the better.

```console
export JAVA_OPTS=-Xmx4G
```

## License

This project is distributed under the Eclipse Public License 1.0. The project was supported by the MONDO EU FP7 project (EU ICT-611125) and is currently developed by the MTA-BME Lendület Research Group on Cyber-Physical Systems.
