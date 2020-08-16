# Trabalho 01 - Room, SQLite e RecyclerView

### Disciplina: 
Dispositivos Móveis

### Informações:
Usando RecyclerView, CardView e Room, melhore o seu aplicativo To Do List.

Não haverão mais os campos para inclusão de Tarefas fora do RecyclerView.
Ao abrir o app, o usuário se deparará apenas com o RecyclerView e um botão de adicionar.
Ao clicar em adicionar, um card é adicionado ao RecyclerView (mas não ainda ao BD), contendo os campos de título e descrição (multilinha), bem como um botão (imagem) de salvar.
Ao salvar, a Tarefa é persistida no BD e o Card passa a apresentar um formato compacto da mesma, apenas com o título (não editável).
Ao clicar na Tarefa, a mesma é reaberta para edição no próprio CardView, como na inserção, podendo Salvar e voltando ao estado compacto.
Durante a edição, o usuário também vê um botão de Excluir (usar uma imagem), que remove a Tarefa do BD e o card da lista.
Ao clique longo no CardView compacto, a tarefa deve ser marcada como Feita (ou desfeita, caso já esteja feita), devendo ser utilizadas ferramentas gráficas que mostrem bem isto ao usuário (exemplos: riscar texto, mudar cor, etc).
Uma tarefa feita deve mostrar um botão de compartilhar (usar uma imagem) ao lado. Ao clicar, deve-se iniciar o compartilhamento de texto com outros aplicativos, compartilhando o seguinte: "Oba! Acabei de concluir: <Título da Tarefa>".
Observações:

Para mostrar um item da lista em diferentes formatos (compacto / edição) usar diferentes layouts XML. Sobrescrever o método getItemViewType() do Adapter pode ajudar bastante.
As ações de Inserir, Remover e Editar NÃO PODEM RECARREGAR TODOS OS DADOS, devendo ser detectado o item adicionado, removido ou alterado e utilizados os métodos "notifyItem...()". Faça isto para garantir as corretas animações do RecyclerView.
Utilizar apenas ícones com imagens vetoriais.
TODAS Strings de usuário devem ser internacionalizáveis, com inglês como padrão e mais dois idiomas criados.
Utilizar ConstraintLayout.
O app deve funcionar bem nas diferentes resoluções e orientações de dispositivos.
Mais Observações

Deve ser feito individualmente.
O trabalho deve ser enviado até o dia 16/08/2020 via GitHub (enviar link aqui).
Passível de validação pelo professor (formato a definir).
