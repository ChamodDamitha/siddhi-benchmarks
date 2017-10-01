#!/bin/bash
# ---------------------------------------------------------------------------
#  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
#
#  Licensed under the Apache License, Version 2.0 (the "License");
#  you may not use this file except in compliance with the License.
#  You may obtain a copy of the License at
#
#  http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.
# -----------------------------------------------------------------------------	
CLASSPATH=.:target/classes:target/filter-4.0.0-M20-1.0-SNAPSHOT-jar-with-dependencies.jar:/home/gwthamy/projects/streamperf/git/siddhi-benchmarks/filter-4.0.0-M20/lib/HdrHistogram.jar:siddhi-execution-approximate-1.0.2-SNAPSHOT.jar:streaminer-1.1.1.jar:siddhi-execution-math-4.0.4-SNAPSHOT.jar:t-digest-3.1.jar
JAVA_OPTS="-Xmx8g -Xms8g"
FULL_EXPERIMENT_DURATION_MINUTES=30
WARM_UP_PERIOD_MINS=10

java -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:StartFlightRecording=settings=profile,duration=1800s,dumponexit=true,filename=approximate-count-test-1-10-2017.jfr $JAVA_OPTS -cp $CLASSPATH  org.wso2.siddhi.common.benchmarks.filter.SiddhiFilterBenchmark $FULL_EXPERIMENT_DURATION_MINUTES $WARM_UP_PERIOD_MINS
