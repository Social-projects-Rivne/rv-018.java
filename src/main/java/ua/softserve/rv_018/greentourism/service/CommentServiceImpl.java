package ua.softserve.rv_018.greentourism.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.softserve.rv_018.greentourism.model.Comment;
import ua.softserve.rv_018.greentourism.repository.CommentRepository;

@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class CommentServiceImpl implements CommentService {
	/**
	 * The logger service for logging purpose.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * The Spring Data repository for Comment entities.
	 */
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public Comment create(Comment comment) {
		logger.info("> Comment create");
		
		Comment savedComment = commentRepository.save(comment);
		
		logger.info("> Comment create");
		
		return savedComment;
	}
	
	@Override
	public List<Comment> findByPlaceId(int id) {
		logger.info("> Comment findByPlaceId");

		List<Comment> comments = commentRepository.findByPlaceId(id);

        logger.info("< Comment findByPlaceId");
        
        return comments;
	}
}
