window.onload = function (ev) {
    initStocks();
};

var stocks = null;
var stompClient = null;


function initStocks() {
    // fetch('http://localhost:8082/stocks').then(function (response) {
    fetch('http://localhost:8080/stocknames').then(function (response) {
        return response.json();
    }).then(function (response) {
        drawStockChart(response.names);
    });
}

function drawStockChart(names) {
    var data = {
        labels: [],
        datasets: []
    };
    names.forEach(function (name) {
        data.datasets.push({
            data: [],
            label: name,
            borderColor: getRandomColor(),
            fill: false
        });

    });

    var ws = new WebSocket('ws://localhost:8080/data');
    ws.onmessage = function (event) {
        var data = JSON.parse(event.data);
        updateChart(0, data.value);
    };


    stocks = new Chart(document.getElementById('stocks'), {
        type: 'line',
        data: data,
        options: {
            title: {
                display: true,
                text: 'Live Stock Ticker'
            }
        }
    });
}

function updateChart(index, value) {
    var now = new Date();
    var formattedDate = now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
    stocks.data.labels.push(formattedDate);
    stocks.data.datasets[ index ].data.push(value);
    if (stocks.data.datasets[ index ].data.length > 20) {
        stocks.data.datasets[ index ].data.shift();
        stocks.data.labels.shift();
    }
    stocks.update();
}


// function init() {
//     var addBtn = document.getElementById('add');
//     addBtn.addEventListener('click', function () {
//         var now = new Date();
//         var formattedDate = now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
//         stocks.data.labels.push(formattedDate);
//         stocks.data.datasets[ 0 ].data.push(1000);
//         stocks.update();
//     });
// }

function getRandomColor() {
    var letters = '0123456789A';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[ Math.floor(Math.random() * 10) ];
    }
    return color;
}


