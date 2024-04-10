document.getElementById("postForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Предотвращаем стандартное поведение формы
    let formData = new FormData(document.getElementById("postForm"));
    fetch("/products", {
        method: "POST",
        body: formData
    }).then(response => {
        // Обработка ответа, если необходимо
        console.log("POST запрос выполнен успешно");
    }).catch(error => {
        console.error('Ошибка при отправке запроса:', error);
    });
        event.target.reset();
    });