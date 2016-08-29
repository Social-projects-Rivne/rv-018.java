package ua.softserve.rv_018.greentourism.service;

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
	 * The Spring Data repository for Events entities.
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
}
