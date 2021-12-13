const ordersList = document.getElementById('tbody')
const searchBar = document.getElementById('searchInput')

const allOrders = [];

fetch("http://localhost:8080/orders/all/api").
then(response => response.json()).
then(data => {
    for (let order of data) {
        allOrders.push(order);
    }
    displayOrders(allOrders)
})


searchBar.addEventListener('keyup', (e) => {
    const searchCharacters = searchBar.value.toLowerCase();

    if(searchCharacters.length === 0){
        displayOrders(allOrders);
    }

    let filteredOrders = allOrders.filter(order => {
        return order.clientFullName.toLowerCase().includes(searchCharacters);
    });

    displayOrders(filteredOrders);
})


const displayOrders = (orders) => {
    ordersList.innerHTML = orders
        .map((o) => {
            return '<tr>' +
            '<td>' + o.productName + '</td>' +
            '<td>' + o.clientFullName + '</td>' +
            '<td>' + o.created+ '</td>' +
          '</tr>'
        })
        .join('');
}