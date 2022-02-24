package psensor.net.wpitchoune.client;

import org.hsh.ms.common.ep.client.BaseRestClient;
import org.hsh.ms.common.web.MimeTypes;

import javax.net.ssl.HostnameVerifier;
import java.util.concurrent.ExecutorService;

/*
/api/1.1/sensors
/api/1.1/sensors/[id]
/api/1.1/sysinfo
/api/1.1/cpu/usage
*/
public class PSensorClient extends BaseRestClient {
    public PSensorClient(String baseUri,
                         long connectionTimeoutMillis,
                         long readTimeoutMillis,
                         HostnameVerifier sslHostnameVerifier,
                         ExecutorService executorService) {
        super(baseUri, connectionTimeoutMillis, readTimeoutMillis, MimeTypes.Application.JSON, sslHostnameVerifier, executorService);
    }

//    public
}
