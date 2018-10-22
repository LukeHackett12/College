package cs.tcd.ie; /**
 *
 */

import tcdIO.Terminal;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


/**
 * cs.tcd.ie.Client class
 * <p>
 * An instance accepts user input
 */
public class Client extends Node {
    static final int DEFAULT_SRC_PORT = 50000;
    static final int DEFAULT_DST_PORT = 50001;
    static final String DEFAULT_DST_NODE = "localhost";

    Terminal terminal;
    InetSocketAddress dstAddress;

    /**
     * Constructor
     * <p>
     * Attempts to create socket at given port and create an InetSocketAddress for the destinations
     */
    Client(Terminal terminal, String dstHost, int dstPort, int srcPort) {
        try {
            this.terminal = terminal;
            dstAddress = new InetSocketAddress(dstHost, dstPort);
            socket = new DatagramSocket(srcPort);
            listener.go();
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method
     * <p>
     * Sends a packet to a given address
     */
    public static void main(String[] args) {
        try {
            Terminal terminal = new Terminal("cs.tcd.ie.Client");
            Terminal terminal2 = new Terminal("Resr");
            (new Client(terminal, DEFAULT_DST_NODE, DEFAULT_DST_PORT, DEFAULT_SRC_PORT)).start();
            (new Client(terminal2, DEFAULT_DST_NODE, DEFAULT_DST_PORT, DEFAULT_SRC_PORT)).start();
            terminal.println("Program completed");
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Assume that incoming packets contain a String and print the string.
     */
    public synchronized void onReceipt(DatagramPacket packet) {
        StringContent content = new StringContent(packet);
        this.notify();
        terminal.println(content.toString());
    }

    /**
     * Sender Method
     */
    public synchronized void start() throws Exception {
        byte[] data = null;
        DatagramPacket packet = null;

        data = (terminal.readString("String to send: ")).getBytes();

        terminal.println("Sending packet...");
        packet = new DatagramPacket(data, data.length, dstAddress);
        socket.send(packet);
        terminal.println("Packet sent");
        this.wait();
    }
}
