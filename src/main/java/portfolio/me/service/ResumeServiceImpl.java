package portfolio.me.service;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ResumeServiceImpl implements ResumeService {

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }


    @Override
    public Blob downloadCV(String fileName) throws IOException {
        System.out.println(11111);
        String destFileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));
        System.out.println(222222);
        String destFilePath =  destFileName;

        ////////////////////////////////   Download  /////////////////////////////////////////////////////////////////
        Credentials credentials = GoogleCredentials.fromStream
                (new FileInputStream("src/main/resources/taha-s-portfolio-firebase-adminsdk-vwvbi-e240e6b6fa.json"));
        System.out.println(333333);
        System.out.println(credentials.toString());
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        System.out.println(44444);
        System.out.println(storage.toString());
        Blob blob = storage.get(BlobId.of("taha-s-portfolio.appspot.com", fileName));
        System.out.println(blob.getBucket() +" 555555555");
        blob.downloadTo(Paths.get(destFilePath));
        System.out.println(7777777);
         return blob;

    }
}
