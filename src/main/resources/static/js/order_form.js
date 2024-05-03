document.getElementById("order_form").addEventListener("submit", function(event) {
    event.preventDefault(); // Предотвращаем стандартное поведение формы
    let formData = new FormData(document.getElementById("order_form"));
    fetch("/postman/add", {
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