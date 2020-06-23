package com.blobs.quickstart;

import java.io.File;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;

// https://docs.microsoft.com/en-us/azure/storage/blobs/storage-quickstart-blobs-java
public class App {

  public static void main(String[] args) {
    System.out.println("Azure Blob storage v12 - Java quickstart sample\n");

    // Retrieve the connection string for use with the application. The storage
    // connection string is stored in an environment variable on the machine
    // running the application called AZURE_STORAGE_CONNECTION_STRING. If the environment variable
    // is created after the application is launched in a console or with
    // Visual Studio, the shell or application needs to be closed and reloaded
    // to take the environment variable into account.
    String connectStr = System.getenv("AZURE_STORAGE_CONNECTION_STRING");
    System.out.println("connectStr ==>> " + connectStr);

    // Create a BlobServiceClient object which will be used to create a container client
    BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();
    System.out.println("blobServiceClient ==>> " + blobServiceClient);

    BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient("dev-configs");
    //    //Create a unique name for the container
    //    String containerName = "quickstartblobs" + java.util.UUID.randomUUID();
    //
    //    // Create the container and return a container client object
    //    BlobContainerClient containerClient = blobServiceClient.createBlobContainer(containerName);
    System.out.println("containerClient ==>> " + containerClient);

    System.out.println("\nListing blobs...");
    // List the blob(s) in the container.
    for (BlobItem blobItem : containerClient.listBlobs()) {
      System.out.println("\t" + blobItem.getName());
    }

    // Download the blob to a local file
    // Append the string "DOWNLOAD" before the .txt extension so that you can see both files.
    String fileName = "tmp1.txt";
    String localPath = "/home/jheckel/Dropbox/AzureDevops/maven-webapp/";
    String downloadFileName = fileName.replace(".txt", "DOWNLOAD.txt");
    File downloadedFile = new File(localPath + downloadFileName);
    if (downloadedFile.exists())
      downloadedFile.delete();
    System.out.println("\nDownloading blob to\n\t " + localPath + downloadFileName);
    BlobClient blobClient = containerClient.getBlobClient(fileName);
    System.out.println("blobClient ==>> " + blobClient);
    blobClient.downloadToFile(localPath + downloadFileName);
  }

}
