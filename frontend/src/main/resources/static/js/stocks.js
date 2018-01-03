window.onload = function (ev) {
    drawStockChart();
    init();
};

var stocks;

function drawStockChart() {
    stocks = new Chart(document.getElementById('stocks'), {
        type: 'line',
        data: {
            labels: [],
            datasets: [{
                data: [],
                label: "ACME",
                borderColor: "#3e95cd",
                fill: false
            }]
        },
        options: {
            title: {
                display: true,
                text: 'Live Stock Ticker'
            }
        }
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


