import static org.junit.Assert.assertFalse;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

import com.serpro.depae.treinamento.webescola.domain.Disciplina;


@RunWith(DemoiselleRunner.class)
public class MatriculaTest {

	//se criarmos manualmente não serão injetadas as 
	//dependências.
	@Inject Disciplina disciplina;

	@Test
	public void matriculaAlunoComSucesso() {
		boolean r = disciplina.matricularAluno("Fulano de Tal");
	}
	
	
	@Test
	public void matricularAlunoDuasVezesFalha() {
		boolean r = disciplina.matricularAluno("Fulano de Tal");
		r = disciplina.matricularAluno("Fulano de Tal");
		assertFalse(r);
	}
	
	@Test
	public void matricularEmDisciplinaCheiaFalha() {
		disciplina.matricularAluno("Fulano de Tal 1");
		disciplina.matricularAluno("Fulano de Tal 2");
		disciplina.matricularAluno("Fulano de Tal 3");
		disciplina.matricularAluno("Fulano de Tal 4");
		disciplina.matricularAluno("Fulano de Tal 5");
		boolean r = disciplina.matricularAluno("Fulano de Tal 6");
		assertFalse(r);
	}
	
	
}
