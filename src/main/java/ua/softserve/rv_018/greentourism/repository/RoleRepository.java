/*package ua.softserve.rv_018.greentourism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.softserve.rv_018.greentourism.model.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findAll();
	
	@Query(value="SELECT ud.role_id as id, ur.name from user_detail ud inner join user_role ur on ud.role_id = ur.id where ud.id = :user_id"
			, nativeQuery=true)
	Role findByUserId(
			@Param ("user_id") Long id);
}
*/