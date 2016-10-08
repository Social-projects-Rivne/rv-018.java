package ua.softserve.rv_018.greentourism.service;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.softserve.rv_018.greentourism.model.Attachment;
import ua.softserve.rv_018.greentourism.model.Gallery;
import ua.softserve.rv_018.greentourism.repository.AttachmentRepository;
import ua.softserve.rv_018.greentourism.repository.GalleryRepository;

@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class AttachmentServiceImpl implements AttachmentService {
	/**
     * The logger service for logging purpose.
     */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
     * The Spring Data repository for Attachment entities.
     */
    @Autowired
    private AttachmentRepository attachmentRepository;
    
    @Autowired
    private GalleryRepository galleryRepository;

	@Override
	public Attachment create(Attachment attachment) {
		logger.info("> Attachment create");
		
		Attachment savedAttachment = attachmentRepository.save(attachment);
		
		logger.info("> Attachment created");
		
		return savedAttachment;
	}
	    
	@Override
	public List<Attachment> findByPlaceId(int id) {
		logger.info("> Attachment findByPlaceId");

		List<Attachment> attachments = attachmentRepository.findByPlaceId(id);

        logger.info("< Attachment findByPlaceId");
        
        return attachments;
	}
	
	@Override
	public List<Attachment> findByPlaceItemId(int id) {
		logger.info("> Attachment findByPlaceItemId {}", id);
		
		List<Gallery> galleries = galleryRepository.findByPlaceItemId(id);
		
		ArrayList<Attachment> attachments = new ArrayList<Attachment>();
		
		for(Gallery g : galleries) {
			attachments.add(g.getAttachment());
		}
		
		logger.info("< Attachment findByPlaceItemId {}", id);
		
		return attachments;
	}
	
	@Override
	public List<Attachment> findByEventItemId(int id) {
		logger.info("> Attachment findByEventItemId {}", id);
		
		List<Gallery> galleries = galleryRepository.findByEventItemId(id);
		
		ArrayList<Attachment> attachments = new ArrayList<Attachment>();
		
		for(Gallery g : galleries) {
			attachments.add(g.getAttachment());
		}
		
		logger.info("< Attachment findByEventItemId {}", id);
		
		return attachments;
	}
}
