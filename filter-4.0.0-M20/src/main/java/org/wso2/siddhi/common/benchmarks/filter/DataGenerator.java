/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.siddhi.common.benchmarks.filter;


import org.apache.log4j.Logger;
import org.wso2.siddhi.core.stream.input.InputHandler;

import java.util.Random;


/**
 * A data generator which uses random numbers.
 */
public class DataGenerator extends Thread {
    private InputHandler inputHandler;
    private static final Logger log = Logger.getLogger(DataGenerator.class);
    private boolean shutdownFlag = false;

    public DataGenerator(InputHandler inputHandler) {
        super("Data Generator");
        this.inputHandler = inputHandler;
    }

    public void run() {
        Random rand = new Random(123);
        int i = 0;
                        Object[] dataItem = new Object[]{System.currentTimeMillis(), rand.nextInt(10000)};

        while (!shutdownFlag) {
            try {
                inputHandler.send(dataItem);
                dataItem[0] = System.currentTimeMillis();
                dataItem[1] = rand.nextInt(10000);
                if (i % 1000 == 0) {
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                log.error("Error sending an event to Input Handler, " + e.getMessage(), e);
            }
            //log
            if (i % 1000000 == 0) {
                System.out.println("sent i : " + i);
            }
            i++;
        }
    }

    public void shutdown() {
        shutdownFlag = true;
    }
}
