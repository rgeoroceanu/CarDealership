package rgeoroceanu.cardealership.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import rgeoroceanu.cardealership.BaseTest;
import rgeoroceanu.cardealership.model.business.Car;
import rgeoroceanu.cardealership.model.business.Configuration;
import rgeoroceanu.cardealership.service.DataService;
import rgeoroceanu.cardealership.service.ImageService;
import rgeoroceanu.cardealership.service.exception.DataDoesNotExistException;
import rgeoroceanu.cardealership.service.exception.ImageDeleteException;
import rgeoroceanu.cardealership.service.exception.ImageWriteException;

/**
 * Test image service.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class ImageServiceTest extends BaseTest {
	
	@Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
	@Rule
	public ExpectedException exception = ExpectedException.none();
	@Autowired
	private ImageService imageService;
	@Value("classpath:images/test-image.jpg")
	private Resource testImageResource;
	@Value("classpath:images/test.txt")
	private Resource testFileResource;
	@Autowired
	private Configuration configuration;
	@Autowired
	private DataService dataService;
	
	@Before
	public void prepare() throws DataDoesNotExistException, IOException {
		File imagesFolder = testFolder.newFolder("images");	
		configuration.setImagesPath(imagesFolder.getAbsolutePath());
		configuration.setWebServerUrl("http://testurl.com");
	}
	
	@After
	public void clean() throws DataDoesNotExistException {
		final Car car = dataService.getCar(1l);
		car.getPreviewImages().clear();
		car.getFullImages().clear();
		dataService.saveCar(car);
	}
	
	@Test
	public void testSaveImages() throws ImageWriteException, IOException, DataDoesNotExistException {
		final String carImagePath = testFolder.getRoot().getAbsolutePath() + "/images/1";
		final File carImagesFolder = new File(carImagePath);
		imageService.saveImages(testImageResource.getFile(), 1l);
		final Car car = dataService.getCar(1L);
		assertThat(carImagesFolder.exists()).isTrue();
		assertThat(car.getFullImages()).isNotEmpty();
		assertThat(car.getPreviewImages()).isNotEmpty();
		assertThat(carImagesFolder.list()).hasSize(2);
	}
	
	@Test
	public void testSaveImagesInexistentCar() throws ImageWriteException, IOException {
		exception.expect(ImageWriteException.class);
		exception.expectMessage("Entry not found!");
		imageService.saveImages(testImageResource.getFile(), 123l);
	}
	
	@Test
	public void testSaveImagesNullImage() throws ImageWriteException, IOException {
		exception.expect(NullPointerException.class);
		imageService.saveImages(null, 1l);
	}
	
	@Test
	public void testSaveImagesWrongFile() throws ImageWriteException, IOException {
		exception.expect(ImageWriteException.class);
		exception.expectMessage("Cannot read image");
		imageService.saveImages(testFileResource.getFile(), 1l);
	}
	
	@Test
	public void testGetImageUrl() throws ImageWriteException, IOException {
		final String carImagePath = testFolder.getRoot().getAbsolutePath() + "/images/1";
		final File carImagesFolder = new File(carImagePath);
		imageService.saveImages(testImageResource.getFile(), 1l);
		final List<File> imageFiles = Arrays.asList(carImagesFolder.listFiles());
		for (final File imageFile : imageFiles) {
			final String filename = imageFile.getName();
			final String imageUrl = imageService.getImageUrl(filename, 1l);
			assertThat(imageUrl).isNotEmpty();
			assertThat(imageUrl).isEqualTo("http://testurl.com/" + carImagePath + "/" + filename);
		}
	}
	
	@Test
	public void testDeleteAllImages() throws ImageWriteException, IOException, ImageDeleteException, DataDoesNotExistException {
		final String carImagePath = testFolder.getRoot().getAbsolutePath() + "/images/1";
		final File carImagesFolder = new File(carImagePath);
		imageService.saveImages(testImageResource.getFile(), 1l);
		imageService.deleteAllImages(1l);
		assertThat(carImagesFolder.listFiles()).isEmpty();
	}
	
	@Test
	public void testDeleteImage() throws ImageWriteException, IOException, ImageDeleteException {
		final String carImagePath = testFolder.getRoot().getAbsolutePath() + "/images/1";
		final File carImagesFolder = new File(carImagePath);
		imageService.saveImages(testImageResource.getFile(), 1l);
		imageService.deleteImage(carImagesFolder.listFiles()[0].getName(), 1l);
		assertThat(carImagesFolder.listFiles()).hasSize(1);
	}
	
	@Test
	public void testGetPreviewImageUrls() throws ImageWriteException, IOException {
		imageService.saveImages(testImageResource.getFile(), 1l);
		List<String> imageUrls = imageService.getPreviewImageUrls(1l);
		assertThat(imageUrls).hasSize(1);
	}
	
	@Test
	public void testGetFullImageUrls() throws ImageWriteException, IOException {
		imageService.saveImages(testImageResource.getFile(), 1l);
		List<String> imageUrls = imageService.getFullImageUrls(1l);
		assertThat(imageUrls).hasSize(1);
	}
}
