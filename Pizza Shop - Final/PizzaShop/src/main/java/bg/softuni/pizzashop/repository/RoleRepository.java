package bg.softuni.pizzashop.repository;

import bg.softuni.pizzashop.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE LOWER(r.role) = LOWER(:role)")
    Optional<Role> findByRole(@Param("role") String role);
}
