# Metric Based Analyzer

[![Build Status](https://travis-ci.org/FTSRG/model-analyzer.svg?branch=incremental)](https://travis-ci.org/FTSRG/model-analyzer)

## Prerequisites

Environment:
 * Java 8
 * [Eclipse Modeling Tools - Mars](http://www.eclipse.org/downloads/packages/eclipse-modeling-tools/mars2)

## Usage from source

Required third-party plug-ins:
 * Goggle Guava 15.0.0 - one possible installation is from the [Orbit update site](http://download.eclipse.org/tools/orbit/downloads/drops/R20151221205849/repository/)
 * For test purposes: [TestNG 6.9.12](http://testng.org/doc/index.html)

To use MBA, clone the project and import the plugins directory to the workspace. Use the **Import -> Import as Existing Projects into Workspace** option, select the `plugins/` directory and select the **Search for nested projects option** on the same page.

It is also possible to the projects as Maven projects. For further information about Maven and Tycho, please visit [Maven-and-Eclipse](https://github.com/FTSRG/cheat-sheets/wiki/Maven-and-Eclipse).

## Install to Eclipse

Clone the project and build the project: `cd plugins && mvn clean install`

After the build ended succesfully, go to Eclipse and open **Help -> Install New Software**. **Add** a repository by choosing **Local** and navigate to the `releng/hu.bme.mit.mba.updatesite/target/repository` directory. After adding the repository, an MBA category should appear with the features **MBA Base** and **MBA Model Analyzer**. Choose and install **MBA Model Analyzer.**

## Use the library

For examples, please visit [Model Analyzer Examples](https://github.com/ZsoltKovari/model-analyzer-examples).
