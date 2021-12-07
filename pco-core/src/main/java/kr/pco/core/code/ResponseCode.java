package kr.pco.core.code;

/**
 * @author chano
 *
 */
public enum ResponseCode {
	
	SERVER_ERROR("Server Error"),
	NOT_FOUND("Not Found"),
	BAD_REQUEST("Bad Request"),
	NOT_AUTORIZED("Not Authorized");
	
	private final String message;
	
	public String getMessage() {
		return message;
	}
	ResponseCode( final String message){
		this.message=message;
	}
	
}
