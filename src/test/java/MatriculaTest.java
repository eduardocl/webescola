import static org.junit.Assert.assertFalse;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

import com.serpro.depae.treinamento.webescola.domain.Disciplina;
import com.serpro.depae.treinamento.webescola.exception.AlunoDuplicadoException;
import com.serpro.depae.treinamento.webescola.exception.DisciplinaLotadaException;


@RunWith(DemoiselleRunner.class)
public class MatriculaTest {

	//se criarmos manualmente não serão injetadas as 
	//dependências.
	@Inject Disciplina disciplina;

	@Test
	public void matriculaAlunoComSucesso() {
		disciplina.matricularAluno("Fulano de Tal");
	}
	
	
	@Test(expected=AlunoDuplicadoException.class)
	public void matricularAlunoDuasVezesFalha() {
		disciplina.matricularAluno("Fulano de Tal");
		disciplina.matricularAluno("Fulano de Tal");
	}
	
	@Test(expected=DisciplinaLotadaException.class)
	public void matricularEmDisciplinaCheiaFalha() {
		disciplina.matricularAluno("Fulano de Tal 1");
		disciplina.matricularAluno("Fulano de Tal 2");
		disciplina.matricularAluno("Fulano de Tal 3");
		disciplina.matricularAluno("Fulano de Tal 4");
		disciplina.matricularAluno("Fulano de Tal 5");
		disciplina.matricularAluno("Fulano de Tal 6");
	}
	
}
