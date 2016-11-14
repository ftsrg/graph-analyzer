# Properties of a model generator

## Definitions

A _model generator_ can produce domain-specific models, e.g. statecharts, railway networks, etc.

In our view, an _ideal model generator_ satisfies the following properties.

* **Consistent:** the generated models are well-formed with respect to a set of well-formedness constraints.
* **Scalable:** the generator is able to generate models scalable in size. In other words, the execution time of the generator is approximately a linear function of the desired model size.
* **Diverse:** the generator is capable of producing models of that are different from each other, not only in size, but also in their structure.
* **Realistic:** the generated models are realistic, i.e. based on their structure and type information, a domain expert cannot distinguish them from real models in the same domain. (Currently, this definition does not consider attribute values, so these must obfuscated or hidden from the domain expert.)

(If you have a hard time memorizing these properties, think "core disc" for "consistent, realistic, diverse and scalable".)

## Use cases

The two most important use cases are to generate models for 1) testing and 2) benchmarking.

These use cases have somewhat different requirements. In the following table, we list their **primary** requirements.

|                | Testing | Benchmarking |
| -------------- | :-----: | :----------: |
| **Consistent** | ✓       | ✓            |
| **Diverse**    | ✓       |              |
| **Realistic**  | ✓       | ✓            |
| **Scalable**   |         | ✓            |

## State of research

We collected our results and the related work for each property.

### Our results

In our [SOSYM 2015 paper](https://inf.mit.bme.hu/research/publications/formal-validation-domain-specific-languages-derived-features-and-well-formedne), ... (keywords: **consistent**, **diverse**)

In our [FASE 2016 paper](https://inf.mit.bme.hu/research/publications/iterative-and-incremental-model-generation-logic-solvers), ... (keywords: **consistent**, **diverse**)

In our [MODELS 2016 paper](https://inf.mit.bme.hu/research/publications/towards-characterization-realistic-models-evaluation-multidisciplinary-graph-m), we analyzed models from typical MDE domains, such as architecture modeling, code analysis, statecharts, system modeling. We gathered a set of graph metrics for determining if a model is **realistic**.

In the [Train Benchmark](https://github.com/FTSRG/trainbenchmark), we implemented a model generator that is capable of generating models of increasing sizes in a **scalable** way.

The metrics from the MODELS 2016 paper are demonstrated a simplified Train Benchmark model in a [GraphGist](http://portal.graphgist.org/graph_gists/1b9df3bc-5b01-47d3-8c37-ddff30c5c08d) (using Neo4j and Cypher queries).

#### Related work

[gMark](https://arxiv.org/abs/1511.08386) is a scalable graph generator tool, designed to allow **scalable** model generation. The user of can specify the number/proportion of the node/edge types and specify the distribution of edges (uniform, Gaussian, Zipfian), which allows the generation of graphs that are **realistic** to some degree.

[Xiao et al.](http://ieeexplore.ieee.org/document/7541886/)

[Xiao et al.](https://www.computer.org/csdl/proceedings/compsac/2014/3575/00/3575a011.pdf)
