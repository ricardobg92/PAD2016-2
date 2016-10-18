# Programação de Alto Desempenho
Alunos: Matheus Ronsani de Figueiredo e Ricardo Bianchim Gomes    
Professora Andrea Schwertner Charão  
2016/2  

##Ferramentas de Detecção de DataRacing
Códigos paralelos, em geral, são difíceis de depurar, sendo que a identificação de um data race pode não ser trivial. Desta forma, torna-se necessária a utilização de ferramentas automatizadas para detecção de data races.  
Os Exemplos apresentados no arquivo [Exemplos.md](./Exemplos.md) foram submetidos a análise em ferramentas de detecção de data race: [ValGrind](http://valgrind.org/docs/manual/drd-manual.html) e [Oracle Developer Studio 12.5](https://www.oracle.com/tools/developerstudio/index.html) (antigo Sun/Oracle Solaris Studio). A seguir serão apresentados as telas dos programas, juntamente com os resultados obtidos através das verificações.

##1. Apresentação da IDE Oracle Developer Studio (ODS)

A figura 1 apresenta uma visão geral de uma das ferramentas utilizadas, a ODS. Nota-se uma interface muito similar à da IDE NetBeans. Os campos enumerados na imagem são:  
 1) Projetos abertos: contém todos os projetos abertos e a árvore de arquivos a ele pertencentes.  
 2) Barra de arquivos abertos e área de edição;
 3) Botão analisar programa: abre o menu de seleção de tipo de análise;  
 4) Seleção de tipo de análise: permite selecionar diversos tipos de análise de execução dos códigos. Em especial, estamos interessados na análise de data races.
![Figura-1](Imagens/Apresentacao-solStudio.png)


