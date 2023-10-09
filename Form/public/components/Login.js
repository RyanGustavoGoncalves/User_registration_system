document.querySelector("form").addEventListener("submit", function (event) {
    event.preventDefault();
    const inpNome = document.getElementById("nome").value;
    const inpSenha = document.getElementById("senha").value;
    const inpId= document.getElementById("id").value;


    function limpar() {
        document.getElementById("nome").value = "";
        document.getElementById("senha").value = "";
        document.getElementById("id").value = "";
    }
    
    fetch("http://localhost:8080/usuarios/login", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            nome: inpNome,
            senha: inpSenha,
            id: inpId
        }),
    })
    .then((response) => {
        if (response.status === 200) {
            // Login bem-sucedido, redirecionar para a página de perfil ou outra ação apropriada
            window.location.href = "Home.html"; // Substitua pelo redirecionamento desejado
        } else if (response.status === 401) {
            // Credenciais inválidas, exibir uma mensagem de erro ao usuário
            var errorContainer = document.getElementById("error-container");
            var errorBox = document.getElementById("BoxError");
            errorBox.style.display = 'block'
            errorContainer.style.display = 'block';
            errorContainer.innerHTML = "Nome do Usuario ou senha incorretos!";
        } else {
            // Outro erro, lide com ele conforme necessário
            console.error("Erro ao fazer login:", response.statusText);
        }
        document.getElementById('fecharErrorBox').addEventListener('click', function (){
            errorBox.style.display = 'none';
            errorContainer.style.display = 'none';
        });
        limpar(); // Limpar os campos após o login (independente do resultado)
    })
    .catch((error) => {
        console.error("Erro ao fazer login:", error);
        limpar(); // Limpar os campos em caso de erro
    });
});
