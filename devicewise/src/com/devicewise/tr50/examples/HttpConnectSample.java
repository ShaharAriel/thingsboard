/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 ILS Technology, LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.devicewise.tr50.examples;

import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.api.response.session.DwOpenSessionInfo;
import com.devicewise.tr50.api.response.thing.DwOpenThingList;
import com.devicewise.tr50.clients.DwHttpClient;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.protocol.DwOpenWorker;


import java.io.IOException;
import java.util.LinkedHashMap;

public class HttpConnectSample {


    public static String HTTP_URL = "https://api.devicewise.com/api";

    private static String sessionId;

    public static String USER = "sariel@ravtech.co.il";

    public static String PASSWORD = "Sariel79!";

    private static String thingKey;

    private static String appToken;

    public static void main(String[] args)  {


        try {

            int ret = 0;

            ret = http_connect_as_user();
            if (ret != 0)
                System.out.println("HTTP connect as User failed with error code" + ret);

            ret = http_connect_as_method();
            if (ret != 0)
                System.out.println("HTTP connect as Thing failed with error code " + ret);

//			ret = http_connect_with_session_id();
            if (ret != 0)
                System.out.println("HTTP connect with Session Id failed with error code " + ret);


        } catch (IOException | DwOpenException e) {
            e.printStackTrace();
        }

    }

    public static int http_connect_as_user() throws IOException, DwOpenException {

        if (USER == null || PASSWORD == null) {
            System.out.println("No User And/or Password Specified. Skipping http_connect_as_user()");
            return (-1);
        }

        int ret = 0;
        DwHttpClient client = new DwHttpClient();

        ret = client.initialize(HTTP_URL);
        if (ret == 0)
            ret = client.authenticate(USER, PASSWORD);

        DwOpenSessionInfo session = new DwOpenSessionInfo();
        DwOpenThingList thing = new DwOpenThingList();
        client.getWorker().Session().orgSwitch("ROKEHA", session);
        client.getWorker().Thing().list(0, 100, null, null, null, null, thing);
        client.getWorker().Session().info(session);

        if (thing.isSuccess()) {

            System.out.println("User " + session.getUserName() + " Connected!");

            System.out.println("Session Protocol: " + session.getConnInfo().getProtocol());
            System.out.println("Remote Addr     : " + session.getConnInfo().getRemoteAddr());
            System.out.println("Org Key         : " + thing.getResult().get(0).getDefKey());
            System.out.println("Org Key         : " + thing.getResult().get(1).getDefKey());
            System.out.println("Org Key         : " + thing.getResult().get(2).getDefKey());
        } else {
            System.out.println("Failed to get session info with Error: " + session.getErrorcodes()[0] + " [" + session.getErrormessages()[0] + "]");
        }

        System.out.println(thing.getJsonResponse());
        return ret;
    }

    public static int http_connect_as_method() throws IOException, DwOpenException {

        DwHttpClient httpClient;
        try {
            httpClient = new DwHttpClient();
            httpClient.initialize(HTTP_URL);
            httpClient.authenticate(USER, PASSWORD);
            DwOpenResponse response = new DwOpenResponse();
            DwOpenWorker worker = httpClient.getWorker();
            DwOpenSessionInfo session = new DwOpenSessionInfo();
            worker.Session().orgSwitch("ROKEHA", session);

            LinkedHashMap<String, Object> method = new LinkedHashMap<>();

            method.put("ctu", "id(2) gnrcprgm udb(9) prt(5) sampf(2)");
            worker.Method().exec("351580051597645", "ctu"
                    , false, 20, method, response);
            System.out.println(response.getJsonResponse());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DwOpenException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static int http_connect_as_thing() throws IOException, DwOpenException {

        if (thingKey == null || appToken == null) {
            System.out.println("No ThingKey And/or AppToken Specified. Skipping http_connect_as_thing()");
            return (-1);
        }

        int ret = 0;
        DwHttpClient client = new DwHttpClient();
        ret = client.initialize(HTTP_URL);
        if (ret == 0)
            ret = client.authenticate(USER, PASSWORD);

        DwOpenSessionInfo session = new DwOpenSessionInfo();
        client.getWorker().Session().info(session);

        if (session.isSuccess()) {

            System.out.println("Thing " + session.getThingKey() + " Connected!");

            System.out.println("Session Protocol: " + session.getConnInfo().getProtocol());
            System.out.println("Remote Addr     : " + session.getConnInfo().getRemoteAddr());
            System.out.println("Org Key         : " + session.getOrgKey());
            sessionId = session.getId();
        } else {
            System.out.println("Failed to get session info with Error: " + session.getErrorcodes()[0] + " [" + session.getErrormessages()[0] + "]");
        }

        System.out.println();
        return ret;

    }

    public static int http_connect_with_session_id(String sessionId) throws IOException, DwOpenException {

        if (sessionId == null) {
            System.out.println("Session ID not set! Skipping Connect with Session ID");
            return (-1);
        }

        int ret = 0;
        DwHttpClient client = new DwHttpClient();
        ret = client.initialize(HTTP_URL);
        if (ret == 0)
            client.setSessionId(sessionId);

        DwOpenSessionInfo session = new DwOpenSessionInfo();
        client.getWorker().Session().info(session);

        if (session.isSuccess()) {

            System.out.println("Session " + session.getId() + " Connected!");

            System.out.println("Session Protocol: " + session.getConnInfo().getProtocol());
            System.out.println("Remote Addr     : " + session.getConnInfo().getRemoteAddr());
            System.out.println("Org Key         : " + session.getOrgKey());
        } else {
            System.out.println("Failed to get session info with Error: " + session.getErrorcodes()[0] + " [" + session.getErrormessages()[0] + "]");
        }

        System.out.println();
        return ret;

    }

    public static void parseArgs(String[] args) throws Exception {

        for (int i = 0; i < args.length; i++) {

            if ("-o".equals(args[i])) {
                setHttpUrl(args[++i]);
            } else if ("-u".equals(args[i])) {
                setUser(args[++i]);
            } else if ("-p".equals(args[i])) {
                setPassword(args[++i]);
            } else if ("-t".equals(args[i])) {
                setThingKey(args[++i]);
            } else if ("-a".equals(args[i])) {
                setAppToken(args[++i]);
            } else if ("-s".equals(args[i])) {
                setSessionId(args[++i]);
            } else throw new Exception("Unknown argument: " + args[i]);
        }
    }

    public static void printHelpInfo() {

        System.out.println("Usage: -o openserverurl [-a appToken] [-t thingKey] [-u username] [-p password] [-s sessionId] ");

        System.out.println("\t-o\tOpen Server URL");
        System.out.println("\t-t\tThing Key to Connect to Open Server (As Thing)");
        System.out.println("\t-a\tApplication Token to Connect to Open Server (As Thing)");
        System.out.println("\t-u\tUsername to Connect to Open Server (As User)");
        System.out.println("\t-p\tPassword to Connect To Open Server (As User)");
        System.out.println("\t-s\tSession Id");
        System.out.println("");

    }

    public static String getHttpUrl() {

        return HTTP_URL;
    }

    public static void setHttpUrl(String _httpUrl) {

        HTTP_URL = _httpUrl;
    }

    public String getSessionId() {

        return sessionId;
    }

    public static void setSessionId(String _sessionId) {

        sessionId = _sessionId;
    }

    public String getUser() {

        return USER;
    }

    public static void setUser(String _user) {

        USER = _user;
    }

    public String getPassword() {

        return PASSWORD;
    }

    public static void setPassword(String _password) {

        PASSWORD = _password;
    }

    public String getThingKey() {

        return thingKey;
    }

    public static void setThingKey(String _thingKey) {

        thingKey = _thingKey;
    }

    public String getAppToken() {

        return appToken;
    }

    public static void setAppToken(String _appToken) {

        appToken = _appToken;
    }

}
