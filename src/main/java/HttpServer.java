import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.net.ServerSocket;

public class HttpServer {
    private final int port;
    private final String directory;
    private static final Logger log = LogManager.getLogger(HttpServer.class);

    public HttpServer(int port, String directory) {
        this.port = port;
        this.directory = directory;
    }

    void start(){
        PropertyConfigurator.configure("/Users/Александр/Desktop/http-server-on-sockets-java-main/http-server-on-sockets-java-main/HttpServer/src/log4j.properties");
        log.info("Server started. Listening for connections on port :" + port);
        try (var server = new ServerSocket(this.port)){
            while (true){
                var socket= server.accept();
                var session = new Session(socket, this.directory);
                Thread thread = new Thread(session);
                thread.start();
            }

        } catch (IOException  exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        var port= 8080;
        var directory = "/Users/Александр/Desktop/http-server-on-sockets-java-main/http-server-on-sockets-java-main/HttpServer/files/";
        new HttpServer(port, directory).start();
    }
}
