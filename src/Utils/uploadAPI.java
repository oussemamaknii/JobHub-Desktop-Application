package Utils;
import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 *
 */
public class uploadAPI {
    public static String upload(File x) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String fileName = "";
        try {
            HttpPost httppost = new HttpPost("http://localhost/savetofile.php");
            FileBody bin = new FileBody(x);
            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart("myFile", bin)
                    .build();
            httppost.setEntity(reqEntity);
            System.out.println("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    String responseXml = EntityUtils.toString(resEntity);
                    fileName+= responseXml;
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return fileName;
    }

}
