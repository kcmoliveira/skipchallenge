#!/bin/bash

java -classpath jooq-3.11.0.jar:jooq-meta-3.11.0.jar:jooq-codegen-3.11.0.jar:mysql-connector-java-5.1.46.jar:. org.jooq.codegen.GenerationTool skipthedishes.xml