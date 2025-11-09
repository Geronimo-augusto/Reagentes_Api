// package: com.lumen.cp56.config
package com.lumen.cp56.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lumen.cp56.domian.model.Fabricante;
import com.lumen.cp56.domian.model.LocalizacaoEstoque;
import com.lumen.cp56.domian.model.MovimentacaoEstoque;
import com.lumen.cp56.domian.model.Reagente;
import com.lumen.cp56.domian.model.StatusReagente;
import com.lumen.cp56.domian.model.TipoLocalizacaoEstoque;
import com.lumen.cp56.domian.model.TipoMovimentacao;
import com.lumen.cp56.domian.repository.FabricanteRepository;
import com.lumen.cp56.domian.repository.LocalizacaoEstoqueRepository;
import com.lumen.cp56.domian.repository.MovimentacaoEstoqueRepository;
import com.lumen.cp56.domian.repository.ReagenteRepository;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    // Injetamos todos os repositórios que precisamos para popular
    private final FabricanteRepository fabricanteRepository;
    private final LocalizacaoEstoqueRepository localizacaoEstoqueRepository;
    private final ReagenteRepository reagenteRepository;
    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    @Override
    public void run(String... args) throws Exception {

        // Verifica se os dados já não foram carregados
        if (fabricanteRepository.findAll().isEmpty()) {
            log.info("Banco de dados em memória vazio. Populando com dados iniciais...");

            // --- 1. Criar Fabricantes ---
            Fabricante fab1 = new Fabricante();
            fab1.setNomeOficial("Roche Diagnóstica Brasil Ltda.");
            fab1.setNomeFantasia("Roche");
            fab1.setCnpj("30.280.358/0001-23");
            fab1.setPaisOrigem("Brasil");
            fabricanteRepository.save(fab1); 

            Fabricante fab2 = new Fabricante();
            fab2.setNomeOficial("Abbott Laboratórios do Brasil Ltda.");
            fab2.setNomeFantasia("Abbott");
            fab2.setCnpj("56.998.701/0001-46");
            fab2.setPaisOrigem("EUA");
            fabricanteRepository.save(fab2);

            // --- 2. Criar Localizações ---
            LocalizacaoEstoque loc1 = new LocalizacaoEstoque();
            loc1.setCodigoLocal("REF-01");
            loc1.setDescricao("Refrigerador 1 - Imunologia");
            loc1.setSetor("Imunologia");
            loc1.setFaixaTemperaturaNominal("2-8°C");
            loc1.setTipo(TipoLocalizacaoEstoque.REFRIGERADOR);
            localizacaoEstoqueRepository.save(loc1);

            LocalizacaoEstoque loc2 = new LocalizacaoEstoque();
            loc2.setCodigoLocal("FRZ-M20-01");
            loc2.setDescricao("Freezer -20°C - Bioquímica");
            loc2.setSetor("Bioquímica");
            loc2.setFaixaTemperaturaNominal("-25 a -15°C");
            loc2.setTipo(TipoLocalizacaoEstoque.FREEZER_MINUS20);
            localizacaoEstoqueRepository.save(loc2);

            // --- 3. Criar Reagentes (associando) ---
            Reagente reagente1 = new Reagente();
            reagente1.setNome("Elecsys Anti-HBs II");
            reagente1.setCodigoSku("04788737190");
            reagente1.setLote("LOTE-A123");
            reagente1.setDataValidade(LocalDate.now().plusMonths(6)); // Válido por 6 meses
            reagente1.setDataRecebimento(LocalDate.now().minusDays(10));
            reagente1.setStatus(StatusReagente.LIBERADO);
            reagente1.setQuantidadeEstoque(100); // Estoque inicial
            reagente1.setFabricante(fab1); // Associa ao fab1
            reagente1.setLocalizacaoEstoque(loc1); // Associa ao loc1
            reagenteRepository.save(reagente1);

            Reagente reagente2 = new Reagente();
            reagente2.setNome("Architect CK-MB");
            reagente2.setCodigoSku("2P36");
            reagente2.setLote("LOTE-B456");
            reagente2.setDataValidade(LocalDate.now().plusMonths(3)); // Válido por 3 meses
            reagente2.setDataRecebimento(LocalDate.now().minusDays(5));
            reagente2.setStatus(StatusReagente.QUARENTENA);
            reagente2.setQuantidadeEstoque(50); // Estoque inicial
            reagente2.setFabricante(fab2); // Associa ao fab2
            reagente2.setLocalizacaoEstoque(loc2); // Associa ao loc2
            reagenteRepository.save(reagente2);

            // --- 4. Criar Movimentações Iniciais (para justificar o estoque) ---
            MovimentacaoEstoque mov1 = new MovimentacaoEstoque();
            mov1.setId(UUID.randomUUID());
            mov1.setReagente(reagente1); // Associa ao reagente1
            mov1.setQuantidadeMovimentada(100); // Mesma qtd do estoque
            mov1.setTipo(TipoMovimentacao.ENTRADA_NOTA);
            mov1.setObservacao("Carga inicial do sistema.");
            mov1.setDataHoraMovimentacao(LocalDateTime.now().minusDays(10));
            movimentacaoEstoqueRepository.save(mov1);
            reagente1.getMovimentacoes().add(mov1); // Adiciona na lista do reagente

            MovimentacaoEstoque mov2 = new MovimentacaoEstoque();
            mov2.setId(UUID.randomUUID());
            mov2.setReagente(reagente2); // Associa ao reagente2
            mov2.setQuantidadeMovimentada(50); // Mesma qtd do estoque
            mov2.setTipo(TipoMovimentacao.ENTRADA_NOTA);
            mov2.setObservacao("Carga inicial do sistema.");
            mov2.setDataHoraMovimentacao(LocalDateTime.now().minusDays(5));
            movimentacaoEstoqueRepository.save(mov2);
            reagente2.getMovimentacoes().add(mov2); // Adiciona na lista do reagente

            log.info("... Carga de dados em memória concluída.");

        } else {
            log.info("Banco de dados já populado. Nenhuma ação necessária.");
        }
    }
}