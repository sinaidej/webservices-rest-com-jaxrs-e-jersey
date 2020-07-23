package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Projeto;
import junit.framework.Assert;



public class ClientTest {
	
	private HttpServer servidor; 
	private Client client;
	private WebTarget target;
	
	@Before
	public void iniciaServidor() {	
		this.servidor = Servidor.startaServidor();
		System.out.println("Servidor iniciando...");
		
		ClientConfig config  = new ClientConfig();
		config.register(new LoggingFilter());
		
		this.client = ClientBuilder.newClient(config);
		this.target = client.target("http://localhost:8080");
	}
	
	
	
	@After
	public void paraServidor() {
		this.servidor.stop();
		System.out.println("Servidor parando...");
	}
	
	@Test
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
		
		Carrinho carrinho = target.path("/carrinhos/1").request().get(Carrinho.class);
		
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar",carrinho.getRua());
	}
	
	@Test
	public void testaQueAConexaoComOServidorFuncionaPathProjetos() {
		
		Projeto projeto = target.path("/projetos/1").request().get(Projeto.class);
		Assert.assertEquals("Minha loja", projeto.getNome());
	}
	
	@Test
	public void testaSeOPostEstaFuncionando() {
		
		
		Carrinho carrinho = new Carrinho();
		carrinho.setCidade("Brasilia");
		carrinho.setId(5l);
		carrinho.setRua("Rua Alberico Rodrigues, 5");
			
		Entity<Carrinho> entity = Entity.entity(carrinho, MediaType.APPLICATION_XML);
		
		Response response = target.path("/carrinhos").request().post(entity);
				
		Assert.assertEquals(201, response.getStatus());
	}
	

}
