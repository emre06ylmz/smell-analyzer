package com.eylmz.master.sonar.client.integration.shell;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShellIntegrator {

    private static final Logger logger = LogManager.getLogger(ShellIntegrator.class);

    public void runCommand() throws IOException, InterruptedException {

        String cmd = "ls -al";
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);

        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line=buf.readLine())!=null) {
            logger.info(line);
        }

    }

}
