document.getElementById("registration").addEventListener("submit", function(event) {
    event.preventDefault(); // Предотвращаем стандартное поведение формы
    let formData = new FormData(document.getElementById("registration"));
    fetch("/registration_page", {
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