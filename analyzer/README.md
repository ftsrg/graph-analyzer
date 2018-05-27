# Standalone application for CLI execution

Build the project, and move the files to the root of the repository.

```bash
cd $(git rev-parse --show-toplevel) && \
  rm -rf lib/ bin/ && \
  ./gradlew clean build installDist -x test && \
  cp -R analyzer/build/install/analyzer/. analyzer && \
  cd analyzer
```

Ensure that the JVM will have sufficient memory by using:

```bash
export JAVA_OPTS="-Xms4G -Xmx12G"
```

Run the analyzer with:

```bash
bin/analyzer panama/panama
```
