package br.fiap.projeto.produto.external.api.exception.handler;

import br.fiap.projeto.produto.entity.Produto;
import br.fiap.projeto.produto.external.api.ProdutoApiController;
import br.fiap.projeto.produto.external.api.exception.ProdutoResponseError;
import br.fiap.projeto.produto.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.produto.usecase.exception.ProdutoDuplicadoException;
import br.fiap.projeto.produto.usecase.exception.ProdutoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = ProdutoApiController.class)
public class ProdutoControllerExceptionHandler {
    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ProdutoResponseError> handleProdutoNaoEncontradoException(Exception e) {
        ProdutoResponseError response = new ProdutoResponseError(3001, e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(EntradaInvalidaException.class)
    public ResponseEntity<ProdutoResponseError> handleEntradaInvalidaException(Exception e) {
        ProdutoResponseError response = new ProdutoResponseError(3003, e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(response);
    }

    @ExceptionHandler(ProdutoDuplicadoException.class)
    public ResponseEntity<Produto> handleProdutoDuplicadoException(ProdutoDuplicadoException e) {
        ProdutoResponseError response = new ProdutoResponseError(3002, e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getProdutoExistente());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProdutoResponseError> handleException(Exception e) {
        e.printStackTrace();
        ProdutoResponseError response = new ProdutoResponseError(3000, e.getMessage(), e.getCause());
        return ResponseEntity.internalServerError().body(response);
    }
}
