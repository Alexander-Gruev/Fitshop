$('#showOrders').click(() => {
    loadOrders()
});

function loadOrders() {
    $("#orders-container").empty();
    fetch("http://localhost:8080/orders/user").
    then(response => response.json()).
    then(json => json.forEach(o => {
        let liElement = document.createElement("li");
        liElement.textContent = `${o.productName} by ${o.productBrandName}
                                for address: ${o.address}. Order created at ${o.created}`;
         $("#orders-container").append(liElement);
    }))
}

