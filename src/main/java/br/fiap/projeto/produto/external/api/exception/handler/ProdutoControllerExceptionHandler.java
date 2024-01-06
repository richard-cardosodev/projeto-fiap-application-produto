package br.fiap.projeto.produto.external.api.exception.handler;

import br.fiap.projeto.produto.external.api.ProdutoApiController;
import br.fiap.projeto.produto.external.api.exception.ProdutoResponseException;
import br.fiap.projeto.produto.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.produto.usecase.exception.ProdutoNaoEncontradoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = ProdutoApiController.class)
public class ProdutoControllerExceptionHandler {
    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ProdutoResponseException> handleProdutoNaoEncontradoException(Exception e) {
        ProdutoResponseException response = new ProdutoResponseException(3001, e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(EntradaInvalidaException.class)
    public ResponseEntity<ProdutoResponseException> handleEntradaInvalidaException(Exception e) {
        ProdutoResponseException response = new ProdutoResponseException(3003, e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ProdutoResponseException> handleDataIntegrityViolationException(Exception e) {
        ProdutoResponseException response = new ProdutoResponseException(3002, e.getMessage(), e.getCause());
        return ResponseEntity.unprocessableEntity().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProdutoResponseException> handleException(Exception e) {
        e.printStackTrace();
        ProdutoResponseException response = new ProdutoResponseException(3000, e.getMessage(), e.getCause());
        return ResponseEntity.internalServerError().body(response);
    }
}
