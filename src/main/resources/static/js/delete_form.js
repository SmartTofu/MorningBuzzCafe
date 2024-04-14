document.getElementById("deleteForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Предотвращаем стандартное поведение формы
    let delData = new FormData(document.getElementById("deleteForm"));
    fetch("/admin", {
        method: "DELETE",
        body: delData
    }).then(response => {
        // Обработка ответа, если необходимо
        console.log("DELETE запрос выполнен успешно");
    }).catch(error => {
        console.error('Ошибка при отправке запроса:', error);
    });
        event.target.reset();
    });