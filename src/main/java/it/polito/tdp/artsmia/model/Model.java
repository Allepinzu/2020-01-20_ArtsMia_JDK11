package it.polito.tdp.artsmia.model;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {

	ArtsmiaDAO dao = new ArtsmiaDAO();
	Graph <Artist,DefaultWeightedEdge >grafo;
	Map<Integer,Artist> idMap;
	private double peso;
	
	List<Artist> listaMigliori;
	List<Artist> prova;
	public List<String> getRole() {
		return dao.listRoles();
		
	}
	
	
	public List<Artist> ListaMigliori(int a){
		
		listaMigliori = new LinkedList<Artist>();
		prova = new LinkedList<Artist>();
		Artist artist=idMap.get(a);
		prova.add(artist);
		ricorsione(prova, 0);
		return listaMigliori;
		
	}
	
	public void ricorsione(List<Artist> prova , int livello) {
		if(prova.size()>listaMigliori.size()) {
			listaMigliori.clear();
			listaMigliori.addAll(prova);
			
		}
		boolean b=false;
		for(Artist a:Graphs.neighborListOf(grafo,prova.get(livello))) {
			
			if(prova.size()==1) {
				prova.add(a);
				peso=grafo.getEdgeWeight(grafo.getEdge(a,prova.get(0)));
				b=true;
				ricorsione(prova,livello+1);
				prova.remove(livello+1);
			}
			else if(!prova.contains(a) && grafo.getEdgeWeight(grafo.getEdge(a,prova.get(livello)))==peso) {
				
				prova.add(a);
			   b=true;
				ricorsione(prova,livello+1);
				prova.remove(livello+1);
				
			}
			
			
			
		}
		
			return;
		
		
			
			
		
	}
	
	public void creaGrafo(String role) {
		
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		idMap= new LinkedHashMap<>();
		
		Graphs.addAllVertices(grafo, dao.listArtist(role));
		
		for(Artist a:grafo.vertexSet()) {
			idMap.put(a.getId(), a);
			
		}
		
		for(Arco ar:dao.listArchi(role, idMap)) {
			if(!grafo.containsEdge(grafo.getEdge(ar.getA1(), ar.getA2()))&&!grafo.containsEdge(grafo.getEdge(ar.getA2(), ar.getA1()))) {
				Graphs.addEdge(grafo, ar.getA1(), ar.getA2(), ar.getPeso());
				
			}
			
		}
		
	}
	
	public String VA() {
		
		String s= "NUM VER ARC " + grafo.vertexSet().size()+" "+ grafo.edgeSet().size()+"\n";
		return s;
	}
	
	public String edgeSet() {
		String s="";
		for(DefaultWeightedEdge de:grafo.edgeSet()) {
			s =s+ grafo.getEdgeTarget(de).getNome() +" "+ grafo.getEdgeSource(de).getNome()+ " "+ grafo.getEdgeWeight(de)+"\n";
			
		}
		
		return s;
		
	}
	
	
}
