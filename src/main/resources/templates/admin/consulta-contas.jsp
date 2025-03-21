<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sistema Contas - CONSULTA</title>

<!-- Folha de estilos CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />

<link th:href="@{/css/style.css}" rel="stylesheet" />

</head>
<body>

	<!-- chama no formato thymeleaf a pagina menu.jsp -->
	<div th:replace="admin/components/menu :: menu"></div>

	<div class="container mt-3">
		<h4>Consulta de Contas</h4>
		<p>Pesquise as contas cadastradas de acordo com um periodo de datas.</p>
	</div>
	
	<!-- pego o atributo que criei no controlador-->
	<h5 th:if="${mensagem != null}" 
		th:text="${mensagem}" th:class="${mensagemTipo == 'erro' ? 'erro' : 'sucesso'}"></h5>
		
	<form id="formConsulta" action="consulta-contas-post" method="post" class="mt-2 mb-2">
		<div class="row">
			<div class="col-md-2">
				<label>Data de inicio:</label> 
				<input type="date" id="dataMin" name="dataMin" th:value="${dataMin}" class="form-control" />
			</div>
			<div class="col-md-2">
				<label>Data de fim:</label> 
				<input type="date" id="dataMax" name="dataMax" th:value="${dataMax}" class="form-control" />
			</div>
			<div class="col-md-8">
				<input type="submit" class="btn btn-success mt-4" value="Pesquisar contas" />
			</div>
		</div>
	</form>

	<div th:if="${contas != null and contas.size() > 0}" class="table-responsive mt-3">
    <table class="table table-sm">
        <thead>
            <tr>
                <th>Data</th>
                <th>Nome</th>
                <th>Valor</th>
                <th>Tipo</th>                
                <th>Descricao</th>
                <th>Operacoes</th>
            </tr>
        </thead>
        <tbody>
            <tr th:each="conta : ${contas}">
                <td th:text="${#dates.format(conta.data, 'dd/MM/yyyy')}"></td>
                <td th:text="${conta.nome}"></td>
                
				<td th:text="${#numbers.formatCurrency(conta.valor)}"></td>
                <td>
                    <span th:if="${conta.tipo == 1}" class="badge bg-success">RECEBER</span>
                    <span th:if="${conta.tipo == 2}" class="badge bg-danger">PAGAR</span>
                </td>
                <td th:text="${conta.descricao}"></td>
                <td>
                    <a th:href="@{/contas/admin/edicao-contas(id=${conta.id})}" 
                    	class="btn btn-outline-primary btn-sm me-2">
                    	Editar
                    </a>
					<a th:href="@{/contas/admin/exclusao-contas(id=${conta.id}, dataMin=${dataMin}, dataMax=${dataMax})}"
					   onclick="return confirm('Deseja excluir a conta selecionada?');"
					   class="btn btn-outline-danger btn-sm">
					   Excluir
					</a>
                </td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="6" th:text="'Quantidade de registros: ' + ${contas.size()}"></td>
            </tr>
        </tfoot>
    </table>
</div>

<div th:if="${contas == null or contas.size() == 0}" class="mt-3">
    Nenhum resultado foi encontrado para o filtro selecionado.
</div>


<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	
<!-- JQuery -->	
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	
<!--  JQuery Validation -->
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/additional-methods.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.21.0/localization/messages_pt_BR.min.js"></script>

<script>

//executando quando a p�gina abrir..
	$(document).ready(function(){ 
		$("#formConsulta").validate({
			rules: {			
				"dataMin" : {
					required: true
				},
				"dataMax" : {
					required: true
				}
			}
		});			
	});
</script>
</body>
</html>