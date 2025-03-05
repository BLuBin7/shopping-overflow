Middle ware

import logging

logger = logging.getLogger(__name__)

@app.exception_handler(AppException)
async def app_exception_handler(request: Request, exc: AppException):
    logger.error(f"Error {exc.status_code}: {exc.detail} | Trace ID: {exc.trace_id}")
    return JSONResponse(
        status_code=exc.status_code,
        content={
            "status_code": exc.status_code,
            "title": exc.title,
            "detail": exc.detail,
            "field_errors": exc.field_errors,
            "trace_id": exc.trace_id,
        }
    )

Base Excep

class AppException(Exception):
    def __init__(self, status_code: int, title: str, detail: str, field_errors: Optional[List[str]] = None):
        self.error_response = ErrorResponse(
            status_code=status_code,
            title=title,
            detail=detail,
            field_errors=field_errors or []
        )


Error Res

from typing import List, Optional
from pydantic import BaseModel
import uuid

class ErrorResponse(BaseModel):
    status_code: int
    title: str
    detail: str
    field_errors: Optional[List[str]] = []
    trace_id: str

    def __init__(self, **data):
        super().__init__(trace_id=str(uuid.uuid4()), **data)


router


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
        raise AppException(
            status_code=status_code,
            title="Unauthorized Request",
            detail=detail,
            trace_id="trace-12345"
        )

    # Get chat sessions of user
    chat_sessions, err = ChatService(db_session=db_session).get_chat_sessions(user_id=user.id)
    if err:
        status_code, detail = err.kind
        raise AppException(
            status_code=status_code,
            title="Database Error",
            detail=detail,
            trace_id="trace-67890"
        )

    # Parse chat sessions
    data = [ChatSessionResponse.model_validate(chat_session) for chat_session in chat_sessions] if chat_sessions else []

    return (
        BackendAPIResponse()
        .set_message(message=Constants.API_SUCCESS)
        .set_data(data=data)
        .respond()
    )