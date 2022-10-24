package lv.javaguru.java2.library.core.database.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lv.javaguru.java2.library.core.domain.Reader;

@Repository
public interface JpaReaderRepository extends JpaRepository<Reader, Long> {


}
