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

function cadastrar(){
    fetch("http://localhost:8080/usuarios",
    {
        headers:{
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify ({
            nome: inpNome.value,
            email: inEmail.value,
            senha: inpSenha.value
        })
    })
    .then(function (res) {console.log(res)})
    .catch(function (res) {console.log(res)})
}