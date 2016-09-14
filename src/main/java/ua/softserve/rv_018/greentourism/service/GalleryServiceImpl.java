package ua.softserve.rv_018.greentourism.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.softserve.rv_018.greentourism.model.Gallery;
import ua.softserve.rv_018.greentourism.repository.GalleryRepository;

@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class GalleryServiceImpl implements GalleryService{
	/**
     * The logger service for logging purpose.
     */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
     * The Spring Data repository for Gallery entities.
     */
    @Autowired
    private GalleryRepository galleryRepository;

	@Override
	public Gallery create(Gallery gallery) {
		logger.info("> Gallery create");
		
		Gallery savedGallery = galleryRepository.save(gallery);
		
		logger.info("> Gallery created");
		
		return savedGallery;
	}
}
