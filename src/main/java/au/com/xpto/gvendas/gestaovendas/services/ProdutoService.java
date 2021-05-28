package au.com.xpto.gvendas.gestaovendas.services;

import au.com.xpto.gvendas.gestaovendas.entities.Produto;
import au.com.xpto.gvendas.gestaovendas.exceptions.RegraNegocioException;
import au.com.xpto.gvendas.gestaovendas.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaService categoriaService;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaService categoriaService) {
        this.produtoRepository = produtoRepository;
        this.categoriaService = categoriaService;
    }

    public List<Produto> listarTodos(Long codigoCategoria){
        return this.produtoRepository.findByCategoriaCodigo(codigoCategoria);
    }

    public Optional<Produto> buscarPorCodigo(Long codigo, Long codigoCategoria){
        return this.produtoRepository.buscarPorCodigo(codigo, codigoCategoria);
    }

    public Produto salvar(Produto produto){
        this.validarCategoriaDoProdutoExiste(produto.getCategoria().getCodigo());
        this.validarProdutoDuplicado(produto);
        return this.produtoRepository.save(produto);
    }

    private void validarCategoriaDoProdutoExiste(Long codigoCategoria){
        if(codigoCategoria == null){
            throw new RegraNegocioException("Categoria nao pode ser nula");
        }

        if(!this.categoriaService.buscarPorCodigo(codigoCategoria).isPresent()){
            throw new RegraNegocioException(String.format("A categoria de codigo %s nao existe no cadastro", codigoCategoria));
        }
    }

    private void validarProdutoDuplicado(Produto produto){
        if(this.produtoRepository.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao()).isPresent()){
            throw new RegraNegocioException(String.format("O produto %s ja esta cadastrado", produto.getDescricao()));
        }

    }


}
