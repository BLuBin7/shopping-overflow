class Constants:
    # Error Handler
    API_SUCCESS = "Success"
    INVALID_REQUEST_MESSAGE = "Invalid request"
    UNAUTHORIZED_REQUEST_MESSAGE = "Unauthorized request"
    FORBIDDEN_REQUEST_MESSAGE = "Forbidden request"
    NOT_FOUND_MESSAGE = "Not found"
    USER_NOT_FOUND_MESSAGE = "User not found"
    INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error"
    EMPTY_CHAT_MESSAGE_MESSAGE = "Empty chat message"

class ErrorCodesMappingNumber(Enum):
    INVALID_REQUEST = (400, Constants.INVALID_REQUEST_MESSAGE)
    UNAUTHORIZED_REQUEST = (401, Constants.UNAUTHORIZED_REQUEST_MESSAGE)
    FORBIDDEN_REQUEST = (403, Constants.FORBIDDEN_REQUEST_MESSAGE)
    NOT_FOUND = (404, Constants.NOT_FOUND_MESSAGE)
    INTERNAL_SERVER_ERROR = (500, Constants.INTERNAL_SERVER_ERROR_MESSAGE)

    # TODO: Think about adding more error codes here
    AGENT_NOT_FOUND = (404, "Agent not specified or found for chat session")
    STARTER_MESSAGE_NOT_FOUND = (404, "Starter message not found")
    FOLDER_NOT_FOUND = (404, "Folder not found")
    CONNECTOR_NOT_FOUND = (404, "Connector not found")
    CHAT_SESSION_NOT_FOUND = (404, "Chat session not found")
    CHAT_MESSAGE_NOT_FOUND = (404, "Chat message not found")
    UNABLE_TO_UPLOAD_FILE_TO_MINIO = (404, "Unable to upload file to Minio")
    UNABLE_TO_DELETE_FILE_FROM_MINIO = (404, "Unable to delete file from Minio")
    LLM_PROVIDER_NOT_FOUND = (404, "LLM provider not found")
    USER_SETTING_NOT_FOUND = (404, "User setting not found")
    EMBEDDING_PROVIDER_NOT_FOUND = (404, "Embedding provider not found")
    PROVIDER_TYPE_CHANGE_NOT_ALLOWED = (422, "Provider type change not allowed")

    NO_CONTENT = (204, "No content found")

    USER_WRONG_LOGIN_METHOD = (405, "User already exists with wrong login method")


class BaseException(Exception):
    """
    Custom exception class for handling errors
    """

    def __init__(self, message: str, detail: str = None):
        super().__init__(message)
        self._message = message
        self._detail = detail

    def __str__(self):
        if self._detail:
            return f"{self._message}: {self._detail}"
        return self._message

@router.get("/chat-sessions", response_model=APIResponse, status_code=status.HTTP_200_OK)
def get_chat_sessions(
    db_session: Session = Depends(get_db_session), user: User = Depends(get_current_user_from_token)
) -> BackendAPIResponse:
    """
    Get all chat sessions of the user.

    Args:
        db_session (Session): Database session. Defaults to relational database session.
        user (User): User object

    Returns:
        BackendAPIResponse: API response.
    """
    if not user:
        status_code, detail = ErrorCodesMappingNumber.UNAUTHORIZED_REQUEST.value
        raise HTTPException(status_code=status_code, detail=detail)

    # Get chat sessions of user
    chat_sessions, err = ChatService(db_session=db_session).get_chat_sessions(user_id=user.id)
    if err:
        status_code, detail = err.kind
        raise HTTPException(status_code=status_code, detail=detail)

    # Parse chat sessions
    if chat_sessions:
        data = [ChatSessionResponse.model_validate(chat_session) for chat_session in chat_sessions]
    else:
        data = []

    return (
        BackendAPIResponse()
        .set_message(message=Constants.API_SUCCESS)
        .set_data(data=data)
        .respond()
    )

