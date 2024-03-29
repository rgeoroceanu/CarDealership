package rgeoroceanu.cardealership.service;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import rgeoroceanu.cardealership.model.business.Car;
import rgeoroceanu.cardealership.model.business.Configuration;
import rgeoroceanu.cardealership.service.exception.DataDoesNotExistException;
import rgeoroceanu.cardealership.service.exception.ImageDeleteException;
import rgeoroceanu.cardealership.service.exception.ImageWriteException;

@Service
public class ImageServiceImpl implements ImageService {
	
	private static final Logger LOG = Logger.getLogger(ImageServiceImpl.class);
	private static final String IMAGE_EXTENSION = ".jpg";
	private static final char[] FILENAME_CHARS = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
	private static final int FULL_IMAGE_WIDTH = 1024;
	private static final int PREVIEW_IMAGE_WIDTH = 400;
	private final Random random;
	@Autowired
	private Configuration configuration;
	@Autowired
	private DataService dataService;
	
	public ImageServiceImpl() {
		random = new Random();
	}
	
	@Override
	public void saveImages(final File sourceImageFile, final Long carId) throws ImageWriteException {
		LOG.info("Save images for car id " + carId);
		Preconditions.checkNotNull(sourceImageFile);
		Preconditions.checkNotNull(carId);
		
		final String carImagesFolderPath = getCarFolderPath(carId);
		final String previewFilename = generateFilename();
		final String fullFilename = generateFilename();
		final File previewDestinationFile = new File(carImagesFolderPath + "/" + previewFilename);
		final File fullDestinationFile = new File(carImagesFolderPath + "/" + fullFilename);
		final File fullDestinationFolder = fullDestinationFile.getParentFile();
		
		if (fullDestinationFolder.exists() == false) {
			boolean success = fullDestinationFolder.mkdirs();
			if (success == false) {
				LOG.error("Error saving images for car id " + carId + "! Cannot create directories!");
				throw new ImageWriteException("Error saving images for car id " + carId + "! Reason: cannot create directories");
			}
		}
		
		final File previewDestinationFolder = previewDestinationFile.getParentFile();
		if (previewDestinationFolder.exists() == false) {
			boolean success = previewDestinationFolder.mkdirs();
			if (success == false) {
				LOG.error("Error saving images for car id " + carId + "! Cannot create directories!");
				throw new ImageWriteException("Error saving images for car id " + carId + "! Reason: cannot create directories");
			}
		}
		
		try {
			// write full image
			writeImageFile(sourceImageFile, fullDestinationFile, FULL_IMAGE_WIDTH);
			// write preview image
			writeImageFile(sourceImageFile, previewDestinationFile, PREVIEW_IMAGE_WIDTH);
		} catch (IOException e) {
			LOG.error("Error saving images for car id " + carId);
			throw new ImageWriteException("Error saving images for car id " + carId + "! Reason: " + e.getMessage());
		}
		
		try {
			addFullImageFilename(fullFilename, carId);
			addPreviewImageFilename(previewFilename, carId);
		} catch (DataDoesNotExistException e) {
			LOG.error("Error saving images for car id " + carId);
			throw new ImageWriteException("Error saving images for car id " + carId + "! Reason: " + e.getMessage());
		}
	}

	@Override
	public String getImageUrl(final String filename, final Long carId) {
		Preconditions.checkNotNull(filename);
		Preconditions.checkNotNull(carId);
		
		return getCarFolderUrl(carId) + "/" + filename;
	}

	@Override
	public void deleteAllImages(final Long carId) throws ImageDeleteException {
		Preconditions.checkNotNull(carId);
		
		final String folderPath = getCarFolderPath(carId);

		try {
			FileUtils.cleanDirectory(Paths.get(folderPath).toFile());
		} catch (IOException e) {
			LOG.error("Error deleting image folder " + folderPath + " for car id " + carId);
			throw new ImageDeleteException("Error deleting image! Reason: " + e.getMessage());
		}
	}

	@Override
	public void deleteImage(final String filename, final Long carId) throws ImageDeleteException {
		Preconditions.checkNotNull(filename);
		Preconditions.checkNotNull(carId);
		
		final String folderPath = getCarFolderPath(carId);

		try {
			Files.deleteIfExists(Paths.get(folderPath + "/" + filename));
		} catch (IOException e) {
			LOG.error("Error deleting image " + filename + " for car id " + carId);
			throw new ImageDeleteException("Error deleting image! Reason: " + e.getMessage());
		}
	}
	
	@Override
	public List<String> getPreviewImageUrls(final Long carId) {
		Preconditions.checkNotNull(carId);
		
		final List<String> urls = new ArrayList<>();
		Car car;
		try {
			car = dataService.getCar(carId);
		} catch (DataDoesNotExistException e) {
			return Collections.emptyList();
		}
		
		for (final String filename : car.getPreviewImages()) {
			final String url = this.getImageUrl(filename, car.getId());
			urls.add(url);
		}
		return urls;
	}
	
	@Override
	public List<String> getFullImageUrls(final Long carId) {
		Preconditions.checkNotNull(carId);
		
		final List<String> urls = new ArrayList<>();
		Car car;
		try {
			car = dataService.getCar(carId);
		} catch (DataDoesNotExistException e) {
			return Collections.emptyList();
		}
		
		for (final String filename : car.getFullImages()) {
			final String url = this.getImageUrl(filename, car.getId());
			urls.add(url);
		}
		return urls;
	}
	
	private String getCarFolderPath(final Long carId) {
		final String imagesPath = configuration.getImagesFolderPath();
		return imagesPath + "/" + carId;
	}
	
	private String getCarFolderUrl(final Long carId) {
		final String webServerUrl = configuration.getWebServerUrl();
		final String imagesPath = configuration.getImagesUrlPath();
		return webServerUrl + "/" + imagesPath + "/" + carId;
	}
	
	private String generateFilename() {
		final StringBuilder fileneme = new StringBuilder();
		for (int i = 0; i < 15; i++) {
		    final char c = FILENAME_CHARS[random.nextInt(FILENAME_CHARS.length)];
		    fileneme.append(c);
		}
		fileneme.append(IMAGE_EXTENSION);
		return fileneme.toString();
	}
	
	private void writeImageFile(final File sourceImageFile, final File destinationFile, final int imageWidth) throws IOException {
		Preconditions.checkNotNull(sourceImageFile);
		Preconditions.checkNotNull(destinationFile);
		Preconditions.checkArgument(imageWidth > 0, "Image width must be greated than 0!");
		
		final BufferedImage sourceImage = ImageIO.read(sourceImageFile); 
		
		if (sourceImage == null ) {
			throw new IOException("Cannot read image from file!");
		}
		
		final BufferedImage resizedImage = resizeImage(sourceImage, imageWidth);
		
		if (resizedImage == null ) {
			throw new IOException("Cannot resize image from file!");
		}
		
		ImageIO.write(resizedImage, "JPEG", destinationFile);
	}
	
	private BufferedImage resizeImage(final BufferedImage sourceImage, int newWidth) {
		final double ratio = (double) sourceImage.getWidth() / sourceImage.getHeight();
		final int newHeight = (int) (newWidth / ratio);
		final BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

		final Graphics g = resizedImage.createGraphics();
		g.drawImage(sourceImage, 0, 0, newWidth, newHeight, null);
		g.dispose();
		
		return resizedImage;
	}
	
	private void addFullImageFilename(final String imageFilename, final Long carId) throws DataDoesNotExistException {
		final Car car = dataService.getCar(carId);
		car.addFullImage(imageFilename);
		dataService.saveCar(car);
	}
	
	private void addPreviewImageFilename(final String imageFilename, final Long carId) throws DataDoesNotExistException {
		final Car car = dataService.getCar(carId);
		car.addPreviewImage(imageFilename);
		dataService.saveCar(car);
	}
}
