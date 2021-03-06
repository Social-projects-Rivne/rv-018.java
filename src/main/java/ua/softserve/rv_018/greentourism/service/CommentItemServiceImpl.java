package ua.softserve.rv_018.greentourism.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.softserve.rv_018.greentourism.model.CommentItem;
import ua.softserve.rv_018.greentourism.repository.CommentItemRepository;

@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class CommentItemServiceImpl implements CommentItemService{
	/**
     * The logger service for logging purpose.
     */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
     * The Spring Data repository for CommentItem entities.
     */
    @Autowired
    private CommentItemRepository commentItemRepository;

	@Override
	public CommentItem create(CommentItem commentItem) {
		logger.info("> CommentItem create");
		
		CommentItem savedCommentItem = commentItemRepository.save(commentItem);
		
		logger.info("> CommentItem created");
		
		return savedCommentItem;
	}
}
