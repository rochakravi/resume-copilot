#!/bin/sh
# Minimal Gradle wrapper shim: invoke the wrapper JAR directly to avoid shell script corruption.
# Requires: gradle/wrapper/gradle-wrapper.jar exists.
DIRNAME=$(dirname "$0")
JARPATH="$DIRNAME/gradle/wrapper/gradle-wrapper.jar"
if [ ! -f "$JARPATH" ]; then
  echo "Gradle wrapper JAR not found at $JARPATH" >&2
  exit 1
fi
exec java -jar "$JARPATH" "$@"
