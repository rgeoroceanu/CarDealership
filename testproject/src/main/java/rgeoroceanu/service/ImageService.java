package rgeoroceanu.service;

import java.io.File;
import java.util.List;

import rgeoroceanu.service.exception.ImageDeleteException;
import rgeoroceanu.service.exception.ImageWriteException;

public interface ImageService {
	
	public void saveImages(final File sourceImageFile, final Long carId) throws ImageWriteException;
	public String getImageUrl(final String filename, final Long carId);
	public void deleteAllImages(final Long carId) throws ImageDeleteException;
	public void deleteImage(final String filename, final Long carId) throws ImageDeleteException;
	public List<String> getPreviewImageUrls(final Long carId);
	public List<String> getFullImageUrls(final Long carId);
}
