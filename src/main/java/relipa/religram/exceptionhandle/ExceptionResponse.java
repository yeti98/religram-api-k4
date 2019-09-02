package relipa.religram.exceptionhandle;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExceptionResponse<Integer, T> {

	Integer statusCode;
	T message;

	public ExceptionResponse(Integer statusCode, T message) {
		this.message = message;
		this.statusCode = statusCode;
	}

}