/**
 * Public client class of the Universal Controller
 * library. Provides APIs access of the library.
 */
public class UCClient {

    private static volatile UCClient instance;

    private String remoteAddr;
    private int remotePort;
    private int localPort;

    /**
     * Default constructor.
     */
    private UCClient() {
    }

    /**
     * Constructor with predefine server IP address, and
     * port number on both server & client.
     *
     * @param remoteAddr IPv4 address of the server
     * @param remotePort port number of the server
     * @param localPort  port number of the client
     */
    private UCClient(String remoteAddr, int remotePort, int localPort) {
        this.remoteAddr = remoteAddr;
        this.remotePort = remotePort;
        this.localPort = localPort;
    }

    /**
     * Static initialisation method of UCClient. This
     * is the default method to initial an new instance.
     *
     * @return instance of UCClient
     */
    public static UCClient init() {

        // lazy initialisation
        if (instance == null) {

            // utilising DCL idiom to ensure only
            // one instance is being created
            synchronized (UCClient.class) {

                // must check again as one of the blocked
                // threads can still enter this block
                if (instance == null)
                    instance = new UCClient(); // safe
            } // end synchronized
        } // end if

        return instance;
    }

    /**
     * Alternative static init method of UCClient with
     * server address, and port number on both server
     * & client set.
     *
     * @param remoteAddr IPv4 address of the server
     * @param remotePort port number of the server
     * @param localPort  port number of the client
     * @return instance of UCClient with
     * parameters set
     */
    public static UCClient init(String remoteAddr, int remotePort,
                                int localPort) {

        init();

        instance.remoteAddr = remoteAddr;
        instance.remotePort = remotePort;
        instance.localPort = localPort;

        return instance;
    }

    /**
     * @return the instance that currently pointing to
     */
    public UCClient getInstance() {
        return instance;
    }

    /**
     * Reset the static instance reference to null
     * in case there is any need for an new instance.
     */
    public void destroy() {
        instance = null;
    }

}