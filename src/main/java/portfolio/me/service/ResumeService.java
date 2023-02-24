package portfolio.me.service;

import com.google.cloud.storage.Blob;

import java.io.IOException;

public interface ResumeService {

    Blob downloadCV(String fileName) throws IOException;
}
