package com.esnuguel.inicio.common.mediator;
//T request
// R Response
public interface RequestHandler<T extends Request<R>,R> {
    R handle(T request);
    Class<T> getRequestType();
}
