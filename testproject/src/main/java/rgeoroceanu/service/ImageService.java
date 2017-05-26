package rgeoroceanu.service;

import java.io.File;
import java.util.List;

import rgeoroceanu.service.exception.ImageDeleteException;
import rgeoroceanu.service.exception.ImageWriteException;

/**
 * Service that handles {@link Car} image operations.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public interface ImageService {
	
	/**
	 * Save preview and full images for a certain car given its id and source image.
	 * @param sourceImageFile of the image to save
	 * @param carId to which the images belong.
	 * @throws ImageWriteException in case the image cannot be saved.
	 */
	public void saveImages(final File sourceImageFile, final Long carId) throws ImageWriteException;
	
	/**
	 * Retrieve image url given the image filename and {@link Car} id.
	 * @param filename of the image.
	 * @param carId to which the filename belongs.
	 * @return full url of the image.
	 */
	public String getImageUrl(final String filename, final Long carId);
	
	/**
	 * Delete all images associated with a {@link Car}.
	 * @param carId of which to delete the images.
	 * @throws ImageDeleteException thrown in case there was a problem deleting an image.
	 */
	public void deleteAllImages(final Long carId) throws ImageDeleteException;
	
	/**
	 * Delete a single image file.
	 * @param filename of the image to delete.
	 * @param carId of the {@link Car} to which the image belongs.
	 * @throws ImageDeleteException thrown in case there was a problem deleting the image.
	 */
	public void deleteImage(final String filename, final Long carId) throws ImageDeleteException;
	
	/**
	 * Retrieve the urls of all preview images for a certain {@link Car}.
	 * @param carId from which to retrieve the images.
	 * @return list of preview image urls.
	 */
	public List<String> getPreviewImageUrls(final Long carId);
	
	/**
	 * Retrieve the urls of all full images for a certain {@link Car}.
	 * @param carId from which to retrieve the images.
	 * @return list of full image urls.
	 */
	public List<String> getFullImageUrls(final Long carId);
}
