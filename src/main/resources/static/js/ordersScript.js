// $('#loadOrders').click(() => {
//     loadOrders()
// });
//
// function loadOrders() {
//     $("#orders-container").empty();
//
//     fetch("http://localhost:8080/orders/all").
//     then(response => response.json()).
//     then(json => json.forEach(order => {
//         let tableRow = '<tr>' +
//             '<td>' + order.productName + '</td>' +
//             '<td>' + order.clientFullName + '</td>' +
//             '<td>' + order.created+ '</td>' +
//             '</tr>'
//         $("#orders-container").append(tableRow)
//     }))
// }

