# Standalone application for CLI execution

Build the project, and move the files to the root of the repository.

```bash
cd $(git rev-parse --show-toplevel) && \
  rm -rf lib/ bin/ && \
  ./gradlew clean build installDist -x test && \
  cp -R analyzer-app/build/install/analyzer-app/. analyzer-app && \
  cd analyzer-app
```

Ensure that the JVM will have sufficient memory by using:

```bash
export JAVA_OPTS="-Xms4G -Xmx12G"
```

Run the analyzer application with:

```bash
bin/analyzer-app panama/panama
```
