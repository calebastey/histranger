package services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;


import java.io.File;

public class S3Service {

    private static AmazonS3 s3client() {
        return AmazonS3ClientBuilder.standard().withRegion(Regions.US_WEST_2).build();
    }

    public static String upload(File file) {
        AmazonS3 s3client = s3client();

        try {
            s3client.putObject(new PutObjectRequest("histranger", "test", file));

            return "SUCCESS";
        } catch (AmazonServiceException ex) {
            return String.format("Amazon S3 service exception [%s]", ex.getMessage());
        } catch (AmazonClientException ex) {
           return String.format("Amazon S3 client exception [%s]", ex.getMessage());
        }

    }

}
