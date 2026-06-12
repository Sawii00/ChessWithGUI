#!/usr/bin/env bash
set -euo pipefail

if ! command -v mvn >/dev/null 2>&1; then
  echo "Apache Maven (mvn) is required to build and run this project." >&2
  exit 1
fi

if [ ! -f pom.xml ]; then
  echo "No pom.xml found in current directory: $(pwd)" >&2
  exit 1
fi

MODE="${1:-run}"

if [ "$MODE" = "build" ]; then
  mvn -q -DskipTests compile
  echo "Build complete."
  exit 0
fi

if [ "$MODE" = "package" ]; then
  mvn -q -DskipTests package
  echo "Packaging complete."
  exit 0
fi

if [ "$MODE" = "test" ]; then
  mvn -q test
  exit 0
fi

# Default: run the app
mvn -q -DskipTests compile

mkdir -p target/deps
mvn -q -DincludeScope=runtime -DincludeTypes=jar -DskipTests dependency:copy-dependencies -DoutputDirectory=target/deps

# Ensure Maven resolved JavaFX modules.
if [ -z "$(ls -1 target/deps 2>/dev/null | head -n 1)" ]; then
  echo "No runtime dependencies were copied. Ensure dependencies resolved successfully." >&2
  exit 1
fi

java --module-path target/deps --add-modules=javafx.controls,javafx.fxml -cp target/classes application.Main
