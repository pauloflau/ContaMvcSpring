<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sistema Contas - DASHBOARD</title>

<!-- Folha de estilos CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />

<link th:href="@{/css/style.css}" rel="stylesheet" />

</head>
<body>
<!-- chama no formato thymeleaf a pagina menu.jsp -->
  <div th:replace="admin/components/menu :: menu"></div>
	
	<div class="container mt-3">
		<h4>Dashboard principal</h4>
		<p>Seja bem vindo ao sistema.</p>
	</div>
	<div class="row mb-5">
			<div class="col">				
				<p>
					<strong>Resumo financeiro do mes de <span th:text="${#dates.format(dataAtual, 'MMMM/yyyy')}"></span></strong> 
				</p>				
				<table class="table table-sm table-bordered">
					<tbody>
						<tr>
							<td>Total de contas a receber:</td>
							<td th:text="${#numbers.formatCurrency(totalContasReceber)}"></td>
						</tr>
						<tr>
							<td>Total de contas a pagar:</td>
							<td th:text="${#numbers.formatCurrency(totalContasPagar)}"></td>
						</tr>
						<tr>
							<td>Saldo:</td>
							<td th:text="${#numbers.formatCurrency(saldo)}"></td>
						</tr>
						<tr>
							<td>Situacao:</td>
							<td th:text="${situacao}"></td>
						</tr>
					</tbody>
				</table>				
			</div>
			<div class="col">
				<div id="grafico">
				        <div class="chart-container">
            				<div id="grafico" class="chart"></div>
        				</div>
				</div>
			</div>
		</div>		
	
	

<!-- Bootstrap JS -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"> </script>
	
<!-- Highcharts JS -->	
 	<script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.highcharts.com/highcharts-more.js"></script>
    <script src="https://code.highcharts.com/modules/series-label.js"></script>
    
    <script>
            Highcharts.chart('grafico', {
                chart: {
                    type: 'pie',
                },
                title: {
                    text: 'Contas a Pagar e a Receber'
                },
                plotOptions: {
                    pie: {
                        innerSize: '50%', // Faz o gráfico ser no estilo donut
                        depth: 45,
                        dataLabels: {
                            enabled: true,
                            format: '{point.name}: {point.percentage:.1f}%'
                        }
                    }
                },
                series: [{
                    name: 'Contas',
                    data: [
                        { name: 'Contas a Receber', y: [[${totalContasReceber}]] },
                        { name: 'Contas a Pagar', y: [[${totalContasPagar}]] }
                    ]
                }],
            });
        </script>
</body>
</html>