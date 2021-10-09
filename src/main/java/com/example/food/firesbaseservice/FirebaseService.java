package com.example.food.firesbaseservice;

import com.example.food.exception.FailToUploadException;
import com.example.food.exception.RestaurantException;
import com.example.food.model.Restaurant;
import com.example.food.model.RestaurantRequest;
import com.example.food.repository.RestaurantRepository;
import com.google.api.client.util.Base64;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FirebaseService {
		
		static final String FIREBASE_SDK_JSON = "C:\\Users\\Admin\\Documents\\workspace-spring-tool-suite-4-4.11.1.RELEASE\\food-dashboard-backend\\src\\main\\resources\\firebase.json";
		static final String FIREBASE_BUCKET = "food-dashboard-988db.appspot.com";
		static final String FIREBASE_PROJECT_ID ="food-dashboard-988db";
		static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/food-dashboard-988db.appspot.com/o/%s?alt=media";		

			
		    public String upload(RestaurantRequest restaurantRequest) throws IOException {
				
		        try {
		            String fileName = restaurantRequest.getFileName();                        			// to get original file name
		            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  		// to generated random string values for file name. 

		            File file = this.convertToAFile(restaurantRequest, fileName);                       // to convert to File
		            String TEMP_URL = this.uploadFile(file, fileName, restaurantRequest.getFileType()); // to get uploaded file link
		            file.delete();                                                            			// to delete the copy of uploaded file stored in the project folder
		            
		            return TEMP_URL;
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		            throw new FailToUploadException("Image Fail to Upload");
		        }

		    }
		    
		    private String uploadFile(File file, String fileName, String type) throws IOException {
		        BlobId blobId = BlobId.of(FIREBASE_BUCKET, fileName);
		        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(type).build();
		        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(FIREBASE_SDK_JSON));
		        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
		        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
					return String.format(DOWNLOAD_URL, java.net.URLEncoder.encode(fileName, StandardCharsets.UTF_8));
		        
		    }
		 
				 
		    
		    private File convertToAFile(RestaurantRequest restaurantRequest, String fileName) throws IOException {
		        File tempFile = new File(fileName);
		        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
		        	byte[] decodedBytes = java.util.Base64.getDecoder().decode(restaurantRequest.getBytes());
		            fos.write(decodedBytes);
		            fos.close();
		        }
		        return tempFile;
		    }
		    
		    private String getExtension(String fileName) {
		        return fileName.substring(fileName.lastIndexOf("."));
		    }
	
}
