window.onload = function (ev) {
    initStocks();
    drawStockChart();
    init();
};

var stocks;

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

function initStocks() {
    fetch('http://localhost:8082/stocks').then(function (response) {
        return response.json();
    }).then(function (response) {
        drawStockChart(response.names);
    });
}

function init() {
    var addBtn = document.getElementById('add');
    addBtn.addEventListener('click', function () {
        var now = new Date();
        var formattedDate = now.getHours()+":"+now.getMinutes()+":"+now.getSeconds();
        stocks.data.labels.push(formattedDate);
        stocks.data.datasets[ 0 ].data.push(1000);
        stocks.update();
    });
}

function getRandomColor() {
    var letters = '0123456789A';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 10)];
    }
    return color;
}


