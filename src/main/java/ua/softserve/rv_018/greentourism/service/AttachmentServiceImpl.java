package ua.softserve.rv_018.greentourism.service;

import static org.mockito.Matchers.intThat;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.softserve.rv_018.greentourism.model.Attachment;
import ua.softserve.rv_018.greentourism.repository.AttachmentRepository;

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
}
