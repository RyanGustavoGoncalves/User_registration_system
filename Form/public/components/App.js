const formulario = document.querySelector("form");

const inpNome = document.getElementById("nome");
const inEmail = document.getElementById("email");
const inpSenha = document.getElementById("senha");

formulario.addEventListener('submit', function (event){
    event.preventDefault();
    
    cadastrar();
    limpar();
});

function limpar(){
    inpNome.value = "";
    inEmail.value = "";
    inpSenha.value = ""
}

function cadastrar() {
    fetch("http://localhost:8080/usuarios", {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            nome: inpNome.value,
            email: inEmail.value,
            senha: inpSenha.value
        })
    })
    .then(function (response) {
        if (response.status === 200) {
            // Sucesso: redirecionar ou fazer algo após o cadastro bem-sucedido
            console.log("Cadastro bem-sucedido!");
        } else if (response.status === 400) {
            // Erros de validação: exibir os erros no HTML
            response.json().then(function (errors) {
                var errorContainer = document.getElementById("error-container");
                var errorBox = document.getElementById("BoxError");
                errorBox.style.display = 'block'
                errorContainer.style.display = 'block';
                errorContainer.innerHTML = ""; // Limpar erros anteriores
                for (var fieldName in errors) {
                    var errorMessage = errors[fieldName];
                    var errorElement = document.createElement("div");
                    errorElement.classList.add('errorText');
                    errorElement.innerHTML = fieldName + ": " + errorMessage;
                    errorContainer.appendChild(errorElement);
                }
                var fecharErrorBox = document.getElementById('fecharErrorBox');
                fecharErrorBox.addEventListener('click', function (){
                    errorBox.style.display = 'none';
                    errorContainer.style.display = 'none';
                });
            });
        } else {
            // Outro erro: lidar com ele conforme necessário
            console.log("Ocorreu um erro inesperado: " + response.status);
        }
    })
    .catch(function (error) {
        console.log("Erro ao enviar a solicitação:", error);
    });
}
