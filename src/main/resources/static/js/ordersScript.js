let btn = $('#showOrders');

btn.click(() => {
    handleOrdersClick(event)
});

function handleOrdersClick(event) {
    let btnText = event.target.textContent;

    if(btnText === 'Show my orders') {
        fetch("http://localhost:8080/orders/user").
        then(response => response.json()).
        then(json => json.forEach(o => {
        let liElement = document.createElement("li");
        liElement.textContent = `${o.productName} by ${o.productBrandName}
                                for address: ${o.address}. Order created at ${o.created}`;
         $("#orders-container").append(liElement);
        }));
        btn.text('Hide my orders');
    } else if (btnText === 'Hide my orders') {
        $("#orders-container").empty();
        btn.text('Show my orders');
    }

}
