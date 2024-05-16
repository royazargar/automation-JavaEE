package com.mftplus.automation.controller.session;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionListener implements HttpSessionListener {
    //for admin page

    private static int online;
    private static int visited;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("SessionListener - session created");
        online++;
        visited++;
        log.info("Num of Visited : " + visited);
        log.info("Num of Online : " + online);
        HttpSessionListener.super.sessionCreated(se);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("SessionListener - session destroyed");
        online--;
        HttpSessionListener.super.sessionDestroyed(se);
    }

    public SessionListener() {
    }

    public static int getOnline() {
        return online;
    }

    public static void setOnline(int online) {
        SessionListener.online = online;
    }

    public static int getVisited() {
        return visited;
    }

    public static void setVisited(int visited) {
        SessionListener.visited = visited;
    }
}
