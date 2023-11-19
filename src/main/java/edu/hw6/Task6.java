package edu.hw6;

import java.net.ServerSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MagicNumber")

public class Task6 {

    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 49151;

    private static final Logger LOGGER = LogManager.getLogger(Task6.class);
    private static final Map<Integer, String> KNOWN_PORTS = new HashMap<>();

    static {
        KNOWN_PORTS.put(135, "EPMAP");
        KNOWN_PORTS.put(137, "NetBIOS Name Service");
        KNOWN_PORTS.put(138, "NetBIOS Datagram Service");
        KNOWN_PORTS.put(139, "NetBIOS Session Service");
        KNOWN_PORTS.put(445, "Microsoft-DS Active Directory");
        KNOWN_PORTS.put(843, "Adobe Flash");
        KNOWN_PORTS.put(1900, "Simple Service Discovery Protocol (SSDP)");
        KNOWN_PORTS.put(3702, "Dynamic Web Services Discovery");
        KNOWN_PORTS.put(5040, "");
        KNOWN_PORTS.put(5050, "");
        KNOWN_PORTS.put(5353, "Multicast DNS");
        KNOWN_PORTS.put(5355, "Link-Local Multicast Name Resolution (LLMNR)");
        KNOWN_PORTS.put(5939, "");
        KNOWN_PORTS.put(6463, "");
        KNOWN_PORTS.put(6942, "");
        KNOWN_PORTS.put(17500, "Dropbox");
        KNOWN_PORTS.put(17600, "");
        KNOWN_PORTS.put(27017, "MongoDB");
        KNOWN_PORTS.put(42420, "");
    }

    private static void scanPorts() {
        LOGGER.info(String.format("%-9s%-7s%-40s%n", "Protocol", "Port", "Service"));

        for (int port = MIN_PORT; port <= MAX_PORT; port++) {
            scanPort("TCP", port);
            scanPort("UDP", port);
        }
    }

    private static void scanPort(String protocol, int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            LOGGER.info(String.format("%-9s%-7d%-40s%n", protocol, port, getServiceName(port)));
        } catch (SocketException e) {
            // Port is occupied
        } catch (Exception e) {
            LOGGER.error("Error scanning port: " + port, e);
        }
    }

    private static String getServiceName(int port) {
        return KNOWN_PORTS.getOrDefault(port, "");
    }
}

