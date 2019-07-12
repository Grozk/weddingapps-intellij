package fr.grozk.perso.weddingapps.model.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelControlException extends RuntimeException {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 */
	private static final long serialVersionUID = 5560221950903523731L;
	private String fieldName;
    private String currentValue;
    private Object expectedValue;
    
    public ModelControlException( String fieldName, String currentValue, Object expectedValue) {
        super(String.format("%s with %s : format not correct, expected '%s'", fieldName, currentValue, expectedValue));
        LOGGER.error(String.format("%s with \"%s\" : format not correct, expected '%s'", fieldName, currentValue, expectedValue));
        this.fieldName = fieldName;
        this.currentValue = currentValue;
        this.expectedValue = expectedValue;
    }

	public String getFieldName() {
		return fieldName;
	}

	public String getCurrentValue() {
		return currentValue;
	}

	public Object getExpectedValue() {
		return expectedValue;
	}

}
