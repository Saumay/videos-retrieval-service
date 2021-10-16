package com.fampay.assignment.videosretrievalserviceserver.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VideosRetrievalWorker implements DisposableBean, Runnable {

    private Thread thread;
    private volatile boolean runningCondition = true;

    VideosRetrievalWorker() {
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override public void run() {
        while (runningCondition) {
            sleep(10000);
            log.debug("Running");
        }
    }

    @Override public void destroy() {
        runningCondition = false;
    }

    private void sleep(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
