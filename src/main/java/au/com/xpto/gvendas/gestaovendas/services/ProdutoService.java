package au.com.xpto.gvendas.gestaovendas.services;

import au.com.xpto.gvendas.gestaovendas.entities.Produto;
import au.com.xpto.gvendas.gestaovendas.exceptions.RegraNegocioException;
import au.com.xpto.gvendas.gestaovendas.repositories.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public Produto salvar(Long codigoCategoria, Produto produto){
        this.validarCategoriaDoProdutoExiste(codigoCategoria);
        this.validarProdutoDuplicado(produto);
        return this.produtoRepository.save(produto);
    }

    public Produto atualizar(Long codigoCategoria, Long codigoProduto, Produto produto){
        Produto produtoSalvar = validarProdutoExiste(codigoProduto, codigoCategoria);//validate if product exists
        this.validarCategoriaDoProdutoExiste(codigoCategoria);
        this.validarProdutoDuplicado(produto);
        BeanUtils.copyProperties(produto, produtoSalvar, "codigo");//Copying produto attributes into produtoSalvar but ignoring 'codigo' attribute.
        return this.produtoRepository.save(produtoSalvar);
    }

    public void deletar(Long codigoCategoria, Long codigoProduto){
        this.produtoRepository.delete(this.validarProdutoExiste(codigoProduto, codigoCategoria));
    }

    private Produto validarProdutoExiste(Long codigoProduto, Long codigoCategoria) {
        Optional<Produto> produto = this.buscarPorCodigo(codigoProduto, codigoCategoria);
        if(!produto.isPresent()){
            throw new EmptyResultDataAccessException(1);
        }
        return produto.get();
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
        Optional<Produto> produtoPorDescricao = this.produtoRepository.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao());
        if(produtoPorDescricao.isPresent() && produtoPorDescricao.get().getCodigo() != produto.getCodigo()){
            throw new RegraNegocioException(String.format("O produto %s ja esta cadastrado", produto.getDescricao()));
        }

    }


}
