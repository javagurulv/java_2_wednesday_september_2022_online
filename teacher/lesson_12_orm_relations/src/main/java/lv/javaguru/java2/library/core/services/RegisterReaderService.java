package lv.javaguru.java2.library.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lv.javaguru.java2.library.core.database.ReaderRepository;
import lv.javaguru.java2.library.core.domain.Reader;
import lv.javaguru.java2.library.core.requests.RegisterReaderRequest;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.core.responses.RegisterReaderResponse;
import lv.javaguru.java2.library.core.services.validators.RegisterReaderRequestValidator;

@Component
@Transactional
public class RegisterReaderService {

	@Autowired private ReaderRepository readerRepository;
	@Autowired private RegisterReaderRequestValidator validator;

	public RegisterReaderResponse execute(RegisterReaderRequest request) {
		List<CoreError> errors = validator.validate(request);
		if (!errors.isEmpty()) {
			return new RegisterReaderResponse(errors);
		}

		Reader reader = new Reader(request.getFirstName(), request.getLastName());
		readerRepository.save(reader);

		return new RegisterReaderResponse(reader);
	}

}