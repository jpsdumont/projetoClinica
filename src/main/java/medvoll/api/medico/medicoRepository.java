package medvoll.api.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface medicoRepository extends JpaRepository<medico, Long> {

    Page<medico> findAllByAtivoTrue(Pageable paginacao);

}
